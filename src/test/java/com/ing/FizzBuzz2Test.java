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

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithFiveIsRepresentedAsBuzz() {
        final int number = 5;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("Buzz", list.get(number - 1));
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithTenIsRepresentedAsBuzz() {
        final int number = 10;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("Buzz", list.get(number - 1));
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithTwentyIsRepresentedAsBuzz() {
        final int number = 20;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("Buzz", list.get(number - 1));
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithFifteenIsRepresentedAsFizzBuzz() {
        final int number = 15;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("FizzBuzz", list.get(number - 1));
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithThirtyIsRepresentedAsFizzBuzz() {
        final int number = 30;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("FizzBuzz", list.get(number - 1));
    }

    @Test
    public void whenGenerateFizzBuzzThenReturnAListWithFortyFiveIsRepresentedAsFizzBuzz() {
        final int number = 45;
        FizzBuzz2 sut = new FizzBuzz2();
        
        List<String> list = sut.generate(); 
    
        assertEquals("FizzBuzz", list.get(number - 1));
    }
}