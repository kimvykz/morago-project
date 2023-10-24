package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PasswordUpdateInput {
    private String oldPassword;
    private String newPassword;
}
