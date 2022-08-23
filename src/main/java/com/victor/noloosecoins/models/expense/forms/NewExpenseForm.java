package com.victor.noloosecoins.models.expense.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.noloosecoins.models.expense.Expense;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewExpenseForm {

    @Length(min = 0 , max = 255)
    private String description;
    @Digits(integer = 9, fraction = 2)
    private BigDecimal value;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    private Long category = 1L;

    public NewExpenseForm() {
    }

    public NewExpenseForm(String description, BigDecimal value, LocalDate date, Long category) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Expense convertToExpenseEntity(){
        Expense expense = new Expense();
        expense.setDescription(this.description);
        expense.setDate(this.date);
        expense.setValue(this.value);
        return expense;
    }

    public Expense convertToExpenseEntity(Long id){
        Expense expense = new Expense();
        expense.setId(id);
        expense.setDescription(this.description);
        expense.setDate(this.date);
        expense.setValue(this.value);
        return expense;
    }
}
