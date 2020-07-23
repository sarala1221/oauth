package com.module.student.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class StudentDto {
	private long stuid;
	@NotBlank(message = "{first_name.not.empty}")
	private String first_name;
	@NotBlank(message = "{last_name.not.empty}")
	private String last_name;
	@NotBlank(message = "{email.not.empty}")
	@Email(message = "{email.not.valid}")
	private String email;
	@Pattern(regexp = "(\\+91-|0)[0-9]{10}", message = "{phone.not.invalid}")
	@NotEmpty(message = "{phone.not.empty}")
	private String phone;
	private CourseDto course;
	private List<SubjectDto> subjects;
	private Date doj;
	private List<AddressDto> address;
	public long getStuid() {
		return stuid;
	}
	public void setStuid(long stuid) {
		this.stuid = stuid;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public CourseDto getCourse() {
		return course;
	}
	public void setCourse(CourseDto course) {
		this.course = course;
	}
	public List<SubjectDto> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public List<AddressDto> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDto> address) {
		this.address = address;
	}

}
