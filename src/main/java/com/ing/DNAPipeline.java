package com.ing;

import java.util.Set;
import java.util.stream.Collectors;

public class DNAPipeline {
    public boolean validateDNASequence(String dnaSequence) throws IllegalDNASequenceException {
        if (dnaSequence == null || "".equals(dnaSequence)) throw new IllegalDNASequenceException();
        return true;
    }

    public Set<Character> getCharSet(String dna) {
        return dna.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
    }
}
