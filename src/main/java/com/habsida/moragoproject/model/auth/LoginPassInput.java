package com.habsida.moragoproject.model.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginPassInput {
    private String phone;
    private String password;
}
