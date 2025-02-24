/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.attachments;
import java.util.Map;

public interface AttachmentGenerator {

  record Descriptor(Map<String, String> headers, String signedUploadLocation) {}

  Descriptor generateAttachment(final String key);

}
