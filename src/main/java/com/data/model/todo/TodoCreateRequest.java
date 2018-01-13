package com.data.model.todo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoCreateRequest {

    private final String createdByUserUuid;
    private final String title;
    private final String description;
}
