package com.project.poster.shared.clients.json.placeholder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JsonPlaceholderApiUrls {

    private final String clientUrl;

    public JsonPlaceholderApiUrls(@Value("${api.json-placeholder.url}") String clientUrl) {
        this.clientUrl = clientUrl;
    }

    public String getPostsUrl() {
        return clientUrl + "/posts";
    }

}
