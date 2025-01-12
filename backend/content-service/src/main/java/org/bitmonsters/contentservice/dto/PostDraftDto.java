package org.bitmonsters.contentservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record PostDraftDto(
        @NotNull(message = "title is required")
        String title,
        @NotNull(message = "content is required")
        @Size(max = 5000, message = "cannot exceeds 5000 characters")
        String content,
        @URL(message = "cover image must be a url")
        String coverImage
) {
}