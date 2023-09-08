package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class RegisterNewUserInput {
    private String phone;
    private String password;
    private String firstName;
    private String lastName;
    private List<Role> roles;

}
