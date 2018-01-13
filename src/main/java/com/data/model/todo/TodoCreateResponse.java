package com.data.model.todo;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class TodoCreateResponse {

    private final String id;
}
