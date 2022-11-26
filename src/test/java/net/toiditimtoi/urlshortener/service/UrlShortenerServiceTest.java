package net.toiditimtoi.urlshortener.service;

import net.toiditimtoi.urlshortener.persistent.UrlMapping;
import net.toiditimtoi.urlshortener.repository.UrlMappingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UrlShortenerServiceTest {

    @Mock
    private HashFunction hashFunction;

    @Mock
    private UrlMappingRepository urlMappingRepository;

    @InjectMocks
    UrlShortenerService urlShortenerService;

    @Test
    void shortenUrl() {
        // preparing
        var dummyInput = "http://dummy.net";
        var dummyOutputHash = "dummyoutput";
        var dummyUrlMapping = new UrlMapping(null, dummyOutputHash, dummyInput);
        when(hashFunction.hash(dummyInput)).thenReturn(dummyOutputHash);

        // execute
        urlShortenerService.shortenUrl(dummyInput);

        // verify
        verify(hashFunction, times(1)).hash(dummyInput);
        verify(urlMappingRepository, times(1)).save(eq(dummyUrlMapping));
    }
}