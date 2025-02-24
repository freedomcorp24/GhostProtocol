package org.ghostprotocol.chat.backup;

import static org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/backups.proto")
public final class ReactorBackupsAnonymousGrpc {
    private ReactorBackupsAnonymousGrpc() {}

    public static ReactorBackupsAnonymousStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorBackupsAnonymousStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Service for backup operations with anonymous credentials
     * 
     *  This service never requires account authentication. It instead requires a
     *  backup-id authenticated with an anonymous credential that cannot be linked
     *  to the account.
     * 
     *  To register an anonymous credential:
     *    1. Set a backup-id on the authenticated channel via Backups::SetBackupId
     *    2. Retrieve BackupAuthCredentials via Backups::GetBackupAuthCredentials
     *    3. Generate a key pair and set the public key via
     *       BackupsAnonymous::SetPublicKey
     * 
     *  Unless otherwise noted, requests for this service require a
     *  SignedPresentation, which includes:
     *    - a presentation generated from a BackupAuthCredential issued by
     *      GetBackupAuthCredentials
     *    - a signature of that presentation using the private key of a key pair
     *      previously set with SetPublicKey.
     * 
     *  All RPCs on this service may return these errors. RPC specific errors
     *  documented on the individual RPC.
     * 
     *  errors:
     *  UNAUTHENTICATED     Either the presentation was missing, the credential was
     *                      expired, presentation verification failed, the signature
     *                      was incorrect, there was no committed public key for the
     *                      corresponding backup id, or the request was made on a
     *                      non-anonymous channel.
     *  PERMISSION_DENIED   The credential does not have permission to perform the
     *                      requested action.
     *  RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
     *                      ISO8601 duration string will be present in the response
     *                      trailers.
     *  INVALID_ARGUMENT    The request did not meet a documented requirement
     * </pre>
     */
    public static final class ReactorBackupsAnonymousStub extends io.grpc.stub.AbstractStub<ReactorBackupsAnonymousStub> {
        private BackupsAnonymousGrpc.BackupsAnonymousStub delegateStub;

        private ReactorBackupsAnonymousStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = BackupsAnonymousGrpc.newStub(channel);
        }

        private ReactorBackupsAnonymousStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = BackupsAnonymousGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorBackupsAnonymousStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorBackupsAnonymousStub(channel, callOptions);
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> getCdnCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetCdnCredentialsRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getCdnCredentials, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupInfoResponse> getBackupInfo(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupInfoRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getBackupInfo, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetPublicKeyResponse> setPublicKey(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetPublicKeyRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setPublicKey, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Refresh the backup, indicating that the backup is still active. Clients
         *  must periodically upload new backups or perform a refresh. If a backup has
         *  not been active for 30 days, it may deleted
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RefreshResponse> refresh(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RefreshRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::refresh, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieve an upload form that can be used to perform a resumable upload
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetUploadFormResponse> getUploadForm(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetUploadFormRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getUploadForm, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Copy and re-encrypt media from the attachments cdn into the backup cdn.
         *  The original, already encrypted, attachments will be encrypted with the
         *  provided key material before being copied.
         * 
         *  The copy operation is not atomic and responses will be returned as copy
         *  operations complete with detailed information about the outcome. If an
         *  error is encountered, not all requests may be reflected in the responses.
         * 
         *  On retries, a particular destination media id must not be reused with a
         *  different source media id or different encryption parameters.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.backup.CopyMediaResponse> copyMedia(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.CopyMediaRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactorRequest, delegateStub::copyMedia, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieve a page of media objects stored for this backup-id. A client may
         *  have previously stored media objects that are no longer referenced in their
         *  current backup. To reclaim storage space used by these orphaned objects,
         *  perform a list operation and remove any unreferenced media objects
         *  via DeleteMedia.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.ListMediaResponse> listMedia(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.ListMediaRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::listMedia, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Delete media objects stored with this backup-id. Streams the locations of
         *  media items back when the item has successfully been removed.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.backup.DeleteMediaResponse> deleteMedia(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.DeleteMediaRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactorRequest, delegateStub::deleteMedia, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Delete all backup metadata, objects, and stored public key. To use
         *  backups again, a public key must be resupplied.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.DeleteAllResponse> deleteAll(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.DeleteAllRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::deleteAll, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> getCdnCredentials(org.ghostprotocol.chat.backup.GetCdnCredentialsRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getCdnCredentials, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupInfoResponse> getBackupInfo(org.ghostprotocol.chat.backup.GetBackupInfoRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getBackupInfo, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetPublicKeyResponse> setPublicKey(org.ghostprotocol.chat.backup.SetPublicKeyRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setPublicKey, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Refresh the backup, indicating that the backup is still active. Clients
         *  must periodically upload new backups or perform a refresh. If a backup has
         *  not been active for 30 days, it may deleted
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RefreshResponse> refresh(org.ghostprotocol.chat.backup.RefreshRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::refresh, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieve an upload form that can be used to perform a resumable upload
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetUploadFormResponse> getUploadForm(org.ghostprotocol.chat.backup.GetUploadFormRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getUploadForm, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Copy and re-encrypt media from the attachments cdn into the backup cdn.
         *  The original, already encrypted, attachments will be encrypted with the
         *  provided key material before being copied.
         * 
         *  The copy operation is not atomic and responses will be returned as copy
         *  operations complete with detailed information about the outcome. If an
         *  error is encountered, not all requests may be reflected in the responses.
         * 
         *  On retries, a particular destination media id must not be reused with a
         *  different source media id or different encryption parameters.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.backup.CopyMediaResponse> copyMedia(org.ghostprotocol.chat.backup.CopyMediaRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::copyMedia, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieve a page of media objects stored for this backup-id. A client may
         *  have previously stored media objects that are no longer referenced in their
         *  current backup. To reclaim storage space used by these orphaned objects,
         *  perform a list operation and remove any unreferenced media objects
         *  via DeleteMedia.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.ListMediaResponse> listMedia(org.ghostprotocol.chat.backup.ListMediaRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::listMedia, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Delete media objects stored with this backup-id. Streams the locations of
         *  media items back when the item has successfully been removed.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.backup.DeleteMediaResponse> deleteMedia(org.ghostprotocol.chat.backup.DeleteMediaRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToMany(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::deleteMedia, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Delete all backup metadata, objects, and stored public key. To use
         *  backups again, a public key must be resupplied.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.DeleteAllResponse> deleteAll(org.ghostprotocol.chat.backup.DeleteAllRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::deleteAll, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Service for backup operations with anonymous credentials
     * 
     *  This service never requires account authentication. It instead requires a
     *  backup-id authenticated with an anonymous credential that cannot be linked
     *  to the account.
     * 
     *  To register an anonymous credential:
     *    1. Set a backup-id on the authenticated channel via Backups::SetBackupId
     *    2. Retrieve BackupAuthCredentials via Backups::GetBackupAuthCredentials
     *    3. Generate a key pair and set the public key via
     *       BackupsAnonymous::SetPublicKey
     * 
     *  Unless otherwise noted, requests for this service require a
     *  SignedPresentation, which includes:
     *    - a presentation generated from a BackupAuthCredential issued by
     *      GetBackupAuthCredentials
     *    - a signature of that presentation using the private key of a key pair
     *      previously set with SetPublicKey.
     * 
     *  All RPCs on this service may return these errors. RPC specific errors
     *  documented on the individual RPC.
     * 
     *  errors:
     *  UNAUTHENTICATED     Either the presentation was missing, the credential was
     *                      expired, presentation verification failed, the signature
     *                      was incorrect, there was no committed public key for the
     *                      corresponding backup id, or the request was made on a
     *                      non-anonymous channel.
     *  PERMISSION_DENIED   The credential does not have permission to perform the
     *                      requested action.
     *  RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
     *                      ISO8601 duration string will be present in the response
     *                      trailers.
     *  INVALID_ARGUMENT    The request did not meet a documented requirement
     * </pre>
     */
    public static abstract class BackupsAnonymousImplBase implements io.grpc.BindableService {

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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> getCdnCredentials(org.ghostprotocol.chat.backup.GetCdnCredentialsRequest request) {
            return getCdnCredentials(reactor.core.publisher.Mono.just(request));
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> getCdnCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetCdnCredentialsRequest> request) {
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupInfoResponse> getBackupInfo(org.ghostprotocol.chat.backup.GetBackupInfoRequest request) {
            return getBackupInfo(reactor.core.publisher.Mono.just(request));
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupInfoResponse> getBackupInfo(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetBackupInfoRequest> request) {
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetPublicKeyResponse> setPublicKey(org.ghostprotocol.chat.backup.SetPublicKeyRequest request) {
            return setPublicKey(reactor.core.publisher.Mono.just(request));
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetPublicKeyResponse> setPublicKey(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.SetPublicKeyRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Refresh the backup, indicating that the backup is still active. Clients
         *  must periodically upload new backups or perform a refresh. If a backup has
         *  not been active for 30 days, it may deleted
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RefreshResponse> refresh(org.ghostprotocol.chat.backup.RefreshRequest request) {
            return refresh(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Refresh the backup, indicating that the backup is still active. Clients
         *  must periodically upload new backups or perform a refresh. If a backup has
         *  not been active for 30 days, it may deleted
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RefreshResponse> refresh(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.RefreshRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Retrieve an upload form that can be used to perform a resumable upload
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetUploadFormResponse> getUploadForm(org.ghostprotocol.chat.backup.GetUploadFormRequest request) {
            return getUploadForm(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Retrieve an upload form that can be used to perform a resumable upload
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetUploadFormResponse> getUploadForm(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.GetUploadFormRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Copy and re-encrypt media from the attachments cdn into the backup cdn.
         *  The original, already encrypted, attachments will be encrypted with the
         *  provided key material before being copied.
         * 
         *  The copy operation is not atomic and responses will be returned as copy
         *  operations complete with detailed information about the outcome. If an
         *  error is encountered, not all requests may be reflected in the responses.
         * 
         *  On retries, a particular destination media id must not be reused with a
         *  different source media id or different encryption parameters.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.backup.CopyMediaResponse> copyMedia(org.ghostprotocol.chat.backup.CopyMediaRequest request) {
            return copyMedia(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Copy and re-encrypt media from the attachments cdn into the backup cdn.
         *  The original, already encrypted, attachments will be encrypted with the
         *  provided key material before being copied.
         * 
         *  The copy operation is not atomic and responses will be returned as copy
         *  operations complete with detailed information about the outcome. If an
         *  error is encountered, not all requests may be reflected in the responses.
         * 
         *  On retries, a particular destination media id must not be reused with a
         *  different source media id or different encryption parameters.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.backup.CopyMediaResponse> copyMedia(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.CopyMediaRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Retrieve a page of media objects stored for this backup-id. A client may
         *  have previously stored media objects that are no longer referenced in their
         *  current backup. To reclaim storage space used by these orphaned objects,
         *  perform a list operation and remove any unreferenced media objects
         *  via DeleteMedia.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.ListMediaResponse> listMedia(org.ghostprotocol.chat.backup.ListMediaRequest request) {
            return listMedia(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Retrieve a page of media objects stored for this backup-id. A client may
         *  have previously stored media objects that are no longer referenced in their
         *  current backup. To reclaim storage space used by these orphaned objects,
         *  perform a list operation and remove any unreferenced media objects
         *  via DeleteMedia.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.ListMediaResponse> listMedia(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.ListMediaRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Delete media objects stored with this backup-id. Streams the locations of
         *  media items back when the item has successfully been removed.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.backup.DeleteMediaResponse> deleteMedia(org.ghostprotocol.chat.backup.DeleteMediaRequest request) {
            return deleteMedia(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Delete media objects stored with this backup-id. Streams the locations of
         *  media items back when the item has successfully been removed.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.backup.DeleteMediaResponse> deleteMedia(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.DeleteMediaRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Delete all backup metadata, objects, and stored public key. To use
         *  backups again, a public key must be resupplied.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.DeleteAllResponse> deleteAll(org.ghostprotocol.chat.backup.DeleteAllRequest request) {
            return deleteAll(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Delete all backup metadata, objects, and stored public key. To use
         *  backups again, a public key must be resupplied.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.DeleteAllResponse> deleteAll(reactor.core.publisher.Mono<org.ghostprotocol.chat.backup.DeleteAllRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getGetCdnCredentialsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.GetCdnCredentialsRequest,
                                            org.ghostprotocol.chat.backup.GetCdnCredentialsResponse>(
                                            this, METHODID_GET_CDN_CREDENTIALS)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getGetBackupInfoMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.GetBackupInfoRequest,
                                            org.ghostprotocol.chat.backup.GetBackupInfoResponse>(
                                            this, METHODID_GET_BACKUP_INFO)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getSetPublicKeyMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.SetPublicKeyRequest,
                                            org.ghostprotocol.chat.backup.SetPublicKeyResponse>(
                                            this, METHODID_SET_PUBLIC_KEY)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getRefreshMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.RefreshRequest,
                                            org.ghostprotocol.chat.backup.RefreshResponse>(
                                            this, METHODID_REFRESH)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getGetUploadFormMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.GetUploadFormRequest,
                                            org.ghostprotocol.chat.backup.GetUploadFormResponse>(
                                            this, METHODID_GET_UPLOAD_FORM)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getCopyMediaMethod(),
                            asyncServerStreamingCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.CopyMediaRequest,
                                            org.ghostprotocol.chat.backup.CopyMediaResponse>(
                                            this, METHODID_COPY_MEDIA)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getListMediaMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.ListMediaRequest,
                                            org.ghostprotocol.chat.backup.ListMediaResponse>(
                                            this, METHODID_LIST_MEDIA)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getDeleteMediaMethod(),
                            asyncServerStreamingCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.DeleteMediaRequest,
                                            org.ghostprotocol.chat.backup.DeleteMediaResponse>(
                                            this, METHODID_DELETE_MEDIA)))
                    .addMethod(
                            org.ghostprotocol.chat.backup.BackupsAnonymousGrpc.getDeleteAllMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.backup.DeleteAllRequest,
                                            org.ghostprotocol.chat.backup.DeleteAllResponse>(
                                            this, METHODID_DELETE_ALL)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_CDN_CREDENTIALS = 0;
    public static final int METHODID_GET_BACKUP_INFO = 1;
    public static final int METHODID_SET_PUBLIC_KEY = 2;
    public static final int METHODID_REFRESH = 3;
    public static final int METHODID_GET_UPLOAD_FORM = 4;
    public static final int METHODID_COPY_MEDIA = 5;
    public static final int METHODID_LIST_MEDIA = 6;
    public static final int METHODID_DELETE_MEDIA = 7;
    public static final int METHODID_DELETE_ALL = 8;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final BackupsAnonymousImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(BackupsAnonymousImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_CDN_CREDENTIALS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.GetCdnCredentialsRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse>) responseObserver,
                            serviceImpl::getCdnCredentials, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_BACKUP_INFO:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.GetBackupInfoRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetBackupInfoResponse>) responseObserver,
                            serviceImpl::getBackupInfo, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_PUBLIC_KEY:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.SetPublicKeyRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.SetPublicKeyResponse>) responseObserver,
                            serviceImpl::setPublicKey, serviceImpl::onErrorMap);
                    break;
                case METHODID_REFRESH:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.RefreshRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.RefreshResponse>) responseObserver,
                            serviceImpl::refresh, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_UPLOAD_FORM:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.GetUploadFormRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetUploadFormResponse>) responseObserver,
                            serviceImpl::getUploadForm, serviceImpl::onErrorMap);
                    break;
                case METHODID_COPY_MEDIA:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToMany((org.ghostprotocol.chat.backup.CopyMediaRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.CopyMediaResponse>) responseObserver,
                            serviceImpl::copyMedia, serviceImpl::onErrorMap);
                    break;
                case METHODID_LIST_MEDIA:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.ListMediaRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.ListMediaResponse>) responseObserver,
                            serviceImpl::listMedia, serviceImpl::onErrorMap);
                    break;
                case METHODID_DELETE_MEDIA:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToMany((org.ghostprotocol.chat.backup.DeleteMediaRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.DeleteMediaResponse>) responseObserver,
                            serviceImpl::deleteMedia, serviceImpl::onErrorMap);
                    break;
                case METHODID_DELETE_ALL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.backup.DeleteAllRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.DeleteAllResponse>) responseObserver,
                            serviceImpl::deleteAll, serviceImpl::onErrorMap);
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
