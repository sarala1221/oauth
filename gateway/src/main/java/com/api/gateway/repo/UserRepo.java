package com.api.gateway.repo;

import com.api.gateway.domain.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity, Integer> {

    @Query("from UserEntity u where u.username= :username")
    UserEntity findbyName(String username);
}
