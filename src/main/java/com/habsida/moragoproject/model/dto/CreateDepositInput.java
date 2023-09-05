package com.habsida.moragoproject.model.dto;


import com.habsida.moragoproject.model.enums.EStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateDepositInput {
    private String accountHolder;
    private Double coin;
    private String nameOfBank;
    private EStatus eStatus;
    private Double won;

    //private User user;
}