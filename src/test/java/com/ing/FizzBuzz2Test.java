package com.ing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class FizzBuzz2Test {
    @Test
    public void respondsToGenerateMethod() {
        FizzBuzz2 sut = new FizzBuzz2();
        sut.generate();
    }
    
    @Test
    public void whenGenerateThenReturnsAListOfElements() {
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<Integer> list = sut.generate(); 
        
        assertEquals(100, list.size());
    }
}