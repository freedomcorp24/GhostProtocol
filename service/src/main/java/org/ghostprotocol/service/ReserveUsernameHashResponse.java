/*
 * Copyright 2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ghostprotocol.ghostprotocol.controllers.AccountController;
import org.ghostprotocol.ghostprotocol.util.ByteArrayBase64UrlAdapter;
import org.ghostprotocol.ghostprotocol.util.ExactlySize;
import java.util.UUID;

public record ReserveUsernameHashResponse(
    @JsonSerialize(using = ByteArrayBase64UrlAdapter.Serializing.class)
    @JsonDeserialize(using = ByteArrayBase64UrlAdapter.Deserializing.class)
    @ExactlySize(AccountController.USERNAME_HASH_LENGTH)
    byte[] usernameHash
) {}
