package com.victor.noloosecoins.tools;

import com.victor.noloosecoins.exceptions.InvalidDateArgumentException;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class Validator {

    public static void validYearAndMonthRequest(int year, int month) {
        int yearLength = String.valueOf(year).length();
        int monthLength = String.valueOf(month).length();

        if(yearLength != 4 || monthLength < 1 || monthLength > 2 || month < 1 || month > 12){
            throw new InvalidDateArgumentException("Year must have 4 digits, and month must have 2 digits between 1 and 12");
        }
    }
}
