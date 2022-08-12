package com.victor.noloosecoins.models.expense.dto;

import com.victor.noloosecoins.models.expense.Expense;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDto {


    private final Long id;
    private final String description;
    private final BigDecimal value;
    private final LocalDate date;

    public ExpenseDto(Expense expense){
        this.id  = expense.getId();
        this.description = expense.getDescription();
        this.value = expense.getValue();
        this.date = expense.getDate();
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
}
