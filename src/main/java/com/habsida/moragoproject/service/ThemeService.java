package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Theme;
import com.habsida.moragoproject.model.input.ThemeCreateInput;
import com.habsida.moragoproject.model.input.ThemeUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ThemeService {
    List<Theme> getAll ();
    Page<Theme> getAllByPaging (PageRequest pageRequest);
    Theme getById (Long id);
    Theme create (ThemeCreateInput themeCreateInput);
    Theme update (ThemeUpdateInput themeUpdateInput);
    Boolean deleteById (Long id);
}
