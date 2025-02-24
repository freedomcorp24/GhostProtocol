package org.ghostprotocol.chat.credentials;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for obtaining and verifying credentials for "external" services
 * (i.e. services that are not a part of the chat server deployment).
 * All methods of this service require authentication.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/credentials.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ExternalServiceCredentialsGrpc {

  private ExternalServiceCredentialsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.credentials.ExternalServiceCredentials";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest,
      org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> getGetExternalServiceCredentialsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetExternalServiceCredentials",
      requestType = org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest.class,
      responseType = org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest,
      org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> getGetExternalServiceCredentialsMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest, org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> getGetExternalServiceCredentialsMethod;
    if ((getGetExternalServiceCredentialsMethod = ExternalServiceCredentialsGrpc.getGetExternalServiceCredentialsMethod) == null) {
      synchronized (ExternalServiceCredentialsGrpc.class) {
        if ((getGetExternalServiceCredentialsMethod = ExternalServiceCredentialsGrpc.getGetExternalServiceCredentialsMethod) == null) {
          ExternalServiceCredentialsGrpc.getGetExternalServiceCredentialsMethod = getGetExternalServiceCredentialsMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest, org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetExternalServiceCredentials"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ExternalServiceCredentialsMethodDescriptorSupplier("GetExternalServiceCredentials"))
              .build();
        }
      }
    }
    return getGetExternalServiceCredentialsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExternalServiceCredentialsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsStub>() {
        @java.lang.Override
        public ExternalServiceCredentialsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalServiceCredentialsStub(channel, callOptions);
        }
      };
    return ExternalServiceCredentialsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExternalServiceCredentialsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsBlockingStub>() {
        @java.lang.Override
        public ExternalServiceCredentialsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalServiceCredentialsBlockingStub(channel, callOptions);
        }
      };
    return ExternalServiceCredentialsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExternalServiceCredentialsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExternalServiceCredentialsFutureStub>() {
        @java.lang.Override
        public ExternalServiceCredentialsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExternalServiceCredentialsFutureStub(channel, callOptions);
        }
      };
    return ExternalServiceCredentialsFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for obtaining and verifying credentials for "external" services
   * (i.e. services that are not a part of the chat server deployment).
   * All methods of this service require authentication.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Generates and returns an external service credentials for the caller.
     * `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
     * `INVALID_ARGUMENT` status is returned if service is not configured for the service type
     * found in the request OR if `externalService` is not specified in the request.
     * `RESOURCE_EXHAUSTED` status is returned if a rate limit for
     * generating credentials has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    default void getExternalServiceCredentials(org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetExternalServiceCredentialsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ExternalServiceCredentials.
   * <pre>
   **
   * Provides methods for obtaining and verifying credentials for "external" services
   * (i.e. services that are not a part of the chat server deployment).
   * All methods of this service require authentication.
   * </pre>
   */
  public static abstract class ExternalServiceCredentialsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ExternalServiceCredentialsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ExternalServiceCredentials.
   * <pre>
   **
   * Provides methods for obtaining and verifying credentials for "external" services
   * (i.e. services that are not a part of the chat server deployment).
   * All methods of this service require authentication.
   * </pre>
   */
  public static final class ExternalServiceCredentialsStub
      extends io.grpc.stub.AbstractAsyncStub<ExternalServiceCredentialsStub> {
    private ExternalServiceCredentialsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalServiceCredentialsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalServiceCredentialsStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Generates and returns an external service credentials for the caller.
     * `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
     * `INVALID_ARGUMENT` status is returned if service is not configured for the service type
     * found in the request OR if `externalService` is not specified in the request.
     * `RESOURCE_EXHAUSTED` status is returned if a rate limit for
     * generating credentials has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public void getExternalServiceCredentials(org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetExternalServiceCredentialsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ExternalServiceCredentials.
   * <pre>
   **
   * Provides methods for obtaining and verifying credentials for "external" services
   * (i.e. services that are not a part of the chat server deployment).
   * All methods of this service require authentication.
   * </pre>
   */
  public static final class ExternalServiceCredentialsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ExternalServiceCredentialsBlockingStub> {
    private ExternalServiceCredentialsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalServiceCredentialsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalServiceCredentialsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Generates and returns an external service credentials for the caller.
     * `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
     * `INVALID_ARGUMENT` status is returned if service is not configured for the service type
     * found in the request OR if `externalService` is not specified in the request.
     * `RESOURCE_EXHAUSTED` status is returned if a rate limit for
     * generating credentials has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse getExternalServiceCredentials(org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetExternalServiceCredentialsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ExternalServiceCredentials.
   * <pre>
   **
   * Provides methods for obtaining and verifying credentials for "external" services
   * (i.e. services that are not a part of the chat server deployment).
   * All methods of this service require authentication.
   * </pre>
   */
  public static final class ExternalServiceCredentialsFutureStub
      extends io.grpc.stub.AbstractFutureStub<ExternalServiceCredentialsFutureStub> {
    private ExternalServiceCredentialsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExternalServiceCredentialsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExternalServiceCredentialsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Generates and returns an external service credentials for the caller.
     * `UNAUTHENTICATED` status is returned if the call is made on unauthenticated channel.
     * `INVALID_ARGUMENT` status is returned if service is not configured for the service type
     * found in the request OR if `externalService` is not specified in the request.
     * `RESOURCE_EXHAUSTED` status is returned if a rate limit for
     * generating credentials has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse> getExternalServiceCredentials(
        org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetExternalServiceCredentialsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_EXTERNAL_SERVICE_CREDENTIALS = 0;

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
        case METHODID_GET_EXTERNAL_SERVICE_CREDENTIALS:
          serviceImpl.getExternalServiceCredentials((org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse>) responseObserver);
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
          getGetExternalServiceCredentialsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsRequest,
              org.ghostprotocol.chat.credentials.GetExternalServiceCredentialsResponse>(
                service, METHODID_GET_EXTERNAL_SERVICE_CREDENTIALS)))
        .build();
  }

  private static abstract class ExternalServiceCredentialsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ExternalServiceCredentialsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.credentials.Credentials.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ExternalServiceCredentials");
    }
  }

  private static final class ExternalServiceCredentialsFileDescriptorSupplier
      extends ExternalServiceCredentialsBaseDescriptorSupplier {
    ExternalServiceCredentialsFileDescriptorSupplier() {}
  }

  private static final class ExternalServiceCredentialsMethodDescriptorSupplier
      extends ExternalServiceCredentialsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ExternalServiceCredentialsMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ExternalServiceCredentialsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExternalServiceCredentialsFileDescriptorSupplier())
              .addMethod(getGetExternalServiceCredentialsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
