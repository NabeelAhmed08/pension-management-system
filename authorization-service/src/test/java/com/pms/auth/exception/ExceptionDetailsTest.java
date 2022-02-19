package com.pms.auth.exception;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ExceptionDetailsTest {

	private ExceptionDetails details = new ExceptionDetails(LocalDateTime.now(),"message");
	
	@Test
	void testMessageSetter() {
		details.setMessage("new message");
		assertThat(details.getMessage().equals("new message"));
		
	}
	
	@Test
	void testTimeStampSetter() {
		
		LocalDateTime date = LocalDateTime.now();
		details.setTimeStamp(date);
		assertThat(details.getTimeStamp().equals(date));
		
	}
	
	
}
