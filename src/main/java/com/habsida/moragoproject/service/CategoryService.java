package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllItems();
    Category getItemById(Long id);
    Category createItem(Category category);
    Category modifyItem(Category category);
    void removeItem(Long id);
}
