package org.ghostprotocol.chat.profile;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for working with profiles and profile-related data.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/profile.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProfileGrpc {

  private ProfileGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.profile.Profile";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.SetProfileRequest,
      org.ghostprotocol.chat.profile.SetProfileResponse> getSetProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetProfile",
      requestType = org.ghostprotocol.chat.profile.SetProfileRequest.class,
      responseType = org.ghostprotocol.chat.profile.SetProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.SetProfileRequest,
      org.ghostprotocol.chat.profile.SetProfileResponse> getSetProfileMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.SetProfileRequest, org.ghostprotocol.chat.profile.SetProfileResponse> getSetProfileMethod;
    if ((getSetProfileMethod = ProfileGrpc.getSetProfileMethod) == null) {
      synchronized (ProfileGrpc.class) {
        if ((getSetProfileMethod = ProfileGrpc.getSetProfileMethod) == null) {
          ProfileGrpc.getSetProfileMethod = getSetProfileMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.profile.SetProfileRequest, org.ghostprotocol.chat.profile.SetProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.SetProfileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.SetProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileMethodDescriptorSupplier("SetProfile"))
              .build();
        }
      }
    }
    return getSetProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetVersionedProfileRequest,
      org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getGetVersionedProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetVersionedProfile",
      requestType = org.ghostprotocol.chat.profile.GetVersionedProfileRequest.class,
      responseType = org.ghostprotocol.chat.profile.GetVersionedProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetVersionedProfileRequest,
      org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getGetVersionedProfileMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetVersionedProfileRequest, org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getGetVersionedProfileMethod;
    if ((getGetVersionedProfileMethod = ProfileGrpc.getGetVersionedProfileMethod) == null) {
      synchronized (ProfileGrpc.class) {
        if ((getGetVersionedProfileMethod = ProfileGrpc.getGetVersionedProfileMethod) == null) {
          ProfileGrpc.getGetVersionedProfileMethod = getGetVersionedProfileMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.profile.GetVersionedProfileRequest, org.ghostprotocol.chat.profile.GetVersionedProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetVersionedProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetVersionedProfileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetVersionedProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileMethodDescriptorSupplier("GetVersionedProfile"))
              .build();
        }
      }
    }
    return getGetVersionedProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetUnversionedProfileRequest,
      org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getGetUnversionedProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUnversionedProfile",
      requestType = org.ghostprotocol.chat.profile.GetUnversionedProfileRequest.class,
      responseType = org.ghostprotocol.chat.profile.GetUnversionedProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetUnversionedProfileRequest,
      org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getGetUnversionedProfileMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetUnversionedProfileRequest, org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getGetUnversionedProfileMethod;
    if ((getGetUnversionedProfileMethod = ProfileGrpc.getGetUnversionedProfileMethod) == null) {
      synchronized (ProfileGrpc.class) {
        if ((getGetUnversionedProfileMethod = ProfileGrpc.getGetUnversionedProfileMethod) == null) {
          ProfileGrpc.getGetUnversionedProfileMethod = getGetUnversionedProfileMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.profile.GetUnversionedProfileRequest, org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUnversionedProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetUnversionedProfileRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetUnversionedProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileMethodDescriptorSupplier("GetUnversionedProfile"))
              .build();
        }
      }
    }
    return getGetUnversionedProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest,
      org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getGetExpiringProfileKeyCredentialMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetExpiringProfileKeyCredential",
      requestType = org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest.class,
      responseType = org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest,
      org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getGetExpiringProfileKeyCredentialMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest, org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getGetExpiringProfileKeyCredentialMethod;
    if ((getGetExpiringProfileKeyCredentialMethod = ProfileGrpc.getGetExpiringProfileKeyCredentialMethod) == null) {
      synchronized (ProfileGrpc.class) {
        if ((getGetExpiringProfileKeyCredentialMethod = ProfileGrpc.getGetExpiringProfileKeyCredentialMethod) == null) {
          ProfileGrpc.getGetExpiringProfileKeyCredentialMethod = getGetExpiringProfileKeyCredentialMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest, org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetExpiringProfileKeyCredential"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileMethodDescriptorSupplier("GetExpiringProfileKeyCredential"))
              .build();
        }
      }
    }
    return getGetExpiringProfileKeyCredentialMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProfileStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileStub>() {
        @java.lang.Override
        public ProfileStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileStub(channel, callOptions);
        }
      };
    return ProfileStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProfileBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileBlockingStub>() {
        @java.lang.Override
        public ProfileBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileBlockingStub(channel, callOptions);
        }
      };
    return ProfileBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProfileFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileFutureStub>() {
        @java.lang.Override
        public ProfileFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileFutureStub(channel, callOptions);
        }
      };
    return ProfileFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Sets profile data and if needed, returns S3 credentials used by clients to upload an avatar.
     * This RPC may fail with `PERMISSION_DENIED` if it attempts to set the MobileCoin wallet ID
     * on an account whose profile does not currently have a MobileCoin wallet ID and
     * whose phone number contains a disallowed country prefix.
     * </pre>
     */
    default void setProfile(org.ghostprotocol.chat.profile.SetProfileRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.SetProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves versioned profile data. Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    default void getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetVersionedProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves unversioned profile data. Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    default void getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUnversionedProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves a profile key credential.
     * Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers. It may also fail with an
     * `INVALID_ARGUMENT` status if the given credential type is invalid.
     * </pre>
     */
    default void getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetExpiringProfileKeyCredentialMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Profile.
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data.
   * </pre>
   */
  public static abstract class ProfileImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProfileGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Profile.
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data.
   * </pre>
   */
  public static final class ProfileStub
      extends io.grpc.stub.AbstractAsyncStub<ProfileStub> {
    private ProfileStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Sets profile data and if needed, returns S3 credentials used by clients to upload an avatar.
     * This RPC may fail with `PERMISSION_DENIED` if it attempts to set the MobileCoin wallet ID
     * on an account whose profile does not currently have a MobileCoin wallet ID and
     * whose phone number contains a disallowed country prefix.
     * </pre>
     */
    public void setProfile(org.ghostprotocol.chat.profile.SetProfileRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.SetProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves versioned profile data. Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public void getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetVersionedProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves unversioned profile data. Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public void getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUnversionedProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves a profile key credential.
     * Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers. It may also fail with an
     * `INVALID_ARGUMENT` status if the given credential type is invalid.
     * </pre>
     */
    public void getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetExpiringProfileKeyCredentialMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Profile.
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data.
   * </pre>
   */
  public static final class ProfileBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProfileBlockingStub> {
    private ProfileBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Sets profile data and if needed, returns S3 credentials used by clients to upload an avatar.
     * This RPC may fail with `PERMISSION_DENIED` if it attempts to set the MobileCoin wallet ID
     * on an account whose profile does not currently have a MobileCoin wallet ID and
     * whose phone number contains a disallowed country prefix.
     * </pre>
     */
    public org.ghostprotocol.chat.profile.SetProfileResponse setProfile(org.ghostprotocol.chat.profile.SetProfileRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieves versioned profile data. Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.profile.GetVersionedProfileResponse getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetVersionedProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieves unversioned profile data. Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.profile.GetUnversionedProfileResponse getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUnversionedProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieves a profile key credential.
     * Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers. It may also fail with an
     * `INVALID_ARGUMENT` status if the given credential type is invalid.
     * </pre>
     */
    public org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetExpiringProfileKeyCredentialMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Profile.
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data.
   * </pre>
   */
  public static final class ProfileFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProfileFutureStub> {
    private ProfileFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Sets profile data and if needed, returns S3 credentials used by clients to upload an avatar.
     * This RPC may fail with `PERMISSION_DENIED` if it attempts to set the MobileCoin wallet ID
     * on an account whose profile does not currently have a MobileCoin wallet ID and
     * whose phone number contains a disallowed country prefix.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.profile.SetProfileResponse> setProfile(
        org.ghostprotocol.chat.profile.SetProfileRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieves versioned profile data. Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(
        org.ghostprotocol.chat.profile.GetVersionedProfileRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetVersionedProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieves unversioned profile data. Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(
        org.ghostprotocol.chat.profile.GetUnversionedProfileRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUnversionedProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieves a profile key credential.
     * Callers with an unidentified access key for the account
     * should use the version of this method in `ProfileAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching profiles has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers. It may also fail with an
     * `INVALID_ARGUMENT` status if the given credential type is invalid.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(
        org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetExpiringProfileKeyCredentialMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_PROFILE = 0;
  private static final int METHODID_GET_VERSIONED_PROFILE = 1;
  private static final int METHODID_GET_UNVERSIONED_PROFILE = 2;
  private static final int METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_PROFILE:
          serviceImpl.setProfile((org.ghostprotocol.chat.profile.SetProfileRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.SetProfileResponse>) responseObserver);
          break;
        case METHODID_GET_VERSIONED_PROFILE:
          serviceImpl.getVersionedProfile((org.ghostprotocol.chat.profile.GetVersionedProfileRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetVersionedProfileResponse>) responseObserver);
          break;
        case METHODID_GET_UNVERSIONED_PROFILE:
          serviceImpl.getUnversionedProfile((org.ghostprotocol.chat.profile.GetUnversionedProfileRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>) responseObserver);
          break;
        case METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL:
          serviceImpl.getExpiringProfileKeyCredential((org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSetProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.profile.SetProfileRequest,
              org.ghostprotocol.chat.profile.SetProfileResponse>(
                service, METHODID_SET_PROFILE)))
        .addMethod(
          getGetVersionedProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.profile.GetVersionedProfileRequest,
              org.ghostprotocol.chat.profile.GetVersionedProfileResponse>(
                service, METHODID_GET_VERSIONED_PROFILE)))
        .addMethod(
          getGetUnversionedProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.profile.GetUnversionedProfileRequest,
              org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>(
                service, METHODID_GET_UNVERSIONED_PROFILE)))
        .addMethod(
          getGetExpiringProfileKeyCredentialMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialRequest,
              org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse>(
                service, METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL)))
        .build();
  }

  private static abstract class ProfileBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProfileBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.profile.ProfileOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Profile");
    }
  }

  private static final class ProfileFileDescriptorSupplier
      extends ProfileBaseDescriptorSupplier {
    ProfileFileDescriptorSupplier() {}
  }

  private static final class ProfileMethodDescriptorSupplier
      extends ProfileBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProfileMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProfileGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProfileFileDescriptorSupplier())
              .addMethod(getSetProfileMethod())
              .addMethod(getGetVersionedProfileMethod())
              .addMethod(getGetUnversionedProfileMethod())
              .addMethod(getGetExpiringProfileKeyCredentialMethod())
              .build();
        }
      }
    }
    return result;
  }
}
