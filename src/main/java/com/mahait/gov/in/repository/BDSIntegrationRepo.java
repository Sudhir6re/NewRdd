package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.BeamsIntegrationEntity;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;

public interface BDSIntegrationRepo {
	public List<Object[]> getBillDtls(Long consolidatePaybillTrnId);

	public void updateDeptStatus(BeamsIntegrationEntity beamsIntegrationEntity);
	///public List<String> getData(BeamsIntegrationEntity beamsIntegrationEntity,List<Object[]> lst);

	public List<String> getData(BeamsIntegrationEntity beamsIntegrationEntity, List<String> lst);
	public PaybillGenerationTrnEntity getConsBillDtls(Long consolidatePaybillTrnId);

	void updategetBillDtls(PaybillGenerationTrnEntity objPaybillGenerationTrnEntity);

	public List<Object[]> getheadpf(Long consolidatePaybillTrnId);

	public boolean isEkuber(String ddoCode);

	public ConsolidatePayBillTrnEntity getConsolidatedPaybillDtls(Long consolidatePaybillTrnId);

	public void updategetBillDtls(ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity);

	public String getBEAMSDdoCode(String ddocode);
}

