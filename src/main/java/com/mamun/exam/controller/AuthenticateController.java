package com.mamun.exam.controller;

import java.security.Principal;

import com.mamun.exam.config.JwtUtils;
import com.mamun.exam.helper.UserNotFoundException;
import com.mamun.exam.model.JwtRequest;
import com.mamun.exam.model.JwtResponse;
import com.mamun.exam.model.User;
import com.mamun.exam.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class AuthenticateController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

//generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try{

            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch(UserNotFoundException e){
            e.printStackTrace();
            throw new Exception(("user not found"));
        }

        ///authenticate


        UserDetails userDetails= this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        //got user
        String token=this.jwtUtils.generateToken(userDetails);//generating token
  // got token

        return  ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
      //need to authenticate
        try{
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException e){
            throw new Exception("USER DISABLED" + e.getMessage());
        }catch(BadCredentialsException e){
            throw new Exception("Invalid Credential " +e.getMessage());
        }
    }

    //returns the details of current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){

    return (User) this.userDetailsService.loadUserByUsername(principal.getName());
    }
}
