package com.habsida.moragoproject.model.payload.response;

import com.habsida.moragoproject.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginPayloadResponse {
    private User user;
    private String token;
    private String refreshToken;

    public LoginPayloadResponse(User user,
                                String token,
                                String refreshToken) {
        this.user = user;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}
