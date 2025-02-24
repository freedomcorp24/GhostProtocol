package org.ghostprotocol.chat.account;

import static org.ghostprotocol.chat.account.AccountsGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/account.proto")
public final class ReactorAccountsGrpc {
    private ReactorAccountsGrpc() {}

    public static ReactorAccountsStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountsStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with Signal accounts.
     * </pre>
     */
    public static final class ReactorAccountsStub extends io.grpc.stub.AbstractStub<ReactorAccountsStub> {
        private AccountsGrpc.AccountsStub delegateStub;

        private ReactorAccountsStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountsGrpc.newStub(channel);
        }

        private ReactorAccountsStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountsGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountsStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorAccountsStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Returns basic identifiers for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.GetAccountIdentityResponse> getAccountIdentity(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.GetAccountIdentityRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getAccountIdentity, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Deletes the authenticated account, purging all associated data in the
         *  process.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteAccountResponse> deleteAccount(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteAccountRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::deleteAccount, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration lock secret for the authenticated account. To remove
         *  a registration lock, please use `ClearRegistrationLock`.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationLockResponse> setRegistrationLock(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationLockRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setRegistrationLock, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Removes any registration lock credentials from the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ClearRegistrationLockResponse> clearRegistrationLock(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ClearRegistrationLockRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::clearRegistrationLock, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Attempts to reserve one of multiple given username hashes. Reserved
         *  usernames may be claimed later via `ConfirmUsernameHash`. This RPC may
         *  fail with a `RESOURCE_EXHAUSTED` status if a rate limit for modifying
         *  usernames has been exceeded, in which case a `retry-after` header
         *  containing an ISO 8601 duration string will be present in the response
         *  trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ReserveUsernameHashResponse> reserveUsernameHash(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ReserveUsernameHashRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::reserveUsernameHash, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the username hash/encrypted username to a previously-reserved value
         *  (see `ReserveUsernameHash`). This RPC may fail with a status of
         *  `FAILED_PRECONDITION` if no reserved username hash was found for the given
         *  account or `NOT_FOUND` if the reservation has lapsed and been claimed by
         *  another caller. It may also  fail with a `RESOURCE_EXHAUSTED` if a rate
         *  limit for modifying usernames has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> confirmUsernameHash(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfirmUsernameHashRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::confirmUsernameHash, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Clears the current username hash, ciphertext, and link for the
         *  authenticated user.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameHashResponse> deleteUsernameHash(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameHashRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::deleteUsernameHash, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Associates the given username ciphertext with the account, replacing any
         *  previously stored ciphertext. A new link handle will optionally be created,
         *  and the link handle to use will be returned in any event.
         * 
         *  This RPC may fail with a status of `FAILED_PRECONDITION` if the
         *  authenticated account does not have a username. It may also fail with
         *  `RESOURCE_EXHAUSTED` if a rate limit for modifying username links has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetUsernameLinkResponse> setUsernameLink(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetUsernameLinkRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setUsernameLink, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Clears any username link associated with the authenticated account. This
         *  RPC may fail with `RESOURCE_EXHAUSTED` if a rate limit for modifying
         *  username links has been exceeded, in which case a `retry-after` header
         *  containing an ISO 8601 duration string will be present in the response
         *  trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> deleteUsernameLink(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameLinkRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::deleteUsernameLink, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Configures &quot;unidentified access&quot; keys and preferences for the authenticated
         *  account. Other users permitted to interact with this account anonymously
         *  may take actions like fetching pre-keys and profiles for this account or
         *  sending sealed-sender messages without providing identifying credentials.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> configureUnidentifiedAccess(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::configureUnidentifiedAccess, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets whether the authenticated account may be discovered by phone number
         *  via the Contact Discovery Service (CDS).
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> setDiscoverableByPhoneNumber(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setDiscoverableByPhoneNumber, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration recovery password for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> setRegistrationRecoveryPassword(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setRegistrationRecoveryPassword, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Returns basic identifiers for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.GetAccountIdentityResponse> getAccountIdentity(org.ghostprotocol.chat.account.GetAccountIdentityRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getAccountIdentity, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Deletes the authenticated account, purging all associated data in the
         *  process.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteAccountResponse> deleteAccount(org.ghostprotocol.chat.account.DeleteAccountRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::deleteAccount, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration lock secret for the authenticated account. To remove
         *  a registration lock, please use `ClearRegistrationLock`.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationLockResponse> setRegistrationLock(org.ghostprotocol.chat.account.SetRegistrationLockRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setRegistrationLock, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Removes any registration lock credentials from the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ClearRegistrationLockResponse> clearRegistrationLock(org.ghostprotocol.chat.account.ClearRegistrationLockRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::clearRegistrationLock, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Attempts to reserve one of multiple given username hashes. Reserved
         *  usernames may be claimed later via `ConfirmUsernameHash`. This RPC may
         *  fail with a `RESOURCE_EXHAUSTED` status if a rate limit for modifying
         *  usernames has been exceeded, in which case a `retry-after` header
         *  containing an ISO 8601 duration string will be present in the response
         *  trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ReserveUsernameHashResponse> reserveUsernameHash(org.ghostprotocol.chat.account.ReserveUsernameHashRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::reserveUsernameHash, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the username hash/encrypted username to a previously-reserved value
         *  (see `ReserveUsernameHash`). This RPC may fail with a status of
         *  `FAILED_PRECONDITION` if no reserved username hash was found for the given
         *  account or `NOT_FOUND` if the reservation has lapsed and been claimed by
         *  another caller. It may also  fail with a `RESOURCE_EXHAUSTED` if a rate
         *  limit for modifying usernames has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> confirmUsernameHash(org.ghostprotocol.chat.account.ConfirmUsernameHashRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::confirmUsernameHash, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Clears the current username hash, ciphertext, and link for the
         *  authenticated user.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameHashResponse> deleteUsernameHash(org.ghostprotocol.chat.account.DeleteUsernameHashRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::deleteUsernameHash, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Associates the given username ciphertext with the account, replacing any
         *  previously stored ciphertext. A new link handle will optionally be created,
         *  and the link handle to use will be returned in any event.
         * 
         *  This RPC may fail with a status of `FAILED_PRECONDITION` if the
         *  authenticated account does not have a username. It may also fail with
         *  `RESOURCE_EXHAUSTED` if a rate limit for modifying username links has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetUsernameLinkResponse> setUsernameLink(org.ghostprotocol.chat.account.SetUsernameLinkRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setUsernameLink, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Clears any username link associated with the authenticated account. This
         *  RPC may fail with `RESOURCE_EXHAUSTED` if a rate limit for modifying
         *  username links has been exceeded, in which case a `retry-after` header
         *  containing an ISO 8601 duration string will be present in the response
         *  trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> deleteUsernameLink(org.ghostprotocol.chat.account.DeleteUsernameLinkRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::deleteUsernameLink, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Configures &quot;unidentified access&quot; keys and preferences for the authenticated
         *  account. Other users permitted to interact with this account anonymously
         *  may take actions like fetching pre-keys and profiles for this account or
         *  sending sealed-sender messages without providing identifying credentials.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> configureUnidentifiedAccess(org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::configureUnidentifiedAccess, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets whether the authenticated account may be discovered by phone number
         *  via the Contact Discovery Service (CDS).
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> setDiscoverableByPhoneNumber(org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setDiscoverableByPhoneNumber, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration recovery password for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> setRegistrationRecoveryPassword(org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setRegistrationRecoveryPassword, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with Signal accounts.
     * </pre>
     */
    public static abstract class AccountsImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Returns basic identifiers for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.GetAccountIdentityResponse> getAccountIdentity(org.ghostprotocol.chat.account.GetAccountIdentityRequest request) {
            return getAccountIdentity(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Returns basic identifiers for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.GetAccountIdentityResponse> getAccountIdentity(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.GetAccountIdentityRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Deletes the authenticated account, purging all associated data in the
         *  process.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteAccountResponse> deleteAccount(org.ghostprotocol.chat.account.DeleteAccountRequest request) {
            return deleteAccount(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Deletes the authenticated account, purging all associated data in the
         *  process.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteAccountResponse> deleteAccount(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteAccountRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration lock secret for the authenticated account. To remove
         *  a registration lock, please use `ClearRegistrationLock`.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationLockResponse> setRegistrationLock(org.ghostprotocol.chat.account.SetRegistrationLockRequest request) {
            return setRegistrationLock(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration lock secret for the authenticated account. To remove
         *  a registration lock, please use `ClearRegistrationLock`.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationLockResponse> setRegistrationLock(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationLockRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Removes any registration lock credentials from the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ClearRegistrationLockResponse> clearRegistrationLock(org.ghostprotocol.chat.account.ClearRegistrationLockRequest request) {
            return clearRegistrationLock(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Removes any registration lock credentials from the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ClearRegistrationLockResponse> clearRegistrationLock(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ClearRegistrationLockRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Attempts to reserve one of multiple given username hashes. Reserved
         *  usernames may be claimed later via `ConfirmUsernameHash`. This RPC may
         *  fail with a `RESOURCE_EXHAUSTED` status if a rate limit for modifying
         *  usernames has been exceeded, in which case a `retry-after` header
         *  containing an ISO 8601 duration string will be present in the response
         *  trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ReserveUsernameHashResponse> reserveUsernameHash(org.ghostprotocol.chat.account.ReserveUsernameHashRequest request) {
            return reserveUsernameHash(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Attempts to reserve one of multiple given username hashes. Reserved
         *  usernames may be claimed later via `ConfirmUsernameHash`. This RPC may
         *  fail with a `RESOURCE_EXHAUSTED` status if a rate limit for modifying
         *  usernames has been exceeded, in which case a `retry-after` header
         *  containing an ISO 8601 duration string will be present in the response
         *  trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ReserveUsernameHashResponse> reserveUsernameHash(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ReserveUsernameHashRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets the username hash/encrypted username to a previously-reserved value
         *  (see `ReserveUsernameHash`). This RPC may fail with a status of
         *  `FAILED_PRECONDITION` if no reserved username hash was found for the given
         *  account or `NOT_FOUND` if the reservation has lapsed and been claimed by
         *  another caller. It may also  fail with a `RESOURCE_EXHAUSTED` if a rate
         *  limit for modifying usernames has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> confirmUsernameHash(org.ghostprotocol.chat.account.ConfirmUsernameHashRequest request) {
            return confirmUsernameHash(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets the username hash/encrypted username to a previously-reserved value
         *  (see `ReserveUsernameHash`). This RPC may fail with a status of
         *  `FAILED_PRECONDITION` if no reserved username hash was found for the given
         *  account or `NOT_FOUND` if the reservation has lapsed and been claimed by
         *  another caller. It may also  fail with a `RESOURCE_EXHAUSTED` if a rate
         *  limit for modifying usernames has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> confirmUsernameHash(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfirmUsernameHashRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Clears the current username hash, ciphertext, and link for the
         *  authenticated user.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameHashResponse> deleteUsernameHash(org.ghostprotocol.chat.account.DeleteUsernameHashRequest request) {
            return deleteUsernameHash(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Clears the current username hash, ciphertext, and link for the
         *  authenticated user.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameHashResponse> deleteUsernameHash(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameHashRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Associates the given username ciphertext with the account, replacing any
         *  previously stored ciphertext. A new link handle will optionally be created,
         *  and the link handle to use will be returned in any event.
         * 
         *  This RPC may fail with a status of `FAILED_PRECONDITION` if the
         *  authenticated account does not have a username. It may also fail with
         *  `RESOURCE_EXHAUSTED` if a rate limit for modifying username links has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetUsernameLinkResponse> setUsernameLink(org.ghostprotocol.chat.account.SetUsernameLinkRequest request) {
            return setUsernameLink(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Associates the given username ciphertext with the account, replacing any
         *  previously stored ciphertext. A new link handle will optionally be created,
         *  and the link handle to use will be returned in any event.
         * 
         *  This RPC may fail with a status of `FAILED_PRECONDITION` if the
         *  authenticated account does not have a username. It may also fail with
         *  `RESOURCE_EXHAUSTED` if a rate limit for modifying username links has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetUsernameLinkResponse> setUsernameLink(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetUsernameLinkRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Clears any username link associated with the authenticated account. This
         *  RPC may fail with `RESOURCE_EXHAUSTED` if a rate limit for modifying
         *  username links has been exceeded, in which case a `retry-after` header
         *  containing an ISO 8601 duration string will be present in the response
         *  trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> deleteUsernameLink(org.ghostprotocol.chat.account.DeleteUsernameLinkRequest request) {
            return deleteUsernameLink(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Clears any username link associated with the authenticated account. This
         *  RPC may fail with `RESOURCE_EXHAUSTED` if a rate limit for modifying
         *  username links has been exceeded, in which case a `retry-after` header
         *  containing an ISO 8601 duration string will be present in the response
         *  trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> deleteUsernameLink(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.DeleteUsernameLinkRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Configures &quot;unidentified access&quot; keys and preferences for the authenticated
         *  account. Other users permitted to interact with this account anonymously
         *  may take actions like fetching pre-keys and profiles for this account or
         *  sending sealed-sender messages without providing identifying credentials.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> configureUnidentifiedAccess(org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest request) {
            return configureUnidentifiedAccess(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Configures &quot;unidentified access&quot; keys and preferences for the authenticated
         *  account. Other users permitted to interact with this account anonymously
         *  may take actions like fetching pre-keys and profiles for this account or
         *  sending sealed-sender messages without providing identifying credentials.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> configureUnidentifiedAccess(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets whether the authenticated account may be discovered by phone number
         *  via the Contact Discovery Service (CDS).
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> setDiscoverableByPhoneNumber(org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest request) {
            return setDiscoverableByPhoneNumber(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets whether the authenticated account may be discovered by phone number
         *  via the Contact Discovery Service (CDS).
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> setDiscoverableByPhoneNumber(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration recovery password for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> setRegistrationRecoveryPassword(org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest request) {
            return setRegistrationRecoveryPassword(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration recovery password for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> setRegistrationRecoveryPassword(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getGetAccountIdentityMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.GetAccountIdentityRequest,
                                            org.ghostprotocol.chat.account.GetAccountIdentityResponse>(
                                            this, METHODID_GET_ACCOUNT_IDENTITY)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getDeleteAccountMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.DeleteAccountRequest,
                                            org.ghostprotocol.chat.account.DeleteAccountResponse>(
                                            this, METHODID_DELETE_ACCOUNT)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getSetRegistrationLockMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.SetRegistrationLockRequest,
                                            org.ghostprotocol.chat.account.SetRegistrationLockResponse>(
                                            this, METHODID_SET_REGISTRATION_LOCK)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getClearRegistrationLockMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.ClearRegistrationLockRequest,
                                            org.ghostprotocol.chat.account.ClearRegistrationLockResponse>(
                                            this, METHODID_CLEAR_REGISTRATION_LOCK)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getReserveUsernameHashMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.ReserveUsernameHashRequest,
                                            org.ghostprotocol.chat.account.ReserveUsernameHashResponse>(
                                            this, METHODID_RESERVE_USERNAME_HASH)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getConfirmUsernameHashMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.ConfirmUsernameHashRequest,
                                            org.ghostprotocol.chat.account.ConfirmUsernameHashResponse>(
                                            this, METHODID_CONFIRM_USERNAME_HASH)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getDeleteUsernameHashMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.DeleteUsernameHashRequest,
                                            org.ghostprotocol.chat.account.DeleteUsernameHashResponse>(
                                            this, METHODID_DELETE_USERNAME_HASH)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getSetUsernameLinkMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.SetUsernameLinkRequest,
                                            org.ghostprotocol.chat.account.SetUsernameLinkResponse>(
                                            this, METHODID_SET_USERNAME_LINK)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getDeleteUsernameLinkMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.DeleteUsernameLinkRequest,
                                            org.ghostprotocol.chat.account.DeleteUsernameLinkResponse>(
                                            this, METHODID_DELETE_USERNAME_LINK)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getConfigureUnidentifiedAccessMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest,
                                            org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse>(
                                            this, METHODID_CONFIGURE_UNIDENTIFIED_ACCESS)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getSetDiscoverableByPhoneNumberMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest,
                                            org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse>(
                                            this, METHODID_SET_DISCOVERABLE_BY_PHONE_NUMBER)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsGrpc.getSetRegistrationRecoveryPasswordMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest,
                                            org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse>(
                                            this, METHODID_SET_REGISTRATION_RECOVERY_PASSWORD)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_ACCOUNT_IDENTITY = 0;
    public static final int METHODID_DELETE_ACCOUNT = 1;
    public static final int METHODID_SET_REGISTRATION_LOCK = 2;
    public static final int METHODID_CLEAR_REGISTRATION_LOCK = 3;
    public static final int METHODID_RESERVE_USERNAME_HASH = 4;
    public static final int METHODID_CONFIRM_USERNAME_HASH = 5;
    public static final int METHODID_DELETE_USERNAME_HASH = 6;
    public static final int METHODID_SET_USERNAME_LINK = 7;
    public static final int METHODID_DELETE_USERNAME_LINK = 8;
    public static final int METHODID_CONFIGURE_UNIDENTIFIED_ACCESS = 9;
    public static final int METHODID_SET_DISCOVERABLE_BY_PHONE_NUMBER = 10;
    public static final int METHODID_SET_REGISTRATION_RECOVERY_PASSWORD = 11;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountsImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountsImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_ACCOUNT_IDENTITY:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.GetAccountIdentityRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.GetAccountIdentityResponse>) responseObserver,
                            serviceImpl::getAccountIdentity, serviceImpl::onErrorMap);
                    break;
                case METHODID_DELETE_ACCOUNT:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.DeleteAccountRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteAccountResponse>) responseObserver,
                            serviceImpl::deleteAccount, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_REGISTRATION_LOCK:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.SetRegistrationLockRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetRegistrationLockResponse>) responseObserver,
                            serviceImpl::setRegistrationLock, serviceImpl::onErrorMap);
                    break;
                case METHODID_CLEAR_REGISTRATION_LOCK:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.ClearRegistrationLockRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ClearRegistrationLockResponse>) responseObserver,
                            serviceImpl::clearRegistrationLock, serviceImpl::onErrorMap);
                    break;
                case METHODID_RESERVE_USERNAME_HASH:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.ReserveUsernameHashRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ReserveUsernameHashResponse>) responseObserver,
                            serviceImpl::reserveUsernameHash, serviceImpl::onErrorMap);
                    break;
                case METHODID_CONFIRM_USERNAME_HASH:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.ConfirmUsernameHashRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse>) responseObserver,
                            serviceImpl::confirmUsernameHash, serviceImpl::onErrorMap);
                    break;
                case METHODID_DELETE_USERNAME_HASH:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.DeleteUsernameHashRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteUsernameHashResponse>) responseObserver,
                            serviceImpl::deleteUsernameHash, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_USERNAME_LINK:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.SetUsernameLinkRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetUsernameLinkResponse>) responseObserver,
                            serviceImpl::setUsernameLink, serviceImpl::onErrorMap);
                    break;
                case METHODID_DELETE_USERNAME_LINK:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.DeleteUsernameLinkRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse>) responseObserver,
                            serviceImpl::deleteUsernameLink, serviceImpl::onErrorMap);
                    break;
                case METHODID_CONFIGURE_UNIDENTIFIED_ACCESS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse>) responseObserver,
                            serviceImpl::configureUnidentifiedAccess, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_DISCOVERABLE_BY_PHONE_NUMBER:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse>) responseObserver,
                            serviceImpl::setDiscoverableByPhoneNumber, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_REGISTRATION_RECOVERY_PASSWORD:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse>) responseObserver,
                            serviceImpl::setRegistrationRecoveryPassword, serviceImpl::onErrorMap);
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
