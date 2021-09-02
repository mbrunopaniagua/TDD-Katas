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

    private static final Map<List<String>, String> PEPTIDE_BY_CODONS = new HashMap<>();
    static {
        PEPTIDE_BY_CODONS.put(Arrays.asList("UUU","UUC"), "Phe");
        PEPTIDE_BY_CODONS.put(Arrays.asList("UUA","UUG","CUU","CUC","CUA","CUG"), "Leu");
        PEPTIDE_BY_CODONS.put(Arrays.asList("AUU","AUC","AUA"), "Ile");
        PEPTIDE_BY_CODONS.put(Collections.singletonList("AUG"), "Met");
        PEPTIDE_BY_CODONS.put(Arrays.asList("GUU","GUC","GUA","GUG"), "Val");
        PEPTIDE_BY_CODONS.put(Arrays.asList("UCU","UCC","UCA","UCG","AGU","AGC"), "Ser");
        PEPTIDE_BY_CODONS.put(Arrays.asList("CCU","CCC","CCA","CCG"), "Pro");
        PEPTIDE_BY_CODONS.put(Arrays.asList("ACU","ACC","ACA","ACG"), "Thr");
        PEPTIDE_BY_CODONS.put(Arrays.asList("GCU","GCC","GCA","GCG"), "Ala");
        PEPTIDE_BY_CODONS.put(Arrays.asList("UAU","UAC"), "Tyr");
        PEPTIDE_BY_CODONS.put(Arrays.asList("UAA","UAG","UGA"), "Stop");
        PEPTIDE_BY_CODONS.put(Arrays.asList("CAU","CAC"), "His");
        PEPTIDE_BY_CODONS.put(Arrays.asList("CAA","CAG"), "Gln");
        PEPTIDE_BY_CODONS.put(Arrays.asList("AAU","AAC"), "Asn");
        PEPTIDE_BY_CODONS.put(Arrays.asList("AAA","AAG"), "Lys");
        PEPTIDE_BY_CODONS.put(Arrays.asList("GAU","GAC"), "Asp");
        PEPTIDE_BY_CODONS.put(Arrays.asList("GAA","GAG"), "Glu");
        PEPTIDE_BY_CODONS.put(Arrays.asList("UGU","UGC"), "Cys");
        PEPTIDE_BY_CODONS.put(Collections.singletonList("UGG"), "Trp");
        PEPTIDE_BY_CODONS.put(Arrays.asList("CGU","CGC","CGA","CGG","AGA","AGG"), "Arg");
        PEPTIDE_BY_CODONS.put(Arrays.asList("GGU","GGC","GGA","GGG"), "Gly");
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
        PEPTIDES.put("Stop","-");
        PEPTIDES.put("Thr","T");
        PEPTIDES.put("Trp","W");
        PEPTIDES.put("Tyr","Y");
        PEPTIDES.put("Val","V");
    }


    public boolean isValid(String dnaSequence) {
        return dnaSequence != null && !"".equals(dnaSequence)
                && getAllowedNucleotides().containsAll(getNucleotides(dnaSequence));
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

    public List<String> codons(String rnaSequence) {
        return Arrays.stream(rnaSequence.split("(?<=\\G...)"))
                .filter(codon -> codon.length() == 3)
                .collect(Collectors.toList());
    }

    public String toPeptide(String codon) {
        return PEPTIDE_BY_CODONS.keySet().stream()
                .filter(codons -> codons.contains(codon))
                .map(PEPTIDE_BY_CODONS::get)
                .collect(Collectors.joining());
    }

    public String toProtein(String dnaSequence) {
        final String rna = transcribe(dnaSequence);
        return codons(rna).stream()
                .map(this::toPeptide)
                .map(PEPTIDES::get)
                .collect(Collectors.joining());
    }
}
