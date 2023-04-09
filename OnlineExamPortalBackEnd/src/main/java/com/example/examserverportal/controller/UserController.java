package com.example.examserverportal.controller;


import com.example.examserverportal.model.Role;
import com.example.examserverportal.model.User;
import com.example.examserverportal.model.UserRole;
import com.example.examserverportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {



    @Autowired
    UserService  userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //creating user
    @CrossOrigin("*")
    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {
        Set<UserRole> roles= new HashSet<>();
        user.setProfile("default.png");

        //encoding password with bcryptpasswordencoder
        user.setPassword((this.bCryptPasswordEncoder.encode((user.getPassword()))));

        Role role= new Role();
        role.setRoleId((2L));
        role.setRoleName("Normal");

        UserRole userRole= new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);
        return this.userService.createUser(user, roles);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username)
    {
        return this.userService.getUser(username);
    }


    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteuser(userId);
    }
}
