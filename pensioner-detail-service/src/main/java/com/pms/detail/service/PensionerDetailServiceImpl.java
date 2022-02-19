package com.pms.detail.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pms.detail.exception.AadharNumberNotFound;
import com.pms.detail.model.PensionerDetail;
import com.pms.detail.util.CsvToListConverter;

@Service
public class PensionerDetailServiceImpl implements PensionerDetailService {

	
	@Autowired
	CsvToListConverter pensionerList;
	
	@Override
	public PensionerDetail getPensionerDetailByAadharCard(long aadharNumber) throws AadharNumberNotFound, NumberFormatException, IOException
	{
	  		
		return pensionerList.processTxt().stream()
				 .filter(p -> p.getAadharNumber() == aadharNumber )
				 .findAny().orElseThrow(()-> new AadharNumberNotFound("Aadhar Card Number is not Valid. Please check it and try again"));
	}
	
	
	public List<PensionerDetail> getAllPensioner() throws NumberFormatException, IOException
	{
		return pensionerList.processTxt();
	}


	
	
}
