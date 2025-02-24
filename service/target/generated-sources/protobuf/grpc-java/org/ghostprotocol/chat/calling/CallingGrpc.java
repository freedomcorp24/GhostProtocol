package org.ghostprotocol.chat.calling;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for getting credentials for one-on-one and group calls.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/calling.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CallingGrpc {

  private CallingGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.calling.Calling";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.calling.GetTurnCredentialsRequest,
      org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> getGetTurnCredentialsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTurnCredentials",
      requestType = org.ghostprotocol.chat.calling.GetTurnCredentialsRequest.class,
      responseType = org.ghostprotocol.chat.calling.GetTurnCredentialsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.calling.GetTurnCredentialsRequest,
      org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> getGetTurnCredentialsMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.calling.GetTurnCredentialsRequest, org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> getGetTurnCredentialsMethod;
    if ((getGetTurnCredentialsMethod = CallingGrpc.getGetTurnCredentialsMethod) == null) {
      synchronized (CallingGrpc.class) {
        if ((getGetTurnCredentialsMethod = CallingGrpc.getGetTurnCredentialsMethod) == null) {
          CallingGrpc.getGetTurnCredentialsMethod = getGetTurnCredentialsMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.calling.GetTurnCredentialsRequest, org.ghostprotocol.chat.calling.GetTurnCredentialsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTurnCredentials"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.calling.GetTurnCredentialsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.calling.GetTurnCredentialsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CallingMethodDescriptorSupplier("GetTurnCredentials"))
              .build();
        }
      }
    }
    return getGetTurnCredentialsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CallingStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallingStub>() {
        @java.lang.Override
        public CallingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallingStub(channel, callOptions);
        }
      };
    return CallingStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CallingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallingBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallingBlockingStub>() {
        @java.lang.Override
        public CallingBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallingBlockingStub(channel, callOptions);
        }
      };
    return CallingBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CallingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CallingFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CallingFutureStub>() {
        @java.lang.Override
        public CallingFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CallingFutureStub(channel, callOptions);
        }
      };
    return CallingFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for getting credentials for one-on-one and group calls.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Generates and returns TURN credentials for the caller.
     * This RPC may fail with a `RESOURCE_EXHAUSTED` status if a rate limit for
     * generating TURN credentials has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    default void getTurnCredentials(org.ghostprotocol.chat.calling.GetTurnCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTurnCredentialsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Calling.
   * <pre>
   **
   * Provides methods for getting credentials for one-on-one and group calls.
   * </pre>
   */
  public static abstract class CallingImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CallingGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Calling.
   * <pre>
   **
   * Provides methods for getting credentials for one-on-one and group calls.
   * </pre>
   */
  public static final class CallingStub
      extends io.grpc.stub.AbstractAsyncStub<CallingStub> {
    private CallingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Generates and returns TURN credentials for the caller.
     * This RPC may fail with a `RESOURCE_EXHAUSTED` status if a rate limit for
     * generating TURN credentials has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public void getTurnCredentials(org.ghostprotocol.chat.calling.GetTurnCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTurnCredentialsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Calling.
   * <pre>
   **
   * Provides methods for getting credentials for one-on-one and group calls.
   * </pre>
   */
  public static final class CallingBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CallingBlockingStub> {
    private CallingBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallingBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallingBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Generates and returns TURN credentials for the caller.
     * This RPC may fail with a `RESOURCE_EXHAUSTED` status if a rate limit for
     * generating TURN credentials has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.calling.GetTurnCredentialsResponse getTurnCredentials(org.ghostprotocol.chat.calling.GetTurnCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTurnCredentialsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Calling.
   * <pre>
   **
   * Provides methods for getting credentials for one-on-one and group calls.
   * </pre>
   */
  public static final class CallingFutureStub
      extends io.grpc.stub.AbstractFutureStub<CallingFutureStub> {
    private CallingFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CallingFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CallingFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Generates and returns TURN credentials for the caller.
     * This RPC may fail with a `RESOURCE_EXHAUSTED` status if a rate limit for
     * generating TURN credentials has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse> getTurnCredentials(
        org.ghostprotocol.chat.calling.GetTurnCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTurnCredentialsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_TURN_CREDENTIALS = 0;

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
        case METHODID_GET_TURN_CREDENTIALS:
          serviceImpl.getTurnCredentials((org.ghostprotocol.chat.calling.GetTurnCredentialsRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.calling.GetTurnCredentialsResponse>) responseObserver);
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
          getGetTurnCredentialsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.calling.GetTurnCredentialsRequest,
              org.ghostprotocol.chat.calling.GetTurnCredentialsResponse>(
                service, METHODID_GET_TURN_CREDENTIALS)))
        .build();
  }

  private static abstract class CallingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CallingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.calling.CallingOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Calling");
    }
  }

  private static final class CallingFileDescriptorSupplier
      extends CallingBaseDescriptorSupplier {
    CallingFileDescriptorSupplier() {}
  }

  private static final class CallingMethodDescriptorSupplier
      extends CallingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CallingMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (CallingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CallingFileDescriptorSupplier())
              .addMethod(getGetTurnCredentialsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
