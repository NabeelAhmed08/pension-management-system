package com.pms.detail.service;

import java.io.IOException;

import com.pms.detail.exception.AadharNumberNotFound;
import com.pms.detail.model.PensionerDetail;

public interface PensionerDetailService {
	
	public PensionerDetail getPensionerDetailByAadharCard(long aadharNumber) throws AadharNumberNotFound, NumberFormatException, IOException;

}
