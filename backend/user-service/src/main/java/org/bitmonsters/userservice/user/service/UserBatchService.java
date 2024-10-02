package org.bitmonsters.userservice.user.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.user.dto.ShortUserResponse;
import org.bitmonsters.userservice.user.model.User;
import org.bitmonsters.userservice.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBatchService {

    private final UserRepository repository;
    private final UserMapper mapper;


    public List<ShortUserResponse> getUserBatch(List<Long> userIds) {
        List<ShortUserResponse> users = new ArrayList<>(userIds.size());

        for (Long userId: userIds) {
            users.add(mapper.toShortUserResponse(
                    repository.findById(userId)
                            .orElse(User.builder()
                                    .id(userId)
                                    .displayEmail(Boolean.FALSE)
                                    .build()
                            )
            ));
        }

        return users;
    }
}
