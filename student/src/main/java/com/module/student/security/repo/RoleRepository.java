package com.module.student.security.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.module.student.security.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Optional<Role> findByName(String name);

}
