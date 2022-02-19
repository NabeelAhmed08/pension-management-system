package com.pms.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.pms.auth.model.User;
import com.pms.auth.repository.UserRepo;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class JwtUserDetailsServiceTest {
	
	@Mock
	private UserRepo userDao;


	@InjectMocks
	private MyUserDetailsService service;
	
	
	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void loadUserByUserNameShouldThrowExceptionTest() {
		when(userDao.findByUsername("wrongUserName")).thenReturn(null);
		assertThatThrownBy(() -> service.loadUserByUsername("wrongUserName")) 
        .isInstanceOf(UsernameNotFoundException.class)
        .hasMessage("User not found with username: wrongUserName");
		verify(userDao, Mockito.times(1)).findByUsername("wrongUserName");
	}
	
	@Test
	void loadUserByUserNameShouldUserNameTest() {
		when(userDao.findByUsername("admin")).thenReturn(new User(1,"admin","pass"));
		assertThat(service.loadUserByUsername("admin")).isNotNull();
		verify(userDao, Mockito.times(1)).findByUsername("admin");
	}

}
