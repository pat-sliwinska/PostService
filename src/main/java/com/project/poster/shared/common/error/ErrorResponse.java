package com.project.poster.shared.common.error;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(
        int status,
        String message,
        String errorCode,
        String detailedMessage,
        List<ErrorMessage> violations,
        String timestamp
) {
}
