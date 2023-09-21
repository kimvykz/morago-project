package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class DebtorCreateInput {
    private String accountHolder;
    private Boolean isPaid;
    private String nameOfBank;

    private User user;
}
