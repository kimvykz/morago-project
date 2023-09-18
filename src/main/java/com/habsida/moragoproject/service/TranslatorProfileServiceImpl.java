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

    public TranslatorProfileServiceImpl (TranslatorProfileRepository translatorProfileRepository) {
        this.translatorProfileRepository = translatorProfileRepository;
    }

    @Override
    public List<TranslatorProfile> getAll () {
        return translatorProfileRepository.findAll();
    }

    @Override
    public Page<TranslatorProfile> getAllByPaging (PageRequest pageRequest) {
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
        TranslatorProfile translatorProfile = new TranslatorProfile();

        if (createTranslatorProfileInput.getDateOfBirth() == null) {
            throw new IllegalArgumentException("field dateOfBirth cannot be null");
        } else {
            translatorProfile.setDateOfBirth(createTranslatorProfileInput.getDateOfBirth());
        }
        if (createTranslatorProfileInput.getEmail() == null
            && createTranslatorProfileInput.getEmail().isBlank()) {
            throw new IllegalArgumentException("field email cannot be null");
        } else {
            translatorProfile.setEmail(createTranslatorProfileInput.getEmail());
        }
        if (createTranslatorProfileInput.getIsAvailable() == null) {
            throw new IllegalArgumentException("field isAvailable cannot be null");
        } else {
            translatorProfile.setIsAvailable(createTranslatorProfileInput.getIsAvailable());
        }
        if (createTranslatorProfileInput.getIsOnline() == null) {
            throw new IllegalArgumentException("field isOnline cannot be null");
        } else {
            translatorProfile.setIsOnline(createTranslatorProfileInput.getIsOnline());
        }
        if (createTranslatorProfileInput.getLevelOfKorean() == null
            && createTranslatorProfileInput.getLevelOfKorean().isBlank()) {
            throw new IllegalArgumentException("field levelOfKorean cannot be null");
        } else {
            translatorProfile.setLevelOfKorean(createTranslatorProfileInput.getLevelOfKorean());
        }
//        if (createTranslatorProfileInput.getUser() == null) {
//            throw new IllegalArgumentException("field user cannot be null");
//        } else {
//            translatorProfile.setUser(createTranslatorProfileInput.getUser());
//        }
        if (createTranslatorProfileInput.getLanguages() == null) {
            throw new IllegalArgumentException("field languages cannot be null");
        } else {
            translatorProfile.setLanguages(createTranslatorProfileInput.getLanguages());
        }
        if (createTranslatorProfileInput.getThemes() == null) {
            throw new IllegalArgumentException("field themes cannot be null");
        } else {
            translatorProfile.setThemes(createTranslatorProfileInput.getThemes());
        }

        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public TranslatorProfile update (UpdateTranslatorProfileInput updateTranslatorProfileInput) {
        TranslatorProfile translatorProfile = getById(updateTranslatorProfileInput.getId());

        if (updateTranslatorProfileInput.getDateOfBirth() != null
            && !translatorProfile.getDateOfBirth().equals(updateTranslatorProfileInput.getDateOfBirth())) {
            translatorProfile.setDateOfBirth(updateTranslatorProfileInput.getDateOfBirth());
        }
        if (updateTranslatorProfileInput.getEmail() != null
            && !updateTranslatorProfileInput.getEmail().isBlank()
            && !translatorProfile.getEmail().equals(updateTranslatorProfileInput.getEmail())) {
            translatorProfile.setEmail(updateTranslatorProfileInput.getEmail());
        }
        if (updateTranslatorProfileInput.getIsAvailable() != null
            && !translatorProfile.getIsAvailable().equals(updateTranslatorProfileInput.getIsAvailable())) {
            translatorProfile.setIsAvailable(updateTranslatorProfileInput.getIsAvailable());
        }
        if (updateTranslatorProfileInput.getIsOnline() != null
            && !translatorProfile.getIsOnline().equals(updateTranslatorProfileInput.getIsOnline())) {
            translatorProfile.setIsOnline(updateTranslatorProfileInput.getIsOnline());
        }
        if (updateTranslatorProfileInput.getLevelOfKorean() != null
            && !updateTranslatorProfileInput.getLevelOfKorean().isBlank()
            && !translatorProfile.getLevelOfKorean().equals(updateTranslatorProfileInput.getLevelOfKorean())) {
            translatorProfile.setLevelOfKorean(updateTranslatorProfileInput.getLevelOfKorean());
        }
        if (updateTranslatorProfileInput.getUser() != null
            && !translatorProfile.getUser().equals(updateTranslatorProfileInput.getUser())) {
            translatorProfile.setUser(updateTranslatorProfileInput.getUser());
        }
        if (updateTranslatorProfileInput.getLanguages() != null
            && !translatorProfile.getLanguages().equals(updateTranslatorProfileInput.getLanguages())) {
            translatorProfile.setLanguages(updateTranslatorProfileInput.getLanguages());
        }
        if (updateTranslatorProfileInput.getThemes() != null
            && !translatorProfile.getThemes().equals(updateTranslatorProfileInput.getThemes())) {
            translatorProfile.setThemes(updateTranslatorProfileInput.getThemes());
        }
        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public Boolean deleteById (Long id) {
        translatorProfileRepository.deleteById(id);
        return true;
    }
}
