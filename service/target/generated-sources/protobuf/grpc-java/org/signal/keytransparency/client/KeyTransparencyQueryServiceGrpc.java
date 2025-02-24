package org.signal.keytransparency.client;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * An external-facing, read-only key transparency service used by Signal's chat server
 * to look up and monitor identifiers.
 * There are three types of identifier mappings stored by the key transparency log:
 * - An ACI which maps to an ACI identity key
 * - An E164-formatted phone number which maps to an ACI
 * - A username hash which also maps to an ACI
 * Separately, the log also stores and periodically updates a fixed value known as the `distinguished` key.
 * Clients use the verified tree head from looking up this key for future calls to the Search and Monitor endpoints.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/keytransparency/client/KeyTransparencyService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class KeyTransparencyQueryServiceGrpc {

  private KeyTransparencyQueryServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "kt_query.KeyTransparencyQueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.signal.keytransparency.client.DistinguishedRequest,
      org.signal.keytransparency.client.DistinguishedResponse> getDistinguishedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Distinguished",
      requestType = org.signal.keytransparency.client.DistinguishedRequest.class,
      responseType = org.signal.keytransparency.client.DistinguishedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.signal.keytransparency.client.DistinguishedRequest,
      org.signal.keytransparency.client.DistinguishedResponse> getDistinguishedMethod() {
    io.grpc.MethodDescriptor<org.signal.keytransparency.client.DistinguishedRequest, org.signal.keytransparency.client.DistinguishedResponse> getDistinguishedMethod;
    if ((getDistinguishedMethod = KeyTransparencyQueryServiceGrpc.getDistinguishedMethod) == null) {
      synchronized (KeyTransparencyQueryServiceGrpc.class) {
        if ((getDistinguishedMethod = KeyTransparencyQueryServiceGrpc.getDistinguishedMethod) == null) {
          KeyTransparencyQueryServiceGrpc.getDistinguishedMethod = getDistinguishedMethod =
              io.grpc.MethodDescriptor.<org.signal.keytransparency.client.DistinguishedRequest, org.signal.keytransparency.client.DistinguishedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Distinguished"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.signal.keytransparency.client.DistinguishedRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.signal.keytransparency.client.DistinguishedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyTransparencyQueryServiceMethodDescriptorSupplier("Distinguished"))
              .build();
        }
      }
    }
    return getDistinguishedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.signal.keytransparency.client.SearchRequest,
      org.signal.keytransparency.client.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Search",
      requestType = org.signal.keytransparency.client.SearchRequest.class,
      responseType = org.signal.keytransparency.client.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.signal.keytransparency.client.SearchRequest,
      org.signal.keytransparency.client.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<org.signal.keytransparency.client.SearchRequest, org.signal.keytransparency.client.SearchResponse> getSearchMethod;
    if ((getSearchMethod = KeyTransparencyQueryServiceGrpc.getSearchMethod) == null) {
      synchronized (KeyTransparencyQueryServiceGrpc.class) {
        if ((getSearchMethod = KeyTransparencyQueryServiceGrpc.getSearchMethod) == null) {
          KeyTransparencyQueryServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<org.signal.keytransparency.client.SearchRequest, org.signal.keytransparency.client.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.signal.keytransparency.client.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.signal.keytransparency.client.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyTransparencyQueryServiceMethodDescriptorSupplier("Search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.signal.keytransparency.client.MonitorRequest,
      org.signal.keytransparency.client.MonitorResponse> getMonitorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Monitor",
      requestType = org.signal.keytransparency.client.MonitorRequest.class,
      responseType = org.signal.keytransparency.client.MonitorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.signal.keytransparency.client.MonitorRequest,
      org.signal.keytransparency.client.MonitorResponse> getMonitorMethod() {
    io.grpc.MethodDescriptor<org.signal.keytransparency.client.MonitorRequest, org.signal.keytransparency.client.MonitorResponse> getMonitorMethod;
    if ((getMonitorMethod = KeyTransparencyQueryServiceGrpc.getMonitorMethod) == null) {
      synchronized (KeyTransparencyQueryServiceGrpc.class) {
        if ((getMonitorMethod = KeyTransparencyQueryServiceGrpc.getMonitorMethod) == null) {
          KeyTransparencyQueryServiceGrpc.getMonitorMethod = getMonitorMethod =
              io.grpc.MethodDescriptor.<org.signal.keytransparency.client.MonitorRequest, org.signal.keytransparency.client.MonitorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Monitor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.signal.keytransparency.client.MonitorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.signal.keytransparency.client.MonitorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new KeyTransparencyQueryServiceMethodDescriptorSupplier("Monitor"))
              .build();
        }
      }
    }
    return getMonitorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static KeyTransparencyQueryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyQueryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyQueryServiceStub>() {
        @java.lang.Override
        public KeyTransparencyQueryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyTransparencyQueryServiceStub(channel, callOptions);
        }
      };
    return KeyTransparencyQueryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static KeyTransparencyQueryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyQueryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyQueryServiceBlockingStub>() {
        @java.lang.Override
        public KeyTransparencyQueryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyTransparencyQueryServiceBlockingStub(channel, callOptions);
        }
      };
    return KeyTransparencyQueryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static KeyTransparencyQueryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyQueryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<KeyTransparencyQueryServiceFutureStub>() {
        @java.lang.Override
        public KeyTransparencyQueryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new KeyTransparencyQueryServiceFutureStub(channel, callOptions);
        }
      };
    return KeyTransparencyQueryServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * An external-facing, read-only key transparency service used by Signal's chat server
   * to look up and monitor identifiers.
   * There are three types of identifier mappings stored by the key transparency log:
   * - An ACI which maps to an ACI identity key
   * - An E164-formatted phone number which maps to an ACI
   * - A username hash which also maps to an ACI
   * Separately, the log also stores and periodically updates a fixed value known as the `distinguished` key.
   * Clients use the verified tree head from looking up this key for future calls to the Search and Monitor endpoints.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * An endpoint used by clients to retrieve the most recent distinguished tree
     * head, which should be used to derive consistency parameters for
     * subsequent Search and Monitor requests. It should be the first key
     * transparency RPC a client calls.
     * </pre>
     */
    default void distinguished(org.signal.keytransparency.client.DistinguishedRequest request,
        io.grpc.stub.StreamObserver<org.signal.keytransparency.client.DistinguishedResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDistinguishedMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * An endpoint used by clients to search for one or more identifiers in the transparency log.
     * The server returns proof that the identifier(s) exist in the log.
     * </pre>
     */
    default void search(org.signal.keytransparency.client.SearchRequest request,
        io.grpc.stub.StreamObserver<org.signal.keytransparency.client.SearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * An endpoint that allows users to monitor a group of identifiers by returning proof that the log continues to be
     * constructed correctly in later entries for those identifiers.
     * </pre>
     */
    default void monitor(org.signal.keytransparency.client.MonitorRequest request,
        io.grpc.stub.StreamObserver<org.signal.keytransparency.client.MonitorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMonitorMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service KeyTransparencyQueryService.
   * <pre>
   **
   * An external-facing, read-only key transparency service used by Signal's chat server
   * to look up and monitor identifiers.
   * There are three types of identifier mappings stored by the key transparency log:
   * - An ACI which maps to an ACI identity key
   * - An E164-formatted phone number which maps to an ACI
   * - A username hash which also maps to an ACI
   * Separately, the log also stores and periodically updates a fixed value known as the `distinguished` key.
   * Clients use the verified tree head from looking up this key for future calls to the Search and Monitor endpoints.
   * </pre>
   */
  public static abstract class KeyTransparencyQueryServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return KeyTransparencyQueryServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service KeyTransparencyQueryService.
   * <pre>
   **
   * An external-facing, read-only key transparency service used by Signal's chat server
   * to look up and monitor identifiers.
   * There are three types of identifier mappings stored by the key transparency log:
   * - An ACI which maps to an ACI identity key
   * - An E164-formatted phone number which maps to an ACI
   * - A username hash which also maps to an ACI
   * Separately, the log also stores and periodically updates a fixed value known as the `distinguished` key.
   * Clients use the verified tree head from looking up this key for future calls to the Search and Monitor endpoints.
   * </pre>
   */
  public static final class KeyTransparencyQueryServiceStub
      extends io.grpc.stub.AbstractAsyncStub<KeyTransparencyQueryServiceStub> {
    private KeyTransparencyQueryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyTransparencyQueryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyTransparencyQueryServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * An endpoint used by clients to retrieve the most recent distinguished tree
     * head, which should be used to derive consistency parameters for
     * subsequent Search and Monitor requests. It should be the first key
     * transparency RPC a client calls.
     * </pre>
     */
    public void distinguished(org.signal.keytransparency.client.DistinguishedRequest request,
        io.grpc.stub.StreamObserver<org.signal.keytransparency.client.DistinguishedResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDistinguishedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * An endpoint used by clients to search for one or more identifiers in the transparency log.
     * The server returns proof that the identifier(s) exist in the log.
     * </pre>
     */
    public void search(org.signal.keytransparency.client.SearchRequest request,
        io.grpc.stub.StreamObserver<org.signal.keytransparency.client.SearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * An endpoint that allows users to monitor a group of identifiers by returning proof that the log continues to be
     * constructed correctly in later entries for those identifiers.
     * </pre>
     */
    public void monitor(org.signal.keytransparency.client.MonitorRequest request,
        io.grpc.stub.StreamObserver<org.signal.keytransparency.client.MonitorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getMonitorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service KeyTransparencyQueryService.
   * <pre>
   **
   * An external-facing, read-only key transparency service used by Signal's chat server
   * to look up and monitor identifiers.
   * There are three types of identifier mappings stored by the key transparency log:
   * - An ACI which maps to an ACI identity key
   * - An E164-formatted phone number which maps to an ACI
   * - A username hash which also maps to an ACI
   * Separately, the log also stores and periodically updates a fixed value known as the `distinguished` key.
   * Clients use the verified tree head from looking up this key for future calls to the Search and Monitor endpoints.
   * </pre>
   */
  public static final class KeyTransparencyQueryServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<KeyTransparencyQueryServiceBlockingStub> {
    private KeyTransparencyQueryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyTransparencyQueryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyTransparencyQueryServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * An endpoint used by clients to retrieve the most recent distinguished tree
     * head, which should be used to derive consistency parameters for
     * subsequent Search and Monitor requests. It should be the first key
     * transparency RPC a client calls.
     * </pre>
     */
    public org.signal.keytransparency.client.DistinguishedResponse distinguished(org.signal.keytransparency.client.DistinguishedRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDistinguishedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * An endpoint used by clients to search for one or more identifiers in the transparency log.
     * The server returns proof that the identifier(s) exist in the log.
     * </pre>
     */
    public org.signal.keytransparency.client.SearchResponse search(org.signal.keytransparency.client.SearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * An endpoint that allows users to monitor a group of identifiers by returning proof that the log continues to be
     * constructed correctly in later entries for those identifiers.
     * </pre>
     */
    public org.signal.keytransparency.client.MonitorResponse monitor(org.signal.keytransparency.client.MonitorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getMonitorMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service KeyTransparencyQueryService.
   * <pre>
   **
   * An external-facing, read-only key transparency service used by Signal's chat server
   * to look up and monitor identifiers.
   * There are three types of identifier mappings stored by the key transparency log:
   * - An ACI which maps to an ACI identity key
   * - An E164-formatted phone number which maps to an ACI
   * - A username hash which also maps to an ACI
   * Separately, the log also stores and periodically updates a fixed value known as the `distinguished` key.
   * Clients use the verified tree head from looking up this key for future calls to the Search and Monitor endpoints.
   * </pre>
   */
  public static final class KeyTransparencyQueryServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<KeyTransparencyQueryServiceFutureStub> {
    private KeyTransparencyQueryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected KeyTransparencyQueryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new KeyTransparencyQueryServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * An endpoint used by clients to retrieve the most recent distinguished tree
     * head, which should be used to derive consistency parameters for
     * subsequent Search and Monitor requests. It should be the first key
     * transparency RPC a client calls.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.signal.keytransparency.client.DistinguishedResponse> distinguished(
        org.signal.keytransparency.client.DistinguishedRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDistinguishedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * An endpoint used by clients to search for one or more identifiers in the transparency log.
     * The server returns proof that the identifier(s) exist in the log.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.signal.keytransparency.client.SearchResponse> search(
        org.signal.keytransparency.client.SearchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * An endpoint that allows users to monitor a group of identifiers by returning proof that the log continues to be
     * constructed correctly in later entries for those identifiers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.signal.keytransparency.client.MonitorResponse> monitor(
        org.signal.keytransparency.client.MonitorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getMonitorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_DISTINGUISHED = 0;
  private static final int METHODID_SEARCH = 1;
  private static final int METHODID_MONITOR = 2;

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
        case METHODID_DISTINGUISHED:
          serviceImpl.distinguished((org.signal.keytransparency.client.DistinguishedRequest) request,
              (io.grpc.stub.StreamObserver<org.signal.keytransparency.client.DistinguishedResponse>) responseObserver);
          break;
        case METHODID_SEARCH:
          serviceImpl.search((org.signal.keytransparency.client.SearchRequest) request,
              (io.grpc.stub.StreamObserver<org.signal.keytransparency.client.SearchResponse>) responseObserver);
          break;
        case METHODID_MONITOR:
          serviceImpl.monitor((org.signal.keytransparency.client.MonitorRequest) request,
              (io.grpc.stub.StreamObserver<org.signal.keytransparency.client.MonitorResponse>) responseObserver);
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
          getDistinguishedMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.signal.keytransparency.client.DistinguishedRequest,
              org.signal.keytransparency.client.DistinguishedResponse>(
                service, METHODID_DISTINGUISHED)))
        .addMethod(
          getSearchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.signal.keytransparency.client.SearchRequest,
              org.signal.keytransparency.client.SearchResponse>(
                service, METHODID_SEARCH)))
        .addMethod(
          getMonitorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.signal.keytransparency.client.MonitorRequest,
              org.signal.keytransparency.client.MonitorResponse>(
                service, METHODID_MONITOR)))
        .build();
  }

  private static abstract class KeyTransparencyQueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    KeyTransparencyQueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.signal.keytransparency.client.KeyTransparencyService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("KeyTransparencyQueryService");
    }
  }

  private static final class KeyTransparencyQueryServiceFileDescriptorSupplier
      extends KeyTransparencyQueryServiceBaseDescriptorSupplier {
    KeyTransparencyQueryServiceFileDescriptorSupplier() {}
  }

  private static final class KeyTransparencyQueryServiceMethodDescriptorSupplier
      extends KeyTransparencyQueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    KeyTransparencyQueryServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (KeyTransparencyQueryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new KeyTransparencyQueryServiceFileDescriptorSupplier())
              .addMethod(getDistinguishedMethod())
              .addMethod(getSearchMethod())
              .addMethod(getMonitorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
