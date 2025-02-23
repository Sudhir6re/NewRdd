package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.ConsolidatePayBillTrnEntity;

public interface ViewConsolidatePayBillRepo {
	public List<Object[]> findAllConsolidatedPaybillList(int monthName,int yearName,String ddoCode);
	public List<Object[]> findAllConsolidatedPaybillListUsingFilter(int monthName,int yearName,String schemeCodeArr,int afterSaveId,String ddoName);
	public List<Object[]> findAllConsolidatedPaybillListWithoutFilter(int monthName,int yearName,int afterSaveId,String ddoName);
/*	public PaybillGenerationTrnEntity findAllConsolidatedPaybill(int consPaybillGenerationTrnId);*/
	
	//for delete consolidate paybill
	public ConsolidatePayBillTrnEntity updateConsolidateStatusById(Long consPaybillGenerationTrnId);
	public void updateConsolidateStatus(ConsolidatePayBillTrnEntity consolidatePayBillTrnEntity);
	public int updatePaybillStatus(Long lstConsolidatedBillList);
	public Integer findConsolidatedPaybillNumber(Long consPaybillGenerationTrnId);
	public int deletePaybillStatus(Long consPaybillGenerationTrnId);
	
}
