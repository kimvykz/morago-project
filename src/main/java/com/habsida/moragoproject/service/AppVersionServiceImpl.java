package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.input.AppVersionCreateInput;
import com.habsida.moragoproject.model.input.AppVersionUpdateInput;
import com.habsida.moragoproject.repository.AppVersionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppVersionServiceImpl implements AppVersionService{

    private AppVersionRepository appVersionRepository;

    public AppVersionServiceImpl (AppVersionRepository appVersionRepository) {
        this.appVersionRepository = appVersionRepository;
    }

    @Override
    public List<AppVersion> getAll () {
        return appVersionRepository.findAll();
    }

    @Override
    public Page<AppVersion> getAllByPaging (PageRequest pageRequest) {
        return appVersionRepository.findAll(pageRequest);
    }

    @Override
    public AppVersion getById (Long id) {
        return appVersionRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("AppVersion is not found for the id - " + id);
                });
    }

    @Override
    public AppVersion create (AppVersionCreateInput appVersionCreateInput) {

        AppVersion appVersion = new AppVersion();

        if (appVersionCreateInput.getPlatform() == null) {
            throw new IllegalArgumentException("field platform cannot be empty");
        } else {
            appVersion.setPlatform(appVersionCreateInput.getPlatform());
        }
        if (appVersionCreateInput.getLatest() == null
                || appVersion.getLatest().isBlank()) {
            throw new IllegalArgumentException("field latest cannot be empty");
        } else {
            appVersion.setLatest(appVersionCreateInput.getLatest());
        }
        if (appVersionCreateInput.getMin() == null
                || appVersion.getLatest().isBlank()) {
            throw new IllegalArgumentException("field min cannot be empty");
        } else {
            appVersion.setMin(appVersionCreateInput.getMin());
        }
        return appVersionRepository.save(appVersion);
    }

    @Override
    public AppVersion update (AppVersionUpdateInput appVersionUpdateInput) {

        AppVersion appVersion = getById(appVersionUpdateInput.getId());

        if (appVersionUpdateInput.getPlatform() != null
                && !appVersion.getPlatform().equals(appVersionUpdateInput.getPlatform())) {
            appVersion.setPlatform(appVersionUpdateInput.getPlatform());
        }
        if (appVersionUpdateInput.getLatest() != null
                && !appVersionUpdateInput.getLatest().isBlank()
                && !appVersion.getLatest().equals(appVersionUpdateInput.getLatest())) {
            appVersion.setLatest(appVersionUpdateInput.getLatest());
        }
        if (appVersionUpdateInput.getMin() != null
                && !appVersion.getLatest().isBlank()
                && !appVersion.getMin().equals(appVersionUpdateInput.getMin())) {
            appVersion.setMin(appVersionUpdateInput.getMin());
        }
        return appVersionRepository.save(appVersion);
    }

    @Override
    public Boolean deleteById (Long id) {
        appVersionRepository.deleteById(id);
        return true;
    }
}
