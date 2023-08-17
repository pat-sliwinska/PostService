package com.project.poster.api.config;

import com.project.poster.shared.common.exception.TechnicalException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.project.poster.shared.common.error.ErrorMessage.ERROR_WEB_CLIENT_REQUEST;

@Configuration
public class WebClientConfiguration {

    @Bean
    WebClient webClient() {
        return WebClient.builder()
                .filter(errorHandler())
                .build();
    }

    private static ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (isError(clientResponse)) {
                return clientResponse
                        .bodyToMono(ClientResponse.class)
                        .flatMap(res -> Mono.error(new TechnicalException(HttpStatus.valueOf(res.statusCode().value()), ERROR_WEB_CLIENT_REQUEST)));
            } else {
                return Mono.just(clientResponse);
            }
        });
    }

    private static boolean isError(ClientResponse clientResponse) {
        return clientResponse.statusCode().is5xxServerError() || clientResponse.statusCode().is4xxClientError();
    }

}
