package com.victor.noloosecoins.models.category;

import com.victor.noloosecoins.models.expense.Expense;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Expense> expenses;

    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
