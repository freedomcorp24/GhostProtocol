package org.ghostprotocol.chat.device;

import static org.ghostprotocol.chat.device.DevicesGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/device.proto")
public final class ReactorDevicesGrpc {
    private ReactorDevicesGrpc() {}

    public static ReactorDevicesStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorDevicesStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with devices attached to a Signal account.
     * </pre>
     */
    public static final class ReactorDevicesStub extends io.grpc.stub.AbstractStub<ReactorDevicesStub> {
        private DevicesGrpc.DevicesStub delegateStub;

        private ReactorDevicesStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = DevicesGrpc.newStub(channel);
        }

        private ReactorDevicesStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = DevicesGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorDevicesStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorDevicesStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Returns a list of devices associated with the caller&#39;s account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.GetDevicesResponse> getDevices(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.GetDevicesRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getDevices, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Removes a linked device from the caller&#39;s account. This call will fail with
         *  a status of `PERMISSION_DENIED` if not called from the primary device
         *  associated with an account. It will also fail with a status of
         *  `INVALID_ARGUMENT` if the targeted device is the primary device associated
         *  with the account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.RemoveDeviceResponse> removeDevice(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.RemoveDeviceRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::removeDevice, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the encrypted human-readable name for a specific devices. Primary
         *  devices may change the name of any device associated with their account,
         *  but linked devices may only change their own name. This call will fail with
         *  a status of `NOT_FOUND` if no device was found with the given identifier.
         *  It will also fail with a status of `PERMISSION_DENIED` if a linked device
         *  tries to change the name of any device other than itself.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetDeviceNameResponse> setDeviceName(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetDeviceNameRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setDeviceName, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the token(s) the server should use to send new message notifications
         *  to the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetPushTokenResponse> setPushToken(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetPushTokenRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setPushToken, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Removes any push tokens associated with the authenticated device. After
         *  calling this method, the server will assume that the authenticated device
         *  will periodically poll for new messages.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.ClearPushTokenResponse> clearPushToken(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.ClearPushTokenRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::clearPushToken, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Declares that the authenticated device supports certain features.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetCapabilitiesResponse> setCapabilities(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetCapabilitiesRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setCapabilities, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Returns a list of devices associated with the caller&#39;s account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.GetDevicesResponse> getDevices(org.ghostprotocol.chat.device.GetDevicesRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getDevices, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Removes a linked device from the caller&#39;s account. This call will fail with
         *  a status of `PERMISSION_DENIED` if not called from the primary device
         *  associated with an account. It will also fail with a status of
         *  `INVALID_ARGUMENT` if the targeted device is the primary device associated
         *  with the account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.RemoveDeviceResponse> removeDevice(org.ghostprotocol.chat.device.RemoveDeviceRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::removeDevice, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the encrypted human-readable name for a specific devices. Primary
         *  devices may change the name of any device associated with their account,
         *  but linked devices may only change their own name. This call will fail with
         *  a status of `NOT_FOUND` if no device was found with the given identifier.
         *  It will also fail with a status of `PERMISSION_DENIED` if a linked device
         *  tries to change the name of any device other than itself.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetDeviceNameResponse> setDeviceName(org.ghostprotocol.chat.device.SetDeviceNameRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setDeviceName, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the token(s) the server should use to send new message notifications
         *  to the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetPushTokenResponse> setPushToken(org.ghostprotocol.chat.device.SetPushTokenRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setPushToken, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Removes any push tokens associated with the authenticated device. After
         *  calling this method, the server will assume that the authenticated device
         *  will periodically poll for new messages.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.ClearPushTokenResponse> clearPushToken(org.ghostprotocol.chat.device.ClearPushTokenRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::clearPushToken, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Declares that the authenticated device supports certain features.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetCapabilitiesResponse> setCapabilities(org.ghostprotocol.chat.device.SetCapabilitiesRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setCapabilities, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with devices attached to a Signal account.
     * </pre>
     */
    public static abstract class DevicesImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Returns a list of devices associated with the caller&#39;s account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.GetDevicesResponse> getDevices(org.ghostprotocol.chat.device.GetDevicesRequest request) {
            return getDevices(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Returns a list of devices associated with the caller&#39;s account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.GetDevicesResponse> getDevices(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.GetDevicesRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Removes a linked device from the caller&#39;s account. This call will fail with
         *  a status of `PERMISSION_DENIED` if not called from the primary device
         *  associated with an account. It will also fail with a status of
         *  `INVALID_ARGUMENT` if the targeted device is the primary device associated
         *  with the account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.RemoveDeviceResponse> removeDevice(org.ghostprotocol.chat.device.RemoveDeviceRequest request) {
            return removeDevice(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Removes a linked device from the caller&#39;s account. This call will fail with
         *  a status of `PERMISSION_DENIED` if not called from the primary device
         *  associated with an account. It will also fail with a status of
         *  `INVALID_ARGUMENT` if the targeted device is the primary device associated
         *  with the account.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.RemoveDeviceResponse> removeDevice(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.RemoveDeviceRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets the encrypted human-readable name for a specific devices. Primary
         *  devices may change the name of any device associated with their account,
         *  but linked devices may only change their own name. This call will fail with
         *  a status of `NOT_FOUND` if no device was found with the given identifier.
         *  It will also fail with a status of `PERMISSION_DENIED` if a linked device
         *  tries to change the name of any device other than itself.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetDeviceNameResponse> setDeviceName(org.ghostprotocol.chat.device.SetDeviceNameRequest request) {
            return setDeviceName(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets the encrypted human-readable name for a specific devices. Primary
         *  devices may change the name of any device associated with their account,
         *  but linked devices may only change their own name. This call will fail with
         *  a status of `NOT_FOUND` if no device was found with the given identifier.
         *  It will also fail with a status of `PERMISSION_DENIED` if a linked device
         *  tries to change the name of any device other than itself.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetDeviceNameResponse> setDeviceName(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetDeviceNameRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets the token(s) the server should use to send new message notifications
         *  to the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetPushTokenResponse> setPushToken(org.ghostprotocol.chat.device.SetPushTokenRequest request) {
            return setPushToken(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets the token(s) the server should use to send new message notifications
         *  to the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetPushTokenResponse> setPushToken(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetPushTokenRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Removes any push tokens associated with the authenticated device. After
         *  calling this method, the server will assume that the authenticated device
         *  will periodically poll for new messages.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.ClearPushTokenResponse> clearPushToken(org.ghostprotocol.chat.device.ClearPushTokenRequest request) {
            return clearPushToken(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Removes any push tokens associated with the authenticated device. After
         *  calling this method, the server will assume that the authenticated device
         *  will periodically poll for new messages.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.ClearPushTokenResponse> clearPushToken(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.ClearPushTokenRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Declares that the authenticated device supports certain features.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetCapabilitiesResponse> setCapabilities(org.ghostprotocol.chat.device.SetCapabilitiesRequest request) {
            return setCapabilities(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Declares that the authenticated device supports certain features.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetCapabilitiesResponse> setCapabilities(reactor.core.publisher.Mono<org.ghostprotocol.chat.device.SetCapabilitiesRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.device.DevicesGrpc.getGetDevicesMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.device.GetDevicesRequest,
                                            org.ghostprotocol.chat.device.GetDevicesResponse>(
                                            this, METHODID_GET_DEVICES)))
                    .addMethod(
                            org.ghostprotocol.chat.device.DevicesGrpc.getRemoveDeviceMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.device.RemoveDeviceRequest,
                                            org.ghostprotocol.chat.device.RemoveDeviceResponse>(
                                            this, METHODID_REMOVE_DEVICE)))
                    .addMethod(
                            org.ghostprotocol.chat.device.DevicesGrpc.getSetDeviceNameMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.device.SetDeviceNameRequest,
                                            org.ghostprotocol.chat.device.SetDeviceNameResponse>(
                                            this, METHODID_SET_DEVICE_NAME)))
                    .addMethod(
                            org.ghostprotocol.chat.device.DevicesGrpc.getSetPushTokenMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.device.SetPushTokenRequest,
                                            org.ghostprotocol.chat.device.SetPushTokenResponse>(
                                            this, METHODID_SET_PUSH_TOKEN)))
                    .addMethod(
                            org.ghostprotocol.chat.device.DevicesGrpc.getClearPushTokenMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.device.ClearPushTokenRequest,
                                            org.ghostprotocol.chat.device.ClearPushTokenResponse>(
                                            this, METHODID_CLEAR_PUSH_TOKEN)))
                    .addMethod(
                            org.ghostprotocol.chat.device.DevicesGrpc.getSetCapabilitiesMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.device.SetCapabilitiesRequest,
                                            org.ghostprotocol.chat.device.SetCapabilitiesResponse>(
                                            this, METHODID_SET_CAPABILITIES)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_DEVICES = 0;
    public static final int METHODID_REMOVE_DEVICE = 1;
    public static final int METHODID_SET_DEVICE_NAME = 2;
    public static final int METHODID_SET_PUSH_TOKEN = 3;
    public static final int METHODID_CLEAR_PUSH_TOKEN = 4;
    public static final int METHODID_SET_CAPABILITIES = 5;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final DevicesImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(DevicesImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_DEVICES:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.device.GetDevicesRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.GetDevicesResponse>) responseObserver,
                            serviceImpl::getDevices, serviceImpl::onErrorMap);
                    break;
                case METHODID_REMOVE_DEVICE:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.device.RemoveDeviceRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.RemoveDeviceResponse>) responseObserver,
                            serviceImpl::removeDevice, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_DEVICE_NAME:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.device.SetDeviceNameRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetDeviceNameResponse>) responseObserver,
                            serviceImpl::setDeviceName, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_PUSH_TOKEN:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.device.SetPushTokenRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetPushTokenResponse>) responseObserver,
                            serviceImpl::setPushToken, serviceImpl::onErrorMap);
                    break;
                case METHODID_CLEAR_PUSH_TOKEN:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.device.ClearPushTokenRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.ClearPushTokenResponse>) responseObserver,
                            serviceImpl::clearPushToken, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_CAPABILITIES:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.device.SetCapabilitiesRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.device.SetCapabilitiesResponse>) responseObserver,
                            serviceImpl::setCapabilities, serviceImpl::onErrorMap);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }

}
