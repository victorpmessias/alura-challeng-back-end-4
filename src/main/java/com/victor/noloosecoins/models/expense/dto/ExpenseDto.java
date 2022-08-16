package com.victor.noloosecoins.models.expense.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.noloosecoins.models.category.dto.CategoryDto;
import com.victor.noloosecoins.models.expense.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDto {


    private final Long id;
    private final String description;
    private final BigDecimal value;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDate date;
    private final CategoryDto category;

    public ExpenseDto(Expense expense){
        this.id  = expense.getId();
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.date = expense.getDate();
        this.category = new CategoryDto(expense.getCategory());
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public CategoryDto getCategory() {
        return category;
    }
}
