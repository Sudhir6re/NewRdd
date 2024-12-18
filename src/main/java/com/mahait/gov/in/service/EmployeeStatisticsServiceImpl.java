package com.mahait.gov.in.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.EmployeeStatisticsModel;
import com.mahait.gov.in.repository.EmployeeStatisticsRepo;

@Service
@Transactional
public class EmployeeStatisticsServiceImpl implements EmployeeStatisticsService{

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private EmployeeStatisticsRepo employeeStatisticsRepo;
	
	

	@Override
	public List<EmployeeStatisticsModel> findEmployeeStatistics(String DDOCode,int roleId) {

		

		List<Object[]> lstprop = employeeStatisticsRepo.findEmployeeStatistics(DDOCode,roleId);
		List<EmployeeStatisticsModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				EmployeeStatisticsModel obj = new EmployeeStatisticsModel();

				obj.setEmpname(StringHelperUtils.isNullString(objLst[0]));
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[1]));
				obj.setDob(StringHelperUtils.isNullDate(objLst[2]));
				obj.setCadre(StringHelperUtils.isNullString(objLst[3]));
				obj.setPostname(StringHelperUtils.isNullString(objLst[4]));
				obj.setPostType(StringHelperUtils.isNullString(objLst[5]));
				obj.setPoststartdate(StringHelperUtils.isNullDate(objLst[6]));
				obj.setPostenddate(StringHelperUtils.isNullDate(objLst[7]));
				obj.setDateofjoining(StringHelperUtils.isNullDate(objLst[8]));
				obj.setDateofexipry(StringHelperUtils.isNullDate(objLst[9]));
				obj.setScaledec(StringHelperUtils.isNullString(objLst[10]));
				obj.setBasicpay(StringHelperUtils.isNullDouble(objLst[11]));
				obj.setSevenbasicpay(StringHelperUtils.isNullDouble(objLst[12]));
				obj.setSevenPcLvl(StringHelperUtils.isNullString(objLst[13]));
				obj.setPfserives(StringHelperUtils.isNullString(objLst[14]));
				obj.setGpfdcps(StringHelperUtils.isNullString(objLst[15]));
				obj.setGisgroup(StringHelperUtils.isNullString(objLst[16]));
				obj.setGisvalue(StringHelperUtils.isNullString(objLst[17]));
				obj.setHandicap(StringHelperUtils.isNullString(objLst[18].toString()));
				obj.setBankname(StringHelperUtils.isNullString(objLst[19]));
				obj.setBankbarname(StringHelperUtils.isNullString(objLst[20]));
				obj.setAccno(StringHelperUtils.isNullBigInteger(objLst[21]));
				obj.setPranNo(StringHelperUtils.isNullString(objLst[22]));

				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}
}
