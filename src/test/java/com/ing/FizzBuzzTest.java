package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {

    private FizzBuzz sut;

    @BeforeEach
    void setUp() {
        sut = new FizzBuzz();
    }

    @Test
    public void whenInputNumbersHaveJustOneAndTwo_ThenReturnsTheSameList() {
        final List<String> numbers = Arrays.asList("1","2");
        final List<String> expected = Arrays.asList("1","2");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }
}