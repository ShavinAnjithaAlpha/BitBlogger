package org.bitmonsters.mediaservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.bitmonsters.mediaservice.model.ContentType;

public record FileRequest(
        @NotBlank(message = "file name is required")
        @Pattern(regexp = "[a-zA-Z0-9.-_8*]{0,100}.[a-zA-Z0-9]{1,20}", message = "invalid file name format")
        String fileName,
        ContentType contentType
) {
}
