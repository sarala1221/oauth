package com.module.student.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.module.student.entity.Course;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubjectDto {

	private long subjId;

	private String subjName;
	private long score;
	private List<Course> courses;

	public Long getSubjId() {
		return subjId;
	}

	public void setSubjId(Long subjId) {
		this.subjId = subjId;
	}

	public String getSubjName() {
		return subjName;
	}

	public void setSubjName(String subjName) {
		this.subjName = subjName;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
