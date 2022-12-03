package net.toiditimtoi.urlshortener.controller;

import net.toiditimtoi.urlshortener.persistent.UrlMapping;
import net.toiditimtoi.urlshortener.service.UrlShortenerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = {UrlShortenerController.class})
class UrlShortenerControllerTest {

    @MockBean
    private UrlShortenerService urlShortenerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test_endpoint_to_shorten_url() throws Exception {
        var dummyUrl = "http://url.short/abcxyz";
        var dummyHash = "ax5cz67";
        var dummyUrlMapping = new UrlMapping(null, dummyHash, dummyUrl);
        var expectedOutput =
                when(urlShortenerService.shortenUrl(any())).thenReturn(dummyUrlMapping);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/shorten")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "longUrl": "https://google.com"
                                }
                                """)
        ).andExpect(
                MockMvcResultMatchers
                        .status().isCreated()

        ).andExpect(
                MockMvcResultMatchers
                        .content().contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers
                        .jsonPath("$.shortUrl")
                        .value("localhost/" + dummyHash)
        );
        Mockito.verify(urlShortenerService, Mockito.times(1)).shortenUrl(any());
    }

    @Test
    void test_endpoint_to_get_back_full_url() throws Exception {
        var fullUrl = "https://github.com/kukot";
        var hashUrl = "6dM0R0F";
        var urlMapping = new UrlMapping(null, hashUrl, fullUrl);
        when(urlShortenerService.getUrlMappingByHash("6dM0R0F")).thenReturn(urlMapping);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/api/v1/shorten/6dM0R0F")
                ).andExpect(
                        MockMvcResultMatchers.status().is2xxSuccessful()
                ).andExpect(
                        MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)
                ).andExpect(
                        MockMvcResultMatchers.jsonPath("$.fullUrl")
                                .value(fullUrl)
                );
        verify(urlShortenerService, times(1)).getUrlMappingByHash("6dM0R0F");
    }

}
