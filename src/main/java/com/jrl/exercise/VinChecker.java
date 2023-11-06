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
