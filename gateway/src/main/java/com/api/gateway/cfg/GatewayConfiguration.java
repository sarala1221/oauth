package com.api.gateway.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.api.gateway.filters.SimpleFilter;

@Configuration
@EnableZuulProxy
@EnableDiscoveryClient
@EnableEurekaClient
@EnableResourceServer
public class GatewayConfiguration extends ResourceServerConfigurerAdapter {

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

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtTokenStore tokenStore() {
		JwtTokenStore jwtTokenStore = new JwtTokenStore(tokenEnhancer());
		return jwtTokenStore;
	}

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/v1/students").hasAnyRole("USER", "MODERATOR")
				.antMatchers("/api/v1/students/**").hasRole("ADMIN").anyRequest().authenticated();
	}

}
