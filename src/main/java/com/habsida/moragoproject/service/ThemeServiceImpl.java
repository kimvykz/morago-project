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
    private ModelMapper modelMapper;

    public ThemeServiceImpl (ThemeRepository themeRepository, ModelMapper modelMapper) {
        this.themeRepository = themeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Theme> getAll () {
        return themeRepository.findAll();
    }

    @Override
    public Page<Theme> getAllPaged(PageRequest pageRequest) {
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
        Theme theme = modelMapper.map(createThemeInput, Theme.class);

        if (theme.getDescription() == null) {
            throw new IllegalArgumentException("field description cannot be null");
        }
        if (theme.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        }
        if (theme.getIsPopular() == null) {
            throw new IllegalArgumentException("field isPopular cannot be null");
        }
        if (theme.getKoreanTitle() == null) {
            throw new IllegalArgumentException("field koreanTitle cannot be null");
        }
        if (theme.getName() == null) {
            throw new IllegalArgumentException("field name cannot be null");
        }
        if (theme.getNightPrice() == null) {
            throw new IllegalArgumentException("field nightPrice cannot be null");
        }
        if (theme.getPrice() == null) {
            throw new IllegalArgumentException("field price cannot be null");
        }
        if (theme.getCategory() == null) {
            throw new IllegalArgumentException("field category cannot be null");
        }
        if (theme.getFile() == null) {
            //throw new IllegalArgumentException("field file cannot be null");
        }

        return themeRepository.save(theme);
    }

    @Override
    public Theme update (UpdateThemeInput updateThemeInput) {
        Theme theme = getById(updateThemeInput.getId());
        modelMapper.map(updateThemeInput, theme);
        if (theme.getDescription() == null) {
            throw new IllegalArgumentException("field description cannot be null");
        }
        if (theme.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        }
        if (theme.getIsPopular() == null) {
            throw new IllegalArgumentException("field isPopular cannot be null");
        }
        if (theme.getKoreanTitle() == null) {
            throw new IllegalArgumentException("field koreanTitle cannot be null");
        }
        if (theme.getName() == null) {
            throw new IllegalArgumentException("field name cannot be null");
        }
        if (theme.getNightPrice() == null) {
            throw new IllegalArgumentException("field nightPrice cannot be null");
        }
        if (theme.getPrice() == null) {
            throw new IllegalArgumentException("field price cannot be null");
        }
        if (theme.getCategory() == null) {
            throw new IllegalArgumentException("field category cannot be null");
        }
        if (theme.getFile() == null) {
            //throw new IllegalArgumentException("field file cannot be null");
        }
        return themeRepository.save(theme);
    }

    @Override
    public Boolean deleteById (Long id) {
        themeRepository.deleteById(id);
        return true;
    }
}
