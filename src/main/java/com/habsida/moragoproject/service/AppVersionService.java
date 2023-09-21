package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.input.AppVersionCreateInput;
import com.habsida.moragoproject.model.input.AppVersionUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AppVersionService {

    List<AppVersion> getAll ();
    Page<AppVersion> getAllByPaging (PageRequest pageRequest);
    AppVersion getById (Long id);
    AppVersion create (AppVersionCreateInput appVersionCreateInput);
    AppVersion update (AppVersionUpdateInput appVersionUpdateInput);
    Boolean deleteById (Long id);
}
