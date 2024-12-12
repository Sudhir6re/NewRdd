package com.mahait.gov.in.nps.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;
import com.mahait.gov.in.nps.repository.VerifyDcpsLegacyRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class VerifyDcpsLegacyServiceImpl implements VerifyDcpsLegacyService{
	
	@Autowired
	VerifyDcpsLegacyRepo verifyDcpsLegacyRepo;

	@Override
	public List<DcpsLegacyModel> findLegacyData(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages,String locationId) {
		List<DcpsLegacyModel> lstDcpsLegacyModel = new ArrayList<>();
		Long locId=verifyDcpsLegacyRepo.findLocId(locationId);
		List<Object[]> lstObject = verifyDcpsLegacyRepo.findLegacyData(dcpsLegacyModel, messages,locId);
		if (lstObject.size() > 0) {
			for (Object[] object : lstObject) {
				DcpsLegacyModel dcpsLegacyModel1 = new DcpsLegacyModel();
				dcpsLegacyModel1.setEmployeeFullName(StringHelperUtils.isNullString(object[0]));
				dcpsLegacyModel1.setDcpsNo(StringHelperUtils.isNullString(object[1]));
				dcpsLegacyModel1.setSevaarthId(StringHelperUtils.isNullString(object[2]));
				dcpsLegacyModel1.setPeriod(StringHelperUtils.isNullString(object[3]));
				dcpsLegacyModel1.setEmpContri(StringHelperUtils.isNullFloat(object[4]).longValue());
				dcpsLegacyModel1.setEmployerContri(StringHelperUtils.isNullFloat(object[5]).longValue());
				dcpsLegacyModel1.setEmpInterest(StringHelperUtils.isNullFloat(object[6]).longValue());
				dcpsLegacyModel1.setEmployerInterest(StringHelperUtils.isNullFloat(object[7]).longValue());
				dcpsLegacyModel1.setTotal(StringHelperUtils.isNullFloat(object[8]).longValue());
				dcpsLegacyModel1.setStatus(StringHelperUtils.isNullString(object[9]));
				dcpsLegacyModel1.setPranNo(StringHelperUtils.isNullString(object[10]));
				dcpsLegacyModel1.setRemark(StringHelperUtils.isNullString(object[11]));
				lstDcpsLegacyModel.add(dcpsLegacyModel1);
			}
		}
		return lstDcpsLegacyModel;
	}

	@Override
	public Integer verifySavedData(Map<String, String> inputParam, OrgUserMst messages) {
		String sevaarthId = inputParam.get("sevaarthId");
		String period = inputParam.get("period");
		return verifyDcpsLegacyRepo.verifySavedData(sevaarthId,period,messages);
	}

	@Override
	public Integer rejectSavedData(Map<String, String> inputParam, OrgUserMst messages) {
		String sevaarthId = inputParam.get("sevaarthId");
		String period = inputParam.get("period");
		String remark = inputParam.get("remark");
		return verifyDcpsLegacyRepo.rejectSavedData(sevaarthId,period,remark,messages);
	}
	
	

}
