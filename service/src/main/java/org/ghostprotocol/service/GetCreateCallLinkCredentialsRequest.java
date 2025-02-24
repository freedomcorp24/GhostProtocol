package org.ghostprotocol.ghostprotocol.entities;

import jakarta.validation.constraints.NotEmpty;


public record GetCreateCallLinkCredentialsRequest(@NotEmpty byte[] createCallLinkCredentialRequest) {}
