package com.module.student.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.module.student.entity.Student;
import com.module.student.exception.StudentNotFoundException;
import com.module.student.repo.AddressRepository;
import com.module.student.repo.CourseRepository;
import com.module.student.repo.StudentRepository;
import com.module.student.repo.SubjectRepository;

@Component
public class StudentDAO {
	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private SubjectRepository subjectRepo;

	@Autowired
	private AddressRepository addressRepo;

	public void addStudent(Student student) {
		log.info("Enrolling student :{}", student.getFirst_name() + student.getLast_name());
		addressRepo.saveAll(student.getAddress());
		subjectRepo.saveAll(student.getCourse().getSubjects());
		courseRepo.save(student.getCourse());
		studentRepo.save(student);
	}

	@Transactional(value = TxType.REQUIRED)
	public void patchUpdateStudent(long id, String phone) {
		studentRepo.patchUpdateStudent(id, phone);
	}

	public Student getStudentById(long id) {
		try {
			Optional<Object> studetEntity = Optional.ofNullable(studentRepo.findById(id).get());
			return (Student) studetEntity.get();
		} catch (NoSuchElementException e) {
			throw new StudentNotFoundException();
		}
	}

	public void deleteStudent(long id) {
		try {
			studentRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new StudentNotFoundException();
		}
	}

	public List<Student> getStudents() {
		List<Student> students = new ArrayList<Student>();
		studentRepo.findAll().forEach(student -> students.add((Student) student));
		return students;
	}
}
