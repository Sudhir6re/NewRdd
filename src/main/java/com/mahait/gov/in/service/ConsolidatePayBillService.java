package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.ConsolidatePayBillModel;

public interface ConsolidatePayBillService {
	
	

	public List<ConsolidatePayBillModel> fetchDDOLst(String ddoCode);

	public List<ConsolidatePayBillModel> searchConsolidatedPaybill(ConsolidatePayBillModel consolidatePayBillModel,OrgUserMst messages);

	public int saveConsolidatePayBill(ConsolidatePayBillModel consolidatePayBillModel, OrgUserMst messages);

	public PaybillGenerationTrnEntity rejectConsolidatePaybill(String ddoCode, Long billNo);

	public List<ConsolidatePayBillModel> fetchDDOLstForConsolidateApproval(String ddoCode);

	public int approveConsolidateBill(Long consolidateId);

}
