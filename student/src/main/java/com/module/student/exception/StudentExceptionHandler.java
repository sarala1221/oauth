package com.module.student.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
@RestController
public class StudentExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<Object> handleStudentNotFoundException(Exception ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Student not found");
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", HttpStatus.NOT_FOUND.name().toLowerCase());
		body.put("path", request.getContextPath());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(ConstraintViolationException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
				.collect(Collectors.toList()));
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", HttpStatus.BAD_REQUEST.name().toLowerCase());
		body.put("path", request.getContextPath());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {

		ex.printStackTrace();
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.name().toLowerCase());
		body.put("path", request.getContextPath());
		body.put("message", "Unable to prosess the request, please try after sometime!!");

		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Object> handleUnAuthorizedException(AccessDeniedException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("error", HttpStatus.FORBIDDEN.name().toLowerCase());
		body.put("path", request.getContextPath());

		return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<Object> handleTokenExpireException(TokenExpiredException ex, WebRequest request) {

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", "Jwt Token Expired!!");
		body.put("status", HttpStatus.FORBIDDEN.value());
		body.put("error", HttpStatus.FORBIDDEN.name().toLowerCase());
		body.put("path", request.getContextPath());

		return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
	}

}
