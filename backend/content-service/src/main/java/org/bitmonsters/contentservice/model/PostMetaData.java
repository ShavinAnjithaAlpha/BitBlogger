package org.bitmonsters.contentservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class PostMetaData {

    @Column(name = "reading_time")
    private Integer readingTime;

    private Integer rating;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "is_pinned", nullable = false)
    private Boolean isPinned;

    @Column(name = "is_featured", nullable = false)
    private Boolean isFeatured;

}
