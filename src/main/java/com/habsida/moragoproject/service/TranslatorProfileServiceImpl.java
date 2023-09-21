package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.TranslatorProfile;
import com.habsida.moragoproject.model.input.TranslatorProfileCreateInput;
import com.habsida.moragoproject.model.input.TranslatorProfileUpdateInput;
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
    public TranslatorProfile create (TranslatorProfileCreateInput translatorProfileCreateInput) {
        TranslatorProfile translatorProfile = new TranslatorProfile();

        if (translatorProfileCreateInput.getDateOfBirth() == null) {
            throw new IllegalArgumentException("field dateOfBirth cannot be null");
        } else {
            translatorProfile.setDateOfBirth(translatorProfileCreateInput.getDateOfBirth());
        }
        if (translatorProfileCreateInput.getEmail() == null
            && translatorProfileCreateInput.getEmail().isBlank()) {
            throw new IllegalArgumentException("field email cannot be null");
        } else {
            translatorProfile.setEmail(translatorProfileCreateInput.getEmail());
        }
        if (translatorProfileCreateInput.getIsAvailable() == null) {
            throw new IllegalArgumentException("field isAvailable cannot be null");
        } else {
            translatorProfile.setIsAvailable(translatorProfileCreateInput.getIsAvailable());
        }
        if (translatorProfileCreateInput.getIsOnline() == null) {
            throw new IllegalArgumentException("field isOnline cannot be null");
        } else {
            translatorProfile.setIsOnline(translatorProfileCreateInput.getIsOnline());
        }
        if (translatorProfileCreateInput.getLevelOfKorean() == null
            && translatorProfileCreateInput.getLevelOfKorean().isBlank()) {
            throw new IllegalArgumentException("field levelOfKorean cannot be null");
        } else {
            translatorProfile.setLevelOfKorean(translatorProfileCreateInput.getLevelOfKorean());
        }
//        if (createTranslatorProfileInput.getUser() == null) {
//            throw new IllegalArgumentException("field user cannot be null");
//        } else {
//            translatorProfile.setUser(createTranslatorProfileInput.getUser());
//        }
        if (translatorProfileCreateInput.getLanguages() == null) {
            throw new IllegalArgumentException("field languages cannot be null");
        } else {
            translatorProfile.setLanguages(translatorProfileCreateInput.getLanguages());
        }
        if (translatorProfileCreateInput.getThemes() == null) {
            throw new IllegalArgumentException("field themes cannot be null");
        } else {
            translatorProfile.setThemes(translatorProfileCreateInput.getThemes());
        }

        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public TranslatorProfile update (TranslatorProfileUpdateInput translatorProfileUpdateInput) {
        TranslatorProfile translatorProfile = getById(translatorProfileUpdateInput.getId());

        if (translatorProfileUpdateInput.getDateOfBirth() != null
            && !translatorProfile.getDateOfBirth().equals(translatorProfileUpdateInput.getDateOfBirth())) {
            translatorProfile.setDateOfBirth(translatorProfileUpdateInput.getDateOfBirth());
        }
        if (translatorProfileUpdateInput.getEmail() != null
            && !translatorProfileUpdateInput.getEmail().isBlank()
            && !translatorProfile.getEmail().equals(translatorProfileUpdateInput.getEmail())) {
            translatorProfile.setEmail(translatorProfileUpdateInput.getEmail());
        }
        if (translatorProfileUpdateInput.getIsAvailable() != null
            && !translatorProfile.getIsAvailable().equals(translatorProfileUpdateInput.getIsAvailable())) {
            translatorProfile.setIsAvailable(translatorProfileUpdateInput.getIsAvailable());
        }
        if (translatorProfileUpdateInput.getIsOnline() != null
            && !translatorProfile.getIsOnline().equals(translatorProfileUpdateInput.getIsOnline())) {
            translatorProfile.setIsOnline(translatorProfileUpdateInput.getIsOnline());
        }
        if (translatorProfileUpdateInput.getLevelOfKorean() != null
            && !translatorProfileUpdateInput.getLevelOfKorean().isBlank()
            && !translatorProfile.getLevelOfKorean().equals(translatorProfileUpdateInput.getLevelOfKorean())) {
            translatorProfile.setLevelOfKorean(translatorProfileUpdateInput.getLevelOfKorean());
        }
        if (translatorProfileUpdateInput.getUser() != null
            && !translatorProfile.getUser().equals(translatorProfileUpdateInput.getUser())) {
            translatorProfile.setUser(translatorProfileUpdateInput.getUser());
        }
        if (translatorProfileUpdateInput.getLanguages() != null
            && !translatorProfile.getLanguages().equals(translatorProfileUpdateInput.getLanguages())) {
            translatorProfile.setLanguages(translatorProfileUpdateInput.getLanguages());
        }
        if (translatorProfileUpdateInput.getThemes() != null
            && !translatorProfile.getThemes().equals(translatorProfileUpdateInput.getThemes())) {
            translatorProfile.setThemes(translatorProfileUpdateInput.getThemes());
        }
        return translatorProfileRepository.save(translatorProfile);
    }

    @Override
    public Boolean deleteById (Long id) {
        translatorProfileRepository.deleteById(id);
        return true;
    }
}
