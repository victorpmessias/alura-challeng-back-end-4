package com.victor.noloosecoins.repositories;

import com.victor.noloosecoins.models.expense.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
