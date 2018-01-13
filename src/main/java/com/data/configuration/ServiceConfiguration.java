package com.data.configuration;

import com.data.persistence.repository.TodoRepository;
import com.data.persistence.repository.UserRepository;
import com.data.service.TodoService;
import com.data.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    public TodoService todoService(TodoRepository todoRepository, UserRepository userRepository) {
        return new TodoService(todoRepository, userRepository);
    }

}
