package com.project.poster.shared.common.http;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebClientConnector {

    private final WebClient webClient;

    public <T> Mono<T> retrieveRequest(RequestData<T> requestData) {
        return webClient
                .method(requestData.getMethod())
                .uri(requestData.getUrl())
                .retrieve()
                .bodyToMono(requestData.getResponseBodyClass());
    }

}
