package com.data.model.user;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class User {

    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final String email;
}