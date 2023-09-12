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
import java.util.Optional;

@Service
public class AppVersionServiceImpl implements AppVersionService{

    private AppVersionRepository appVersionRepository;
    private ModelMapper modelMapper;

    public AppVersionServiceImpl (AppVersionRepository appVersionRepository, ModelMapper modelMapper) {
        this.appVersionRepository = appVersionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AppVersion> getAll () {
        return appVersionRepository.findAll();
    }

    @Override
    public Page<AppVersion> getAllPaged (PageRequest pageRequest) {
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

        AppVersion appVersion = modelMapper.map(createAppVersionInput, AppVersion.class);

        if (appVersion.getPlatform() == null) {
            throw new IllegalArgumentException("field platform cannot be empty");
        }
        if (appVersion.getLatest() == null || appVersion.getLatest().trim().isEmpty()) {
            throw new IllegalArgumentException("field latest cannot be empty");
        }
        if (appVersion.getMin() == null || appVersion.getLatest().trim().isEmpty()) {
            throw new IllegalArgumentException("field min cannot be empty");
        }
        return appVersionRepository.save(appVersion);
    }

    @Override
    public AppVersion update (UpdateAppVersionInput updateAppVersionInput) {

        AppVersion appVersion = getById(updateAppVersionInput.getId());

        modelMapper.map(updateAppVersionInput, appVersion);

        if (appVersion.getPlatform() == null) {
            throw new IllegalArgumentException("field platform cannot be empty");
        }
        if (appVersion.getLatest() == null || appVersion.getLatest().trim().isEmpty()) {
            throw new IllegalArgumentException("field latest cannot be empty");
        }
        if (appVersion.getMin() == null || appVersion.getLatest().trim().isEmpty()) {
            throw new IllegalArgumentException("field min cannot be empty");
        }
        return appVersionRepository.save(appVersion);
    }

    @Override
    public Boolean deleteById (Long id) {
        appVersionRepository.deleteById(id);
        return true;
    }
}
