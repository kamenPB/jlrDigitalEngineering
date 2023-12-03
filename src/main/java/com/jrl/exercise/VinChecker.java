package com.jrl.exercise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VinChecker {

    public boolean checkVinFormat (String vin) {
        /* example correct vin 0471958692
             The check digit is calculated by:
                ○ multiplying each digit by its position
                ○ adding the results together
                ○ then taking modulo 11 of the total.
         */
        Pattern vinPattern = Pattern.compile("[0-9]{9}-(1[0-9]|[0-9])");
        Matcher vinMatcher = vinPattern.matcher(vin);
        if(!vinMatcher.matches()) return false; // to avoid exceptions below

        int sum = 0;
        int counter = 0;
        int checkDigit = -1;
        for (char c : vin.toCharArray()) {
            if (counter < 9) {
                sum += Character.getNumericValue(c) * counter;
            } else {
                checkDigit = Integer.parseInt(vin.substring(1 + counter));
                break;
            }
            counter++;
        }

        return sum % 11 == checkDigit;
    }

}
