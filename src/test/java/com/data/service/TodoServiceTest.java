package com.data.service;

import com.data.model.exception.TodoNotFoundException;
import com.data.model.exception.UserNotFoundException;
import com.data.model.todo.*;
import com.data.persistence.entity.TodoEntity;
import com.data.persistence.entity.UserEntity;
import com.data.persistence.repository.TodoRepository;
import com.data.persistence.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository mockTodoRepository;

    @Mock
    private UserRepository mockUserRepository;

    private TodoService todoService;

    @Before
    public void setUp() throws Exception {
        todoService = new TodoService(mockTodoRepository, mockUserRepository);
    }

    @Test
    public void createShouldThrowUserNotFoundExceptionIfUserEntityIsNotPresent() {
        //given
        final TodoCreateRequest todoCreateRequest = new TodoCreateRequest("12dasdas", "crossfit", "push press");

        //when
        when(mockUserRepository.findOne(todoCreateRequest.getCreatedByUserUuid())).thenReturn(null);
        try {
            todoService.create(todoCreateRequest);
            //code should not be reach here!!
            fail();
        } catch (UserNotFoundException us) {

        }
    }

    @Test
    public void createShouldInvokeSaveIfUserEntityIsPresent() {
        //given
        final TodoCreateRequest todoCreateRequest = new TodoCreateRequest("12dasdas", "crossfit", "push press");
        final UserEntity userEntity = UserEntity.builder().build();
        final TodoEntity todoEntity = TodoEntity.builder().id("asdf").build();

        //when
        when(mockUserRepository.findOne(todoCreateRequest.getCreatedByUserUuid())).thenReturn(userEntity);
        when(mockTodoRepository.save(any(TodoEntity.class))).thenReturn(todoEntity);
        final TodoCreateResponse todoCreateResponse = todoService.create(todoCreateRequest);

        //then
        verify(mockTodoRepository).save(any(TodoEntity.class));
        assertNotNull(todoCreateResponse.getId());
    }

    @Test
    public void todoSizeOfTodoListResponseShouldBeEqualToSizeOfTodoEntities() {
        //given
        final UUID userid = UUID.randomUUID();
        final TodoEntity todoEntity = TodoEntity.builder().id("asdf").title("asd").description("adsd").creationTime(LocalDateTime.now()).modificationTime(LocalDateTime.now()).build();
        final TodoEntity todoEntity2 = TodoEntity.builder().id("asdf").title("asd").description("adsd").creationTime(LocalDateTime.now()).modificationTime(LocalDateTime.now()).build();
        final TodoEntity todoEntity3 = TodoEntity.builder().id("asdf").title("asd").description("adsd").creationTime(LocalDateTime.now()).modificationTime(LocalDateTime.now()).build();
        final TodoEntity todoEntity4 = TodoEntity.builder().id("asdf").title("asd").description("adsd").creationTime(LocalDateTime.now()).modificationTime(LocalDateTime.now()).build();
        final List<TodoEntity> todoEntityList = Lists.newArrayList(todoEntity, todoEntity2, todoEntity3, todoEntity4);

        //when
        when(mockTodoRepository.findAllByCreatedByUser(userid.toString())).thenReturn(todoEntityList);
        final TodoListResponse todoListResponse = todoService.getTodos(userid);

        //then
        assertEquals(todoEntityList.size(), todoListResponse.getTodoList().size());
    }

    @Test
    public void updateShouldThrowTodoNotFoundExceptionWhenOptionalTodoEntityIsNotPresent() {
        //given
        final TodoUpdateRequest todoUpdateRequest = new TodoUpdateRequest("12dasdas", "crossfit");
        final UUID uuid = UUID.randomUUID();

        //when
        when(mockTodoRepository.findOne(uuid.toString())).thenReturn(null);
        try {
            todoService.update(todoUpdateRequest, uuid);
            //code should not be reach here!!
            fail();
        } catch (TodoNotFoundException t) {
        }
    }

    @Test
    public void updateShouldInvokeSaveIfOptionalTodoEntityIsPresent() {
        //given
        final TodoUpdateRequest todoUpdateRequest = new TodoUpdateRequest("12dasdas", "crossfit");
        final UUID uuid = UUID.randomUUID();
        final TodoEntity todoEntity = TodoEntity.builder().id("asdf").build();

        //when
        when(mockTodoRepository.findOne(uuid.toString())).thenReturn(todoEntity);
        final TodoUpdateResponse todoUpdateResponse = todoService.update(todoUpdateRequest, uuid);

        //then
        verify(mockTodoRepository).save(any(TodoEntity.class));
    }
}