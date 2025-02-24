package org.ghostprotocol.service.admin;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

public class AdminRoleRecord {
    private final UUID userId;
    private final AdminRole role;
    private final UUID assignedBy;
    private final Instant assignedAt;

    public AdminRoleRecord(
            @JsonProperty("userId") UUID userId,
            @JsonProperty("role") AdminRole role,
            @JsonProperty("assignedBy") UUID assignedBy,
            @JsonProperty("assignedAt") Instant assignedAt) {
        this.userId = userId;
        this.role = role;
        this.assignedBy = assignedBy;
        this.assignedAt = assignedAt;
    }

    @JsonProperty
    public UUID userId() {
        return userId;
    }

    @JsonProperty
    public AdminRole role() {
        return role;
    }

    @JsonProperty
    public UUID assignedBy() {
        return assignedBy;
    }

    @JsonProperty
    public Instant assignedAt() {
        return assignedAt;
    }
}
