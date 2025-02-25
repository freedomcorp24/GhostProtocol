/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.workers;

import static com.codahale.metrics.MetricRegistry.name;

import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.DeserializationFeature;
import io.dropwizard.core.setup.Environment;
import io.lettuce.core.resource.ClientResources;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.time.Clock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import org.ghostprotocol.protocol.zkgroup.GenericServerSecretParams;
import org.ghostprotocol.protocol.zkgroup.InvalidInputException;
import org.ghostprotocol.service.GhostProtocolServerConfiguration;
import org.ghostprotocol.service.attachments.TusAttachmentGenerator;
import org.ghostprotocol.service.auth.DisconnectionRequestManager;
import org.ghostprotocol.service.auth.ExternalServiceCredentialsGenerator;
import org.ghostprotocol.service.backup.BackupManager;
import org.ghostprotocol.service.backup.BackupsDb;
import org.ghostprotocol.service.backup.Cdn3BackupCredentialGenerator;
import org.ghostprotocol.service.backup.Cdn3RemoteStorageManager;
import org.ghostprotocol.service.configuration.dynamic.DynamicConfiguration;
import org.ghostprotocol.service.controllers.SecureStorageController;
import org.ghostprotocol.service.controllers.SecureValueRecovery2Controller;
import org.ghostprotocol.service.experiment.PushNotificationExperimentSamples;
import org.ghostprotocol.service.limits.RateLimiters;
import org.ghostprotocol.service.metrics.MicrometerAwsSdkMetricPublisher;
import org.ghostprotocol.service.push.APNSender;
import org.ghostprotocol.service.push.FcmSender;
import org.ghostprotocol.service.push.WebSocketConnectionEventManager;
import org.ghostprotocol.service.push.PushNotificationManager;
import org.ghostprotocol.service.push.PushNotificationScheduler;
import org.ghostprotocol.service.redis.FaultTolerantRedisClient;
import org.ghostprotocol.service.redis.FaultTolerantRedisClusterClient;
import org.ghostprotocol.service.securestorage.SecureStorageClient;
import org.ghostprotocol.service.securevaluerecovery.SecureValueRecovery2Client;
import org.ghostprotocol.service.storage.AccountLockManager;
import org.ghostprotocol.service.storage.Accounts;
import org.ghostprotocol.service.storage.AccountsManager;
import org.ghostprotocol.service.storage.ClientPublicKeys;
import org.ghostprotocol.service.storage.ClientPublicKeysManager;
import org.ghostprotocol.service.storage.DynamicConfigurationManager;
import org.ghostprotocol.service.storage.IssuedReceiptsManager;
import org.ghostprotocol.service.storage.KeysManager;
import org.ghostprotocol.service.storage.MessagesCache;
import org.ghostprotocol.service.storage.MessagesDynamoDb;
import org.ghostprotocol.service.storage.MessagesManager;
import org.ghostprotocol.service.storage.PhoneNumberIdentifiers;
import org.ghostprotocol.service.storage.Profiles;
import org.ghostprotocol.service.storage.ProfilesManager;
import org.ghostprotocol.service.storage.RegistrationRecoveryPasswords;
import org.ghostprotocol.service.storage.RegistrationRecoveryPasswordsManager;
import org.ghostprotocol.service.storage.ReportMessageDynamoDb;
import org.ghostprotocol.service.storage.ReportMessageManager;
import org.ghostprotocol.service.util.ManagedAwsCrt;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

/**
 * Construct utilities commonly used by worker commands
 */
