package com.ing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public List<String> toPolypeptides(String dna) throws IllegalDNAException {
        if (!isValid(dna)) throw new IllegalDNAException();

        final String antiSense = antiSense(dna);
        final String rnaFromAntisense = transcribe(antiSense);
        final String rnaFromDna = transcribe(dna);
        List<String> polypeptidesFromAntisense = toPolypeptidesByFrame(rnaFromAntisense);
        List<String> polypeptidesFromRna = toPolypeptidesByFrame(rnaFromDna);
        return Stream.of(polypeptidesFromAntisense, polypeptidesFromRna).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private boolean isValid(String dna) {
        return dna != null && !"".equals(dna)
                && getAllowedNucleotides().containsAll(getNucleotides(dna));
    }

    private Set<Character> getNucleotides(String dna) {
        return dna.chars().mapToObj(c -> (char)c).collect(Collectors.toSet());
    }

    private String antiSense(String dna) {
        return translateReversedSequenceFromFunction(dna, RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE::get);
    }

    private String transcribe(String dna) {
        return translateReversedSequenceFromFunction(dna, RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_TRANSCRIBE::get);
    }

    private Set<Character> getAllowedNucleotides() {
        return RELATIONSHIP_BETWEEN_NUCLEOTIDES_FOR_ANTISENSE.keySet();
    }

    private String translateReversedSequenceFromFunction(String dna, Function<Character,Character> translationFunction) {
        return new StringBuilder(dna.toUpperCase()).reverse().chars()
                .mapToObj(c -> (char)c)
                .map(translationFunction)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private List<String> toPolypeptidesByFrame(String sequence) {
        return Stream.of(1, 2, 3)
                .map(frame -> sequenceCodonSplitting(sequence, frame))
                .map(this::polypeptideTranslation)
                .collect(Collectors.toList());
    }

    private String polypeptideTranslation(List<String> codons) {
        List<String> polypeptide = codons.stream().map(this::fromCodonToPeptide).collect(Collectors.toList());
        return polypeptide.stream().map(PEPTIDES::get).collect(Collectors.joining());
    }

    private String fromCodonToPeptide(String codon) {
        return PEPTIDE_BY_CODONS.keySet().stream()
                .filter(codons -> codons.contains(codon))
                .map(PEPTIDE_BY_CODONS::get)
                .collect(Collectors.joining());
    }

    private List<String> sequenceCodonSplitting(String dna, int frame) {
        final String dnaSequenceByFrame = dna.substring(frame-1);
        return codons(dnaSequenceByFrame);
    }

    private List<String> codons(String rna) {
        return Arrays.stream(rna.split("(?<=\\G...)"))
                .filter(codon -> codon.length() == 3)
                .collect(Collectors.toList());
    }
}
