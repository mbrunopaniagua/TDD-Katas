package com.ing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz2 {

    private static final int NUM_OF_ELEMENTS = 100;

    public List<Integer> generate() {
        return IntStream.range(0, NUM_OF_ELEMENTS)
        .boxed()
        .collect(Collectors.toList());
    }
    
}
