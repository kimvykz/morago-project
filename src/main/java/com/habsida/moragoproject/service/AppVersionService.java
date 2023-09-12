package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.input.CreateAppVersionInput;
import com.habsida.moragoproject.model.input.UpdateAppVersionInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AppVersionService {

    List<AppVersion> getAll ();
    Page<AppVersion> getAllPaged (PageRequest pageRequest);
    AppVersion getById (Long id);
    AppVersion create (CreateAppVersionInput createAppVersionInput);
    AppVersion update (UpdateAppVersionInput updateAppVersionInput);
    Boolean deleteById (Long id);
}
