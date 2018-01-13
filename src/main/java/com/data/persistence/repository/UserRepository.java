package com.data.persistence.repository;

import com.data.persistence.entity.UserEntity;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "userentity")
public interface UserRepository extends CrudRepository<UserEntity, String> {

    Optional<UserEntity> findByEmail(String email);
}
