package com.pms.process.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;




class ModelTests {



	@Test
	void testSetterMethod() {
		PensionerDetail pensionerDetail = new PensionerDetail();
		Bank bank = new Bank();
		bank.setAccountNumber("4059586623");
		bank.setName("SBI");
		bank.setBankType("private");
		pensionerDetail.setAadharNumber(12050423020l);
		pensionerDetail.setName("Nabeel");
		pensionerDetail.setDateOfBirth(LocalDate.of(1999, 05, 24));
		pensionerDetail.setPan("DSDPB0495B");
		pensionerDetail.setSalaryEarned(79000);
		pensionerDetail.setAllowances(6200);
		pensionerDetail.setPensionType("self");
		pensionerDetail.setBank(bank);

		assertThat(pensionerDetail).isNotNull();
	}
	
	
	@Test
	void testNoArgs() {
		assertThat(new PensionerDetail()).isNotNull();
	}

	@Test
	void testProcessPension() {
		ProcessPensionInput processPensionInput = new ProcessPensionInput();
		processPensionInput.setAadharNumber(12050423020l);
		assertThat(processPensionInput).isNotNull();
	}

	@Test
	void testProcessPensionInputNoArgs() {
		assertThat(new ProcessPensionInput()).isNotNull();
	}

	@Test
	void testPensiondetail() {
		PensionDetail processDetail = new PensionDetail();
		processDetail.setPensionAmount(34567);
		processDetail.setBankServiceCharge(500);
		assertThat(processDetail).isNotNull();
	}

	@Test
	void testProcessPensionResponceNoArgs() {
		assertThat(new ProcessPensionInput()).isNotNull();
	}


}
