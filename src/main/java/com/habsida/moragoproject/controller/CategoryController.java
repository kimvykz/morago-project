package com.habsida.moragoproject.controller;

import com.habsida.moragoproject.model.input.CategoryCreateInput;
import com.habsida.moragoproject.model.input.PaginationInput;
import com.habsida.moragoproject.model.input.CategoryUpdateInput;
import com.habsida.moragoproject.model.entity.Category;
import com.habsida.moragoproject.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController (CategoryService categoryService) {
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

    @QueryMapping(name = "getCategoriesByPaging")
    public Page<Category> getAllByPaging (@Argument(name = "paginationInput") PaginationInput paginationInput) {
        PageRequest pageRequest = PageRequest.of(paginationInput.getPage(), paginationInput.getSize());
        return categoryService.getAllByPaging(pageRequest);
    }

    @MutationMapping(name = "createCategory")
    public Category create (@Valid @Argument(name = "categoryInput") CategoryCreateInput categoryCreateInput) {
        return categoryService.create(categoryCreateInput);
    }

    @MutationMapping(name = "updateCategory")
    public Category update (@Valid @Argument(name = "categoryInput") CategoryUpdateInput categoryUpdateInput) {
        return categoryService.update(categoryUpdateInput);
    }

    @MutationMapping(name = "deleteCategoryById")
    public Boolean deleteById (@Argument Long id) {
        return categoryService.deleteById(id);
    }


}
