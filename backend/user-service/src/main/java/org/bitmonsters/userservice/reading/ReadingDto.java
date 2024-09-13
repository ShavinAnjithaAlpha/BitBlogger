package org.bitmonsters.userservice.reading;

import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ReadingDto(
        Long id,
        Long postId,
        @Size(max = 512, message = "description cannot exceed 512 characters")
        String note
) {
}
