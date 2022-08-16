package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.models.category.dto.CategoryDto;
import com.victor.noloosecoins.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<CategoryDto> listCategories(){
        return service.getCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategoryByd(@PathVariable Long id){
        return service.getBydId(id);
    }
}
