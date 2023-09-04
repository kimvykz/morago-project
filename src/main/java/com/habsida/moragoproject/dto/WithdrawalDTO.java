package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.entity.User;
import com.habsida.moragoproject.enums.EStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@ToString
@Getter
@Setter
public class WithdrawalDTO {
    private String accountHolder;
    private String accountNumber;
    private String nameOfBank;
    private EStatus status;
    private Double sum;

    //private User user;
}
