package com.module.student.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.student.entity.Student;
import com.module.student.model.StudentDto;

public class TestUtil {
	static ObjectMapper mapper = new ObjectMapper();

	public static String loadStudent() {
		try (FileInputStream fis = new FileInputStream("src/test/resources/student.json")) {
			return IOUtils.toString(fis, "UTF-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static StudentDto studentDto() {

		try {
			return mapper.readValue(loadStudent(), StudentDto.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	public static Student student() {

		try {
			return mapper.readValue(loadStudent(), Student.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
