package org.bitmonsters.commentservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewCommentDto(
        @NotNull(message = "content is required")
        @NotBlank(message = "comment cannot be blank")
        String content
) {
}