package com.mahait.gov.in.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.MstTalukaEntity;
import com.mahait.gov.in.model.MstTalukaModel;
import com.mahait.gov.in.repository.MstSaveTalukaRepo;
import com.mahait.gov.in.repository.MstTalukaRepo;

@Service
@Transactional
public class MstTalukaServiceImpl implements MstTalukaService{

	@Autowired
	private MstTalukaRepo mstTalukaRepo;
	
	@Autowired
	private MstSaveTalukaRepo mstSaveTalukaRepo;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public List<Object[]> findAllTaluka(int distId) {
		return mstTalukaRepo.findAllTaluka(distId);
	}

	@Override
	public List<MstTalukaEntity> findTalukaAll() {
		// TODO Auto-generated method stub
		return mstTalukaRepo.findTalukaAll();
	}
	
	@Override
	public List<MstTalukaModel> fetchDistrictByState(int stateCode) {
		return mstTalukaRepo.fetchDistrictByState(stateCode);
	}
	//Added for Saving
	@Override
	public MstTalukaEntity saveTaluka(MstTalukaEntity mstDepartmentEntity) {
		mstDepartmentEntity.setIsActive('1');
		mstDepartmentEntity.setCreatedUserId(1); 
		mstDepartmentEntity.setCreatedDate(now());
		mstDepartmentEntity.setTalukaNameEn(mstDepartmentEntity.getTalukaNameEn());
		
		
		//MstTalukaEntity saveId=mstSaveTalukaRepo.save(mstDepartmentEntity);
		
		
		
		
		
		return mstSaveTalukaRepo.save(mstDepartmentEntity);
}

	private Date now() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MstTalukaEntity> findByTaluka(Integer subDepartmentCode) {
		return mstTalukaRepo.findByTaluka(subDepartmentCode);
	}

	@Override
	public List<Long> validateTalukaName(String talukaname, Integer districtcode) {
		// TODO Auto-generated method stub
		return mstTalukaRepo.validateTalukaName(talukaname,districtcode);
	}

	
}
