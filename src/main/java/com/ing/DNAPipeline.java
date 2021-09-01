package com.ing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DNAPipeline {
    private static final Map<Character, Character> RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE = new HashMap<>();
    static {
        RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE.put('A', 'T');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE.put('T', 'A');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE.put('C', 'G');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE.put('G', 'C');
    }

    private static final Map<Character, Character> RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_TRANSCRIBE = new HashMap<>();
    static {
        RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_TRANSCRIBE.put('A', 'U');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_TRANSCRIBE.put('T', 'A');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_TRANSCRIBE.put('C', 'G');
        RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_TRANSCRIBE.put('G', 'C');
    }

    public boolean validateDNASequence(String dnaSequence) throws IllegalDNASequenceException {
        if (dnaSequence == null || "".equals(dnaSequence)
                || !getAllowedNucleotides().containsAll(getNucleotides(dnaSequence))) throw new IllegalDNASequenceException();
        return true;
    }

    public Set<Character> getNucleotides(String dnaSequence) {
        return dnaSequence.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
    }

    public String antiSense(String dnaSequence) {
        return translateReversedSequenceFromFunction(dnaSequence, RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE::get);
    }

    public String transcribe(String dnaSequence) {
        return translateReversedSequenceFromFunction(dnaSequence, RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_TRANSCRIBE::get);
    }

    private Set<Character> getAllowedNucleotides() {
        return RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE.keySet();
    }

    private String translateReversedSequenceFromFunction(String dnaSequence, Function<Character,Character> translationFunction) {
        return new StringBuilder(dnaSequence.toUpperCase()).reverse().chars()
                .mapToObj(c -> (char)c)
                .map(translationFunction)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
