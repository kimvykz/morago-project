package com.habsida.moragoproject.model.payload;

import com.habsida.moragoproject.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginPayload {
    private User user;
    private String token;

    public LoginPayload(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