record CommandDependencies(
    AccountsManager accountsManager,
    ProfilesManager profilesManager,
    ReportMessageManager reportMessageManager,
    MessagesCache messagesCache,
    MessagesManager messagesManager,
    KeysManager keysManager,
    RegistrationRecoveryPasswordsManager registrationRecoveryPasswordsManager,
    APNSender apnSender,
    FcmSender fcmSender,
    PushNotificationManager pushNotificationManager,
    PushNotificationExperimentSamples pushNotificationExperimentSamples,
    FaultTolerantRedisClusterClient cacheCluster,
    FaultTolerantRedisClusterClient pushSchedulerCluster,
    ClientResources.Builder redisClusterClientResourcesBuilder,
    BackupManager backupManager,
    IssuedReceiptsManager issuedReceiptsManager,
    DynamicConfigurationManager<DynamicConfiguration> dynamicConfigurationManager,
    DynamoDbAsyncClient dynamoDbAsyncClient,
    PhoneNumberIdentifiers phoneNumberIdentifiers) {

  static CommandDependencies build(
      final String name,
      final Environment environment,
      final GhostProtocolServerConfiguration configuration)
      throws IOException, CertificateException, NoSuchAlgorithmException, InvalidKeyException {
    Clock clock = Clock.systemUTC();

    environment.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    final AwsCredentialsProvider awsCredentialsProvider = configuration.getAwsCredentialsConfiguration().build();

    ScheduledExecutorService dynamicConfigurationExecutor = environment.lifecycle()
        .scheduledExecutorService(name(name, "dynamicConfiguration-%d")).threads(1).build();

    DynamicConfigurationManager<DynamicConfiguration> dynamicConfigurationManager =
        new DynamicConfigurationManager<>(
            configuration.getDynamicConfig().build(awsCredentialsProvider, dynamicConfigurationExecutor), DynamicConfiguration.class);
    dynamicConfigurationManager.start();

    final ClientResources.Builder redisClientResourcesBuilder = ClientResources.builder();

    FaultTolerantRedisClusterClient cacheCluster = configuration.getCacheClusterConfiguration()
        .build("main_cache", redisClientResourcesBuilder);
    FaultTolerantRedisClusterClient pushSchedulerCluster = configuration.getPushSchedulerCluster()
        .build("push_scheduler", redisClientResourcesBuilder);
    FaultTolerantRedisClient pubsubClient =
        configuration.getRedisPubSubConfiguration().build("pubsub", redisClientResourcesBuilder.build());

    Scheduler messageDeliveryScheduler = Schedulers.fromExecutorService(
        environment.lifecycle().executorService("messageDelivery").minThreads(4).maxThreads(4).build());
    ExecutorService messageDeletionExecutor = environment.lifecycle()
        .executorService(name(name, "messageDeletion-%d")).minThreads(4).maxThreads(4).build();
    ExecutorService secureValueRecoveryServiceExecutor = environment.lifecycle()
        .executorService(name(name, "secureValueRecoveryService-%d")).maxThreads(8).minThreads(8).build();
    ExecutorService storageServiceExecutor = environment.lifecycle()
        .executorService(name(name, "storageService-%d")).maxThreads(8).minThreads(8).build();
    ExecutorService accountLockExecutor = environment.lifecycle()
        .executorService(name(name, "accountLock-%d")).minThreads(8).maxThreads(8).build();
    ExecutorService remoteStorageHttpExecutor = environment.lifecycle()
        .executorService(name(name, "remoteStorage-%d"))
        .minThreads(0).maxThreads(Integer.MAX_VALUE).workQueue(new SynchronousQueue<>())
        .keepAliveTime(io.dropwizard.util.Duration.seconds(60L)).build();
    ExecutorService apnSenderExecutor = environment.lifecycle().executorService(name(name, "apnSender-%d"))
        .maxThreads(1).minThreads(1).build();
    ExecutorService fcmSenderExecutor = environment.lifecycle().executorService(name(name, "fcmSender-%d"))
        .maxThreads(16).minThreads(16).build();
    ExecutorService clientEventExecutor = environment.lifecycle()
        .virtualExecutorService(name(name, "clientEvent-%d"));
    ExecutorService asyncOperationQueueingExecutor = environment.lifecycle()
        .executorService(name(name, "asyncOperationQueueing-%d")).minThreads(1).maxThreads(1).build();
    ExecutorService disconnectionRequestListenerExecutor = environment.lifecycle()
        .virtualExecutorService(name(name, "disconnectionRequest-%d"));

    ScheduledExecutorService secureValueRecoveryServiceRetryExecutor = environment.lifecycle()
        .scheduledExecutorService(name(name, "secureValueRecoveryServiceRetry-%d")).threads(1).build();
    ScheduledExecutorService remoteStorageRetryExecutor = environment.lifecycle()
        .scheduledExecutorService(name(name, "remoteStorageRetry-%d")).threads(1).build();
    ScheduledExecutorService storageServiceRetryExecutor = environment.lifecycle()
        .scheduledExecutorService(name(name, "storageServiceRetry-%d")).threads(1).build();
    ScheduledExecutorService messagePollExecutor = environment.lifecycle()
        .scheduledExecutorService(name(name, "messagePollExecutor-%d")).threads(1).build();

    ExternalServiceCredentialsGenerator storageCredentialsGenerator = SecureStorageController.credentialsGenerator(
        configuration.getSecureStorageServiceConfiguration());
    ExternalServiceCredentialsGenerator secureValueRecovery2CredentialsGenerator = SecureValueRecovery2Controller.credentialsGenerator(
        configuration.getSvr2Configuration());

    final ExecutorService awsSdkMetricsExecutor = environment.lifecycle()
        .virtualExecutorService(MetricRegistry.name(CommandDependencies.class, "awsSdkMetrics-%d"));

    DynamoDbAsyncClient dynamoDbAsyncClient = configuration.getDynamoDbClientConfiguration()
        .buildAsyncClient(awsCredentialsProvider, new MicrometerAwsSdkMetricPublisher(awsSdkMetricsExecutor, "dynamoDbAsyncCommand"));

    DynamoDbClient dynamoDbClient = configuration.getDynamoDbClientConfiguration()
        .buildSyncClient(awsCredentialsProvider, new MicrometerAwsSdkMetricPublisher(awsSdkMetricsExecutor, "dynamoDbSyncCommand"));

    RegistrationRecoveryPasswords registrationRecoveryPasswords = new RegistrationRecoveryPasswords(
        configuration.getDynamoDbTables().getRegistrationRecovery().getTableName(),
        configuration.getDynamoDbTables().getRegistrationRecovery().getExpiration(),
        dynamoDbAsyncClient,
        clock);

    ClientPublicKeys clientPublicKeys =
        new ClientPublicKeys(dynamoDbAsyncClient, configuration.getDynamoDbTables().getClientPublicKeys().getTableName());

    Accounts accounts = new Accounts(
        clock,
        dynamoDbClient,
        dynamoDbAsyncClient,
        configuration.getDynamoDbTables().getAccounts().getTableName(),
        configuration.getDynamoDbTables().getAccounts().getPhoneNumberTableName(),
        configuration.getDynamoDbTables().getAccounts().getPhoneNumberIdentifierTableName(),
        configuration.getDynamoDbTables().getAccounts().getUsernamesTableName(),
        configuration.getDynamoDbTables().getDeletedAccounts().getTableName(),
        configuration.getDynamoDbTables().getAccounts().getUsedLinkDeviceTokensTableName());
    PhoneNumberIdentifiers phoneNumberIdentifiers = new PhoneNumberIdentifiers(dynamoDbAsyncClient,
        configuration.getDynamoDbTables().getPhoneNumberIdentifiers().getTableName());
    Profiles profiles = new Profiles(dynamoDbClient, dynamoDbAsyncClient,
        configuration.getDynamoDbTables().getProfiles().getTableName());
    KeysManager keys = new KeysManager(
            dynamoDbAsyncClient,
        configuration.getDynamoDbTables().getEcKeys().getTableName(),
        configuration.getDynamoDbTables().getKemKeys().getTableName(),
        configuration.getDynamoDbTables().getEcSignedPreKeys().getTableName(),
        configuration.getDynamoDbTables().getKemLastResortKeys().getTableName()
    );
    MessagesDynamoDb messagesDynamoDb = new MessagesDynamoDb(dynamoDbClient, dynamoDbAsyncClient,
        configuration.getDynamoDbTables().getMessages().getTableName(),
        configuration.getDynamoDbTables().getMessages().getExpiration(),
        messageDeletionExecutor);
    FaultTolerantRedisClusterClient messagesCluster = configuration.getMessageCacheConfiguration()
        .getRedisClusterConfiguration().build("messages", redisClientResourcesBuilder);
    FaultTolerantRedisClusterClient rateLimitersCluster = configuration.getRateLimitersCluster().build("rate_limiters",
        redisClientResourcesBuilder);
    SecureValueRecovery2Client secureValueRecovery2Client = new SecureValueRecovery2Client(
        secureValueRecovery2CredentialsGenerator, secureValueRecoveryServiceExecutor,
        secureValueRecoveryServiceRetryExecutor,
        configuration.getSvr2Configuration());
    SecureStorageClient secureStorageClient = new SecureStorageClient(storageCredentialsGenerator,
        storageServiceExecutor, storageServiceRetryExecutor, configuration.getSecureStorageServiceConfiguration());
    DisconnectionRequestManager disconnectionRequestManager = new DisconnectionRequestManager(pubsubClient, disconnectionRequestListenerExecutor);
    MessagesCache messagesCache = new MessagesCache(messagesCluster,
        messageDeliveryScheduler, messageDeletionExecutor, Clock.systemUTC());
    ProfilesManager profilesManager = new ProfilesManager(profiles, cacheCluster);
    ReportMessageDynamoDb reportMessageDynamoDb = new ReportMessageDynamoDb(dynamoDbClient, dynamoDbAsyncClient,
        configuration.getDynamoDbTables().getReportMessage().getTableName(),
        configuration.getReportMessageConfiguration().getReportTtl());
    ReportMessageManager reportMessageManager = new ReportMessageManager(reportMessageDynamoDb, rateLimitersCluster,
        configuration.getReportMessageConfiguration().getCounterTtl());
    MessagesManager messagesManager = new MessagesManager(messagesDynamoDb, messagesCache,
        reportMessageManager, messageDeletionExecutor, Clock.systemUTC());
    AccountLockManager accountLockManager = new AccountLockManager(dynamoDbClient,
        configuration.getDynamoDbTables().getDeletedAccountsLock().getTableName());
    ClientPublicKeysManager clientPublicKeysManager =
        new ClientPublicKeysManager(clientPublicKeys, accountLockManager, accountLockExecutor);
    RegistrationRecoveryPasswordsManager registrationRecoveryPasswordsManager =
        new RegistrationRecoveryPasswordsManager(registrationRecoveryPasswords);
    AccountsManager accountsManager = new AccountsManager(accounts, phoneNumberIdentifiers, cacheCluster,
        pubsubClient, accountLockManager, keys, messagesManager, profilesManager,
        secureStorageClient, secureValueRecovery2Client, disconnectionRequestManager,
        registrationRecoveryPasswordsManager, clientPublicKeysManager, accountLockExecutor, messagePollExecutor,
        clock, configuration.getLinkDeviceSecretConfiguration().secret().value(), dynamicConfigurationManager);
    RateLimiters rateLimiters = RateLimiters.createAndValidate(configuration.getLimitsConfiguration(),
        dynamicConfigurationManager, rateLimitersCluster);
    final BackupsDb backupsDb =
        new BackupsDb(dynamoDbAsyncClient, configuration.getDynamoDbTables().getBackups().getTableName(), clock);
    final GenericServerSecretParams backupsGenericZkSecretParams;
    try {
      backupsGenericZkSecretParams =
          new GenericServerSecretParams(configuration.getBackupsZkConfig().serverSecret().value());
    } catch (InvalidInputException e) {
      throw new IllegalArgumentException(e);
    }
    final BackupManager backupManager = new BackupManager(
        backupsDb,
        backupsGenericZkSecretParams,
        rateLimiters,
        new TusAttachmentGenerator(configuration.getTus()),
        new Cdn3BackupCredentialGenerator(configuration.getTus()),
        new Cdn3RemoteStorageManager(
            remoteStorageHttpExecutor,
            remoteStorageRetryExecutor,
            configuration.getCdn3StorageManagerConfiguration()),
        clock);

    final IssuedReceiptsManager issuedReceiptsManager = new IssuedReceiptsManager(
        configuration.getDynamoDbTables().getIssuedReceipts().getTableName(),
        configuration.getDynamoDbTables().getIssuedReceipts().getExpiration(),
        dynamoDbAsyncClient,
        configuration.getDynamoDbTables().getIssuedReceipts().getGenerator(),
        configuration.getDynamoDbTables().getIssuedReceipts().getmaxIssuedReceiptsPerPaymentId());

    APNSender apnSender = new APNSender(apnSenderExecutor, configuration.getApnConfiguration());
    FcmSender fcmSender = new FcmSender(fcmSenderExecutor, configuration.getFcmConfiguration().credentials().value());
    PushNotificationScheduler pushNotificationScheduler = new PushNotificationScheduler(pushSchedulerCluster,
        apnSender, fcmSender, accountsManager, 0, 0);
    PushNotificationManager pushNotificationManager =
        new PushNotificationManager(accountsManager, apnSender, fcmSender, pushNotificationScheduler);
    PushNotificationExperimentSamples pushNotificationExperimentSamples =
        new PushNotificationExperimentSamples(dynamoDbAsyncClient,
            configuration.getDynamoDbTables().getPushNotificationExperimentSamples().getTableName(),
            Clock.systemUTC());

    WebSocketConnectionEventManager webSocketConnectionEventManager =
        new WebSocketConnectionEventManager(accountsManager, pushNotificationManager, messagesCluster, clientEventExecutor, asyncOperationQueueingExecutor);

    environment.lifecycle().manage(apnSender);
    environment.lifecycle().manage(disconnectionRequestManager);
    environment.lifecycle().manage(webSocketConnectionEventManager);
    environment.lifecycle().manage(new ManagedAwsCrt());

    return new CommandDependencies(
        accountsManager,
        profilesManager,
        reportMessageManager,
        messagesCache,
        messagesManager,
        keys,
        registrationRecoveryPasswordsManager,
        apnSender,
        fcmSender,
        pushNotificationManager,
        pushNotificationExperimentSamples,
        cacheCluster,
        pushSchedulerCluster,
        redisClientResourcesBuilder,
        backupManager,
        issuedReceiptsManager,
        dynamicConfigurationManager,
        dynamoDbAsyncClient,
        phoneNumberIdentifiers
    );
  }

}
