package org.ghostprotocol.chat.backup;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Service for backup operations that require account authentication.
 * Most actual backup operations operate on the backup-id and cannot be linked
 * to the caller's account, but setting up anonymous credentials and changing
 * backup tier requires account authentication.
 * All rpcs on this service may return these errors. rpc specific errors
 * documented on the individual rpc.
 * errors:
 * UNAUTHENTICATED     Authentication failed or the account does not exist
 * INVALID_ARGUMENT    The request did not meet a documented requirement
 * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
 *                     ISO8601 duration string will be present in the response
 *                     trailers.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/backups.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BackupsGrpc {

  private BackupsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.backup.Backups";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.SetBackupIdRequest,
      org.ghostprotocol.chat.backup.SetBackupIdResponse> getSetBackupIdMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetBackupId",
      requestType = org.ghostprotocol.chat.backup.SetBackupIdRequest.class,
      responseType = org.ghostprotocol.chat.backup.SetBackupIdResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.SetBackupIdRequest,
      org.ghostprotocol.chat.backup.SetBackupIdResponse> getSetBackupIdMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.SetBackupIdRequest, org.ghostprotocol.chat.backup.SetBackupIdResponse> getSetBackupIdMethod;
    if ((getSetBackupIdMethod = BackupsGrpc.getSetBackupIdMethod) == null) {
      synchronized (BackupsGrpc.class) {
        if ((getSetBackupIdMethod = BackupsGrpc.getSetBackupIdMethod) == null) {
          BackupsGrpc.getSetBackupIdMethod = getSetBackupIdMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.SetBackupIdRequest, org.ghostprotocol.chat.backup.SetBackupIdResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetBackupId"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.SetBackupIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.SetBackupIdResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsMethodDescriptorSupplier("SetBackupId"))
              .build();
        }
      }
    }
    return getSetBackupIdMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.RedeemReceiptRequest,
      org.ghostprotocol.chat.backup.RedeemReceiptResponse> getRedeemReceiptMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RedeemReceipt",
      requestType = org.ghostprotocol.chat.backup.RedeemReceiptRequest.class,
      responseType = org.ghostprotocol.chat.backup.RedeemReceiptResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.RedeemReceiptRequest,
      org.ghostprotocol.chat.backup.RedeemReceiptResponse> getRedeemReceiptMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.RedeemReceiptRequest, org.ghostprotocol.chat.backup.RedeemReceiptResponse> getRedeemReceiptMethod;
    if ((getRedeemReceiptMethod = BackupsGrpc.getRedeemReceiptMethod) == null) {
      synchronized (BackupsGrpc.class) {
        if ((getRedeemReceiptMethod = BackupsGrpc.getRedeemReceiptMethod) == null) {
          BackupsGrpc.getRedeemReceiptMethod = getRedeemReceiptMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.RedeemReceiptRequest, org.ghostprotocol.chat.backup.RedeemReceiptResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RedeemReceipt"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.RedeemReceiptRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.RedeemReceiptResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsMethodDescriptorSupplier("RedeemReceipt"))
              .build();
        }
      }
    }
    return getRedeemReceiptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest,
      org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> getGetBackupAuthCredentialsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBackupAuthCredentials",
      requestType = org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest.class,
      responseType = org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest,
      org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> getGetBackupAuthCredentialsMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest, org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> getGetBackupAuthCredentialsMethod;
    if ((getGetBackupAuthCredentialsMethod = BackupsGrpc.getGetBackupAuthCredentialsMethod) == null) {
      synchronized (BackupsGrpc.class) {
        if ((getGetBackupAuthCredentialsMethod = BackupsGrpc.getGetBackupAuthCredentialsMethod) == null) {
          BackupsGrpc.getGetBackupAuthCredentialsMethod = getGetBackupAuthCredentialsMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest, org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBackupAuthCredentials"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsMethodDescriptorSupplier("GetBackupAuthCredentials"))
              .build();
        }
      }
    }
    return getGetBackupAuthCredentialsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BackupsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BackupsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BackupsStub>() {
        @java.lang.Override
        public BackupsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BackupsStub(channel, callOptions);
        }
      };
    return BackupsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BackupsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BackupsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BackupsBlockingStub>() {
        @java.lang.Override
        public BackupsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BackupsBlockingStub(channel, callOptions);
        }
      };
    return BackupsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BackupsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BackupsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BackupsFutureStub>() {
        @java.lang.Override
        public BackupsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BackupsFutureStub(channel, callOptions);
        }
      };
    return BackupsFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Service for backup operations that require account authentication.
   * Most actual backup operations operate on the backup-id and cannot be linked
   * to the caller's account, but setting up anonymous credentials and changing
   * backup tier requires account authentication.
   * All rpcs on this service may return these errors. rpc specific errors
   * documented on the individual rpc.
   * errors:
   * UNAUTHENTICATED     Authentication failed or the account does not exist
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Set a (blinded) backup-id for the account.
     * Each account may have a single active backup-id that can be used
     * to store and retrieve backups. Once the backup-id is set,
     * BackupAuthCredentials can be generated using GetBackupAuthCredentials.
     * The blinded backup-id and the key-pair used to blind it must be derived
     * from a recoverable secret.
     * errors:
     * PERMISSION_DENIED: This account is not currently eligible for backups
     * </pre>
     */
    default void setBackupId(org.ghostprotocol.chat.backup.SetBackupIdRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.SetBackupIdResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetBackupIdMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Redeem a receipt acquired from /v1/subscription/{subscriberId}/receipt_credentials
     * to mark the account as eligible for the paid backup tier.
     * After successful redemption, subsequent requests to
     * GetBackupAuthCredentials will return credentials with the level on the
     * provided receipt until the expiration time on the receipt.
     * </pre>
     */
    default void redeemReceipt(org.ghostprotocol.chat.backup.RedeemReceiptRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.RedeemReceiptResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRedeemReceiptMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * After setting a blinded backup-id with PUT /v1/archives/, this fetches
     * credentials that can be used to perform operations against that backup-id.
     * Clients may (and should) request up to 7 days of credentials at a time.
     * The redemption_start and redemption_end seconds must be UTC day aligned, and
     * must not span more than 7 days.
     * Each credential contains a receipt level which indicates the backup level
     * the credential is good for. If the account has paid backup access that
     * expires at some point in the provided redemption window, credentials with
     * redemption times after the expiration may be on a lower backup level.
     * Clients must validate the receipt level on the credential matches a known
     * receipt level before using it.
     * errors:
     * NOT_FOUND: Could not find an existing blinded backup id associated with the
     *            account.
     * </pre>
     */
    default void getBackupAuthCredentials(org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBackupAuthCredentialsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Backups.
   * <pre>
   **
   * Service for backup operations that require account authentication.
   * Most actual backup operations operate on the backup-id and cannot be linked
   * to the caller's account, but setting up anonymous credentials and changing
   * backup tier requires account authentication.
   * All rpcs on this service may return these errors. rpc specific errors
   * documented on the individual rpc.
   * errors:
   * UNAUTHENTICATED     Authentication failed or the account does not exist
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * </pre>
   */
  public static abstract class BackupsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BackupsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Backups.
   * <pre>
   **
   * Service for backup operations that require account authentication.
   * Most actual backup operations operate on the backup-id and cannot be linked
   * to the caller's account, but setting up anonymous credentials and changing
   * backup tier requires account authentication.
   * All rpcs on this service may return these errors. rpc specific errors
   * documented on the individual rpc.
   * errors:
   * UNAUTHENTICATED     Authentication failed or the account does not exist
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * </pre>
   */
  public static final class BackupsStub
      extends io.grpc.stub.AbstractAsyncStub<BackupsStub> {
    private BackupsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BackupsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BackupsStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Set a (blinded) backup-id for the account.
     * Each account may have a single active backup-id that can be used
     * to store and retrieve backups. Once the backup-id is set,
     * BackupAuthCredentials can be generated using GetBackupAuthCredentials.
     * The blinded backup-id and the key-pair used to blind it must be derived
     * from a recoverable secret.
     * errors:
     * PERMISSION_DENIED: This account is not currently eligible for backups
     * </pre>
     */
    public void setBackupId(org.ghostprotocol.chat.backup.SetBackupIdRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.SetBackupIdResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetBackupIdMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Redeem a receipt acquired from /v1/subscription/{subscriberId}/receipt_credentials
     * to mark the account as eligible for the paid backup tier.
     * After successful redemption, subsequent requests to
     * GetBackupAuthCredentials will return credentials with the level on the
     * provided receipt until the expiration time on the receipt.
     * </pre>
     */
    public void redeemReceipt(org.ghostprotocol.chat.backup.RedeemReceiptRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.RedeemReceiptResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRedeemReceiptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * After setting a blinded backup-id with PUT /v1/archives/, this fetches
     * credentials that can be used to perform operations against that backup-id.
     * Clients may (and should) request up to 7 days of credentials at a time.
     * The redemption_start and redemption_end seconds must be UTC day aligned, and
     * must not span more than 7 days.
     * Each credential contains a receipt level which indicates the backup level
     * the credential is good for. If the account has paid backup access that
     * expires at some point in the provided redemption window, credentials with
     * redemption times after the expiration may be on a lower backup level.
     * Clients must validate the receipt level on the credential matches a known
     * receipt level before using it.
     * errors:
     * NOT_FOUND: Could not find an existing blinded backup id associated with the
     *            account.
     * </pre>
     */
    public void getBackupAuthCredentials(org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBackupAuthCredentialsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Backups.
   * <pre>
   **
   * Service for backup operations that require account authentication.
   * Most actual backup operations operate on the backup-id and cannot be linked
   * to the caller's account, but setting up anonymous credentials and changing
   * backup tier requires account authentication.
   * All rpcs on this service may return these errors. rpc specific errors
   * documented on the individual rpc.
   * errors:
   * UNAUTHENTICATED     Authentication failed or the account does not exist
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * </pre>
   */
  public static final class BackupsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BackupsBlockingStub> {
    private BackupsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BackupsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BackupsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Set a (blinded) backup-id for the account.
     * Each account may have a single active backup-id that can be used
     * to store and retrieve backups. Once the backup-id is set,
     * BackupAuthCredentials can be generated using GetBackupAuthCredentials.
     * The blinded backup-id and the key-pair used to blind it must be derived
     * from a recoverable secret.
     * errors:
     * PERMISSION_DENIED: This account is not currently eligible for backups
     * </pre>
     */
    public org.ghostprotocol.chat.backup.SetBackupIdResponse setBackupId(org.ghostprotocol.chat.backup.SetBackupIdRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetBackupIdMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Redeem a receipt acquired from /v1/subscription/{subscriberId}/receipt_credentials
     * to mark the account as eligible for the paid backup tier.
     * After successful redemption, subsequent requests to
     * GetBackupAuthCredentials will return credentials with the level on the
     * provided receipt until the expiration time on the receipt.
     * </pre>
     */
    public org.ghostprotocol.chat.backup.RedeemReceiptResponse redeemReceipt(org.ghostprotocol.chat.backup.RedeemReceiptRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRedeemReceiptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * After setting a blinded backup-id with PUT /v1/archives/, this fetches
     * credentials that can be used to perform operations against that backup-id.
     * Clients may (and should) request up to 7 days of credentials at a time.
     * The redemption_start and redemption_end seconds must be UTC day aligned, and
     * must not span more than 7 days.
     * Each credential contains a receipt level which indicates the backup level
     * the credential is good for. If the account has paid backup access that
     * expires at some point in the provided redemption window, credentials with
     * redemption times after the expiration may be on a lower backup level.
     * Clients must validate the receipt level on the credential matches a known
     * receipt level before using it.
     * errors:
     * NOT_FOUND: Could not find an existing blinded backup id associated with the
     *            account.
     * </pre>
     */
    public org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse getBackupAuthCredentials(org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBackupAuthCredentialsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Backups.
   * <pre>
   **
   * Service for backup operations that require account authentication.
   * Most actual backup operations operate on the backup-id and cannot be linked
   * to the caller's account, but setting up anonymous credentials and changing
   * backup tier requires account authentication.
   * All rpcs on this service may return these errors. rpc specific errors
   * documented on the individual rpc.
   * errors:
   * UNAUTHENTICATED     Authentication failed or the account does not exist
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * </pre>
   */
  public static final class BackupsFutureStub
      extends io.grpc.stub.AbstractFutureStub<BackupsFutureStub> {
    private BackupsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BackupsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BackupsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Set a (blinded) backup-id for the account.
     * Each account may have a single active backup-id that can be used
     * to store and retrieve backups. Once the backup-id is set,
     * BackupAuthCredentials can be generated using GetBackupAuthCredentials.
     * The blinded backup-id and the key-pair used to blind it must be derived
     * from a recoverable secret.
     * errors:
     * PERMISSION_DENIED: This account is not currently eligible for backups
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.SetBackupIdResponse> setBackupId(
        org.ghostprotocol.chat.backup.SetBackupIdRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetBackupIdMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Redeem a receipt acquired from /v1/subscription/{subscriberId}/receipt_credentials
     * to mark the account as eligible for the paid backup tier.
     * After successful redemption, subsequent requests to
     * GetBackupAuthCredentials will return credentials with the level on the
     * provided receipt until the expiration time on the receipt.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.RedeemReceiptResponse> redeemReceipt(
        org.ghostprotocol.chat.backup.RedeemReceiptRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRedeemReceiptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * After setting a blinded backup-id with PUT /v1/archives/, this fetches
     * credentials that can be used to perform operations against that backup-id.
     * Clients may (and should) request up to 7 days of credentials at a time.
     * The redemption_start and redemption_end seconds must be UTC day aligned, and
     * must not span more than 7 days.
     * Each credential contains a receipt level which indicates the backup level
     * the credential is good for. If the account has paid backup access that
     * expires at some point in the provided redemption window, credentials with
     * redemption times after the expiration may be on a lower backup level.
     * Clients must validate the receipt level on the credential matches a known
     * receipt level before using it.
     * errors:
     * NOT_FOUND: Could not find an existing blinded backup id associated with the
     *            account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse> getBackupAuthCredentials(
        org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBackupAuthCredentialsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_BACKUP_ID = 0;
  private static final int METHODID_REDEEM_RECEIPT = 1;
  private static final int METHODID_GET_BACKUP_AUTH_CREDENTIALS = 2;

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
        case METHODID_SET_BACKUP_ID:
          serviceImpl.setBackupId((org.ghostprotocol.chat.backup.SetBackupIdRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.SetBackupIdResponse>) responseObserver);
          break;
        case METHODID_REDEEM_RECEIPT:
          serviceImpl.redeemReceipt((org.ghostprotocol.chat.backup.RedeemReceiptRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.RedeemReceiptResponse>) responseObserver);
          break;
        case METHODID_GET_BACKUP_AUTH_CREDENTIALS:
          serviceImpl.getBackupAuthCredentials((org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse>) responseObserver);
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
          getSetBackupIdMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.SetBackupIdRequest,
              org.ghostprotocol.chat.backup.SetBackupIdResponse>(
                service, METHODID_SET_BACKUP_ID)))
        .addMethod(
          getRedeemReceiptMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.RedeemReceiptRequest,
              org.ghostprotocol.chat.backup.RedeemReceiptResponse>(
                service, METHODID_REDEEM_RECEIPT)))
        .addMethod(
          getGetBackupAuthCredentialsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.GetBackupAuthCredentialsRequest,
              org.ghostprotocol.chat.backup.GetBackupAuthCredentialsResponse>(
                service, METHODID_GET_BACKUP_AUTH_CREDENTIALS)))
        .build();
  }

  private static abstract class BackupsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BackupsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.backup.BackupsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Backups");
    }
  }

  private static final class BackupsFileDescriptorSupplier
      extends BackupsBaseDescriptorSupplier {
    BackupsFileDescriptorSupplier() {}
  }

  private static final class BackupsMethodDescriptorSupplier
      extends BackupsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BackupsMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (BackupsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BackupsFileDescriptorSupplier())
              .addMethod(getSetBackupIdMethod())
              .addMethod(getRedeemReceiptMethod())
              .addMethod(getGetBackupAuthCredentialsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
