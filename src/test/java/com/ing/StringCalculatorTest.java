package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayNameGeneration(CustomDisplayNameGenerator.ReplaceCamelCase.class)
class StringCalculatorTest {

    private StringCalculator sut;

    @BeforeEach
    public void setup() {
        sut = new StringCalculator();
    }

    @ParameterizedTest(name = "#{index} - Sum \"{0}\" = {1}")
    @MethodSource("argumentsNumbersToInt")
    public void numbersToInt(String numbers, int expected) {
        int result = sut.add(numbers);

        assertEquals(expected, result);
    }

    /**
     *
     * Partial sum = n*(n+1)/2
     */
    private static Stream<Arguments> argumentsNumbersToInt() {
        return Stream.of(
                arguments("", 0),
                arguments("0", 0),
                arguments("1", 1),
                arguments("1,2", 3),
                arguments("1,2,3", 6),
                arguments("1\n2,3", 6),
                arguments("1,2,3,4,5,6,7,8,9,10", 55)
        );
    }

    @Test
    public void givenAListOfNumbersWithANegativeValueWhenAddAllOfThemThenThrowsException() {
        final String numbers = "-1,0,1,2";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> sut.add(numbers));
        assertEquals("Negatives not allowed: -1", exception.getMessage());
    }

    @Test
    public void givenAListOfNumbersWithAValueBiggerThan1000WhenAddAllOfThemThenMustBeIgnoredThatValue() {
        final String numbers = "1,2,1001,3";
        final int expected = 6;

        int result = sut.add(numbers);

        assertEquals(expected, result);
    }

    @Test
    public void givenAListOfNumbersWithAValueLessOrEqualsThan1000WhenAddAllOfThemThenMustNotBeIgnoredThatValue() {
        final String numbers = "1,2,1000,3";
        final int expected = 1006;

        int result = sut.add(numbers);

        assertEquals(expected, result);
    }

}