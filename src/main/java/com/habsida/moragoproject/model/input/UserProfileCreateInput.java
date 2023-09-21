package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserProfileCreateInput {
    private Boolean isFreeCallMade;

    private User user;
}
