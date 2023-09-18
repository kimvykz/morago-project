package com.habsida.moragoproject.model.payload.request;

import com.habsida.moragoproject.model.entity.TranslatorProfile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
public class RegistrationTranslatorRequest {
    @NotBlank(message = "Phone cannot be blank")
    private String phone;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotNull(message = "Translator profile cannot be null for Translator")
    private TranslatorProfile translatorProfile;

}
