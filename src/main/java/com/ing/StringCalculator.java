package com.ing;

import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class StringCalculator {
    private final ToIntFunction<String> lambdaFromStringToInt = (n) -> "".equals(n) ? 0 : Integer.parseInt(n);

    public int add(String numbers) {
        return Stream.of(numbers.split(","))
                .mapToInt(lambdaFromStringToInt)
                .sum();
    }
}
