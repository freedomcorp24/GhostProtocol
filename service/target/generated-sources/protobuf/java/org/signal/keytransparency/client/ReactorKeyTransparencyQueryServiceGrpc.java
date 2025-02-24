package org.signal.keytransparency.client;

import static org.signal.keytransparency.client.KeyTransparencyQueryServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/keytransparency/client/KeyTransparencyService.proto")
public final class ReactorKeyTransparencyQueryServiceGrpc {
    private ReactorKeyTransparencyQueryServiceGrpc() {}

    public static ReactorKeyTransparencyQueryServiceStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorKeyTransparencyQueryServiceStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  An external-facing, read-only key transparency service used by Signal&#39;s chat server
     *  to look up and monitor identifiers.
     *  There are three types of identifier mappings stored by the key transparency log:
     *  - An ACI which maps to an ACI identity key
     *  - An E164-formatted phone number which maps to an ACI
     *  - A username hash which also maps to an ACI
     *  Separately, the log also stores and periodically updates a fixed value known as the `distinguished` key.
     *  Clients use the verified tree head from looking up this key for future calls to the Search and Monitor endpoints.
     * </pre>
     */
    public static final class ReactorKeyTransparencyQueryServiceStub extends io.grpc.stub.AbstractStub<ReactorKeyTransparencyQueryServiceStub> {
        private KeyTransparencyQueryServiceGrpc.KeyTransparencyQueryServiceStub delegateStub;

        private ReactorKeyTransparencyQueryServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = KeyTransparencyQueryServiceGrpc.newStub(channel);
        }

        private ReactorKeyTransparencyQueryServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = KeyTransparencyQueryServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorKeyTransparencyQueryServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorKeyTransparencyQueryServiceStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint used by clients to retrieve the most recent distinguished tree
         *  head, which should be used to derive consistency parameters for
         *  subsequent Search and Monitor requests. It should be the first key
         *  transparency RPC a client calls.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.DistinguishedResponse> distinguished(reactor.core.publisher.Mono<org.signal.keytransparency.client.DistinguishedRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::distinguished, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint used by clients to search for one or more identifiers in the transparency log.
         *  The server returns proof that the identifier(s) exist in the log.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.SearchResponse> search(reactor.core.publisher.Mono<org.signal.keytransparency.client.SearchRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::search, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint that allows users to monitor a group of identifiers by returning proof that the log continues to be
         *  constructed correctly in later entries for those identifiers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.MonitorResponse> monitor(reactor.core.publisher.Mono<org.signal.keytransparency.client.MonitorRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::monitor, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint used by clients to retrieve the most recent distinguished tree
         *  head, which should be used to derive consistency parameters for
         *  subsequent Search and Monitor requests. It should be the first key
         *  transparency RPC a client calls.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.DistinguishedResponse> distinguished(org.signal.keytransparency.client.DistinguishedRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::distinguished, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint used by clients to search for one or more identifiers in the transparency log.
         *  The server returns proof that the identifier(s) exist in the log.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.SearchResponse> search(org.signal.keytransparency.client.SearchRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::search, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint that allows users to monitor a group of identifiers by returning proof that the log continues to be
         *  constructed correctly in later entries for those identifiers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.MonitorResponse> monitor(org.signal.keytransparency.client.MonitorRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::monitor, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  An external-facing, read-only key transparency service used by Signal&#39;s chat server
     *  to look up and monitor identifiers.
     *  There are three types of identifier mappings stored by the key transparency log:
     *  - An ACI which maps to an ACI identity key
     *  - An E164-formatted phone number which maps to an ACI
     *  - A username hash which also maps to an ACI
     *  Separately, the log also stores and periodically updates a fixed value known as the `distinguished` key.
     *  Clients use the verified tree head from looking up this key for future calls to the Search and Monitor endpoints.
     * </pre>
     */
    public static abstract class KeyTransparencyQueryServiceImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  An endpoint used by clients to retrieve the most recent distinguished tree
         *  head, which should be used to derive consistency parameters for
         *  subsequent Search and Monitor requests. It should be the first key
         *  transparency RPC a client calls.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.DistinguishedResponse> distinguished(org.signal.keytransparency.client.DistinguishedRequest request) {
            return distinguished(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint used by clients to retrieve the most recent distinguished tree
         *  head, which should be used to derive consistency parameters for
         *  subsequent Search and Monitor requests. It should be the first key
         *  transparency RPC a client calls.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.DistinguishedResponse> distinguished(reactor.core.publisher.Mono<org.signal.keytransparency.client.DistinguishedRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint used by clients to search for one or more identifiers in the transparency log.
         *  The server returns proof that the identifier(s) exist in the log.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.SearchResponse> search(org.signal.keytransparency.client.SearchRequest request) {
            return search(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint used by clients to search for one or more identifiers in the transparency log.
         *  The server returns proof that the identifier(s) exist in the log.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.SearchResponse> search(reactor.core.publisher.Mono<org.signal.keytransparency.client.SearchRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint that allows users to monitor a group of identifiers by returning proof that the log continues to be
         *  constructed correctly in later entries for those identifiers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.MonitorResponse> monitor(org.signal.keytransparency.client.MonitorRequest request) {
            return monitor(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  An endpoint that allows users to monitor a group of identifiers by returning proof that the log continues to be
         *  constructed correctly in later entries for those identifiers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.signal.keytransparency.client.MonitorResponse> monitor(reactor.core.publisher.Mono<org.signal.keytransparency.client.MonitorRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.signal.keytransparency.client.KeyTransparencyQueryServiceGrpc.getDistinguishedMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.signal.keytransparency.client.DistinguishedRequest,
                                            org.signal.keytransparency.client.DistinguishedResponse>(
                                            this, METHODID_DISTINGUISHED)))
                    .addMethod(
                            org.signal.keytransparency.client.KeyTransparencyQueryServiceGrpc.getSearchMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.signal.keytransparency.client.SearchRequest,
                                            org.signal.keytransparency.client.SearchResponse>(
                                            this, METHODID_SEARCH)))
                    .addMethod(
                            org.signal.keytransparency.client.KeyTransparencyQueryServiceGrpc.getMonitorMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.signal.keytransparency.client.MonitorRequest,
                                            org.signal.keytransparency.client.MonitorResponse>(
                                            this, METHODID_MONITOR)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_DISTINGUISHED = 0;
    public static final int METHODID_SEARCH = 1;
    public static final int METHODID_MONITOR = 2;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final KeyTransparencyQueryServiceImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(KeyTransparencyQueryServiceImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_DISTINGUISHED:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.signal.keytransparency.client.DistinguishedRequest) request,
                            (io.grpc.stub.StreamObserver<org.signal.keytransparency.client.DistinguishedResponse>) responseObserver,
                            serviceImpl::distinguished, serviceImpl::onErrorMap);
                    break;
                case METHODID_SEARCH:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.signal.keytransparency.client.SearchRequest) request,
                            (io.grpc.stub.StreamObserver<org.signal.keytransparency.client.SearchResponse>) responseObserver,
                            serviceImpl::search, serviceImpl::onErrorMap);
                    break;
                case METHODID_MONITOR:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.signal.keytransparency.client.MonitorRequest) request,
                            (io.grpc.stub.StreamObserver<org.signal.keytransparency.client.MonitorResponse>) responseObserver,
                            serviceImpl::monitor, serviceImpl::onErrorMap);
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
