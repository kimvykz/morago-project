package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.TranslatorProfileDTO;
import com.habsida.moragoproject.entity.TranslatorProfile;
import com.habsida.moragoproject.service.TranslatorProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class TranslatorProfileGraphQLController {
    private ModelMapper modelMapper;

    private TranslatorProfileService translatorProfileService;

    public TranslatorProfileGraphQLController(ModelMapper modelMapper,
                                              TranslatorProfileService translatorProfileService) {
        this.modelMapper = modelMapper;
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

    @QueryMapping(name = "getTranslatorProfilesPaged")
    public Page<TranslatorProfile> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return translatorProfileService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createTranslatorProfile")
    public TranslatorProfile create (@Valid @Argument(name = "translatorProfile") TranslatorProfileDTO translatorProfileDTO) {
        TranslatorProfile translatorProfile = modelMapper.map(translatorProfileDTO, TranslatorProfile.class);
        return translatorProfileService.create(translatorProfile);
    }

    @MutationMapping(name = "updateTranslatorProfileById")
    public TranslatorProfile updateById (@Valid @Argument Long id,
                              @Argument(name = "translatorProfile") TranslatorProfileDTO translatorProfileDTO) {
        TranslatorProfile translatorProfile = translatorProfileService.getById(id);
        modelMapper.map(translatorProfileDTO, translatorProfile);
        return translatorProfileService.update(translatorProfile);
    }

    @MutationMapping(name = "deleteTranslatorProfileById")
    public Boolean deleteById (@Argument Long id) {
        return translatorProfileService.deleteById(id);
    }


}
