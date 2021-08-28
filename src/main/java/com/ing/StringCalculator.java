package com.ing;

import java.util.stream.Stream;

public class StringCalculator {

    public int add(String numbers) {
        if ("".equals(numbers)) return 0;
        if (numbers.contains(",")) {
            return Stream.of(numbers.split(","))
                    .mapToInt(i -> Integer.parseInt(i))
                    .sum();
        }
        return Integer.parseInt(numbers);
    }
}
