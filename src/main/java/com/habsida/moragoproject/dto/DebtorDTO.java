package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class DebtorDTO {
    private String accountHolder;
    private Boolean isPaid;
    private String nameOfBank;

    //private User user;
}
