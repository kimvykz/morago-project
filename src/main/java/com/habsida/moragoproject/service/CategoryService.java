package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(Long id);
    Category create(Category category);
    Category update(Category category);
    void deleteById(Long id);
}
