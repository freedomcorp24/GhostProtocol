/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.util.ua;

public class UnrecognizedUserAgentException extends Exception {

    public UnrecognizedUserAgentException() {
    }

    public UnrecognizedUserAgentException(final String message) {
        super(message);
    }

    public UnrecognizedUserAgentException(final Throwable cause) {
        super(cause);
    }
}
