package com.ing;

import java.util.stream.Stream;

public class StringCalculator {

    public int add(String numbers) {
        if ("".equals(numbers)) return 0;
        return Stream.of(numbers.split(","))
                .mapToInt(Integer::parseInt)
                .sum();


    }
}
