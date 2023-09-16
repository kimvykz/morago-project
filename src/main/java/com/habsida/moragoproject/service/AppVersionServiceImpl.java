package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.model.input.CreateAppVersionInput;
import com.habsida.moragoproject.model.input.UpdateAppVersionInput;
import com.habsida.moragoproject.repository.AppVersionRepository;
import org.modelmapper.ModelMapper;
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
    public AppVersion create (CreateAppVersionInput createAppVersionInput) {

        AppVersion appVersion = new AppVersion();

        if (createAppVersionInput.getPlatform() == null) {
            throw new IllegalArgumentException("field platform cannot be empty");
        } else {
            appVersion.setPlatform(createAppVersionInput.getPlatform());
        }
        if (createAppVersionInput.getLatest() == null
                || appVersion.getLatest().isBlank()) {
            throw new IllegalArgumentException("field latest cannot be empty");
        } else {
            appVersion.setLatest(createAppVersionInput.getLatest());
        }
        if (createAppVersionInput.getMin() == null
                || appVersion.getLatest().isBlank()) {
            throw new IllegalArgumentException("field min cannot be empty");
        } else {
            appVersion.setMin(createAppVersionInput.getMin());
        }
        return appVersionRepository.save(appVersion);
    }

    @Override
    public AppVersion update (UpdateAppVersionInput updateAppVersionInput) {

        AppVersion appVersion = getById(updateAppVersionInput.getId());

        if (updateAppVersionInput.getPlatform() != null
                && !appVersion.getPlatform().equals(updateAppVersionInput.getPlatform())) {
            appVersion.setPlatform(updateAppVersionInput.getPlatform());
        }
        if (updateAppVersionInput.getLatest() != null
                && !updateAppVersionInput.getLatest().isBlank()
                && !appVersion.getLatest().equals(updateAppVersionInput.getLatest())) {
            appVersion.setLatest(updateAppVersionInput.getLatest());
        }
        if (updateAppVersionInput.getMin() != null
                && !appVersion.getLatest().isBlank()
                && !appVersion.getMin().equals(updateAppVersionInput.getMin())) {
            appVersion.setMin(updateAppVersionInput.getMin());
        }
        return appVersionRepository.save(appVersion);
    }

    @Override
    public Boolean deleteById (Long id) {
        appVersionRepository.deleteById(id);
        return true;
    }
}
