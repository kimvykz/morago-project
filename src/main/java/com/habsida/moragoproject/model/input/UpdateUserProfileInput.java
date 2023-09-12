package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserProfileInput {
    private Long id;
    private Boolean isFreeCallMade;

    //private User user;
}
