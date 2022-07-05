package com.test.webapp.repo;

import com.test.webapp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

    @Query(value = "select last_login from users where users.id = :inputId", nativeQuery = true)
    Date findLastLogin(@Param("inputId") Long inputId);
}
