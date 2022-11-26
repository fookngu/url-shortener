package net.toiditimtoi.urlshortener.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sha256SimpleDigestTest {

    @Test
    void testStableHashing() {
        var simpleDigest = new Sha256SimpleDigest();
        var input = "https://github.com/kukot";
        var expectedOutput = "6dM0R0F";
        assertEquals(expectedOutput, simpleDigest.hash(input));
    }
}