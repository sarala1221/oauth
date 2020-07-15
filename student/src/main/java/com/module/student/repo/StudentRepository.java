package com.module.student.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.module.student.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	@Modifying
	@Query("update Student s set s.phone = :phone where s.id = :id")
	public void patchUpdateStudent(long id, String phone);
	@Query("from Student s where s.email = :email")
	public Optional<Student> findStudentByEmail(String email);
}
