package com.project.poster.module.posts.dto;

import lombok.Builder;

@Builder
public record PostDto(
        Long userId,
        Long id,
        String title,
        String body
) {
}
