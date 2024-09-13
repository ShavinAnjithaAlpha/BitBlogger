package org.bitmonsters.userservice.reading;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.me.MessageResponse;
import org.bitmonsters.userservice.user.IdResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/me/readings")
public class ReadingController {

    private final ReadingService service;

    @PostMapping
    public IdResponse addNewReading(
            @RequestHeader("userId") Long userId,
            @Validated @RequestBody ReadingDto readingDto
    ) {
        return service.createReading(userId, readingDto);
    }

    @GetMapping
    public Page<ReadingDto> getUserReadingsOfAuthenticatedUser(
            @RequestHeader("userId") Long userId,
            Pageable page
    ) {
        return service.getUserReadings(userId, page);
    }

    @DeleteMapping("/{readingId}")
    public MessageResponse removeReadingFromAuthenticatedUser(
            @RequestHeader("userId") Long userId,
            @PathVariable("readingId") Long readingId
    ) {
        service.removeReading(userId, readingId);
        return new MessageResponse("reading removed successfully");
    }

}
