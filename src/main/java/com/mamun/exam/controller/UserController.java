package com.mamun.exam.controller;


import com.mamun.exam.helper.UserFoundException;
import com.mamun.exam.model.Role;
import com.mamun.exam.model.User;
import com.mamun.exam.model.UserRole;
import com.mamun.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws Exception {


        
          user.setProfile("default.png");

          user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
          
          Set<UserRole> userRoleSet=new HashSet<>();
          Role role=new Role();
          role.setRoleName("Normal");
          role.setRoleId(50L);
          UserRole userRole=new UserRole();
          userRole.setRole(role);
          userRole.setUser(user);

          userRoleSet.add(userRole);

        return this.userService.createUser(user,userRoleSet);
    }
    //get user by username

    @GetMapping("/{username}")
    public User getUser(@PathVariable ("username")String username){
        return this.userService.getUser(username);

    }
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable ("userId")Long userId){
        this.userService.deleteUser(userId);

    }

    // @ExceptionHandler(UserFoundException.class)
    // public ResponseEntity<?> exceptionHandler(UserFoundException ex){
    //     return ResponseEntity.;
    // }

    /*@PutMapping("/{username}")
    public void updateUser(@PathVariable ("username") String username){
        this.userService.updateUser(username);

    }*/
}
