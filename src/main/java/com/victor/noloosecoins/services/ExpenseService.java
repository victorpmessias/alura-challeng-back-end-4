package com.victor.noloosecoins.services;

import com.victor.noloosecoins.exceptions.AlreadyRegisteredDescription;
import com.victor.noloosecoins.models.category.Category;
import com.victor.noloosecoins.models.expense.Expense;
import com.victor.noloosecoins.models.expense.dto.ExpenseDto;
import com.victor.noloosecoins.models.expense.forms.NewExpenseForm;
import com.victor.noloosecoins.models.revenues.Revenue;
import com.victor.noloosecoins.repositories.ExpenseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;

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
        checkIfExistAnEqualsDescriptionWithinTheMonth(expense);
        Category category = categoryService.findById(form.getCategory());
        expense.setCategory(category);
        expense = repository.save(expense);
        return new ExpenseDto(expense);
    }

    public ExpenseDto updateRegistry(NewExpenseForm form, Long id) {
        Expense expense = getExpenseById(id);
        checkIfExistAnEqualsDescriptionWithinTheMonth(form.convertToExpenseEntity(id));
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

    public Page<ExpenseDto> getAllByDescription(String description, Pageable pageable) {
        Page<Expense> expenses = repository.findByDescriptionContains(description, pageable);
        return expenses.map(ExpenseDto::new);
    }

    public Page<ExpenseDto> searchExpenseByMonth(int year, int month, Pageable pageable) {
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.ofEpochDay(startDate.toEpochDay()).plusMonths(1).withDayOfMonth(1).minusDays(1);
        Page<Expense> expenses = repository.findByDateBetween(startDate, endDate, pageable);
        return expenses.map(ExpenseDto::new);
    }

    private void checkIfExistAnEqualsDescriptionWithinTheMonth(Expense newExpense) {
        LocalDate start = newExpense.getDate().withDayOfMonth(1);
        LocalDate endDate = newExpense.getDate().plusMonths(1).withDayOfMonth(1).minusDays(1);
        List<Expense> expenses = repository.findByDateBetween(start, endDate);
        for(Expense registeredExpense : expenses){
            if(registeredExpense.getDescription().equalsIgnoreCase(newExpense.getDescription())
                    && newExpense.getId() == null
                    || !registeredExpense.getId().equals(newExpense.getId())) throw new AlreadyRegisteredDescription("Have been find an expense with same description registered in this month");
        }
    }
}
