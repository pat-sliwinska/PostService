package com.project.poster.shared;

import com.project.poster.shared.common.http.RequestData;
import com.project.poster.shared.common.http.WebClientConnector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static com.project.poster.shared.common.error.ErrorMessage.ERROR_EXTERNAL_API;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiConnector {

    private final WebClientConnector webClientConnector;
    private final ReactiveCircuitBreaker circuitBreaker;

    @Autowired
    public ApiConnector(ReactiveCircuitBreakerFactory circuitBreakerFactory, WebClientConnector webClientConnector) {
        this.circuitBreaker = circuitBreakerFactory.create("circuitBreaker");
        this.webClientConnector = webClientConnector;
    }

    public <T> Optional<T> retrieveRequest(RequestData<T> requestData) {
        Mono<T> request = webClientConnector.retrieveRequest(requestData);
        Mono<T> result = circuitBreaker.run(request, throwable -> handleNoApiResponse());
        return result.blockOptional();
    }

    private <T> Mono<T> handleNoApiResponse() {
        log.error(ERROR_EXTERNAL_API.getExceptionMessage());
        return Mono.empty();
    }

}
