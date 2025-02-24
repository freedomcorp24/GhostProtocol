package org.ghostprotocol.chat.keys;

import static org.ghostprotocol.chat.keys.KeysAnonymousGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/keys.proto")
public final class ReactorKeysAnonymousGrpc {
    private ReactorKeysAnonymousGrpc() {}

    public static ReactorKeysAnonymousStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorKeysAnonymousStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with pre-keys using &quot;unidentified access&quot;
     *  credentials.
     * </pre>
     */
    public static final class ReactorKeysAnonymousStub extends io.grpc.stub.AbstractStub<ReactorKeysAnonymousStub> {
        private KeysAnonymousGrpc.KeysAnonymousStub delegateStub;

        private ReactorKeysAnonymousStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = KeysAnonymousGrpc.newStub(channel);
        }

        private ReactorKeysAnonymousStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = KeysAnonymousGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorKeysAnonymousStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorKeysAnonymousStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves an approximate count of the number of the various kinds of
         *  pre-keys stored for the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getPreKeys, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a set of pre-keys for establishing a session with the targeted
         *  device or devices. Note that callers with an unidentified access key for
         *  the targeted account should use the version of this method in
         *  `KeysAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found, if no active device with the given ID (if specified) was found on
         *  the target account, or if the account has no active devices. It may also
         *  fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.keys.CheckIdentityKeyResponse> checkIdentityKeys(reactor.core.publisher.Flux<org.ghostprotocol.chat.keys.CheckIdentityKeyRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.manyToMany(reactorRequest, delegateStub::checkIdentityKeys, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves an approximate count of the number of the various kinds of
         *  pre-keys stored for the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getPreKeys, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with pre-keys using &quot;unidentified access&quot;
     *  credentials.
     * </pre>
     */
    public static abstract class KeysAnonymousImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Retrieves an approximate count of the number of the various kinds of
         *  pre-keys stored for the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest request) {
            return getPreKeys(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves an approximate count of the number of the various kinds of
         *  pre-keys stored for the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }


        /**
         * <pre>
         * &#42;
         *  Retrieves a set of pre-keys for establishing a session with the targeted
         *  device or devices. Note that callers with an unidentified access key for
         *  the targeted account should use the version of this method in
         *  `KeysAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found, if no active device with the given ID (if specified) was found on
         *  the target account, or if the account has no active devices. It may also
         *  fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Flux<org.ghostprotocol.chat.keys.CheckIdentityKeyResponse> checkIdentityKeys(reactor.core.publisher.Flux<org.ghostprotocol.chat.keys.CheckIdentityKeyRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.keys.KeysAnonymousGrpc.getGetPreKeysMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest,
                                            org.ghostprotocol.chat.keys.GetPreKeysResponse>(
                                            this, METHODID_GET_PRE_KEYS)))
                    .addMethod(
                            org.ghostprotocol.chat.keys.KeysAnonymousGrpc.getCheckIdentityKeysMethod(),
                            asyncBidiStreamingCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.keys.CheckIdentityKeyRequest,
                                            org.ghostprotocol.chat.keys.CheckIdentityKeyResponse>(
                                            this, METHODID_CHECK_IDENTITY_KEYS)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_PRE_KEYS = 0;
    public static final int METHODID_CHECK_IDENTITY_KEYS = 1;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final KeysAnonymousImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(KeysAnonymousImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_PRE_KEYS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeysResponse>) responseObserver,
                            serviceImpl::getPreKeys, serviceImpl::onErrorMap);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_IDENTITY_KEYS:
                    return (io.grpc.stub.StreamObserver<Req>) com.salesforce.reactorgrpc.stub.ServerCalls.manyToMany(
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.CheckIdentityKeyResponse>) responseObserver,
                            serviceImpl::checkIdentityKeys, serviceImpl::onErrorMap, serviceImpl.getCallOptions(methodId));
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
