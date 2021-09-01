package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(CustomDisplayNameGenerator.ReplaceCamelCase.class)
class FizzBuzzTest {

    private FizzBuzz sut;

    @BeforeEach
    void setUp() {
        sut = new FizzBuzz();
    }

    @Test
    public void givenAListWithoutMultipleOf3Nor5WhenCalculateFizzBuzzThenReturnsTheSameList() {
        final List<String> numbers = Arrays.asList("1","2");
        final List<String> expected = Arrays.asList("1","2");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }

    @Test
    public void givenAListWithANumberMultipleOf3WhenCalculateFizzBuzzThenReturnsTheSameListWithFizzInsteadOfTheNumber() {
        final List<String> numbers = Collections.singletonList("3");
        final List<String> expected = Collections.singletonList("Fizz");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }

    @Test
    public void givenAListWithANumberMultipleOf5WhenCalculateFizzBuzzThenReturnsTheSameListWithBuzzInsteadOfTheNumber() {
        final List<String> numbers = Collections.singletonList("5");
        final List<String> expected = Collections.singletonList("Buzz");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }

    @Test
    public void givenAListWithANumberBothMultipleOf3And5WhenCalculateFizzBuzzThenReturnsTheSameListWithFizzBuzzInsteadTheNumber() {
        final List<String> numbers = Collections.singletonList("15");
        final List<String> expected = Collections.singletonList("FizzBuzz");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }

    @Test
    public void givenAListFrom1To15WhenCalculateFizzBuzzThenReturnsTheSameListWithFizzAndBuzzAndFizzBuzzInsteadTheNumber() {
        final List<String> numbers = Arrays.asList("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15");
        final List<String> expected = Arrays.asList("1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz");

        List<String> fizzBuzzList = sut.calculate(numbers);

        assertEquals(expected, fizzBuzzList);
    }
}