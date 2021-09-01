package com.ing;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    private static final Map<List<String>, String> CODONS = new HashMap<>();
    static {
        CODONS.put(Arrays.asList("UUU","UUC"), "Phe");
        CODONS.put(Arrays.asList("UUA","UUG","CUU","CUC","CUA","CUG"), "Leu");
        CODONS.put(Arrays.asList("AUU","AUC","AUA"), "Ile");
        CODONS.put(Collections.singletonList("AUG"), "Met");
        CODONS.put(Arrays.asList("GUU","GUC","GUA","GUG"), "Val");
        CODONS.put(Arrays.asList("UCU","UCC","UCA","UCG","AGU","AGC"), "Ser");
        CODONS.put(Arrays.asList("CCU","CCC","CCA","CCG"), "Pro");
        CODONS.put(Arrays.asList("ACU","ACC","ACA","ACG"), "Thr");
        CODONS.put(Arrays.asList("GCU","GCC","GCA","GCG"), "Ala");
        CODONS.put(Arrays.asList("UAU","UAC"), "Tyr");
        CODONS.put(Arrays.asList("UAA","UAG","UGA"), "Stop");
        CODONS.put(Arrays.asList("CAU","CAC"), "His");
        CODONS.put(Arrays.asList("CAA","CAG"), "Gln");
        CODONS.put(Arrays.asList("AAU","AAC"), "Asn");
        CODONS.put(Arrays.asList("AAA","AAG"), "Lys");
        CODONS.put(Arrays.asList("GAU","GAC"), "Asp");
        CODONS.put(Arrays.asList("GAA","GAG"), "Glu");
        CODONS.put(Arrays.asList("UGU","UGC"), "Cys");
        CODONS.put(Collections.singletonList("UGG"), "Trp");
        CODONS.put(Arrays.asList("CGU","CGC","CGA","CGG","AGA","AGG"), "Arg");
        CODONS.put(Arrays.asList("GGU","GGC","GGA","GGG"), "Gly");
    }

    private static final Map<String, String> PEPTIDES = new HashMap<>();
    static {
        PEPTIDES.put("Ala","A");
        PEPTIDES.put("Arg","R");
        PEPTIDES.put("Asn","N");
        PEPTIDES.put("Asp","D");
        PEPTIDES.put("Asx","B");
        PEPTIDES.put("Cys","C");
        PEPTIDES.put("Glu","E");
        PEPTIDES.put("Gln","Q");
        PEPTIDES.put("Glx","Z");
        PEPTIDES.put("Gly","G");
        PEPTIDES.put("His","H");
        PEPTIDES.put("Ile","I");
        PEPTIDES.put("Leu","L");
        PEPTIDES.put("Lys","K");
        PEPTIDES.put("Met","M");
        PEPTIDES.put("Phe","F");
        PEPTIDES.put("Pro","P");
        PEPTIDES.put("Ser","S");
        PEPTIDES.put("Thr","T");
        PEPTIDES.put("Trp","W");
        PEPTIDES.put("Tyr","Y");
        PEPTIDES.put("Val","V");
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
