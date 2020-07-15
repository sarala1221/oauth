package com.module.student.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.module.student.entity.Subject;


@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long>{

}
