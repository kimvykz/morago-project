package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class RatingCreateInput {
    private Double grade;

    private User raterUser;
    private User assessedUser;

}
