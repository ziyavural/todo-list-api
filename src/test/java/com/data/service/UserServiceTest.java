package com.data.service;

import com.data.model.exception.AlreadyExistsException;
import com.data.model.exception.UserNotFoundException;
import com.data.model.exception.WrongPasswordException;
import com.data.model.user.*;
import com.data.persistence.entity.UserEntity;
import com.data.persistence.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private TodoService mockTodoService;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(mockUserRepository, mockTodoService);
    }

    @Test(expected = AlreadyExistsException.class)
    public void regiterShouldThrowAlreadyExistsExceptionWhenEmailIsPresent() {
        //given
        final UserSignUpRequest userSignUpRequest = new UserSignUpRequest("ziya", "vural", "abc@gmail.com", "asdf");
        final UserEntity userEntity = UserEntity.builder().build();

        //when
        when(mockUserRepository.findByEmail(userSignUpRequest.getEmail())).thenReturn(Optional.ofNullable(userEntity));
        userService.register(userSignUpRequest);
        //code should not be reach here!!
        fail();
        //then
        verify(mockUserRepository).findByEmail(userSignUpRequest.getEmail());
    }

    @Test
    public void registerShouldInvokeSaveIfEmailIsNotExists() {
        //given
        final UserSignUpRequest userSignUpRequest = new UserSignUpRequest("ziya", "vural", "abc@gmail.com", "asdf");

        //when
        when(mockUserRepository.findByEmail(userSignUpRequest.getEmail())).thenReturn(Optional.ofNullable(null));
        final UserSignUpResponse userSignUpResponse = userService.register(userSignUpRequest);

        //then
        verify(mockUserRepository).save(any(UserEntity.class));
        assertNotNull(userSignUpResponse.getUserId());
    }

    @Test(expected = UserNotFoundException.class)
    public void loginShouldThrowUserNotFoundExceptionIfOptionalUserEntityIsPresent() {
        //given
        final UserLoginRequest userLoginRequest = new UserLoginRequest("asdf@hotmail.com", "asdf");

        //when
        when(mockUserRepository.findByEmail(userLoginRequest.getEmail())).thenReturn(Optional.ofNullable(null));
        userService.login(userLoginRequest);
        //code should not be reach here!!
        fail();
        //then
        verify(mockUserRepository).findByEmail(userLoginRequest.getEmail());
    }

    @Test(expected = WrongPasswordException.class)
    public void loginShouldThrowWrongPasswordExceptionWhenUserLoginRequestPasswordIsNotEqualsToUserEntityPassword() {
        //given
        final UserLoginRequest userLoginRequest = new UserLoginRequest("asdf@hotmail.com", "asdf");
        final UserEntity userEntity = UserEntity.builder().password("adsdasd").build();

        //when
        when(mockUserRepository.findByEmail(userLoginRequest.getEmail())).thenReturn(Optional.ofNullable(userEntity));
        userService.login(userLoginRequest);
        //code should not be reach here!!
        fail();
        //then
        verify(mockUserRepository).findByEmail(userLoginRequest.getEmail());
    }

    @Test
    public void loginShouldReturnUserLoginResponseWhichHasSameIdWithIdOfUserEntity() {
        //given
        final UserLoginRequest userLoginRequest = new UserLoginRequest("asdf@hotmail.com", "asdf");
        final UserEntity userEntity = UserEntity.builder().status(UserStatusEnum.ACTIVE).id(UUID.randomUUID().toString()).password("asdf").build();

        //when
        when(mockUserRepository.findByEmail(userLoginRequest.getEmail())).thenReturn(Optional.ofNullable(userEntity));
        final UserLoginResponse userLoginResponse = userService.login(userLoginRequest);

        //then
        assertThat("Id of userLoginResponse should be equal to id of userEntity", userLoginResponse.getId(), is(userEntity.getId()));
    }
}