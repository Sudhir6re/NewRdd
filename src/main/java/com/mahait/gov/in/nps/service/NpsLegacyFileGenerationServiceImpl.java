package com.mahait.gov.in.nps.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;
import com.mahait.gov.in.nps.repository.NpsLegacyFileGenerationRepo;
import com.mahait.gov.in.nps.repository.VerifyDcpsLegacyRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class NpsLegacyFileGenerationServiceImpl implements NpsLegacyFileGenerationService{

	@Autowired
	NpsLegacyFileGenerationRepo npsLegacyFileGenerationRepo;
	
	@Autowired
	VerifyDcpsLegacyRepo verifyDcpsLegacyRepo;
	

	@Override
	public List<DcpsLegacyModel> findLegacyEmployeeList(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages,
			String locationId) {
		List<DcpsLegacyModel> lstDcpsLegacyModel = new ArrayList<>();
		Long locId=verifyDcpsLegacyRepo.findLocId(locationId);
		List<Object[]> lstObject = npsLegacyFileGenerationRepo.findLegacyEmployeeList(dcpsLegacyModel, messages,locId);
		if (lstObject.size() > 0) {
			for (Object[] object : lstObject) {
				DcpsLegacyModel dcpsLegacyModel1 = new DcpsLegacyModel();
				dcpsLegacyModel1.setEmployeeFullName(StringHelperUtils.isNullString(object[0]));
				dcpsLegacyModel1.setDcpsNo(StringHelperUtils.isNullString(object[1]));
				dcpsLegacyModel1.setPranNo(StringHelperUtils.isNullString(object[2]));
				dcpsLegacyModel1.setEmpContri(StringHelperUtils.isNullFloat(object[3]).longValue());
				dcpsLegacyModel1.setEmployerContri(StringHelperUtils.isNullFloat(object[4]).longValue());
				dcpsLegacyModel1.setEmpInterest(StringHelperUtils.isNullFloat(object[5]).longValue());
				dcpsLegacyModel1.setEmployerInterest(StringHelperUtils.isNullFloat(object[6]).longValue());
				dcpsLegacyModel1.setLocName(StringHelperUtils.isNullString(object[7]));
				dcpsLegacyModel1.setDtoRegNo(StringHelperUtils.isNullString(object[8]));
				dcpsLegacyModel1.setDdoRegNo(StringHelperUtils.isNullString(object[9]));
				dcpsLegacyModel1.setNpsId(StringHelperUtils.isNullBigInteger(object[10]).longValue());
				dcpsLegacyModel1.setDdoCode(StringHelperUtils.isNullString(object[11]));
				dcpsLegacyModel1.setTotal(StringHelperUtils.isNullFloat(object[12]).longValue());
				lstDcpsLegacyModel.add(dcpsLegacyModel1);
			}
		}
		return lstDcpsLegacyModel;
	}


	@Override
	public String findLocId(String locationCode) {
		Long locId=verifyDcpsLegacyRepo.findLocId(locationCode);
		return locId.toString();
	}


	@Override
	public String getBatchId(String locId) {
		return npsLegacyFileGenerationRepo.getBatchId(locId);
	}


	@Override
	public List<Object[]> findDdoWiseTotalAmnt(String locId, String treasuryyno, String strDDOCode) {
		return npsLegacyFileGenerationRepo.findDdoWiseTotalAmnt(locId,treasuryyno,strDDOCode) ;
	}


	@Override
	public String getEmployeeRecordCountDdoregNsdl(String locId, String treasuryyno, String ddoRegNo) {
		return npsLegacyFileGenerationRepo.getEmployeeRecordCountDdoregNsdl(locId,treasuryyno,ddoRegNo) ;
	}


	@Override
	public List<Object[]> getEmployeeListDdoregNsdl(String locId, String treasuryyno, String ddoRegNo) {
		return npsLegacyFileGenerationRepo.getEmployeeListDdoregNsdl(locId,treasuryyno, ddoRegNo) ;
	}


	@Override
	public List<Object[]> findDtoRegWiseAmnt(String locId, String treasuryyno, String ddoRegNo) {
		return npsLegacyFileGenerationRepo.findDtoRegWiseAmnt(locId,treasuryyno, ddoRegNo) ;
	}


	


}
