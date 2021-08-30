package com.ing;

public class DNAPipeline {
    public boolean validateDNASequence(String dnaSequence) throws IllegalDNASequenceException {
        if (dnaSequence == null || "".equals(dnaSequence)) throw new IllegalDNASequenceException();
        return true;
    }
}
