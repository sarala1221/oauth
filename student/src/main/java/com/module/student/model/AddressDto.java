package com.module.student.model;

import java.util.List;

import lombok.Data;


public class AddressDto {

	private long adId;

	private String street;
	private long pin;
	private String landmark;
	private String city;
	private String state;
	private String Country;
	private List<StudentDto> students;
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
	public List<StudentDto> getStudents() {
		return students;
	}
	public void setStudents(List<StudentDto> students) {
		this.students = students;
	}

}
