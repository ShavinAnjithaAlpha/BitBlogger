package org.bitmonsters.likeservice.dto;

import jakarta.annotation.Nullable;
import lombok.Data;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class CassandraPage<T> {

    private final Integer count;
    private final List<T> content;
    private final String pagingState;
    private final Boolean isFirst;
    private final Boolean isLast;
    private final Boolean hasPrevious;
    private final Boolean hasNext;

    public CassandraPage(final Slice<T> slice) {
        this.count = slice.getNumberOfElements();
        this.content = slice.getContent();
        this.hasNext = slice.hasNext();
        this.hasPrevious = slice.hasPrevious();
        this.isFirst = slice.isFirst();
        this.isLast = slice.isLast();
        this.pagingState = getPagingState(slice);

    }

    @Nullable
    public static <T> String getPagingState(final Slice<T> slice) {
        if (slice.hasNext()) {
            var pageRequest = (CassandraPageRequest) slice.nextPageable();
            return Objects.requireNonNull(pageRequest.getPagingState()).toString();
        } else {
            return null;
        }
    }

    public Optional<String> getPagingState() {
        return Optional.ofNullable(pagingState);
    }

}
