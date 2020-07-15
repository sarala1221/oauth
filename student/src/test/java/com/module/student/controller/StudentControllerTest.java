package com.module.student.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.module.student.dao.StudentDAO;
import com.module.student.dao.UserRoleManagerDAO;
import com.module.student.entity.Student;
import com.module.student.exception.StudentNotFoundException;
import com.module.student.repo.AddressRepository;
import com.module.student.repo.CourseRepository;
import com.module.student.repo.StudentRepository;
import com.module.student.repo.SubjectRepository;
import com.module.student.security.repo.RoleRepository;
import com.module.student.security.repo.UserRepository;
import com.module.student.service.StudentService;
import com.module.student.util.TestUtil;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = { StudentController.class, StudentService.class, StudentDAO.class,
		UserRoleManagerDAO.class, })
@WebMvcTest
@ActiveProfiles("default")
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private final static String TEST_USER_ID = "testuser1";

	@MockBean
	private StudentRepository studentRepository;

	@MockBean
	private CourseRepository courseRepository2;

	@MockBean
	private SubjectRepository repository;

	@MockBean
	private AddressRepository addressRepository2;
	@MockBean
	private UserRepository userRepository;

	@MockBean
	private RoleRepository roleRepository;

	private String student() {
		return TestUtil.loadStudent();
	}

	@Test
	public void testEnrollStudnt() {
		HttpHeaders httpHeaders = new HttpHeaders();
		List<Locale> locales = new ArrayList<Locale>();
		Locale locale = new Locale(Locale.US.getLanguage());
		locales.add(locale);
		httpHeaders.setAcceptLanguageAsLocales(locales);
		MvcResult result;
		try {
			result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/students/enroll").with(csrf())
					.with(user("TEST_USER_ID")).content(student()).headers(httpHeaders)
					.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated()).andReturn();
			int restult = result.getResponse().getStatus();
			assertNotNull(restult);
			assertEquals("Student Enrolled Successfully", result.getResponse().getContentAsString());
			assertEquals(HttpStatus.CREATED.value(), restult);
		} catch (IllegalArgumentException e) {
		} catch (Exception e) {
		}

	}

	@Test
	public void testUpdateStudent() {

		try {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/v1/students/update")
					.with(user(TEST_USER_ID)).with(csrf()).content(student()).contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent()).andReturn();
			int restult = result.getResponse().getStatus();
			assertNotNull(restult);
			assertEquals(HttpStatus.NO_CONTENT.value(), restult);
		} catch (Exception e) {
		}
	}

	@Test
	public void testUpdateStudentPhone() throws Exception {

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch("/v1/students/updatePhone")
				.with(user(TEST_USER_ID)).with(csrf()).param("id", "1").param("phone", "+91-0123456789")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent()).andReturn();
		int restult = result.getResponse().getStatus();
		assertNotNull(restult);
		assertEquals(HttpStatus.NO_CONTENT.value(), restult);
	}

	@Test
	public void testGetStudentById() throws Exception {
		assertThrows(org.springframework.web.util.NestedServletException.class, () -> {
			MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/v1/students/student")
					.with(user(TEST_USER_ID)).with(csrf()).param("id", "1").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()).andReturn();
			int restult = result.getResponse().getStatus();
			assertNotNull(restult);
			assertEquals(HttpStatus.NOT_FOUND.value(), restult);
		}, "Student Not Found!!");

	}

	@Test
	public void testGetStudents() throws Exception {

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get("/v1/students").with(user(TEST_USER_ID)).with(csrf())
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		int restult = result.getResponse().getStatus();

		Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
			throw new IllegalArgumentException("a message");
		});
		assertEquals("a message", exception.getMessage());
		assertNotNull(restult);
		assertEquals(HttpStatus.OK.value(), restult);
	}

	@Test
	public void testRemoveStudent() throws Exception {

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/v1/students/remove").with(user(TEST_USER_ID)).with(csrf())
						.param("id", "1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		int restult = result.getResponse().getStatus();
		assertNotNull(restult);
		assertEquals(HttpStatus.OK.value(), restult);
	}

}
