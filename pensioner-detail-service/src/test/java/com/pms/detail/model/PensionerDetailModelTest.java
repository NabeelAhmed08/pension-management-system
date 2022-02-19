package com.pms.detail.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;



class PensionerDetailModelTest {
	
	
	@Test
	void testNoArgs() {
		assertThat(new PensionerDetail()).isNotNull();
	}

	Bank bank = new Bank("SBI", "9029486523", "private");

	@Test
	void testAllArgs() {
		PensionerDetail pensionerDetail = new PensionerDetail(420559429029l, "Nabeel", LocalDate.of(1999, 12, 03),
				"BSDPS1495K", 29000, 1200, "self", bank);
		assertThat(pensionerDetail).isNotNull();
	}

	@Test
	void testPensionerDetailSetterMethod() {
		PensionerDetail pensionerDetail = new PensionerDetail();
		pensionerDetail.setAadharNumber(420559429029l);
		pensionerDetail.setName("Nabeel");
		pensionerDetail.setDateOfBirth(LocalDate.of(1999, 12, 03));
		pensionerDetail.setPan("BSDPS1495K");
		pensionerDetail.setSalaryEarned(29000);
		pensionerDetail.setAllowances(1200);
		pensionerDetail.setPensionType("self");
		pensionerDetail.setBank(bank);

		assertThat(pensionerDetail).isNotNull();
	}

}
