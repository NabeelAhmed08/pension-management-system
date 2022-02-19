package com.pms.process.service;

import com.pms.process.exception.AadharNumberNotFound;
import com.pms.process.exception.AuthorizationException;
import com.pms.process.exception.PensionerDetailException;
import com.pms.process.model.PensionDetail;
import com.pms.process.model.ProcessPensionInput;

public interface ProcessPensionService {
	
	public PensionDetail processPension(String requestTokenHeader,ProcessPensionInput processPensionInput) throws PensionerDetailException, AuthorizationException, AadharNumberNotFound;
	
}
