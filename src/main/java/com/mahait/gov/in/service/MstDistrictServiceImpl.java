package com.mahait.gov.in.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.model.MstStateModel;
import com.mahait.gov.in.repository.MstDistrictRepo;
import com.mahait.gov.in.repository.MstSaveDistrictRepo;



@Service
@Transactional
public class MstDistrictServiceImpl implements MstDistrictService{
	
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private MstDistrictRepo mstDistrictRepo;
	
	@Autowired
	private MstSaveDistrictRepo mstSaveDistrictRepo;
	
	@Override
	public List<CmnDistrictMst> findAllDistrict() {
		return mstDistrictRepo.findAllDistrict();
	}
	/*@Override
	public List<MstStateModel> fetchStateByCountry(int countryId) {
		return mstDistrictRepo.fetchStateByCountry(countryId);
	}*/
	 
	@Override
	public CmnDistrictMst saveDistrict(CmnDistrictMst mstDepartmentEntity) {
		mstDepartmentEntity.setActivateFlag(1);
		mstDepartmentEntity.setLangId(1);
		mstDepartmentEntity.setCreatedBy(1l); 
		mstDepartmentEntity.setCreatedByPost(1l); 
		mstDepartmentEntity.setCreatedDate(LocalDateTime.now());
		mstDepartmentEntity.setDistrictCode("15");
		mstDepartmentEntity.setDistrictType(44l);
		mstDepartmentEntity.setActivateFlag(1);
		mstDepartmentEntity.setDistrictName(mstDepartmentEntity.getDistrictName().toUpperCase());
		mstDepartmentEntity.setStateId(mstDepartmentEntity.getStateId());
		return mstSaveDistrictRepo.save(mstDepartmentEntity);
}
	/*private Timestamp now() {
		// TODO Auto-generated method stub
		return null;
	}*/

	@Override
	public List<Long> validateDistrictname(String districtname) {
		// TODO Auto-generated method stub
		return mstDistrictRepo.validateDistrictName(districtname);
	}
}
