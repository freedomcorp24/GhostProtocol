package org.ghostprotocol.chat.profile;

import static org.ghostprotocol.chat.profile.ProfileGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/profile.proto")
public final class ReactorProfileGrpc {
    private ReactorProfileGrpc() {}

    public static ReactorProfileStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorProfileStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with profiles and profile-related data.
     * </pre>
     */
    public static final class ReactorProfileStub extends io.grpc.stub.AbstractStub<ReactorProfileStub> {
        private ProfileGrpc.ProfileStub delegateStub;

        private ReactorProfileStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = ProfileGrpc.newStub(channel);
        }

        private ReactorProfileStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = ProfileGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorProfileStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorProfileStub(channel, callOptions);
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.SetProfileResponse> setProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.SetProfileRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setProfile, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getVersionedProfile, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getUnversionedProfile, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a profile key credential.
         *  Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers. It may also fail with an
         *  `INVALID_ARGUMENT` status if the given credential type is invalid.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest> reactorRequest) {
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.SetProfileResponse> setProfile(org.ghostprotocol.chat.profile.SetProfileRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setProfile, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getVersionedProfile, getCallOptions());
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getUnversionedProfile, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a profile key credential.
         *  Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers. It may also fail with an
         *  `INVALID_ARGUMENT` status if the given credential type is invalid.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getExpiringProfileKeyCredential, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with profiles and profile-related data.
     * </pre>
     */
    public static abstract class ProfileImplBase implements io.grpc.BindableService {

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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.SetProfileResponse> setProfile(org.ghostprotocol.chat.profile.SetProfileRequest request) {
            return setProfile(reactor.core.publisher.Mono.just(request));
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.SetProfileResponse> setProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.SetProfileRequest> request) {
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileRequest request) {
            return getVersionedProfile(reactor.core.publisher.Mono.just(request));
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetVersionedProfileRequest> request) {
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileRequest request) {
            return getUnversionedProfile(reactor.core.publisher.Mono.just(request));
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
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetUnversionedProfileRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a profile key credential.
         *  Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers. It may also fail with an
         *  `INVALID_ARGUMENT` status if the given credential type is invalid.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest request) {
            return getExpiringProfileKeyCredential(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a profile key credential.
         *  Callers with an unidentified access key for the account
         *  should use the version of this method in `ProfileAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers. It may also fail with an
         *  `INVALID_ARGUMENT` status if the given credential type is invalid.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(reactor.core.publisher.Mono<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.profile.ProfileGrpc.getSetProfileMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.profile.SetProfileRequest,
                                            org.ghostprotocol.chat.profile.SetProfileResponse>(
                                            this, METHODID_SET_PROFILE)))
                    .addMethod(
                            org.ghostprotocol.chat.profile.ProfileGrpc.getGetVersionedProfileMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.profile.GetVersionedProfileRequest,
                                            org.ghostprotocol.chat.profile.GetVersionedProfileResponse>(
                                            this, METHODID_GET_VERSIONED_PROFILE)))
                    .addMethod(
                            org.ghostprotocol.chat.profile.ProfileGrpc.getGetUnversionedProfileMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.profile.GetUnversionedProfileRequest,
                                            org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>(
                                            this, METHODID_GET_UNVERSIONED_PROFILE)))
                    .addMethod(
                            org.ghostprotocol.chat.profile.ProfileGrpc.getGetExpiringProfileKeyCredentialMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest,
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

    public static final int METHODID_SET_PROFILE = 0;
    public static final int METHODID_GET_VERSIONED_PROFILE = 1;
    public static final int METHODID_GET_UNVERSIONED_PROFILE = 2;
    public static final int METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL = 3;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final ProfileImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(ProfileImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_SET_PROFILE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.profile.SetProfileRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.SetProfileResponse>) responseObserver,
                            serviceImpl::setProfile, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_VERSIONED_PROFILE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.profile.GetVersionedProfileRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetVersionedProfileResponse>) responseObserver,
                            serviceImpl::getVersionedProfile, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_UNVERSIONED_PROFILE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.profile.GetUnversionedProfileRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>) responseObserver,
                            serviceImpl::getUnversionedProfile, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest) request,
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
