package com.project.poster.module.posts.service;

import com.google.common.collect.Lists;
import com.project.poster.shared.clients.json.placeholder.JsonPlaceholderApiConnector;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final JsonPlaceholderApiConnector jsonPlaceholderApiConnector;

    public List<PostResponse> getPosts() {
        return Lists.newArrayList(jsonPlaceholderApiConnector.getPosts().orElse(new PostResponse[]{}));
    }
}
