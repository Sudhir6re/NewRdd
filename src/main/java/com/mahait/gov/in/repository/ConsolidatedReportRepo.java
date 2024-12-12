package com.mahait.gov.in.repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.ConsolidatedReportModel;

public interface ConsolidatedReportRepo {
	public String getOffice(String ddoCode);
	public List getReportHeaderDetails(String bill_no) ;
	public List<ConsolidatedReportModel> findBillDescription(String ddoCode,int month,int year);
	public int getTotalDeduction(double billno);
	public List<Object[]> getAllDataForOuternew(String ddocode);
	public List<Map<String,Object>> getempDetails(String bill_no);
	public List<Object[]> getempDetails(int monthName, int yearName, String ddocode, String billnumber);
	public List<ConsolidatedReportModel> findAllSchemeBillGroupByDDOCode(String dDOCode, int roleid);
	public List getReportHeaderCOnsolidateDetails(String valueOf);
	public List<Object[]> findbillBillNumber(Long consolidatePayBillTrnId);
	public Date findbillCreateConsolidateDate(Long consolidatePayBillTrnId);
}
