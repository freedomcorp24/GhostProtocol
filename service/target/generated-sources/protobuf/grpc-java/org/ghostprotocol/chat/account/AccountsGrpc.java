package org.ghostprotocol.chat.account;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for working with Signal accounts.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/account.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AccountsGrpc {

  private AccountsGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.account.Accounts";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.GetAccountIdentityRequest,
      org.ghostprotocol.chat.account.GetAccountIdentityResponse> getGetAccountIdentityMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountIdentity",
      requestType = org.ghostprotocol.chat.account.GetAccountIdentityRequest.class,
      responseType = org.ghostprotocol.chat.account.GetAccountIdentityResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.GetAccountIdentityRequest,
      org.ghostprotocol.chat.account.GetAccountIdentityResponse> getGetAccountIdentityMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.GetAccountIdentityRequest, org.ghostprotocol.chat.account.GetAccountIdentityResponse> getGetAccountIdentityMethod;
    if ((getGetAccountIdentityMethod = AccountsGrpc.getGetAccountIdentityMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getGetAccountIdentityMethod = AccountsGrpc.getGetAccountIdentityMethod) == null) {
          AccountsGrpc.getGetAccountIdentityMethod = getGetAccountIdentityMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.GetAccountIdentityRequest, org.ghostprotocol.chat.account.GetAccountIdentityResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountIdentity"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.GetAccountIdentityRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.GetAccountIdentityResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("GetAccountIdentity"))
              .build();
        }
      }
    }
    return getGetAccountIdentityMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteAccountRequest,
      org.ghostprotocol.chat.account.DeleteAccountResponse> getDeleteAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAccount",
      requestType = org.ghostprotocol.chat.account.DeleteAccountRequest.class,
      responseType = org.ghostprotocol.chat.account.DeleteAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteAccountRequest,
      org.ghostprotocol.chat.account.DeleteAccountResponse> getDeleteAccountMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteAccountRequest, org.ghostprotocol.chat.account.DeleteAccountResponse> getDeleteAccountMethod;
    if ((getDeleteAccountMethod = AccountsGrpc.getDeleteAccountMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getDeleteAccountMethod = AccountsGrpc.getDeleteAccountMethod) == null) {
          AccountsGrpc.getDeleteAccountMethod = getDeleteAccountMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.DeleteAccountRequest, org.ghostprotocol.chat.account.DeleteAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.DeleteAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.DeleteAccountResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("DeleteAccount"))
              .build();
        }
      }
    }
    return getDeleteAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetRegistrationLockRequest,
      org.ghostprotocol.chat.account.SetRegistrationLockResponse> getSetRegistrationLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetRegistrationLock",
      requestType = org.ghostprotocol.chat.account.SetRegistrationLockRequest.class,
      responseType = org.ghostprotocol.chat.account.SetRegistrationLockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetRegistrationLockRequest,
      org.ghostprotocol.chat.account.SetRegistrationLockResponse> getSetRegistrationLockMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetRegistrationLockRequest, org.ghostprotocol.chat.account.SetRegistrationLockResponse> getSetRegistrationLockMethod;
    if ((getSetRegistrationLockMethod = AccountsGrpc.getSetRegistrationLockMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getSetRegistrationLockMethod = AccountsGrpc.getSetRegistrationLockMethod) == null) {
          AccountsGrpc.getSetRegistrationLockMethod = getSetRegistrationLockMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.SetRegistrationLockRequest, org.ghostprotocol.chat.account.SetRegistrationLockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetRegistrationLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.SetRegistrationLockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.SetRegistrationLockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("SetRegistrationLock"))
              .build();
        }
      }
    }
    return getSetRegistrationLockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ClearRegistrationLockRequest,
      org.ghostprotocol.chat.account.ClearRegistrationLockResponse> getClearRegistrationLockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClearRegistrationLock",
      requestType = org.ghostprotocol.chat.account.ClearRegistrationLockRequest.class,
      responseType = org.ghostprotocol.chat.account.ClearRegistrationLockResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ClearRegistrationLockRequest,
      org.ghostprotocol.chat.account.ClearRegistrationLockResponse> getClearRegistrationLockMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ClearRegistrationLockRequest, org.ghostprotocol.chat.account.ClearRegistrationLockResponse> getClearRegistrationLockMethod;
    if ((getClearRegistrationLockMethod = AccountsGrpc.getClearRegistrationLockMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getClearRegistrationLockMethod = AccountsGrpc.getClearRegistrationLockMethod) == null) {
          AccountsGrpc.getClearRegistrationLockMethod = getClearRegistrationLockMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.ClearRegistrationLockRequest, org.ghostprotocol.chat.account.ClearRegistrationLockResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClearRegistrationLock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.ClearRegistrationLockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.ClearRegistrationLockResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("ClearRegistrationLock"))
              .build();
        }
      }
    }
    return getClearRegistrationLockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ReserveUsernameHashRequest,
      org.ghostprotocol.chat.account.ReserveUsernameHashResponse> getReserveUsernameHashMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReserveUsernameHash",
      requestType = org.ghostprotocol.chat.account.ReserveUsernameHashRequest.class,
      responseType = org.ghostprotocol.chat.account.ReserveUsernameHashResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ReserveUsernameHashRequest,
      org.ghostprotocol.chat.account.ReserveUsernameHashResponse> getReserveUsernameHashMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ReserveUsernameHashRequest, org.ghostprotocol.chat.account.ReserveUsernameHashResponse> getReserveUsernameHashMethod;
    if ((getReserveUsernameHashMethod = AccountsGrpc.getReserveUsernameHashMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getReserveUsernameHashMethod = AccountsGrpc.getReserveUsernameHashMethod) == null) {
          AccountsGrpc.getReserveUsernameHashMethod = getReserveUsernameHashMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.ReserveUsernameHashRequest, org.ghostprotocol.chat.account.ReserveUsernameHashResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReserveUsernameHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.ReserveUsernameHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.ReserveUsernameHashResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("ReserveUsernameHash"))
              .build();
        }
      }
    }
    return getReserveUsernameHashMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ConfirmUsernameHashRequest,
      org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> getConfirmUsernameHashMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmUsernameHash",
      requestType = org.ghostprotocol.chat.account.ConfirmUsernameHashRequest.class,
      responseType = org.ghostprotocol.chat.account.ConfirmUsernameHashResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ConfirmUsernameHashRequest,
      org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> getConfirmUsernameHashMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ConfirmUsernameHashRequest, org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> getConfirmUsernameHashMethod;
    if ((getConfirmUsernameHashMethod = AccountsGrpc.getConfirmUsernameHashMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getConfirmUsernameHashMethod = AccountsGrpc.getConfirmUsernameHashMethod) == null) {
          AccountsGrpc.getConfirmUsernameHashMethod = getConfirmUsernameHashMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.ConfirmUsernameHashRequest, org.ghostprotocol.chat.account.ConfirmUsernameHashResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmUsernameHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.ConfirmUsernameHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.ConfirmUsernameHashResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("ConfirmUsernameHash"))
              .build();
        }
      }
    }
    return getConfirmUsernameHashMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteUsernameHashRequest,
      org.ghostprotocol.chat.account.DeleteUsernameHashResponse> getDeleteUsernameHashMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteUsernameHash",
      requestType = org.ghostprotocol.chat.account.DeleteUsernameHashRequest.class,
      responseType = org.ghostprotocol.chat.account.DeleteUsernameHashResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteUsernameHashRequest,
      org.ghostprotocol.chat.account.DeleteUsernameHashResponse> getDeleteUsernameHashMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteUsernameHashRequest, org.ghostprotocol.chat.account.DeleteUsernameHashResponse> getDeleteUsernameHashMethod;
    if ((getDeleteUsernameHashMethod = AccountsGrpc.getDeleteUsernameHashMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getDeleteUsernameHashMethod = AccountsGrpc.getDeleteUsernameHashMethod) == null) {
          AccountsGrpc.getDeleteUsernameHashMethod = getDeleteUsernameHashMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.DeleteUsernameHashRequest, org.ghostprotocol.chat.account.DeleteUsernameHashResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteUsernameHash"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.DeleteUsernameHashRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.DeleteUsernameHashResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("DeleteUsernameHash"))
              .build();
        }
      }
    }
    return getDeleteUsernameHashMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetUsernameLinkRequest,
      org.ghostprotocol.chat.account.SetUsernameLinkResponse> getSetUsernameLinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUsernameLink",
      requestType = org.ghostprotocol.chat.account.SetUsernameLinkRequest.class,
      responseType = org.ghostprotocol.chat.account.SetUsernameLinkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetUsernameLinkRequest,
      org.ghostprotocol.chat.account.SetUsernameLinkResponse> getSetUsernameLinkMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetUsernameLinkRequest, org.ghostprotocol.chat.account.SetUsernameLinkResponse> getSetUsernameLinkMethod;
    if ((getSetUsernameLinkMethod = AccountsGrpc.getSetUsernameLinkMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getSetUsernameLinkMethod = AccountsGrpc.getSetUsernameLinkMethod) == null) {
          AccountsGrpc.getSetUsernameLinkMethod = getSetUsernameLinkMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.SetUsernameLinkRequest, org.ghostprotocol.chat.account.SetUsernameLinkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetUsernameLink"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.SetUsernameLinkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.SetUsernameLinkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("SetUsernameLink"))
              .build();
        }
      }
    }
    return getSetUsernameLinkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteUsernameLinkRequest,
      org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> getDeleteUsernameLinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteUsernameLink",
      requestType = org.ghostprotocol.chat.account.DeleteUsernameLinkRequest.class,
      responseType = org.ghostprotocol.chat.account.DeleteUsernameLinkResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteUsernameLinkRequest,
      org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> getDeleteUsernameLinkMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.DeleteUsernameLinkRequest, org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> getDeleteUsernameLinkMethod;
    if ((getDeleteUsernameLinkMethod = AccountsGrpc.getDeleteUsernameLinkMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getDeleteUsernameLinkMethod = AccountsGrpc.getDeleteUsernameLinkMethod) == null) {
          AccountsGrpc.getDeleteUsernameLinkMethod = getDeleteUsernameLinkMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.DeleteUsernameLinkRequest, org.ghostprotocol.chat.account.DeleteUsernameLinkResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteUsernameLink"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.DeleteUsernameLinkRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.DeleteUsernameLinkResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("DeleteUsernameLink"))
              .build();
        }
      }
    }
    return getDeleteUsernameLinkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest,
      org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> getConfigureUnidentifiedAccessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfigureUnidentifiedAccess",
      requestType = org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest.class,
      responseType = org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest,
      org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> getConfigureUnidentifiedAccessMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest, org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> getConfigureUnidentifiedAccessMethod;
    if ((getConfigureUnidentifiedAccessMethod = AccountsGrpc.getConfigureUnidentifiedAccessMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getConfigureUnidentifiedAccessMethod = AccountsGrpc.getConfigureUnidentifiedAccessMethod) == null) {
          AccountsGrpc.getConfigureUnidentifiedAccessMethod = getConfigureUnidentifiedAccessMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest, org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfigureUnidentifiedAccess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("ConfigureUnidentifiedAccess"))
              .build();
        }
      }
    }
    return getConfigureUnidentifiedAccessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest,
      org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> getSetDiscoverableByPhoneNumberMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDiscoverableByPhoneNumber",
      requestType = org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest.class,
      responseType = org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest,
      org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> getSetDiscoverableByPhoneNumberMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest, org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> getSetDiscoverableByPhoneNumberMethod;
    if ((getSetDiscoverableByPhoneNumberMethod = AccountsGrpc.getSetDiscoverableByPhoneNumberMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getSetDiscoverableByPhoneNumberMethod = AccountsGrpc.getSetDiscoverableByPhoneNumberMethod) == null) {
          AccountsGrpc.getSetDiscoverableByPhoneNumberMethod = getSetDiscoverableByPhoneNumberMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest, org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDiscoverableByPhoneNumber"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("SetDiscoverableByPhoneNumber"))
              .build();
        }
      }
    }
    return getSetDiscoverableByPhoneNumberMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest,
      org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> getSetRegistrationRecoveryPasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetRegistrationRecoveryPassword",
      requestType = org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest.class,
      responseType = org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest,
      org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> getSetRegistrationRecoveryPasswordMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest, org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> getSetRegistrationRecoveryPasswordMethod;
    if ((getSetRegistrationRecoveryPasswordMethod = AccountsGrpc.getSetRegistrationRecoveryPasswordMethod) == null) {
      synchronized (AccountsGrpc.class) {
        if ((getSetRegistrationRecoveryPasswordMethod = AccountsGrpc.getSetRegistrationRecoveryPasswordMethod) == null) {
          AccountsGrpc.getSetRegistrationRecoveryPasswordMethod = getSetRegistrationRecoveryPasswordMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest, org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetRegistrationRecoveryPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AccountsMethodDescriptorSupplier("SetRegistrationRecoveryPassword"))
              .build();
        }
      }
    }
    return getSetRegistrationRecoveryPasswordMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountsStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsStub>() {
        @java.lang.Override
        public AccountsStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsStub(channel, callOptions);
        }
      };
    return AccountsStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AccountsBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsBlockingStub>() {
        @java.lang.Override
        public AccountsBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsBlockingStub(channel, callOptions);
        }
      };
    return AccountsBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AccountsFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AccountsFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AccountsFutureStub>() {
        @java.lang.Override
        public AccountsFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AccountsFutureStub(channel, callOptions);
        }
      };
    return AccountsFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for working with Signal accounts.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Returns basic identifiers for the authenticated account.
     * </pre>
     */
    default void getAccountIdentity(org.ghostprotocol.chat.account.GetAccountIdentityRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.GetAccountIdentityResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAccountIdentityMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Deletes the authenticated account, purging all associated data in the
     * process.
     * </pre>
     */
    default void deleteAccount(org.ghostprotocol.chat.account.DeleteAccountRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the registration lock secret for the authenticated account. To remove
     * a registration lock, please use `ClearRegistrationLock`.
     * </pre>
     */
    default void setRegistrationLock(org.ghostprotocol.chat.account.SetRegistrationLockRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetRegistrationLockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetRegistrationLockMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Removes any registration lock credentials from the authenticated account.
     * </pre>
     */
    default void clearRegistrationLock(org.ghostprotocol.chat.account.ClearRegistrationLockRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ClearRegistrationLockResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClearRegistrationLockMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Attempts to reserve one of multiple given username hashes. Reserved
     * usernames may be claimed later via `ConfirmUsernameHash`. This RPC may
     * fail with a `RESOURCE_EXHAUSTED` status if a rate limit for modifying
     * usernames has been exceeded, in which case a `retry-after` header
     * containing an ISO 8601 duration string will be present in the response
     * trailers.
     * </pre>
     */
    default void reserveUsernameHash(org.ghostprotocol.chat.account.ReserveUsernameHashRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ReserveUsernameHashResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReserveUsernameHashMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the username hash/encrypted username to a previously-reserved value
     * (see `ReserveUsernameHash`). This RPC may fail with a status of
     * `FAILED_PRECONDITION` if no reserved username hash was found for the given
     * account or `NOT_FOUND` if the reservation has lapsed and been claimed by
     * another caller. It may also  fail with a `RESOURCE_EXHAUSTED` if a rate
     * limit for modifying usernames has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    default void confirmUsernameHash(org.ghostprotocol.chat.account.ConfirmUsernameHashRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfirmUsernameHashMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Clears the current username hash, ciphertext, and link for the
     * authenticated user.
     * </pre>
     */
    default void deleteUsernameHash(org.ghostprotocol.chat.account.DeleteUsernameHashRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteUsernameHashResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUsernameHashMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Associates the given username ciphertext with the account, replacing any
     * previously stored ciphertext. A new link handle will optionally be created,
     * and the link handle to use will be returned in any event.
     * This RPC may fail with a status of `FAILED_PRECONDITION` if the
     * authenticated account does not have a username. It may also fail with
     * `RESOURCE_EXHAUSTED` if a rate limit for modifying username links has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    default void setUsernameLink(org.ghostprotocol.chat.account.SetUsernameLinkRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetUsernameLinkResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetUsernameLinkMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Clears any username link associated with the authenticated account. This
     * RPC may fail with `RESOURCE_EXHAUSTED` if a rate limit for modifying
     * username links has been exceeded, in which case a `retry-after` header
     * containing an ISO 8601 duration string will be present in the response
     * trailers.
     * </pre>
     */
    default void deleteUsernameLink(org.ghostprotocol.chat.account.DeleteUsernameLinkRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUsernameLinkMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Configures "unidentified access" keys and preferences for the authenticated
     * account. Other users permitted to interact with this account anonymously
     * may take actions like fetching pre-keys and profiles for this account or
     * sending sealed-sender messages without providing identifying credentials.
     * </pre>
     */
    default void configureUnidentifiedAccess(org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfigureUnidentifiedAccessMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Sets whether the authenticated account may be discovered by phone number
     * via the Contact Discovery Service (CDS).
     * </pre>
     */
    default void setDiscoverableByPhoneNumber(org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetDiscoverableByPhoneNumberMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the registration recovery password for the authenticated account.
     * </pre>
     */
    default void setRegistrationRecoveryPassword(org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetRegistrationRecoveryPasswordMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Accounts.
   * <pre>
   **
   * Provides methods for working with Signal accounts.
   * </pre>
   */
  public static abstract class AccountsImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AccountsGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Accounts.
   * <pre>
   **
   * Provides methods for working with Signal accounts.
   * </pre>
   */
  public static final class AccountsStub
      extends io.grpc.stub.AbstractAsyncStub<AccountsStub> {
    private AccountsStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns basic identifiers for the authenticated account.
     * </pre>
     */
    public void getAccountIdentity(org.ghostprotocol.chat.account.GetAccountIdentityRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.GetAccountIdentityResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAccountIdentityMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Deletes the authenticated account, purging all associated data in the
     * process.
     * </pre>
     */
    public void deleteAccount(org.ghostprotocol.chat.account.DeleteAccountRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the registration lock secret for the authenticated account. To remove
     * a registration lock, please use `ClearRegistrationLock`.
     * </pre>
     */
    public void setRegistrationLock(org.ghostprotocol.chat.account.SetRegistrationLockRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetRegistrationLockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetRegistrationLockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Removes any registration lock credentials from the authenticated account.
     * </pre>
     */
    public void clearRegistrationLock(org.ghostprotocol.chat.account.ClearRegistrationLockRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ClearRegistrationLockResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClearRegistrationLockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Attempts to reserve one of multiple given username hashes. Reserved
     * usernames may be claimed later via `ConfirmUsernameHash`. This RPC may
     * fail with a `RESOURCE_EXHAUSTED` status if a rate limit for modifying
     * usernames has been exceeded, in which case a `retry-after` header
     * containing an ISO 8601 duration string will be present in the response
     * trailers.
     * </pre>
     */
    public void reserveUsernameHash(org.ghostprotocol.chat.account.ReserveUsernameHashRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ReserveUsernameHashResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReserveUsernameHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the username hash/encrypted username to a previously-reserved value
     * (see `ReserveUsernameHash`). This RPC may fail with a status of
     * `FAILED_PRECONDITION` if no reserved username hash was found for the given
     * account or `NOT_FOUND` if the reservation has lapsed and been claimed by
     * another caller. It may also  fail with a `RESOURCE_EXHAUSTED` if a rate
     * limit for modifying usernames has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public void confirmUsernameHash(org.ghostprotocol.chat.account.ConfirmUsernameHashRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfirmUsernameHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Clears the current username hash, ciphertext, and link for the
     * authenticated user.
     * </pre>
     */
    public void deleteUsernameHash(org.ghostprotocol.chat.account.DeleteUsernameHashRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteUsernameHashResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUsernameHashMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Associates the given username ciphertext with the account, replacing any
     * previously stored ciphertext. A new link handle will optionally be created,
     * and the link handle to use will be returned in any event.
     * This RPC may fail with a status of `FAILED_PRECONDITION` if the
     * authenticated account does not have a username. It may also fail with
     * `RESOURCE_EXHAUSTED` if a rate limit for modifying username links has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public void setUsernameLink(org.ghostprotocol.chat.account.SetUsernameLinkRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetUsernameLinkResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetUsernameLinkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Clears any username link associated with the authenticated account. This
     * RPC may fail with `RESOURCE_EXHAUSTED` if a rate limit for modifying
     * username links has been exceeded, in which case a `retry-after` header
     * containing an ISO 8601 duration string will be present in the response
     * trailers.
     * </pre>
     */
    public void deleteUsernameLink(org.ghostprotocol.chat.account.DeleteUsernameLinkRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUsernameLinkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Configures "unidentified access" keys and preferences for the authenticated
     * account. Other users permitted to interact with this account anonymously
     * may take actions like fetching pre-keys and profiles for this account or
     * sending sealed-sender messages without providing identifying credentials.
     * </pre>
     */
    public void configureUnidentifiedAccess(org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfigureUnidentifiedAccessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Sets whether the authenticated account may be discovered by phone number
     * via the Contact Discovery Service (CDS).
     * </pre>
     */
    public void setDiscoverableByPhoneNumber(org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetDiscoverableByPhoneNumberMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the registration recovery password for the authenticated account.
     * </pre>
     */
    public void setRegistrationRecoveryPassword(org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetRegistrationRecoveryPasswordMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Accounts.
   * <pre>
   **
   * Provides methods for working with Signal accounts.
   * </pre>
   */
  public static final class AccountsBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AccountsBlockingStub> {
    private AccountsBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns basic identifiers for the authenticated account.
     * </pre>
     */
    public org.ghostprotocol.chat.account.GetAccountIdentityResponse getAccountIdentity(org.ghostprotocol.chat.account.GetAccountIdentityRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAccountIdentityMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Deletes the authenticated account, purging all associated data in the
     * process.
     * </pre>
     */
    public org.ghostprotocol.chat.account.DeleteAccountResponse deleteAccount(org.ghostprotocol.chat.account.DeleteAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Sets the registration lock secret for the authenticated account. To remove
     * a registration lock, please use `ClearRegistrationLock`.
     * </pre>
     */
    public org.ghostprotocol.chat.account.SetRegistrationLockResponse setRegistrationLock(org.ghostprotocol.chat.account.SetRegistrationLockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetRegistrationLockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Removes any registration lock credentials from the authenticated account.
     * </pre>
     */
    public org.ghostprotocol.chat.account.ClearRegistrationLockResponse clearRegistrationLock(org.ghostprotocol.chat.account.ClearRegistrationLockRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClearRegistrationLockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Attempts to reserve one of multiple given username hashes. Reserved
     * usernames may be claimed later via `ConfirmUsernameHash`. This RPC may
     * fail with a `RESOURCE_EXHAUSTED` status if a rate limit for modifying
     * usernames has been exceeded, in which case a `retry-after` header
     * containing an ISO 8601 duration string will be present in the response
     * trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.account.ReserveUsernameHashResponse reserveUsernameHash(org.ghostprotocol.chat.account.ReserveUsernameHashRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReserveUsernameHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Sets the username hash/encrypted username to a previously-reserved value
     * (see `ReserveUsernameHash`). This RPC may fail with a status of
     * `FAILED_PRECONDITION` if no reserved username hash was found for the given
     * account or `NOT_FOUND` if the reservation has lapsed and been claimed by
     * another caller. It may also  fail with a `RESOURCE_EXHAUSTED` if a rate
     * limit for modifying usernames has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.account.ConfirmUsernameHashResponse confirmUsernameHash(org.ghostprotocol.chat.account.ConfirmUsernameHashRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmUsernameHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Clears the current username hash, ciphertext, and link for the
     * authenticated user.
     * </pre>
     */
    public org.ghostprotocol.chat.account.DeleteUsernameHashResponse deleteUsernameHash(org.ghostprotocol.chat.account.DeleteUsernameHashRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUsernameHashMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Associates the given username ciphertext with the account, replacing any
     * previously stored ciphertext. A new link handle will optionally be created,
     * and the link handle to use will be returned in any event.
     * This RPC may fail with a status of `FAILED_PRECONDITION` if the
     * authenticated account does not have a username. It may also fail with
     * `RESOURCE_EXHAUSTED` if a rate limit for modifying username links has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.account.SetUsernameLinkResponse setUsernameLink(org.ghostprotocol.chat.account.SetUsernameLinkRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetUsernameLinkMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Clears any username link associated with the authenticated account. This
     * RPC may fail with `RESOURCE_EXHAUSTED` if a rate limit for modifying
     * username links has been exceeded, in which case a `retry-after` header
     * containing an ISO 8601 duration string will be present in the response
     * trailers.
     * </pre>
     */
    public org.ghostprotocol.chat.account.DeleteUsernameLinkResponse deleteUsernameLink(org.ghostprotocol.chat.account.DeleteUsernameLinkRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUsernameLinkMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Configures "unidentified access" keys and preferences for the authenticated
     * account. Other users permitted to interact with this account anonymously
     * may take actions like fetching pre-keys and profiles for this account or
     * sending sealed-sender messages without providing identifying credentials.
     * </pre>
     */
    public org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse configureUnidentifiedAccess(org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfigureUnidentifiedAccessMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Sets whether the authenticated account may be discovered by phone number
     * via the Contact Discovery Service (CDS).
     * </pre>
     */
    public org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse setDiscoverableByPhoneNumber(org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetDiscoverableByPhoneNumberMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Sets the registration recovery password for the authenticated account.
     * </pre>
     */
    public org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse setRegistrationRecoveryPassword(org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetRegistrationRecoveryPasswordMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Accounts.
   * <pre>
   **
   * Provides methods for working with Signal accounts.
   * </pre>
   */
  public static final class AccountsFutureStub
      extends io.grpc.stub.AbstractFutureStub<AccountsFutureStub> {
    private AccountsFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountsFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AccountsFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns basic identifiers for the authenticated account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.GetAccountIdentityResponse> getAccountIdentity(
        org.ghostprotocol.chat.account.GetAccountIdentityRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAccountIdentityMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Deletes the authenticated account, purging all associated data in the
     * process.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.DeleteAccountResponse> deleteAccount(
        org.ghostprotocol.chat.account.DeleteAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Sets the registration lock secret for the authenticated account. To remove
     * a registration lock, please use `ClearRegistrationLock`.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.SetRegistrationLockResponse> setRegistrationLock(
        org.ghostprotocol.chat.account.SetRegistrationLockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetRegistrationLockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Removes any registration lock credentials from the authenticated account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.ClearRegistrationLockResponse> clearRegistrationLock(
        org.ghostprotocol.chat.account.ClearRegistrationLockRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClearRegistrationLockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Attempts to reserve one of multiple given username hashes. Reserved
     * usernames may be claimed later via `ConfirmUsernameHash`. This RPC may
     * fail with a `RESOURCE_EXHAUSTED` status if a rate limit for modifying
     * usernames has been exceeded, in which case a `retry-after` header
     * containing an ISO 8601 duration string will be present in the response
     * trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.ReserveUsernameHashResponse> reserveUsernameHash(
        org.ghostprotocol.chat.account.ReserveUsernameHashRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReserveUsernameHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Sets the username hash/encrypted username to a previously-reserved value
     * (see `ReserveUsernameHash`). This RPC may fail with a status of
     * `FAILED_PRECONDITION` if no reserved username hash was found for the given
     * account or `NOT_FOUND` if the reservation has lapsed and been claimed by
     * another caller. It may also  fail with a `RESOURCE_EXHAUSTED` if a rate
     * limit for modifying usernames has been exceeded, in which case a
     * `retry-after` header containing an ISO 8601 duration string will be present
     * in the response trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse> confirmUsernameHash(
        org.ghostprotocol.chat.account.ConfirmUsernameHashRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfirmUsernameHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Clears the current username hash, ciphertext, and link for the
     * authenticated user.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.DeleteUsernameHashResponse> deleteUsernameHash(
        org.ghostprotocol.chat.account.DeleteUsernameHashRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUsernameHashMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Associates the given username ciphertext with the account, replacing any
     * previously stored ciphertext. A new link handle will optionally be created,
     * and the link handle to use will be returned in any event.
     * This RPC may fail with a status of `FAILED_PRECONDITION` if the
     * authenticated account does not have a username. It may also fail with
     * `RESOURCE_EXHAUSTED` if a rate limit for modifying username links has been
     * exceeded, in which case a `retry-after` header containing an ISO 8601
     * duration string will be present in the response trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.SetUsernameLinkResponse> setUsernameLink(
        org.ghostprotocol.chat.account.SetUsernameLinkRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetUsernameLinkMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Clears any username link associated with the authenticated account. This
     * RPC may fail with `RESOURCE_EXHAUSTED` if a rate limit for modifying
     * username links has been exceeded, in which case a `retry-after` header
     * containing an ISO 8601 duration string will be present in the response
     * trailers.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse> deleteUsernameLink(
        org.ghostprotocol.chat.account.DeleteUsernameLinkRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUsernameLinkMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Configures "unidentified access" keys and preferences for the authenticated
     * account. Other users permitted to interact with this account anonymously
     * may take actions like fetching pre-keys and profiles for this account or
     * sending sealed-sender messages without providing identifying credentials.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse> configureUnidentifiedAccess(
        org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfigureUnidentifiedAccessMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Sets whether the authenticated account may be discovered by phone number
     * via the Contact Discovery Service (CDS).
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse> setDiscoverableByPhoneNumber(
        org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetDiscoverableByPhoneNumberMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Sets the registration recovery password for the authenticated account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse> setRegistrationRecoveryPassword(
        org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetRegistrationRecoveryPasswordMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ACCOUNT_IDENTITY = 0;
  private static final int METHODID_DELETE_ACCOUNT = 1;
  private static final int METHODID_SET_REGISTRATION_LOCK = 2;
  private static final int METHODID_CLEAR_REGISTRATION_LOCK = 3;
  private static final int METHODID_RESERVE_USERNAME_HASH = 4;
  private static final int METHODID_CONFIRM_USERNAME_HASH = 5;
  private static final int METHODID_DELETE_USERNAME_HASH = 6;
  private static final int METHODID_SET_USERNAME_LINK = 7;
  private static final int METHODID_DELETE_USERNAME_LINK = 8;
  private static final int METHODID_CONFIGURE_UNIDENTIFIED_ACCESS = 9;
  private static final int METHODID_SET_DISCOVERABLE_BY_PHONE_NUMBER = 10;
  private static final int METHODID_SET_REGISTRATION_RECOVERY_PASSWORD = 11;

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
        case METHODID_GET_ACCOUNT_IDENTITY:
          serviceImpl.getAccountIdentity((org.ghostprotocol.chat.account.GetAccountIdentityRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.GetAccountIdentityResponse>) responseObserver);
          break;
        case METHODID_DELETE_ACCOUNT:
          serviceImpl.deleteAccount((org.ghostprotocol.chat.account.DeleteAccountRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteAccountResponse>) responseObserver);
          break;
        case METHODID_SET_REGISTRATION_LOCK:
          serviceImpl.setRegistrationLock((org.ghostprotocol.chat.account.SetRegistrationLockRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetRegistrationLockResponse>) responseObserver);
          break;
        case METHODID_CLEAR_REGISTRATION_LOCK:
          serviceImpl.clearRegistrationLock((org.ghostprotocol.chat.account.ClearRegistrationLockRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ClearRegistrationLockResponse>) responseObserver);
          break;
        case METHODID_RESERVE_USERNAME_HASH:
          serviceImpl.reserveUsernameHash((org.ghostprotocol.chat.account.ReserveUsernameHashRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ReserveUsernameHashResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_USERNAME_HASH:
          serviceImpl.confirmUsernameHash((org.ghostprotocol.chat.account.ConfirmUsernameHashRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ConfirmUsernameHashResponse>) responseObserver);
          break;
        case METHODID_DELETE_USERNAME_HASH:
          serviceImpl.deleteUsernameHash((org.ghostprotocol.chat.account.DeleteUsernameHashRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteUsernameHashResponse>) responseObserver);
          break;
        case METHODID_SET_USERNAME_LINK:
          serviceImpl.setUsernameLink((org.ghostprotocol.chat.account.SetUsernameLinkRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetUsernameLinkResponse>) responseObserver);
          break;
        case METHODID_DELETE_USERNAME_LINK:
          serviceImpl.deleteUsernameLink((org.ghostprotocol.chat.account.DeleteUsernameLinkRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.DeleteUsernameLinkResponse>) responseObserver);
          break;
        case METHODID_CONFIGURE_UNIDENTIFIED_ACCESS:
          serviceImpl.configureUnidentifiedAccess((org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse>) responseObserver);
          break;
        case METHODID_SET_DISCOVERABLE_BY_PHONE_NUMBER:
          serviceImpl.setDiscoverableByPhoneNumber((org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse>) responseObserver);
          break;
        case METHODID_SET_REGISTRATION_RECOVERY_PASSWORD:
          serviceImpl.setRegistrationRecoveryPassword((org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse>) responseObserver);
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
          getGetAccountIdentityMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.GetAccountIdentityRequest,
              org.ghostprotocol.chat.account.GetAccountIdentityResponse>(
                service, METHODID_GET_ACCOUNT_IDENTITY)))
        .addMethod(
          getDeleteAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.DeleteAccountRequest,
              org.ghostprotocol.chat.account.DeleteAccountResponse>(
                service, METHODID_DELETE_ACCOUNT)))
        .addMethod(
          getSetRegistrationLockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.SetRegistrationLockRequest,
              org.ghostprotocol.chat.account.SetRegistrationLockResponse>(
                service, METHODID_SET_REGISTRATION_LOCK)))
        .addMethod(
          getClearRegistrationLockMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.ClearRegistrationLockRequest,
              org.ghostprotocol.chat.account.ClearRegistrationLockResponse>(
                service, METHODID_CLEAR_REGISTRATION_LOCK)))
        .addMethod(
          getReserveUsernameHashMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.ReserveUsernameHashRequest,
              org.ghostprotocol.chat.account.ReserveUsernameHashResponse>(
                service, METHODID_RESERVE_USERNAME_HASH)))
        .addMethod(
          getConfirmUsernameHashMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.ConfirmUsernameHashRequest,
              org.ghostprotocol.chat.account.ConfirmUsernameHashResponse>(
                service, METHODID_CONFIRM_USERNAME_HASH)))
        .addMethod(
          getDeleteUsernameHashMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.DeleteUsernameHashRequest,
              org.ghostprotocol.chat.account.DeleteUsernameHashResponse>(
                service, METHODID_DELETE_USERNAME_HASH)))
        .addMethod(
          getSetUsernameLinkMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.SetUsernameLinkRequest,
              org.ghostprotocol.chat.account.SetUsernameLinkResponse>(
                service, METHODID_SET_USERNAME_LINK)))
        .addMethod(
          getDeleteUsernameLinkMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.DeleteUsernameLinkRequest,
              org.ghostprotocol.chat.account.DeleteUsernameLinkResponse>(
                service, METHODID_DELETE_USERNAME_LINK)))
        .addMethod(
          getConfigureUnidentifiedAccessMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessRequest,
              org.ghostprotocol.chat.account.ConfigureUnidentifiedAccessResponse>(
                service, METHODID_CONFIGURE_UNIDENTIFIED_ACCESS)))
        .addMethod(
          getSetDiscoverableByPhoneNumberMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberRequest,
              org.ghostprotocol.chat.account.SetDiscoverableByPhoneNumberResponse>(
                service, METHODID_SET_DISCOVERABLE_BY_PHONE_NUMBER)))
        .addMethod(
          getSetRegistrationRecoveryPasswordMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordRequest,
              org.ghostprotocol.chat.account.SetRegistrationRecoveryPasswordResponse>(
                service, METHODID_SET_REGISTRATION_RECOVERY_PASSWORD)))
        .build();
  }

  private static abstract class AccountsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountsBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.account.Account.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Accounts");
    }
  }

  private static final class AccountsFileDescriptorSupplier
      extends AccountsBaseDescriptorSupplier {
    AccountsFileDescriptorSupplier() {}
  }

  private static final class AccountsMethodDescriptorSupplier
      extends AccountsBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AccountsMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (AccountsGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountsFileDescriptorSupplier())
              .addMethod(getGetAccountIdentityMethod())
              .addMethod(getDeleteAccountMethod())
              .addMethod(getSetRegistrationLockMethod())
              .addMethod(getClearRegistrationLockMethod())
              .addMethod(getReserveUsernameHashMethod())
              .addMethod(getConfirmUsernameHashMethod())
              .addMethod(getDeleteUsernameHashMethod())
              .addMethod(getSetUsernameLinkMethod())
              .addMethod(getDeleteUsernameLinkMethod())
              .addMethod(getConfigureUnidentifiedAccessMethod())
              .addMethod(getSetDiscoverableByPhoneNumberMethod())
              .addMethod(getSetRegistrationRecoveryPasswordMethod())
              .build();
        }
      }
    }
    return result;
  }
}
