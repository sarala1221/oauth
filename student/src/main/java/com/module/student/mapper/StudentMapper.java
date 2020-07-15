package com.module.student.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.module.student.entity.Student;
import com.module.student.model.StudentDto;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	StudentDto studentEntityToStudentDto(Student student);

	Student studentDtoToStudentEntity(StudentDto studentDto);

	List<StudentDto> studentEntitiesToStudentDtos(List<Student> student);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, ignoreByDefault = false)
	Student updateStudentFromDto(StudentDto sourceStudent, @MappingTarget Student destStudent);
}
