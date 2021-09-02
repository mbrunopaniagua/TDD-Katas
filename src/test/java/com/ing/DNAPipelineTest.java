package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(CustomDisplayNameGenerator.ReplaceCamelCase.class)
class DNAPipelineTest {

    private DNAPipeline sut;

    @BeforeEach
    void setUp() {
        sut = new DNAPipeline();
    }

    @Test
    public void givenANullDnaSequenceWhenValidateItThenThrowsAnIllegalDnaSequenceException() {
        final String dnaSequence = null;

        assertThrows(IllegalDNASequenceException.class, () -> sut.validateDNASequence(dnaSequence));
    }

    @Test
    public void givenAnEmptyDnaSequenceWhenValidateItThenThrowsAnIllegalDnaSequenceException() {
        final String dnaSequence = "";

        assertThrows(IllegalDNASequenceException.class, () -> sut.validateDNASequence(dnaSequence));
    }

    @Test
    public void givenADnaSequenceWithAllAllowedNucleotidesWhenValidateItThenIsValid() throws IllegalDNASequenceException {
        final String dnaSequence = "ATCG";

        boolean isValid = sut.validateDNASequence(dnaSequence);

        assertTrue(isValid);
    }

    @Test
    public void givenAnDnaSequenceWithSomeNotAllowedNucleotideWhenValidateItThenThrowsAnIllegalDnaSequenceException() {
        final String dnaSequence = "ATCGB";

        assertThrows(IllegalDNASequenceException.class, () -> sut.validateDNASequence(dnaSequence));
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
}