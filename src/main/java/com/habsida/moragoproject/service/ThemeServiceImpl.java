package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.entity.File;
import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.CreateThemeInput;
import com.habsida.moragoproject.model.input.UpdateThemeInput;
import com.habsida.moragoproject.repository.ThemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Service
public class ThemeServiceImpl implements ThemeService {
    private ThemeRepository themeRepository;

    public ThemeServiceImpl (ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public List<Theme> getAll () {
        return themeRepository.findAll();
    }

    @Override
    public Page<Theme> getAllByPaging (PageRequest pageRequest) {
        return themeRepository.findAll(pageRequest);
    }

    @Override
    public Theme getById (Long id) {
        return themeRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Theme is not found for the id - " + id);
        });
    }

    @Override
    public Theme create (CreateThemeInput createThemeInput) {
        Theme theme = new Theme();

        if (createThemeInput.getDescription() == null
            || createThemeInput.getDescription().isBlank()) {
            throw new IllegalArgumentException("field description cannot be null");
        } else {
            theme.setDescription(createThemeInput.getDescription());
        }
        if (createThemeInput.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        } else {
            theme.setIsActive(createThemeInput.getIsActive());
        }
        if (createThemeInput.getIsPopular() == null) {
            throw new IllegalArgumentException("field isPopular cannot be null");
        } else {
            theme.setIsPopular(createThemeInput.getIsPopular());
        }
        if (createThemeInput.getKoreanTitle() == null
            || createThemeInput.getKoreanTitle().isBlank()) {
            throw new IllegalArgumentException("field koreanTitle cannot be null");
        } else {
            theme.setKoreanTitle(createThemeInput.getKoreanTitle());
        }
        if (createThemeInput.getName() == null
            || createThemeInput.getName().isBlank()) {
            throw new IllegalArgumentException("field name cannot be null");
        } else {
            theme.setName(createThemeInput.getName());
        }
        if (createThemeInput.getNightPrice() == null) {
            throw new IllegalArgumentException("field nightPrice cannot be null");
        } else {
            theme.setNightPrice(createThemeInput.getNightPrice());
        }
        if (theme.getPrice() == null) {
            throw new IllegalArgumentException("field price cannot be null");
        } else {
            theme.setPrice(createThemeInput.getPrice());
        }
        if (theme.getCategory() == null) {
            throw new IllegalArgumentException("field category cannot be null");
        } else {
            theme.setCategory(createThemeInput.getCategory());
        }
        if (theme.getFiles() == null) {
            throw new IllegalArgumentException("field file cannot be null");
        } else {
            theme.setFiles(createThemeInput.getFiles());
        }

        return themeRepository.save(theme);
    }

    @Override
    public Theme update (UpdateThemeInput updateThemeInput) {
        Theme theme = getById(updateThemeInput.getId());

        if (updateThemeInput.getDescription() != null
            && !updateThemeInput.getDescription().isBlank()
            && !theme.getDescription().equals(updateThemeInput.getDescription())) {
            theme.setDescription(updateThemeInput.getDescription());
        }
        if (updateThemeInput.getIsActive() != null
            && !theme.getIsActive().equals(updateThemeInput.getIsActive())) {
            theme.setIsActive(updateThemeInput.getIsActive());
        }
        if (updateThemeInput.getIsPopular() != null
            && !theme.getIsPopular().equals(updateThemeInput.getIsPopular())) {
            theme.setIsPopular(updateThemeInput.getIsPopular());
        }
        if (updateThemeInput.getKoreanTitle() != null
            && !theme.getKoreanTitle().equals(updateThemeInput.getKoreanTitle())
            && !updateThemeInput.getKoreanTitle().isBlank()) {
            theme.setKoreanTitle(updateThemeInput.getKoreanTitle());
        }
        if (updateThemeInput.getName() != null
            && !theme.getName().equals(updateThemeInput.getName())
            && !updateThemeInput.getName().isBlank()) {
            theme.setName(updateThemeInput.getName());
        }
        if (updateThemeInput.getNightPrice() != null
            && !theme.getNightPrice().equals(updateThemeInput.getNightPrice())) {
            theme.setNightPrice(updateThemeInput.getNightPrice());
        }
        if (updateThemeInput.getPrice() != null
            && !theme.getPrice().equals(updateThemeInput.getPrice())) {
            theme.setPrice(updateThemeInput.getPrice());
        }
        if (updateThemeInput.getCategory() != null
            && !theme.getCategory().equals(updateThemeInput.getCategory())) {
            theme.setCategory(updateThemeInput.getCategory());
        }
        if (updateThemeInput.getFiles() != null
            && !theme.getFiles().equals(updateThemeInput.getFiles())) {
            theme.setFiles(updateThemeInput.getFiles());
        }
        return themeRepository.save(theme);
    }

    @Override
    public Boolean deleteById (Long id) {
        themeRepository.deleteById(id);
        return true;
    }
}
