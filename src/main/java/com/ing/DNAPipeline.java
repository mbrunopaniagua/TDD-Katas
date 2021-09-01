package com.ing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DNAPipeline {
    private static final Map<Character, Character> RELATIONSHIP_BETWEEN_NUCLEOTIDES = new HashMap<>();
    static {
        RELATIONSHIP_BETWEEN_NUCLEOTIDES.put('A', 'T');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES.put('T', 'A');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES.put('C', 'G');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES.put('G', 'C');
    }

    public boolean validateDNASequence(String dnaSequence) throws IllegalDNASequenceException {
        if (dnaSequence == null || "".equals(dnaSequence)
                || !getAllowedNucleotides().containsAll(getCharSet(dnaSequence))) throw new IllegalDNASequenceException();
        return true;
    }

    private Set<Character> getAllowedNucleotides() {
        return RELATIONSHIP_BETWEEN_NUCLEOTIDES.keySet();
    }

    public Set<Character> getCharSet(String dnaSequence) {
        return dnaSequence.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
    }

    public String getAntiSense(String dnaSequence) {
        return new StringBuilder(dnaSequence.toUpperCase()).reverse().chars()
                .mapToObj(c -> (char)c)
                .map(RELATIONSHIP_BETWEEN_NUCLEOTIDES::get)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
