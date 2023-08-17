package com.project.poster.module.posts.service;

import com.project.poster.shared.clients.json.placeholder.model.PostResponse;

import java.util.List;

public interface PostService {
    List<PostResponse> getPosts();
}
