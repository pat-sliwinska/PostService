package com.project.poster.module.posts.service;

import com.google.common.collect.Lists;
import com.project.poster.shared.clients.json.placeholder.JsonPlaceholderApiConnector;
import com.project.poster.shared.clients.json.placeholder.model.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final JsonPlaceholderApiConnector jsonPlaceholderApiConnector;

    @Override
    public List<CommentResponse> getComments(Long id) {
        return Lists.newArrayList(jsonPlaceholderApiConnector.getCommentsByPost(id).orElse(new CommentResponse[]{}));
    }
}
