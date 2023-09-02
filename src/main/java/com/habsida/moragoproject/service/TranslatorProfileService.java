package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.TranslatorProfile;

import java.util.List;

public interface TranslatorProfileService {
    List<TranslatorProfile> getAll ();
    TranslatorProfile getById (Long id);
    TranslatorProfile create (TranslatorProfile translatorProfile);
    TranslatorProfile update (TranslatorProfile translatorProfile);
    void deleteById (Long id);
}
