package org.bitmonsters.userservice.reading;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.exception.ReadingException;
import org.bitmonsters.userservice.user.IdResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadingService {

    private final ReadingRepository repository;
    private final ReadingMapper mapper;

    public IdResponse createReading(Long userId, ReadingDto readingDto) {
        // check whether post is in the system

        // check whether reading is still in the system
        if (isReadingExists(userId, readingDto.postId())) {
            throw new ReadingException(String.format("reading with post id %d of the user with id %d is already exists", readingDto.postId(), userId));
        }

        var reading = repository.save(mapper.toReading(userId, readingDto));
        return new IdResponse(reading.getId(), "reading added successfully");
    }

    public boolean isReadingExists(Long userId, Long postId) {
        var reading = repository.findByUserIdAndPostId(userId, postId);
        return reading.isPresent();
    }

    public Page<ReadingDto> getUserReadings(Long userId, Pageable page) {
        return repository.findByUserId(userId, page).map(mapper::toReadingDto);
    }

    public void removeReading(Long userId, Long readingId) {
        var reading = repository.findById(readingId).orElse(null);

        if (reading != null && !reading.getUser().getId().equals(userId)) {
            throw new ReadingException(String.format("reading with id %d is not correspond to user with id %d", readingId, userId));
        }

        repository.deleteById(readingId);
    }
}
