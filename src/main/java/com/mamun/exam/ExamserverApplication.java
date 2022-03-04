package com.mamun.exam;

import com.mamun.exam.helper.UserFoundException;
import com.mamun.exam.model.Role;
import com.mamun.exam.model.User;
import com.mamun.exam.model.UserRole;
import com.mamun.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
         

		SpringApplication.run(ExamserverApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		try{
		System.out.println("Starting code");

		User user=new User();
		user.setFirstName("Abdullah");
		user.setLastName("Mamun");
		user.setUsername("mamun19999");
		user.setPassword(this.bCryptPasswordEncoder.encode("12345"));
		user.setEmail("mamun@gmail.com");
		user.setProfile("default.png");

		Role role1=new Role();
		role1.setRoleId(4L);
		role1.setRoleName("Admin");

		Set<UserRole> userRoleSet=new HashSet<>();
		UserRole userRole=new UserRole();
		userRole.setRole(role1);
		userRole.setUser(user);

		userRoleSet.add(userRole);

		User user1=this.userService.createUser(user,userRoleSet);
		System.out.println(user1.getUsername());
		}catch(UserFoundException e){
			e.printStackTrace();

		}


	}
}
