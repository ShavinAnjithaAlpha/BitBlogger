package org.bitmonsters.userservice.user.service;

import org.bitmonsters.userservice.link.model.UserLink;
import org.bitmonsters.userservice.user.dto.UserLinkResponse;
import org.bitmonsters.userservice.user.dto.UserRegisterDto;
import org.bitmonsters.userservice.user.dto.UserResponse;
import org.bitmonsters.userservice.user.model.User;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {


    public User toUser(UserRegisterDto userRegisterDto) {
        return User.builder()
                .name(userRegisterDto.name())
                .username(userRegisterDto.username())
                .email(userRegisterDto.email())
                .displayEmail(false)
                .build();
    }

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getDisplayEmail() ? user.getEmail() : null)
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .location(user.getLocation())
                .profile(user.getUserProfile())
                .links(user.getLinks().stream().map(this::toUserLinkResponse).collect(Collectors.toList()))
                .createdAt(user.getCreatedAt())
                .build();
    }

    public UserResponse toFullUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .location(user.getLocation())
                .profile(user.getUserProfile())
                .links(user.getLinks().stream().map(this::toUserLinkResponse).collect(Collectors.toList()))
                .createdAt(user.getCreatedAt())
                .build();
    }

    private UserLinkResponse toUserLinkResponse(UserLink userLink) {
        return UserLinkResponse.builder()
                .platform(userLink.getPlatform().getName())
                .url(userLink.getUrl())
                .custom(userLink.getCustom())
                .build();
    }
}
