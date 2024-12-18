package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PayslipReportModel;

public interface EmpPayslipRepo {

	List<Object[]> isPaybillGenerated(Long billNumber, int monthName, int yearName, OrgUserMst messages);

	List<Object[]> getEmployeeData1(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			OrgUserMst messages);

	List<PayslipReportModel> getAllDataForinnernew(String savaarthid);

	List<Map<String, Object>> getempDetails(String schemeBillgroupId, Integer paybillYear, Integer paybillMonth,
			String savaarthid);

	List<Object[]> lstBillDesc(OrgUserMst messages);

}