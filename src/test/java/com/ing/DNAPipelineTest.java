package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayNameGeneration(CustomDisplayNameGenerator.ReplaceCamelCase.class)
class DNAPipelineTest {

    private DNAPipeline sut;

    @BeforeEach
    void setUp() {
        sut = new DNAPipeline();
    }

    @Test
    public void givenANullDnaSequenceThenIsNotValid() {
        final String dnaSequence = null;
        final boolean expectedIsValid = false;

        boolean isValid = sut.isValid(dnaSequence);

        assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void givenAnEmptyDnaSequenceThenIsNotValid() {
        final String dnaSequence = "";
        final boolean expectedIsValid = false;

        boolean isValid = sut.isValid(dnaSequence);

        assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void givenADnaSequenceWithAllAllowedNucleotidesThenIsValid() {
        final String dnaSequence = "ATCG";
        final boolean expectedIsValid = true;

        boolean isValid = sut.isValid(dnaSequence);

        assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void givenAnDnaSequenceWithSomeNotAllowedNucleotideThenIsNotValid() {
        final String dnaSequence = "ATCGB";
        final boolean expectedIsValid = false;

        boolean isValid = sut.isValid(dnaSequence);

        assertEquals(expectedIsValid, isValid);
    }

    @Test
    public void givenADnaSequenceWithRepeatedNucleotidesWhenGetNucleotidesThenGetsASetNucleotides() {
        final String dnaSequence = "TTATTTGGGCATCC";
        final Set<Character> expectedNucleotides = new HashSet<>(Arrays.asList('T','A','G','C'));

        Set<Character> nucleotides = sut.getNucleotides(dnaSequence);

        assertEquals(expectedNucleotides, nucleotides);
    }

    @Test
    public void givenADnaSequenceWithAllNucleotidesWhenGetAntiSenseThenGetAReversedSequenceWithTheNucleotidesProperlyChanged() {
        final String dnaSequence = "ATCG";
        final String expectedAntiSense = "CGAT";

        String antiSense = sut.antiSense(dnaSequence);

        assertEquals(expectedAntiSense, antiSense);
    }

    @Test
    public void givenADnaSequenceWithAllNucleotidesThenReturnsAReverseSequenceWhenIsTranscribed() {
        final String dnaSequenceWithAdenine = "TTATGCATC";
        final String rnaSequenceExpected = "GAUGCAUAA";

        String rnaSequence = sut.transcribe(dnaSequenceWithAdenine);

        assertEquals(rnaSequenceExpected, rnaSequence);
    }

    @Test
    public void givenARnaSequenceComposedBy3NucleotidesWhenDivideItInCodonsThenReturnsASingletonList() {
        final String rnaSequence = "GAU";
        final List<String> expectedCodons = Collections.singletonList("GAU");

        List<String> codons = sut.codons(rnaSequence);

        assertEquals(expectedCodons, codons);
    }

    @Test
    public void givenARnaSequenceComposedBy6NucleotidesWhenDivideItInCodonsThenReturnsATwoCodons() {
        final String rnaSequence = "GAUAUC";
        final List<String> expectedCodons = Arrays.asList("GAU", "AUC");

        List<String> codons = sut.codons(rnaSequence);

        assertEquals(expectedCodons, codons);
    }

    @Test
    public void givenARnaSequenceComposedBy8NucleotidesWhenDivideItInCodonsThenReturns2CodonsAndTheLast2NucleotidesAreDiscarded() {
        final String rnaSequence = "GAUAUCAA";
        final List<String> expectedCodons = Arrays.asList("GAU", "AUC");

        List<String> codons = sut.codons(rnaSequence);

        assertEquals(expectedCodons, codons);
    }

    @ParameterizedTest(name = "#{index} - Codon \"{0}\" = Peptide \"{1}\"")
    @MethodSource("argumentsFromCodonToPeptide")
    public void codonsToPeptide(String codon, String expectedPeptide) {
        String peptide = sut.toPeptide(codon);

        assertEquals(expectedPeptide, peptide);
    }

    private static Stream<Arguments> argumentsFromCodonToPeptide() {
        return Stream.of(
                arguments("UUU", "Phe"),
                arguments("UUC", "Phe"),
                arguments("UUA", "Leu"),
                arguments("UUG", "Leu"),
                arguments("CUU", "Leu"),
                arguments("CUC", "Leu"),
                arguments("CUA", "Leu"),
                arguments("CUG", "Leu"),
                arguments("AUU", "Ile"),
                arguments("AUC", "Ile"),
                arguments("AUA", "Ile"),
                arguments("AUG", "Met"),
                arguments("GUU", "Val"),
                arguments("GUC", "Val"),
                arguments("GUA", "Val"),
                arguments("GUG", "Val"),
                arguments("UCU", "Ser"),
                arguments("UCC", "Ser"),
                arguments("UCA", "Ser"),
                arguments("UCG", "Ser"),
                arguments("AGU", "Ser"),
                arguments("AGC", "Ser"),
                arguments("CCU", "Pro"),
                arguments("CCC", "Pro"),
                arguments("CCA", "Pro"),
                arguments("CCG", "Pro"),
                arguments("ACU", "Thr"),
                arguments("ACC", "Thr"),
                arguments("ACA", "Thr"),
                arguments("ACG", "Thr"),
                arguments("GCU", "Ala"),
                arguments("GCC", "Ala"),
                arguments("GCA", "Ala"),
                arguments("GCG", "Ala"),
                arguments("UAU", "Tyr"),
                arguments("UAC", "Tyr"),
                arguments("UAA", "Stop"),
                arguments("UAG", "Stop"),
                arguments("UGA", "Stop"),
                arguments("CAU", "His"),
                arguments("CAC", "His"),
                arguments("CAA", "Gln"),
                arguments("CAG", "Gln"),
                arguments("AAU", "Asn"),
                arguments("AAC", "Asn"),
                arguments("AAA", "Lys"),
                arguments("AAG", "Lys"),
                arguments("GAU", "Asp"),
                arguments("GAC", "Asp"),
                arguments("GAA", "Glu"),
                arguments("GAG", "Glu"),
                arguments("UGU", "Cys"),
                arguments("UGC", "Cys"),
                arguments("UGG", "Trp"),
                arguments("CGU", "Arg"),
                arguments("CGC", "Arg"),
                arguments("CGA", "Arg"),
                arguments("CGG", "Arg"),
                arguments("AGA", "Arg"),
                arguments("AGG", "Arg"),
                arguments("GGU", "Gly"),
                arguments("GGC", "Gly"),
                arguments("GGA", "Gly"),
                arguments("GGG", "Gly")
        );
    }
}