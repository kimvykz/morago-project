package com.habsida.moragoproject.model.input;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class UpdateRatingInput {
    private Long id;
    private Double grade;

    //private User user;
}
