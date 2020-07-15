package com.module.student.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
//@EnableWebSecurity
@Deprecated
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment envProp;
	
	// Create 2 users for demo
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		String user = envProp.getProperty("spring.security.user.username");
		String password = envProp.getProperty("spring.security.user.password");
		auth.inMemoryAuthentication().withUser(user).password(passwordEncoder().encode(password)).roles("USER")
				.and().withUser("admin").password(passwordEncoder().encode("password")).roles("USER", "ADMIN");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/v1/student/allStudents")
				.hasAnyRole("USER", "ADMIN").antMatchers("/v1/student/enroll").hasRole("ADMIN")
				.antMatchers("/v1/student/update").hasRole("ADMIN").antMatchers("/v1/student/delete").hasRole("ADMIN")
				.and().csrf().disable().formLogin().disable();

	}
}