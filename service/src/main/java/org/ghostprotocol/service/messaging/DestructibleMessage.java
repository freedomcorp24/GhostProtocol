package org.ghostprotocol.service.messaging;

import java.util.UUID;

public class DestructibleMessage {
    private final String messageId;
    private final UUID senderId;
    private final long destructionTime;
    private final String content;
    private final long createdAt;
    private boolean isDestroyed;

    public DestructibleMessage(String messageId, UUID senderId, String content, long destructionTime) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.content = content;
        this.destructionTime = destructionTime;
        this.createdAt = System.currentTimeMillis();
        this.isDestroyed = false;
    }

    public String getMessageId() {
        return messageId;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public String getContent() {
        if (isDestroyed) {
            throw new IllegalStateException("Message has been destroyed");
        }
        return content;
    }

    public long getDestructionTime() {
        return destructionTime;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void markAsDestroyed() {
        this.isDestroyed = true;
    }

    public boolean shouldBeDestroyed() {
        return System.currentTimeMillis() >= createdAt + destructionTime;
    }
}
