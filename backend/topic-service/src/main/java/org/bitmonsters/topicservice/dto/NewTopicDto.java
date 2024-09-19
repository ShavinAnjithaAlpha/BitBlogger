package org.bitmonsters.topicservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record NewTopicDto(
        @NotNull(message = "topic name is required")
        @NotBlank(message = "topic name cannot be blank")
        @Pattern(regexp = "^[A-Za-z0-9+_.\\-\\s]{1,100}$", message = "topic name must be maximum 100 characters")
        String name,
        @NotNull(message = "topic description is required")
        @NotBlank(message = "topic description cannot be blank")
        @Size(max = 2048, message = "description cannot exceeds 2048 characters")
        String description,
        Integer parentTopicId
) {
}
