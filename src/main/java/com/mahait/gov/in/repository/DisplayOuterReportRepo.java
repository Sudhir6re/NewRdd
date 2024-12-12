package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.DisplayOuterReportModel;

public interface DisplayOuterReportRepo {
	public String getOffice(String locId);
	public List<Object[]> getReportHeaderDetails(String bill_no) ;
	public List<DisplayOuterReportModel> findBillDescription(String ddoCode,int month,int year);
	public int getTotalDeduction(double billno);
	/*public List<DisplayOuterReportModel> getAllDataForOuternew(String ddocode);*/
	public List<Object[]> getAllDataForOuternew(String ddocode,Long billNumber);
	public List<Map<String,Object>> getempDetails(String bill_no);
	public List<Object[]> getempDetails(int monthName, int yearName, String ddocode, String billnumber);
	public List<DisplayOuterReportModel> findAllSchemeBillGroupByDDOCode(String dDOCode, int roleid);
	public List<Object[]> getcardecode(String strddo);
	public Double gettotalNetAmount(String billNumber);
}
