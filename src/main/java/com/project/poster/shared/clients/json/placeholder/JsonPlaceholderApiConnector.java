package com.project.poster.shared.clients.json.placeholder;

import com.project.poster.shared.clients.json.placeholder.model.CommentResponse;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;

import java.util.Optional;

public interface JsonPlaceholderApiConnector {
    Optional<PostResponse[]> getPosts();
    Optional<CommentResponse[]> getCommentsByPost(Long id);
}
