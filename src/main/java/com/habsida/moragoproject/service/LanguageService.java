package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.input.CreateFileInput;
import com.habsida.moragoproject.model.input.CreateLanguageInput;
import com.habsida.moragoproject.model.input.UpdateLanguageInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LanguageService {
    List<Language> getAll ();
    Page<Language> getAllByPaging (PageRequest pageRequest);
    Language getById (Long id);
    Language create (CreateLanguageInput createLanguageInput);
    Language update (UpdateLanguageInput updateLanguageInput);
    Boolean deleteById (Long id);
}
