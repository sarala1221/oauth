package com.api.gateway.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "student-service")
public interface StudentFeignClient {

	@GetMapping
	public ResponseEntity<List<?>> getStudents();

}
