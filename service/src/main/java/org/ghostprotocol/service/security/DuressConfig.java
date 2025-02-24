package org.ghostprotocol.service.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class DuressConfig {
    private final UUID userId;
    private final String duressPassword;
    private final String customMessage;
    private final UUID emergencyContact;

    public DuressConfig(
            @JsonProperty("userId") UUID userId,
            @JsonProperty("duressPassword") String duressPassword,
            @JsonProperty("customMessage") String customMessage,
            @JsonProperty("emergencyContact") UUID emergencyContact) {
        this.userId = userId;
        this.duressPassword = duressPassword;
        this.customMessage = customMessage;
        this.emergencyContact = emergencyContact;
    }

    @JsonProperty
    public UUID getUserId() {
        return userId;
    }

    @JsonProperty
    public String getDuressPassword() {
        return duressPassword;
    }

    @JsonProperty
    public String getCustomMessage() {
        return customMessage;
    }

    @JsonProperty
    public UUID getEmergencyContact() {
        return emergencyContact;
    }
}
