package org.bitmonsters.likeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@Table("like_by_post_user")
public class LikeByPostAndUser {

    @Data
    @AllArgsConstructor
    @Builder
    @PrimaryKeyClass
    public static class LikeKey {

        @PrimaryKeyColumn(
                name = "post_id",
                type = PrimaryKeyType.PARTITIONED
        )
        private Long postId;

        @PrimaryKeyColumn(
                name = "user_id",
                ordinal = 0,
                type = PrimaryKeyType.CLUSTERED
        )
        private Long userId;

    }

    @PrimaryKey
    private LikeKey key;

    private Integer likeStatus;

    @CreatedDate
    private Instant likedAt;

    public LikeStatus getLikeStatus() {
        return LikeStatus.fromValue(likeStatus);
    }

}
