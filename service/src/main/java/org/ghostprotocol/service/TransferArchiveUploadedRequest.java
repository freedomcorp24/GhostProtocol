/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.ghostprotocol.ghostprotocol.storage.Device;

public record TransferArchiveUploadedRequest(
    @Min(1)
    @Max(Device.MAXIMUM_DEVICE_ID)
    @Schema(description = "The ID of the device for which the transfer archive has been prepared")
    byte destinationDeviceId,

    @Positive
    @Schema(description = "The timestamp, in milliseconds since the epoch, at which the destination device was created")
    long destinationDeviceCreated,

    @NotNull
    @Valid
    @Schema(description = """
          The location of the transfer archive if the archive was successfully uploaded, otherwise a error indicating that
           the upload has failed and the destination device should stop waiting
          """, oneOf = {RemoteAttachment.class, RemoteAttachmentError.class})
    TransferArchiveResult transferArchive) {}
