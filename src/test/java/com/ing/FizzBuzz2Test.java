package com.ing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class FizzBuzz2Test {
    @Test
    public void respondsToGenerateMethod() {
        FizzBuzz2 fizzBuzz = new FizzBuzz2();
        fizzBuzz.generate();
    }
    
    @Test
    public void whenGenerateThenReturnsAListOf100Elements() {
        FizzBuzz2 fizzBuzz = new FizzBuzz2();
        List<Integer> list = fizzBuzz.generate(); 
        assertEquals(100, list.size());
    }
}