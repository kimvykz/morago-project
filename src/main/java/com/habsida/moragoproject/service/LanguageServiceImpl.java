package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.input.CreateLanguageInput;
import com.habsida.moragoproject.model.input.UpdateLanguageInput;
import com.habsida.moragoproject.repository.LanguageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService{
    private LanguageRepository languageRepository;
    private ModelMapper modelMapper;

    public LanguageServiceImpl(LanguageRepository languageRepository, ModelMapper modelMapper) {
        this.languageRepository = languageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Page<Language> getAllPaged(PageRequest pageRequest) {
        return languageRepository.findAll(pageRequest);
    }

    @Override
    public Language getById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Language is not found for the id - " + id);
        });
    }

    @Override
    public Language create(CreateLanguageInput createLanguageInput) {
        Language language = modelMapper.map(createLanguageInput, Language.class);
        return languageRepository.save(language);
    }

    @Override
    public Language update(UpdateLanguageInput updateLanguageInput) {
        Language language = getById(updateLanguageInput.getId());
        modelMapper.map(updateLanguageInput, language);
        return languageRepository.save(language);
    }

    @Override
    public Boolean deleteById(Long id) {
        languageRepository.deleteById(id);
        return true;
    }
}
