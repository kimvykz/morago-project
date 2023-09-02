package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.TranslatorProfile;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void deleteById (Long id) {
        translatorProfileRepository.deleteById(id);
    }
}
