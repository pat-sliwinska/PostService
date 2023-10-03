package com.project.poster.module.posts.mapper;

import com.project.poster.module.posts.dto.CommentDto;
import com.project.poster.shared.clients.json.placeholder.model.CommentResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDto commentResponseToCommentDto(CommentResponse commentResponse);

    List<CommentDto> commentsResponseToCommentDtos(List<CommentResponse> commentResponse);
}