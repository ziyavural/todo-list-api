package com.data.web;

import com.data.TestUtil;
import com.data.model.user.UserLoginResponse;
import com.data.model.user.UserSignUpResponse;
import com.data.model.user.UserStatusEnum;
import com.data.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService mockUserService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(mockUserService)).build();
    }

    @Test
    public void register() throws Exception {
        //given
        String jsonStr = TestUtil.readFile("json/controller/userSignUpRequest.json");
        final UserSignUpResponse userSignUpResponse = UserSignUpResponse.builder().userId("asdd-adsd2e-dsads-dasd").build();

        //when
        when(mockUserService.register(any())).thenReturn(userSignUpResponse);

        //then
        mockMvc.perform(RestDocumentationRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId", is("asdd-adsd2e-dsads-dasd")));

    }

    @Test
    public void login() throws Exception {
        //given
        String jsonStr = TestUtil.readFile("json/controller/userLoginRequest.json");
        final UserLoginResponse userLoginResponse = UserLoginResponse.builder().id("asda12312asd").status(UserStatusEnum.ACTIVE).build();

        //when
        when(mockUserService.login(any())).thenReturn(userLoginResponse);

        //then
        mockMvc.perform(RestDocumentationRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStr))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("asda12312asd")));
    }
}