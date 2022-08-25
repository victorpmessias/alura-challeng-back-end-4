package com.victor.noloosecoins.models.revenues.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.victor.noloosecoins.models.revenues.Revenue;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NewRevenueForm {

    @NotBlank(message = "description can't be blank")
    @Length(min = 0 , max = 255)
    private String description;
    @NotNull(message = "value field is required")
    @Digits(integer = 9, fraction = 2)
    private BigDecimal value;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "date field is required")
    private LocalDate date;

    public NewRevenueForm() {
    }

    public NewRevenueForm(String description, BigDecimal value, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
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


    public Revenue convertToRevenueEntity(){
        Revenue revenue = new Revenue();
        revenue.setDescription(this.description);
        revenue.setDate(this.date);
        revenue.setValue(this.value);
        return revenue;
    }
    public Revenue convertToRevenueEntity(Long id){
        Revenue revenue = new Revenue();
        revenue.setId(id);
        revenue.setDescription(this.description);
        revenue.setDate(this.date);
        revenue.setValue(this.value);
        return revenue;
    }
}
