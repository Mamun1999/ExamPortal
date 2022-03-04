package com.mamun.exam.helper;

public class UserNotFoundException extends Exception {
    
    public UserNotFoundException(){
        super("User with this name is not found!!");
    }
    public UserNotFoundException(String msg){
        super(msg);
    }
}
