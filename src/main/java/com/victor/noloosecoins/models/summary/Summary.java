package com.victor.noloosecoins.models.summary;

import java.math.BigDecimal;
import java.util.Map;

public class Summary {

    private BigDecimal totalRevenues;
    private BigDecimal totalExpenses;
    private BigDecimal monthBalance;
    private Map<String, BigDecimal> totalExpenseByCategory;

    public Summary() {
    }

    public Summary(BigDecimal totalRevenues, BigDecimal totalExpenses, BigDecimal monthBalance, Map<String, BigDecimal> totalExpenseByCategory) {
        this.totalRevenues = totalRevenues;
        this.totalExpenses = totalExpenses;
        this.monthBalance = monthBalance;
        this.totalExpenseByCategory = totalExpenseByCategory;
    }

    public BigDecimal getTotalRevenues() {
        return totalRevenues;
    }

    public void setTotalRevenues(BigDecimal totalRevenues) {
        this.totalRevenues = totalRevenues;
    }

    public BigDecimal getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(BigDecimal totalExpenses) {
        this.totalExpenses = totalExpenses;
    }

    public BigDecimal getMonthBalance() {
        return monthBalance;
    }

    public void setMonthBalance(BigDecimal monthBalance) {
        this.monthBalance = monthBalance;
    }

    public Map<String, BigDecimal> getTotalExpenseByCategory() {
        return totalExpenseByCategory;
    }

    public void setTotalExpenseByCategory(Map<String, BigDecimal> totalExpenseByCategory) {
        this.totalExpenseByCategory = totalExpenseByCategory;
    }
}
