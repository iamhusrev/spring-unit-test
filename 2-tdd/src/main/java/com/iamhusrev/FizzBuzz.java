package com.iamhusrev;

public class FizzBuzz {

    // If number is divisible by 3, print Fizz
    // If number is divisible by 5, print Buzz
    // If number is divisible by 3 and 5, print com.iamhusrev.FizzBuzz
    // If number is NOT divisible by 3 or 5, then print the number

    public static String compute(int i) {

        StringBuilder result = new StringBuilder();

        if (i % 3 == 0 && i % 5 == 0) {
            result.append("com.iamhusrev.FizzBuzz");
        } else if (i % 3 == 0) {
            result.append("Fizz");
        } else if (i % 5 == 0) {
            result.append("Buzz");

        } else if (result.isEmpty()) {
            result.append(i);
        }

        return result.toString();
    }

    /*
    public static String compute(int i) {

        if (i % 3 == 0 && i % 5 == 0) {
            return "com.iamhusrev.FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buzz";
        } else {
            return String.valueOf(i);
        }

    }

    */

}
