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

@Entity
@Table(name = "TBL_SUBJECT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Subject {

	@Id
	@GeneratedValue
	private long subjId;

	private String subjName;
	private long score;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Course> courses = new ArrayList<Course>();
	public long getSubjId() {
		return subjId;
	}
	public void setSubjId(long subjId) {
		this.subjId = subjId;
	}
	public String getSubjName() {
		return subjName;
	}
	public void setSubjName(String subjName) {
		this.subjName = subjName;
	}
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
