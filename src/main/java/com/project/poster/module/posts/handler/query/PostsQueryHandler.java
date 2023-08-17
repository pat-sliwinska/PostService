package com.project.poster.module.posts.handler.query;

import an.awesome.pipelinr.Command;
import com.project.poster.module.posts.dto.PostDto;
import com.project.poster.module.posts.mapper.PostMapper;
import com.project.poster.module.posts.service.PostService;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import com.project.poster.shared.common.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.project.poster.shared.common.utils.FileExtension.JSON;
import static com.project.poster.shared.common.utils.FileUtils.writeObjectToJsonFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostsQueryHandler implements Command.Handler<PostsQuery, List<PostDto>> {

    private static final String DEFAULT_POSTS_SAVE_DIRECTORY = "target";
    private final PostService postService;
    private final PostMapper postMapper;

    @Override
    public List<PostDto> handle(PostsQuery command) {
        List<PostResponse> postsResponse = postService.getPosts();
        List<PostDto> posts = postMapper.postsResponseToPostDtos(postsResponse);
        FileUtils.createDirectories(Path.of(DEFAULT_POSTS_SAVE_DIRECTORY));
        return getSavedPosts(posts);
    }

    private List<PostDto> getSavedPosts(List<PostDto> posts) {
        return posts.stream()
                .filter(this::savePostToFile)
                .toList();
    }

    public boolean savePostToFile(PostDto post) {
        File postFile = Paths.get(DEFAULT_POSTS_SAVE_DIRECTORY, post.id() + JSON.getExtensionName()).toFile();
        return writeObjectToJsonFile(postFile, post);
    }

}
