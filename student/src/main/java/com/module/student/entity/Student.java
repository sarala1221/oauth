package com.module.student.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "TBL_STUDENTS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Student {
	@Id
	@GeneratedValue
	private long stuid;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Course course = new Course();
	private Date doj;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Address> address = new ArrayList<Address>();
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
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}

}
