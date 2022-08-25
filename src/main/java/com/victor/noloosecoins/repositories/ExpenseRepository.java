package com.victor.noloosecoins.repositories;

import com.victor.noloosecoins.models.expense.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Page<Expense> findByDescriptionContains(String description, Pageable pageable);

    Page<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);
    boolean existsByDateBetweenAndDescription(LocalDate startDate, LocalDate endDate, String description);
}
