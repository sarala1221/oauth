package com.module.student.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.module.student.entity.Course;


@Repository
public interface CourseRepository extends CrudRepository<Course, Long> {

}
