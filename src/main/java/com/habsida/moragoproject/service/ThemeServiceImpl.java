package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Theme;
import com.habsida.moragoproject.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Theme getById (Long id) {
        return themeRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Theme is not found for the id - " + id);
        });
    }

    @Override
    public Theme create (Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Theme update (Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public void deleteById (Long id) {
        themeRepository.deleteById(id);
    }
}
