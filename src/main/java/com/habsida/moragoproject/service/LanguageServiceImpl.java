package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Language;
import com.habsida.moragoproject.repository.LanguageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language update(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Boolean deleteById(Long id) {
        languageRepository.deleteById(id);
        return true;
    }
}
