package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.TranslatorProfile;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;

import java.util.List;
import java.util.Optional;

public class TranslatorProfileServiceImpl implements TranslatorProfileService{
    private TranslatorProfileRepository translatorProfileRepository;

    public TranslatorProfileServiceImpl(TranslatorProfileRepository translatorProfileRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
    }

    @Override
    public List<TranslatorProfile> getAllItems() {
        return translatorProfileRepository.findAll();
    }

    @Override
    public TranslatorProfile getItemById(Long id) {
        Optional<TranslatorProfile> translatorProfile = translatorProfileRepository.findById(id);
        if (translatorProfile.isPresent()) {
            return translatorProfile.get();
        }
        throw new RuntimeException("TranslatorProfile is not found for the id - " + id);
    }

    @Override
    public TranslatorProfile createItem(TranslatorProfile translatorProfile) {
        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public TranslatorProfile modifyItem(TranslatorProfile translatorProfile) {
        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public void removeItem(Long id) {
        translatorProfileRepository.deleteById(id);
    }
}
