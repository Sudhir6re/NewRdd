package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.HRAAllowanceMstEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.repository.HRAAllowanceMstRepo;

@Service
public class HRAAllowanceMstServiceImpl implements HRAAllowanceMstService {
	
	@Autowired
	HRAAllowanceMstRepo hraAllowanceMstRepo;

	@Override
	public int SaveHRAAllowanceMaster(HRAAllowanceMstEntity hraAllowanceMstEntity) {
		HRAAllowanceMstEntity hraAllowanceMstEntity1=new HRAAllowanceMstEntity();
		hraAllowanceMstEntity1.setPayCommissionCode(hraAllowanceMstEntity.getPayCommissionCode());
		hraAllowanceMstEntity1.setCityClassX(hraAllowanceMstEntity.getCityClassX());
		hraAllowanceMstEntity1.setCityClassY(hraAllowanceMstEntity.getCityClassY());
		hraAllowanceMstEntity1.setCityClassZ(hraAllowanceMstEntity.getCityClassZ());
		hraAllowanceMstEntity1.setStartDate(hraAllowanceMstEntity.getStartDate());
		hraAllowanceMstEntity1.setCreatedDate(new Date()); 
		hraAllowanceMstEntity1.setCreatedUserId(1);
		hraAllowanceMstEntity1.setIsActive('1');
		
		return hraAllowanceMstRepo.SaveHRAAllowanceMaster(hraAllowanceMstEntity1);
	}

	@Override
	public List<MstPayCommissionEntity> getlstddcPayCommission() {
		// TODO Auto-generated method stub
		
		
		return hraAllowanceMstRepo.getlstddcPayCommission();
	}

	@Override
	public List<HRAAllowanceMstEntity> getlstAllowanceDeductionMst() {
		
		List<HRAAllowanceMstEntity> lstAllowanceDeductionMstEntity=new ArrayList<>();
		List<Object[]> lstObj=hraAllowanceMstRepo.getlstAllowanceDeductionMst();
 		for(Object object[]:lstObj) {
 			HRAAllowanceMstEntity allowanceDeductionMstEntity=new HRAAllowanceMstEntity(); 
 			
 			allowanceDeductionMstEntity.setAllowanceHRAId(StringHelperUtils.isNullInt(object[0]));
 			allowanceDeductionMstEntity.setPayCommissionName(StringHelperUtils.isNullString(object[6]));
 			allowanceDeductionMstEntity.setStartDate(StringHelperUtils.isNullDate(object[1]));
 			
 			if(object[2]!=null) {
 			allowanceDeductionMstEntity.setEndDate(StringHelperUtils.isNullDate(object[2]));
 			}
 			allowanceDeductionMstEntity.setCityClassX(StringHelperUtils.isNullString(object[3])); 
 			allowanceDeductionMstEntity.setCityClassY(StringHelperUtils.isNullString(object[4])); 
 			allowanceDeductionMstEntity.setCityClassZ(StringHelperUtils.isNullString(object[5])); 
 			allowanceDeductionMstEntity.setIsActive(StringHelperUtils.isNullChar(object[7]));
 			lstAllowanceDeductionMstEntity.add(allowanceDeductionMstEntity);
		}
		
		
		return lstAllowanceDeductionMstEntity;
	}

	@Override
	public int checkComponentAlreadyPresent(HRAAllowanceMstEntity hraAllowanceMstEntity) {
		return hraAllowanceMstRepo.checkComponentAlreadyPresent(hraAllowanceMstEntity);
	}

	@Override
	public HRAAllowanceMstEntity findAllowanceDeductionWiseMasterById(int checkIsDataAlreadyPresent) {
		// TODO Auto-generated method stub
		return hraAllowanceMstRepo.findAllowanceDeductionWiseMasterById(checkIsDataAlreadyPresent);
	}

	@Override
	public void updateRRAAllowanceMstEntity(HRAAllowanceMstEntity hRAAllowanceMstEntity) {
		hraAllowanceMstRepo.updateAllowanceDeductionMstEntity(hRAAllowanceMstEntity);
		
	}

	@Override
	public HRAAllowanceMstEntity findEntrybyId(int allowanceHRAId) {
		// TODO Auto-generated method stub
		HRAAllowanceMstEntity objforedit = hraAllowanceMstRepo.findEntrybyId(allowanceHRAId);
		
		return objforedit;
	}

	@Override
	public String hRAAllowanceMstUpdate(HRAAllowanceMstEntity hraAllowanceMstEntity) {
		HRAAllowanceMstEntity hraAllowanceMstEntity1 =hraAllowanceMstRepo.findbyhraid(hraAllowanceMstEntity.getAllowanceHRAId());
		if(hraAllowanceMstEntity1!=null) {
			hraAllowanceMstEntity1.setPayCommissionCode(hraAllowanceMstEntity.getPayCommissionCode());
			hraAllowanceMstEntity1.setCityClassX(hraAllowanceMstEntity.getCityClassX());
			hraAllowanceMstEntity1.setCityClassY(hraAllowanceMstEntity.getCityClassY());
			hraAllowanceMstEntity1.setCityClassZ(hraAllowanceMstEntity.getCityClassZ());
			hraAllowanceMstEntity1.setIsActive(hraAllowanceMstEntity.getIsActive());
			hraAllowanceMstEntity1.setStartDate(hraAllowanceMstEntity.getStartDate());
			if(hraAllowanceMstEntity1.getEndDate()!=null)
			hraAllowanceMstEntity.setEndDate(hraAllowanceMstEntity.getEndDate());
			
			hraAllowanceMstEntity1.setUpdatedDate(new Date());
			hraAllowanceMstRepo.updateHRAStatus(hraAllowanceMstEntity1);
		}
		return "UPDATED";
	}

	@Override
	public HRAAllowanceMstEntity findAllowanceHRAByIdForReject(int allowanceHRAId) {
		HRAAllowanceMstEntity objCadre = hraAllowanceMstRepo.findAllowanceHRAByIdForReject(allowanceHRAId);
		if(objCadre != null) {
			objCadre.setIsActive('0');	// REJECTED
			objCadre.setUpdatedDate(new Date());
			hraAllowanceMstRepo.updateAllowanceHRAStatus(objCadre);
		}
		return objCadre;
	}

	
	

}
