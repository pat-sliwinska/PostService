package com.project.poster.module.posts.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
@Setter
public final class PostDto {
    private Long userId;
    private Long id;
    private String title;
    private String body;
    private List<CommentDto> comments;
}
