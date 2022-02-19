package com.pms.auth.config;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;

@SpringBootTest 
class JwtTokenUtilTest {


	UserDetails userDetails;
	
	@Autowired
	private JwtTokenUtil util;
	
	
	@Test
	void testGenerateTokenWithRandomUserGeneratesNull()
			throws ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException, Exception {
		UserDetails details = new org.springframework.security.core.userdetails.User("admin", "pass",
				new ArrayList<>());
		assertThat(util.generateToken(details)).isNotNull();
	}

	@Test
	void validateTokenTest() throws ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException, Exception {
		userDetails = new org.springframework.security.core.userdetails.User("admin", "admin", new ArrayList<>());
		String generateToken = util.generateToken(userDetails);
		System.out.println("57 Line ==============="+ userDetails + generateToken);	
		
		Boolean validateToken = util.validateToken(generateToken, userDetails);
		assertThat(validateToken).isTrue();
	}
}
