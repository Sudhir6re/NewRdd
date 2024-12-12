package com.mahait.gov.in.nps.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.entity.DcpsLegacyDataEntity;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;
import com.mahait.gov.in.nps.repository.EntryDcpsLegacyRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class EntryDcpsLegacyServiceImpl implements EntryDcpsLegacyService {

	@Autowired
	EntryDcpsLegacyRepo entryDcpsLegacyRepo;

	@Override
	public List<DcpsLegacyModel> findDcpsEmployeeDetails(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages) {
		List<DcpsLegacyModel> lstDcpsLegacyModel = new ArrayList<>();
		List<Object[]> lstObject = entryDcpsLegacyRepo.findDcpsEmployeeDetails(dcpsLegacyModel, messages);
		if (lstObject.size() > 0) {
			for (Object[] object : lstObject) {
				DcpsLegacyModel dcpsLegacyModel1 = new DcpsLegacyModel();
				dcpsLegacyModel1.setDcpsNo(StringHelperUtils.isNullString(object[0]));
				dcpsLegacyModel1.setEmployeeFullName(StringHelperUtils.isNullString(object[1]));
				dcpsLegacyModel1.setSevaarthId(StringHelperUtils.isNullString(object[2]));
				dcpsLegacyModel1.setDob(StringHelperUtils.isNullDate(object[3]));
				dcpsLegacyModel1.setSuperAnnDate(StringHelperUtils.isNullDate(object[4]));
				dcpsLegacyModel1.setMulTwoYearBasic(StringHelperUtils.isNullBigInteger(object[5]).longValue());
				dcpsLegacyModel1.setMulOneYearBasic(StringHelperUtils.isNullBigInteger(object[6]).longValue());
				dcpsLegacyModel1.setPranNo(StringHelperUtils.isNullString(object[7]));
				dcpsLegacyModel1.setEmpServiceEndDate(StringHelperUtils.isNullDate(object[8]));
				dcpsLegacyModel1.setDdoCode(StringHelperUtils.isNullString(object[10]));
				dcpsLegacyModel1.setDoj(StringHelperUtils.isNullDate(object[11]));
				lstDcpsLegacyModel.add(dcpsLegacyModel1);
			}
		}
		return lstDcpsLegacyModel;
	}

	@Override
	public Long saveDcpsLegacyData(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages) {
		Long saveId=0l;
		for (DcpsLegacyModel dcpsLegacyModel1 : dcpsLegacyModel.getLstDcpsLegacyModel()) {
			DcpsLegacyDataEntity dcpsLegacyDataEntity = new DcpsLegacyDataEntity();
			dcpsLegacyDataEntity.setContriEndDate(dcpsLegacyModel1.getContriEndDt());
			dcpsLegacyDataEntity.setDdoCode(dcpsLegacyModel1.getDdoCode());
			dcpsLegacyDataEntity.setContriStartDate(dcpsLegacyModel1.getContriStartDt());
			dcpsLegacyDataEntity.setDcpsId(dcpsLegacyModel1.getDcpsNo());
			dcpsLegacyDataEntity.setSevarthId(dcpsLegacyModel1.getSevaarthId());
			dcpsLegacyDataEntity.setStatus("2");
			dcpsLegacyDataEntity.setEmpContri(dcpsLegacyModel1.getEmpContri().floatValue());
			dcpsLegacyDataEntity.setEmpInt(dcpsLegacyModel1.getEmpInterest().floatValue());
			dcpsLegacyDataEntity.setEmployerContri(dcpsLegacyModel1.getEmployerContri().floatValue());
			dcpsLegacyDataEntity.setEmployerInt(dcpsLegacyModel1.getEmployerInterest().floatValue());
			dcpsLegacyDataEntity.setRemarks(dcpsLegacyModel1.getRemark());
			dcpsLegacyDataEntity.setPeriod(dcpsLegacyModel1.getPeriod());
			dcpsLegacyDataEntity.setCreatedDate(new Timestamp(new Date().getTime()));
			dcpsLegacyDataEntity.setCreatedPostId(messages.getUserId());
			dcpsLegacyDataEntity.setYear((short)0);
			dcpsLegacyDataEntity.setMonth((short)0);
			dcpsLegacyDataEntity.setTotal(dcpsLegacyModel1.getTotal().floatValue());
			saveId = entryDcpsLegacyRepo.saveDcpsLegacyData(dcpsLegacyDataEntity);
		}

		return saveId;
	}

	@Override
	public Integer checkDataExistsForPeriod(OrgUserMst messages, String period, String sevaarthId) {
		return entryDcpsLegacyRepo.checkDataExistsForPeriod(messages,period,sevaarthId);
	}

	
	
}
