package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ResetCodeHashInput {
    private String phone;
    private Integer hashcode;
    private LocalDateTime expirationDate;
    private Long passwordResetId;
}
