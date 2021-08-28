package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {

    private StringCalculator sut;

    @BeforeEach
    public void setup() {
        sut = new StringCalculator();
    }

    @Test
    public void whenNumbersIsEmpty_ThenIsZero() {
        final String numbers = "";
        final int expected = 0;

        int result = sut.add(numbers);

        assertEquals(expected, result);
    }

    @Test
    public void whenNumbersHasAUniqueNumber_ThenReturnsTheSameValue() {
        final String numbers = "1";
        final int expected = 1;

        int result = sut.add(numbers);

        assertEquals(expected, result);
    }

    @Test
    public void whenNumbersHasSeven_ThenReturnsTheSameValue() {
        final String numbers = "7";
        final int expected = 7;

        int result = sut.add(numbers);

        assertEquals(expected, result);
    }

    @Test
    public void whenNumbersHasOneAndTwo_ThenReturnsThree() {
        final String numbers = "1,2";
        final int expected = 3;

        int result = sut.add(numbers);

        assertEquals(expected, result);
    }

}