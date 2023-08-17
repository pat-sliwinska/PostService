package com.project.poster.module.posts.service;

import an.awesome.pipelinr.repack.com.google.common.collect.Lists;
import com.project.poster.shared.clients.json.placeholder.JsonPlaceholderApiConnector;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.project.poster.shared.common.utils.PostTestUtils.createPostsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private JsonPlaceholderApiConnector jsonPlaceholderApiConnector;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    void shouldGetPosts() {
        // given
        PostResponse[] postResponses = createPostsResponse();
        when(jsonPlaceholderApiConnector.getPosts()).thenReturn(Optional.of(postResponses));

        // when
        final List<PostResponse> result = postService.getPosts();

        // then
        assertEquals(Lists.newArrayList(postResponses), result);
    }
}