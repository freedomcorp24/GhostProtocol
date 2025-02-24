package org.ghostprotocol.chat.payments;

import static org.ghostprotocol.chat.payments.PaymentsGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/payments.proto")
public final class ReactorPaymentsGrpc {
    private ReactorPaymentsGrpc() {}

    public static ReactorPaymentsStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorPaymentsStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with payments.
     * </pre>
     */
    public static final class ReactorPaymentsStub extends io.grpc.stub.AbstractStub<ReactorPaymentsStub> {
        private PaymentsGrpc.PaymentsStub delegateStub;

        private ReactorPaymentsStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = PaymentsGrpc.newStub(channel);
        }

        private ReactorPaymentsStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = PaymentsGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorPaymentsStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorPaymentsStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> getCurrencyConversions(reactor.core.publisher.Mono<org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getCurrencyConversions, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> getCurrencyConversions(org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getCurrencyConversions, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with payments.
     * </pre>
     */
    public static abstract class PaymentsImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> getCurrencyConversions(org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest request) {
            return getCurrencyConversions(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> getCurrencyConversions(reactor.core.publisher.Mono<org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.payments.PaymentsGrpc.getGetCurrencyConversionsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest,
                                            org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse>(
                                            this, METHODID_GET_CURRENCY_CONVERSIONS)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_CURRENCY_CONVERSIONS = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final PaymentsImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(PaymentsImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_CURRENCY_CONVERSIONS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse>) responseObserver,
                            serviceImpl::getCurrencyConversions, serviceImpl::onErrorMap);
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
