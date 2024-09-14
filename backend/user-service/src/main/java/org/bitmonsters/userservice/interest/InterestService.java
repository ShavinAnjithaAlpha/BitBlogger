package org.bitmonsters.userservice.interest;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.exception.UserInterestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestRepository repository;
    private final InterestMapper mapper;

    public Interest addInterest(Long userId, NewInterestDto newInterestDto) {
        // check whether if the tag also in the interests of the user
        if (isInterestExists(userId, newInterestDto.tagId())) {
            throw new UserInterestException(
                    String.format("interest with tag id %d already exists in the user %d", newInterestDto.tagId(), userId),
                    HttpStatus.CONFLICT);
        }

        // check whether if the tag exists in the tag service

        // save the interest if all good
        return repository.save(mapper.toInterest(userId, newInterestDto));
    }

    private boolean isInterestExists(Long userId, Integer tagId) {
        var interest = repository.findByUserIdAndTagId(userId, tagId);
        return interest.isPresent();
    }

    public void removeInterest(Long userId, Integer tagId) {
        repository.deleteInterestByUserIdAndTagId(userId, tagId);
    }

    public List<Integer> getInterestOfUser(Long userId) {
        return repository.findAllByUserId(userId).stream()
                .map(Interest::getTagId)
                .collect(Collectors.toList());
    }
}
