package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.AquittanceRollReportModel;
import com.mahait.gov.in.repository.AquittanceRollReportRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service

public class AquittanceRollReportServiceImpl implements AquittanceRollReportService{
	
	@Autowired
	
	AquittanceRollReportRepo aquittanceRollReportRepo;

	@Override
	public List<AquittanceRollReportModel> findAquittanceReportDtls(int monthName, int yearName, String ddoCode,
			Long billNumber) {

		

		List<Object[]> lstprop = aquittanceRollReportRepo.findAquittanceReportDtls(monthName,yearName,ddoCode,billNumber);
		List<AquittanceRollReportModel> lstObj = new ArrayList<>();
		
		Double sum=0d;
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				AquittanceRollReportModel obj = new AquittanceRollReportModel();
			///emp.employee_full_name_en||' ('||  emp.sevaarth_id ||' )'|| deg.designation_name as employee,billdtl.total_net_amt,billdtl.total_deduction
				
				obj.setEmpName(StringHelperUtils.isNullString(objLst[0]));
				obj.setNetamt(StringHelperUtils.isNullDouble(objLst[1]));
				obj.setTotaldedamt(StringHelperUtils.isNullDouble(objLst[2]));
				
				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}

}
