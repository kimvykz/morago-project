package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.TranslatorProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TranslatorProfileService {
    List<TranslatorProfile> getAll ();
    Page<TranslatorProfile> getAllPaged (PageRequest pageRequest);
    TranslatorProfile getById (Long id);
    TranslatorProfile create (TranslatorProfile translatorProfile);
    TranslatorProfile update (TranslatorProfile translatorProfile);
    Boolean deleteById (Long id);
}
