package org.bitmonsters.pollservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bitmonsters.pollservice.model.AnswerStatus;
import org.bitmonsters.pollservice.model.PollAnswer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollStatByDateDto {

    @Builder
    private record AttemptRecordByDateDto(
            LocalDate date,
            Map<Integer, Long> attempts,
            Long optionalAnswerCount
    ) {}

    @Builder
    private record AnswerRecord(
            Integer answerId,
            String answer,
            AnswerStatus status
    ) {}

    private LocalDate startDate;
    private LocalDate endDate;
    private List<AttemptRecordByDateDto> attemptResults = new ArrayList<>();
    private Map<Integer, Long> summary = new HashMap<>();
    private List<AnswerRecord> answerMap = new ArrayList<>();

    @JsonIgnore
    private LocalDate currentDate;

    public void addAttemptsToCurrentDate(Map<Integer, Long> attempts, Long optionalAnswerCount) {
        attemptResults.add(
                AttemptRecordByDateDto.builder()
                        .date(currentDate)
                        .attempts(attempts)
                        .optionalAnswerCount(optionalAnswerCount)
                        .build()
        );

        // add to summary
        attempts.keySet().forEach(
                answerId -> {
                    if (summary.containsKey(answerId))
                        summary.put(answerId, summary.get(answerId) + attempts.get(answerId));
                    else
                        summary.put(answerId, attempts.get(answerId));
                }
        );

        currentDate = currentDate.plusDays(1);
    }

    public void addAnswerRecord(PollAnswer answer) {
        answerMap.add(
                AnswerRecord.builder()
                        .answerId(answer.getAnswerId())
                        .answer(answer.getAnswer())
                        .status(answer.getAnswerStatus())
                        .build()
        );
    }

    public boolean isEnd() {
        return currentDate.isAfter(endDate);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        this.currentDate = startDate.plusDays(0);
    }
}
