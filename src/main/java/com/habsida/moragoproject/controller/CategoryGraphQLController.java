package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.dto.CategoryDTO;
import com.habsida.moragoproject.entity.Category;
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
public class CategoryGraphQLController {
    private ModelMapper modelMapper;

    private CategoryService categoryService;

    public CategoryGraphQLController(ModelMapper modelMapper,
                                     CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @QueryMapping(name = "getCategory")
    public Iterable<Category> getAll (){
        return categoryService.getAll();
    }

    @QueryMapping(name = "getCategoryById")
    public Category getById (@Argument Long id) {
        return categoryService.getById(id);
    }

    @QueryMapping(name = "getCategorysPaged")
    public Page<Category> getAllPaged (@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return categoryService.getAllPaged(pageRequest);
    }

    @MutationMapping(name = "createCategory")
    public Category create (@Valid @Argument(name = "category") CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return categoryService.create(category);
    }

    @MutationMapping(name = "updateCategoryById")
    public Category updateById (@Valid @Argument Long id,
                              @Argument(name = "category") CategoryDTO categoryDTO) {
        Category category = categoryService.getById(id);
        modelMapper.map(categoryDTO, category);
        return categoryService.update(category);
    }

    @MutationMapping(name = "deleteCategoryById")
    public Boolean deleteById (@Argument Long id) {
        return categoryService.deleteById(id);
    }


}
