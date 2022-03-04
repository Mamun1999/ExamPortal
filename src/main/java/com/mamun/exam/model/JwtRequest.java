package com.mamun.exam.model;

//take the username and password for verfication ant then return a token using jwtresponse class
public class JwtRequest {
     String username;
     String password;

    public JwtRequest() {

    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
