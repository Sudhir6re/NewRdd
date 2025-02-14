package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.BeamsIntegrationEntity;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;

public interface BDSIntegrationService {
	public List<Object[]> getPaybillDtls(Long consolidatePaybillTrnId);
	public PaybillGenerationTrnEntity getConsPaybillDtls(Long consolidatePaybillTrnId, String statusCode);
	public List<String> getData(BeamsIntegrationEntity beamsIntegrationEntity,List<String> lst);
	public List<Object[]> getheadcodePF(Long consolidatePaybillTrnId);
	
	public boolean isEkuber(String ddoCode);
	public ConsolidatePayBillTrnEntity getConsolidatedPaybillDtls(Long consolidatePaybillTrnId, String statusCode);
	public String getBEAMSDdoCode(String ddocode);
	
	
}
