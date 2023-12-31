package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.LanguageCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.LanguageUpdateInput;
import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.service.LanguageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class LanguageController {

    private LanguageService languageService;

    public LanguageController (LanguageService languageService) {
        this.languageService = languageService;
    }

    @QueryMapping(name = "getLanguages")
    public Iterable<Language> getAll (){
        return languageService.getAll();
    }

    @QueryMapping(name = "getLanguageById")
    public Language getById (@Argument Long id) {
        return languageService.getById(id);
    }

    @QueryMapping(name = "getLanguagesByPaging")
    public Page<Language> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return languageService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createLanguage")
    public Language create (@Valid @Argument(name = "languageInput") LanguageCreateInput languageCreateInput) {
        return languageService.create(languageCreateInput);
    }

    @MutationMapping(name = "updateLanguage")
    public Language update (@Valid @Argument(name = "languageInput") LanguageUpdateInput languageUpdateInput) {
        return languageService.update(languageUpdateInput);
    }

    @MutationMapping(name = "deleteLanguageById")
    public Boolean deleteById (@Argument Long id) {
        return languageService.deleteById(id);
    }


}
