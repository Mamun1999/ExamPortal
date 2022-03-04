package com.mamun.exam.model;

import org.springframework.security.core.GrantedAuthority;

//check whteher an admin or general
public class Authority implements GrantedAuthority {

    private String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
