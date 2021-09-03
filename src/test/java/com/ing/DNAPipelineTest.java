package com.ing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(CustomDisplayNameGenerator.ReplaceCamelCase.class)
class DNAPipelineTest {

    private DNAPipeline sut;

    @BeforeEach
    void setUp() {
        sut = new DNAPipeline();
    }

    @Test
    public void givenANullDnaWhenTranslateToPolypeptideThenThrowsIllegalDnaException() {
        final String dnaSequence = null;

        assertThrows(IllegalDNAException.class, () -> sut.toPolypeptides(dnaSequence));
    }

    @Test
    public void givenAnEmptyDnaWhenTranslateToPolypeptideThenThrowsIllegalDnaException() {
        final String dnaSequence = "";

        assertThrows(IllegalDNAException.class, () -> sut.toPolypeptides(dnaSequence));
    }

    @Test
    public void givenADnaWithSomeNotAllowedNucleotideWhenTranslateToPolypeptideThenThrowsIllegalDnaException() {
        final String dnaSequence = "ATCGB";

        assertThrows(IllegalDNAException.class, () -> sut.toPolypeptides(dnaSequence));
    }

    @Test
    public void givenADnaSequenceWhenTranslateIntoPolypeptidesThenReturnsAListOfPolypeptides() throws IllegalDNAException {
        final String dna = "TTAGGGCATG";
        final List<String> expectedPolypeptides = Arrays.asList("LGH","-GM","RA","HAL","MP-","CP");

        List<String> polypeptides = sut.toPolypeptides(dna);

        assertEquals(expectedPolypeptides, polypeptides);
    }
}