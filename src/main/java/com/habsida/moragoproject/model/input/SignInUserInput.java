package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInUserInput {
    private String phone;
    private String password;
}
