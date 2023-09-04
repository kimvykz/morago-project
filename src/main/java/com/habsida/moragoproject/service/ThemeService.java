package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Theme;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ThemeService {
    List<Theme> getAll ();
    Page<Theme> getAllPaged(PageRequest pageRequest);
    Theme getById (Long id);
    Theme create (Theme theme);
    Theme update (Theme theme);
    Boolean deleteById (Long id);
}
