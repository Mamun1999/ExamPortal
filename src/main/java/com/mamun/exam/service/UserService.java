package com.mamun.exam.service;

import com.mamun.exam.model.User;
import com.mamun.exam.model.UserRole;

import java.util.Set;

// this is for cretaing an abstract method for implementation class for reuse code or increase the flexibility of code
public interface UserService {
      public User createUser(User user, Set<UserRole> userRoles) throws Exception;

      //get user by user name
      public User getUser(String username);
      //delete user

      public void deleteUser(Long userId);

    //  public User updateUser(String username);
}

