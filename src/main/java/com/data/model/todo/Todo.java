package com.data.model.todo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Todo {

    private String id;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime modifiedDate;
}