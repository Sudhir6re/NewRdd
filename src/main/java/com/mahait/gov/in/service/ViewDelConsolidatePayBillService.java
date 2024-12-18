package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;
import com.mahait.gov.in.model.LstConsolidatedPayBillModel;

public interface ViewDelConsolidatePayBillService {
	
	public List<LstConsolidatedPayBillModel> viewDelconsolidatePayBill(int monthName,int yearName,String ddoCode);
	public List<Object[]> findAllConsolidatedPaybillListUsingFilter(int monthName,int yearName,String schemeCodeArr,int afterSaveId,String ddoName);
	public List<Object[]> findAllConsolidatedPaybillListWithoutFilter(int monthName,int yearName,int afterSaveId,String ddoName);
	public ConsolidatePayBillTrnEntity findDeleteBillById(int consPaybillGenerationTrnId);	//added for delete consolidate bill
}
