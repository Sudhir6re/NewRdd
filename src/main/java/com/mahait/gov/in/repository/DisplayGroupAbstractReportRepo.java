package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.DisplayGroupAbstractReportModel;

public interface DisplayGroupAbstractReportRepo {
	public List getReportHeaderDetails(String bill_no) ;
	public List<DisplayGroupAbstractReportModel> findBillDescription(String ddoCode,int month,int year);
	public int getTotalDeductionGroupAbstract(double billno);
	public List<Object[]> getAllDataForAbstractnew(String ddocode,Long billNumber);
	public List<Map<String,Object>> getempDetailsGroupAbstract(String bill_no);
	public List<Map<String,Object>> getempDetailsGroupAbstractA(String bill_no);
	public List<Map<String,Object>> getempDetailsGroupAbstractB(String bill_no);
	public List<Map<String,Object>> getempDetailsGroupAbstractC(String bill_no);
	public List<Map<String,Object>> getempDetailsGroupAbstractD(String bill_no);
	public List<Map<String,Object>> getempDetailsGroupAbstractE(String bill_no);
	
	
}
