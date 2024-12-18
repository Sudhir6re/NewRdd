package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.repository.GISReportRepo;
@Service
@Transactional
public class GISReportServiceImpl implements GISReportService{

	@Autowired
	GISReportRepo gisReportRepo;
	
	
	@Override
	public List<RegularReportModel> findRptDtls(Integer monthId, Integer yearId, Long billGroup,Long gisAllowDeducCode) {
		
		List<Object[]> lst = gisReportRepo.findRptDtls(monthId,yearId,billGroup,gisAllowDeducCode);
		
		
		List<RegularReportModel> lstObj = new ArrayList<>();
		//count (a.sevaarth_id),d.group_name_en,'preminum_amount'
		for (Object[] objects : lst) {
			RegularReportModel regularReportModel = new RegularReportModel();	
			regularReportModel.setCountGisEmp(StringHelperUtils.isNullBigInteger(objects[0]));
			regularReportModel.setCadreGroup(StringHelperUtils.isNullString(objects[1]));
			regularReportModel.setGisRateType(StringHelperUtils.isNullString(objects[2]));
			regularReportModel.setGisAmount(StringHelperUtils.isNullDouble(objects[3]));
			regularReportModel.setGisRate(StringHelperUtils.isNullInt(objects[4]));
			lstObj.add(regularReportModel);
			
		}
		
		// TODO Auto-generated method stub
		return lstObj;
	}

}
