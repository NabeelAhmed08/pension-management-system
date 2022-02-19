package com.pms.detail.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import com.pms.detail.model.TokenValidationResponse;



@FeignClient(name = "Authorization-Microservice", url = "${url.app.auth}")
public interface AuthClient {

	
	@PostMapping("/authorize")
	public TokenValidationResponse authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
}
