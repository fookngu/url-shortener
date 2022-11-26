package net.toiditimtoi.urlshortener.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * A simple implementation of the URL hashing that makes use of the SHA-256
 * This simple implementation takes only the first 7 characters of the hashing result
 * Accept the risk of hash-and-substring collision
 */

@Component
@Primary
public class Sha256SimpleDigest implements HashFunction {
    @Override
    public String hash(String originalUrl) {
        MessageDigest simpleDigest;
        try {
            simpleDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        var digested = simpleDigest.digest(originalUrl.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(digested).substring(0, 7);
    }
}
