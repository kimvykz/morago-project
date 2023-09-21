package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PasswordResetUpdateInput {
    private Long id;
    private String phone;
    private Integer resetCode;
    private String token;
}
