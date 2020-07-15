package com.module.student.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "TBL_COURSE")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Course {

	@Id
	@GeneratedValue
	private long courseId;
	private String cName;
	private long duration;
	@OneToMany(fetch = FetchType.LAZY)
	private List<Student> students;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Subject> subjects = new ArrayList<Subject>();
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

}
