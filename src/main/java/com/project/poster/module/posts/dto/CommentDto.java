package com.project.poster.module.posts.dto;

import lombok.Builder;

@Builder
public record CommentDto(
        Long postId,
        Long id,
        String name,
        String email,
        String body
) {
}
