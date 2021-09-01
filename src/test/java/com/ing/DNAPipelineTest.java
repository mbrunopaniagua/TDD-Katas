package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DNAPipelineTest {

    private DNAPipeline sut;

    @BeforeEach
    void setUp() {
        sut = new DNAPipeline();
    }

    @Test
    public void whenDNASequenceIsNull_ThenThrowsIllegalDNASequenceException() {
        final String dnaSequence = null;

        assertThrows(IllegalDNASequenceException.class, () -> sut.validateDNASequence(dnaSequence));
    }

    @Test
    public void whenDNASequenceIsEmpty_ThenThrowsIllegalDNASequenceException() {
        final String dnaSequence = "";

        assertThrows(IllegalDNASequenceException.class, () -> sut.validateDNASequence(dnaSequence));
    }

    @Test
    public void whenDNASequenceHasAllowedNucleotides_ThenReturnsTrue() throws IllegalDNASequenceException {
        final String dnaSequence = "ATCG";

        boolean isValid = sut.validateDNASequence(dnaSequence);

        assertTrue(isValid);
    }

    @Test
    public void whenDNASequenceHasNotAllowedNucleotides_ThenThrowsIllegalDNASequenceException() {
        final String dnaSequence = "ATCGB";

        assertThrows(IllegalDNASequenceException.class, () -> sut.validateDNASequence(dnaSequence));
    }

    @Test
    public void whenDNAIsRoom_ThenCharSetIsRom() {
        final String dna = "ROOM";
        final Set<Character> expected = new HashSet<>(Arrays.asList('R','O','M'));

        Set<Character> charSet = sut.getCharSet(dna);

        assertEquals(expected, charSet);
    }

    @Test
    public void givenADNASequenceJustWithTheNucleotideAWhenGetAntiSense_ThenGetASequenceWithTheNucleotideT() {
        final String dnaSequenceWithJustOneNucleotide = "A";
        final String expectedAntiSense = "T";

        String antiSense = sut.getAntiSense(dnaSequenceWithJustOneNucleotide);

        assertEquals(expectedAntiSense, antiSense);
    }

    @Test
    public void givenADNASequenceJustWithTheNucleotideTWhenGetAntiSense_ThenGetASequenceWithTheNucleotideA() {
        final String dnaSequenceWithJustOneNucleotide = "T";
        final String expectedAntiSense = "A";

        String antiSense = sut.getAntiSense(dnaSequenceWithJustOneNucleotide);

        assertEquals(expectedAntiSense, antiSense);
    }

    @Test
    public void givenADNASequenceJustWithTheNucleotideCWhenGetAntiSense_ThenGetASequenceWithTheNucleotideG() {
        final String dnaSequenceWithJustOneNucleotide = "C";
        final String expectedAntiSense = "G";

        String antiSense = sut.getAntiSense(dnaSequenceWithJustOneNucleotide);

        assertEquals(expectedAntiSense, antiSense);
    }

    @Test
    public void givenADNASequenceJustWithTheNucleotideGWhenGetAntiSense_ThenGetASequenceWithTheNucleotideC() {
        final String dnaSequenceWithJustOneNucleotide = "G";
        final String expectedAntiSense = "C";

        String antiSense = sut.getAntiSense(dnaSequenceWithJustOneNucleotide);

        assertEquals(expectedAntiSense, antiSense);
    }

    @Test
    public void givenADNASequenceWithAllNucleotidesWhenGetAntiSense_ThenGetAReversedSequenceWithTheNucleotidesProperlyChanged() {
        final String dnaSequenceWithJustOneNucleotide = "ATCG";
        final String expectedAntiSense = "CGAT";

        String antiSense = sut.getAntiSense(dnaSequenceWithJustOneNucleotide);

        assertEquals(expectedAntiSense, antiSense);
    }
}