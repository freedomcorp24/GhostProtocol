package org.whispersystems.textsecuregcm.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsernameRegistrationRequest {
    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    public UsernameRegistrationRequest() {}

    public UsernameRegistrationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
