package com.ing;

import java.util.function.IntPredicate;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

public class StringCalculator {
    private final ToIntFunction<String> lambdaFromStringToInt = n -> "".equals(n) ? 0 : Integer.parseInt(n);
    private final IntPredicate lambdaFilter = n -> {
      if (n < 0) throw new IllegalArgumentException("Negatives not allowed: " + n);
      return n <= 1000;
    };

    public int add(String numbers) {
        return Stream.of(numbers.split("[,\n]"))
                .mapToInt(lambdaFromStringToInt)
                .filter(lambdaFilter)
                .sum();
    }
}
