package com.api.gateway.cfg;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic().disable().authorizeRequests().antMatchers("/oauth/**").permitAll().antMatchers("/api/v1/students")
				.hasAnyRole("USER", "MODERATOR").antMatchers("/api/v1/students/**").hasRole("ADMIN").anyRequest()
				.permitAll();
	}
}
