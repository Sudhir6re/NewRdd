package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.GrossReportModel;
import com.mahait.gov.in.repository.GrossReportRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class GrossReportServiceImpl implements GrossReportService {
	
	@Autowired
	GrossReportRepo grossReportRepo;

	@Override
	public List<GrossReportModel> lstGetDeptLst() {
		// TODO Auto-generated method stub
		
		
		List<Object[]> lst = grossReportRepo.lstGetDeptLst();
		List<GrossReportModel> lstObj= new ArrayList<>();
		for (Object[] objects : lst) {
			GrossReportModel obj = new GrossReportModel();
			BigInteger k = (BigInteger) objects[0];
			obj.setLocId(k.longValue());
			obj.setLocName(StringHelperUtils.isNullString(objects[1]));
			
			lstObj.add(obj);
			
		}
		return lstObj;
	}


	@Override
	public List<GrossReportModel> grossReport(int yearId, long locId) {
		List<Object[]> lstprop = grossReportRepo.grossReport(yearId,locId);
		List<GrossReportModel> lstObj = new ArrayList<>();
		
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				GrossReportModel obj = new GrossReportModel();
				obj.setLocName(StringHelperUtils.isNullString(objLst[0]));
				obj.setDistrict(StringHelperUtils.isNullString(objLst[1]));
				obj.setOffice(StringHelperUtils.isNullString(objLst[2]));
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[3]));
				obj.setGender(StringHelperUtils.isNullString(objLst[4]));
				obj.setDesgn(StringHelperUtils.isNullString(objLst[5]));
				obj.setDob(StringHelperUtils.isNullDate(objLst[6]));
				obj.setAge(StringHelperUtils.isNullDouble(objLst[7]));
				obj.setGross(StringHelperUtils.isNullBigDecimal(objLst[8]));
				obj.setBasicArr(StringHelperUtils.isNullBigDecimal(objLst[9]));
				obj.setDaArr(StringHelperUtils.isNullBigInteger(objLst[10]));
				obj.setGpfArr(StringHelperUtils.isNullBigInteger(objLst[11]));
				obj.setDcpsArr(StringHelperUtils.isNullBigInteger(objLst[12]));
				obj.setTransArr(StringHelperUtils.isNullBigInteger(objLst[13]));
				obj.setTaArr(StringHelperUtils.isNullBigInteger(objLst[14]));
				obj.setHrafifthPay(StringHelperUtils.isNullBigInteger(objLst[15]));
				
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

}
