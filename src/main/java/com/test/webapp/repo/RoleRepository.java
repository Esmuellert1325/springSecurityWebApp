package com.test.webapp.repo;

import com.test.webapp.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query(value = "select roles.role, roles.id from roles, users_roles where users_roles.user_id = :inputId and users_roles.role_id = roles.id", nativeQuery = true)
    List<Role> findAllRoles(@Param("inputId") Long inputId);
    @Query(value = "select id from users where name = :name", nativeQuery = true)
    Long getUserId(@Param("name") String name);

}
