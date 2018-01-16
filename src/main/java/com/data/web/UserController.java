package com.data.web;

import com.data.model.user.UserLoginRequest;
import com.data.model.user.UserLoginResponse;
import com.data.model.user.UserSignUpRequest;
import com.data.model.user.UserSignUpResponse;
import com.data.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<UserSignUpResponse> register(@RequestBody UserSignUpRequest userSignUpRequest) {
        final UserSignUpResponse userSignUpResponse = userService.register(userSignUpRequest);
        return new ResponseEntity<>(userSignUpResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = userService.login(userLoginRequest);
        return new ResponseEntity<>(userLoginResponse, HttpStatus.OK);
    }
}
