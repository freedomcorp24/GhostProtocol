package org.ghostprotocol.chat.keys;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for working with pre-keys using "unidentified access"
 * credentials.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/keys.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class KeysAnonymousGrpc {

  private KeysAnonymousGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.keys.KeysAnonymous";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest,
      org.ghostprotocol.chat.keys.GetPreKeysResponse> getGetPreKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPreKeys",
      requestType = org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest.class,
      responseType = org.ghostprotocol.chat.keys.GetPreKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest,
      org.ghostprotocol.chat.keys.GetPreKeysResponse> getGetPreKeysMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest, org.ghostprotocol.chat.keys.GetPreKeysResponse> getGetPreKeysMethod;
    if ((getGetPreKeysMethod = KeysAnonymousGrpc.getGetPreKeysMethod) == null) {
      synchronized (KeysAnonymousGrpc.class) {
        if ((getGetPreKeysMethod = KeysAnonymousGrpc.getGetPreKeysMethod) == null) {
          KeysAnonymousGrpc.getGetPreKeysMethod = getGetPreKeysMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest, org.ghostprotocol.chat.keys.GetPreKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPreKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.GetPreKeysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeysAnonymousMethodDescriptorSupplier("GetPreKeys"))
              .build();
        }
      }
    }
    return getGetPreKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.CheckIdentityKeyRequest,
      org.ghostprotocol.chat.keys.CheckIdentityKeyResponse> getCheckIdentityKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckIdentityKeys",
      requestType = org.ghostprotocol.chat.keys.CheckIdentityKeyRequest.class,
      responseType = org.ghostprotocol.chat.keys.CheckIdentityKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.CheckIdentityKeyRequest,
      org.ghostprotocol.chat.keys.CheckIdentityKeyResponse> getCheckIdentityKeysMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.CheckIdentityKeyRequest, org.ghostprotocol.chat.keys.CheckIdentityKeyResponse> getCheckIdentityKeysMethod;
    if ((getCheckIdentityKeysMethod = KeysAnonymousGrpc.getCheckIdentityKeysMethod) == null) {
      synchronized (KeysAnonymousGrpc.class) {
        if ((getCheckIdentityKeysMethod = KeysAnonymousGrpc.getCheckIdentityKeysMethod) == null) {
          KeysAnonymousGrpc.getCheckIdentityKeysMethod = getCheckIdentityKeysMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.keys.CheckIdentityKeyRequest, org.ghostprotocol.chat.keys.CheckIdentityKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckIdentityKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.CheckIdentityKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.CheckIdentityKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeysAnonymousMethodDescriptorSupplier("CheckIdentityKeys"))
              .build();
        }
      }
    }
    return getCheckIdentityKeysMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KeysAnonymousStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeysAnonymousStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeysAnonymousStub>() {
        @java.lang.Override
        public KeysAnonymousStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeysAnonymousStub(channel, callOptions);
        }
      };
    return KeysAnonymousStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KeysAnonymousBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeysAnonymousBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeysAnonymousBlockingStub>() {
        @java.lang.Override
        public KeysAnonymousBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeysAnonymousBlockingStub(channel, callOptions);
        }
      };
    return KeysAnonymousBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KeysAnonymousFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeysAnonymousFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeysAnonymousFutureStub>() {
        @java.lang.Override
        public KeysAnonymousFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeysAnonymousFutureStub(channel, callOptions);
        }
      };
    return KeysAnonymousFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for working with pre-keys using "unidentified access"
   * credentials.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Retrieves a set of pre-keys for establishing a session with the targeted
     * device or devices. Callers must not submit any self-identifying credentials
     * when calling this method and must instead present the targeted account's
     * unidentified access key as an anonymous authentication mechanism. Callers
     * without an unidentified access key should use the equivalent, authenticated
     * method in `Keys` instead.
     * This RPC may fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key or if the target account was not found. It may also fail with a
     * `NOT_FOUND` status if no active device with the given ID (if specified) was
     * found on the target account, or if the target account has no active
     * devices.
     * </pre>
     */
    default void getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeysResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPreKeysMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Checks identity key fingerprints of the target accounts.
     * Returns a stream of elements, each one representing an account that had a mismatched
     * identity key fingerprint with the server and the corresponding identity key stored by the server.
     * </pre>
     */
    default io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.CheckIdentityKeyRequest> checkIdentityKeys(
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.CheckIdentityKeyResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getCheckIdentityKeysMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KeysAnonymous.
   * <pre>
   **
   * Provides methods for working with pre-keys using "unidentified access"
   * credentials.
   * </pre>
   */
  public static abstract class KeysAnonymousImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KeysAnonymousGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KeysAnonymous.
   * <pre>
   **
   * Provides methods for working with pre-keys using "unidentified access"
   * credentials.
   * </pre>
   */
  public static final class KeysAnonymousStub
      extends io.grpc.stub.AbstractAsyncStub<KeysAnonymousStub> {
    private KeysAnonymousStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeysAnonymousStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeysAnonymousStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves a set of pre-keys for establishing a session with the targeted
     * device or devices. Callers must not submit any self-identifying credentials
     * when calling this method and must instead present the targeted account's
     * unidentified access key as an anonymous authentication mechanism. Callers
     * without an unidentified access key should use the equivalent, authenticated
     * method in `Keys` instead.
     * This RPC may fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key or if the target account was not found. It may also fail with a
     * `NOT_FOUND` status if no active device with the given ID (if specified) was
     * found on the target account, or if the target account has no active
     * devices.
     * </pre>
     */
    public void getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeysResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPreKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Checks identity key fingerprints of the target accounts.
     * Returns a stream of elements, each one representing an account that had a mismatched
     * identity key fingerprint with the server and the corresponding identity key stored by the server.
     * </pre>
     */
    public io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.CheckIdentityKeyRequest> checkIdentityKeys(
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.CheckIdentityKeyResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getCheckIdentityKeysMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KeysAnonymous.
   * <pre>
   **
   * Provides methods for working with pre-keys using "unidentified access"
   * credentials.
   * </pre>
   */
  public static final class KeysAnonymousBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KeysAnonymousBlockingStub> {
    private KeysAnonymousBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeysAnonymousBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeysAnonymousBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves a set of pre-keys for establishing a session with the targeted
     * device or devices. Callers must not submit any self-identifying credentials
     * when calling this method and must instead present the targeted account's
     * unidentified access key as an anonymous authentication mechanism. Callers
     * without an unidentified access key should use the equivalent, authenticated
     * method in `Keys` instead.
     * This RPC may fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key or if the target account was not found. It may also fail with a
     * `NOT_FOUND` status if no active device with the given ID (if specified) was
     * found on the target account, or if the target account has no active
     * devices.
     * </pre>
     */
    public org.ghostprotocol.chat.keys.GetPreKeysResponse getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPreKeysMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KeysAnonymous.
   * <pre>
   **
   * Provides methods for working with pre-keys using "unidentified access"
   * credentials.
   * </pre>
   */
  public static final class KeysAnonymousFutureStub
      extends io.grpc.stub.AbstractFutureStub<KeysAnonymousFutureStub> {
    private KeysAnonymousFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeysAnonymousFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeysAnonymousFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves a set of pre-keys for establishing a session with the targeted
     * device or devices. Callers must not submit any self-identifying credentials
     * when calling this method and must instead present the targeted account's
     * unidentified access key as an anonymous authentication mechanism. Callers
     * without an unidentified access key should use the equivalent, authenticated
     * method in `Keys` instead.
     * This RPC may fail with an `UNAUTHENTICATED` status if the given
     * unidentified access key did not match the target account's unidentified
     * access key or if the target account was not found. It may also fail with a
     * `NOT_FOUND` status if no active device with the given ID (if specified) was
     * found on the target account, or if the target account has no active
     * devices.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(
        org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPreKeysMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PRE_KEYS = 0;
  private static final int METHODID_CHECK_IDENTITY_KEYS = 1;

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
        case METHODID_GET_PRE_KEYS:
          serviceImpl.getPreKeys((org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeysResponse>) responseObserver);
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
        case METHODID_CHECK_IDENTITY_KEYS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.checkIdentityKeys(
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.CheckIdentityKeyResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetPreKeysMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.keys.GetPreKeysAnonymousRequest,
              org.ghostprotocol.chat.keys.GetPreKeysResponse>(
                service, METHODID_GET_PRE_KEYS)))
        .addMethod(
          getCheckIdentityKeysMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              org.ghostprotocol.chat.keys.CheckIdentityKeyRequest,
              org.ghostprotocol.chat.keys.CheckIdentityKeyResponse>(
                service, METHODID_CHECK_IDENTITY_KEYS)))
        .build();
  }

  private static abstract class KeysAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KeysAnonymousBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.keys.KeysOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KeysAnonymous");
    }
  }

  private static final class KeysAnonymousFileDescriptorSupplier
      extends KeysAnonymousBaseDescriptorSupplier {
    KeysAnonymousFileDescriptorSupplier() {}
  }

  private static final class KeysAnonymousMethodDescriptorSupplier
      extends KeysAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KeysAnonymousMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KeysAnonymousGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KeysAnonymousFileDescriptorSupplier())
              .addMethod(getGetPreKeysMethod())
              .addMethod(getCheckIdentityKeysMethod())
              .build();
        }
      }
    }
    return result;
  }
}
