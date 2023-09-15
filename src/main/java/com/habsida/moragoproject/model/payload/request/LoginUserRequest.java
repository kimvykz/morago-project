package com.habsida.moragoproject.model.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginUserRequest {
    private String phone;
    private String password;
}
