package org.bitmonsters.userservice.reading;

import org.bitmonsters.userservice.user.User;
import org.springframework.stereotype.Service;

@Service
public class ReadingMapper {
    public ReadingList toReading(Long userId, ReadingDto readingDto) {
        return ReadingList.builder()
                .user(User.builder().id(userId).build())
                .postId(readingDto.postId())
                .note(readingDto.note())
                .build();
    }

    public ReadingDto toReadingDto(ReadingList readingList) {
        return ReadingDto.builder()
                .id(readingList.getId())
                .postId(readingList.getPostId())
                .note(readingList.getNote())
                .build();
    }
}
