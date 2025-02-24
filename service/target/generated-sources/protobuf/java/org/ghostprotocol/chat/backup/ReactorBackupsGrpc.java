package org.ghostprotocol.chat.backup;

import static org.ghostprotocol.chat.backup.BackupsGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/backups.proto")
public final class ReactorBackupsGrpc {
    private ReactorBackupsGrpc() {}

    public static ReactorBackupsStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorBackupsStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Service for backup operations that require account authentication.
     * 
     *  Most actual backup operations operate on the backup-id and cannot be linked
     *  to the caller&#39;s account, but setting up anonymous credentials and changing
     *  backup tier requires account authentication.
     * 
     *  All rpcs on this service may return these errors. rpc specific errors
     *  documented on the individual rpc.
     * 
     *  errors:
     *  UNAUTHENTICATED     Authentication failed or the account does not exist
     *  INVALID_ARGUMENT    The request did not meet a documented requirement
     *  RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
     *                      ISO8601 duration string will be present in the response
     *                      trailers.
     * </pre>
     */
    public static final class ReactorBackupsStub extends io.grpc.stub.AbstractStub<ReactorBackupsStub> {
        private BackupsGrpc.BackupsStub delegateStub;

        private ReactorBackupsStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = BackupsGrpc.newStub(channel);
        }

