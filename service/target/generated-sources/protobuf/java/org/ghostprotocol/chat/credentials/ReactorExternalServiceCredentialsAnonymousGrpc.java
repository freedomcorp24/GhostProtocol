package org.ghostprotocol.chat.credentials;

import static org.ghostprotocol.chat.credentials.ExternalServiceCredentialsAnonymousGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/credentials.proto")
public final class ReactorExternalServiceCredentialsAnonymousGrpc {
    private ReactorExternalServiceCredentialsAnonymousGrpc() {}

    public static ReactorExternalServiceCredentialsAnonymousStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorExternalServiceCredentialsAnonymousStub(channel);
    }

    public static final class ReactorExternalServiceCredentialsAnonymousStub extends io.grpc.stub.AbstractStub<ReactorExternalServiceCredentialsAnonymousStub> {
        private ExternalServiceCredentialsAnonymousGrpc.ExternalServiceCredentialsAnonymousStub delegateStub;

        private ReactorExternalServiceCredentialsAnonymousStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = ExternalServiceCredentialsAnonymousGrpc.newStub(channel);
        }

        private ReactorExternalServiceCredentialsAnonymousStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = ExternalServiceCredentialsAnonymousGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorExternalServiceCredentialsAnonymousStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorExternalServiceCredentialsAnonymousStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Generates and returns an external service credentials for the caller.
         * 
         *  `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
         * 
         *  `INVALID_ARGUMENT` status is returned if service is not configured for the service type
         *  found in the request OR if `externalService` is not specified in the request.
         * 
         *  `RESOURCE_EXHAUSTED` status is returned if a rate limit for
         *  generating credentials has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> checkSvrCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::checkSvrCredentials, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Generates and returns an external service credentials for the caller.
         * 
         *  `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
         * 
         *  `INVALID_ARGUMENT` status is returned if service is not configured for the service type
         *  found in the request OR if `externalService` is not specified in the request.
         * 
         *  `RESOURCE_EXHAUSTED` status is returned if a rate limit for
         *  generating credentials has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> checkSvrCredentials(org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::checkSvrCredentials, getCallOptions());
        }

    }

    public static abstract class ExternalServiceCredentialsAnonymousImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Generates and returns an external service credentials for the caller.
         * 
         *  `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
         * 
         *  `INVALID_ARGUMENT` status is returned if service is not configured for the service type
         *  found in the request OR if `externalService` is not specified in the request.
         * 
         *  `RESOURCE_EXHAUSTED` status is returned if a rate limit for
         *  generating credentials has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> checkSvrCredentials(org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest request) {
            return checkSvrCredentials(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Generates and returns an external service credentials for the caller.
         * 
         *  `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
         * 
         *  `INVALID_ARGUMENT` status is returned if service is not configured for the service type
         *  found in the request OR if `externalService` is not specified in the request.
         * 
         *  `RESOURCE_EXHAUSTED` status is returned if a rate limit for
         *  generating credentials has been exceeded, in which case a
         *  `retry-after` header containing an ISO 8601 duration string will be present
         *  in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> checkSvrCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.credentials.ExternalServiceCredentialsAnonymousGrpc.getCheckSvrCredentialsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest,
                                            org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse>(
                                            this, METHODID_CHECK_SVR_CREDENTIALS)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_CHECK_SVR_CREDENTIALS = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final ExternalServiceCredentialsAnonymousImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(ExternalServiceCredentialsAnonymousImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_CHECK_SVR_CREDENTIALS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse>) responseObserver,
                            serviceImpl::checkSvrCredentials, serviceImpl::onErrorMap);
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
