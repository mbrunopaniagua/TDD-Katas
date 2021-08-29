package com.ing;

import java.util.Collections;
import java.util.List;

public class FizzBuzz {

    List<String> calculate(List<String> numbers) {
        if (numbers.contains("3")) {
            Collections.replaceAll(numbers, "3", "Fizz");
            return numbers;
        }

        if (numbers.contains("5")) {
            Collections.replaceAll(numbers, "5", "Buzz");
            return numbers;
        }
        return numbers;
    }
}
