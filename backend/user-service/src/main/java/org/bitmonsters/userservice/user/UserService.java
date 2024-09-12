package org.bitmonsters.userservice.user;

import lombok.RequiredArgsConstructor;
import org.bitmonsters.userservice.exception.UserAlreadyRegisteredException;
import org.bitmonsters.userservice.exception.UserNotFoundException;
import org.bitmonsters.userservice.me.UserUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Long createUser(UserRegisterDto userRegisterDto) {

        // check whether username or email is already registered
        if (isUserExists(userRegisterDto.username(), userRegisterDto.email())) {
            throw new UserAlreadyRegisteredException(String.format("User with email %s or username %s is already registered",
                    userRegisterDto.email(), userRegisterDto.username()));
        }

        User user = repository.save(
                mapper.toUser(userRegisterDto)
        );
        return user.getId();
    }

    public boolean isUserExists(String username, String email) {
        Optional<User> user = repository.findByUsernameOrEmail(username, email);
        return user.isPresent();
    }

    public boolean isUserExists(Long id) {
        Optional<User> user = repository.findById(id);
        return user.isPresent();
    }

    public Page<UserResponse> getUsers(Pageable page) {
        return repository.findAll(page).map(mapper::toUserResponse);
    }


    public UserResponse getUser(Long userId) {
        return mapper.toUserResponse(
                repository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                        String.format("user with user id %d is not found", userId)
                ))
        );
    }

    public void deleteUser(Long userId) {
        if (!isUserExists(userId)) {
            throw new UserNotFoundException(String.format("user with user id  %d is not found", userId));
        }
        repository.deleteById(userId);
    }

    public void updateUser(UserUpdateDto userUpdateDto) {
        var user = repository.findById(userUpdateDto.id()).orElse(null);

        if (user == null) {
            throw new UserNotFoundException(String.format("user with user id %d is not found", userUpdateDto.id()));
        }

        // update the user object with relevant fields
        user.setUsername(userUpdateDto.username());
        user.setEmail(userUpdateDto.email());
        user.setName(userUpdateDto.name());
        user.setProfileImage(userUpdateDto.profileImage());
        user.setWebsiteUrl(userUpdateDto.websiteUrl());
        user.setUserProfile(userUpdateDto.profile());
        user.setLocation(userUpdateDto.location());

        repository.save(user);
    }
}
