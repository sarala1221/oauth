package com.module.student.dao;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.module.student.security.entity.ERole;
import com.module.student.security.entity.Role;
import com.module.student.security.entity.User;
import com.module.student.security.repo.UserRepository;

@Component
@Profile(value = { "dev", "stage" })
public class UserRoleManagerDAO {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostConstruct
	public void initialize() {

		User user = buildUser("testuser1", "testuser1$");
		Set<Role> roles = new HashSet<Role>();
		roles.add(new Role(ERole.ROLE_MODERATOR));
		roles.add(new Role(ERole.ROLE_ADMIN));
		roles.add(new Role(ERole.ROLE_USER));
		user.setRoles(roles);
		userRepository.save(user);

		User user2 = buildUser("testuser2", "testuser2$");
		Set<Role> roles1 = new HashSet<Role>();
		roles1.add(new Role(ERole.ROLE_MODERATOR));
		roles1.add(new Role(ERole.ROLE_ADMIN));
		user2.setRoles(roles1);
		
		userRepository.save(user2);
		User user3 = buildUser("testuser3", "testuser3$");
		Set<Role> roles3 = new HashSet<Role>();
		roles3.add(new Role(ERole.ROLE_MODERATOR));
		user3.setRoles(roles3);
		userRepository.save(user3);
	}

	private User buildUser(String username, String paswrd) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(encoder.encode(paswrd));
		return user;
	}
}
