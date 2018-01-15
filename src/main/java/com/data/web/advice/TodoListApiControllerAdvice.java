package com.data.web.advice;

import com.data.model.exception.AlreadyExistsException;
import com.data.model.exception.TodoNotFoundException;
import com.data.model.exception.UserNotFoundException;
import com.data.model.exception.WrongPasswordException;
import com.data.model.todo.TodoCreateResponse;
import com.data.model.todo.TodoUpdateResponse;
import com.data.model.user.UserLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {
        RestController.class
})
@ResponseBody
@Slf4j
public class TodoListApiControllerAdvice {

    @ExceptionHandler(AlreadyExistsException.class)
    private ResponseEntity<TodoCreateResponse> handle(final AlreadyExistsException alreadyExistsException) {
        log.error("TodoListApiControllerAdvice handle alreadyExistsException.|Msg={}", alreadyExistsException);
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<UserLoginResponse> handle(UserNotFoundException userNotFoundException) {
        log.error("TodoListApiControllerAdvice handle userNotFoundException.|Msg={}", userNotFoundException);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPasswordException.class)
    private ResponseEntity<UserLoginResponse> handle(final WrongPasswordException wrongPasswordException) {
        log.error("TodoListApiControllerAdvice handle wrongPasswordException.|Msg={}", wrongPasswordException);
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(TodoNotFoundException.class)
    private ResponseEntity<TodoUpdateResponse> handle(final TodoNotFoundException todoNotFoundException) {
        log.error("TodoListApiControllerAdvice handle todoNotFoundException.|Msg={}", todoNotFoundException);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity handle(final Exception exception) {
        log.error("TodoListApi handle global exception.", exception);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}