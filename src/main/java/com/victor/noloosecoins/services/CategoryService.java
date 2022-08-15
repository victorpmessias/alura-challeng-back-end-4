package com.victor.noloosecoins.services;

import com.victor.noloosecoins.models.category.Category;
import com.victor.noloosecoins.models.category.dto.CategoryDto;
import com.victor.noloosecoins.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<CategoryDto> getCategories() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(CategoryDto::new).toList();
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
             new EntityNotFoundException("Can't find a category with id: " + id)
        );

    }

    public CategoryDto getBydId(Long id) {
        Category category = findById(id);
        return new CategoryDto(category);
    }
}
