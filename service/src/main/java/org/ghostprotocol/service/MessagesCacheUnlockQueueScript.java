/*
 * Copyright 2024 GhostProtocol
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.ghostprotocol.ghostprotocol.storage;

import io.lettuce.core.ScriptOutputType;
import org.ghostprotocol.ghostprotocol.push.ClientEvent;
import org.ghostprotocol.ghostprotocol.push.MessagesPersistedEvent;
import org.ghostprotocol.ghostprotocol.push.WebSocketConnectionEventManager;
import org.ghostprotocol.ghostprotocol.redis.ClusterLuaScript;
import org.ghostprotocol.ghostprotocol.redis.FaultTolerantRedisClusterClient;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Unlocks a message queue for persistence/message retrieval.
 */
class MessagesCacheUnlockQueueScript {

  private final ClusterLuaScript unlockQueueScript;

  private final List<byte[]> MESSAGES_PERSISTED_EVENT_ARGS = List.of(ClientEvent.newBuilder()
      .setMessagesPersisted(MessagesPersistedEvent.getDefaultInstance())
      .build()
      .toByteArray()); // eventPayload

  MessagesCacheUnlockQueueScript(final FaultTolerantRedisClusterClient redisCluster) throws IOException {
    this.unlockQueueScript =
        ClusterLuaScript.fromResource(redisCluster, "lua/unlock_queue.lua", ScriptOutputType.STATUS);
  }

  void execute(final UUID accountIdentifier, final byte deviceId) {
    final List<byte[]> keys = List.of(
        MessagesCache.getPersistInProgressKey(accountIdentifier, deviceId), // persistInProgressKey
        WebSocketConnectionEventManager.getClientEventChannel(accountIdentifier, deviceId) // eventChannelKey
    );

    unlockQueueScript.executeBinary(keys, MESSAGES_PERSISTED_EVENT_ARGS);
  }
}
