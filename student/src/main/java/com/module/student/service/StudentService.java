package com.module.student.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.module.student.dao.StudentDAO;
import com.module.student.entity.Student;
import com.module.student.mapper.StudentMapper;
import com.module.student.model.StudentDto;

@Service
public class StudentService {

	@Autowired
	private StudentDAO studentDAO;

	private StudentMapper mapper = Mappers.getMapper(StudentMapper.class);

	@Transactional(value = TxType.REQUIRED)
	public void addStudent(StudentDto studentDto) {
		studentDAO.addStudent(mapper.studentDtoToStudentEntity(studentDto));

	}

	@Transactional(value = TxType.REQUIRED)
	public void updateStudent(StudentDto studentDto) {
		Student student = studentDAO.getStudentById(studentDto.getStuid());
		studentDAO.updateStudent(mapper.updateStudentFromDto(studentDto, student));
	}

	public void patchUpdateStudent(long id, String phone) {
		studentDAO.patchUpdateStudent(id, phone);
	}

	public StudentDto getStudentById(long id) {

		return mapper.studentEntityToStudentDto(studentDAO.getStudentById(id));
	}

	public void deleteStudent(long id) {
		studentDAO.deleteStudent(id);
	}

	public List<StudentDto> getStudents() {
		return mapper.studentEntitiesToStudentDtos(studentDAO.getStudents());
	}

}
