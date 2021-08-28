package com.ing;

import java.util.stream.Stream;

public class StringCalculator {

    public int add(String numbers) {
        return Stream.of(numbers.split(","))
                .mapToInt(n -> "".equals(n) ? 0 : Integer.parseInt(n))
                .sum();
    }
}
