package com.pms.auth.controller;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.auth.config.JwtTokenUtil;
import com.pms.auth.model.JwtRequest;
import com.pms.auth.model.User;
import com.pms.auth.service.MyUserDetailsService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationControllerEndPointsTest {


	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MyUserDetailsService userDetailsService;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;

	@MockBean
	private AuthenticationManager authenticationManager;

	@Test
	void testBadRequestGenerateToken() throws Exception {
		this.mockMvc.perform(post("/api/v1/authenticate")).andExpect(status().isBadRequest());
	}

	@Test
	void testAuthorizedGenerateToken() throws Exception {

		User user = new User(1, "admin", "admin");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), new ArrayList<>());
		when(jwtTokenUtil.generateToken(details)).thenReturn("Bearer @token@token");
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(new JwtRequest("admin", "admin"));
		this.mockMvc.perform(post("/api/v1/authenticate").contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isUnauthorized());
	}

	@Test
	void testBadRequest() throws Exception {
		this.mockMvc.perform(post("/api/v1/authenticate")).andExpect(status().isBadRequest());
	}

	@Test
	void testRandomUrl() throws Exception {
		this.mockMvc.perform(get("/api/v1/other/url")).andExpect(status().isNotFound());
	}

	@Test
	void textExistingUserAuthenticate() throws Exception {
		User user = new User(1, "admin", "pass");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), new ArrayList<>());
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				"admin", "admin");
		when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("admin", "admin")))
				.thenReturn(usernamePasswordAuthenticationToken);
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		when(jwtTokenUtil.generateToken(details)).thenReturn("token");
		ObjectMapper mapper = new ObjectMapper();
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authenticate").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(new JwtRequest("admin", "pass")))).andExpect(status().isUnauthorized());

	}

	@Test
	void textExistingUserAuthorize() throws Exception {
		User user = new User(1, "admin", "pass");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authorize").header("Authorization", "Bearer token"))
				.andExpect(status().isOk());

	}

	@Test
	void textNullTokenAuthorize() throws Exception {
		User user = new User(1, "admin", "pass");
		UserDetails details = new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), new ArrayList<>());
		when(userDetailsService.loadUserByUsername("admin")).thenReturn(details);
		when(jwtTokenUtil.getUsernameFromToken("token")).thenReturn("admin");
		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/authorize").header("Authorization", "")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}


}
