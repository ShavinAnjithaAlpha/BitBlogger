package org.bitmonsters.contentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "post_history")
public class PostHistory {

    @Id
    private String id;

    @DBRef
    private Post post;

    private String title;

    private String content;

    private Integer topicId;

    private String version;

    @CreatedDate
    private LocalDateTime modifiedAt;

    private PostAction action;

    private String changedSummary;

}
