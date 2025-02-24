/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ghostprotocol.ghostprotocol.identity.ServiceIdentifier;
import org.ghostprotocol.ghostprotocol.util.ServiceIdentifierAdapter;

public record AccountStaleDevices(@JsonSerialize(using = ServiceIdentifierAdapter.ServiceIdentifierSerializer.class)
                                  @JsonDeserialize(using = ServiceIdentifierAdapter.ServiceIdentifierDeserializer.class)
                                  ServiceIdentifier uuid,

                                  StaleDevices devices) {
}
