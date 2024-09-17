package org.bitmonsters.likeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@Builder
@Table("post_like_count")
public class PostLikeCount {

    @PrimaryKeyColumn(
            name = "post_id",
            type = PrimaryKeyType.PARTITIONED
    )
    private Long postId;

    @PrimaryKeyColumn(
            name = "like_status",
            ordinal = 0,
            type = PrimaryKeyType.CLUSTERED
    )
    private Integer likeStatus;

    private Long count;
}
