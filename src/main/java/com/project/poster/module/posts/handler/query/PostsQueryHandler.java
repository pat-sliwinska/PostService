package com.project.poster.module.posts.handler.query;

import an.awesome.pipelinr.Command;
import com.project.poster.module.posts.dto.CommentDto;
import com.project.poster.module.posts.dto.PostDto;
import com.project.poster.module.posts.mapper.CommentMapper;
import com.project.poster.module.posts.mapper.PostMapper;
import com.project.poster.module.posts.service.CommentService;
import com.project.poster.module.posts.service.PostService;
import com.project.poster.shared.clients.json.placeholder.model.CommentResponse;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import com.project.poster.shared.common.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static com.project.poster.shared.common.utils.FileExtension.JSON;
import static com.project.poster.shared.common.utils.FileUtils.writeObjectToJsonFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostsQueryHandler implements Command.Handler<PostsQuery, List<PostDto>> {

    private final PostService postService;
    private final CommentService commentService;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @Override
    public List<PostDto> handle(PostsQuery command) {
        List<PostResponse> postsResponse = postService.getPosts();
        List<PostDto> posts = postMapper.postsResponseToPostDtos(postsResponse);
        CompletableFuture<List<PostDto>> postDtosFuture = setCommentsAsync(posts);
        return postDtosFuture.join();
    }

    private CompletableFuture<List<PostDto>> setCommentsAsync(List<PostDto> posts) {
        List<CompletableFuture<PostDto>> postDtos = posts.stream()
                .map(this::setComments)
                .toList();

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                postDtos.toArray(new CompletableFuture[0])
        );

        return allFutures.thenApply(v ->
                postDtos.stream()
                        .map(CompletableFuture::join)
                        .toList());
    }

    private CompletableFuture<PostDto> setComments(PostDto post) {
        return CompletableFuture.supplyAsync(() -> {
            Long postId = post.getId();
            List<CommentResponse> commentResponse = commentService.getComments(postId);
            List<CommentDto> comments = commentMapper.commentsResponseToCommentDtos(commentResponse);
            post.setComments(comments);
            return post;
        });
    }

}
