package com.data.web;

import com.data.TestUtil;
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
        final String todoId = "abc-def";

        //when
        when(mockTodoService.create(any())).thenReturn(todoId);

        //then
        mockMvc.perform(RestDocumentationRequestBuilders.post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("abc-def")));
    }

    @Test
    public void update() throws Exception {
        //given
        String jsonStr = TestUtil.readFile("json/controller/todoUpdateRequest.json");
        final UUID id = UUID.randomUUID();

        //when
        when(mockTodoService.update(any(), any())).thenReturn(id.toString());

        //then
        mockMvc.perform(RestDocumentationRequestBuilders.put("/todos/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.toString())));
    }
}