package com.project.poster.shared.clients.json.placeholder;

import com.project.poster.shared.ApiConnector;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import com.project.poster.shared.common.http.RequestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.project.poster.shared.common.utils.PostTestUtils.createPostsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JsonPlaceholderConnectorImplTest {

    @Mock
    private JsonPlaceholderApiUrls urls;

    @Mock
    private ApiConnector apiConnector;

    @InjectMocks
    private JsonPlaceholderConnectorImpl jsonPlaceholderConnector;

    @Test
    void shouldRetrieveRequest() {
        //given
        PostResponse[] postResponses = createPostsResponse();
        when(apiConnector.retrieveRequest(ArgumentMatchers.<RequestData<PostResponse[]>>any())).thenReturn(Optional.of(postResponses));

        //when
        PostResponse[] posts = jsonPlaceholderConnector.getPosts().orElse(new PostResponse[]{});

        //then
        assertEquals(postResponses, posts);
    }
}