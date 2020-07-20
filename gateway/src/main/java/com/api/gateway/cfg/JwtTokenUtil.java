package com.api.gateway.cfg;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	public static final long JWT_TOKEN_VALIDITY = 60;
	private final static String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n" + 
			"MIICXAIBAAKBgHqvhqTSG/k3VSZAyTOx6BmVpa9GlXEYg+11MNNG8YZJi08AhGco\r\n" + 
			"f9HS2zXV9oT13CYVtjhlH7Xf8kAledjaJiBwL4wpDiqRwRgtKk3a5GtGkudqbA2t\r\n" + 
			"NJjp43B6SEWL6sOjmW7k73DLvfxMGUaNaWtaucrBEhxshaRt3TECkIDJAgMBAAEC\r\n" + 
			"gYBmQSvo7hJQqjS3OGI6SvrXcCnzJ9Jgu/0hin6SadvegCezRgwU6uV2HdeFTHs2\r\n" + 
			"oLDHsWr6IBbJAQmpO1MOUexaDV6PXzHtGVBsY1e+Movm1jm070v/IfFQtzgAVeV9\r\n" + 
			"TPWgQzmCnmWurHrV1dpXZJ2/8FruNEwkMgW/P3/E7nLd7QJBAMhpt/cNlKiLZvuc\r\n" + 
			"vhpAQGl3nJpbDVMZDXDqwbYwYK06ijE8hkDvr9h92vLk+gCpqlciCWeSrXBYXfnO\r\n" + 
			"flRKD38CQQCctsuWSUiqBYdVVJVJPkI4shb8z3TOgO7jbP+hWhQI0ADws9tJ6kY9\r\n" + 
			"BC1JcABZc+gvSrteS5nxO9cRm/AqBxO3AkEAnknxhO1zBpPj6MLp2u34cdSJGdjk\r\n" + 
			"c0eMOC0ShoU7NlbQIwc8ujkVWBY/Qizb0H4xDdTSPL26wsrono8bdBNynQJBAIjE\r\n" + 
			"z7D9jDk2UgIaq58cgtbQNle1BpAi3loFiqPa5Zk7T1bC4SMFHv+pYYyx/twS2BRN\r\n" + 
			"+HA3MsbiHrTzjwpe2skCQAf+3pNyPrdO+VOCx9F48XY2/8K2AWvGydUR1f6BMMRC\r\n" + 
			"IaI9FWTrJZsmYZGRCfK75Vq6cmfBFASwutW9h4J7SQA=\r\n" + 
			"-----END RSA PRIVATE KEY-----";
	private final static String publicKey = "-----BEGIN PUBLIC KEY-----\r\n" + 
			"MIGeMA0GCSqGSIb3DQEBAQUAA4GMADCBiAKBgHqvhqTSG/k3VSZAyTOx6BmVpa9G\r\n" + 
			"lXEYg+11MNNG8YZJi08AhGcof9HS2zXV9oT13CYVtjhlH7Xf8kAledjaJiBwL4wp\r\n" + 
			"DiqRwRgtKk3a5GtGkudqbA2tNJjp43B6SEWL6sOjmW7k73DLvfxMGUaNaWtaucrB\r\n" + 
			"EhxshaRt3TECkIDJAgMBAAE=\r\n" + 
			"-----END PUBLIC KEY-----";

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.RS512, privateKey).compact();
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
