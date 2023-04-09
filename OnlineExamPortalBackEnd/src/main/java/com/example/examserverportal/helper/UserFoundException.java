package com.example.examserverportal.helper;

public class UserFoundException extends Exception{

    public UserFoundException(){
        super("User with this Username is already exist in DB !! try with another");
    }
    public  UserFoundException(String msg){
        super(msg);
    }
}
