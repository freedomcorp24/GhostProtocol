package org.ghostprotocol.service.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class DecoyContact {
    private final UUID id;
    private final String name;
    private final String phoneNumber;

    public DecoyContact(
            @JsonProperty("id") UUID id,
            @JsonProperty("name") String name,
            @JsonProperty("phoneNumber") String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty
    public UUID getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
