package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.input.LanguageCreateInput;
import com.habsida.moragoproject.model.input.LanguageUpdateInput;
import com.habsida.moragoproject.repository.LanguageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService{
    private LanguageRepository languageRepository;

    public LanguageServiceImpl (LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAll () {
        return languageRepository.findAll();
    }

    @Override
    public Page<Language> getAllByPaging (PageRequest pageRequest) {
        return languageRepository.findAll(pageRequest);
    }

    @Override
    public Language getById (Long id) {
        return languageRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Language is not found for the id - " + id);
        });
    }

    @Override
    public Language create (LanguageCreateInput languageCreateInput) {
        Language language = new Language();
        if (languageCreateInput.getName() == null
            || languageCreateInput.getName().isBlank()) {
            throw new IllegalArgumentException("field name cannot be null");
        } else {
            language.setName(languageCreateInput.getName());
        }
        return languageRepository.save(language);
    }

    @Override
    public Language update (LanguageUpdateInput languageUpdateInput) {
        Language language = getById(languageUpdateInput.getId());

        if (languageUpdateInput.getName() != null
            && !languageUpdateInput.getName().isBlank()
            && !language.getName().equals(languageUpdateInput.getName())) {
            language.setName(languageUpdateInput.getName());
        }
        return languageRepository.save(language);
    }

    @Override
    public Boolean deleteById (Long id) {
        languageRepository.deleteById(id);
        return true;
    }
}
