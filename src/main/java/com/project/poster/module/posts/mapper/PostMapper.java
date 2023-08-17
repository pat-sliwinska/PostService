package com.project.poster.module.posts.mapper;

import com.project.poster.module.posts.dto.PostDto;
import com.project.poster.shared.clients.json.placeholder.model.PostResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto postResponseToPostDto(PostResponse postResponse);

    List<PostDto> postsResponseToPostDtos(List<PostResponse> postResponse);
}
