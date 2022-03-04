package com.mamun.exam.service.impl;

import com.mamun.exam.model.User;
import com.mamun.exam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//this class is used for -using loadUserByUsername method
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //We have to get the user name so we autowired UserRepository class for using the method findbyUsername

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       User user= this.userRepository.findByUsername(username);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("No user found");
        }
        return user;
    }
}
