package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.ThemeCreateInput;
import com.habsida.moragoproject.model.input.ThemeUpdateInput;
import com.habsida.moragoproject.repository.ThemeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
    public Theme create (ThemeCreateInput themeCreateInput) {
        Theme theme = new Theme();

        if (themeCreateInput.getDescription() == null
            || themeCreateInput.getDescription().isBlank()) {
            throw new IllegalArgumentException("field description cannot be null");
        } else {
            theme.setDescription(themeCreateInput.getDescription());
        }
        if (themeCreateInput.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        } else {
            theme.setIsActive(themeCreateInput.getIsActive());
        }
        if (themeCreateInput.getIsPopular() == null) {
            throw new IllegalArgumentException("field isPopular cannot be null");
        } else {
            theme.setIsPopular(themeCreateInput.getIsPopular());
        }
        if (themeCreateInput.getKoreanTitle() == null
            || themeCreateInput.getKoreanTitle().isBlank()) {
            throw new IllegalArgumentException("field koreanTitle cannot be null");
        } else {
            theme.setKoreanTitle(themeCreateInput.getKoreanTitle());
        }
        if (themeCreateInput.getName() == null
            || themeCreateInput.getName().isBlank()) {
            throw new IllegalArgumentException("field name cannot be null");
        } else {
            theme.setName(themeCreateInput.getName());
        }
        if (themeCreateInput.getNightPrice() == null) {
            throw new IllegalArgumentException("field nightPrice cannot be null");
        } else {
            theme.setNightPrice(themeCreateInput.getNightPrice());
        }
        if (theme.getPrice() == null) {
            throw new IllegalArgumentException("field price cannot be null");
        } else {
            theme.setPrice(themeCreateInput.getPrice());
        }
        if (theme.getCategory() == null) {
            throw new IllegalArgumentException("field category cannot be null");
        } else {
            theme.setCategory(themeCreateInput.getCategory());
        }
        if (theme.getFile() == null) {
            throw new IllegalArgumentException("field file cannot be null");
        } else {
            theme.setFile(themeCreateInput.getFile());
        }

        return themeRepository.save(theme);
    }

    @Override
    public Theme update (ThemeUpdateInput themeUpdateInput) {
        Theme theme = getById(themeUpdateInput.getId());

        if (themeUpdateInput.getDescription() != null
            && !themeUpdateInput.getDescription().isBlank()
            && !theme.getDescription().equals(themeUpdateInput.getDescription())) {
            theme.setDescription(themeUpdateInput.getDescription());
        }
        if (themeUpdateInput.getIsActive() != null
            && !theme.getIsActive().equals(themeUpdateInput.getIsActive())) {
            theme.setIsActive(themeUpdateInput.getIsActive());
        }
        if (themeUpdateInput.getIsPopular() != null
            && !theme.getIsPopular().equals(themeUpdateInput.getIsPopular())) {
            theme.setIsPopular(themeUpdateInput.getIsPopular());
        }
        if (themeUpdateInput.getKoreanTitle() != null
            && !theme.getKoreanTitle().equals(themeUpdateInput.getKoreanTitle())
            && !themeUpdateInput.getKoreanTitle().isBlank()) {
            theme.setKoreanTitle(themeUpdateInput.getKoreanTitle());
        }
        if (themeUpdateInput.getName() != null
            && !theme.getName().equals(themeUpdateInput.getName())
            && !themeUpdateInput.getName().isBlank()) {
            theme.setName(themeUpdateInput.getName());
        }
        if (themeUpdateInput.getNightPrice() != null
            && !theme.getNightPrice().equals(themeUpdateInput.getNightPrice())) {
            theme.setNightPrice(themeUpdateInput.getNightPrice());
        }
        if (themeUpdateInput.getPrice() != null
            && !theme.getPrice().equals(themeUpdateInput.getPrice())) {
            theme.setPrice(themeUpdateInput.getPrice());
        }
        if (themeUpdateInput.getCategory() != null
            && !theme.getCategory().equals(themeUpdateInput.getCategory())) {
            theme.setCategory(themeUpdateInput.getCategory());
        }
        if (themeUpdateInput.getFile() != null
            && !theme.getFile().equals(themeUpdateInput.getFile())) {
            theme.setFile(themeUpdateInput.getFile());
        }
        return themeRepository.save(theme);
    }

    @Override
    public Boolean deleteById (Long id) {
        themeRepository.deleteById(id);
        return true;
    }
}
