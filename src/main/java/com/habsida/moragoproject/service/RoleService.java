package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.enums.ERole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RoleService {
    List<Role> getAll ();
    Page<Role> getAllPaged(PageRequest pageRequest);
    Role getById (Long id);
    Role create (Role role);
    Role update (Role role);
    Boolean deleteById (Long id);
    Role getByName (ERole name);
}
