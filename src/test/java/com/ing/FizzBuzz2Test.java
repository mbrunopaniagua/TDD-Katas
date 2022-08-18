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
        
        List<String> list = sut.generate(); 
        
        assertEquals(100, list.size());
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithTheOneInThePositionZero() {
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("1", list.get(0));
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithThreeIsRepresentedAsFizz() {
        final int number = 3;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("Fizz", list.get(number - 1));
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithSixIsRepresentedAsFizz() {
        final int number = 6;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("Fizz", list.get(number - 1));
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithNineIsRepresentedAsFizz() {
        final int number = 9;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("Fizz", list.get(number - 1));
    }
}