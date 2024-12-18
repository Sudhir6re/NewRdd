package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.LstGenerateBillDetailsModel;
import com.mahait.gov.in.model.PayBillViewApprDelBillModel;

public interface PayBillViewApprDelBillService {

	public List<LstGenerateBillDetailsModel> findAlllstGenerateBillDetailsAgainstDDO(String ddoCode,int roleId,int month);

	public List<LstGenerateBillDetailsModel> findAllPayBillsForConsolidatedAgainstDDO(String userName,int roleId);
	
	public List<LstGenerateBillDetailsModel> findPayBillByBillNumber(String billGroup, int paybillMonth, int paybillYear, int ddoCode);
	
	public  List<LstGenerateBillDetailsModel> findPayBillByMonthYear(int paybillMonth, int paybillYear, String ddoCode,int roleId);

	public List<LstGenerateBillDetailsModel> findPayBillByBillNumber(
			PayBillViewApprDelBillModel payBillViewApprDelBillModel, OrgUserMst messages);

}
