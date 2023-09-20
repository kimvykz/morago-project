package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
public class AuthenticationInput {
    @NotBlank(message = "phone cannot be blank")
    private String phone;
    @NotBlank(message = "password cannot be blank")
    private String password;

}
