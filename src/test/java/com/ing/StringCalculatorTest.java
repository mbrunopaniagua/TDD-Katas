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

}