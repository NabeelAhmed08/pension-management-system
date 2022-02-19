package com.pms.detail.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pms.detail.model.Bank;
import com.pms.detail.model.PensionerDetail;
import com.pms.detail.model.TokenValidationResponse;
import com.pms.detail.repository.AuthClient;
import com.pms.detail.service.PensionerDetailServiceImpl;
import com.pms.detail.util.CsvToListConverter;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class PensionerDetailControllerTest {

	@MockBean
	private AuthClient authorisingClient;

	@MockBean
	private PensionerDetailServiceImpl pensionerDetailServiceImpl;

	@MockBean
	private CsvToListConverter pensionerDetailRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testClientNotNull() {
		assertThat(authorisingClient).isNotNull();
	}

	@Test
	void testMockMvcNotNull() {
		assertThat(mockMvc).isNotNull();
	}

	String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

	@Test
	void testGetResponse() throws Exception {
		when(authorisingClient.authorizeTheRequest("Bearer @token@token"))
				.thenReturn(new TokenValidationResponse(true));
		mockMvc.perform(
				get("/api/v1/PensionerDetailByAadhaar/420559429029").header("Authorization", "Bearer @token@token"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetResponseWrongAuthorization() throws Exception {
		when(authorisingClient.authorizeTheRequest("Bearer @token@token"))
				.thenReturn(new TokenValidationResponse(false));
		mockMvc.perform(
				get("/api/v1/PensionerDetailByAadhaar/420559429029").header("Authorization", "Bearer @token@token"))
				.andExpect(status().isForbidden());
	}

	@Test
	void testGetPensionDetailsValidAadharCard() throws Exception {

		when(authorisingClient.authorizeTheRequest("Bearer @token@token"))
				.thenReturn(new TokenValidationResponse(true));
		Bank bank = new Bank("SBI", "private", "9029486523");
		PensionerDetail pensionerDetail = new PensionerDetail(420559429029l, "Nabeel", LocalDate.of(1999, 12, 03),
				"BSDPS1495K", 29000, 1200, "self", bank);
		when(pensionerDetailServiceImpl.getPensionerDetailByAadharCard(420559429029l)).thenReturn(pensionerDetail);

		List<PensionerDetail> list = new ArrayList<PensionerDetail>();
		list.add(pensionerDetail);
		Mockito.when(pensionerDetailRepository.processTxt()).thenReturn(list);
		mockMvc.perform(get("/api/v1/PensionerDetailByAadhaar/420559429029").contentType("application/json")
				.header("Authorization", "Bearer @token@token")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.aadharNumber").value(420559429029l))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Nabeel"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.dateOfBirth").value("1999-12-03"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.pan").value("BSDPS1495K"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.salaryEarned").value(29000))
				.andExpect(MockMvcResultMatchers.jsonPath("$.allowances").value(1200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.pensionType").value("self"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bank.name").value("SBI"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bank.bankType").value("private"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.bank.accountNumber").value("9029486523"));

	}

	@Test
	void testGetAllPensioner() throws Exception {
		when(authorisingClient.authorizeTheRequest("Bearer @token@token"))
				.thenReturn(new TokenValidationResponse(true));
		mockMvc.perform(get("/api/v1/getAllPensioner").header("Authorization", "Bearer @token@token"))
				.andExpect(status().isOk());
	}

	@Test
	void testGetAllPensionerWrongAuthorization() throws Exception {
		when(authorisingClient.authorizeTheRequest("Bearer @token@token"))
				.thenReturn(new TokenValidationResponse(false));
		mockMvc.perform(get("/api/v1/getAllPensioner")
				.header("Authorization", "Bearer @token@token"))
				.andExpect(status().isForbidden());
	}

	@Test
	void testGetAllPensionerBadRequest() throws Exception {

		mockMvc.perform(get("/api/v1/getAllPensioner"))
		.andExpect(status().isBadRequest());
	}

}
