package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.input.CategoryCreateInput;
import com.habsida.moragoproject.model.input.CategoryUpdateInput;
import com.habsida.moragoproject.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllByPaging(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest);
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
        throw new IllegalArgumentException("Category is not found for the id - " + id);
        });
    }

    @Override
    public Category create(CategoryCreateInput categoryCreateInput) {
        Category category = new Category();

        if (categoryCreateInput.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        } else {
            category.setIsActive(categoryCreateInput.getIsActive());
        }
        if (categoryCreateInput.getName() == null
            || categoryCreateInput.getName().isBlank()) {
            throw new IllegalArgumentException("field name cannot be null");
        } else {
            category.setName(categoryCreateInput.getName());
        }
        if (categoryCreateInput.getThemes() == null) {
            throw new IllegalArgumentException("field Themes cannot be null");
        } else {
            category.setThemes(categoryCreateInput.getThemes());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(CategoryUpdateInput categoryUpdateInput) {
        Category category = getById(categoryUpdateInput.getId());

        if (categoryUpdateInput.getIsActive() != null
            && !category.getIsActive().equals(categoryUpdateInput.getIsActive())) {
            category.setIsActive(categoryUpdateInput.getIsActive());
        }
        if (categoryUpdateInput.getName() != null
            && !category.getName().equals(categoryUpdateInput.getName())
            && !categoryUpdateInput.getName().isBlank()) {
            category.setName(categoryUpdateInput.getName());
        }
        if (categoryUpdateInput.getThemes() != null
                && !category.getThemes().equals(categoryUpdateInput.getThemes())
                ) {
            category.setThemes(categoryUpdateInput.getThemes());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Boolean deleteById(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
