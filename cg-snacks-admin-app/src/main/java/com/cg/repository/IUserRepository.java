package com.cg.repository;

import com.cg.model.entity.User;
import com.cg.model.dto.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    Iterable<User> findAllByRole(RoleDto roleDto);
    User findByUsername(String username);
    Page<User> findAllByFullNameContaining(String fullName, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT r.name FROM roles r " +
                    "INNER JOIN users u ON r.id = u.role_id " +
                    "WHERE u.username = :username")
    List<String> findRolesByUsername(@Param("username") String username);
}
