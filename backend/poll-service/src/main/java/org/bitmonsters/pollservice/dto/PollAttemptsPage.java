package org.bitmonsters.pollservice.dto;

import lombok.Data;
import org.bitmonsters.pollservice.model.PollAttempt;
import org.springframework.data.domain.Slice;

import java.util.List;


@Data
public class PollAttemptsPage<T> {

    private final Integer count;
    private final List<T> content;
    private final Boolean isFirst;
    private final Boolean isLast;
    private final Boolean hasPrevious;
    private final Boolean hasNext;

    public PollAttemptsPage(final Slice<Long> slice, List<T> content) {
        this.count = slice.getNumberOfElements();
        this.content = content;
        this.hasNext = slice.hasNext();
        this.hasPrevious = slice.hasPrevious();
        this.isFirst = slice.isFirst();
        this.isLast = slice.isLast();

    }

}
