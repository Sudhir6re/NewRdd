package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.ConsolidatePayBillTrnMpgEntity;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.ConsolidatePayBillModel;

public interface ConsolidatePayBillRepo {
	public Long saveConsolidatePayBill(ConsolidatePayBillTrnEntity objEntity);
	public Long saveConsolidatePayBillTrnMpg(ConsolidatePayBillTrnMpgEntity objEntity);
	List<Object[]> addConsComponents(String ddoCode, List<Integer> payBillGenerationTransId);
	public List<Object[]> fetchDDOLst(String ddoCode);
	public List<Object[]> fetchConsolidateDetails(String ddoCode, Integer monthId, Integer yearId, String schemeCode);
	public List<PaybillGenerationTrnEntity> getPaybillDtls(Integer monthName, Integer yearName, String ddoCode);
	public List<Object[]> fetchbilldts(Long paybillGenerationTrnId);
	Long getConsolidateTrnId();
	public Serializable saveDtlsPaybillTrn(PaybillGenerationTrnEntity paybillGenerationTrnEntity);
	public PaybillGenerationTrnEntity findPaybillDtls(String ddoCode, Long billNo);
	public void updaterejectConsolidateStatus(PaybillGenerationTrnEntity paybillGenerationTrnEntity);
	public List<Object[]> fetchDDOLstForConsolidateApproval(String ddoCode);
	public Serializable updateConsolidateapproveStatus(Long consolidateId);

}
