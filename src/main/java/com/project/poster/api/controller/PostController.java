package com.project.poster.api.controller;

import an.awesome.pipelinr.Pipeline;
import com.project.poster.module.posts.dto.PostDto;
import com.project.poster.module.posts.handler.query.PostsQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("posts")
@RequiredArgsConstructor
public class PostController {

    private final Pipeline pipeline;

    @GetMapping
    List<PostDto> getPosts() {
        return pipeline.send(new PostsQuery());
    }

}
