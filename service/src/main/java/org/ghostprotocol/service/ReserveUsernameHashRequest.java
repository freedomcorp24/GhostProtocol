/*
 * Copyright 2022 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.ghostprotocol.ghostprotocol.controllers.AccountController;
import org.ghostprotocol.ghostprotocol.util.ByteArrayBase64UrlAdapter;

public record ReserveUsernameHashRequest(
    @NotNull
    @Valid
    @Size(min=1, max=AccountController.MAXIMUM_USERNAME_HASHES_LIST_LENGTH)
    @JsonSerialize(contentUsing = ByteArrayBase64UrlAdapter.Serializing.class)
    @JsonDeserialize(contentUsing = ByteArrayBase64UrlAdapter.Deserializing.class)
    List<byte[]> usernameHashes
) {}
