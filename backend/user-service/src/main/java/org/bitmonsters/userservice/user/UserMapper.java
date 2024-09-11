package org.bitmonsters.userservice.user;

import org.springframework.stereotype.Service;

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
                .email(user.getEmail())
                .name(user.getName())
                .profileImage(user.getProfileImage())
                .location(user.getLocation())
                .profile(user.getUserProfile())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
