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
@Table(name = "TBL_ADDRESS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class Address {

	@Id
	@GeneratedValue
	private long adId;

	private String street;
	private long pin;
	private String landmark;
	private String city;
	private String state;
	private String Country;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Student> students = new ArrayList<Student>();
	public long getAdId() {
		return adId;
	}
	public void setAdId(long adId) {
		this.adId = adId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public long getPin() {
		return pin;
	}
	public void setPin(long pin) {
		this.pin = pin;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

}
