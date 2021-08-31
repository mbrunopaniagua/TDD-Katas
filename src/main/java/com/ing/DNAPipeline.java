package com.ing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DNAPipeline {
    private static final Set<Character> ALLOWED_NUCLEOTIDES = new HashSet<>(Arrays.asList('A','T','C', 'G'));

    public boolean validateDNASequence(String dnaSequence) throws IllegalDNASequenceException {
        if (dnaSequence == null || "".equals(dnaSequence)
                || !ALLOWED_NUCLEOTIDES.containsAll(getCharSet(dnaSequence))) throw new IllegalDNASequenceException();
        return true;
    }

    public Set<Character> getCharSet(String dnaSequence) {
        return dnaSequence.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
    }
}
