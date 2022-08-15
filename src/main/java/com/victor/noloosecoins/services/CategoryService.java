package com.victor.noloosecoins.services;

import com.victor.noloosecoins.models.category.Category;
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

    public List<Category> getCategories() {
        return repository.findAll();
    }

    public Category getBydId(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Can't find a category with id: " + id);
        });
    }
}
