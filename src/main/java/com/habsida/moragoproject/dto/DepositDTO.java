package com.habsida.moragoproject.dto;


import com.habsida.moragoproject.enums.EStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepositDTO {
    private String accountHolder;
    private Double coin;
    private String nameOfBank;
    private EStatus eStatus;
    private Double won;

    //private User user;
}
