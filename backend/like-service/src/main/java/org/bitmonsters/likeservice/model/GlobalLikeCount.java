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
@Table("global_like_count")
public class GlobalLikeCount {

    @PrimaryKeyColumn(
            name = "like_status",
            type = PrimaryKeyType.PARTITIONED
    )
    private Integer likeStatus;

    private Long count;
}
