package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.repository.IncomeTaxReportRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class IncomeTaxReportServiceImpl implements IncomeTaxReportService{
	
	@Autowired
	IncomeTaxReportRepo incomeTaxReportRepo;

	@Override
	public List<RegularReportModel> findIncomeTaxDtls(Integer yearId, Integer monthId, Long billGroup, String ddoCode) {
		
		List<Object[]> lstprop = incomeTaxReportRepo.findIncomeTaxDtls(yearId,monthId,billGroup,ddoCode);
		List<RegularReportModel> lstObj = new ArrayList<>();
		
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				RegularReportModel obj = new RegularReportModel();
				obj.setName(StringHelperUtils.isNullString(objLst[0]));
				obj.setPanNo(StringHelperUtils.isNullString(objLst[1]));
				obj.setGrossAmount(StringHelperUtils.isNullDouble(objLst[2]));
				obj.setIncometax(StringHelperUtils.isNullDouble(objLst[3]));
				
				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}

}
