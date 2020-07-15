package com.module.student.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.module.student.entity.Address;


@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{

}
