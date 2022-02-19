package com.pms.auth.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class JwtResponseTest {
	
	private JwtResponse jwtResArg;

	@BeforeEach
	void setUp() throws Exception {
		
		jwtResArg = new JwtResponse("token","validity");  
		
	}

	@Test
	void test() {
		assertThat(jwtResArg.getToken().equals("token"));
	}

}
