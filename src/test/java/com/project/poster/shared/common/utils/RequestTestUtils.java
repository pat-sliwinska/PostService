package com.project.poster.shared.common.utils;

import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import com.project.poster.shared.common.http.RequestData;
import org.springframework.http.HttpMethod;

public final class RequestTestUtils {

    private RequestTestUtils() {
    }

    public static final String BASE_URL = "http://localhost:8080/posts";

    public static RequestData<PostResponse[]> createPostResponsesRequest() {
        return RequestData.<PostResponse[]>builder()
                .url(BASE_URL)
                .method(HttpMethod.GET)
                .responseBodyClass(PostResponse[].class)
                .build();
    }
}
