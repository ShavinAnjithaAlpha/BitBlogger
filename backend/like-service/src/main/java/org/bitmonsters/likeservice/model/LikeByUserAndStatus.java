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
@AllArgsConstructor
@Builder
@Table("like_by_user_status")
public class LikeByUserAndStatus {

    @Data
    @AllArgsConstructor
    @Builder
    @PrimaryKeyClass
    public static class LikeKey {

        @PrimaryKeyColumn(
                name = "user_id",
                type = PrimaryKeyType.PARTITIONED
        )
        private Long userId;

        @PrimaryKeyColumn(
                name = "like_status",
                ordinal = 0,
                type = PrimaryKeyType.CLUSTERED
        )
        private Integer likeStatus;

        @PrimaryKeyColumn(
                name = "post_id",
                ordinal = 1,
                type = PrimaryKeyType.CLUSTERED
        )
        private Long postId;

        public LikeStatus getLikeStatus() {
            return LikeStatus.fromValue(likeStatus);
        }

    }

    @PrimaryKey
    private LikeKey key;

    @CreatedDate
    private Instant likedAt;

}
