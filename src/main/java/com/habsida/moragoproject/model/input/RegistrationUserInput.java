package com.habsida.moragoproject.model.input;

import com.habsida.moragoproject.model.entity.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@ToString
@Getter
@Setter
public class RegistrationUserInput {
    @NotBlank(message = "phone cannot be blank")
    private String phone;
    @NotBlank(message = "password cannot be blank")
    private String password;
    @NotNull(message = "user must have at least 1 role")
    private List<Role> roles;
}
