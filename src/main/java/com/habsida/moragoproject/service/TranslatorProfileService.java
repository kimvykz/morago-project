package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.TranslatorProfile;

import java.util.List;

public interface TranslatorProfileService {
    List<TranslatorProfile> getAllItems();
    TranslatorProfile getItemById(Long id);
    TranslatorProfile createItem(TranslatorProfile translatorProfile);
    TranslatorProfile modifyItem(TranslatorProfile translatorProfile);
    void removeItem(Long id);
}
