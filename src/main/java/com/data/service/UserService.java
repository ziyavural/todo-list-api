package com.data.service;

import com.data.model.exception.AlreadyExistsException;
import com.data.model.exception.UserNotFoundException;
import com.data.model.exception.WrongPasswordException;
import com.data.model.user.*;
import com.data.persistence.entity.UserEntity;
import com.data.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserSignUpResponse register(UserSignUpRequest userSignUpRequest) {

        if (userRepository.findByEmail(userSignUpRequest.getEmail()).isPresent()) {
            throw new AlreadyExistsException();
        }

        final UserEntity userEntity = buildUserEntity(userSignUpRequest);

        userRepository.save(userEntity);

        return UserSignUpResponse.builder()
                .userId(userEntity.getId())
                .build();
    }

    public UserLoginResponse login(UserLoginRequest userLoginRequest) {
        final Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(userLoginRequest.getEmail());

        if (!optionalUserEntity.isPresent()) {
            throw new UserNotFoundException();
        }

        final UserEntity userEntity = optionalUserEntity.get();

        if (!userLoginRequest.getPassword().equalsIgnoreCase(userEntity.getPassword())) {
            throw new WrongPasswordException();
        }

        return UserLoginResponse.builder()
                .id(userEntity.getId())
                //TODO add todos
                //.todoList()
                .status(UserStatusEnum.of(userEntity.getStatus()))
                .build();
    }

    private UserEntity buildUserEntity(UserSignUpRequest userSignUpRequest) {
        return UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .email(userSignUpRequest.getEmail())
                .status(UserStatusEnum.ACTIVE.getState())
                .firstName(userSignUpRequest.getFirstName())
                .lastName(userSignUpRequest.getLastName())
                .password(userSignUpRequest.getPassword())
                .registrationDate(LocalDateTime.now())
                .build();
    }
}
