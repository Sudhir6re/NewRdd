package com.mahait.gov.in.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.ConsolidatedReportModel;

public interface ConsolidatedReportService {
	public String getOffice(String locId);
	public List getReportHeaderDetails(String bill_no) ;
	public List<ConsolidatedReportModel> findBillDescription(String ddoCode,int month,int year);
	public int getTotalDeduction(double billno);
	public List<ConsolidatedReportModel> getAllDataForOuternew(String ddocode);
	public List<Map<String,Object>> getempDetails(String bill_no);
	public List<Object[]> findbillcrateornot(int monthName, int yearName, String ddocode, String billnumber);
	public List<ConsolidatedReportModel> findAllSchemeBillGroupByDDOCode(String DDOCode,int roleid);
	public List getReportHeaderCOnsolidateDetails(String valueOf);
	public List<Object[]> findbillBillNumber(Long consolidatePayBillTrnId);
	public Date findbillCreateConsolidateDate(Long consolidatePayBillTrnId);
}
