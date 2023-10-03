package com.project.poster.module.posts.service;

import com.project.poster.shared.clients.json.placeholder.model.CommentResponse;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;

import java.util.List;

public interface CommentService {

    public List<CommentResponse> getComments(Long id);
}
