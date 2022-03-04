package com.mamun.exam.helper;

public class UserFoundException extends Exception {
    
    public UserFoundException(){
        super("User with this name is already exist!!");
    }
    public UserFoundException(String msg){
        super(msg);
    }
}
