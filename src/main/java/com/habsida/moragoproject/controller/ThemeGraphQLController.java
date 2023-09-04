package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.ThemeDTO;
import com.habsida.moragoproject.entity.Theme;
import com.habsida.moragoproject.service.ThemeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class ThemeGraphQLController {
    private ModelMapper modelMapper;

    private ThemeService themeService;

    public ThemeGraphQLController(ModelMapper modelMapper,
                                  ThemeService themeService) {
        this.modelMapper = modelMapper;
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

    @QueryMapping(name = "getThemesPaged")
    public Page<Theme> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return themeService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createTheme")
    public Theme create (@Valid @Argument(name = "theme") ThemeDTO themeDTO) {
        Theme theme = modelMapper.map(themeDTO, Theme.class);
        return themeService.create(theme);
    }

    @MutationMapping(name = "updateThemeById")
    public Theme updateById (@Valid @Argument Long id,
                              @Argument(name = "theme") ThemeDTO themeDTO) {
        Theme theme = themeService.getById(id);
        modelMapper.map(themeDTO, theme);
        return themeService.update(theme);
    }

    @MutationMapping(name = "deleteThemeById")
    public Boolean deleteById (@Argument Long id) {
        return themeService.deleteById(id);
    }


}
