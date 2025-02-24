package org.ghostprotocol.service.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.media.Schema;
import org.signal.libsignal.protocol.ecc.ECPublicKey;
import org.ghostprotocol.service.util.ECPublicKeyAdapter;

public record SetPublicKeyRequest(
    @JsonSerialize(using = ECPublicKeyAdapter.Serializer.class)
    @JsonDeserialize(using = ECPublicKeyAdapter.Deserializer.class)
    @Schema(type="string", description="""
        The public key, serialized in libsignal's elliptic-curve public key format and then encoded as a standard (i.e.
        not URL-safe), padded, base64-encoded string.
        """)
    ECPublicKey publicKey) {
}
