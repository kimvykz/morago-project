package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.AppVersion;

import java.util.List;

public interface AppVersionService {

    List<AppVersion> getAllItems();
    AppVersion getItemById(Long id);
    AppVersion createItem(AppVersion appVersion);
    AppVersion modifyItem(AppVersion appVersion);
    void removeItem(Long id);
}
