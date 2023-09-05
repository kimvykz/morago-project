package com.habsida.moragoproject.model.dto;

import com.habsida.moragoproject.model.enums.ERole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class CreateRoleInput {
    private ERole name;
}
