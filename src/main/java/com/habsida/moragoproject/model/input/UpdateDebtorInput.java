package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UpdateDebtorInput {
    private Long id;
    private String accountHolder;
    private Boolean isPaid;
    private String nameOfBank;

    //private User user;
}
