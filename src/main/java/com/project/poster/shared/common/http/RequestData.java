package com.project.poster.shared.common.http;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Getter
@Setter
@Builder
public class RequestData<T> {
    private HttpMethod method;
    private String url;
    private String body;
    private Map<String, String> headers;
    private Class<T> responseBodyClass;
}
