package org.bitmonsters.userservice.user.service;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.dto.IdResponse;
import org.bitmonsters.userservice.exception.UserAlreadyRegisteredException;
import org.bitmonsters.userservice.exception.UserNotFoundException;
import org.bitmonsters.userservice.exception.UserReportException;
import org.bitmonsters.userservice.me.dto.UserReportDto;
import org.bitmonsters.userservice.me.dto.UserUpdateDto;
import org.bitmonsters.userservice.report.repository.UserReportsRepository;
import org.bitmonsters.userservice.report.service.UserReportMapper;
import org.bitmonsters.userservice.user.dto.UserRegisterDto;
import org.bitmonsters.userservice.user.dto.UserResponse;
import org.bitmonsters.userservice.user.model.User;
import org.bitmonsters.userservice.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReportsRepository userReportsRepository;
    private final UserMapper userMapper;
    private final UserReportMapper userReportMapper;

    public IdResponse createUser(UserRegisterDto userRegisterDto) {

        // check whether username or email is already registered
        if (isUserExists(userRegisterDto.username(), userRegisterDto.email())) {
            throw new UserAlreadyRegisteredException(String.format("User with email %s or username %s is already registered",
                    userRegisterDto.email(), userRegisterDto.username()));
        }

        User user = userRepository.save(
                userMapper.toUser(userRegisterDto)
        );
        return new IdResponse(
                user.getId(),
                "user registered successfully"
        );
    }

    public boolean isUserExists(String username, String email) {
        Optional<User> user = userRepository.findByUsernameOrEmail(username, email);
        return user.isPresent();
    }

    public boolean isUserExists(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent();
    }

    public Page<UserResponse> getUsers(Pageable page) {
        return userRepository.findAll(page).map(userMapper::toUserResponse);
    }


    public ResponseEntity<?> getUser(Long userId, Boolean isShort) {
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                String.format("user with user id %d not found", userId)
        ));
        if (isShort) {
            return ResponseEntity.ok(userMapper.toShortUserResponse(user));
        } else {
            return ResponseEntity.ok(userMapper.toFullUserResponse(user));
        }
    }

    public UserResponse getUser(Long userId) {
        return userMapper.toFullUserResponse(userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(String.format("user with user id %d not found", userId))
        ));
    }

    public void deleteUser(Long userId) {
        if (!isUserExists(userId)) {
            throw new UserNotFoundException(String.format("user with user id  %d is not found", userId));
        }
        userRepository.deleteById(userId);
    }

    public void updateUser(Long userId, UserUpdateDto userUpdateDto) {
        var user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new UserNotFoundException(String.format("user with id %d is not found", userId));
        }

        // check whether if there are any user with new username or email
        if (isUserExists(userUpdateDto.username(), userUpdateDto.email())
                && !userRepository.findByUsernameOrEmail(userUpdateDto.username(), userUpdateDto.email())
                            .orElse(User.builder().build()).equals(user)) {
            throw new UserAlreadyRegisteredException(String.format("user with username %s or email %s is already exists",
                    userUpdateDto.username(), userUpdateDto.email()));
        }

        // update the user object with relevant fields
        user.setUsername(userUpdateDto.username());
        user.setEmail(userUpdateDto.email());
        user.setName(userUpdateDto.name());
        user.setProfileImage(userUpdateDto.profileImage());
        user.setWebsiteUrl(userUpdateDto.websiteUrl());
        user.setUserProfile(userUpdateDto.profile());
        user.setLocation(userUpdateDto.location());

        userRepository.save(user);
    }

    public void makeEmailPublic(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        assert user != null;
        user.setDisplayEmail(Boolean.TRUE);
        userRepository.save(user);

    }

    public void makeEmailPrivate(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        assert user != null;
        user.setDisplayEmail(Boolean.FALSE);
        userRepository.save(user);
    }

    public void reportUser(Long userId, UserReportDto reportDto) {
        // check whether reported user exists
        if (!isUserExists(reportDto.userId())) {
            throw new UserNotFoundException(String.format("reported user with id %d is not exists", reportDto.userId()));
        }

        // check whether if the there are report on the same user twice by the same reporter
        if (isReportedTwice(userId, reportDto.userId())) {
            throw new UserReportException(String.format("user with id %d is already reported", reportDto.userId()));
        }

        userReportsRepository.save(userReportMapper.toUserReport(userId, reportDto));
    }

    private boolean isReportedTwice(Long reporterId, Long userId) {
        var report = userReportsRepository.findByUserIdAndReporterId(userId, reporterId);
        return report.isPresent();
    }
}
