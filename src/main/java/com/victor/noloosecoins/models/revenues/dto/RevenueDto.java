package com.victor.noloosecoins.models.revenues.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.noloosecoins.models.revenues.Revenue;

import java.math.BigDecimal;
import java.time.LocalDate;

public class RevenueDto {
    private final Long id;
    private final String description;
    private final BigDecimal value;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private final LocalDate date;


    public RevenueDto(Revenue revenue){
        this.id  = revenue.getId();
        this.description = revenue.getDescription();
        this.value = revenue.getValue();
        this.date = revenue.getDate();
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
