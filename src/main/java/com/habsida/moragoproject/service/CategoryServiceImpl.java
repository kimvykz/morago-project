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
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllPaged(PageRequest pageRequest) {
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
        Category category = modelMapper.map(createCategoryInput, Category.class);

        if (category.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        }
        if (category.getName() == null) {
            throw new IllegalArgumentException("field name cannot be null");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(UpdateCategoryInput updateCategoryInput) {
        Category category = getById(updateCategoryInput.getId());
        modelMapper.map(updateCategoryInput, category);

        if (category.getIsActive() == null) {
            throw new IllegalArgumentException("field isActive cannot be null");
        }
        if (category.getName() == null) {
            throw new IllegalArgumentException("field name cannot be null");
        }
        return categoryRepository.save(category);
    }

    @Override
    public Boolean deleteById(Long id) {
        categoryRepository.deleteById(id);
        return true;
    }
}
