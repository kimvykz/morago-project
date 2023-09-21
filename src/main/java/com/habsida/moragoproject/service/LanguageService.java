package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.input.LanguageCreateInput;
import com.habsida.moragoproject.model.input.LanguageUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LanguageService {
    List<Language> getAll ();
    Page<Language> getAllByPaging (PageRequest pageRequest);
    Language getById (Long id);
    Language create (LanguageCreateInput languageCreateInput);
    Language update (LanguageUpdateInput languageUpdateInput);
    Boolean deleteById (Long id);
}
