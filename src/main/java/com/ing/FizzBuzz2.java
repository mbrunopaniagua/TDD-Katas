package com.ing;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FizzBuzz2 {

    public List<Integer> generate() {
        return IntStream.range(0, 100)
        .boxed()
        .collect(Collectors.toList());
    }
    
}
