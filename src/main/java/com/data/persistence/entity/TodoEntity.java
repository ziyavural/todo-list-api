package com.data.persistence.entity;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import java.time.LocalDateTime;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.UNIQUE;

@Document
@Getter
@Setter
@Builder
public class TodoEntity {

    @Id
    @Field
    @GeneratedValue(strategy = UNIQUE)
    private String id;

    @Field
    private String createdByUser;

    @Field
    private LocalDateTime creationTime;

    @Field
    private String description;

    @Field
    private LocalDateTime modificationTime;
}
