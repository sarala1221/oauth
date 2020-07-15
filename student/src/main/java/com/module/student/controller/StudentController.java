package com.module.student.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.module.student.model.StudentDto;
import com.module.student.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/students")
@Validated
@Slf4j
public class StudentController {
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private StudentService studentService;

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@PostMapping(value = "/enroll", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> enrollStudent(@Valid @RequestBody StudentDto student,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
//		log.info("Enrolling student Name: {}", student.getFirst_name() + " " + student.getLast_name());
		studentService.addStudent(student);

		return new ResponseEntity<>(messageSource.getMessage("http.create.success.msg", null, locale),
				HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping(value = "/update")
	public ResponseEntity<String> updateStudent(@RequestBody StudentDto student) {
//		log.info("Updating student Info with Student Id: {}", student.getStuid());
		studentService.addStudent(student);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PatchMapping(value = "/updatePhone")
	public ResponseEntity<String> updateStudentPhone(@RequestParam String id, @RequestParam String phone) {
//		log.info("Updating student Phone: {} for Student Id: {}", phone, id);
		studentService.patchUpdateStudent(Long.valueOf(id), phone);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping(value = "/student")
	public ResponseEntity<StudentDto> getStudentRecord(@RequestParam String id) {
//		log.info("Found Student for the given Student Id: {}", id);
		return new ResponseEntity<StudentDto>(studentService.getStudentById(Long.valueOf(id)), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<StudentDto>> getStudents() {
		return new ResponseEntity<List<StudentDto>>(studentService.getStudents(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(value = "/remove")
	public ResponseEntity<String> deleteStudent(@RequestParam long id) {
//		log.info("Deleting Student for the given Student Id: {}", id);
		studentService.deleteStudent(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
