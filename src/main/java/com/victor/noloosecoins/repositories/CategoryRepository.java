package com.victor.noloosecoins.repositories;

import com.victor.noloosecoins.models.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
