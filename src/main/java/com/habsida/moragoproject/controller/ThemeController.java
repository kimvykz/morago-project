package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.ThemeCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.ThemeUpdateInput;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.service.ThemeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class ThemeController {

    private ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @QueryMapping(name = "getThemes")
    public Iterable<Theme> getAll (){
        return themeService.getAll();
    }

    @QueryMapping(name = "getThemeById")
    public Theme getById (@Argument Long id) {
        return themeService.getById(id);
    }

    @QueryMapping(name = "getThemesByPaging")
    public Page<Theme> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return themeService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createTheme")
    public Theme create (@Valid @Argument(name = "themeInput") ThemeCreateInput themeCreateInput) {
        return themeService.create(themeCreateInput);
    }

    @MutationMapping(name = "updateTheme")
    public Theme update (@Valid @Argument(name = "themeInput") ThemeUpdateInput themeUpdateInput) {
        return themeService.update(themeUpdateInput);
    }

    @MutationMapping(name = "deleteThemeById")
    public Boolean deleteById (@Argument Long id) {
        return themeService.deleteById(id);
    }


}
