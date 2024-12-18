package com.mahait.gov.in.service;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.DisplayOuterReportModel;
import com.mahait.gov.in.model.RegularReportModel;

public interface DisplayOuterReportService {
	public String getOffice(String locId);
	public List<RegularReportModel> getReportHeaderDetails(String bill_no) ;
	public List<DisplayOuterReportModel> findBillDescription(String ddoCode,int month,int year);
	public int getTotalDeduction(double billno);
	public List<DisplayOuterReportModel> getAllDataForOuternew(String ddocode,Long billNumber);
	public List<Map<String,Object>> getempDetails(String bill_no);
	public List<Object[]> findbillcrateornot(int monthName, int yearName, String ddocode, String billnumber);
	public List<DisplayOuterReportModel> findAllSchemeBillGroupByDDOCode(String DDOCode,int roleid);
	public List<Object[]> getcardecode(String strddo);
	public Double gettotalNetAmount(String valueOf);
}
