package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.models.expense.dto.ExpenseDto;
import com.victor.noloosecoins.models.expense.forms.NewExpenseForm;
import com.victor.noloosecoins.services.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {
    private final ExpenseService service;

    public ExpensesController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ExpenseDto> getAll(Pageable pageable) {
        return service.listAll(pageable);
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpenseById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteExpenseById(@PathVariable Long id) {
        service.deleteExpense(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> registerNewExpense(@RequestBody @Valid NewExpenseForm form) {
        ExpenseDto expense = service.registerNewExpense(form);
        return ResponseEntity.created(URI.create("/expenses/"+expense.getId())).body(expense);
    }

    @PutMapping("/{id}")
    @Transactional
    public ExpenseDto updateExpenseRegistry(@RequestBody @Valid NewExpenseForm form, @PathVariable Long id) {
        return service.updateRegistry(form, id);
    }
}
