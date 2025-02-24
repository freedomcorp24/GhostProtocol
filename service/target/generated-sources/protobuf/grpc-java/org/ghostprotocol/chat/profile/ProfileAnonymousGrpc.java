package org.ghostprotocol.chat.profile;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for working with profiles and profile-related data using "unidentified access"
 * credentials. Callers must not submit any self-identifying credentials
 * when calling methods in this service and must instead present the targeted account's
 * unidentified access key as an anonymous authentication mechanism. Callers
 * without an unidentified access key should use the equivalent, authenticated
 * methods in `Profile` instead.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/profile.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProfileAnonymousGrpc {

  private ProfileAnonymousGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.profile.ProfileAnonymous";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest,
      org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getGetVersionedProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetVersionedProfile",
      requestType = org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest.class,
      responseType = org.ghostprotocol.chat.profile.GetVersionedProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest,
      org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getGetVersionedProfileMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest, org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getGetVersionedProfileMethod;
    if ((getGetVersionedProfileMethod = ProfileAnonymousGrpc.getGetVersionedProfileMethod) == null) {
      synchronized (ProfileAnonymousGrpc.class) {
        if ((getGetVersionedProfileMethod = ProfileAnonymousGrpc.getGetVersionedProfileMethod) == null) {
          ProfileAnonymousGrpc.getGetVersionedProfileMethod = getGetVersionedProfileMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest, org.ghostprotocol.chat.profile.GetVersionedProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetVersionedProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetVersionedProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileAnonymousMethodDescriptorSupplier("GetVersionedProfile"))
              .build();
        }
      }
    }
    return getGetVersionedProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest,
      org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getGetUnversionedProfileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUnversionedProfile",
      requestType = org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest.class,
      responseType = org.ghostprotocol.chat.profile.GetUnversionedProfileResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest,
      org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getGetUnversionedProfileMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest, org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getGetUnversionedProfileMethod;
    if ((getGetUnversionedProfileMethod = ProfileAnonymousGrpc.getGetUnversionedProfileMethod) == null) {
      synchronized (ProfileAnonymousGrpc.class) {
        if ((getGetUnversionedProfileMethod = ProfileAnonymousGrpc.getGetUnversionedProfileMethod) == null) {
          ProfileAnonymousGrpc.getGetUnversionedProfileMethod = getGetUnversionedProfileMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest, org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUnversionedProfile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetUnversionedProfileResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileAnonymousMethodDescriptorSupplier("GetUnversionedProfile"))
              .build();
        }
      }
    }
    return getGetUnversionedProfileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest,
      org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getGetExpiringProfileKeyCredentialMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetExpiringProfileKeyCredential",
      requestType = org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest.class,
      responseType = org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest,
      org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getGetExpiringProfileKeyCredentialMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest, org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getGetExpiringProfileKeyCredentialMethod;
    if ((getGetExpiringProfileKeyCredentialMethod = ProfileAnonymousGrpc.getGetExpiringProfileKeyCredentialMethod) == null) {
      synchronized (ProfileAnonymousGrpc.class) {
        if ((getGetExpiringProfileKeyCredentialMethod = ProfileAnonymousGrpc.getGetExpiringProfileKeyCredentialMethod) == null) {
          ProfileAnonymousGrpc.getGetExpiringProfileKeyCredentialMethod = getGetExpiringProfileKeyCredentialMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest, org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetExpiringProfileKeyCredential"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfileAnonymousMethodDescriptorSupplier("GetExpiringProfileKeyCredential"))
              .build();
        }
      }
    }
    return getGetExpiringProfileKeyCredentialMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProfileAnonymousStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileAnonymousStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileAnonymousStub>() {
        @java.lang.Override
        public ProfileAnonymousStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileAnonymousStub(channel, callOptions);
        }
      };
    return ProfileAnonymousStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProfileAnonymousBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileAnonymousBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileAnonymousBlockingStub>() {
        @java.lang.Override
        public ProfileAnonymousBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileAnonymousBlockingStub(channel, callOptions);
        }
      };
    return ProfileAnonymousBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProfileAnonymousFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfileAnonymousFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfileAnonymousFutureStub>() {
        @java.lang.Override
        public ProfileAnonymousFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfileAnonymousFutureStub(channel, callOptions);
        }
      };
    return ProfileAnonymousFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data using "unidentified access"
   * credentials. Callers must not submit any self-identifying credentials
   * when calling methods in this service and must instead present the targeted account's
   * unidentified access key as an anonymous authentication mechanism. Callers
   * without an unidentified access key should use the equivalent, authenticated
   * methods in `Profile` instead.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Retrieves versioned profile data.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key.
     * </pre>
     */
    default void getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetVersionedProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves unversioned profile data.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key.
     * </pre>
     */
    default void getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUnversionedProfileMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves a profile key credential.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key, or an `INVALID_ARGUMENT` status if the given credential type is invalid.
     * </pre>
     */
    default void getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetExpiringProfileKeyCredentialMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProfileAnonymous.
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data using "unidentified access"
   * credentials. Callers must not submit any self-identifying credentials
   * when calling methods in this service and must instead present the targeted account's
   * unidentified access key as an anonymous authentication mechanism. Callers
   * without an unidentified access key should use the equivalent, authenticated
   * methods in `Profile` instead.
   * </pre>
   */
  public static abstract class ProfileAnonymousImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProfileAnonymousGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProfileAnonymous.
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data using "unidentified access"
   * credentials. Callers must not submit any self-identifying credentials
   * when calling methods in this service and must instead present the targeted account's
   * unidentified access key as an anonymous authentication mechanism. Callers
   * without an unidentified access key should use the equivalent, authenticated
   * methods in `Profile` instead.
   * </pre>
   */
  public static final class ProfileAnonymousStub
      extends io.grpc.stub.AbstractAsyncStub<ProfileAnonymousStub> {
    private ProfileAnonymousStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileAnonymousStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileAnonymousStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves versioned profile data.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key.
     * </pre>
     */
    public void getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetVersionedProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves unversioned profile data.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key.
     * </pre>
     */
    public void getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUnversionedProfileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves a profile key credential.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key, or an `INVALID_ARGUMENT` status if the given credential type is invalid.
     * </pre>
     */
    public void getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetExpiringProfileKeyCredentialMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProfileAnonymous.
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data using "unidentified access"
   * credentials. Callers must not submit any self-identifying credentials
   * when calling methods in this service and must instead present the targeted account's
   * unidentified access key as an anonymous authentication mechanism. Callers
   * without an unidentified access key should use the equivalent, authenticated
   * methods in `Profile` instead.
   * </pre>
   */
  public static final class ProfileAnonymousBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProfileAnonymousBlockingStub> {
    private ProfileAnonymousBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileAnonymousBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileAnonymousBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves versioned profile data.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key.
     * </pre>
     */
    public org.ghostprotocol.chat.profile.GetVersionedProfileResponse getVersionedProfile(org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetVersionedProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieves unversioned profile data.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key.
     * </pre>
     */
    public org.ghostprotocol.chat.profile.GetUnversionedProfileResponse getUnversionedProfile(org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUnversionedProfileMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieves a profile key credential.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key, or an `INVALID_ARGUMENT` status if the given credential type is invalid.
     * </pre>
     */
    public org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse getExpiringProfileKeyCredential(org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetExpiringProfileKeyCredentialMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProfileAnonymous.
   * <pre>
   **
   * Provides methods for working with profiles and profile-related data using "unidentified access"
   * credentials. Callers must not submit any self-identifying credentials
   * when calling methods in this service and must instead present the targeted account's
   * unidentified access key as an anonymous authentication mechanism. Callers
   * without an unidentified access key should use the equivalent, authenticated
   * methods in `Profile` instead.
   * </pre>
   */
  public static final class ProfileAnonymousFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProfileAnonymousFutureStub> {
    private ProfileAnonymousFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfileAnonymousFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfileAnonymousFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves versioned profile data.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.profile.GetVersionedProfileResponse> getVersionedProfile(
        org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetVersionedProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieves unversioned profile data.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse> getUnversionedProfile(
        org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUnversionedProfileMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieves a profile key credential.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found. It may also fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key, or an `INVALID_ARGUMENT` status if the given credential type is invalid.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse> getExpiringProfileKeyCredential(
        org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetExpiringProfileKeyCredentialMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_VERSIONED_PROFILE = 0;
  private static final int METHODID_GET_UNVERSIONED_PROFILE = 1;
  private static final int METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL = 2;

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
        case METHODID_GET_VERSIONED_PROFILE:
          serviceImpl.getVersionedProfile((org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetVersionedProfileResponse>) responseObserver);
          break;
        case METHODID_GET_UNVERSIONED_PROFILE:
          serviceImpl.getUnversionedProfile((org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>) responseObserver);
          break;
        case METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL:
          serviceImpl.getExpiringProfileKeyCredential((org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest) request,
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
          getGetVersionedProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.profile.GetVersionedProfileAnonymousRequest,
              org.ghostprotocol.chat.profile.GetVersionedProfileResponse>(
                service, METHODID_GET_VERSIONED_PROFILE)))
        .addMethod(
          getGetUnversionedProfileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.profile.GetUnversionedProfileAnonymousRequest,
              org.ghostprotocol.chat.profile.GetUnversionedProfileResponse>(
                service, METHODID_GET_UNVERSIONED_PROFILE)))
        .addMethod(
          getGetExpiringProfileKeyCredentialMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialAnonymousRequest,
              org.ghostprotocol.chat.profile.GetExpiringProfileKeyCredentialResponse>(
                service, METHODID_GET_EXPIRING_PROFILE_KEY_CREDENTIAL)))
        .build();
  }

  private static abstract class ProfileAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProfileAnonymousBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.profile.ProfileOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProfileAnonymous");
    }
  }

  private static final class ProfileAnonymousFileDescriptorSupplier
      extends ProfileAnonymousBaseDescriptorSupplier {
    ProfileAnonymousFileDescriptorSupplier() {}
  }

  private static final class ProfileAnonymousMethodDescriptorSupplier
      extends ProfileAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProfileAnonymousMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ProfileAnonymousGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProfileAnonymousFileDescriptorSupplier())
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
