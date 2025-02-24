/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.service.util.logging;

import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.core.filter.Filter;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.common.filter.FilterFactory;

@JsonTypeName("requestLogEnabled")
class RequestLogEnabledFilterFactory implements FilterFactory<IAccessEvent> {

    @Override
    public Filter<IAccessEvent> build() {
        return RequestLogManager.getHttpRequestLogFilter();
    }
}
