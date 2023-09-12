package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Language;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.model.input.CreateTranslatorProfileInput;
import com.habsida.moragoproject.model.input.UpdateTranslatorProfileInput;
import com.habsida.moragoproject.repository.TranslatorProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
public class TranslatorProfileServiceImpl implements TranslatorProfileService{
    private TranslatorProfileRepository translatorProfileRepository;
    private ModelMapper modelMapper;

    public TranslatorProfileServiceImpl (TranslatorProfileRepository translatorProfileRepository, ModelMapper modelMapper) {
        this.translatorProfileRepository = translatorProfileRepository;
        this.modelMapper = modelMapper;
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
    public TranslatorProfile create (CreateTranslatorProfileInput createTranslatorProfileInput) {
        TranslatorProfile translatorProfile = modelMapper.map(createTranslatorProfileInput, TranslatorProfile.class);

        if (translatorProfile.getDateOfBirth() == null) {
            throw new IllegalArgumentException("field dateOfBirth cannot be null");
        }
        if (translatorProfile.getEmail() == null) {
            throw new IllegalArgumentException("field email cannot be null");
        }
        if (translatorProfile.getIsAvailable() == null) {
            throw new IllegalArgumentException("field isAvailable cannot be null");
        }
        if (translatorProfile.getIsOnline() == null) {
            throw new IllegalArgumentException("field isOnline cannot be null");
        }
        if (translatorProfile.getLevelOfKorean() == null) {
            throw new IllegalArgumentException("field levelOfKorean cannot be null");
        }
        if (translatorProfile.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }
        if (translatorProfile.getLanguages() == null) {
            throw new IllegalArgumentException("field languages cannot be null");
        }
        if (translatorProfile.getThemes() == null) {
            throw new IllegalArgumentException("field themes cannot be null");
        }

        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public TranslatorProfile update (UpdateTranslatorProfileInput updateTranslatorProfileInput) {
        TranslatorProfile translatorProfile = getById(updateTranslatorProfileInput.getId());
        modelMapper.map(updateTranslatorProfileInput, translatorProfile);
        if (translatorProfile.getDateOfBirth() == null) {
            throw new IllegalArgumentException("field dateOfBirth cannot be null");
        }
        if (translatorProfile.getEmail() == null) {
            throw new IllegalArgumentException("field email cannot be null");
        }
        if (translatorProfile.getIsAvailable() == null) {
            throw new IllegalArgumentException("field isAvailable cannot be null");
        }
        if (translatorProfile.getIsOnline() == null) {
            throw new IllegalArgumentException("field isOnline cannot be null");
        }
        if (translatorProfile.getLevelOfKorean() == null) {
            throw new IllegalArgumentException("field levelOfKorean cannot be null");
        }
        if (translatorProfile.getUser() == null) {
            throw new IllegalArgumentException("field user cannot be null");
        }
        if (translatorProfile.getLanguages() == null) {
            throw new IllegalArgumentException("field languages cannot be null");
        }
        if (translatorProfile.getThemes() == null) {
            throw new IllegalArgumentException("field themes cannot be null");
        }
        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public Boolean deleteById (Long id) {
        translatorProfileRepository.deleteById(id);
        return true;
    }
}
