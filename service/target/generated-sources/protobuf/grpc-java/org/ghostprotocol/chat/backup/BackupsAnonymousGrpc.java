package org.ghostprotocol.chat.backup;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Service for backup operations with anonymous credentials
 * This service never requires account authentication. It instead requires a
 * backup-id authenticated with an anonymous credential that cannot be linked
 * to the account.
 * To register an anonymous credential:
 *   1. Set a backup-id on the authenticated channel via Backups::SetBackupId
 *   2. Retrieve BackupAuthCredentials via Backups::GetBackupAuthCredentials
 *   3. Generate a key pair and set the public key via
 *      BackupsAnonymous::SetPublicKey
 * Unless otherwise noted, requests for this service require a
 * SignedPresentation, which includes:
 *   - a presentation generated from a BackupAuthCredential issued by
 *     GetBackupAuthCredentials
 *   - a signature of that presentation using the private key of a key pair
 *     previously set with SetPublicKey.
 * All RPCs on this service may return these errors. RPC specific errors
 * documented on the individual RPC.
 * errors:
 * UNAUTHENTICATED     Either the presentation was missing, the credential was
 *                     expired, presentation verification failed, the signature
 *                     was incorrect, there was no committed public key for the
 *                     corresponding backup id, or the request was made on a
 *                     non-anonymous channel.
 * PERMISSION_DENIED   The credential does not have permission to perform the
 *                     requested action.
 * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
 *                     ISO8601 duration string will be present in the response
 *                     trailers.
 * INVALID_ARGUMENT    The request did not meet a documented requirement
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/backups.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BackupsAnonymousGrpc {

  private BackupsAnonymousGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.backup.BackupsAnonymous";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetCdnCredentialsRequest,
      org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> getGetCdnCredentialsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCdnCredentials",
      requestType = org.ghostprotocol.chat.backup.GetCdnCredentialsRequest.class,
      responseType = org.ghostprotocol.chat.backup.GetCdnCredentialsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetCdnCredentialsRequest,
      org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> getGetCdnCredentialsMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetCdnCredentialsRequest, org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> getGetCdnCredentialsMethod;
    if ((getGetCdnCredentialsMethod = BackupsAnonymousGrpc.getGetCdnCredentialsMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getGetCdnCredentialsMethod = BackupsAnonymousGrpc.getGetCdnCredentialsMethod) == null) {
          BackupsAnonymousGrpc.getGetCdnCredentialsMethod = getGetCdnCredentialsMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.GetCdnCredentialsRequest, org.ghostprotocol.chat.backup.GetCdnCredentialsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetCdnCredentials"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.GetCdnCredentialsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.GetCdnCredentialsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("GetCdnCredentials"))
              .build();
        }
      }
    }
    return getGetCdnCredentialsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetBackupInfoRequest,
      org.ghostprotocol.chat.backup.GetBackupInfoResponse> getGetBackupInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBackupInfo",
      requestType = org.ghostprotocol.chat.backup.GetBackupInfoRequest.class,
      responseType = org.ghostprotocol.chat.backup.GetBackupInfoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetBackupInfoRequest,
      org.ghostprotocol.chat.backup.GetBackupInfoResponse> getGetBackupInfoMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetBackupInfoRequest, org.ghostprotocol.chat.backup.GetBackupInfoResponse> getGetBackupInfoMethod;
    if ((getGetBackupInfoMethod = BackupsAnonymousGrpc.getGetBackupInfoMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getGetBackupInfoMethod = BackupsAnonymousGrpc.getGetBackupInfoMethod) == null) {
          BackupsAnonymousGrpc.getGetBackupInfoMethod = getGetBackupInfoMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.GetBackupInfoRequest, org.ghostprotocol.chat.backup.GetBackupInfoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBackupInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.GetBackupInfoRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.GetBackupInfoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("GetBackupInfo"))
              .build();
        }
      }
    }
    return getGetBackupInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.SetPublicKeyRequest,
      org.ghostprotocol.chat.backup.SetPublicKeyResponse> getSetPublicKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetPublicKey",
      requestType = org.ghostprotocol.chat.backup.SetPublicKeyRequest.class,
      responseType = org.ghostprotocol.chat.backup.SetPublicKeyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.SetPublicKeyRequest,
      org.ghostprotocol.chat.backup.SetPublicKeyResponse> getSetPublicKeyMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.SetPublicKeyRequest, org.ghostprotocol.chat.backup.SetPublicKeyResponse> getSetPublicKeyMethod;
    if ((getSetPublicKeyMethod = BackupsAnonymousGrpc.getSetPublicKeyMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getSetPublicKeyMethod = BackupsAnonymousGrpc.getSetPublicKeyMethod) == null) {
          BackupsAnonymousGrpc.getSetPublicKeyMethod = getSetPublicKeyMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.SetPublicKeyRequest, org.ghostprotocol.chat.backup.SetPublicKeyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetPublicKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.SetPublicKeyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.SetPublicKeyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("SetPublicKey"))
              .build();
        }
      }
    }
    return getSetPublicKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.RefreshRequest,
      org.ghostprotocol.chat.backup.RefreshResponse> getRefreshMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Refresh",
      requestType = org.ghostprotocol.chat.backup.RefreshRequest.class,
      responseType = org.ghostprotocol.chat.backup.RefreshResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.RefreshRequest,
      org.ghostprotocol.chat.backup.RefreshResponse> getRefreshMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.RefreshRequest, org.ghostprotocol.chat.backup.RefreshResponse> getRefreshMethod;
    if ((getRefreshMethod = BackupsAnonymousGrpc.getRefreshMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getRefreshMethod = BackupsAnonymousGrpc.getRefreshMethod) == null) {
          BackupsAnonymousGrpc.getRefreshMethod = getRefreshMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.RefreshRequest, org.ghostprotocol.chat.backup.RefreshResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Refresh"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.RefreshRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.RefreshResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("Refresh"))
              .build();
        }
      }
    }
    return getRefreshMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetUploadFormRequest,
      org.ghostprotocol.chat.backup.GetUploadFormResponse> getGetUploadFormMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUploadForm",
      requestType = org.ghostprotocol.chat.backup.GetUploadFormRequest.class,
      responseType = org.ghostprotocol.chat.backup.GetUploadFormResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetUploadFormRequest,
      org.ghostprotocol.chat.backup.GetUploadFormResponse> getGetUploadFormMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.GetUploadFormRequest, org.ghostprotocol.chat.backup.GetUploadFormResponse> getGetUploadFormMethod;
    if ((getGetUploadFormMethod = BackupsAnonymousGrpc.getGetUploadFormMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getGetUploadFormMethod = BackupsAnonymousGrpc.getGetUploadFormMethod) == null) {
          BackupsAnonymousGrpc.getGetUploadFormMethod = getGetUploadFormMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.GetUploadFormRequest, org.ghostprotocol.chat.backup.GetUploadFormResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUploadForm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.GetUploadFormRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.GetUploadFormResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("GetUploadForm"))
              .build();
        }
      }
    }
    return getGetUploadFormMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.CopyMediaRequest,
      org.ghostprotocol.chat.backup.CopyMediaResponse> getCopyMediaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CopyMedia",
      requestType = org.ghostprotocol.chat.backup.CopyMediaRequest.class,
      responseType = org.ghostprotocol.chat.backup.CopyMediaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.CopyMediaRequest,
      org.ghostprotocol.chat.backup.CopyMediaResponse> getCopyMediaMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.CopyMediaRequest, org.ghostprotocol.chat.backup.CopyMediaResponse> getCopyMediaMethod;
    if ((getCopyMediaMethod = BackupsAnonymousGrpc.getCopyMediaMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getCopyMediaMethod = BackupsAnonymousGrpc.getCopyMediaMethod) == null) {
          BackupsAnonymousGrpc.getCopyMediaMethod = getCopyMediaMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.CopyMediaRequest, org.ghostprotocol.chat.backup.CopyMediaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CopyMedia"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.CopyMediaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.CopyMediaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("CopyMedia"))
              .build();
        }
      }
    }
    return getCopyMediaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.ListMediaRequest,
      org.ghostprotocol.chat.backup.ListMediaResponse> getListMediaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListMedia",
      requestType = org.ghostprotocol.chat.backup.ListMediaRequest.class,
      responseType = org.ghostprotocol.chat.backup.ListMediaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.ListMediaRequest,
      org.ghostprotocol.chat.backup.ListMediaResponse> getListMediaMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.ListMediaRequest, org.ghostprotocol.chat.backup.ListMediaResponse> getListMediaMethod;
    if ((getListMediaMethod = BackupsAnonymousGrpc.getListMediaMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getListMediaMethod = BackupsAnonymousGrpc.getListMediaMethod) == null) {
          BackupsAnonymousGrpc.getListMediaMethod = getListMediaMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.ListMediaRequest, org.ghostprotocol.chat.backup.ListMediaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListMedia"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.ListMediaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.ListMediaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("ListMedia"))
              .build();
        }
      }
    }
    return getListMediaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.DeleteMediaRequest,
      org.ghostprotocol.chat.backup.DeleteMediaResponse> getDeleteMediaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteMedia",
      requestType = org.ghostprotocol.chat.backup.DeleteMediaRequest.class,
      responseType = org.ghostprotocol.chat.backup.DeleteMediaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.DeleteMediaRequest,
      org.ghostprotocol.chat.backup.DeleteMediaResponse> getDeleteMediaMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.DeleteMediaRequest, org.ghostprotocol.chat.backup.DeleteMediaResponse> getDeleteMediaMethod;
    if ((getDeleteMediaMethod = BackupsAnonymousGrpc.getDeleteMediaMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getDeleteMediaMethod = BackupsAnonymousGrpc.getDeleteMediaMethod) == null) {
          BackupsAnonymousGrpc.getDeleteMediaMethod = getDeleteMediaMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.DeleteMediaRequest, org.ghostprotocol.chat.backup.DeleteMediaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteMedia"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.DeleteMediaRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.DeleteMediaResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("DeleteMedia"))
              .build();
        }
      }
    }
    return getDeleteMediaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.DeleteAllRequest,
      org.ghostprotocol.chat.backup.DeleteAllResponse> getDeleteAllMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAll",
      requestType = org.ghostprotocol.chat.backup.DeleteAllRequest.class,
      responseType = org.ghostprotocol.chat.backup.DeleteAllResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.DeleteAllRequest,
      org.ghostprotocol.chat.backup.DeleteAllResponse> getDeleteAllMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.backup.DeleteAllRequest, org.ghostprotocol.chat.backup.DeleteAllResponse> getDeleteAllMethod;
    if ((getDeleteAllMethod = BackupsAnonymousGrpc.getDeleteAllMethod) == null) {
      synchronized (BackupsAnonymousGrpc.class) {
        if ((getDeleteAllMethod = BackupsAnonymousGrpc.getDeleteAllMethod) == null) {
          BackupsAnonymousGrpc.getDeleteAllMethod = getDeleteAllMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.backup.DeleteAllRequest, org.ghostprotocol.chat.backup.DeleteAllResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAll"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.DeleteAllRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.backup.DeleteAllResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BackupsAnonymousMethodDescriptorSupplier("DeleteAll"))
              .build();
        }
      }
    }
    return getDeleteAllMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BackupsAnonymousStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BackupsAnonymousStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BackupsAnonymousStub>() {
        @java.lang.Override
        public BackupsAnonymousStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BackupsAnonymousStub(channel, callOptions);
        }
      };
    return BackupsAnonymousStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BackupsAnonymousBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BackupsAnonymousBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BackupsAnonymousBlockingStub>() {
        @java.lang.Override
        public BackupsAnonymousBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BackupsAnonymousBlockingStub(channel, callOptions);
        }
      };
    return BackupsAnonymousBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BackupsAnonymousFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BackupsAnonymousFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BackupsAnonymousFutureStub>() {
        @java.lang.Override
        public BackupsAnonymousFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BackupsAnonymousFutureStub(channel, callOptions);
        }
      };
    return BackupsAnonymousFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Service for backup operations with anonymous credentials
   * This service never requires account authentication. It instead requires a
   * backup-id authenticated with an anonymous credential that cannot be linked
   * to the account.
   * To register an anonymous credential:
   *   1. Set a backup-id on the authenticated channel via Backups::SetBackupId
   *   2. Retrieve BackupAuthCredentials via Backups::GetBackupAuthCredentials
   *   3. Generate a key pair and set the public key via
   *      BackupsAnonymous::SetPublicKey
   * Unless otherwise noted, requests for this service require a
   * SignedPresentation, which includes:
   *   - a presentation generated from a BackupAuthCredential issued by
   *     GetBackupAuthCredentials
   *   - a signature of that presentation using the private key of a key pair
   *     previously set with SetPublicKey.
   * All RPCs on this service may return these errors. RPC specific errors
   * documented on the individual RPC.
   * errors:
   * UNAUTHENTICATED     Either the presentation was missing, the credential was
   *                     expired, presentation verification failed, the signature
   *                     was incorrect, there was no committed public key for the
   *                     corresponding backup id, or the request was made on a
   *                     non-anonymous channel.
   * PERMISSION_DENIED   The credential does not have permission to perform the
   *                     requested action.
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Retrieve credentials used to read objects stored on the backup cdn
     * </pre>
     */
    default void getCdnCredentials(org.ghostprotocol.chat.backup.GetCdnCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetCdnCredentialsMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieve information about the currently stored backup
     * </pre>
     */
    default void getBackupInfo(org.ghostprotocol.chat.backup.GetBackupInfoRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetBackupInfoResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBackupInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Permanently set the public key of an ED25519 key-pair for the backup-id.
     * All requests (including this one!) must sign their BackupAuthCredential
     * presentations with the private key corresponding to the provided public key.
     * Trying to set a public key when a different one is already set will return
     * an UNAUTHENTICATED error.
     * </pre>
     */
    default void setPublicKey(org.ghostprotocol.chat.backup.SetPublicKeyRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.SetPublicKeyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetPublicKeyMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Refresh the backup, indicating that the backup is still active. Clients
     * must periodically upload new backups or perform a refresh. If a backup has
     * not been active for 30 days, it may deleted
     * </pre>
     */
    default void refresh(org.ghostprotocol.chat.backup.RefreshRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.RefreshResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRefreshMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieve an upload form that can be used to perform a resumable upload
     * </pre>
     */
    default void getUploadForm(org.ghostprotocol.chat.backup.GetUploadFormRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetUploadFormResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUploadFormMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Copy and re-encrypt media from the attachments cdn into the backup cdn.
     * The original, already encrypted, attachments will be encrypted with the
     * provided key material before being copied.
     * The copy operation is not atomic and responses will be returned as copy
     * operations complete with detailed information about the outcome. If an
     * error is encountered, not all requests may be reflected in the responses.
     * On retries, a particular destination media id must not be reused with a
     * different source media id or different encryption parameters.
     * </pre>
     */
    default void copyMedia(org.ghostprotocol.chat.backup.CopyMediaRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.CopyMediaResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCopyMediaMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieve a page of media objects stored for this backup-id. A client may
     * have previously stored media objects that are no longer referenced in their
     * current backup. To reclaim storage space used by these orphaned objects,
     * perform a list operation and remove any unreferenced media objects
     * via DeleteMedia.
     * </pre>
     */
    default void listMedia(org.ghostprotocol.chat.backup.ListMediaRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.ListMediaResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListMediaMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Delete media objects stored with this backup-id. Streams the locations of
     * media items back when the item has successfully been removed.
     * </pre>
     */
    default void deleteMedia(org.ghostprotocol.chat.backup.DeleteMediaRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.DeleteMediaResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMediaMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Delete all backup metadata, objects, and stored public key. To use
     * backups again, a public key must be resupplied.
     * </pre>
     */
    default void deleteAll(org.ghostprotocol.chat.backup.DeleteAllRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.DeleteAllResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteAllMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BackupsAnonymous.
   * <pre>
   **
   * Service for backup operations with anonymous credentials
   * This service never requires account authentication. It instead requires a
   * backup-id authenticated with an anonymous credential that cannot be linked
   * to the account.
   * To register an anonymous credential:
   *   1. Set a backup-id on the authenticated channel via Backups::SetBackupId
   *   2. Retrieve BackupAuthCredentials via Backups::GetBackupAuthCredentials
   *   3. Generate a key pair and set the public key via
   *      BackupsAnonymous::SetPublicKey
   * Unless otherwise noted, requests for this service require a
   * SignedPresentation, which includes:
   *   - a presentation generated from a BackupAuthCredential issued by
   *     GetBackupAuthCredentials
   *   - a signature of that presentation using the private key of a key pair
   *     previously set with SetPublicKey.
   * All RPCs on this service may return these errors. RPC specific errors
   * documented on the individual RPC.
   * errors:
   * UNAUTHENTICATED     Either the presentation was missing, the credential was
   *                     expired, presentation verification failed, the signature
   *                     was incorrect, there was no committed public key for the
   *                     corresponding backup id, or the request was made on a
   *                     non-anonymous channel.
   * PERMISSION_DENIED   The credential does not have permission to perform the
   *                     requested action.
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * </pre>
   */
  public static abstract class BackupsAnonymousImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BackupsAnonymousGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BackupsAnonymous.
   * <pre>
   **
   * Service for backup operations with anonymous credentials
   * This service never requires account authentication. It instead requires a
   * backup-id authenticated with an anonymous credential that cannot be linked
   * to the account.
   * To register an anonymous credential:
   *   1. Set a backup-id on the authenticated channel via Backups::SetBackupId
   *   2. Retrieve BackupAuthCredentials via Backups::GetBackupAuthCredentials
   *   3. Generate a key pair and set the public key via
   *      BackupsAnonymous::SetPublicKey
   * Unless otherwise noted, requests for this service require a
   * SignedPresentation, which includes:
   *   - a presentation generated from a BackupAuthCredential issued by
   *     GetBackupAuthCredentials
   *   - a signature of that presentation using the private key of a key pair
   *     previously set with SetPublicKey.
   * All RPCs on this service may return these errors. RPC specific errors
   * documented on the individual RPC.
   * errors:
   * UNAUTHENTICATED     Either the presentation was missing, the credential was
   *                     expired, presentation verification failed, the signature
   *                     was incorrect, there was no committed public key for the
   *                     corresponding backup id, or the request was made on a
   *                     non-anonymous channel.
   * PERMISSION_DENIED   The credential does not have permission to perform the
   *                     requested action.
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * </pre>
   */
  public static final class BackupsAnonymousStub
      extends io.grpc.stub.AbstractAsyncStub<BackupsAnonymousStub> {
    private BackupsAnonymousStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BackupsAnonymousStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BackupsAnonymousStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieve credentials used to read objects stored on the backup cdn
     * </pre>
     */
    public void getCdnCredentials(org.ghostprotocol.chat.backup.GetCdnCredentialsRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetCdnCredentialsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieve information about the currently stored backup
     * </pre>
     */
    public void getBackupInfo(org.ghostprotocol.chat.backup.GetBackupInfoRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetBackupInfoResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBackupInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Permanently set the public key of an ED25519 key-pair for the backup-id.
     * All requests (including this one!) must sign their BackupAuthCredential
     * presentations with the private key corresponding to the provided public key.
     * Trying to set a public key when a different one is already set will return
     * an UNAUTHENTICATED error.
     * </pre>
     */
    public void setPublicKey(org.ghostprotocol.chat.backup.SetPublicKeyRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.SetPublicKeyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetPublicKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Refresh the backup, indicating that the backup is still active. Clients
     * must periodically upload new backups or perform a refresh. If a backup has
     * not been active for 30 days, it may deleted
     * </pre>
     */
    public void refresh(org.ghostprotocol.chat.backup.RefreshRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.RefreshResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRefreshMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieve an upload form that can be used to perform a resumable upload
     * </pre>
     */
    public void getUploadForm(org.ghostprotocol.chat.backup.GetUploadFormRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetUploadFormResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetUploadFormMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Copy and re-encrypt media from the attachments cdn into the backup cdn.
     * The original, already encrypted, attachments will be encrypted with the
     * provided key material before being copied.
     * The copy operation is not atomic and responses will be returned as copy
     * operations complete with detailed information about the outcome. If an
     * error is encountered, not all requests may be reflected in the responses.
     * On retries, a particular destination media id must not be reused with a
     * different source media id or different encryption parameters.
     * </pre>
     */
    public void copyMedia(org.ghostprotocol.chat.backup.CopyMediaRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.CopyMediaResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getCopyMediaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieve a page of media objects stored for this backup-id. A client may
     * have previously stored media objects that are no longer referenced in their
     * current backup. To reclaim storage space used by these orphaned objects,
     * perform a list operation and remove any unreferenced media objects
     * via DeleteMedia.
     * </pre>
     */
    public void listMedia(org.ghostprotocol.chat.backup.ListMediaRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.ListMediaResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListMediaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Delete media objects stored with this backup-id. Streams the locations of
     * media items back when the item has successfully been removed.
     * </pre>
     */
    public void deleteMedia(org.ghostprotocol.chat.backup.DeleteMediaRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.DeleteMediaResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getDeleteMediaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Delete all backup metadata, objects, and stored public key. To use
     * backups again, a public key must be resupplied.
     * </pre>
     */
    public void deleteAll(org.ghostprotocol.chat.backup.DeleteAllRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.DeleteAllResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteAllMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BackupsAnonymous.
   * <pre>
   **
   * Service for backup operations with anonymous credentials
   * This service never requires account authentication. It instead requires a
   * backup-id authenticated with an anonymous credential that cannot be linked
   * to the account.
   * To register an anonymous credential:
   *   1. Set a backup-id on the authenticated channel via Backups::SetBackupId
   *   2. Retrieve BackupAuthCredentials via Backups::GetBackupAuthCredentials
   *   3. Generate a key pair and set the public key via
   *      BackupsAnonymous::SetPublicKey
   * Unless otherwise noted, requests for this service require a
   * SignedPresentation, which includes:
   *   - a presentation generated from a BackupAuthCredential issued by
   *     GetBackupAuthCredentials
   *   - a signature of that presentation using the private key of a key pair
   *     previously set with SetPublicKey.
   * All RPCs on this service may return these errors. RPC specific errors
   * documented on the individual RPC.
   * errors:
   * UNAUTHENTICATED     Either the presentation was missing, the credential was
   *                     expired, presentation verification failed, the signature
   *                     was incorrect, there was no committed public key for the
   *                     corresponding backup id, or the request was made on a
   *                     non-anonymous channel.
   * PERMISSION_DENIED   The credential does not have permission to perform the
   *                     requested action.
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * </pre>
   */
  public static final class BackupsAnonymousBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BackupsAnonymousBlockingStub> {
    private BackupsAnonymousBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BackupsAnonymousBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BackupsAnonymousBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieve credentials used to read objects stored on the backup cdn
     * </pre>
     */
    public org.ghostprotocol.chat.backup.GetCdnCredentialsResponse getCdnCredentials(org.ghostprotocol.chat.backup.GetCdnCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetCdnCredentialsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieve information about the currently stored backup
     * </pre>
     */
    public org.ghostprotocol.chat.backup.GetBackupInfoResponse getBackupInfo(org.ghostprotocol.chat.backup.GetBackupInfoRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBackupInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Permanently set the public key of an ED25519 key-pair for the backup-id.
     * All requests (including this one!) must sign their BackupAuthCredential
     * presentations with the private key corresponding to the provided public key.
     * Trying to set a public key when a different one is already set will return
     * an UNAUTHENTICATED error.
     * </pre>
     */
    public org.ghostprotocol.chat.backup.SetPublicKeyResponse setPublicKey(org.ghostprotocol.chat.backup.SetPublicKeyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetPublicKeyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Refresh the backup, indicating that the backup is still active. Clients
     * must periodically upload new backups or perform a refresh. If a backup has
     * not been active for 30 days, it may deleted
     * </pre>
     */
    public org.ghostprotocol.chat.backup.RefreshResponse refresh(org.ghostprotocol.chat.backup.RefreshRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRefreshMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieve an upload form that can be used to perform a resumable upload
     * </pre>
     */
    public org.ghostprotocol.chat.backup.GetUploadFormResponse getUploadForm(org.ghostprotocol.chat.backup.GetUploadFormRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetUploadFormMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Copy and re-encrypt media from the attachments cdn into the backup cdn.
     * The original, already encrypted, attachments will be encrypted with the
     * provided key material before being copied.
     * The copy operation is not atomic and responses will be returned as copy
     * operations complete with detailed information about the outcome. If an
     * error is encountered, not all requests may be reflected in the responses.
     * On retries, a particular destination media id must not be reused with a
     * different source media id or different encryption parameters.
     * </pre>
     */
    public java.util.Iterator<org.ghostprotocol.chat.backup.CopyMediaResponse> copyMedia(
        org.ghostprotocol.chat.backup.CopyMediaRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getCopyMediaMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieve a page of media objects stored for this backup-id. A client may
     * have previously stored media objects that are no longer referenced in their
     * current backup. To reclaim storage space used by these orphaned objects,
     * perform a list operation and remove any unreferenced media objects
     * via DeleteMedia.
     * </pre>
     */
    public org.ghostprotocol.chat.backup.ListMediaResponse listMedia(org.ghostprotocol.chat.backup.ListMediaRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListMediaMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Delete media objects stored with this backup-id. Streams the locations of
     * media items back when the item has successfully been removed.
     * </pre>
     */
    public java.util.Iterator<org.ghostprotocol.chat.backup.DeleteMediaResponse> deleteMedia(
        org.ghostprotocol.chat.backup.DeleteMediaRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getDeleteMediaMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Delete all backup metadata, objects, and stored public key. To use
     * backups again, a public key must be resupplied.
     * </pre>
     */
    public org.ghostprotocol.chat.backup.DeleteAllResponse deleteAll(org.ghostprotocol.chat.backup.DeleteAllRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteAllMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BackupsAnonymous.
   * <pre>
   **
   * Service for backup operations with anonymous credentials
   * This service never requires account authentication. It instead requires a
   * backup-id authenticated with an anonymous credential that cannot be linked
   * to the account.
   * To register an anonymous credential:
   *   1. Set a backup-id on the authenticated channel via Backups::SetBackupId
   *   2. Retrieve BackupAuthCredentials via Backups::GetBackupAuthCredentials
   *   3. Generate a key pair and set the public key via
   *      BackupsAnonymous::SetPublicKey
   * Unless otherwise noted, requests for this service require a
   * SignedPresentation, which includes:
   *   - a presentation generated from a BackupAuthCredential issued by
   *     GetBackupAuthCredentials
   *   - a signature of that presentation using the private key of a key pair
   *     previously set with SetPublicKey.
   * All RPCs on this service may return these errors. RPC specific errors
   * documented on the individual RPC.
   * errors:
   * UNAUTHENTICATED     Either the presentation was missing, the credential was
   *                     expired, presentation verification failed, the signature
   *                     was incorrect, there was no committed public key for the
   *                     corresponding backup id, or the request was made on a
   *                     non-anonymous channel.
   * PERMISSION_DENIED   The credential does not have permission to perform the
   *                     requested action.
   * RESOURCE_EXHAUSTED  Rate limit exceeded. A retry-after header containing an
   *                     ISO8601 duration string will be present in the response
   *                     trailers.
   * INVALID_ARGUMENT    The request did not meet a documented requirement
   * </pre>
   */
  public static final class BackupsAnonymousFutureStub
      extends io.grpc.stub.AbstractFutureStub<BackupsAnonymousFutureStub> {
    private BackupsAnonymousFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BackupsAnonymousFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BackupsAnonymousFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Retrieve credentials used to read objects stored on the backup cdn
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse> getCdnCredentials(
        org.ghostprotocol.chat.backup.GetCdnCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetCdnCredentialsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieve information about the currently stored backup
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.GetBackupInfoResponse> getBackupInfo(
        org.ghostprotocol.chat.backup.GetBackupInfoRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBackupInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Permanently set the public key of an ED25519 key-pair for the backup-id.
     * All requests (including this one!) must sign their BackupAuthCredential
     * presentations with the private key corresponding to the provided public key.
     * Trying to set a public key when a different one is already set will return
     * an UNAUTHENTICATED error.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.SetPublicKeyResponse> setPublicKey(
        org.ghostprotocol.chat.backup.SetPublicKeyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetPublicKeyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Refresh the backup, indicating that the backup is still active. Clients
     * must periodically upload new backups or perform a refresh. If a backup has
     * not been active for 30 days, it may deleted
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.RefreshResponse> refresh(
        org.ghostprotocol.chat.backup.RefreshRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRefreshMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieve an upload form that can be used to perform a resumable upload
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.GetUploadFormResponse> getUploadForm(
        org.ghostprotocol.chat.backup.GetUploadFormRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetUploadFormMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieve a page of media objects stored for this backup-id. A client may
     * have previously stored media objects that are no longer referenced in their
     * current backup. To reclaim storage space used by these orphaned objects,
     * perform a list operation and remove any unreferenced media objects
     * via DeleteMedia.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.ListMediaResponse> listMedia(
        org.ghostprotocol.chat.backup.ListMediaRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListMediaMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Delete all backup metadata, objects, and stored public key. To use
     * backups again, a public key must be resupplied.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.backup.DeleteAllResponse> deleteAll(
        org.ghostprotocol.chat.backup.DeleteAllRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteAllMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_CDN_CREDENTIALS = 0;
  private static final int METHODID_GET_BACKUP_INFO = 1;
  private static final int METHODID_SET_PUBLIC_KEY = 2;
  private static final int METHODID_REFRESH = 3;
  private static final int METHODID_GET_UPLOAD_FORM = 4;
  private static final int METHODID_COPY_MEDIA = 5;
  private static final int METHODID_LIST_MEDIA = 6;
  private static final int METHODID_DELETE_MEDIA = 7;
  private static final int METHODID_DELETE_ALL = 8;

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
        case METHODID_GET_CDN_CREDENTIALS:
          serviceImpl.getCdnCredentials((org.ghostprotocol.chat.backup.GetCdnCredentialsRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetCdnCredentialsResponse>) responseObserver);
          break;
        case METHODID_GET_BACKUP_INFO:
          serviceImpl.getBackupInfo((org.ghostprotocol.chat.backup.GetBackupInfoRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetBackupInfoResponse>) responseObserver);
          break;
        case METHODID_SET_PUBLIC_KEY:
          serviceImpl.setPublicKey((org.ghostprotocol.chat.backup.SetPublicKeyRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.SetPublicKeyResponse>) responseObserver);
          break;
        case METHODID_REFRESH:
          serviceImpl.refresh((org.ghostprotocol.chat.backup.RefreshRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.RefreshResponse>) responseObserver);
          break;
        case METHODID_GET_UPLOAD_FORM:
          serviceImpl.getUploadForm((org.ghostprotocol.chat.backup.GetUploadFormRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.GetUploadFormResponse>) responseObserver);
          break;
        case METHODID_COPY_MEDIA:
          serviceImpl.copyMedia((org.ghostprotocol.chat.backup.CopyMediaRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.CopyMediaResponse>) responseObserver);
          break;
        case METHODID_LIST_MEDIA:
          serviceImpl.listMedia((org.ghostprotocol.chat.backup.ListMediaRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.ListMediaResponse>) responseObserver);
          break;
        case METHODID_DELETE_MEDIA:
          serviceImpl.deleteMedia((org.ghostprotocol.chat.backup.DeleteMediaRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.DeleteMediaResponse>) responseObserver);
          break;
        case METHODID_DELETE_ALL:
          serviceImpl.deleteAll((org.ghostprotocol.chat.backup.DeleteAllRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.backup.DeleteAllResponse>) responseObserver);
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
          getGetCdnCredentialsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.GetCdnCredentialsRequest,
              org.ghostprotocol.chat.backup.GetCdnCredentialsResponse>(
                service, METHODID_GET_CDN_CREDENTIALS)))
        .addMethod(
          getGetBackupInfoMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.GetBackupInfoRequest,
              org.ghostprotocol.chat.backup.GetBackupInfoResponse>(
                service, METHODID_GET_BACKUP_INFO)))
        .addMethod(
          getSetPublicKeyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.SetPublicKeyRequest,
              org.ghostprotocol.chat.backup.SetPublicKeyResponse>(
                service, METHODID_SET_PUBLIC_KEY)))
        .addMethod(
          getRefreshMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.RefreshRequest,
              org.ghostprotocol.chat.backup.RefreshResponse>(
                service, METHODID_REFRESH)))
        .addMethod(
          getGetUploadFormMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.GetUploadFormRequest,
              org.ghostprotocol.chat.backup.GetUploadFormResponse>(
                service, METHODID_GET_UPLOAD_FORM)))
        .addMethod(
          getCopyMediaMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.CopyMediaRequest,
              org.ghostprotocol.chat.backup.CopyMediaResponse>(
                service, METHODID_COPY_MEDIA)))
        .addMethod(
          getListMediaMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.ListMediaRequest,
              org.ghostprotocol.chat.backup.ListMediaResponse>(
                service, METHODID_LIST_MEDIA)))
        .addMethod(
          getDeleteMediaMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.DeleteMediaRequest,
              org.ghostprotocol.chat.backup.DeleteMediaResponse>(
                service, METHODID_DELETE_MEDIA)))
        .addMethod(
          getDeleteAllMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.backup.DeleteAllRequest,
              org.ghostprotocol.chat.backup.DeleteAllResponse>(
                service, METHODID_DELETE_ALL)))
        .build();
  }

  private static abstract class BackupsAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BackupsAnonymousBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.backup.BackupsOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BackupsAnonymous");
    }
  }

  private static final class BackupsAnonymousFileDescriptorSupplier
      extends BackupsAnonymousBaseDescriptorSupplier {
    BackupsAnonymousFileDescriptorSupplier() {}
  }

  private static final class BackupsAnonymousMethodDescriptorSupplier
      extends BackupsAnonymousBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BackupsAnonymousMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (BackupsAnonymousGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BackupsAnonymousFileDescriptorSupplier())
              .addMethod(getGetCdnCredentialsMethod())
              .addMethod(getGetBackupInfoMethod())
              .addMethod(getSetPublicKeyMethod())
              .addMethod(getRefreshMethod())
              .addMethod(getGetUploadFormMethod())
              .addMethod(getCopyMediaMethod())
              .addMethod(getListMediaMethod())
              .addMethod(getDeleteMediaMethod())
              .addMethod(getDeleteAllMethod())
              .build();
        }
      }
    }
    return result;
  }
}
