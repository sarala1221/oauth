package com.module.student.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.module.student.model.StudentDto;
import com.module.student.service.StudentService;

@RestController
@RequestMapping("/api/v1/students")
@Validated
public class StudentController {

	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private StudentService studentService;

	@PostMapping(value = "/enroll", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> enrollStudent(@Valid @RequestBody StudentDto student,
			@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		log.info("Enrolling student Name: {}", student.getFirst_name() + " " + student.getLast_name());
		studentService.addStudent(student);

		return new ResponseEntity<>(messageSource.getMessage("http.create.success.msg", null, locale),
				HttpStatus.CREATED);
	}

	@PatchMapping(value = "/updatePhone")
	public ResponseEntity<String> updateStudentPhone(@RequestParam String id, @RequestParam String phone) {
		log.info("Updating student Phone: {} for Student Id: {}", phone, id);
		studentService.patchUpdateStudent(Long.valueOf(id), phone);
		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/student")
	public ResponseEntity<StudentDto> getStudentRecord(@RequestParam String id) {
		log.info("Found Student for the given Student Id: {}", id);
		return new ResponseEntity<StudentDto>(studentService.getStudentById(Long.valueOf(id)), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<StudentDto>> getStudents() {
		return new ResponseEntity<List<StudentDto>>(studentService.getStudents(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/remove")
	public ResponseEntity<String> deleteStudent(@RequestParam long id) {
		log.info("Deleting Student for the given Student Id: {}", id);
		studentService.deleteStudent(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
