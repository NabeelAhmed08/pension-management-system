package com.pms.auth.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.pms.auth.model.User;



@SpringBootTest
class UserDaoTest {

	@MockBean
	private UserRepo dao;
	
	@Test
	void testUserDaoFindByName() {
		User user = new User(1,"admin", "password");
		when(dao.findByUsername("admin")).thenReturn(user);
		assertThat(dao.findByUsername("admin").equals(user));
	}

}
