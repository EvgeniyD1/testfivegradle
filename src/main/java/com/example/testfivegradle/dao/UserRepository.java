package com.example.testfivegradle.dao;

import com.example.testfivegradle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(value = "select * from users where username like :username%", nativeQuery = true)
    List<User> findAllByUsername(@Param("username") String username);

}
