package org.ghostprotocol.chat.profile;

import static org.ghostprotocol.chat.profile.ProfileAnonymousGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/profile.proto")
public final class ReactorProfileAnonymousGrpc {
    private ReactorProfileAnonymousGrpc() {}

    public static ReactorProfileAnonymousStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorProfileAnonymousStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with profiles and profile-related data using &quot;unidentified access&quot;
     *  credentials. Callers must not submit any self-identifying credentials
     *  when calling methods in this service and must instead present the targeted account&#39;s
     *  unidentified access key as an anonymous authentication mechanism. Callers
     *  without an unidentified access key should use the equivalent, authenticated
     *  methods in `Profile` instead.
     * </pre>
     */
    public static final class ReactorProfileAnonymousStub extends io.grpc.stub.AbstractStub<ReactorProfileAnonymousStub> {
        private ProfileAnonymousGrpc.ProfileAnonymousStub delegateStub;

        private ReactorProfileAnonymousStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = ProfileAnonymousGrpc.newStub(channel);
        }

        private ReactorProfileAnonymousStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = ProfileAnonymousGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorProfileAnonymousStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorProfileAnonymousStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Sets profile data and if needed, returns S3 credentials used by clients to upload an avatar.
         * 
         *  This RPC may fail with `PERMISSION_DENIED` if it attempts to set the MobileCoin wallet ID
         *  on an account whose profile does not currently have a MobileCoin wallet ID and
         *  whose phone number contains a disallowed country prefix.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getVersionedProfile, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves versioned profile data. Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getUnversionedProfile, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves unversioned profile data. Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getExpiringProfileKeyCredential, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets profile data and if needed, returns S3 credentials used by clients to upload an avatar.
         * 
         *  This RPC may fail with `PERMISSION_DENIED` if it attempts to set the MobileCoin wallet ID
         *  on an account whose profile does not currently have a MobileCoin wallet ID and
         *  whose phone number contains a disallowed country prefix.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getVersionedProfile, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves versioned profile data. Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getUnversionedProfile, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves unversioned profile data. Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getExpiringProfileKeyCredential, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with profiles and profile-related data using &quot;unidentified access&quot;
     *  credentials. Callers must not submit any self-identifying credentials
     *  when calling methods in this service and must instead present the targeted account&#39;s
     *  unidentified access key as an anonymous authentication mechanism. Callers
     *  without an unidentified access key should use the equivalent, authenticated
     *  methods in `Profile` instead.
     * </pre>
     */
    public static abstract class ProfileAnonymousImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Sets profile data and if needed, returns S3 credentials used by clients to upload an avatar.
         * 
         *  This RPC may fail with `PERMISSION_DENIED` if it attempts to set the MobileCoin wallet ID
         *  on an account whose profile does not currently have a MobileCoin wallet ID and
         *  whose phone number contains a disallowed country prefix.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest request) {
            return getVersionedProfile(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets profile data and if needed, returns S3 credentials used by clients to upload an avatar.
         * 
         *  This RPC may fail with `PERMISSION_DENIED` if it attempts to set the MobileCoin wallet ID
         *  on an account whose profile does not currently have a MobileCoin wallet ID and
         *  whose phone number contains a disallowed country prefix.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves versioned profile data. Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest request) {
            return getUnversionedProfile(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves versioned profile data. Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves unversioned profile data. Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest request) {
            return getExpiringProfileKeyCredential(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves unversioned profile data. Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.profile.ProfileAnonymousGrpc.getGetVersionedProfileMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest,
                                            org.ghostprotocol.chat.profile.GetVersionedProfileResponse>(
                                            this, METHODID_GET_VERSIONED_PROFILE)))
                    .addMethod(
                            org.ghostprotocol.chat.profile.ProfileAnonymousGrpc.getGetUnversionedProfileMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest,
                                            org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>(
                                            this, METHODID_GET_UNVERSIONED_PROFILE)))
                    .addMethod(
                            org.ghostprotocol.chat.profile.ProfileAnonymousGrpc.getGetExpiringProfileKeyCredentialMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest,
                                            org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse>(
                                            this, METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_VERSIONED_PROFILE = 0;
    public static final int METHODID_GET_UNVERSIONED_PROFILE = 1;
    public static final int METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL = 2;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final ProfileAnonymousImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(ProfileAnonymousImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_VERSIONED_PROFILE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetVersionedProfileResponse>) responseObserver,
                            serviceImpl::getVersionedProfile, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_UNVERSIONED_PROFILE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>) responseObserver,
                            serviceImpl::getUnversionedProfile, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse>) responseObserver,
                            serviceImpl::getExpiringProfileKeyCredential, serviceImpl::onErrorMap);
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
