package com.data.model.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginRequest {

    private final String email;
    private final String password;
}
