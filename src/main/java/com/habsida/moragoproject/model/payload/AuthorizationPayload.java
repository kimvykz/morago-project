package com.habsida.moragoproject.model.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthorizationPayload {
    private String accessToken;
    private String refreshToken;

    public AuthorizationPayload(String accessToken,
                                String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
