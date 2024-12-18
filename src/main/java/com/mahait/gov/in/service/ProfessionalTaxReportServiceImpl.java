package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.repository.ProfessionalTaxReportRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class ProfessionalTaxReportServiceImpl implements ProfessionalTaxReportService{
	
	@Autowired
	ProfessionalTaxReportRepo professionalTaxReportRepo;

	@Override
	public List<RegularReportModel> findProfessionalTaxDtls(Integer yearId, Integer monthId, Long billGroup,
			String ddoCode) {
		
		List<Object[]> lstprop = professionalTaxReportRepo.findProfessionalTaxDtls(yearId,monthId,billGroup,ddoCode);
		List<RegularReportModel> lstObj = new ArrayList<>();
		
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				RegularReportModel obj = new RegularReportModel();
				obj.setName(StringHelperUtils.isNullString(objLst[0]));
				obj.setPanNo(StringHelperUtils.isNullString(objLst[1]));
				BigInteger gross = (BigInteger) objLst[2];	
				obj.setGrossAmount(gross.doubleValue());
				BigInteger pt = (BigInteger) objLst[3];
				obj.setProfessionalTax(pt.doubleValue());
				
				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}

}
