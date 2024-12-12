package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;

public interface ViewConsolidatePayBillRepo {
	public List<Object[]> findAllConsolidatedPaybillList(int monthName,int yearName,String ddoCode);
	public List<Object[]> findAllConsolidatedPaybillListUsingFilter(int monthName,int yearName,String schemeCodeArr,int afterSaveId,String ddoName);
	public List<Object[]> findAllConsolidatedPaybillListWithoutFilter(int monthName,int yearName,int afterSaveId,String ddoName);
/*	public PaybillGenerationTrnEntity findAllConsolidatedPaybill(int consPaybillGenerationTrnId);*/
	
	//for delete consolidate paybill
	public ConsolidatePayBillTrnEntity updateConsolidateStatusById(int consPaybillGenerationTrnId);
	public void updateConsolidateStatus(ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity);
	public int updatePaybillStatus(int lstConsolidatedBillList);
	public Integer findConsolidatedPaybillNumber(int consPaybillGenerationTrnId);
	
}
