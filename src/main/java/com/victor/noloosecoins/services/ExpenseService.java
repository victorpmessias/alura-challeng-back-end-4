package com.victor.noloosecoins.services;

import com.victor.noloosecoins.models.category.Category;
import com.victor.noloosecoins.models.expense.Expense;
import com.victor.noloosecoins.models.expense.dto.ExpenseDto;
import com.victor.noloosecoins.models.expense.forms.NewExpenseForm;
import com.victor.noloosecoins.repositories.ExpenseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ExpenseService {

    private final ExpenseRepository repository;
    private final CategoryService categoryService;

    public ExpenseService(ExpenseRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }


    public Page<ExpenseDto> listAll(Pageable pageable) {
        Page<Expense> expenses = repository.findAll(pageable);
        return expenses.map(ExpenseDto::new);
    }

    public ExpenseDto registerNewExpense(NewExpenseForm form) {
        Expense expense = form.convertToExpenseEntity();
        Category category = categoryService.findById(form.getCategory());
        expense.setCategory(category);
        expense = repository.save(expense);
        return new ExpenseDto(expense);
    }

    public ExpenseDto updateRegistry(NewExpenseForm form, Long id) {
        Expense expense = getExpenseById(id);
        expense.setValue(form.getValue());
        expense.setDescription(form.getDescription());
        expense.setDate(form.getDate());
        Category category = categoryService.findById(form.getCategory());
        expense.setCategory(category);

        return new ExpenseDto(expense);
    }

    private Expense getExpenseById(Long id) {
        return repository.findById(id).orElseThrow(() ->
             new EntityNotFoundException("Can't find a expense entity with id: " + id)
        );
    }

    public ExpenseDto getById(Long id) {
        Expense expense = getExpenseById(id);
        return new ExpenseDto(expense);
    }

    public void deleteExpense(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Can't find a expense entity with id: " + id);
        }
    }
}
