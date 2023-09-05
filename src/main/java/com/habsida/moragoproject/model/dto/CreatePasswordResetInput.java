package com.habsida.moragoproject.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CreatePasswordResetInput {
    private String phone;
    private Integer resetCode;
    private String token;
}