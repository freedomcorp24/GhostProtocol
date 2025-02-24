/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.configuration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.dropwizard.jackson.Discoverable;
import org.ghostprotocol.ghostprotocol.s3.S3ObjectMonitor;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import java.util.concurrent.ScheduledExecutorService;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = MonitoredS3ObjectConfiguration.class)
public interface S3ObjectMonitorFactory extends Discoverable {

  S3ObjectMonitor build(AwsCredentialsProvider awsCredentialsProvider,
      ScheduledExecutorService refreshExecutorService);
}
