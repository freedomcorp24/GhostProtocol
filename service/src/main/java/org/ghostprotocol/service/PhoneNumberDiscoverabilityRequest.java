package org.ghostprotocol.ghostprotocol.entities;

import jakarta.validation.constraints.NotNull;

public record UsernameDiscoverabilityRequest(@NotNull Boolean discoverableByUsername) {}
