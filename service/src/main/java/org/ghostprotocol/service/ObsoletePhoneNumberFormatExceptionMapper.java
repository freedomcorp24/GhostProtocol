package org.ghostprotocol.ghostprotocol.mappers;

import io.micrometer.core.instrument.Metrics;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import org.ghostprotocol.ghostprotocol.metrics.MetricsUtil;
import org.ghostprotocol.ghostprotocol.util.ObsoletePhoneNumberFormatException;

public class ObsoletePhoneNumberFormatExceptionMapper implements ExceptionMapper<ObsoletePhoneNumberFormatException> {

  private static final String COUNTER_NAME = MetricsUtil.name(ObsoletePhoneNumberFormatExceptionMapper.class, "errors");

  @Override
  public Response toResponse(final ObsoletePhoneNumberFormatException exception) {
    Metrics.counter(COUNTER_NAME, "regionCode", exception.getRegionCode()).increment();
    return Response.status(499).build();
  }
}
