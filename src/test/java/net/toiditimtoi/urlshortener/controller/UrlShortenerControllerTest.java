package net.toiditimtoi.urlshortener.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
public class UrlShortenerControllerTest {

    private final MockMvc mockMvc;

    @Autowired
    public UrlShortenerControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void test_endpoint_to_shorten_url() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/v1/shorten")
                        .accept(MediaType.APPLICATION_JSON)
                        .content("https://google.com")
        ).andExpect(
                MockMvcResultMatchers
                        .status().is2xxSuccessful()
        );
    }

    @Test
    public void test_redirection_endpoint() {

    }

}
