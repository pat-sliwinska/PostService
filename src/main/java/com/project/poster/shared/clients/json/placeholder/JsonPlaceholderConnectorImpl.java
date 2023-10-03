package com.project.poster.shared.clients.json.placeholder;

import com.project.poster.shared.ApiConnector;
import com.project.poster.shared.clients.json.placeholder.model.CommentResponse;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import com.project.poster.shared.common.http.RequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JsonPlaceholderConnectorImpl implements JsonPlaceholderApiConnector {

    private final JsonPlaceholderApiUrls urls;
    private final ApiConnector apiConnector;

    @Override
    public Optional<PostResponse[]> getPosts() {
        RequestData<PostResponse[]> requestData = buildGetPostsRequest();
        return apiConnector.retrieveRequest(requestData);
    }

    @Override
    public Optional<CommentResponse[]> getCommentsByPost(Long id) {
        RequestData<CommentResponse[]> requestData = buildGetCommentsRequest(id);
        return apiConnector.retrieveRequest(requestData);
    }

    private RequestData<PostResponse[]> buildGetPostsRequest() {
        return RequestData.<PostResponse[]>builder()
                .method(HttpMethod.GET)
                .url(urls.getPostsUrl())
                .responseBodyClass(PostResponse[].class)
                .build();
    }

    private RequestData<CommentResponse[]> buildGetCommentsRequest(Long id) {
        return RequestData.<CommentResponse[]>builder()
                .method(HttpMethod.GET)
                .url(urls.getCommentsUrl(id))
                .responseBodyClass(CommentResponse[].class)
                .build();
    }

}
