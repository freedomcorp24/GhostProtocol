package org.ghostprotocol.service.security;

import org.ghostprotocol.service.storage.Account;
import org.ghostprotocol.service.push.PushNotificationManager;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EmergencyContactService {
    private final PushNotificationManager pushNotificationManager;
    private final ConcurrentHashMap<UUID, List<UUID>> emergencyContacts;

    public EmergencyContactService(PushNotificationManager pushNotificationManager) {
        this.pushNotificationManager = pushNotificationManager;
        this.emergencyContacts = new ConcurrentHashMap<>();
    }

    public void addEmergencyContact(Account account, Account contact) {
        List<UUID> contacts = emergencyContacts.computeIfAbsent(
            account.getUuid(), 
            k -> new java.util.concurrent.CopyOnWriteArrayList<>()
        );
        contacts.add(contact.getUuid());
    }

    public void removeEmergencyContact(Account account, Account contact) {
        List<UUID> contacts = emergencyContacts.get(account.getUuid());
        if (contacts != null) {
            contacts.remove(contact.getUuid());
        }
    }

    public List<UUID> getEmergencyContacts(Account account) {
        return emergencyContacts.getOrDefault(
            account.getUuid(),
            new java.util.concurrent.CopyOnWriteArrayList<>()
        );
    }

    public void notifyEmergencyContacts(Account account, EmergencyType type) {
        List<UUID> contacts = getEmergencyContacts(account);
        for (UUID contactId : contacts) {
            pushNotificationManager.sendEmergencyNotification(
                contactId,
                account.getUsername(),
                type
            );
        }
    }

    public enum EmergencyType {
        DURESS_MODE_ACTIVATED,
        ACCOUNT_COMPROMISED,
        LOCATION_SHARED
    }
}
