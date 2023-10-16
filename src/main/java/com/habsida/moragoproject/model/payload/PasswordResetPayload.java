package com.habsida.moragoproject.model.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PasswordResetPayload {
    private Long passwordResetId;
    private Long timeCode;
    private String token;
}
