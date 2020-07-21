package com.api.gateway.domain;

//
public class JwtResponse {
	private final String jwttoken;

	private String issuer;
	private String scopes;
	private String expirationtime;
	private String refresfInterval;

	public JwtResponse(String jwttoken/*
										 * , String issuer, String scopes, String expirationtime, String refresfInterval
										 */) {
		this.jwttoken = jwttoken;
	}

	public String getJwttoken() {
		return jwttoken;
	}

	public String getIssuer() {
		return issuer;
	}

	public String getScopes() {
		return scopes;
	}

	public String getExpirationtime() {
		return expirationtime;
	}

	public String getRefresfInterval() {
		return refresfInterval;
	}

}
