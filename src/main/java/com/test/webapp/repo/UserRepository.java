package com.test.webapp.repo;

import com.test.webapp.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    @Query(value = "select last_login from users where users.id = :inputId", nativeQuery = true)
    Date findLastLogin(@Param("inputId") Long inputId);

    @Modifying
    @Transactional
    @Query(value = "insert into users(birth_date, email, first_name, last_login, last_name, password) values (:birthDate, :email, :firstName, :lastLogin, :lastName, :password)", nativeQuery = true)
    void registerNewUser(
        @Param("email") String email,
        @Param("firstName") String firstName,
        @Param("lastName") String lastName,
        @Param("password") String password,
        @Param("lastLogin") Date lastLogin,
        @Param("birthDate") String birthDate
    );

    @Query(value = "select concat(first_name, ' ', last_name) from users where email = :email", nativeQuery = true)
    String getNameByEmail(@Param("email") String email);
}
