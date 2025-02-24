/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.ghostprotocol.protocol.IdentityKey;
import org.ghostprotocol.ghostprotocol.identity.ServiceIdentifier;
import org.ghostprotocol.ghostprotocol.util.IdentityKeyAdapter;
import org.ghostprotocol.ghostprotocol.util.ServiceIdentifierAdapter;

public record BatchIdentityCheckResponse(@Valid List<Element> elements) {

  public record Element(@JsonInclude(JsonInclude.Include.NON_EMPTY)
                        @JsonSerialize(using = ServiceIdentifierAdapter.ServiceIdentifierSerializer.class)
                        @JsonDeserialize(using = ServiceIdentifierAdapter.ServiceIdentifierDeserializer.class)
                        @NotNull
                        ServiceIdentifier uuid,

                        @NotNull
                        @JsonSerialize(using = IdentityKeyAdapter.Serializer.class)
                        @JsonDeserialize(using = IdentityKeyAdapter.Deserializer.class)
                        IdentityKey identityKey) {
  }
}
