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

@WebMvcTest(controllers = {UrlShortenerController.class})
public class UrlShortenerControllerTest {

    @MockBean
    private UrlShortenerService urlShortenerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_endpoint_to_shorten_url() throws Exception {
        var dummyUrl = "http://url.short/abcxyz";
        var dummyHash = "ax5cz67";
        var dummyUrlMapping = new UrlMapping(null, dummyHash, dummyUrl);
        var expectedOutput =
                Mockito.when(urlShortenerService.shortenUrl(any())).thenReturn(dummyUrlMapping);
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
    public void test_redirection_endpoint() {

    }

}
