package com.data.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserSignUpRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
}
