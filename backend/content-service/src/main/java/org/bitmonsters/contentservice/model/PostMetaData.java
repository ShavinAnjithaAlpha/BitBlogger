package org.bitmonsters.contentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostMetaData {

    private Integer readingTime;

    private Integer rating;

    private Long viewCount;

    private Boolean isPinned;

    private Boolean isFeatured;

}
