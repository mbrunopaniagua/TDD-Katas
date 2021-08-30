package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FizzBuzzTest {

    private FizzBuzz sut;

    @BeforeEach
    void setUp() {
        sut = new FizzBuzz();
    }

    @Test
    public void whenInputNumbersHaveNeitherMultipleOf3Nor5_ThenReturnsTheSameList() {
        final List<String> numbers = Arrays.asList("1","2");
        final List<String> expected = Arrays.asList("1","2");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }

    @Test
    public void whenInputNumbersHaveAMultipleOf3_ThenReturnsTheSameListWithFizzInsteadOfTheNumber() {
        final List<String> numbers = Arrays.asList("3");
        final List<String> expected = Collections.singletonList("Fizz");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }

    @Test
    public void whenInputNumbersHaveAMultipleOf5_ThenReturnsTheSameListWithBuzzInsteadOfTheNumber() {
        final List<String> numbers = Arrays.asList("5");
        final List<String> expected = Collections.singletonList("Buzz");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }

    @Test
    public void whenInputNumbersHaveANumberBothMultipleOf3And5_ThenReturnsTheSameListWithFizzBuzzInsteadTheNumber() {
        final List<String> numbers = Collections.singletonList("15");
        final List<String> expected = Collections.singletonList("FizzBuzz");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }

    @Test
    public void whenInputNumbersHaveAllCases_ThenReturnsTheSameListWithFizzAndBuzzAndFizzBuzzInsteadTheNumber() {
        final List<String> numbers = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
        final List<String> expected = Arrays.asList("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }
}