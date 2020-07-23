package com.module.student.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.student.dao.StudentDAO;
import com.module.student.entity.Student;
import com.module.student.model.StudentDto;

@Service
public class StudentService {

	@Autowired
	private StudentDAO studentDAO;

	ObjectMapper objectMapper = new ObjectMapper();

	@Transactional(value = TxType.REQUIRED)
	public void addStudent(StudentDto studentDto) {
		studentDAO.addStudent(objectMapper.convertValue(studentDto, Student.class));

	}

	public void patchUpdateStudent(long id, String phone) {
		studentDAO.patchUpdateStudent(id, phone);
	}

	public StudentDto getStudentById(long id) {

		return objectMapper.convertValue(studentDAO.getStudentById(id), StudentDto.class);
	}

	public void deleteStudent(long id) {
		studentDAO.deleteStudent(id);
	}

	public List<StudentDto> getStudents() {
		List<StudentDto> studentDtos = new ArrayList<>();
		studentDAO.getStudents().forEach(stu -> {
			studentDtos.add(objectMapper.convertValue(stu, StudentDto.class));
		});
		return studentDtos;
	}

}
