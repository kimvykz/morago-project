package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.enums.ERole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UpdateRoleInput {
    private ERole name;
}
