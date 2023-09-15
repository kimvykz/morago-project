package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class UpdateUserRolesInput {
    private Long userId;
    private List<Role> roles;
}
