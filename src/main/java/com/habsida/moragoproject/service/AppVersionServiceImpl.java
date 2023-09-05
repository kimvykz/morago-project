package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.repository.AppVersionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppVersionServiceImpl implements AppVersionService{

    private AppVersionRepository appVersionRepository;

    public AppVersionServiceImpl(AppVersionRepository appVersionRepository) {
        this.appVersionRepository = appVersionRepository;
    }

    @Override
    public List<AppVersion> getAll() {
        return appVersionRepository.findAll();
    }

    @Override
    public Page<AppVersion> getAllPaged(PageRequest pageRequest) {
        return appVersionRepository.findAll(pageRequest);
    }

    @Override
    public AppVersion getById(Long id) {
        return appVersionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("AppVersion is not found for the id - " + id);
                });
    }

    @Override
    public AppVersion create(AppVersion appVersion) {
        return appVersionRepository.save(appVersion);
    }

    @Override
    public AppVersion update(AppVersion appVersion) {
        return appVersionRepository.save(appVersion);
    }

    @Override
    public Boolean deleteById(Long id) {
        appVersionRepository.deleteById(id);
        return true;
    }
}
