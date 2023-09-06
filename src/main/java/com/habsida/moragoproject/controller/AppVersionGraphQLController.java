package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateAppVersionInput;
import com.habsida.moragoproject.model.input.UpdateAppVersionInput;
import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.service.AppVersionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class AppVersionGraphQLController {
    private ModelMapper modelMapper;

    private AppVersionService appVersionService;

    public AppVersionGraphQLController(ModelMapper modelMapper,
                                       AppVersionService appVersionService) {
        this.modelMapper = modelMapper;
        this.appVersionService = appVersionService;
    }

    @QueryMapping(name = "getAppVersions")
    public Iterable<AppVersion> getAll (){
        return appVersionService.getAll();
    }

    @QueryMapping(name = "getAppVersionById")
    public AppVersion getById (@Argument Long id) {
        return appVersionService.getById(id);
    }

    @QueryMapping(name = "getAppVersionsPaged")
    public Page<AppVersion> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return appVersionService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createAppVersion")
    public AppVersion create (@Valid @Argument(name = "appVersion") CreateAppVersionInput createAppVersionInput) {
        AppVersion appVersion = modelMapper.map(createAppVersionInput, AppVersion.class);
        return appVersionService.create(appVersion);
    }

    @MutationMapping(name = "updateAppVersionById")
    public AppVersion updateById (@Valid @Argument Long id,
                              @Argument(name = "appVersion") UpdateAppVersionInput updateAppVersionInput) {
        AppVersion appVersion = appVersionService.getById(id);
        modelMapper.map(updateAppVersionInput, appVersion);
        return appVersionService.update(appVersion);
    }

    @MutationMapping(name = "deleteAppVersionById")
    public Boolean deleteById (@Argument Long id) {
        return appVersionService.deleteById(id);
    }


}
