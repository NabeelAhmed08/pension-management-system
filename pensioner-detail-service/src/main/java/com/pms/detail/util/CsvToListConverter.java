package com.pms.detail.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import com.pms.detail.model.Bank;
import com.pms.detail.model.PensionerDetail;

@Component
public class CsvToListConverter {
	
	
	
	public List<PensionerDetail> processTxt() throws NumberFormatException, IOException {

		//FileReader fileReader = new FileReader("src/main/resources/PensionerDetail.csv");
		InputStream inputStream = getClass().getResourceAsStream("/PensionerDetail.csv");
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		List<PensionerDetail> pensionerDetails = new ArrayList<>();


		String line = null ;
		
		while((line = bufferedReader.readLine())!=null) {
			
			String[] data = line.split(",");
			
			PensionerDetail pensionerDetail = new PensionerDetail();
			pensionerDetail.setAadharNumber(Long.parseLong(data[0]));
			pensionerDetail.setName(data[1]);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			pensionerDetail.setDateOfBirth(LocalDate.parse((data[2]),formatter));
			
			pensionerDetail.setPan(data[3]);
			pensionerDetail.setSalaryEarned(Double.parseDouble(data[4]));
			pensionerDetail.setAllowances(Double.parseDouble(data[5]));
			pensionerDetail.setPensionType(data[6]);
			
			Bank bank = new Bank();
			bank.setName(data[7]);
			bank.setAccountNumber(data[8]);
			bank.setBankType(data[9]);	
			pensionerDetail.setBank(bank);
			pensionerDetails.add(pensionerDetail);
			
			
		}
		bufferedReader.close();
		return pensionerDetails;
	}
	
	
	

}
