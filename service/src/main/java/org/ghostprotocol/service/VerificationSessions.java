/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.storage;

import java.time.Clock;
import org.ghostprotocol.ghostprotocol.registration.VerificationSession;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

public class VerificationSessions extends SerializedExpireableJsonDynamoStore<VerificationSession> {

  public VerificationSessions(final DynamoDbAsyncClient dynamoDbClient, final String tableName, final Clock clock) {
    super(dynamoDbClient, tableName, clock);
  }
}
