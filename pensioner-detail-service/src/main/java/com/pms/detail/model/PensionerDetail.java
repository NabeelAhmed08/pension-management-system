package com.pms.detail.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor

public class PensionerDetail {
	
	
	private long aadharNumber;
	
	private String name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	private String pan;
	
	
	private double salaryEarned;
	
	private double allowances;
	
	
	private String pensionType;

	private Bank bank;
		
}
