package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslatorProfileServiceImpl implements TranslatorProfileService{
    private TranslatorProfileRepository translatorProfileRepository;

    public TranslatorProfileServiceImpl (TranslatorProfileRepository translatorProfileRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
    }

    @Override
    public List<TranslatorProfile> getAll () {
        return translatorProfileRepository.findAll();
    }

    @Override
    public Page<TranslatorProfile> getAllPaged(PageRequest pageRequest) {
        return translatorProfileRepository.findAll(pageRequest);
    }

    @Override
    public TranslatorProfile getById (Long id) {
        return translatorProfileRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("TranslatorProfile is not found for the id - " + id);
        });
    }

    @Override
    public TranslatorProfile create (TranslatorProfile translatorProfile) {
        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public TranslatorProfile update (TranslatorProfile translatorProfile) {
        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public Boolean deleteById (Long id) {
        translatorProfileRepository.deleteById(id);
        return true;
    }
}
