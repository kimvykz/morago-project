package com.habsida.moragoproject.model.payload.response;

import com.habsida.moragoproject.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginPayloadResponse {
    private String accessToken;
    private String refreshToken;

    public LoginPayloadResponse(String accessToken,
                                String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
