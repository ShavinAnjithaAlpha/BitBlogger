package org.bitmonsters.userservice.link.service;

import org.bitmonsters.userservice.link.dto.UserLinkDto;
import org.bitmonsters.userservice.link.model.UserLink;
import org.bitmonsters.userservice.platform.Platform;
import org.bitmonsters.userservice.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserLinkMapper {


    public UserLink toUserLink(Long userId, UserLinkDto userLinkDto) {
        return UserLink.builder()
                .platform(
                        Platform.builder()
                                .id(userLinkDto.platformId())
                                .build()
                )
                .user(
                        User.builder()
                                .id(userId)
                                .build()
                )
                .url(userLinkDto.url())
                .custom(userLinkDto.custom())
                .build();
    }
}
