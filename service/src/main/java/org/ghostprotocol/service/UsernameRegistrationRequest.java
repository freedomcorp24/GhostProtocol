package org.ghostprotocol.ghostprotocol.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class UsernameRegistrationRequest extends RegistrationRequest {
  @JsonProperty
  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z0-9_-]{3,32}$")
  private String username;

  @JsonProperty
  @NotEmpty
  @Pattern(regexp = "^[a-zA-Z0-9_-]{3,32}$")
  private String publicUsername;

  public String getUsername() {
    return username;
  }

  public String getPublicUsername() {
    return publicUsername;
  }
}
