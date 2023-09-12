package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.input.CreateTranslatorProfileInput;
import com.habsida.moragoproject.model.input.UpdateTranslatorProfileInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TranslatorProfileService {
    List<TranslatorProfile> getAll ();
    Page<TranslatorProfile> getAllPaged (PageRequest pageRequest);
    TranslatorProfile getById (Long id);
    TranslatorProfile create (CreateTranslatorProfileInput createTranslatorProfileInput);
    TranslatorProfile update (UpdateTranslatorProfileInput updateTranslatorProfileInput);
    Boolean deleteById (Long id);
}
