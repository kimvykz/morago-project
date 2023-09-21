package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.input.CategoryCreateInput;
import com.habsida.moragoproject.model.input.CategoryUpdateInput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Page<Category> getAllByPaging(PageRequest pageRequest);
    Category getById(Long id);
    Category create(CategoryCreateInput categoryCreateInput);
    Category update(CategoryUpdateInput categoryUpdateInput);
    Boolean deleteById(Long id);
}
