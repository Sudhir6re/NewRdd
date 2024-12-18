package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.RevenueStampReportModel;
import com.mahait.gov.in.repository.RevenueStampReportRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class RevenueStampReportServiceImpl implements RevenueStampReportService{

	@Autowired
	RevenueStampReportRepo revenueStampReportRepo;
	
	
	@Override
	public List<RevenueStampReportModel> revenueStampReportfind(int monthName, int yearName, String ddoCode, Long billNumber) {

		

		List<Object[]> lstprop = revenueStampReportRepo.findRevenueStampEmpDtls(monthName,yearName,ddoCode,billNumber);
		List<RevenueStampReportModel> lstObj = new ArrayList<>();
		
		Double sum=0d;
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				RevenueStampReportModel obj = new RevenueStampReportModel();
				/// '('|| emp.sevaarth_id ||')'|| employee_full_name_en ||' ('|| designation_name ||')' as employee,pan_no,gross_total_amt,revenue_stamp 
				obj.setEmpName(StringHelperUtils.isNullString(objLst[0]));
				obj.setPanNo(StringHelperUtils.isNullString(objLst[1]));
				BigInteger gross = (BigInteger)objLst[2];
				obj.setGross(gross.doubleValue());
				BigInteger revenueStamp = (BigInteger)objLst[3];
				obj.setRevenueStamp(revenueStamp.doubleValue());
				
				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}


	@Override
	public List<Object[]> getddoLvl2byddo(String ddoCode) {
		// TODO Auto-generated method stub
		return revenueStampReportRepo.getddoLvl2byddo(ddoCode);
	}

}
