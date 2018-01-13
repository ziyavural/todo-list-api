package com.data.web;

import com.data.TestUtil;
import com.data.model.todo.TodoCreateResponse;
import com.data.model.todo.TodoUpdateResponse;
import com.data.service.TodoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TodoService mockTodoService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new TodoController(mockTodoService)).build();
    }

    @Test
    public void add() throws Exception {
        //given
        String jsonStr = TestUtil.readFile("json/controller/todoCreateRequest.json");
        final TodoCreateResponse todoCreateResponse = TodoCreateResponse.builder().id("asdsad-bdacdsfd-dasdasd").build();

        //when
        when(mockTodoService.create(any())).thenReturn(todoCreateResponse);

        //then
        mockMvc.perform(RestDocumentationRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("asdsad-bdacdsfd-dasdasd")));
    }

    @Test
    public void update() throws Exception {
        //given
        String jsonStr = TestUtil.readFile("json/controller/todoUpdateRequest.json");
        final TodoUpdateResponse todoUpdateResponse = TodoUpdateResponse.builder().id("asd-fdds-ddds-adas-dsd").build();
        final UUID id = UUID.randomUUID();

        //when
        when(mockTodoService.update(any(), any())).thenReturn(todoUpdateResponse);

        //then
        mockMvc.perform(RestDocumentationRequestBuilders.put("/todos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("asd-fdds-ddds-adas-dsd")));
    }
}