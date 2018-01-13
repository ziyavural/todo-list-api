package com.data.web;

import com.data.model.todo.*;
import com.data.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @RequestMapping(method = RequestMethod.POST, path = "/todos", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<TodoCreateResponse> add(@RequestBody TodoCreateRequest todoCreateRequest) {
        final TodoCreateResponse todoCreateResponse = todoService.create(todoCreateRequest);
        return new ResponseEntity<>(todoCreateResponse, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/todos/{id}")
    public TodoListResponse get(@PathVariable("id") UUID id) {
        return todoService.getTodos(id);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/todos/{id}")
    public ResponseEntity<TodoUpdateResponse> update(@PathVariable("id") UUID id, @RequestBody TodoUpdateRequest todoUpdateRequest) {
        TodoUpdateResponse todoUpdateResponse = todoService.update(todoUpdateRequest, id);
        return new ResponseEntity<>(todoUpdateResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        todoService.delete(id);
    }
}
