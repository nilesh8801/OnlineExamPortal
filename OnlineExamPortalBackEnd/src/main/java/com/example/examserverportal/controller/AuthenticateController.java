
package com.example.examserverportal.controller;

import com.example.examserverportal.configration.JwtUtil;
import com.example.examserverportal.model.JwtRequest;
import com.example.examserverportal.model.JwtResponse;

import com.example.examserverportal.model.User;
import com.example.examserverportal.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
@CrossOrigin("*")
@RestController
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;


    @Autowired
    JwtUtil jwtUtil;



    //generate token

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());

        }catch(UsernameNotFoundException e)
        {
            e.printStackTrace();
            throw new Exception("user not found");
        }

        ////////authenticate

       UserDetails userDetails= this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());

                String token= this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }



     private void authenticate(String username, String password) throws Exception {

          try{
              authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }   catch(DisabledException e){
              throw new Exception("User Disabled" +e.getMessage());
          }catch(BadCredentialsException e){
              throw new Exception("Invalid credentials" +e.getMessage());
          }


     }
             //return details of current user

     @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return ((User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
     }
}
