package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.AppVersion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface AppVersionService {

    List<AppVersion> getAll ();
    Page<AppVersion> getAllPaged (PageRequest pageRequest);
    AppVersion getById (Long id);
    AppVersion create (AppVersion appVersion);
    AppVersion update (AppVersion appVersion);
    Boolean deleteById (Long id);
}
