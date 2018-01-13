package com.data.model.todo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TodoListResponse {

    private final List<Todo> todoList;
}
