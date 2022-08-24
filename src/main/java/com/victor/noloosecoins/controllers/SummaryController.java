package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.models.summary.Summary;
import com.victor.noloosecoins.services.SummaryService;
import com.victor.noloosecoins.tools.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("summary")
public class SummaryController {
    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("/{year}/{month}")
    public Summary getSummaryByMonth(@PathVariable int year, @PathVariable int month){
        Validator.validYearAndMonthRequest(year,month);
        return summaryService.getSummaryByMonth(year, month);
    }
}
