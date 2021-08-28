package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StringCalculatorTest {

    private StringCalculator sut;

    @BeforeEach
    public void setup() {
        sut = new StringCalculator();
    }

    @ParameterizedTest(name = "#{index} - Sum {0} = {1}")
    @MethodSource("argumentsNumbersToInt")
    public void numbersToInt(String numbers, int expected) {
        int result = sut.add(numbers);

        assertEquals(expected, result);
    }

    private static Stream<Arguments> argumentsNumbersToInt() {
        return Stream.of(
                arguments("", 0),
                arguments("0", 0),
                arguments("1", 1),
                arguments("1,2", 3),
                arguments("1,2,3", 6)
        );
    }

}