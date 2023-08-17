package com.project.poster.shared.clients.json.placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class JsonPlaceholderApiUrlsTest {

    private static final String URL = "https://localhost:8080";
    private JsonPlaceholderApiUrls urls;

    @BeforeEach
    void setUp() {
        urls = new JsonPlaceholderApiUrls(URL);
    }

    @Test
    void shouldGetPostsUrl() {
        //when
        final String result = urls.getPostsUrl();

        //then
        assertEquals(URL + "/posts", result);
    }
}