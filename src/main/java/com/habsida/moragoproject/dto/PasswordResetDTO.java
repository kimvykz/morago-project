package com.habsida.moragoproject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PasswordResetDTO {
    private String phone;
    private Integer resetCode;
    private String token;
}
