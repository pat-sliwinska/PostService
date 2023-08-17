package com.project.poster.shared.common.utils;

import com.project.poster.module.posts.dto.PostDto;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import org.assertj.core.util.Lists;

import java.util.List;

public final class PostTestUtils {

    private static final long ID = 1L;
    private static final long USER_ID = 1L;
    private static final String TITLE = "title";
    private static final String BODY = "body";

    private PostTestUtils() {
    }

    public static List<PostDto> createPostDtos() {
        return Lists.newArrayList(createPostDto());
    }

    public static PostResponse[] createPostsResponse() {
        return new PostResponse[]{createPostResponse()};
    }

    private static PostDto createPostDto() {
        return PostDto.builder()
                .id(ID)
                .userId(USER_ID)
                .title(TITLE)
                .body(BODY)
                .build();
    }

    private static PostResponse createPostResponse() {
        return PostResponse.builder()
                .id(ID)
                .userId(USER_ID)
                .title(TITLE)
                .body(BODY)
                .build();
    }
}
