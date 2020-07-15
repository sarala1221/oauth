package com.module.student.security.jwt.model;

import lombok.Data;

//
public class JwtResponse {
	private final String jwttoken;
	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
	public String getJwttoken() {
		return jwttoken;
	}
	
	
}
