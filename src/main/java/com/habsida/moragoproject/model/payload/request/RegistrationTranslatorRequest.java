package com.habsida.moragoproject.model.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
public class RegistrationTranslatorRequest {
    @NotBlank(message = "phone cannot be blank")
    private String phone;
    @NotBlank(message = "password cannot be blank")
    private String password;

}
