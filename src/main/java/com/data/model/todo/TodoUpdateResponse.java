package com.data.model.todo;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class TodoUpdateResponse {

    private final String id;
}
