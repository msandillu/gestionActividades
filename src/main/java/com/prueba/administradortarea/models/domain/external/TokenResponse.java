package com.prueba.administradortarea.models.domain.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TokenResponse {

    private String token;

    @JsonProperty("expires_in")
    private int expiresIn;

    public String getToken() {
        return token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }
}
