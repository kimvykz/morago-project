package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll ();
    Role getById (Long id);
    Role create (Role role);
    Role update (Role role);
    void deleteById (Long id);
    Role getByName (String name);
}
