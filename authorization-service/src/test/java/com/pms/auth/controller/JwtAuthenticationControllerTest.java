package com.pms.auth.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import com.pms.auth.config.JwtTokenUtil;
import com.pms.auth.model.User;
import com.pms.auth.service.MyUserDetailsService;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class JwtAuthenticationControllerTest {


	
	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	private MyUserDetailsService userDetailsService;
	
	@InjectMocks
	private JwtAuthenticationController controller;

	
	@Test
	 void testAuthorization() throws Exception {
		when(jwtTokenUtil.getUsernameFromToken("Bearer token")).thenReturn(null);
		assertThat(controller.authorizeTheRequest("Bearer token").getBody().isValid()).isFalse();
	}
	
	@Test
	 void testAuthorizationInvalid() throws Exception {
		User user = new User(1,"admin", "pass");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		assertThat(controller.authorizeTheRequest("token").getBody().isValid()).isFalse();

	}
	
	@Test
	 void testAuthorizationNullUser() throws Exception {

		User user = new User(1,"admin", "pass");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		
		assertThat(controller.authorizeTheRequest("WrongToken").getBody().isValid()).isFalse();

	}
}
