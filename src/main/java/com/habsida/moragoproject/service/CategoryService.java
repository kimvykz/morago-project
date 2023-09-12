package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.input.CreateCategoryInput;
import com.habsida.moragoproject.model.input.UpdateCategoryInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Page<Category> getAllPaged(PageRequest pageRequest);
    Category getById(Long id);
    Category create(CreateCategoryInput createCategoryInput);
    Category update(UpdateCategoryInput updateCategoryInput);
    Boolean deleteById(Long id);
}
