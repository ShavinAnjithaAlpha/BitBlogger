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
@Table("like_by_post_status")
public class LikeByPostAndStatus {

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
                name = "like_status",
                ordinal = 0,
                type = PrimaryKeyType.CLUSTERED
        )
        private Integer likeStatus;

        @PrimaryKeyColumn(
                name = "user_id",
                ordinal = 1,
                type = PrimaryKeyType.CLUSTERED
        )
        private Long userId;


        public LikeStatus getLikeStatus() {
            return LikeStatus.fromValue(likeStatus);
        }
    }

    @PrimaryKey
    private LikeKey key;

    @CreatedDate
    private Instant likedAt;

}
