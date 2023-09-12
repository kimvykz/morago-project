package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UpdateCoinInput {
    private Long id;
    private Double coin;
    private Double won;
}
