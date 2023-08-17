package com.project.poster.shared.common.http;

import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import com.project.poster.shared.common.utils.RequestTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.project.poster.shared.common.utils.RequestTestUtils.createPostResponsesRequest;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class WebClientConnectorTest {

    private static final String SAMPLE_JSON = """
               [
                 {
                   "userId": 1,
                   "id": 1,
                   "title": "title",
                   "body": "body"
                 }
               ]
            """;

    @InjectMocks
    private WebClientConnector webClientConnector;

    @BeforeEach
    void setUp() {
        WebClient webClientMock = WebClient.builder()
                .baseUrl(RequestTestUtils.BASE_URL)
                .exchangeFunction(clientRequest -> Mono.just(ClientResponse.create(HttpStatus.OK)
                        .header("content-type", "application/json")
                        .body(SAMPLE_JSON)
                        .build()))
                .build();

        webClientConnector = new WebClientConnector(webClientMock);
    }

    @Test
    void shouldRetrieveRequest() {
        //given
        RequestData<PostResponse[]> requestData = createPostResponsesRequest();

        //when
        Mono<PostResponse[]> response = webClientConnector.retrieveRequest(requestData);

        //then
        assertThat(response.block()).hasSize(1);
    }

}