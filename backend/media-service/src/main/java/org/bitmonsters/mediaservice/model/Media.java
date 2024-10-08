package org.bitmonsters.mediaservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.MimeType;

import java.net.URL;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("media")
public class Media {

    @Id
    private String id;

    private String fileName;

    private Long fileSize;

    private String fileType;

    private LocalDateTime uploadedAt;

    private String fileUrl;

    private String checksum;

    private ContentType contentType;

    private boolean isThumbnail;

    private Long contentId;
}
