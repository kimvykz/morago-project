package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Language;

import java.util.List;

public interface LanguageService {
    List<Language> getAll();
    Language getById(Long id);
    Language create(Language language);
    Language update(Language language);
    void deleteById(Long id);
}