        private ReactorBackupsStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = BackupsGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorBackupsStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorBackupsStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Set a (blinded) backup-id for the account.
         * 
         *  Each account may have a single active backup-id that can be used
         *  to store and retrieve backups. Once the backup-id is set,
         *  BackupAuthCredentials can be generated using GetBackupAuthCredentials.
         * 
         *  The blinded backup-id and the key-pair used to blind it must be derived
         *  from a recoverable secret.
         * 
         *  errors:
         *  PERMISSION_DENIED: This account is not currently eligible for backups
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetBackupIdResponse> setBackupId(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetBackupIdRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setBackupId, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Redeem a receipt acquired from /v1/subscription/{subscriberId}/receipt_credentials
         *  to mark the account as eligible for the paid backup tier.
         * 
         *  After successful redemption, subsequent requests to
         *  GetBackupAuthCredentials will return credentials with the level on the
         *  provided receipt until the expiration time on the receipt.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RedeemReceiptResponse> redeemReceipt(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RedeemReceiptRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::redeemReceipt, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  After setting a blinded backup-id with PUT /v1/archives/, this fetches
         *  credentials that can be used to perform operations against that backup-id.
         *  Clients may (and should) request up to 7 days of credentials at a time.
         * 
         *  The redemption_start and redemption_end seconds must be UTC day aligned, and
         *  must not span more than 7 days.
         * 
         *  Each credential contains a receipt level which indicates the backup level
         *  the credential is good for. If the account has paid backup access that
         *  expires at some point in the provided redemption window, credentials with
         *  redemption times after the expiration may be on a lower backup level.
         * 
         *  Clients must validate the receipt level on the credential matches a known
         *  receipt level before using it.
         * 
         *  errors:
         *  NOT_FOUND: Could not find an existing blinded backup id associated with the
         *             account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> getBackupAuthCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getBackupAuthCredentials, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Set a (blinded) backup-id for the account.
         * 
         *  Each account may have a single active backup-id that can be used
         *  to store and retrieve backups. Once the backup-id is set,
         *  BackupAuthCredentials can be generated using GetBackupAuthCredentials.
         * 
         *  The blinded backup-id and the key-pair used to blind it must be derived
         *  from a recoverable secret.
         * 
         *  errors:
         *  PERMISSION_DENIED: This account is not currently eligible for backups
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetBackupIdResponse> setBackupId(org.ghostprotocol.chat.backup.SetBackupIdRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setBackupId, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Redeem a receipt acquired from /v1/subscription/{subscriberId}/receipt_credentials
         *  to mark the account as eligible for the paid backup tier.
         * 
         *  After successful redemption, subsequent requests to
         *  GetBackupAuthCredentials will return credentials with the level on the
         *  provided receipt until the expiration time on the receipt.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RedeemReceiptResponse> redeemReceipt(org.ghostprotocol.chat.backup.RedeemReceiptRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::redeemReceipt, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  After setting a blinded backup-id with PUT /v1/archives/, this fetches
         *  credentials that can be used to perform operations against that backup-id.
         *  Clients may (and should) request up to 7 days of credentials at a time.
         * 
         *  The redemption_start and redemption_end seconds must be UTC day aligned, and
         *  must not span more than 7 days.
         * 
         *  Each credential contains a receipt level which indicates the backup level
         *  the credential is good for. If the account has paid backup access that
         *  expires at some point in the provided redemption window, credentials with
         *  redemption times after the expiration may be on a lower backup level.
         * 
         *  Clients must validate the receipt level on the credential matches a known
         *  receipt level before using it.
         * 
         *  errors:
         *  NOT_FOUND: Could not find an existing blinded backup id associated with the
         *             account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> getBackupAuthCredentials(org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getBackupAuthCredentials, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Service for backup operations that require account authentication.
     * 
     *  Most actual backup operations operate on the backup-id and cannot be linked
     *  to the caller&#39;s account, but setting up anonymous credentials and changing
     *  backup tier requires account authentication.
     * 
     *  All rpcs on this service may return these errors. rpc specific errors
     *  documented on the individual rpc.
     * 
     *  errors:
     *  UNAUTHENTICATED     Authentication failed or the account does not exist
     *  INVALID_ARGUMENT    The request did not meet a documented requirement
     *  RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
     *                      ISO8601 duration string will be present in the response
     *                      trailers.
     * </pre>
     */
    public static abstract class BackupsImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Set a (blinded) backup-id for the account.
         * 
         *  Each account may have a single active backup-id that can be used
         *  to store and retrieve backups. Once the backup-id is set,
         *  BackupAuthCredentials can be generated using GetBackupAuthCredentials.
         * 
         *  The blinded backup-id and the key-pair used to blind it must be derived
         *  from a recoverable secret.
         * 
         *  errors:
         *  PERMISSION_DENIED: This account is not currently eligible for backups
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetBackupIdResponse> setBackupId(org.ghostprotocol.chat.backup.SetBackupIdRequest request) {
            return setBackupId(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Set a (blinded) backup-id for the account.
         * 
         *  Each account may have a single active backup-id that can be used
         *  to store and retrieve backups. Once the backup-id is set,
         *  BackupAuthCredentials can be generated using GetBackupAuthCredentials.
         * 
         *  The blinded backup-id and the key-pair used to blind it must be derived
         *  from a recoverable secret.
         * 
         *  errors:
         *  PERMISSION_DENIED: This account is not currently eligible for backups
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetBackupIdResponse> setBackupId(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetBackupIdRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Redeem a receipt acquired from /v1/subscription/{subscriberId}/receipt_credentials
         *  to mark the account as eligible for the paid backup tier.
         * 
         *  After successful redemption, subsequent requests to
         *  GetBackupAuthCredentials will return credentials with the level on the
         *  provided receipt until the expiration time on the receipt.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RedeemReceiptResponse> redeemReceipt(org.ghostprotocol.chat.backup.RedeemReceiptRequest request) {
            return redeemReceipt(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Redeem a receipt acquired from /v1/subscription/{subscriberId}/receipt_credentials
         *  to mark the account as eligible for the paid backup tier.
         * 
         *  After successful redemption, subsequent requests to
         *  GetBackupAuthCredentials will return credentials with the level on the
         *  provided receipt until the expiration time on the receipt.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RedeemReceiptResponse> redeemReceipt(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RedeemReceiptRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  After setting a blinded backup-id with PUT /v1/archives/, this fetches
         *  credentials that can be used to perform operations against that backup-id.
         *  Clients may (and should) request up to 7 days of credentials at a time.
         * 
         *  The redemption_start and redemption_end seconds must be UTC day aligned, and
         *  must not span more than 7 days.
         * 
         *  Each credential contains a receipt level which indicates the backup level
         *  the credential is good for. If the account has paid backup access that
         *  expires at some point in the provided redemption window, credentials with
         *  redemption times after the expiration may be on a lower backup level.
         * 
         *  Clients must validate the receipt level on the credential matches a known
         *  receipt level before using it.
         * 
         *  errors:
         *  NOT_FOUND: Could not find an existing blinded backup id associated with the
         *             account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> getBackupAuthCredentials(org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest request) {
            return getBackupAuthCredentials(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  After setting a blinded backup-id with PUT /v1/archives/, this fetches
         *  credentials that can be used to perform operations against that backup-id.
         *  Clients may (and should) request up to 7 days of credentials at a time.
         * 
         *  The redemption_start and redemption_end seconds must be UTC day aligned, and
         *  must not span more than 7 days.
         * 
         *  Each credential contains a receipt level which indicates the backup level
         *  the credential is good for. If the account has paid backup access that
         *  expires at some point in the provided redemption window, credentials with
         *  redemption times after the expiration may be on a lower backup level.
         * 
         *  Clients must validate the receipt level on the credential matches a known
         *  receipt level before using it.
         * 
         *  errors:
         *  NOT_FOUND: Could not find an existing blinded backup id associated with the
         *             account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> getBackupAuthCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsGrpc.getSetBackupIdMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.SetBackupIdRequest,
                                            org.ghostprotocol.chat.backup.SetBackupIdResponse>(
                                            this, METHODID_SET_BACKUP_ID)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsGrpc.getRedeemReceiptMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.RedeemReceiptRequest,
                                            org.ghostprotocol.chat.backup.RedeemReceiptResponse>(
                                            this, METHODID_REDEEM_RECEIPT)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsGrpc.getGetBackupAuthCredentialsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest,
                                            org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse>(
                                            this, METHODID_GET_BACKUP_AUTH_CREDENTIALS)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_SET_BACKUP_ID = 0;
    public static final int METHODID_REDEEM_RECEIPT = 1;
    public static final int METHODID_GET_BACKUP_AUTH_CREDENTIALS = 2;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final BackupsImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(BackupsImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SET_BACKUP_ID:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.SetBackupIdRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.SetBackupIdResponse>) responseObserver,
                            serviceImpl::setBackupId, serviceImpl::onErrorMap);
                    break;
                case METHODID_REDEEM_RECEIPT:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.RedeemReceiptRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.RedeemReceiptResponse>) responseObserver,
                            serviceImpl::redeemReceipt, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_BACKUP_AUTH_CREDENTIALS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse>) responseObserver,
                            serviceImpl::getBackupAuthCredentials, serviceImpl::onErrorMap);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
