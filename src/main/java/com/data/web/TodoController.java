package com.data.web;

import com.data.model.todo.*;
import com.data.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping(method = RequestMethod.POST, path = "/todos", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<TodoCreateResponse> add(@RequestBody TodoCreateRequest todoCreateRequest) {
        final String todoId = todoService.create(todoCreateRequest);
        return new ResponseEntity<>(TodoCreateResponse.builder().id(todoId).build(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/todos/{id}")
    public TodoListResponse get(@PathVariable("id") UUID id) {
        return new TodoListResponse(todoService.getTodos(id));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/todos/{id}")
    public ResponseEntity<TodoUpdateResponse> update(@PathVariable("id") UUID id, @RequestBody TodoUpdateRequest todoUpdateRequest) {
        final String todoId = todoService.update(todoUpdateRequest, id);
        return new ResponseEntity<>(TodoUpdateResponse.builder().id(todoId).build(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        todoService.delete(id);
    }
}
