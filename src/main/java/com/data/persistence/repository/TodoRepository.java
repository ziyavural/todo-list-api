package com.data.persistence.repository;


import com.data.persistence.entity.TodoEntity;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "todoentity")
public interface TodoRepository extends CrudRepository<TodoEntity, String> {

    List<TodoEntity> findAllByCreatedByUser(String userId);
}