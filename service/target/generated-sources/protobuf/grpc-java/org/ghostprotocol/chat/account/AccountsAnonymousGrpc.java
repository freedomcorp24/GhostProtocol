package org.ghostprotocol.chat.account;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for looking up Signal accounts. Callers must not provide
 * identifying credentials when calling methods in this service.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/account.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountsAnonymousGrpc {

  private AccountsAnonymousGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.account.AccountsAnonymous";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.CheckAccountExistenceRequest,
      org.ghostprotocol.chat.account.CheckAccountExistenceResponse> getCheckAccountExistenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckAccountExistence",
      requestType = org.ghostprotocol.chat.account.CheckAccountExistenceRequest.class,
      responseType = org.ghostprotocol.chat.account.CheckAccountExistenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.CheckAccountExistenceRequest,
      org.ghostprotocol.chat.account.CheckAccountExistenceResponse> getCheckAccountExistenceMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.CheckAccountExistenceRequest, org.ghostprotocol.chat.account.CheckAccountExistenceResponse> getCheckAccountExistenceMethod;
    if ((getCheckAccountExistenceMethod = AccountsAnonymousGrpc.getCheckAccountExistenceMethod) == null) {
      synchronized (AccountsAnonymousGrpc.class) {
        if ((getCheckAccountExistenceMethod = AccountsAnonymousGrpc.getCheckAccountExistenceMethod) == null) {
          AccountsAnonymousGrpc.getCheckAccountExistenceMethod = getCheckAccountExistenceMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.CheckAccountExistenceRequest, org.ghostprotocol.chat.account.CheckAccountExistenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckAccountExistence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.CheckAccountExistenceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.CheckAccountExistenceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsAnonymousMethodDescriptorSupplier("CheckAccountExistence"))
              .build();
        }
      }
    }
    return getCheckAccountExistenceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.LookupUsernameHashRequest,
      org.ghostprotocol.chat.account.LookupUsernameHashResponse> getLookupUsernameHashMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LookupUsernameHash",
      requestType = org.ghostprotocol.chat.account.LookupUsernameHashRequest.class,
      responseType = org.ghostprotocol.chat.account.LookupUsernameHashResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.LookupUsernameHashRequest,
      org.ghostprotocol.chat.account.LookupUsernameHashResponse> getLookupUsernameHashMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.LookupUsernameHashRequest, org.ghostprotocol.chat.account.LookupUsernameHashResponse> getLookupUsernameHashMethod;
    if ((getLookupUsernameHashMethod = AccountsAnonymousGrpc.getLookupUsernameHashMethod) == null) {
      synchronized (AccountsAnonymousGrpc.class) {
        if ((getLookupUsernameHashMethod = AccountsAnonymousGrpc.getLookupUsernameHashMethod) == null) {
          AccountsAnonymousGrpc.getLookupUsernameHashMethod = getLookupUsernameHashMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.LookupUsernameHashRequest, org.ghostprotocol.chat.account.LookupUsernameHashResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LookupUsernameHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.LookupUsernameHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.LookupUsernameHashResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsAnonymousMethodDescriptorSupplier("LookupUsernameHash"))
              .build();
        }
      }
    }
    return getLookupUsernameHashMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.LookupUsernameLinkRequest,
      org.ghostprotocol.chat.account.LookupUsernameLinkResponse> getLookupUsernameLinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LookupUsernameLink",
      requestType = org.ghostprotocol.chat.account.LookupUsernameLinkRequest.class,
      responseType = org.ghostprotocol.chat.account.LookupUsernameLinkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.LookupUsernameLinkRequest,
      org.ghostprotocol.chat.account.LookupUsernameLinkResponse> getLookupUsernameLinkMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.LookupUsernameLinkRequest, org.ghostprotocol.chat.account.LookupUsernameLinkResponse> getLookupUsernameLinkMethod;
    if ((getLookupUsernameLinkMethod = AccountsAnonymousGrpc.getLookupUsernameLinkMethod) == null) {
      synchronized (AccountsAnonymousGrpc.class) {
        if ((getLookupUsernameLinkMethod = AccountsAnonymousGrpc.getLookupUsernameLinkMethod) == null) {
          AccountsAnonymousGrpc.getLookupUsernameLinkMethod = getLookupUsernameLinkMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.LookupUsernameLinkRequest, org.ghostprotocol.chat.account.LookupUsernameLinkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LookupUsernameLink"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.LookupUsernameLinkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.LookupUsernameLinkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsAnonymousMethodDescriptorSupplier("LookupUsernameLink"))
              .build();
        }
      }
    }
    return getLookupUsernameLinkMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountsAnonymousStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsAnonymousStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsAnonymousStub>() {
        @java.lang.Override
        public AccountsAnonymousStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsAnonymousStub(channel, callOptions);
        }
      };
    return AccountsAnonymousStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AccountsAnonymousBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsAnonymousBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsAnonymousBlockingStub>() {
        @java.lang.Override
        public AccountsAnonymousBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsAnonymousBlockingStub(channel, callOptions);
        }
      };
    return AccountsAnonymousBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AccountsAnonymousFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsAnonymousFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsAnonymousFutureStub>() {
        @java.lang.Override
        public AccountsAnonymousFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsAnonymousFutureStub(channel, callOptions);
        }
      };
    return AccountsAnonymousFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for looking up Signal accounts. Callers must not provide
   * identifying credentials when calling methods in this service.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Checks whether an account with the given service identifier exists.
     * </pre>
     */
    default void checkAccountExistence(org.ghostprotocol.chat.account.CheckAccountExistenceRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.CheckAccountExistenceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckAccountExistenceMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Finds the service identifier of the account associated with the given
     * username hash. This method will return a `NOT_FOUND` status if no account
     * was found for the given username hash.
     * </pre>
     */
    default void lookupUsernameHash(org.ghostprotocol.chat.account.LookupUsernameHashRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.LookupUsernameHashResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLookupUsernameHashMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Finds the encrypted username identified by a given username link handle.
     * This method will return a `NOT_FOUND` status if no username was found for
     * the given link handle.
     * </pre>
     */
    default void lookupUsernameLink(org.ghostprotocol.chat.account.LookupUsernameLinkRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.LookupUsernameLinkResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLookupUsernameLinkMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AccountsAnonymous.
   * <pre>
   **
   * Provides methods for looking up Signal accounts. Callers must not provide
   * identifying credentials when calling methods in this service.
   * </pre>
   */
  public static abstract class AccountsAnonymousImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AccountsAnonymousGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AccountsAnonymous.
   * <pre>
   **
   * Provides methods for looking up Signal accounts. Callers must not provide
   * identifying credentials when calling methods in this service.
   * </pre>
   */
  public static final class AccountsAnonymousStub
      extends io.grpc.stub.AbstractAsyncStub<AccountsAnonymousStub> {
    private AccountsAnonymousStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsAnonymousStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsAnonymousStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Checks whether an account with the given service identifier exists.
     * </pre>
     */
    public void checkAccountExistence(org.ghostprotocol.chat.account.CheckAccountExistenceRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.CheckAccountExistenceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckAccountExistenceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Finds the service identifier of the account associated with the given
     * username hash. This method will return a `NOT_FOUND` status if no account
     * was found for the given username hash.
     * </pre>
     */
    public void lookupUsernameHash(org.ghostprotocol.chat.account.LookupUsernameHashRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.LookupUsernameHashResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLookupUsernameHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Finds the encrypted username identified by a given username link handle.
     * This method will return a `NOT_FOUND` status if no username was found for
     * the given link handle.
     * </pre>
     */
    public void lookupUsernameLink(org.ghostprotocol.chat.account.LookupUsernameLinkRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.LookupUsernameLinkResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getLookupUsernameLinkMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AccountsAnonymous.
   * <pre>
   **
   * Provides methods for looking up Signal accounts. Callers must not provide
   * identifying credentials when calling methods in this service.
   * </pre>
   */
  public static final class AccountsAnonymousBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountsAnonymousBlockingStub> {
    private AccountsAnonymousBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsAnonymousBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsAnonymousBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Checks whether an account with the given service identifier exists.
     * </pre>
     */
    public org.ghostprotocol.chat.account.CheckAccountExistenceResponse checkAccountExistence(org.ghostprotocol.chat.account.CheckAccountExistenceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckAccountExistenceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Finds the service identifier of the account associated with the given
     * username hash. This method will return a `NOT_FOUND` status if no account
     * was found for the given username hash.
     * </pre>
     */
    public org.ghostprotocol.chat.account.LookupUsernameHashResponse lookupUsernameHash(org.ghostprotocol.chat.account.LookupUsernameHashRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLookupUsernameHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Finds the encrypted username identified by a given username link handle.
     * This method will return a `NOT_FOUND` status if no username was found for
     * the given link handle.
     * </pre>
     */
    public org.ghostprotocol.chat.account.LookupUsernameLinkResponse lookupUsernameLink(org.ghostprotocol.chat.account.LookupUsernameLinkRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getLookupUsernameLinkMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AccountsAnonymous.
   * <pre>
   **
   * Provides methods for looking up Signal accounts. Callers must not provide
   * identifying credentials when calling methods in this service.
   * </pre>
   */
  public static final class AccountsAnonymousFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountsAnonymousFutureStub> {
    private AccountsAnonymousFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsAnonymousFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsAnonymousFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Checks whether an account with the given service identifier exists.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.CheckAccountExistenceResponse> checkAccountExistence(
        org.ghostprotocol.chat.account.CheckAccountExistenceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckAccountExistenceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Finds the service identifier of the account associated with the given
     * username hash. This method will return a `NOT_FOUND` status if no account
     * was found for the given username hash.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.LookupUsernameHashResponse> lookupUsernameHash(
        org.ghostprotocol.chat.account.LookupUsernameHashRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLookupUsernameHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Finds the encrypted username identified by a given username link handle.
     * This method will return a `NOT_FOUND` status if no username was found for
     * the given link handle.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.LookupUsernameLinkResponse> lookupUsernameLink(
        org.ghostprotocol.chat.account.LookupUsernameLinkRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getLookupUsernameLinkMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_ACCOUNT_EXISTENCE = 0;
  private static final int METHODID_LOOKUP_USERNAME_HASH = 1;
  private static final int METHODID_LOOKUP_USERNAME_LINK = 2;

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
        case METHODID_CHECK_ACCOUNT_EXISTENCE:
          serviceImpl.checkAccountExistence((org.ghostprotocol.chat.account.CheckAccountExistenceRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.CheckAccountExistenceResponse>) responseObserver);
          break;
        case METHODID_LOOKUP_USERNAME_HASH:
          serviceImpl.lookupUsernameHash((org.ghostprotocol.chat.account.LookupUsernameHashRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.LookupUsernameHashResponse>) responseObserver);
          break;
        case METHODID_LOOKUP_USERNAME_LINK:
          serviceImpl.lookupUsernameLink((org.ghostprotocol.chat.account.LookupUsernameLinkRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.LookupUsernameLinkResponse>) responseObserver);
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
          getCheckAccountExistenceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.CheckAccountExistenceRequest,
              org.ghostprotocol.chat.account.CheckAccountExistenceResponse>(
                service, METHODID_CHECK_ACCOUNT_EXISTENCE)))
        .addMethod(
          getLookupUsernameHashMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.LookupUsernameHashRequest,
              org.ghostprotocol.chat.account.LookupUsernameHashResponse>(
                service, METHODID_LOOKUP_USERNAME_HASH)))
        .addMethod(
          getLookupUsernameLinkMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.LookupUsernameLinkRequest,
              org.ghostprotocol.chat.account.LookupUsernameLinkResponse>(
                service, METHODID_LOOKUP_USERNAME_LINK)))
        .build();
  }

  private static abstract class AccountsAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountsAnonymousBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.account.Account.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountsAnonymous");
    }
  }

  private static final class AccountsAnonymousFileDescriptorSupplier
      extends AccountsAnonymousBaseDescriptorSupplier {
    AccountsAnonymousFileDescriptorSupplier() {}
  }

  private static final class AccountsAnonymousMethodDescriptorSupplier
      extends AccountsAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AccountsAnonymousMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AccountsAnonymousGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountsAnonymousFileDescriptorSupplier())
              .addMethod(getCheckAccountExistenceMethod())
              .addMethod(getLookupUsernameHashMethod())
              .addMethod(getLookupUsernameLinkMethod())
              .build();
        }
      }
    }
    return result;
  }
}
