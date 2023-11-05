package com.jrl.exercise;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class VINValidatorController {

    @GetMapping("/")
    public String index() {
        return checkVinFormat("0471958692");
    }

    public String checkVinFormat (String vin) {
        /* example correct vin 0471958692
             The check digit is calculated by:
                ○ multiplying each digit by its position
                ○ adding the results together
                ○ then taking modulo 11 of the total.

                0 * 0 = 0
                4 * 1 = 4
                7 * 2 = 14
                1 * 3 = 3
                9 * 4 = 36
                5 * 5 = 25
                8 * 6 = 48
                6 * 7 = 42
                9 * 8 = 72
                sum = 244
                244 % 11 = 2

                what if sum % 11 = 10
         */
        Pattern vinPattern = Pattern.compile("\\d{10}");
        Matcher vinMatcher = vinPattern.matcher(vin);
        return String.valueOf(vinMatcher.matches());
    }
}
