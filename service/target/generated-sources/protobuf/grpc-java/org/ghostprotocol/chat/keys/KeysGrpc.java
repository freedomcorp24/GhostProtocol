package org.ghostprotocol.chat.keys;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for working with pre-keys.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/keys.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class KeysGrpc {

  private KeysGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.keys.Keys";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeyCountRequest,
      org.ghostprotocol.chat.keys.GetPreKeyCountResponse> getGetPreKeyCountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPreKeyCount",
      requestType = org.ghostprotocol.chat.keys.GetPreKeyCountRequest.class,
      responseType = org.ghostprotocol.chat.keys.GetPreKeyCountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeyCountRequest,
      org.ghostprotocol.chat.keys.GetPreKeyCountResponse> getGetPreKeyCountMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeyCountRequest, org.ghostprotocol.chat.keys.GetPreKeyCountResponse> getGetPreKeyCountMethod;
    if ((getGetPreKeyCountMethod = KeysGrpc.getGetPreKeyCountMethod) == null) {
      synchronized (KeysGrpc.class) {
        if ((getGetPreKeyCountMethod = KeysGrpc.getGetPreKeyCountMethod) == null) {
          KeysGrpc.getGetPreKeyCountMethod = getGetPreKeyCountMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.keys.GetPreKeyCountRequest, org.ghostprotocol.chat.keys.GetPreKeyCountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPreKeyCount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.GetPreKeyCountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.GetPreKeyCountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeysMethodDescriptorSupplier("GetPreKeyCount"))
              .build();
        }
      }
    }
    return getGetPreKeyCountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeysRequest,
      org.ghostprotocol.chat.keys.GetPreKeysResponse> getGetPreKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPreKeys",
      requestType = org.ghostprotocol.chat.keys.GetPreKeysRequest.class,
      responseType = org.ghostprotocol.chat.keys.GetPreKeysResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeysRequest,
      org.ghostprotocol.chat.keys.GetPreKeysResponse> getGetPreKeysMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.GetPreKeysRequest, org.ghostprotocol.chat.keys.GetPreKeysResponse> getGetPreKeysMethod;
    if ((getGetPreKeysMethod = KeysGrpc.getGetPreKeysMethod) == null) {
      synchronized (KeysGrpc.class) {
        if ((getGetPreKeysMethod = KeysGrpc.getGetPreKeysMethod) == null) {
          KeysGrpc.getGetPreKeysMethod = getGetPreKeysMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.keys.GetPreKeysRequest, org.ghostprotocol.chat.keys.GetPreKeysResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPreKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.GetPreKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.GetPreKeysResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeysMethodDescriptorSupplier("GetPreKeys"))
              .build();
        }
      }
    }
    return getGetPreKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest,
      org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetOneTimeEcPreKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetOneTimeEcPreKeys",
      requestType = org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest.class,
      responseType = org.ghostprotocol.chat.keys.SetPreKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest,
      org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetOneTimeEcPreKeysMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest, org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetOneTimeEcPreKeysMethod;
    if ((getSetOneTimeEcPreKeysMethod = KeysGrpc.getSetOneTimeEcPreKeysMethod) == null) {
      synchronized (KeysGrpc.class) {
        if ((getSetOneTimeEcPreKeysMethod = KeysGrpc.getSetOneTimeEcPreKeysMethod) == null) {
          KeysGrpc.getSetOneTimeEcPreKeysMethod = getSetOneTimeEcPreKeysMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest, org.ghostprotocol.chat.keys.SetPreKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetOneTimeEcPreKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.SetPreKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeysMethodDescriptorSupplier("SetOneTimeEcPreKeys"))
              .build();
        }
      }
    }
    return getSetOneTimeEcPreKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest,
      org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetOneTimeKemSignedPreKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetOneTimeKemSignedPreKeys",
      requestType = org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest.class,
      responseType = org.ghostprotocol.chat.keys.SetPreKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest,
      org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetOneTimeKemSignedPreKeysMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest, org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetOneTimeKemSignedPreKeysMethod;
    if ((getSetOneTimeKemSignedPreKeysMethod = KeysGrpc.getSetOneTimeKemSignedPreKeysMethod) == null) {
      synchronized (KeysGrpc.class) {
        if ((getSetOneTimeKemSignedPreKeysMethod = KeysGrpc.getSetOneTimeKemSignedPreKeysMethod) == null) {
          KeysGrpc.getSetOneTimeKemSignedPreKeysMethod = getSetOneTimeKemSignedPreKeysMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest, org.ghostprotocol.chat.keys.SetPreKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetOneTimeKemSignedPreKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.SetPreKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeysMethodDescriptorSupplier("SetOneTimeKemSignedPreKeys"))
              .build();
        }
      }
    }
    return getSetOneTimeKemSignedPreKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest,
      org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetEcSignedPreKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetEcSignedPreKey",
      requestType = org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest.class,
      responseType = org.ghostprotocol.chat.keys.SetPreKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest,
      org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetEcSignedPreKeyMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest, org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetEcSignedPreKeyMethod;
    if ((getSetEcSignedPreKeyMethod = KeysGrpc.getSetEcSignedPreKeyMethod) == null) {
      synchronized (KeysGrpc.class) {
        if ((getSetEcSignedPreKeyMethod = KeysGrpc.getSetEcSignedPreKeyMethod) == null) {
          KeysGrpc.getSetEcSignedPreKeyMethod = getSetEcSignedPreKeyMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest, org.ghostprotocol.chat.keys.SetPreKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetEcSignedPreKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.SetPreKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeysMethodDescriptorSupplier("SetEcSignedPreKey"))
              .build();
        }
      }
    }
    return getSetEcSignedPreKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest,
      org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetKemLastResortPreKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetKemLastResortPreKey",
      requestType = org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest.class,
      responseType = org.ghostprotocol.chat.keys.SetPreKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest,
      org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetKemLastResortPreKeyMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest, org.ghostprotocol.chat.keys.SetPreKeyResponse> getSetKemLastResortPreKeyMethod;
    if ((getSetKemLastResortPreKeyMethod = KeysGrpc.getSetKemLastResortPreKeyMethod) == null) {
      synchronized (KeysGrpc.class) {
        if ((getSetKemLastResortPreKeyMethod = KeysGrpc.getSetKemLastResortPreKeyMethod) == null) {
          KeysGrpc.getSetKemLastResortPreKeyMethod = getSetKemLastResortPreKeyMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest, org.ghostprotocol.chat.keys.SetPreKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetKemLastResortPreKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.keys.SetPreKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeysMethodDescriptorSupplier("SetKemLastResortPreKey"))
              .build();
        }
      }
    }
    return getSetKemLastResortPreKeyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KeysStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeysStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeysStub>() {
        @java.lang.Override
        public KeysStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeysStub(channel, callOptions);
        }
      };
    return KeysStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KeysBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeysBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeysBlockingStub>() {
        @java.lang.Override
        public KeysBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeysBlockingStub(channel, callOptions);
        }
      };
    return KeysBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KeysFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeysFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeysFutureStub>() {
        @java.lang.Override
        public KeysFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeysFutureStub(channel, callOptions);
        }
      };
    return KeysFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for working with pre-keys.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Retrieves an approximate count of the number of the various kinds of
     * pre-keys stored for the authenticated device.
     * </pre>
     */
    default void getPreKeyCount(org.ghostprotocol.chat.keys.GetPreKeyCountRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeyCountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPreKeyCountMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves a set of pre-keys for establishing a session with the targeted
     * device or devices. Note that callers with an unidentified access key for
     * the targeted account should use the version of this method in
     * `KeysAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found, if no active device with the given ID (if specified) was found on
     * the target account, or if the account has no active devices. It may also
     * fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    default void getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeysResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPreKeysMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Uploads a new set of one-time EC pre-keys for the authenticated device,
     * clearing any previously-stored pre-keys. Note that all keys submitted via
     * a single call to this method _must_ have the same identity type (i.e. if
     * the first key has an ACI identity type, then all other keys in the same
     * stream must also have an ACI identity type).
     * This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
     * given pre-keys was structurally invalid or if the list of pre-keys was
     * empty.
     * </pre>
     */
    default void setOneTimeEcPreKeys(org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetOneTimeEcPreKeysMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Uploads a new set of one-time KEM pre-keys for the authenticated device,
     * clearing any previously-stored pre-keys. Note that all keys submitted via
     * a single call to this method _must_ have the same identity type (i.e. if
     * the first key has an ACI identity type, then all other keys in the same
     * stream must also have an ACI identity type).
     * This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
     * given pre-keys was structurally invalid, had an invalid signature, or if
     * the list of pre-keys was empty.
     * </pre>
     */
    default void setOneTimeKemSignedPreKeys(org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetOneTimeKemSignedPreKeysMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the signed EC pre-key for one identity (i.e. ACI or PNI) associated
     * with the authenticated device.
     * This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
     * was structurally invalid, had a bad signature, or was missing entirely.
     * </pre>
     */
    default void setEcSignedPreKey(org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetEcSignedPreKeyMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the last-resort KEM pre-key for one identity (i.e. ACI or PNI)
     * associated with the authenticated device.
     * This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
     * was structurally invalid, had a bad signature, or was missing entirely.
     * </pre>
     */
    default void setKemLastResortPreKey(org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetKemLastResortPreKeyMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Keys.
   * <pre>
   **
   * Provides methods for working with pre-keys.
   * </pre>
   */
  public static abstract class KeysImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KeysGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Keys.
   * <pre>
   **
   * Provides methods for working with pre-keys.
   * </pre>
   */
  public static final class KeysStub
      extends io.grpc.stub.AbstractAsyncStub<KeysStub> {
    private KeysStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeysStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeysStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves an approximate count of the number of the various kinds of
     * pre-keys stored for the authenticated device.
     * </pre>
     */
    public void getPreKeyCount(org.ghostprotocol.chat.keys.GetPreKeyCountRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeyCountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPreKeyCountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves a set of pre-keys for establishing a session with the targeted
     * device or devices. Note that callers with an unidentified access key for
     * the targeted account should use the version of this method in
     * `KeysAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found, if no active device with the given ID (if specified) was found on
     * the target account, or if the account has no active devices. It may also
     * fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public void getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeysResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPreKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Uploads a new set of one-time EC pre-keys for the authenticated device,
     * clearing any previously-stored pre-keys. Note that all keys submitted via
     * a single call to this method _must_ have the same identity type (i.e. if
     * the first key has an ACI identity type, then all other keys in the same
     * stream must also have an ACI identity type).
     * This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
     * given pre-keys was structurally invalid or if the list of pre-keys was
     * empty.
     * </pre>
     */
    public void setOneTimeEcPreKeys(org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetOneTimeEcPreKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Uploads a new set of one-time KEM pre-keys for the authenticated device,
     * clearing any previously-stored pre-keys. Note that all keys submitted via
     * a single call to this method _must_ have the same identity type (i.e. if
     * the first key has an ACI identity type, then all other keys in the same
     * stream must also have an ACI identity type).
     * This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
     * given pre-keys was structurally invalid, had an invalid signature, or if
     * the list of pre-keys was empty.
     * </pre>
     */
    public void setOneTimeKemSignedPreKeys(org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetOneTimeKemSignedPreKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the signed EC pre-key for one identity (i.e. ACI or PNI) associated
     * with the authenticated device.
     * This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
     * was structurally invalid, had a bad signature, or was missing entirely.
     * </pre>
     */
    public void setEcSignedPreKey(org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetEcSignedPreKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the last-resort KEM pre-key for one identity (i.e. ACI or PNI)
     * associated with the authenticated device.
     * This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
     * was structurally invalid, had a bad signature, or was missing entirely.
     * </pre>
     */
    public void setKemLastResortPreKey(org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetKemLastResortPreKeyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Keys.
   * <pre>
   **
   * Provides methods for working with pre-keys.
   * </pre>
   */
  public static final class KeysBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KeysBlockingStub> {
    private KeysBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeysBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeysBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves an approximate count of the number of the various kinds of
     * pre-keys stored for the authenticated device.
     * </pre>
     */
    public org.ghostprotocol.chat.keys.GetPreKeyCountResponse getPreKeyCount(org.ghostprotocol.chat.keys.GetPreKeyCountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPreKeyCountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieves a set of pre-keys for establishing a session with the targeted
     * device or devices. Note that callers with an unidentified access key for
     * the targeted account should use the version of this method in
     * `KeysAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found, if no active device with the given ID (if specified) was found on
     * the target account, or if the account has no active devices. It may also
     * fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.keys.GetPreKeysResponse getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPreKeysMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Uploads a new set of one-time EC pre-keys for the authenticated device,
     * clearing any previously-stored pre-keys. Note that all keys submitted via
     * a single call to this method _must_ have the same identity type (i.e. if
     * the first key has an ACI identity type, then all other keys in the same
     * stream must also have an ACI identity type).
     * This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
     * given pre-keys was structurally invalid or if the list of pre-keys was
     * empty.
     * </pre>
     */
    public org.ghostprotocol.chat.keys.SetPreKeyResponse setOneTimeEcPreKeys(org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetOneTimeEcPreKeysMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Uploads a new set of one-time KEM pre-keys for the authenticated device,
     * clearing any previously-stored pre-keys. Note that all keys submitted via
     * a single call to this method _must_ have the same identity type (i.e. if
     * the first key has an ACI identity type, then all other keys in the same
     * stream must also have an ACI identity type).
     * This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
     * given pre-keys was structurally invalid, had an invalid signature, or if
     * the list of pre-keys was empty.
     * </pre>
     */
    public org.ghostprotocol.chat.keys.SetPreKeyResponse setOneTimeKemSignedPreKeys(org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetOneTimeKemSignedPreKeysMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Sets the signed EC pre-key for one identity (i.e. ACI or PNI) associated
     * with the authenticated device.
     * This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
     * was structurally invalid, had a bad signature, or was missing entirely.
     * </pre>
     */
    public org.ghostprotocol.chat.keys.SetPreKeyResponse setEcSignedPreKey(org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetEcSignedPreKeyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Sets the last-resort KEM pre-key for one identity (i.e. ACI or PNI)
     * associated with the authenticated device.
     * This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
     * was structurally invalid, had a bad signature, or was missing entirely.
     * </pre>
     */
    public org.ghostprotocol.chat.keys.SetPreKeyResponse setKemLastResortPreKey(org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetKemLastResortPreKeyMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Keys.
   * <pre>
   **
   * Provides methods for working with pre-keys.
   * </pre>
   */
  public static final class KeysFutureStub
      extends io.grpc.stub.AbstractFutureStub<KeysFutureStub> {
    private KeysFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeysFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeysFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieves an approximate count of the number of the various kinds of
     * pre-keys stored for the authenticated device.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.keys.GetPreKeyCountResponse> getPreKeyCount(
        org.ghostprotocol.chat.keys.GetPreKeyCountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPreKeyCountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieves a set of pre-keys for establishing a session with the targeted
     * device or devices. Note that callers with an unidentified access key for
     * the targeted account should use the version of this method in
     * `KeysAnonymous` instead.
     * This RPC may fail with a `NOT_FOUND` status if the target account was not
     * found, if no active device with the given ID (if specified) was found on
     * the target account, or if the account has no active devices. It may also
     * fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(
        org.ghostprotocol.chat.keys.GetPreKeysRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPreKeysMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Uploads a new set of one-time EC pre-keys for the authenticated device,
     * clearing any previously-stored pre-keys. Note that all keys submitted via
     * a single call to this method _must_ have the same identity type (i.e. if
     * the first key has an ACI identity type, then all other keys in the same
     * stream must also have an ACI identity type).
     * This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
     * given pre-keys was structurally invalid or if the list of pre-keys was
     * empty.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeEcPreKeys(
        org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetOneTimeEcPreKeysMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Uploads a new set of one-time KEM pre-keys for the authenticated device,
     * clearing any previously-stored pre-keys. Note that all keys submitted via
     * a single call to this method _must_ have the same identity type (i.e. if
     * the first key has an ACI identity type, then all other keys in the same
     * stream must also have an ACI identity type).
     * This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
     * given pre-keys was structurally invalid, had an invalid signature, or if
     * the list of pre-keys was empty.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeKemSignedPreKeys(
        org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetOneTimeKemSignedPreKeysMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Sets the signed EC pre-key for one identity (i.e. ACI or PNI) associated
     * with the authenticated device.
     * This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
     * was structurally invalid, had a bad signature, or was missing entirely.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.keys.SetPreKeyResponse> setEcSignedPreKey(
        org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetEcSignedPreKeyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Sets the last-resort KEM pre-key for one identity (i.e. ACI or PNI)
     * associated with the authenticated device.
     * This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
     * was structurally invalid, had a bad signature, or was missing entirely.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.keys.SetPreKeyResponse> setKemLastResortPreKey(
        org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetKemLastResortPreKeyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PRE_KEY_COUNT = 0;
  private static final int METHODID_GET_PRE_KEYS = 1;
  private static final int METHODID_SET_ONE_TIME_EC_PRE_KEYS = 2;
  private static final int METHODID_SET_ONE_TIME_KEM_SIGNED_PRE_KEYS = 3;
  private static final int METHODID_SET_EC_SIGNED_PRE_KEY = 4;
  private static final int METHODID_SET_KEM_LAST_RESORT_PRE_KEY = 5;

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
        case METHODID_GET_PRE_KEY_COUNT:
          serviceImpl.getPreKeyCount((org.ghostprotocol.chat.keys.GetPreKeyCountRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeyCountResponse>) responseObserver);
          break;
        case METHODID_GET_PRE_KEYS:
          serviceImpl.getPreKeys((org.ghostprotocol.chat.keys.GetPreKeysRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeysResponse>) responseObserver);
          break;
        case METHODID_SET_ONE_TIME_EC_PRE_KEYS:
          serviceImpl.setOneTimeEcPreKeys((org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse>) responseObserver);
          break;
        case METHODID_SET_ONE_TIME_KEM_SIGNED_PRE_KEYS:
          serviceImpl.setOneTimeKemSignedPreKeys((org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse>) responseObserver);
          break;
        case METHODID_SET_EC_SIGNED_PRE_KEY:
          serviceImpl.setEcSignedPreKey((org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse>) responseObserver);
          break;
        case METHODID_SET_KEM_LAST_RESORT_PRE_KEY:
          serviceImpl.setKemLastResortPreKey((org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse>) responseObserver);
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
          getGetPreKeyCountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.keys.GetPreKeyCountRequest,
              org.ghostprotocol.chat.keys.GetPreKeyCountResponse>(
                service, METHODID_GET_PRE_KEY_COUNT)))
        .addMethod(
          getGetPreKeysMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.keys.GetPreKeysRequest,
              org.ghostprotocol.chat.keys.GetPreKeysResponse>(
                service, METHODID_GET_PRE_KEYS)))
        .addMethod(
          getSetOneTimeEcPreKeysMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest,
              org.ghostprotocol.chat.keys.SetPreKeyResponse>(
                service, METHODID_SET_ONE_TIME_EC_PRE_KEYS)))
        .addMethod(
          getSetOneTimeKemSignedPreKeysMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest,
              org.ghostprotocol.chat.keys.SetPreKeyResponse>(
                service, METHODID_SET_ONE_TIME_KEM_SIGNED_PRE_KEYS)))
        .addMethod(
          getSetEcSignedPreKeyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest,
              org.ghostprotocol.chat.keys.SetPreKeyResponse>(
                service, METHODID_SET_EC_SIGNED_PRE_KEY)))
        .addMethod(
          getSetKemLastResortPreKeyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest,
              org.ghostprotocol.chat.keys.SetPreKeyResponse>(
                service, METHODID_SET_KEM_LAST_RESORT_PRE_KEY)))
        .build();
  }

  private static abstract class KeysBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KeysBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.keys.KeysOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Keys");
    }
  }

  private static final class KeysFileDescriptorSupplier
      extends KeysBaseDescriptorSupplier {
    KeysFileDescriptorSupplier() {}
  }

  private static final class KeysMethodDescriptorSupplier
      extends KeysBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KeysMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KeysGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KeysFileDescriptorSupplier())
              .addMethod(getGetPreKeyCountMethod())
              .addMethod(getGetPreKeysMethod())
              .addMethod(getSetOneTimeEcPreKeysMethod())
              .addMethod(getSetOneTimeKemSignedPreKeysMethod())
              .addMethod(getSetEcSignedPreKeyMethod())
              .addMethod(getSetKemLastResortPreKeyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
