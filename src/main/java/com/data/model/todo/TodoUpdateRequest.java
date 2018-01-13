package com.data.model.todo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TodoUpdateRequest {

    private final String title;
    private final String description;
}
