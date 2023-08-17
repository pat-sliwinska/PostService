package com.project.poster.module.posts.handler.query;

import com.project.poster.module.posts.dto.PostDto;
import com.project.poster.module.posts.mapper.PostMapper;
import com.project.poster.module.posts.mapper.PostMapperImpl;
import com.project.poster.module.posts.service.PostService;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.project.poster.shared.common.utils.PostTestUtils.createPostsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = PostMapperImpl.class)
class PostsQueryHandlerTest {

    @Mock
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    private PostsQueryHandler postsQueryHandler;

    @BeforeEach
    void setUp() {
        postsQueryHandler = new PostsQueryHandler(postService, postMapper);
    }

    @Test
    void shouldRetrieveRequest() {
        //given
        List<PostResponse> postResponses = Lists.newArrayList(createPostsResponse());
        when(postService.getPosts()).thenReturn(postResponses);

        //when
        List<PostDto> result = postsQueryHandler.handle(new PostsQuery());

        //then
        assertEquals(result, postMapper.postsResponseToPostDtos(postResponses));
    }
}