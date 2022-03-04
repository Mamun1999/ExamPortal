package com.mamun.exam.service.impl;

import com.mamun.exam.helper.UserFoundException;
import com.mamun.exam.model.User;
import com.mamun.exam.model.UserRole;
import com.mamun.exam.repository.RoleRepository;
import com.mamun.exam.repository.UserRepository;
import com.mamun.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//creating user
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local= this.userRepository.findByUsername(user.getUsername());
        if (local!= null){
              //first time check if an user has already saved in database or not
            System.out.println("User alreday exist here");
            throw new UserFoundException("User already present here");
        }
        else{
            // first find userrole then save each userRole in Role table cz we need to save user as a specific user like admin or general user.
            for(UserRole ur : userRoles){
                roleRepository.save(ur.getRole());
            }

            user.getUserRoles().addAll(userRoles);//assign userrole and save it
            local =this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

   // @Override
    //public User updateUser(String username) {
       // return this.userRepository.findByUsername(username);
   // }
}
