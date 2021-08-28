package com.ing;

public class StringCalculator {

    public int add(String numbers) {
        if ("".equals(numbers)) return 0;
        if (numbers.contains(",")) {
            String[] numbersSplit = numbers.split(",");
            return Integer.parseInt(numbersSplit[0]) + Integer.parseInt(numbersSplit[1]);
        }
        return Integer.parseInt(numbers);
    }
}
