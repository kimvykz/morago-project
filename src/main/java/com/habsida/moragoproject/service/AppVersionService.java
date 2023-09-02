package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.AppVersion;

import java.util.List;

public interface AppVersionService {

    List<AppVersion> getAll();
    AppVersion getById(Long id);
    AppVersion create(AppVersion appVersion);
    AppVersion update(AppVersion appVersion);
    void deleteById(Long id);
}
