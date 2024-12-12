package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.PayslipReportModel;

public interface PayslipReportRepo {


	public List<Object[]> getEmployeeData(String schemeBillGroupId, int monname, int curryear);

	public List<PayslipReportModel> getAllDataForinnernew(String savaarthid);

	public List<Map<String, Object>> getempDetails(String schemeBillgroupId, int payBillYear, int payBillMonth,String savaarthid);

	public List<Object[]> getEmployeeData(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			String sevaarthId);

	public List<Object[]> getEmpDataBySevaarthid(Integer paybillMonth, Integer paybillYear, String savaarthid);

	public List<Map<String, Object>> getempDetailsBySevaarthId(Integer paybillYear, Integer paybillYear2,
			String savaarthid);

	public List<Object[]> getEmployeeData1(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			String userName);

	public List<Object[]> lstBillGroupUserWise(String userName);

	public List<Object[]> fetchBillGorupPaySlip(int paybillmonth, int paybillYear, String ddoCode);

	public List<Object[]> getEmpDataBySevaarthidBillGroup(Integer paybillMonth, Integer paybillYear, String savaarthid,
			String schemeBillgroupId);

	public List<Map<String, Object>> getempDetailsBySevaarthIduserPayslip(Integer paybillYear, Integer paybillMonth,
			String savaarthid, String schemeBillgroupId);
	
	
	
}
