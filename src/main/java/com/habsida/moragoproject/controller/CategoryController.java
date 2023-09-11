package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CreateCategoryInput;
import com.habsida.moragoproject.model.input.UpdateCategoryInput;
import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class CategoryController {
    private ModelMapper modelMapper;

    private CategoryService categoryService;

    public CategoryController(ModelMapper modelMapper,
                              CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @QueryMapping(name = "getCategories")
    public Iterable<Category> getAll (){
        return categoryService.getAll();
    }

    @QueryMapping(name = "getCategoryById")
    public Category getById (@Argument Long id) {
        return categoryService.getById(id);
    }

    @QueryMapping(name = "getCategoriesPaged")
    public Page<Category> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return categoryService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createCategory")
    public Category create (@Valid @Argument(name = "category") CreateCategoryInput createCategoryInput) {
        Category category = modelMapper.map(createCategoryInput, Category.class);
        return categoryService.create(category);
    }

    @MutationMapping(name = "updateCategoryById")
    public Category updateById (@Valid @Argument Long id,
                              @Argument(name = "category") UpdateCategoryInput updateCategoryInput) {
        Category category = categoryService.getById(id);
        modelMapper.map(updateCategoryInput, category);
        return categoryService.update(category);
    }

    @MutationMapping(name = "deleteCategoryById")
    public Boolean deleteById (@Argument Long id) {
        return categoryService.deleteById(id);
    }


}
