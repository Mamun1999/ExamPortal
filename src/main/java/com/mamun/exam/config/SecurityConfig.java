package com.mamun.exam.config;

import com.mamun.exam.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //Who is able to access a particular api? general or admin

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private jwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
   private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
  private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //for loadUserByUsername
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http
             .csrf()
             .disable()
             .cors()
             .disable()
             .authorizeRequests()
             .antMatchers("/generate-token","/user/").permitAll()
             .antMatchers(HttpMethod.OPTIONS).permitAll()
             .anyRequest().authenticated()
             .and()
             .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
             //We have created class jwtAuthenticationEntrypoint for unauthorizedhandler
             .and()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

             http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
             //we have to create JWtAuthenticationFilter class for for validating token and give the access to user for using api.

    }
}
