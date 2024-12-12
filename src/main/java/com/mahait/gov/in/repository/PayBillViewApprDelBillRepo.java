package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PayBillViewApprDelBillModel;

public interface PayBillViewApprDelBillRepo {

	public List<Object[]> findAlllstGenerateBillDetailsAgainstDDO(String ddoCode,int month);
	
	public List<String> findDdoNameAgainstGivenDdo(String ddoCode,int roleId);
	
	public List<Object[]> findAlllstGenerateBillDetailsAgainstDDO2(List<String> ddoCode);

	public List<Object[]> findAllPayBillsForConsolidatedAgainstDDO(String ddoCode,int roleId);
	
	public List<Object[]> findPayBillByBillNumber(String billNumber, int paybillMonth, int paybillYear ,int roleId);

	public List<Object[]> findPayBillByMonthYear(int paybillMonth, int paybillYear, String ddoCode,int roleId);

	public List<Object[]> findPayBillByBillNumber(PayBillViewApprDelBillModel payBillViewApprDelBillModel,
			OrgUserMst messages);

	///public Integer saveDCPSNpsDeductions(DcpsCalculationEntity dcpsCalc);
	
	

}
