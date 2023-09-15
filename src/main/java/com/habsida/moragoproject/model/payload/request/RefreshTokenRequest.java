package com.habsida.moragoproject.model.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;

}
