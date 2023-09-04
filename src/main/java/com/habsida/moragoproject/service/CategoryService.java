package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Page<Category> getAllPaged(PageRequest pageRequest);
    Category getById(Long id);
    Category create(Category category);
    Category update(Category category);
    Boolean deleteById(Long id);
}
