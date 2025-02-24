package org.ghostprotocol.chat.payments;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for working with payments.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/payments.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class PaymentsGrpc {

  private PaymentsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.payments.Payments";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest,
      org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> getGetCurrencyConversionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCurrencyConversions",
      requestType = org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest.class,
      responseType = org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest,
      org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> getGetCurrencyConversionsMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest, org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> getGetCurrencyConversionsMethod;
    if ((getGetCurrencyConversionsMethod = PaymentsGrpc.getGetCurrencyConversionsMethod) == null) {
      synchronized (PaymentsGrpc.class) {
        if ((getGetCurrencyConversionsMethod = PaymentsGrpc.getGetCurrencyConversionsMethod) == null) {
          PaymentsGrpc.getGetCurrencyConversionsMethod = getGetCurrencyConversionsMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest, org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCurrencyConversions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PaymentsMethodDescriptorSupplier("GetCurrencyConversions"))
              .build();
        }
      }
    }
    return getGetCurrencyConversionsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PaymentsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaymentsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaymentsStub>() {
        @java.lang.Override
        public PaymentsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaymentsStub(channel, callOptions);
        }
      };
    return PaymentsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PaymentsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaymentsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaymentsBlockingStub>() {
        @java.lang.Override
        public PaymentsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaymentsBlockingStub(channel, callOptions);
        }
      };
    return PaymentsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PaymentsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PaymentsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PaymentsFutureStub>() {
        @java.lang.Override
        public PaymentsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PaymentsFutureStub(channel, callOptions);
        }
      };
    return PaymentsFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for working with payments.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * </pre>
     */
    default void getCurrencyConversions(org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCurrencyConversionsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Payments.
   * <pre>
   **
   * Provides methods for working with payments.
   * </pre>
   */
  public static abstract class PaymentsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PaymentsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Payments.
   * <pre>
   **
   * Provides methods for working with payments.
   * </pre>
   */
  public static final class PaymentsStub
      extends io.grpc.stub.AbstractAsyncStub<PaymentsStub> {
    private PaymentsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaymentsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaymentsStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * </pre>
     */
    public void getCurrencyConversions(org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCurrencyConversionsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Payments.
   * <pre>
   **
   * Provides methods for working with payments.
   * </pre>
   */
  public static final class PaymentsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PaymentsBlockingStub> {
    private PaymentsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaymentsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaymentsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * </pre>
     */
    public org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse getCurrencyConversions(org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCurrencyConversionsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Payments.
   * <pre>
   **
   * Provides methods for working with payments.
   * </pre>
   */
  public static final class PaymentsFutureStub
      extends io.grpc.stub.AbstractFutureStub<PaymentsFutureStub> {
    private PaymentsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PaymentsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PaymentsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse> getCurrencyConversions(
        org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCurrencyConversionsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CURRENCY_CONVERSIONS = 0;

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
        case METHODID_GET_CURRENCY_CONVERSIONS:
          serviceImpl.getCurrencyConversions((org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse>) responseObserver);
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
          getGetCurrencyConversionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.payments.GetCurrencyConversionsRequest,
              org.ghostprotocol.chat.payments.GetCurrencyConversionsResponse>(
                service, METHODID_GET_CURRENCY_CONVERSIONS)))
        .build();
  }

  private static abstract class PaymentsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PaymentsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.payments.PaymentsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Payments");
    }
  }

  private static final class PaymentsFileDescriptorSupplier
      extends PaymentsBaseDescriptorSupplier {
    PaymentsFileDescriptorSupplier() {}
  }

  private static final class PaymentsMethodDescriptorSupplier
      extends PaymentsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PaymentsMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (PaymentsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PaymentsFileDescriptorSupplier())
              .addMethod(getGetCurrencyConversionsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
