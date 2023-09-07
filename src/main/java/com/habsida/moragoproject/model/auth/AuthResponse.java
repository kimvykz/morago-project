package com.habsida.moragoproject.model.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer ";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
