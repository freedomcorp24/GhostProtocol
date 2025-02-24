package org.ghostprotocol.chat.account;

import static org.ghostprotocol.chat.account.AccountsAnonymousGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/account.proto")
public final class ReactorAccountsAnonymousGrpc {
    private ReactorAccountsAnonymousGrpc() {}

    public static ReactorAccountsAnonymousStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorAccountsAnonymousStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for looking up Signal accounts. Callers must not provide
     *  identifying credentials when calling methods in this service.
     * </pre>
     */
    public static final class ReactorAccountsAnonymousStub extends io.grpc.stub.AbstractStub<ReactorAccountsAnonymousStub> {
        private AccountsAnonymousGrpc.AccountsAnonymousStub delegateStub;

        private ReactorAccountsAnonymousStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AccountsAnonymousGrpc.newStub(channel);
        }

        private ReactorAccountsAnonymousStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AccountsAnonymousGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorAccountsAnonymousStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorAccountsAnonymousStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Returns basic identifiers for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.CheckAccountExistenceResponse> checkAccountExistence(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.CheckAccountExistenceRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkAccountExistence, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Deletes the authenticated account, purging all associated data in the
         *  process.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameHashResponse> lookupUsernameHash(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameHashRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::lookupUsernameHash, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration lock secret for the authenticated account. To remove
         *  a registration lock, please use `ClearRegistrationLock`.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameLinkResponse> lookupUsernameLink(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameLinkRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::lookupUsernameLink, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Returns basic identifiers for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.CheckAccountExistenceResponse> checkAccountExistence(org.ghostprotocol.chat.account.CheckAccountExistenceRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkAccountExistence, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Deletes the authenticated account, purging all associated data in the
         *  process.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameHashResponse> lookupUsernameHash(org.ghostprotocol.chat.account.LookupUsernameHashRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::lookupUsernameHash, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration lock secret for the authenticated account. To remove
         *  a registration lock, please use `ClearRegistrationLock`.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameLinkResponse> lookupUsernameLink(org.ghostprotocol.chat.account.LookupUsernameLinkRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::lookupUsernameLink, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for looking up Signal accounts. Callers must not provide
     *  identifying credentials when calling methods in this service.
     * </pre>
     */
    public static abstract class AccountsAnonymousImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Returns basic identifiers for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.CheckAccountExistenceResponse> checkAccountExistence(org.ghostprotocol.chat.account.CheckAccountExistenceRequest request) {
            return checkAccountExistence(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Returns basic identifiers for the authenticated account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.CheckAccountExistenceResponse> checkAccountExistence(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.CheckAccountExistenceRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Deletes the authenticated account, purging all associated data in the
         *  process.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameHashResponse> lookupUsernameHash(org.ghostprotocol.chat.account.LookupUsernameHashRequest request) {
            return lookupUsernameHash(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Deletes the authenticated account, purging all associated data in the
         *  process.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameHashResponse> lookupUsernameHash(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameHashRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration lock secret for the authenticated account. To remove
         *  a registration lock, please use `ClearRegistrationLock`.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameLinkResponse> lookupUsernameLink(org.ghostprotocol.chat.account.LookupUsernameLinkRequest request) {
            return lookupUsernameLink(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets the registration lock secret for the authenticated account. To remove
         *  a registration lock, please use `ClearRegistrationLock`.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameLinkResponse> lookupUsernameLink(reactor.core.publisher.Mono<org.ghostprotocol.chat.account.LookupUsernameLinkRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsAnonymousGrpc.getCheckAccountExistenceMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.CheckAccountExistenceRequest,
                                            org.ghostprotocol.chat.account.CheckAccountExistenceResponse>(
                                            this, METHODID_CHECK_ACCOUNT_EXISTENCE)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsAnonymousGrpc.getLookupUsernameHashMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.LookupUsernameHashRequest,
                                            org.ghostprotocol.chat.account.LookupUsernameHashResponse>(
                                            this, METHODID_LOOKUP_USERNAME_HASH)))
                    .addMethod(
                            org.ghostprotocol.chat.account.AccountsAnonymousGrpc.getLookupUsernameLinkMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.account.LookupUsernameLinkRequest,
                                            org.ghostprotocol.chat.account.LookupUsernameLinkResponse>(
                                            this, METHODID_LOOKUP_USERNAME_LINK)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_CHECK_ACCOUNT_EXISTENCE = 0;
    public static final int METHODID_LOOKUP_USERNAME_HASH = 1;
    public static final int METHODID_LOOKUP_USERNAME_LINK = 2;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AccountsAnonymousImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(AccountsAnonymousImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_ACCOUNT_EXISTENCE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.CheckAccountExistenceRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.CheckAccountExistenceResponse>) responseObserver,
                            serviceImpl::checkAccountExistence, serviceImpl::onErrorMap);
                    break;
                case METHODID_LOOKUP_USERNAME_HASH:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.LookupUsernameHashRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.LookupUsernameHashResponse>) responseObserver,
                            serviceImpl::lookupUsernameHash, serviceImpl::onErrorMap);
                    break;
                case METHODID_LOOKUP_USERNAME_LINK:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.account.LookupUsernameLinkRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.LookupUsernameLinkResponse>) responseObserver,
                            serviceImpl::lookupUsernameLink, serviceImpl::onErrorMap);
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
