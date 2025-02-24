package org.ghostprotocol.service.messaging;

import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.storage.MessagesManager;
import org.ghostprotocol.service.push.PushNotificationManager;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MessageDestructionService {
    private final MessagesManager messagesManager;
    private final PushNotificationManager pushNotificationManager;
    private final ScheduledExecutorService scheduler;

    public MessageDestructionService(
            MessagesManager messagesManager,
            PushNotificationManager pushNotificationManager,
            ScheduledExecutorService scheduler) {
        this.messagesManager = messagesManager;
        this.pushNotificationManager = pushNotificationManager;
        this.scheduler = scheduler;
    }

    public void scheduleMessageDestruction(String messageId, Account sender, long destructionTime) {
        scheduler.schedule(() -> {
            destroyMessage(messageId, sender);
        }, destructionTime, TimeUnit.MILLISECONDS);
    }

    public void destroyMessage(String messageId, Account sender) {
        // Delete message from storage
        messagesManager.delete(messageId);

        // Notify all recipients that message was destroyed
        notifyMessageDestruction(messageId, sender);
    }

    private void notifyMessageDestruction(String messageId, Account sender) {
        // Send push notification to all recipients that message was destroyed
        pushNotificationManager.sendMessageDestructionNotification(messageId, sender);
    }

    public void cancelMessageDestruction(String messageId) {
        // Cancel scheduled destruction if exists
        // Implementation depends on how we track scheduled tasks
    }
}
