package org.bitmonsters.pollservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.Duration;

@Data
@Builder
public class DurationDto {

    @NotNull(message = "days cannot be null")
    @PositiveOrZero(message = "days should be zero or positive value")
    @Max(value = 356, message = "days should be between 0 and 365")
    private Integer days;
    @NotNull(message = "hours cannot be null")
    @PositiveOrZero(message = "days should be zero or positive value")
    @Max(value = 23, message = "hours should be between 0 and 23")
    private Integer hours;
    @PositiveOrZero(message = "days should be zero or positive value")
    @NotNull(message = "hours cannot be null")
    @Max(value = 59, message = "minutes should be in 0 and 59")
    private Integer minutes;
    @NotNull(message = "seconds cannot be null")
    @PositiveOrZero(message = "days should be zero or positive value")
    @Max(value = 59, message = "seconds should be between 0 and 59")
    private Integer seconds;

    public Duration toDuration() {
        return Duration.ofDays(days)
                .plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);
    }

    public static DurationDto fromDuration(Duration duration) {
        return DurationDto.builder()
                .days(Long.valueOf(duration.toHoursPart()).intValue())
                .hours(duration.toHoursPart())
                .minutes(duration.toMinutesPart())
                .seconds(duration.toSecondsPart())
                .build();
    }

}
