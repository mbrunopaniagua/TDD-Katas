package com.ing;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz2 {

    private static final int NUM_OF_ELEMENTS = 100;
    private static final Map<Predicate<Integer>, String> replacementsRules = new LinkedHashMap<>();
    static {
        replacementsRules.put(number -> number % 15 == 0, "FizzBuzz");
        replacementsRules.put(number -> number % 3 == 0, "Fizz");
        replacementsRules.put(number -> number % 5 == 0, "Buzz");
    }

    public List<String> generate() {
        return IntStream.rangeClosed(1, NUM_OF_ELEMENTS)
        .boxed()
        .map(this::calculate)
        .collect(Collectors.toList());
    }
    
    private String calculate(int number) {
        return replacementsRules.keySet().stream()
            .filter(rule -> rule.test(number))
            .findFirst()
            .map(rule -> replacementsRules.get(rule))
            .orElse(String.valueOf(number));
    }
    
}
