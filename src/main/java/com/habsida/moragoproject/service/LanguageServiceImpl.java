package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Language;
import com.habsida.moragoproject.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageServiceImpl implements LanguageService{
    private LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language getById(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Language is not found for the id - " + id);
        });
    }

    @Override
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language update(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public void deleteById(Long id) {
        languageRepository.deleteById(id);
    }
}
