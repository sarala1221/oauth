package com.module.student.cfg;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@org.springframework.context.annotation.Configuration
@Profile(value = { "dev", "stage" })
@ConfigurationProperties("spring.security.user")
public class Configuration implements WebMvcConfigurer {

	@Bean
	public RequestMappingHandlerAdapter annotationMethodHandlerAdapter() {
		final RequestMappingHandlerAdapter annotationMethodHandlerAdapter = new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
		httpMessageConverters.add(new MappingJackson2HttpMessageConverter());
		annotationMethodHandlerAdapter.setMessageConverters(httpMessageConverters);
		return annotationMethodHandlerAdapter;
	}

	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource);
		return validatorFactoryBean;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		return messageSource;
	}
}
