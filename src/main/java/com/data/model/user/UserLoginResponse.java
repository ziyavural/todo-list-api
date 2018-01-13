package com.data.model.user;

import com.data.model.todo.Todo;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class UserLoginResponse {

    private final String id;
    private final List<Todo> todoList;
    private final UserStatusEnum status;
}
