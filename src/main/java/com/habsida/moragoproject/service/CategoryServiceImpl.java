package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.model.input.CreateCategoryInput;
import com.habsida.moragoproject.model.input.UpdateCategoryInput;
import com.habsida.moragoproject.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
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
    public Category create(CreateCategoryInput createCategoryInput) {
        Category category = new Category();

        if (createCategoryInput.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        } else {
            category.setIsActive(createCategoryInput.getIsActive());
        }
        if (createCategoryInput.getName() == null
            || createCategoryInput.getName().isBlank()) {
            throw new IllegalArgumentException("field name cannot be null");
        } else {
            category.setName(createCategoryInput.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(UpdateCategoryInput updateCategoryInput) {
        Category category = getById(updateCategoryInput.getId());

        if (updateCategoryInput.getIsActive() != null
            && !category.getIsActive().equals(updateCategoryInput.getIsActive())) {
            category.setIsActive(updateCategoryInput.getIsActive());
        }
        if (updateCategoryInput.getName() != null
            && !category.getName().equals(updateCategoryInput.getName())
            && !updateCategoryInput.getName().isBlank()) {
            category.setName(updateCategoryInput.getName());
        }
        return categoryRepository.save(category);
    }

    @Override
    public Boolean deleteById(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
