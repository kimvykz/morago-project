package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Theme;

import java.util.List;

public interface ThemeService {
    List<Theme> getAllItems();
    Theme getItemById(Long id);
    Theme createItem(Theme theme);
    Theme modifyItem(Theme theme);
    void removeItem(Long id);
}
