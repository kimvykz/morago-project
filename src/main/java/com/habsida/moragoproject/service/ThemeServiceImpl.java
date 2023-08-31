package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Theme;
import com.habsida.moragoproject.repository.ThemeRepository;

import java.util.List;
import java.util.Optional;

public class ThemeServiceImpl implements ThemeService{
    private ThemeRepository themeRepository;

    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public List<Theme> getAllItems() {
        return themeRepository.findAll();
    }

    @Override
    public Theme getItemById(Long id) {
        Optional<Theme> theme = themeRepository.findById(id);
        if (theme.isPresent()){
            return theme.get();
        }
        throw new RuntimeException("Theme is not found for the id - " + id);
    }

    @Override
    public Theme createItem(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public Theme modifyItem(Theme theme) {
        return themeRepository.save(theme);
    }

    @Override
    public void removeItem(Long id) {
        themeRepository.deleteById(id);
    }
}
