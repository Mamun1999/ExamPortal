package com.mamun.exam.repository;

import com.mamun.exam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //this  interface is for insert delete update create etc
     public User findByUsername(String username);//write camel case after by

    //get user byusername



}
