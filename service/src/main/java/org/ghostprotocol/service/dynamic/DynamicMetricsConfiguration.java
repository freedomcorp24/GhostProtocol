/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.configuration.dynamic;

/**
 * @param enableLettuceRemoteTag whether the `remote` tag should be added. Note: although this is dynamic, meters are
 *                               cached after creation, so changes will only affect servers launched after the change.
 * @param enableAwsSdkMetrics whether to record AWS SDK metrics. Note: although this is dynamic, meters are cached after
 *                            creation, so changes will only affect servers launched after the change.
 */
public record DynamicMetricsConfiguration(boolean enableLettuceRemoteTag, boolean enableAwsSdkMetrics) {
}
