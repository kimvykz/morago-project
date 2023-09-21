package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Role;
import com.habsida.moragoproject.model.enums.ERole;
import com.habsida.moragoproject.model.input.RoleCreateInput;
import com.habsida.moragoproject.model.input.RoleUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RoleService {
    List<Role> getAll ();
    Page<Role> getAllByPaging (PageRequest pageRequest);
    Role getById (Long id);
    Role create (RoleCreateInput roleCreateInput);
    Role update (RoleUpdateInput roleUpdateInput);
    Boolean deleteById (Long id);
    Role getByName (ERole name);
}
