package com.api.gateway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.gateway.dao.OAuthDao;
import com.api.gateway.domain.CustomUser;
import com.api.gateway.domain.UserEntity;

@Service
public class CustomDetailsService implements UserDetailsService {

	Logger log = LoggerFactory.getLogger(CustomDetailsService.class);
	@Autowired
	OAuthDao oauthDao;

	@Override
	public CustomUser loadUserByUsername(final String username) throws UsernameNotFoundException {
		UserEntity userEntity = null;
		try {
			userEntity = oauthDao.getUserDetails(username);
			CustomUser customUser = new CustomUser(userEntity);
			log.info("getAuthorities :::: ",customUser.getAuthorities());
			return customUser;
		} catch (Exception e) {
			log.error("User {},  was not found in the database",username);
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
	}
}
