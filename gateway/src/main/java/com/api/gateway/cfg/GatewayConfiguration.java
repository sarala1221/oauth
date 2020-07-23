package com.api.gateway.cfg;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.api.gateway.exception.FeignClientExceptionErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class GatewayConfiguration {

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@ConditionalOnMissingBean(value = ErrorDecoder.class)
	public FeignClientExceptionErrorDecoder commonFeignErrorDecoder() {
		return new FeignClientExceptionErrorDecoder();
	}

}
