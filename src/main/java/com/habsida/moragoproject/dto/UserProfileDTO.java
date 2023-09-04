package com.habsida.moragoproject.dto;

import com.habsida.moragoproject.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@ToString
public class UserProfileDTO {
    private Boolean isFreeCallMade;

    //private User user;
}
