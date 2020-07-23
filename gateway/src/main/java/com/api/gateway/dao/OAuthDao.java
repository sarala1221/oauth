package com.api.gateway.dao;

import java.util.Optional;

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
	public Optional<UserEntity> getUserDetails(String username) {

		return Optional.of(userRepo.findbyName(username));
	}
}
