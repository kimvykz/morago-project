package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Language;
import com.habsida.moragoproject.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

public class LanguageServiceImpl implements LanguageService{
    private LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAllItems() {
        return languageRepository.findAll();
    }

    @Override
    public Language getItemById(Long id) {
        Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()){
            return language.get();
        }
        throw new RuntimeException("Language is not found for the id - " + id);
    }

    @Override
    public Language createItem(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language modifyItem(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public void removeItem(Long id) {
        languageRepository.deleteById(id);
    }
}
