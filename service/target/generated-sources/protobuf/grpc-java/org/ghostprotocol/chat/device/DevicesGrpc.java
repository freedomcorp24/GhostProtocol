package org.ghostprotocol.chat.device;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 **
 * Provides methods for working with devices attached to a Signal account.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.69.0)",
    comments = "Source: org/ghostprotocol/chat/device.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DevicesGrpc {

  private DevicesGrpc() {}

  public static final java.lang.String SERVICE_NAME = "org.ghostprotocol.chat.device.Devices";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.GetDevicesRequest,
      org.ghostprotocol.chat.device.GetDevicesResponse> getGetDevicesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDevices",
      requestType = org.ghostprotocol.chat.device.GetDevicesRequest.class,
      responseType = org.ghostprotocol.chat.device.GetDevicesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.GetDevicesRequest,
      org.ghostprotocol.chat.device.GetDevicesResponse> getGetDevicesMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.GetDevicesRequest, org.ghostprotocol.chat.device.GetDevicesResponse> getGetDevicesMethod;
    if ((getGetDevicesMethod = DevicesGrpc.getGetDevicesMethod) == null) {
      synchronized (DevicesGrpc.class) {
        if ((getGetDevicesMethod = DevicesGrpc.getGetDevicesMethod) == null) {
          DevicesGrpc.getGetDevicesMethod = getGetDevicesMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.device.GetDevicesRequest, org.ghostprotocol.chat.device.GetDevicesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetDevices"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.GetDevicesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.GetDevicesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DevicesMethodDescriptorSupplier("GetDevices"))
              .build();
        }
      }
    }
    return getGetDevicesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.RemoveDeviceRequest,
      org.ghostprotocol.chat.device.RemoveDeviceResponse> getRemoveDeviceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveDevice",
      requestType = org.ghostprotocol.chat.device.RemoveDeviceRequest.class,
      responseType = org.ghostprotocol.chat.device.RemoveDeviceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.RemoveDeviceRequest,
      org.ghostprotocol.chat.device.RemoveDeviceResponse> getRemoveDeviceMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.RemoveDeviceRequest, org.ghostprotocol.chat.device.RemoveDeviceResponse> getRemoveDeviceMethod;
    if ((getRemoveDeviceMethod = DevicesGrpc.getRemoveDeviceMethod) == null) {
      synchronized (DevicesGrpc.class) {
        if ((getRemoveDeviceMethod = DevicesGrpc.getRemoveDeviceMethod) == null) {
          DevicesGrpc.getRemoveDeviceMethod = getRemoveDeviceMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.device.RemoveDeviceRequest, org.ghostprotocol.chat.device.RemoveDeviceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveDevice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.RemoveDeviceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.RemoveDeviceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DevicesMethodDescriptorSupplier("RemoveDevice"))
              .build();
        }
      }
    }
    return getRemoveDeviceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetDeviceNameRequest,
      org.ghostprotocol.chat.device.SetDeviceNameResponse> getSetDeviceNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDeviceName",
      requestType = org.ghostprotocol.chat.device.SetDeviceNameRequest.class,
      responseType = org.ghostprotocol.chat.device.SetDeviceNameResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetDeviceNameRequest,
      org.ghostprotocol.chat.device.SetDeviceNameResponse> getSetDeviceNameMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetDeviceNameRequest, org.ghostprotocol.chat.device.SetDeviceNameResponse> getSetDeviceNameMethod;
    if ((getSetDeviceNameMethod = DevicesGrpc.getSetDeviceNameMethod) == null) {
      synchronized (DevicesGrpc.class) {
        if ((getSetDeviceNameMethod = DevicesGrpc.getSetDeviceNameMethod) == null) {
          DevicesGrpc.getSetDeviceNameMethod = getSetDeviceNameMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.device.SetDeviceNameRequest, org.ghostprotocol.chat.device.SetDeviceNameResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDeviceName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.SetDeviceNameRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.SetDeviceNameResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DevicesMethodDescriptorSupplier("SetDeviceName"))
              .build();
        }
      }
    }
    return getSetDeviceNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetPushTokenRequest,
      org.ghostprotocol.chat.device.SetPushTokenResponse> getSetPushTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetPushToken",
      requestType = org.ghostprotocol.chat.device.SetPushTokenRequest.class,
      responseType = org.ghostprotocol.chat.device.SetPushTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetPushTokenRequest,
      org.ghostprotocol.chat.device.SetPushTokenResponse> getSetPushTokenMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetPushTokenRequest, org.ghostprotocol.chat.device.SetPushTokenResponse> getSetPushTokenMethod;
    if ((getSetPushTokenMethod = DevicesGrpc.getSetPushTokenMethod) == null) {
      synchronized (DevicesGrpc.class) {
        if ((getSetPushTokenMethod = DevicesGrpc.getSetPushTokenMethod) == null) {
          DevicesGrpc.getSetPushTokenMethod = getSetPushTokenMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.device.SetPushTokenRequest, org.ghostprotocol.chat.device.SetPushTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetPushToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.SetPushTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.SetPushTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DevicesMethodDescriptorSupplier("SetPushToken"))
              .build();
        }
      }
    }
    return getSetPushTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.ClearPushTokenRequest,
      org.ghostprotocol.chat.device.ClearPushTokenResponse> getClearPushTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ClearPushToken",
      requestType = org.ghostprotocol.chat.device.ClearPushTokenRequest.class,
      responseType = org.ghostprotocol.chat.device.ClearPushTokenResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.ClearPushTokenRequest,
      org.ghostprotocol.chat.device.ClearPushTokenResponse> getClearPushTokenMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.ClearPushTokenRequest, org.ghostprotocol.chat.device.ClearPushTokenResponse> getClearPushTokenMethod;
    if ((getClearPushTokenMethod = DevicesGrpc.getClearPushTokenMethod) == null) {
      synchronized (DevicesGrpc.class) {
        if ((getClearPushTokenMethod = DevicesGrpc.getClearPushTokenMethod) == null) {
          DevicesGrpc.getClearPushTokenMethod = getClearPushTokenMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.device.ClearPushTokenRequest, org.ghostprotocol.chat.device.ClearPushTokenResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ClearPushToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.ClearPushTokenRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.ClearPushTokenResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DevicesMethodDescriptorSupplier("ClearPushToken"))
              .build();
        }
      }
    }
    return getClearPushTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetCapabilitiesRequest,
      org.ghostprotocol.chat.device.SetCapabilitiesResponse> getSetCapabilitiesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetCapabilities",
      requestType = org.ghostprotocol.chat.device.SetCapabilitiesRequest.class,
      responseType = org.ghostprotocol.chat.device.SetCapabilitiesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetCapabilitiesRequest,
      org.ghostprotocol.chat.device.SetCapabilitiesResponse> getSetCapabilitiesMethod() {
    io.grpc.MethodDescriptor<org.ghostprotocol.chat.device.SetCapabilitiesRequest, org.ghostprotocol.chat.device.SetCapabilitiesResponse> getSetCapabilitiesMethod;
    if ((getSetCapabilitiesMethod = DevicesGrpc.getSetCapabilitiesMethod) == null) {
      synchronized (DevicesGrpc.class) {
        if ((getSetCapabilitiesMethod = DevicesGrpc.getSetCapabilitiesMethod) == null) {
          DevicesGrpc.getSetCapabilitiesMethod = getSetCapabilitiesMethod =
              io.grpc.MethodDescriptor.<org.ghostprotocol.chat.device.SetCapabilitiesRequest, org.ghostprotocol.chat.device.SetCapabilitiesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetCapabilities"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.SetCapabilitiesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.ghostprotocol.chat.device.SetCapabilitiesResponse.getDefaultInstance()))
              .setSchemaDescriptor(new DevicesMethodDescriptorSupplier("SetCapabilities"))
              .build();
        }
      }
    }
    return getSetCapabilitiesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DevicesStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DevicesStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DevicesStub>() {
        @java.lang.Override
        public DevicesStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DevicesStub(channel, callOptions);
        }
      };
    return DevicesStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DevicesBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DevicesBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DevicesBlockingStub>() {
        @java.lang.Override
        public DevicesBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DevicesBlockingStub(channel, callOptions);
        }
      };
    return DevicesBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DevicesFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DevicesFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DevicesFutureStub>() {
        @java.lang.Override
        public DevicesFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DevicesFutureStub(channel, callOptions);
        }
      };
    return DevicesFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Provides methods for working with devices attached to a Signal account.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     **
     * Returns a list of devices associated with the caller's account.
     * </pre>
     */
    default void getDevices(org.ghostprotocol.chat.device.GetDevicesRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.GetDevicesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDevicesMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Removes a linked device from the caller's account. This call will fail with
     * a status of `PERMISSION_DENIED` if not called from the primary device
     * associated with an account. It will also fail with a status of
     * `INVALID_ARGUMENT` if the targeted device is the primary device associated
     * with the account.
     * </pre>
     */
    default void removeDevice(org.ghostprotocol.chat.device.RemoveDeviceRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.RemoveDeviceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRemoveDeviceMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the encrypted human-readable name for a specific devices. Primary
     * devices may change the name of any device associated with their account,
     * but linked devices may only change their own name. This call will fail with
     * a status of `NOT_FOUND` if no device was found with the given identifier.
     * It will also fail with a status of `PERMISSION_DENIED` if a linked device
     * tries to change the name of any device other than itself.
     * </pre>
     */
    default void setDeviceName(org.ghostprotocol.chat.device.SetDeviceNameRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetDeviceNameResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetDeviceNameMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the token(s) the server should use to send new message notifications
     * to the authenticated device.
     * </pre>
     */
    default void setPushToken(org.ghostprotocol.chat.device.SetPushTokenRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetPushTokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetPushTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Removes any push tokens associated with the authenticated device. After
     * calling this method, the server will assume that the authenticated device
     * will periodically poll for new messages.
     * </pre>
     */
    default void clearPushToken(org.ghostprotocol.chat.device.ClearPushTokenRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.ClearPushTokenResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getClearPushTokenMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Declares that the authenticated device supports certain features.
     * </pre>
     */
    default void setCapabilities(org.ghostprotocol.chat.device.SetCapabilitiesRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetCapabilitiesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetCapabilitiesMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Devices.
   * <pre>
   **
   * Provides methods for working with devices attached to a Signal account.
   * </pre>
   */
  public static abstract class DevicesImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DevicesGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Devices.
   * <pre>
   **
   * Provides methods for working with devices attached to a Signal account.
   * </pre>
   */
  public static final class DevicesStub
      extends io.grpc.stub.AbstractAsyncStub<DevicesStub> {
    private DevicesStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DevicesStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DevicesStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns a list of devices associated with the caller's account.
     * </pre>
     */
    public void getDevices(org.ghostprotocol.chat.device.GetDevicesRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.GetDevicesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDevicesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Removes a linked device from the caller's account. This call will fail with
     * a status of `PERMISSION_DENIED` if not called from the primary device
     * associated with an account. It will also fail with a status of
     * `INVALID_ARGUMENT` if the targeted device is the primary device associated
     * with the account.
     * </pre>
     */
    public void removeDevice(org.ghostprotocol.chat.device.RemoveDeviceRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.RemoveDeviceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRemoveDeviceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the encrypted human-readable name for a specific devices. Primary
     * devices may change the name of any device associated with their account,
     * but linked devices may only change their own name. This call will fail with
     * a status of `NOT_FOUND` if no device was found with the given identifier.
     * It will also fail with a status of `PERMISSION_DENIED` if a linked device
     * tries to change the name of any device other than itself.
     * </pre>
     */
    public void setDeviceName(org.ghostprotocol.chat.device.SetDeviceNameRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetDeviceNameResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetDeviceNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Sets the token(s) the server should use to send new message notifications
     * to the authenticated device.
     * </pre>
     */
    public void setPushToken(org.ghostprotocol.chat.device.SetPushTokenRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetPushTokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetPushTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Removes any push tokens associated with the authenticated device. After
     * calling this method, the server will assume that the authenticated device
     * will periodically poll for new messages.
     * </pre>
     */
    public void clearPushToken(org.ghostprotocol.chat.device.ClearPushTokenRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.ClearPushTokenResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getClearPushTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Declares that the authenticated device supports certain features.
     * </pre>
     */
    public void setCapabilities(org.ghostprotocol.chat.device.SetCapabilitiesRequest request,
        io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetCapabilitiesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetCapabilitiesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Devices.
   * <pre>
   **
   * Provides methods for working with devices attached to a Signal account.
   * </pre>
   */
  public static final class DevicesBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DevicesBlockingStub> {
    private DevicesBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DevicesBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DevicesBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns a list of devices associated with the caller's account.
     * </pre>
     */
    public org.ghostprotocol.chat.device.GetDevicesResponse getDevices(org.ghostprotocol.chat.device.GetDevicesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDevicesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Removes a linked device from the caller's account. This call will fail with
     * a status of `PERMISSION_DENIED` if not called from the primary device
     * associated with an account. It will also fail with a status of
     * `INVALID_ARGUMENT` if the targeted device is the primary device associated
     * with the account.
     * </pre>
     */
    public org.ghostprotocol.chat.device.RemoveDeviceResponse removeDevice(org.ghostprotocol.chat.device.RemoveDeviceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRemoveDeviceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Sets the encrypted human-readable name for a specific devices. Primary
     * devices may change the name of any device associated with their account,
     * but linked devices may only change their own name. This call will fail with
     * a status of `NOT_FOUND` if no device was found with the given identifier.
     * It will also fail with a status of `PERMISSION_DENIED` if a linked device
     * tries to change the name of any device other than itself.
     * </pre>
     */
    public org.ghostprotocol.chat.device.SetDeviceNameResponse setDeviceName(org.ghostprotocol.chat.device.SetDeviceNameRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetDeviceNameMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Sets the token(s) the server should use to send new message notifications
     * to the authenticated device.
     * </pre>
     */
    public org.ghostprotocol.chat.device.SetPushTokenResponse setPushToken(org.ghostprotocol.chat.device.SetPushTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetPushTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Removes any push tokens associated with the authenticated device. After
     * calling this method, the server will assume that the authenticated device
     * will periodically poll for new messages.
     * </pre>
     */
    public org.ghostprotocol.chat.device.ClearPushTokenResponse clearPushToken(org.ghostprotocol.chat.device.ClearPushTokenRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getClearPushTokenMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Declares that the authenticated device supports certain features.
     * </pre>
     */
    public org.ghostprotocol.chat.device.SetCapabilitiesResponse setCapabilities(org.ghostprotocol.chat.device.SetCapabilitiesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetCapabilitiesMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Devices.
   * <pre>
   **
   * Provides methods for working with devices attached to a Signal account.
   * </pre>
   */
  public static final class DevicesFutureStub
      extends io.grpc.stub.AbstractFutureStub<DevicesFutureStub> {
    private DevicesFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DevicesFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DevicesFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns a list of devices associated with the caller's account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.device.GetDevicesResponse> getDevices(
        org.ghostprotocol.chat.device.GetDevicesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDevicesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Removes a linked device from the caller's account. This call will fail with
     * a status of `PERMISSION_DENIED` if not called from the primary device
     * associated with an account. It will also fail with a status of
     * `INVALID_ARGUMENT` if the targeted device is the primary device associated
     * with the account.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.device.RemoveDeviceResponse> removeDevice(
        org.ghostprotocol.chat.device.RemoveDeviceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRemoveDeviceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Sets the encrypted human-readable name for a specific devices. Primary
     * devices may change the name of any device associated with their account,
     * but linked devices may only change their own name. This call will fail with
     * a status of `NOT_FOUND` if no device was found with the given identifier.
     * It will also fail with a status of `PERMISSION_DENIED` if a linked device
     * tries to change the name of any device other than itself.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.device.SetDeviceNameResponse> setDeviceName(
        org.ghostprotocol.chat.device.SetDeviceNameRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetDeviceNameMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Sets the token(s) the server should use to send new message notifications
     * to the authenticated device.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.device.SetPushTokenResponse> setPushToken(
        org.ghostprotocol.chat.device.SetPushTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetPushTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Removes any push tokens associated with the authenticated device. After
     * calling this method, the server will assume that the authenticated device
     * will periodically poll for new messages.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.device.ClearPushTokenResponse> clearPushToken(
        org.ghostprotocol.chat.device.ClearPushTokenRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getClearPushTokenMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Declares that the authenticated device supports certain features.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.ghostprotocol.chat.device.SetCapabilitiesResponse> setCapabilities(
        org.ghostprotocol.chat.device.SetCapabilitiesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetCapabilitiesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_DEVICES = 0;
  private static final int METHODID_REMOVE_DEVICE = 1;
  private static final int METHODID_SET_DEVICE_NAME = 2;
  private static final int METHODID_SET_PUSH_TOKEN = 3;
  private static final int METHODID_CLEAR_PUSH_TOKEN = 4;
  private static final int METHODID_SET_CAPABILITIES = 5;

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
        case METHODID_GET_DEVICES:
          serviceImpl.getDevices((org.ghostprotocol.chat.device.GetDevicesRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.GetDevicesResponse>) responseObserver);
          break;
        case METHODID_REMOVE_DEVICE:
          serviceImpl.removeDevice((org.ghostprotocol.chat.device.RemoveDeviceRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.RemoveDeviceResponse>) responseObserver);
          break;
        case METHODID_SET_DEVICE_NAME:
          serviceImpl.setDeviceName((org.ghostprotocol.chat.device.SetDeviceNameRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetDeviceNameResponse>) responseObserver);
          break;
        case METHODID_SET_PUSH_TOKEN:
          serviceImpl.setPushToken((org.ghostprotocol.chat.device.SetPushTokenRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetPushTokenResponse>) responseObserver);
          break;
        case METHODID_CLEAR_PUSH_TOKEN:
          serviceImpl.clearPushToken((org.ghostprotocol.chat.device.ClearPushTokenRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.ClearPushTokenResponse>) responseObserver);
          break;
        case METHODID_SET_CAPABILITIES:
          serviceImpl.setCapabilities((org.ghostprotocol.chat.device.SetCapabilitiesRequest) request,
              (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetCapabilitiesResponse>) responseObserver);
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
          getGetDevicesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.device.GetDevicesRequest,
              org.ghostprotocol.chat.device.GetDevicesResponse>(
                service, METHODID_GET_DEVICES)))
        .addMethod(
          getRemoveDeviceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.device.RemoveDeviceRequest,
              org.ghostprotocol.chat.device.RemoveDeviceResponse>(
                service, METHODID_REMOVE_DEVICE)))
        .addMethod(
          getSetDeviceNameMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.device.SetDeviceNameRequest,
              org.ghostprotocol.chat.device.SetDeviceNameResponse>(
                service, METHODID_SET_DEVICE_NAME)))
        .addMethod(
          getSetPushTokenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.device.SetPushTokenRequest,
              org.ghostprotocol.chat.device.SetPushTokenResponse>(
                service, METHODID_SET_PUSH_TOKEN)))
        .addMethod(
          getClearPushTokenMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.device.ClearPushTokenRequest,
              org.ghostprotocol.chat.device.ClearPushTokenResponse>(
                service, METHODID_CLEAR_PUSH_TOKEN)))
        .addMethod(
          getSetCapabilitiesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              org.ghostprotocol.chat.device.SetCapabilitiesRequest,
              org.ghostprotocol.chat.device.SetCapabilitiesResponse>(
                service, METHODID_SET_CAPABILITIES)))
        .build();
  }

  private static abstract class DevicesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DevicesBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.ghostprotocol.chat.device.Device.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Devices");
    }
  }

  private static final class DevicesFileDescriptorSupplier
      extends DevicesBaseDescriptorSupplier {
    DevicesFileDescriptorSupplier() {}
  }

  private static final class DevicesMethodDescriptorSupplier
      extends DevicesBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    DevicesMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (DevicesGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DevicesFileDescriptorSupplier())
              .addMethod(getGetDevicesMethod())
              .addMethod(getRemoveDeviceMethod())
              .addMethod(getSetDeviceNameMethod())
              .addMethod(getSetPushTokenMethod())
              .addMethod(getClearPushTokenMethod())
              .addMethod(getSetCapabilitiesMethod())
              .build();
        }
      }
    }
    return result;
  }
}
