package com.project.poster.shared;

import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import com.project.poster.shared.common.http.RequestData;
import com.project.poster.shared.common.http.WebClientConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import reactor.core.publisher.Mono;

import static com.project.poster.shared.common.utils.PostTestUtils.createPostsResponse;
import static com.project.poster.shared.common.utils.RequestTestUtils.createPostResponsesRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ApiConnectorTest {

    @Mock
    private WebClientConnector webClientConnector;

    @Mock
    private ReactiveCircuitBreaker circuitBreaker;

    @InjectMocks
    private ApiConnector apiConnector;

    @Test
    void shouldRetrieveRequest() {
        //given
        RequestData<PostResponse[]> requestData = createPostResponsesRequest();
        PostResponse[] postResponses = createPostsResponse();
        when(webClientConnector.retrieveRequest(ArgumentMatchers.<RequestData<PostResponse[]>>any())).thenReturn(Mono.just(postResponses));
        when(circuitBreaker.run(ArgumentMatchers.<Mono<PostResponse[]>>any(), any())).thenReturn(Mono.just(postResponses));

        //when
        PostResponse[] result = apiConnector.retrieveRequest(requestData).orElse(null);

        //then
        assertEquals(postResponses, result);
    }

}