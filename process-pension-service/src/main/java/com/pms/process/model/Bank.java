package com.pms.process.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

	private String name;	
	private String bankType;
	private String accountNumber;
	
}
