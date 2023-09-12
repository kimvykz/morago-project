package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.CreateRoleInput;
import com.habsida.moragoproject.model.input.UpdateRoleInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RoleService {
    List<Role> getAll ();
    Page<Role> getAllPaged(PageRequest pageRequest);
    Role getById (Long id);
    Role create (CreateRoleInput createRoleInput);
    Role update (UpdateRoleInput updateRoleInput);
    Boolean deleteById (Long id);
    Role getByName (ERole name);
}
