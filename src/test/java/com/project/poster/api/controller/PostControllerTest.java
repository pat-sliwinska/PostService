package com.project.poster.api.controller;

import an.awesome.pipelinr.Pipeline;
import com.project.poster.module.posts.dto.PostDto;
import com.project.poster.module.posts.handler.query.PostsQuery;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.project.poster.shared.common.utils.PostTestUtils.createPostDtos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Mock
    private Pipeline pipeline;

    @InjectMocks
    private PostController postController;

    @Test
    void shouldSendPostQuery() {
        // given
        List<PostDto> postDtos = createPostDtos();
        when(pipeline.send(any(PostsQuery.class))).thenReturn(postDtos);

        // when
        final List<PostDto> result = postController.savePosts();

        // then
        final ArgumentCaptor<PostsQuery> queryCaptor = ArgumentCaptor.forClass(PostsQuery.class);
        verify(pipeline, times(1)).send(queryCaptor.capture());
        assertEquals(postDtos, result);
    }

}