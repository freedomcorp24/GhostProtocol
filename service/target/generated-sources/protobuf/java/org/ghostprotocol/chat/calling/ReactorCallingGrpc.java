package org.ghostprotocol.chat.calling;

import static org.ghostprotocol.chat.calling.CallingGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/calling.proto")
public final class ReactorCallingGrpc {
    private ReactorCallingGrpc() {}

    public static ReactorCallingStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorCallingStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for getting credentials for one-on-one and group calls.
     * </pre>
     */
    public static final class ReactorCallingStub extends io.grpc.stub.AbstractStub<ReactorCallingStub> {
        private CallingGrpc.CallingStub delegateStub;

        private ReactorCallingStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = CallingGrpc.newStub(channel);
        }

        private ReactorCallingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = CallingGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorCallingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorCallingStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Generates and returns TURN credentials for the caller.
         * 
         *  This RPC may fail with a `RESOURCE_EXHAUSTED` status if a rate limit for
         *  generating TURN credentials has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> getTurnCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.calling.GetTurnCredentialsRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getTurnCredentials, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Generates and returns TURN credentials for the caller.
         * 
         *  This RPC may fail with a `RESOURCE_EXHAUSTED` status if a rate limit for
         *  generating TURN credentials has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> getTurnCredentials(org.ghostprotocol.chat.calling.GetTurnCredentialsRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getTurnCredentials, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for getting credentials for one-on-one and group calls.
     * </pre>
     */
    public static abstract class CallingImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Generates and returns TURN credentials for the caller.
         * 
         *  This RPC may fail with a `RESOURCE_EXHAUSTED` status if a rate limit for
         *  generating TURN credentials has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> getTurnCredentials(org.ghostprotocol.chat.calling.GetTurnCredentialsRequest request) {
            return getTurnCredentials(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Generates and returns TURN credentials for the caller.
         * 
         *  This RPC may fail with a `RESOURCE_EXHAUSTED` status if a rate limit for
         *  generating TURN credentials has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> getTurnCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.calling.GetTurnCredentialsRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.calling.CallingGrpc.getGetTurnCredentialsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.calling.GetTurnCredentialsRequest,
                                            org.ghostprotocol.chat.calling.GetTurnCredentialsResponse>(
                                            this, METHODID_GET_TURN_CREDENTIALS)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_TURN_CREDENTIALS = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final CallingImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(CallingImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_TURN_CREDENTIALS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.calling.GetTurnCredentialsRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse>) responseObserver,
                            serviceImpl::getTurnCredentials, serviceImpl::onErrorMap);
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
