package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.AppVersionCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.AppVersionUpdateInput;
import com.habsida.moragoproject.model.entity.AppVersion;
import com.habsida.moragoproject.service.AppVersionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class AppVersionController {


    private AppVersionService appVersionService;

    public AppVersionController(AppVersionService appVersionService) {
        this.appVersionService = appVersionService;
    }

    @QueryMapping(name = "getAppVersions")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Iterable<AppVersion> getAll (){
        return appVersionService.getAll();
    }

    @QueryMapping(name = "getAppVersionById")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public AppVersion getById (@Argument Long id) {
        return appVersionService.getById(id);
    }

    @QueryMapping(name = "getAppVersionsByPaging")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public Page<AppVersion> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return appVersionService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createAppVersion")
    @PreAuthorize("hasAnyRole( 'ROLE_ADMIN')")
    public AppVersion create (@Valid @Argument(name = "appVersionInput") AppVersionCreateInput appVersionCreateInput) {

        return appVersionService.create(appVersionCreateInput);
    }

    @MutationMapping(name = "updateAppVersion")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public AppVersion update (@Valid @Argument(name = "appVersionInput") AppVersionUpdateInput appVersionUpdateInput) {

        return appVersionService.update(appVersionUpdateInput);
    }

    @MutationMapping(name = "deleteAppVersionById")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Boolean deleteById (@Argument Long id) {
        return appVersionService.deleteById(id);
    }


}
