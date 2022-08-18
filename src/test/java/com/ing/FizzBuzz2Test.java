package com.ing;

import org.junit.jupiter.api.Test;

public class FizzBuzz2Test {
    @Test
    public void canInstantiate() {
        new FizzBuzz2();
    }

    @Test
    public void respondsToGenerateMethod() {
        FizzBuzz2 fizzBuzz = new FizzBuzz2();
        fizzBuzz.generate();
    }
}
