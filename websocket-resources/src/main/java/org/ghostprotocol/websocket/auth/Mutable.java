/*
 * Copyright 2024 Signal Messenger, LLC
 * Copyright 2024 GhostProtocol Inc.
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.ghostprotocol.websocket.auth;

import io.dropwizard.auth.Auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An @{@link Auth} object annotated with {@link Mutable} indicates that the consumer of the object
 * will modify the object or its underlying canonical source.
 *
 * Note: An {@link Auth} object that does not specify @{@link ReadOnly} will be assumed to be @Mutable
 *
 * @see org.ghostprotocol.websocket.ReusableAuth
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Mutable {
}

