package com.data.configuration;

import com.data.persistence.repository.TodoRepository;
import com.data.persistence.repository.UserRepository;
import com.data.service.TodoService;
import com.data.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfigurationIT.Config.class})
public class ServiceConfigurationIT {

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @Test
    public void shouldLoadContext() {
        assertThat("UserService could load successfully", userService, notNullValue());
        assertThat("TodoService could load successfully", todoService, notNullValue());
    }


    @Configuration
    @Import(value = {ServiceConfiguration.class})
    protected static class Config {

        @Bean
        public UserRepository userRepository() {
            return Mockito.mock(UserRepository.class);
        }

        @Bean
        public TodoRepository todoRepository() {
            return Mockito.mock(TodoRepository.class);
        }
    }
}
