package com.example.examserverportal.repository;

import com.example.examserverportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
