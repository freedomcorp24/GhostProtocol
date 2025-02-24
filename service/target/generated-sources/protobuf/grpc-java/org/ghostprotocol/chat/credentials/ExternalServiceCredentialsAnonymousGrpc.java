package org.ghostprotocol.chat.credentials;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/credentials.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ExternalServiceCredentialsAnonymousGrpc {

  private ExternalServiceCredentialsAnonymousGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.credentials.ExternalServiceCredentialsAnonymous";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest,
      org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> getCheckSvrCredentialsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckSvrCredentials",
      requestType = org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest.class,
      responseType = org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest,
      org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> getCheckSvrCredentialsMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest, org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> getCheckSvrCredentialsMethod;
    if ((getCheckSvrCredentialsMethod = ExternalServiceCredentialsAnonymousGrpc.getCheckSvrCredentialsMethod) == null) {
      synchronized (ExternalServiceCredentialsAnonymousGrpc.class) {
        if ((getCheckSvrCredentialsMethod = ExternalServiceCredentialsAnonymousGrpc.getCheckSvrCredentialsMethod) == null) {
          ExternalServiceCredentialsAnonymousGrpc.getCheckSvrCredentialsMethod = getCheckSvrCredentialsMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest, org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckSvrCredentials"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ExternalServiceCredentialsAnonymousMethodDescriptorSupplier("CheckSvrCredentials"))
              .build();
        }
      }
    }
    return getCheckSvrCredentialsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExternalServiceCredentialsAnonymousStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsAnonymousStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsAnonymousStub>() {
        @java.lang.Override
        public ExternalServiceCredentialsAnonymousStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalServiceCredentialsAnonymousStub(channel, callOptions);
        }
      };
    return ExternalServiceCredentialsAnonymousStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExternalServiceCredentialsAnonymousBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsAnonymousBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsAnonymousBlockingStub>() {
        @java.lang.Override
        public ExternalServiceCredentialsAnonymousBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalServiceCredentialsAnonymousBlockingStub(channel, callOptions);
        }
      };
    return ExternalServiceCredentialsAnonymousBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExternalServiceCredentialsAnonymousFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsAnonymousFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsAnonymousFutureStub>() {
        @java.lang.Override
        public ExternalServiceCredentialsAnonymousFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalServiceCredentialsAnonymousFutureStub(channel, callOptions);
        }
      };
    return ExternalServiceCredentialsAnonymousFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Given a list of secure value recovery (SVR) service credentials and a phone number,
     * checks, which of the provided credentials were generated by the user with the given phone number
     * and have not yet expired.
     * `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
     * `INVALID_ARGUMENT` status is returned if request contains more than 10 passwords to be checked.
     * </pre>
     */
    default void checkSvrCredentials(org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckSvrCredentialsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ExternalServiceCredentialsAnonymous.
   */
  public static abstract class ExternalServiceCredentialsAnonymousImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ExternalServiceCredentialsAnonymousGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ExternalServiceCredentialsAnonymous.
   */
  public static final class ExternalServiceCredentialsAnonymousStub
      extends io.grpc.stub.AbstractAsyncStub<ExternalServiceCredentialsAnonymousStub> {
    private ExternalServiceCredentialsAnonymousStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalServiceCredentialsAnonymousStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalServiceCredentialsAnonymousStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Given a list of secure value recovery (SVR) service credentials and a phone number,
     * checks, which of the provided credentials were generated by the user with the given phone number
     * and have not yet expired.
     * `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
     * `INVALID_ARGUMENT` status is returned if request contains more than 10 passwords to be checked.
     * </pre>
     */
    public void checkSvrCredentials(org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckSvrCredentialsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ExternalServiceCredentialsAnonymous.
   */
  public static final class ExternalServiceCredentialsAnonymousBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ExternalServiceCredentialsAnonymousBlockingStub> {
    private ExternalServiceCredentialsAnonymousBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalServiceCredentialsAnonymousBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalServiceCredentialsAnonymousBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Given a list of secure value recovery (SVR) service credentials and a phone number,
     * checks, which of the provided credentials were generated by the user with the given phone number
     * and have not yet expired.
     * `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
     * `INVALID_ARGUMENT` status is returned if request contains more than 10 passwords to be checked.
     * </pre>
     */
    public org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse checkSvrCredentials(org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckSvrCredentialsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ExternalServiceCredentialsAnonymous.
   */
  public static final class ExternalServiceCredentialsAnonymousFutureStub
      extends io.grpc.stub.AbstractFutureStub<ExternalServiceCredentialsAnonymousFutureStub> {
    private ExternalServiceCredentialsAnonymousFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalServiceCredentialsAnonymousFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalServiceCredentialsAnonymousFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Given a list of secure value recovery (SVR) service credentials and a phone number,
     * checks, which of the provided credentials were generated by the user with the given phone number
     * and have not yet expired.
     * `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
     * `INVALID_ARGUMENT` status is returned if request contains more than 10 passwords to be checked.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse> checkSvrCredentials(
        org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckSvrCredentialsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_SVR_CREDENTIALS = 0;

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
        case METHODID_CHECK_SVR_CREDENTIALS:
          serviceImpl.checkSvrCredentials((org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse>) responseObserver);
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
          getCheckSvrCredentialsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.credentials.CheckSvrCredentialsRequest,
              org.ghostprotocol.chat.credentials.CheckSvrCredentialsResponse>(
                service, METHODID_CHECK_SVR_CREDENTIALS)))
        .build();
  }

  private static abstract class ExternalServiceCredentialsAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ExternalServiceCredentialsAnonymousBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.credentials.Credentials.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ExternalServiceCredentialsAnonymous");
    }
  }

  private static final class ExternalServiceCredentialsAnonymousFileDescriptorSupplier
      extends ExternalServiceCredentialsAnonymousBaseDescriptorSupplier {
    ExternalServiceCredentialsAnonymousFileDescriptorSupplier() {}
  }

  private static final class ExternalServiceCredentialsAnonymousMethodDescriptorSupplier
      extends ExternalServiceCredentialsAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ExternalServiceCredentialsAnonymousMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ExternalServiceCredentialsAnonymousGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExternalServiceCredentialsAnonymousFileDescriptorSupplier())
              .addMethod(getCheckSvrCredentialsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
