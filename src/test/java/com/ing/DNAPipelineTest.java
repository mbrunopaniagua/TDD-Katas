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

    //calculate antisense sequence
}