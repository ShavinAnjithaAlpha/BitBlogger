package org.bitmonsters.contentservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Builder
public record NewPostDto(
        @NotNull(message = "title is required")
        @NotBlank(message = "title cannot be blank")
        String title,
        @NotNull(message = "content is required")
        @NotBlank(message = "content cannot be blanked")
        @Size(max = 5000, message = "character limit exceeded")
        String content,
        @URL(message = "cover image must be a url")
        String coverImage,
        @Size(max = 100, message = "preview cannot exceeded 100 characters")
        String preview,
        String language,
        @NotNull(message = "topic id is required")
        Integer topicId,
        List<Integer> tagIds,
        Boolean isPinned
) {
}
