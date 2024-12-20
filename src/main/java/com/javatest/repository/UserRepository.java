package com.javatest.repository;


import com.javatest.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.phone = :phone OR u.cell = :cell")
    List<User> findByPhoneOrCell(@Param("phone") String phone, @Param("cell") String cell);

    @Query(value = "SELECT u FROM User u ORDER BY id LIMIT :limit OFFSET :offset")
    List<User> findAll(@Param("limit") int limit, @Param("offset") int offset);
}
