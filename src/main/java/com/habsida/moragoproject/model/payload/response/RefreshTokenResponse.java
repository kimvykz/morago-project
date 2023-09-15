package com.habsida.moragoproject.model.payload.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public RefreshTokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
