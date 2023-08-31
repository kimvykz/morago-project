package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Language;

import java.util.List;

public interface LanguageService {
    List<Language> getAllItems();
    Language getItemById(Long id);
    Language createItem(Language language);
    Language modifyItem(Language language);
    void removeItem(Long id);
}
