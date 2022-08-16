package com.victor.noloosecoins.services;

import com.victor.noloosecoins.models.expense.Expense;
import com.victor.noloosecoins.models.revenues.Revenue;
import com.victor.noloosecoins.models.summary.Summary;
import com.victor.noloosecoins.repositories.ExpenseRepository;
import com.victor.noloosecoins.repositories.RevenueRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SummaryService {

    private final ExpenseRepository expenseRepository;
    private final RevenueRepository revenueRepository;

    public SummaryService(ExpenseRepository expenseRepository, RevenueRepository revenueRepository) {
        this.expenseRepository = expenseRepository;
        this.revenueRepository = revenueRepository;
    }

    public Summary getSummaryByMonth(int year, int month) {
        LocalDate initialDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.ofEpochDay(initialDate.toEpochDay()).plusMonths(1).withDayOfMonth(1).minusDays(1);
        List<Revenue> revenues = revenueRepository.findByDateBetween(initialDate, endDate);
        List<Expense> expenses = expenseRepository.findByDateBetween(initialDate, endDate);
        BigDecimal totalRevenuesValue = BigDecimal.ZERO;
        BigDecimal totalExpensesValue = BigDecimal.ZERO;
        Map<String, BigDecimal> totalExpenseByCategory = new HashMap<>();

        for (Revenue revenue : revenues) {
            totalRevenuesValue = totalRevenuesValue.add(revenue.getValue());
        }


        for (Expense expense : expenses) {
            totalExpensesValue = totalExpensesValue.add(expense.getValue());

            String expenseCategory = expense.getCategory().getName();
            BigDecimal categoryTotalExpenseValue;

            if (totalExpenseByCategory.containsKey(expenseCategory)) {
                categoryTotalExpenseValue = totalExpenseByCategory.get(expenseCategory);
                categoryTotalExpenseValue = categoryTotalExpenseValue.add(expense.getValue());
            } else {
                categoryTotalExpenseValue = expense.getValue();
            }
            totalExpenseByCategory.put(expenseCategory, categoryTotalExpenseValue);
        }

        BigDecimal monthBalance = totalRevenuesValue.subtract(totalExpensesValue);

        return new Summary(totalRevenuesValue, totalExpensesValue, monthBalance, totalExpenseByCategory);

    }
}
