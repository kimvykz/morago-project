package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.TranslatorProfileCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.TranslatorProfileUpdateInput;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.service.TranslatorProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class TranslatorProfileController {

    private TranslatorProfileService translatorProfileService;

    public TranslatorProfileController(TranslatorProfileService translatorProfileService) {
        this.translatorProfileService = translatorProfileService;
    }

    @QueryMapping(name = "getTranslatorProfiles")
    public Iterable<TranslatorProfile> getAll (){
        return translatorProfileService.getAll();
    }

    @QueryMapping(name = "getTranslatorProfileById")
    public TranslatorProfile getById (@Argument Long id) {
        return translatorProfileService.getById(id);
    }

    @QueryMapping(name = "getTranslatorProfilesByPaging")
    public Page<TranslatorProfile> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return translatorProfileService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createTranslatorProfile")
    public TranslatorProfile create (@Valid @Argument(name = "translatorProfileInput") TranslatorProfileCreateInput translatorProfileCreateInput) {
        return translatorProfileService.create(translatorProfileCreateInput);
    }

    @MutationMapping(name = "updateTranslatorProfile")
    public TranslatorProfile update (@Valid
        @Argument(name = "translatorProfileInput") TranslatorProfileUpdateInput translatorProfileUpdateInput) {
        return translatorProfileService.update(translatorProfileUpdateInput);
    }

    @MutationMapping(name = "deleteTranslatorProfileById")
    public Boolean deleteById (@Argument Long id) {
        return translatorProfileService.deleteById(id);
    }


}
