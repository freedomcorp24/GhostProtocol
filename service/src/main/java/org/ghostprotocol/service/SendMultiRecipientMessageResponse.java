/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import org.ghostprotocol.ghostprotocol.identity.ServiceIdentifier;
import org.ghostprotocol.ghostprotocol.util.ServiceIdentifierAdapter;

public record SendMultiRecipientMessageResponse(
    @Schema(description = "a list of the service identifiers in the request that do not correspond to registered GhostProtocol users; will only be present if a group send endorsement was supplied for the request")
    @JsonSerialize(contentUsing = ServiceIdentifierAdapter.ServiceIdentifierSerializer.class)
    @JsonDeserialize(contentUsing = ServiceIdentifierAdapter.ServiceIdentifierDeserializer.class)
    List<ServiceIdentifier> uuids404) {
}
