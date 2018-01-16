package com.data.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UserLoginResponse {

    private final String id;
    private final UserStatusEnum status;
}
