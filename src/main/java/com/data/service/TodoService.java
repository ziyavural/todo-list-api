package com.data.service;

import com.data.model.exception.TodoNotFoundException;
import com.data.model.exception.UserNotFoundException;
import com.data.model.todo.*;
import com.data.persistence.entity.TodoEntity;
import com.data.persistence.entity.UserEntity;
import com.data.persistence.repository.TodoRepository;
import com.data.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    public TodoCreateResponse create(TodoCreateRequest todoCreateRequest) {
        final Optional<UserEntity> userEntity = Optional.ofNullable(userRepository.findOne(todoCreateRequest.getCreatedByUserUuid()));

        if(!userEntity.isPresent()) {
            throw new UserNotFoundException();
        }

        final TodoEntity todoEntity = buildTodoEntity(todoCreateRequest);
        final String todoId = todoRepository.save(todoEntity).getId();
        return TodoCreateResponse.builder().id(todoId).build();
    }

    public TodoListResponse getTodos(UUID id) {
        final List<TodoEntity> todoEntities = todoRepository.findAllByCreatedByUser(id.toString());
        final List<Todo> todos = todoEntities.stream().parallel().map(this::convert).collect(Collectors.toList());
        return new TodoListResponse(todos);
    }

    public TodoUpdateResponse update(TodoUpdateRequest todoUpdateRequest, UUID todoId) {
        final Optional<TodoEntity> optiponalTodoEntity = Optional.ofNullable(todoRepository.findOne(todoId.toString()));

        if (!optiponalTodoEntity.isPresent()) {
            throw new TodoNotFoundException();
        }

        final TodoEntity todoEntity = optiponalTodoEntity.get();

        todoEntity.setTitle(todoUpdateRequest.getTitle());
        todoEntity.setDescription(todoUpdateRequest.getDescription());
        todoRepository.save(todoEntity);
        return TodoUpdateResponse.builder().id(todoId.toString()).build();
    }

    public void delete(UUID id) {
        todoRepository.delete(id.toString());
    }

    private TodoEntity buildTodoEntity(TodoCreateRequest todoCreateRequest) {
        final String id = UUID.randomUUID().toString();
        final LocalDateTime now = LocalDateTime.now();

        return TodoEntity.builder()
                .id(id)
                .createdByUser(todoCreateRequest.getCreatedByUserUuid())
                .creationTime(now)
                .description(todoCreateRequest.getDescription())
                .modificationTime(now)
                .title(todoCreateRequest.getTitle())
                .build();
    }

    private Todo convert(TodoEntity todoEntity) {
        return Todo.builder()
                .id(todoEntity.getId())
                .title(todoEntity.getTitle())
                .description(todoEntity.getDescription())
                .creationDate(todoEntity.getCreationTime())
                .modifiedDate(todoEntity.getModificationTime())
                .build();
    }
}
