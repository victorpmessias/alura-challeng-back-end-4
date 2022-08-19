package com.victor.noloosecoins.services;

import com.victor.noloosecoins.models.category.Category;
import com.victor.noloosecoins.models.expense.Expense;
import com.victor.noloosecoins.models.revenues.Revenue;
import com.victor.noloosecoins.models.summary.Summary;
import com.victor.noloosecoins.repositories.ExpenseRepository;
import com.victor.noloosecoins.repositories.RevenueRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SummaryServiceTest {
    int year;
    int month;
    Summary expectedSummaryReturn;

    @Mock
    private  ExpenseRepository expenseRepository;
    @Mock
    private  RevenueRepository revenueRepository;

    @InjectMocks
    private SummaryService summaryService;

    @BeforeEach
     void setUp(){
        year = 2022;
        month = 8;

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month, 31);

        List<Revenue> revenuesReturn = List.of(
                new Revenue(1L, "Description", new BigDecimal("200.00"), LocalDate.of(year, month, 20)),
                new Revenue(2L, "Description", new BigDecimal("500.00"), LocalDate.of(year, month, 20)),
                new Revenue(2L, "Description", new BigDecimal("700.00"), LocalDate.of(year, month, 20)),
                new Revenue(2L, "Description", new BigDecimal("100.00"), LocalDate.of(year, month, 20)),
                new Revenue(2L, "Description", new BigDecimal("200.00"), LocalDate.of(year, month, 20)),
                new Revenue(2L, "Description", new BigDecimal("50000.00"), LocalDate.of(year, month, 20)),
                new Revenue(2L, "Description", new BigDecimal("500.00"), LocalDate.of(year, month, 20))
        );

        Category category = new Category(1L, "Outras");
        Category category1 = new Category(2L, "Alimentação");
        Category category2 = new Category(3L, "Contas");
        List<Expense> expensesReturn = List.of(
                new Expense(1L, "Description", new BigDecimal("100.00"), LocalDate.of(year, month, 20), category),
                new Expense(2L, "Description", new BigDecimal("500.00"), LocalDate.of(year, month, 20), category),
                new Expense(2L, "Description", new BigDecimal("800.00"), LocalDate.of(year, month, 20), category),
                new Expense(2L, "Description", new BigDecimal("700.00"), LocalDate.of(year, month, 20), category1),
                new Expense(2L, "Description", new BigDecimal("900.00"), LocalDate.of(year, month, 20), category1),
                new Expense(2L, "Description", new BigDecimal("200.00"), LocalDate.of(year, month, 20), category1),
                new Expense(2L, "Description", new BigDecimal("100.00"), LocalDate.of(year, month, 20), category2),
                new Expense(2L, "Description", new BigDecimal("3500.00"), LocalDate.of(year, month, 20), category2),
                new Expense(2L, "Description", new BigDecimal("150.00"), LocalDate.of(year, month, 20), category2),
                new Expense(2L, "Description", new BigDecimal("30.00"), LocalDate.of(year, month, 20), category2)
        );
        category.setExpenses(expensesReturn);

        when(revenueRepository.findByDateBetween(startDate, endDate)).thenReturn(revenuesReturn);
        when(expenseRepository.findByDateBetween(startDate, endDate)).thenReturn(expensesReturn);

        HashMap<String, BigDecimal> expectedSummaryMapSum = new HashMap<>();
        expectedSummaryMapSum.put("Outras", new BigDecimal("1400.00"));
        expectedSummaryMapSum.put("Alimentação", new BigDecimal("1800.00"));
        expectedSummaryMapSum.put("Contas", new BigDecimal("3780.00"));

        expectedSummaryReturn = new Summary(new BigDecimal("52200.00"), new BigDecimal("6980.00"), new BigDecimal("45220.00"), expectedSummaryMapSum);


    }

    @Test
    void mustReturnValueZeroWhenDontHaveExpensesAndRevenuesRegisteredInMonth() {
        int year = 2022;
        int month = 8;

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month, 31);
        List<Revenue> revenuesReturn = new ArrayList<>();
        List<Expense> expensesReturn = new ArrayList<>();
        when(revenueRepository.findByDateBetween(startDate, endDate)).thenReturn(revenuesReturn);
        when(expenseRepository.findByDateBetween(startDate, endDate)).thenReturn(expensesReturn);

        Summary expectedSummaryReturn = new Summary(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, new HashMap<String, BigDecimal>());

        Summary returnedSummary = summaryService.getSummaryByMonth(year, month);
        assertThat(returnedSummary.getMonthBalance(), is(equalTo(expectedSummaryReturn.getMonthBalance())));
    }

    @Test
    void mustReturnSummaryWithSumOfAllExpensesOfMonth() {
        Summary returnedSummary = summaryService.getSummaryByMonth(year, month);
        assertThat(returnedSummary.getTotalExpenses(), is(equalTo(expectedSummaryReturn.getTotalExpenses())));
    }

    @Test
    void mustReturnSummaryWithSumOfAllRevenuesOfMonth() {
        Summary returnedSummary = summaryService.getSummaryByMonth(year, month);
        assertThat(returnedSummary.getTotalRevenues(), is(equalTo(expectedSummaryReturn.getTotalRevenues())));
    }
    @Test
    void mustReturnSummaryWithMonthBalanceOfMonth() {
        Summary returnedSummary = summaryService.getSummaryByMonth(year, month);
        assertThat(returnedSummary.getMonthBalance(), is(equalTo(expectedSummaryReturn.getMonthBalance())));
    }

    @Test
    void mustReturnSummaryWithSumOfAllExpensesOfMonthByCategory() {
        Summary returnedSummary = summaryService.getSummaryByMonth(year, month);
        assertThat(returnedSummary.getTotalExpenseByCategory(), is(equalTo(expectedSummaryReturn.getTotalExpenseByCategory())));
    }
}
