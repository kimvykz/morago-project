package com.habsida.moragoproject.model.input;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserApnTokenInput {
    private Long userId;
    private String apnToken;
}
