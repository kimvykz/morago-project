package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LanguageService {
    List<Language> getAll();
    Page<Language> getAllPaged(PageRequest pageRequest);
    Language getById(Long id);
    Language create(Language language);
    Language update(Language language);
    Boolean deleteById(Long id);
}
