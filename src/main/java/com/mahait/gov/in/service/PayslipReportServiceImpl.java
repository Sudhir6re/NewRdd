
package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.PayslipReportModel;
import com.mahait.gov.in.repository.PayslipReportRepo;

@Service
@Transactional
public class PayslipReportServiceImpl implements PayslipReportService {

	
	@Autowired
	PayslipReportRepo payslipReportRepo;

	@Override
	public List<Object[]> getEmployeeData(String schemeBillGroupId, int monname, int curryear) {
		return payslipReportRepo.getEmployeeData(schemeBillGroupId,monname,curryear);
	}

	@Override
	public List<PayslipReportModel> getAllDataForinnernew(String savaarthid) {
		return payslipReportRepo.getAllDataForinnernew(savaarthid);
	}

	@Override
	public List<Map<String, Object>> getempDetails(String schemeBillgroupId, int payBillYear, int payBillMonth,String savaarthid) {
		return payslipReportRepo.getempDetails(schemeBillgroupId,payBillYear,payBillMonth,savaarthid);
	}

	@Override
	public List<Object[]> getEmployeeData(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			String sevaarthId) {
		// TODO Auto-generated method stub
		return payslipReportRepo.getEmployeeData(schemeBillgroupId,paybillMonth,paybillYear,sevaarthId);
	}

	@Override
	public List<Object[]> getEmpDataBySevaarthid(Integer paybillMonth, Integer paybillYear, String savaarthid) {
		// TODO Auto-generated method stub
		return payslipReportRepo.getEmpDataBySevaarthid(paybillMonth,paybillYear,savaarthid);
	}

	@Override
	public List<Map<String, Object>> getempDetailsBySevaarthId(Integer paybillYear, Integer paybillMonth,
			String savaarthid) {
		// TODO Auto-generated method stub
		return payslipReportRepo.getempDetailsBySevaarthId(paybillYear,paybillMonth,savaarthid);
	}

	@Override
	public List<Object[]> getEmployeeData1(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			String userName) {
		// TODO Auto-generated method stub
		return payslipReportRepo.getEmployeeData1(schemeBillgroupId,paybillMonth,paybillYear,userName);
	}

	@Override
	public List<Object[]> lstBillGroupUserWise(String userName) {
		// TODO Auto-generated method stub
		return payslipReportRepo.lstBillGroupUserWise(userName);
	}

	@Override
	public List<PayslipReportModel> fetchBillGorupPaySlip(int paybillmonth, int paybillYear, String ddoCode) {
		List<Object[]> lstprop = payslipReportRepo.fetchBillGorupPaySlip(paybillmonth,paybillYear,ddoCode);
		List<PayslipReportModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				PayslipReportModel obj = new PayslipReportModel();
				obj.setBillgorupid(StringHelperUtils.isNullInt(objLst[0]));
				obj.setBillgourp(StringHelperUtils.isNullString(objLst[1]));
				lstObj.add(obj);
			}
		}

		return lstObj;
	}

	

	@Override
	public List<Object[]> getEmpDataBySevaarthidBillGroup(Integer paybillMonth, Integer paybillYear, String savaarthid,
			String schemeBillgroupId) {
		return payslipReportRepo.getEmpDataBySevaarthidBillGroup(paybillMonth,paybillYear,savaarthid,schemeBillgroupId);
	}

	@Override
	public List<Map<String, Object>> getempDetailsBySevaarthIduserPayslip(Integer paybillYear, Integer paybillMonth,
			String savaarthid, String schemeBillgroupId) {

		return payslipReportRepo.getempDetailsBySevaarthIduserPayslip(paybillYear,paybillMonth,savaarthid,schemeBillgroupId);
	
	}

	

}

