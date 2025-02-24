package org.ghostprotocol.chat.keys;

import static org.ghostprotocol.chat.keys.KeysGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;


@javax.annotation.Generated(
value = "by ReactorGrpc generator",
comments = "Source: org/ghostprotocol/chat/keys.proto")
public final class ReactorKeysGrpc {
    private ReactorKeysGrpc() {}

    public static ReactorKeysStub newReactorStub(io.grpc.Channel channel) {
        return new ReactorKeysStub(channel);
    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with pre-keys.
     * </pre>
     */
    public static final class ReactorKeysStub extends io.grpc.stub.AbstractStub<ReactorKeysStub> {
        private KeysGrpc.KeysStub delegateStub;

        private ReactorKeysStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = KeysGrpc.newStub(channel);
        }

        private ReactorKeysStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = KeysGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected ReactorKeysStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new ReactorKeysStub(channel, callOptions);
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves an approximate count of the number of the various kinds of
         *  pre-keys stored for the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeyCountResponse> getPreKeyCount(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeyCountRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getPreKeyCount, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a set of pre-keys for establishing a session with the targeted
         *  device or devices. Note that callers with an unidentified access key for
         *  the targeted account should use the version of this method in
         *  `KeysAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found, if no active device with the given ID (if specified) was found on
         *  the target account, or if the account has no active devices. It may also
         *  fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::getPreKeys, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Uploads a new set of one-time EC pre-keys for the authenticated device,
         *  clearing any previously-stored pre-keys. Note that all keys submitted via
         *  a single call to this method _must_ have the same identity type (i.e. if
         *  the first key has an ACI identity type, then all other keys in the same
         *  stream must also have an ACI identity type).
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
         *  given pre-keys was structurally invalid or if the list of pre-keys was
         *  empty.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeEcPreKeys(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setOneTimeEcPreKeys, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Uploads a new set of one-time KEM pre-keys for the authenticated device,
         *  clearing any previously-stored pre-keys. Note that all keys submitted via
         *  a single call to this method _must_ have the same identity type (i.e. if
         *  the first key has an ACI identity type, then all other keys in the same
         *  stream must also have an ACI identity type).
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
         *  given pre-keys was structurally invalid, had an invalid signature, or if
         *  the list of pre-keys was empty.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeKemSignedPreKeys(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setOneTimeKemSignedPreKeys, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the signed EC pre-key for one identity (i.e. ACI or PNI) associated
         *  with the authenticated device.
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
         *  was structurally invalid, had a bad signature, or was missing entirely.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setEcSignedPreKey(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setEcSignedPreKey, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the last-resort KEM pre-key for one identity (i.e. ACI or PNI)
         *  associated with the authenticated device.
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
         *  was structurally invalid, had a bad signature, or was missing entirely.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setKemLastResortPreKey(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest> reactorRequest) {
            return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactorRequest, delegateStub::setKemLastResortPreKey, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves an approximate count of the number of the various kinds of
         *  pre-keys stored for the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeyCountResponse> getPreKeyCount(org.ghostprotocol.chat.keys.GetPreKeyCountRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getPreKeyCount, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a set of pre-keys for establishing a session with the targeted
         *  device or devices. Note that callers with an unidentified access key for
         *  the targeted account should use the version of this method in
         *  `KeysAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found, if no active device with the given ID (if specified) was found on
         *  the target account, or if the account has no active devices. It may also
         *  fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::getPreKeys, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Uploads a new set of one-time EC pre-keys for the authenticated device,
         *  clearing any previously-stored pre-keys. Note that all keys submitted via
         *  a single call to this method _must_ have the same identity type (i.e. if
         *  the first key has an ACI identity type, then all other keys in the same
         *  stream must also have an ACI identity type).
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
         *  given pre-keys was structurally invalid or if the list of pre-keys was
         *  empty.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeEcPreKeys(org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setOneTimeEcPreKeys, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Uploads a new set of one-time KEM pre-keys for the authenticated device,
         *  clearing any previously-stored pre-keys. Note that all keys submitted via
         *  a single call to this method _must_ have the same identity type (i.e. if
         *  the first key has an ACI identity type, then all other keys in the same
         *  stream must also have an ACI identity type).
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
         *  given pre-keys was structurally invalid, had an invalid signature, or if
         *  the list of pre-keys was empty.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeKemSignedPreKeys(org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setOneTimeKemSignedPreKeys, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the signed EC pre-key for one identity (i.e. ACI or PNI) associated
         *  with the authenticated device.
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
         *  was structurally invalid, had a bad signature, or was missing entirely.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setEcSignedPreKey(org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setEcSignedPreKey, getCallOptions());
        }

        /**
         * <pre>
         * &#42;
         *  Sets the last-resort KEM pre-key for one identity (i.e. ACI or PNI)
         *  associated with the authenticated device.
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
         *  was structurally invalid, had a bad signature, or was missing entirely.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setKemLastResortPreKey(org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest reactorRequest) {
           return com.salesforce.reactorgrpc.stub.ClientCalls.oneToOne(reactor.core.publisher.Mono.just(reactorRequest), delegateStub::setKemLastResortPreKey, getCallOptions());
        }

    }

    /**
     * <pre>
     * &#42;
     *  Provides methods for working with pre-keys.
     * </pre>
     */
    public static abstract class KeysImplBase implements io.grpc.BindableService {

        /**
         * <pre>
         * &#42;
         *  Retrieves an approximate count of the number of the various kinds of
         *  pre-keys stored for the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeyCountResponse> getPreKeyCount(org.ghostprotocol.chat.keys.GetPreKeyCountRequest request) {
            return getPreKeyCount(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves an approximate count of the number of the various kinds of
         *  pre-keys stored for the authenticated device.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeyCountResponse> getPreKeyCount(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeyCountRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a set of pre-keys for establishing a session with the targeted
         *  device or devices. Note that callers with an unidentified access key for
         *  the targeted account should use the version of this method in
         *  `KeysAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found, if no active device with the given ID (if specified) was found on
         *  the target account, or if the account has no active devices. It may also
         *  fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(org.ghostprotocol.chat.keys.GetPreKeysRequest request) {
            return getPreKeys(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Retrieves a set of pre-keys for establishing a session with the targeted
         *  device or devices. Note that callers with an unidentified access key for
         *  the targeted account should use the version of this method in
         *  `KeysAnonymous` instead.
         * 
         *  This RPC may fail with a `NOT_FOUND` status if the target account was not
         *  found, if no active device with the given ID (if specified) was found on
         *  the target account, or if the account has no active devices. It may also
         *  fail with a `RESOURCE_EXHAUSTED` if a rate limit for fetching keys has been
         *  exceeded, in which case a `retry-after` header containing an ISO 8601
         *  duration string will be present in the response trailers.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysResponse> getPreKeys(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.GetPreKeysRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Uploads a new set of one-time EC pre-keys for the authenticated device,
         *  clearing any previously-stored pre-keys. Note that all keys submitted via
         *  a single call to this method _must_ have the same identity type (i.e. if
         *  the first key has an ACI identity type, then all other keys in the same
         *  stream must also have an ACI identity type).
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
         *  given pre-keys was structurally invalid or if the list of pre-keys was
         *  empty.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeEcPreKeys(org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest request) {
            return setOneTimeEcPreKeys(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Uploads a new set of one-time EC pre-keys for the authenticated device,
         *  clearing any previously-stored pre-keys. Note that all keys submitted via
         *  a single call to this method _must_ have the same identity type (i.e. if
         *  the first key has an ACI identity type, then all other keys in the same
         *  stream must also have an ACI identity type).
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
         *  given pre-keys was structurally invalid or if the list of pre-keys was
         *  empty.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeEcPreKeys(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Uploads a new set of one-time KEM pre-keys for the authenticated device,
         *  clearing any previously-stored pre-keys. Note that all keys submitted via
         *  a single call to this method _must_ have the same identity type (i.e. if
         *  the first key has an ACI identity type, then all other keys in the same
         *  stream must also have an ACI identity type).
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
         *  given pre-keys was structurally invalid, had an invalid signature, or if
         *  the list of pre-keys was empty.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeKemSignedPreKeys(org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest request) {
            return setOneTimeKemSignedPreKeys(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Uploads a new set of one-time KEM pre-keys for the authenticated device,
         *  clearing any previously-stored pre-keys. Note that all keys submitted via
         *  a single call to this method _must_ have the same identity type (i.e. if
         *  the first key has an ACI identity type, then all other keys in the same
         *  stream must also have an ACI identity type).
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if one or more of the
         *  given pre-keys was structurally invalid, had an invalid signature, or if
         *  the list of pre-keys was empty.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setOneTimeKemSignedPreKeys(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets the signed EC pre-key for one identity (i.e. ACI or PNI) associated
         *  with the authenticated device.
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
         *  was structurally invalid, had a bad signature, or was missing entirely.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setEcSignedPreKey(org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest request) {
            return setEcSignedPreKey(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets the signed EC pre-key for one identity (i.e. ACI or PNI) associated
         *  with the authenticated device.
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
         *  was structurally invalid, had a bad signature, or was missing entirely.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setEcSignedPreKey(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        /**
         * <pre>
         * &#42;
         *  Sets the last-resort KEM pre-key for one identity (i.e. ACI or PNI)
         *  associated with the authenticated device.
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
         *  was structurally invalid, had a bad signature, or was missing entirely.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setKemLastResortPreKey(org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest request) {
            return setKemLastResortPreKey(reactor.core.publisher.Mono.just(request));
        }

        /**
         * <pre>
         * &#42;
         *  Sets the last-resort KEM pre-key for one identity (i.e. ACI or PNI)
         *  associated with the authenticated device.
         * 
         *  This RPC may fail with an `INVALID_ARGUMENT` status if the given pre-key
         *  was structurally invalid, had a bad signature, or was missing entirely.
         * </pre>
         */
        public reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetPreKeyResponse> setKemLastResortPreKey(reactor.core.publisher.Mono<org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest> request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                    .addMethod(
                            org.ghostprotocol.chat.keys.KeysGrpc.getGetPreKeyCountMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.keys.GetPreKeyCountRequest,
                                            org.ghostprotocol.chat.keys.GetPreKeyCountResponse>(
                                            this, METHODID_GET_PRE_KEY_COUNT)))
                    .addMethod(
                            org.ghostprotocol.chat.keys.KeysGrpc.getGetPreKeysMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.keys.GetPreKeysRequest,
                                            org.ghostprotocol.chat.keys.GetPreKeysResponse>(
                                            this, METHODID_GET_PRE_KEYS)))
                    .addMethod(
                            org.ghostprotocol.chat.keys.KeysGrpc.getSetOneTimeEcPreKeysMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest,
                                            org.ghostprotocol.chat.keys.SetPreKeyResponse>(
                                            this, METHODID_SET_ONE_TIME_EC_PRE_KEYS)))
                    .addMethod(
                            org.ghostprotocol.chat.keys.KeysGrpc.getSetOneTimeKemSignedPreKeysMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest,
                                            org.ghostprotocol.chat.keys.SetPreKeyResponse>(
                                            this, METHODID_SET_ONE_TIME_KEM_SIGNED_PRE_KEYS)))
                    .addMethod(
                            org.ghostprotocol.chat.keys.KeysGrpc.getSetEcSignedPreKeyMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest,
                                            org.ghostprotocol.chat.keys.SetPreKeyResponse>(
                                            this, METHODID_SET_EC_SIGNED_PRE_KEY)))
                    .addMethod(
                            org.ghostprotocol.chat.keys.KeysGrpc.getSetKemLastResortPreKeyMethod(),
                            asyncUnaryCall(
                                    new MethodHandlers<
                                            org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest,
                                            org.ghostprotocol.chat.keys.SetPreKeyResponse>(
                                            this, METHODID_SET_KEM_LAST_RESORT_PRE_KEY)))
                    .build();
        }

        protected io.grpc.CallOptions getCallOptions(int methodId) {
            return null;
        }

        protected Throwable onErrorMap(Throwable throwable) {
            return com.salesforce.reactorgrpc.stub.ServerCalls.prepareError(throwable);
        }
    }

    public static final int METHODID_GET_PRE_KEY_COUNT = 0;
    public static final int METHODID_GET_PRE_KEYS = 1;
    public static final int METHODID_SET_ONE_TIME_EC_PRE_KEYS = 2;
    public static final int METHODID_SET_ONE_TIME_KEM_SIGNED_PRE_KEYS = 3;
    public static final int METHODID_SET_EC_SIGNED_PRE_KEY = 4;
    public static final int METHODID_SET_KEM_LAST_RESORT_PRE_KEY = 5;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final KeysImplBase serviceImpl;
        private final int methodId;

        MethodHandlers(KeysImplBase serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_PRE_KEY_COUNT:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.keys.GetPreKeyCountRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeyCountResponse>) responseObserver,
                            serviceImpl::getPreKeyCount, serviceImpl::onErrorMap);
                    break;
                case METHODID_GET_PRE_KEYS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.keys.GetPreKeysRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.GetPreKeysResponse>) responseObserver,
                            serviceImpl::getPreKeys, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_ONE_TIME_EC_PRE_KEYS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.keys.SetOneTimeEcPreKeysRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse>) responseObserver,
                            serviceImpl::setOneTimeEcPreKeys, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_ONE_TIME_KEM_SIGNED_PRE_KEYS:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.keys.SetOneTimeKemSignedPreKeysRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse>) responseObserver,
                            serviceImpl::setOneTimeKemSignedPreKeys, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_EC_SIGNED_PRE_KEY:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.keys.SetEcSignedPreKeyRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse>) responseObserver,
                            serviceImpl::setEcSignedPreKey, serviceImpl::onErrorMap);
                    break;
                case METHODID_SET_KEM_LAST_RESORT_PRE_KEY:
                    com.salesforce.reactorgrpc.stub.ServerCalls.oneToOne((org.ghostprotocol.chat.keys.SetKemLastResortPreKeyRequest) request,
                            (io.grpc.stub.StreamObserver<org.ghostprotocol.chat.keys.SetPreKeyResponse>) responseObserver,
                            serviceImpl::setKemLastResortPreKey, serviceImpl::onErrorMap);
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
