package com.module.student.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseDto {

	private long courseId;
	private String cName;
	private long duration;
	private List<StudentDto> students;
	private List<SubjectDto> subjects;

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

	public List<StudentDto> getStudents() {
		return students;
	}

	public void setStudents(List<StudentDto> students) {
		this.students = students;
	}

	public List<SubjectDto> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<SubjectDto> subjects) {
		this.subjects = subjects;
	}

}
