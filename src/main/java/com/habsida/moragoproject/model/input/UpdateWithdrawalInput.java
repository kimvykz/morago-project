package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.enums.EStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UpdateWithdrawalInput {
    private String accountHolder;
    private String accountNumber;
    private String nameOfBank;
    private EStatus status;
    private Double sum;

    //private User user;
}
