package com.data.persistence.entity;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import com.data.model.user.UserStatusEnum;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import java.time.LocalDateTime;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Document
@Getter
@Builder
public class UserEntity {

    @Id
    @Field
    @GeneratedValue(strategy = UNIQUE)
    private String id;

    @Field
    private String email;

    @Field
    private UserStatusEnum status;

    @Field
    private String firstName;

    @Field
    private String lastName;

    @Field
    private String password;

    @Field
    @CreatedDate
    private LocalDateTime registrationDate;
}