package net.toiditimtoi.urlshortener.service;

import net.toiditimtoi.urlshortener.persistent.UrlMapping;
import net.toiditimtoi.urlshortener.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {


    private final HashFunction hashFunction;

    private final UrlMappingRepository urlMappingRepository;

    public UrlShortenerService(HashFunction hashFunction, UrlMappingRepository urlMappingRepository) {
        this.hashFunction = hashFunction;
        this.urlMappingRepository = urlMappingRepository;
    }

    public UrlMapping shortenUrl(String longUrl) {
        var urlTail = hashFunction.hash(longUrl);
        return urlMappingRepository.save(
                new UrlMapping(null, urlTail, longUrl)
        );
    }

    public UrlMapping getUrlMappingByHash(String urlHash) {
        return urlMappingRepository.findByHashUrl(urlHash);
    }
}
