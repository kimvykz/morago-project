package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.input.TranslatorProfileCreateInput;
import com.habsida.moragoproject.model.input.TranslatorProfileUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TranslatorProfileService {
    List<TranslatorProfile> getAll ();
    Page<TranslatorProfile> getAllByPaging (PageRequest pageRequest);
    TranslatorProfile getById (Long id);
    TranslatorProfile create (TranslatorProfileCreateInput translatorProfileCreateInput);
    TranslatorProfile update (TranslatorProfileUpdateInput translatorProfileUpdateInput);
    Boolean deleteById (Long id);
}
