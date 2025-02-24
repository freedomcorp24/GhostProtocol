/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import org.ghostprotocol.ghostprotocol.identity.AciServiceIdentifier;
import org.ghostprotocol.ghostprotocol.util.ServiceIdentifierAdapter;

public record AccountIdentifierResponse(@NotNull
                                        @JsonSerialize(using = ServiceIdentifierAdapter.ServiceIdentifierSerializer.class)
                                        @JsonDeserialize(using = ServiceIdentifierAdapter.AciServiceIdentifierDeserializer.class)
                                        AciServiceIdentifier uuid) {}
