package net.toiditimtoi.urlshortener.service;

public interface HashFunction {
    String hash(String originalUrl);
}
