package com.mahait.gov.in.service;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.model.PayslipReportModel;

public interface  PayslipReportService {

	List<Object[]> getEmployeeData(String schemeBillGroupId, int monname, int curryear);

	List<PayslipReportModel> getAllDataForinnernew(String savaarthid);

	List<Map<String, Object>> getempDetails(String schemeBillgroupId, int payBillYear, int payBillMonth,String savaarthid);

	List<Object[]> getEmployeeData(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			String sevaarthId);

	List<Object[]> getEmpDataBySevaarthid(Integer paybillMonth, Integer paybillYear, String savaarthid);

	List<Map<String, Object>> getempDetailsBySevaarthId(Integer paybillYear, Integer paybillMonth, String savaarthid);

	List<Object[]> getEmployeeData1(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			String userName);

	List<Object[]>  lstBillGroupUserWise(String userName);

	List<PayslipReportModel> fetchBillGorupPaySlip(int paybillYear, int paybillYear2, String ddoCode);

	List<Object[]> getEmpDataBySevaarthidBillGroup(Integer paybillMonth, Integer paybillYear, String savaarthid,
			String schemeBillgroupId);

	List<Map<String, Object>> getempDetailsBySevaarthIduserPayslip(Integer paybillYear, Integer paybillMonth,
			String savaarthid, String schemeBillgroupId);

	
   	
}
