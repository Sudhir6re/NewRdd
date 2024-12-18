package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PayslipReportModel;
import com.mahait.gov.in.repository.EmpPayslipRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class EmpPayslipServiceImpl  implements EmpPayslipService{
	
	@Autowired
	EmpPayslipRepo empPayslipRepo;

	@Override
	public List<Object[]> isPaybillGenerated(Long billNumber, int monthName, int yearName, OrgUserMst messages) {
		// TODO Auto-generated method stub
		return empPayslipRepo.isPaybillGenerated(billNumber,monthName,yearName,messages) ;
	}

	@Override
	public List<Object[]> getEmployeeData1(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			OrgUserMst messages) {
		return empPayslipRepo.getEmployeeData1(schemeBillgroupId,paybillMonth,paybillYear,messages) ;
	}

	@Override
	public List<PayslipReportModel> getAllDataForinnernew(String savaarthid) {
		return empPayslipRepo.getAllDataForinnernew(savaarthid) ;
	}

	@Override
	public List<Map<String, Object>> getempDetails(String schemeBillgroupId, Integer paybillYear, Integer paybillMonth,
			String savaarthid) {
		return empPayslipRepo.getempDetails(schemeBillgroupId,paybillYear,paybillMonth,savaarthid) ;
	}

	@Override
	public List<MstDcpsBillGroup> lstBillDesc(OrgUserMst messages) {
		// TODO Auto-generated method stub
		List<Object[]> lstObject=empPayslipRepo.lstBillDesc(messages);
		
		List<MstDcpsBillGroup> lstMstDcpsBillGroup=new ArrayList<>();
		for (Object[] objLst : lstObject) {
			MstDcpsBillGroup mstDcpsBillGroup = new MstDcpsBillGroup();
			mstDcpsBillGroup.setDcpsDdoBillGroupId(StringHelperUtils.isNullBigInteger(objLst[0]).longValue());
			mstDcpsBillGroup.setDescription(StringHelperUtils.isNullString(objLst[1]));
			lstMstDcpsBillGroup.add(mstDcpsBillGroup);
		}
		return lstMstDcpsBillGroup;
	}

}
