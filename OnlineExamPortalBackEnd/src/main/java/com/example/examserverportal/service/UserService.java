package com.example.examserverportal.service;

import com.example.examserverportal.model.User;
import com.example.examserverportal.model.UserRole;

import java.util.Set;

public interface UserService {

    //create user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    //get user by username
    public User getUser(String username);

    //delete user by id
    public void deleteuser(Long userId);
}
