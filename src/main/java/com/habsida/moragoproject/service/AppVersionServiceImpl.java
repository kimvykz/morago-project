package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.AppVersion;
import com.habsida.moragoproject.repository.AppVersionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppVersionServiceImpl implements AppVersionService{

    private AppVersionRepository appVersionRepository;

    public AppVersionServiceImpl(AppVersionRepository appVersionRepository) {
        this.appVersionRepository = appVersionRepository;
    }

    @Override
    public List<AppVersion> getAllItems() {
        return appVersionRepository.findAll();
    }

    @Override
    public AppVersion getItemById(Long id) {
        Optional<AppVersion> appVersion = appVersionRepository.findById(id);
        if (appVersion.isPresent()) {
            return appVersion.get();
        }
        throw new RuntimeException("AppVersion is not found for the id - " + id);
    }

    @Override
    public AppVersion createItem(AppVersion appVersion) {
        return appVersionRepository.save(appVersion);
    }

    @Override
    public AppVersion modifyItem(AppVersion appVersion) {
        return appVersionRepository.save(appVersion);
    }

    @Override
    public void removeItem(Long id) {
        appVersionRepository.deleteById(id);
    }
}
