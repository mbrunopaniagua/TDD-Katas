package com.ing;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FizzBuzz {
    private static final Map<Predicate<String>, String> fizzBuzzReplacements = new LinkedHashMap<>();
    static {
        fizzBuzzReplacements.put(s -> Integer.parseInt(s) % 3 == 0 && Integer.parseInt(s) % 5 == 0, "FizzBuzz");
        fizzBuzzReplacements.put(s -> Integer.parseInt(s) % 3 == 0, "Fizz");
        fizzBuzzReplacements.put(s -> Integer.parseInt(s) % 5 == 0, "Buzz");
    }

    public List<String> calculate(List<String> numbers) {
        return numbers.stream()
                .map(this::calculate)
                .collect(Collectors.toList());
    }

    private String calculate(String number) {
        return fizzBuzzReplacements.keySet().stream()
                .filter(p -> p.test(number))
                .findFirst()
                .map(predicate -> fizzBuzzReplacements.get(predicate))
                .orElse(number);
    }
}
