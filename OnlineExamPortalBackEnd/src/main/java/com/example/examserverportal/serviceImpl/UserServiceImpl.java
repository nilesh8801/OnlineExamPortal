package com.example.examserverportal.serviceImpl;

import com.example.examserverportal.helper.UserFoundException;
import com.example.examserverportal.model.User;
import com.example.examserverportal.model.UserRole;
import com.example.examserverportal.repository.RoleRepository;
import com.example.examserverportal.repository.UserRepository;
import com.example.examserverportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.management.relation.Relation;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    //creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local= this.userRepository.findByUsername(user.getUsername());
        if(local !=null){
            System.out.println("user is alredy there !!");
            throw new UserFoundException();
        }else{
             //user create
            for(UserRole ur : userRoles){

                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);
            local =this.userRepository.save(user);
    }
     return  local;
}

    @Override
    public User getUser(String username) {
        return  this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteuser(Long userId) {
        this. userRepository.deleteById(userId);

    }




}