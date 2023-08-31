package com.habsida.moragoproject.service;

import com.habsida.moragoproject.entity.Category;
import com.habsida.moragoproject.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllItems() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getItemById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        }
        throw new RuntimeException("Category is not found for the id - " + id);
    }

    @Override
    public Category createItem(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category modifyItem(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void removeItem(Long id) {
        categoryRepository.deleteById(id);
    }
}
