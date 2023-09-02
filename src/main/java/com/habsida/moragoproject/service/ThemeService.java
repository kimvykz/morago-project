package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Theme;

import java.util.List;

public interface ThemeService {
    List<Theme> getAll ();
    Theme getById (Long id);
    Theme create (Theme theme);
    Theme update (Theme theme);
    void deleteById (Long id);
}
