package org.bitmonsters.searchservice.model;

import co.elastic.clients.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(indexName = "blog")
public class Post {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Text)
    private String preview;

    @Field(type = FieldType.Nested, includeInParent = true)
    private User author;

    @Field(type = FieldType.Date)
    private DateTime createdAt;

    @Field(type = FieldType.Nested, includeInParent = true)
    private Topic topic;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Tag> tags;

}
