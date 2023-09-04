package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.LanguageDTO;
import com.habsida.moragoproject.entity.Language;
import com.habsida.moragoproject.service.LanguageService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class LanguageGraphQLController {
    private ModelMapper modelMapper;

    private LanguageService languageService;

    public LanguageGraphQLController(ModelMapper modelMapper,
                                     LanguageService languageService) {
        this.modelMapper = modelMapper;
        this.languageService = languageService;
    }

    @QueryMapping(name = "getLanguage")
    public Iterable<Language> getAll (){
        return languageService.getAll();
    }

    @QueryMapping(name = "getLanguageById")
    public Language getById (@Argument Long id) {
        return languageService.getById(id);
    }

    @QueryMapping(name = "getLanguagesPaged")
    public Page<Language> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return languageService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createLanguage")
    public Language create (@Valid @Argument(name = "language") LanguageDTO languageDTO) {
        Language language = modelMapper.map(languageDTO, Language.class);
        return languageService.create(language);
    }

    @MutationMapping(name = "updateLanguageById")
    public Language updateById (@Valid @Argument Long id,
                              @Argument(name = "language") LanguageDTO languageDTO) {
        Language language = languageService.getById(id);
        modelMapper.map(languageDTO, language);
        return languageService.update(language);
    }

    @MutationMapping(name = "deleteLanguageById")
    public Boolean deleteById (@Argument Long id) {
        return languageService.deleteById(id);
    }


}
