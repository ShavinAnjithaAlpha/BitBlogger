package org.bitmonsters.userservice.interest.service;

import org.bitmonsters.userservice.interest.dto.NewInterestDto;
import org.bitmonsters.userservice.interest.model.Interest;
import org.bitmonsters.userservice.user.model.User;
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
