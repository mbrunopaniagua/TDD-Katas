package com.ing;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz2 {

    private static final int NUM_OF_ELEMENTS = 100;

    public List<String> generate() {
        List<String> list = new ArrayList<>(NUM_OF_ELEMENTS);
        for (int i = 0; i < NUM_OF_ELEMENTS; i++) {
            int number = i+1; 
            String element = String.valueOf(number);
            
            if (number == 3) {
                element = "Fizz";
            }

            if (number == 6) {
                element = "Fizz";
            }

            if (number == 9) {
                element = "Fizz";
            }

            list.add(i, element);
        }
        return list;
    }
    
}
