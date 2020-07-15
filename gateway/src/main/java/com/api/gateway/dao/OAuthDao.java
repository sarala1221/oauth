package com.api.gateway.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.gateway.domain.UserEntity;
import com.api.gateway.repo.UserRepo;

@Repository
public class OAuthDao {
	@Autowired
	private UserRepo userRepo;

	@Transactional
	public UserEntity getUserDetails(String username) {

		UserEntity user = userRepo.findbyName(username);
		return user;
	}
}
