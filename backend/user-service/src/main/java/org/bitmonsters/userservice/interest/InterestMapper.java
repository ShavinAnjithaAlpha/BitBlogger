package org.bitmonsters.userservice.interest;

import org.bitmonsters.userservice.user.User;
import org.springframework.stereotype.Service;

@Service
public class InterestMapper {
    public Interest toInterest(Long userId, NewInterestDto newInterestDto) {
        return Interest.builder()
                .user(User.builder().id(userId).build())
                .tagId(newInterestDto.tagId())
                .build();
    }
}
