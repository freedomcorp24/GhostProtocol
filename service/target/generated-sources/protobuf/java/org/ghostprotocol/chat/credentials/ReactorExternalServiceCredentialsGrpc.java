package org.ghostprotocol.chat.credentials;

import static org.ghostprotocol.chat.credentials.ExternalServiceCredentialsGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/credentials.proto")
public final class ReactorExternalServiceCredentialsGrpc {
    private ReactorExternalServiceCredentialsGrpc() {}

    public static ReactorExternalServiceCredentialsStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorExternalServiceCredentialsStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for obtaining and verifying credentials for &quot;external&quot; services
     *  (i.e. services that are not a part of the chat server deployment).
     *  All methods of this service require authentication.
     * </pre>
     */
    public static final class ReactorExternalServiceCredentialsStub extends io.grpc.stub.AbstractStub<ReactorExternalServiceCredentialsStub> {
        private ExternalServiceCredentialsGrpc.ExternalServiceCredentialsStub delegateStub;

        private ReactorExternalServiceCredentialsStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = ExternalServiceCredentialsGrpc.newStub(channel);
        }

        private ReactorExternalServiceCredentialsStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = ExternalServiceCredentialsGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorExternalServiceCredentialsStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorExternalServiceCredentialsStub(channel, callOptions);
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> getExternalServiceCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getExternalServiceCredentials, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> getExternalServiceCredentials(org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getExternalServiceCredentials, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for obtaining and verifying credentials for &quot;external&quot; services
     *  (i.e. services that are not a part of the chat server deployment).
     *  All methods of this service require authentication.
     * </pre>
     */
    public static abstract class ExternalServiceCredentialsImplBase implements io.grpc.BindableService {

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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> getExternalServiceCredentials(org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest request) {
            return getExternalServiceCredentials(reactor.core.publisher.Mono.just(request));
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> getExternalServiceCredentials(reactor.core.publisher.Mono<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.credentials.ExternalServiceCredentialsGrpc.getGetExternalServiceCredentialsMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest,
                                            org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse>(
                                            this, METHODID_GET_EXTERNAL_SERVICE_CREDENTIALS)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_EXTERNAL_SERVICE_CREDENTIALS = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final ExternalServiceCredentialsImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(ExternalServiceCredentialsImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_EXTERNAL_SERVICE_CREDENTIALS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse>) responseObserver,
                            serviceImpl::getExternalServiceCredentials, serviceImpl::onErrorMap);
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
