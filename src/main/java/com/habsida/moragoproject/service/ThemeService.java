package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.CreateThemeInput;
import com.habsida.moragoproject.model.input.UpdateThemeInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ThemeService {
    List<Theme> getAll ();
    Page<Theme> getAllPaged(PageRequest pageRequest);
    Theme getById (Long id);
    Theme create (CreateThemeInput createThemeInput);
    Theme update (UpdateThemeInput updateThemeInput);
    Boolean deleteById (Long id);
}
