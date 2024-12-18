package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.AllowanceDeductionMstEntity;
import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.repository.AllowanceDeductionWiseMstRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AllowanceDeductionWiseMstServiceImpl implements AllowanceDeductionWiseMstService{
	

	@Autowired
	AllowanceDeductionWiseMstRepo allowanceDeductionWiseMstRepo;
	

	@Override
	public int saveAllowanceDeductionWiseMaster(AllowanceDeductionMstEntity allowanceDeductionMstEntity) {
		// TODO Auto-generated method stub
		allowanceDeductionMstEntity.setCreatedDate(new Date()); 
		allowanceDeductionMstEntity.setIsActive('1');
		if(allowanceDeductionMstEntity.getPayCommissionCode().equals(8)) {
			allowanceDeductionMstEntity.setDepartmentAllowdeducCode(1);
		}else {
			allowanceDeductionMstEntity.setDepartmentAllowdeducCode(10);
		}
		return allowanceDeductionWiseMstRepo.saveAllowanceDeductionWiseMaster(allowanceDeductionMstEntity);
	}


	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> fetchLstDeptEligibilityForAllowAndDeductEntityByType(
			int isType) {
		List<DeptEligibilityForAllowAndDeductEntity> lstlstDeptEligibilityForAllowAndDeductEntity=new ArrayList<>();
		List<Object[]> lstObj=allowanceDeductionWiseMstRepo.fetchLstDeptEligibilityForAllowAndDeductEntityByType(isType);
		for(Object obj[]:lstObj) {
			DeptEligibilityForAllowAndDeductEntity deptEligibilityForAllowAndDeductEntity=new DeptEligibilityForAllowAndDeductEntity();
			deptEligibilityForAllowAndDeductEntity.setDepartmentAllowdeducCode(StringHelperUtils.isNullInt(obj[0]));
			deptEligibilityForAllowAndDeductEntity.setDepartmentAllowdeducName(StringHelperUtils.isNullString(obj[1]));
			lstlstDeptEligibilityForAllowAndDeductEntity.add(deptEligibilityForAllowAndDeductEntity);
		}
		return lstlstDeptEligibilityForAllowAndDeductEntity;
	}


	@Override
	public List<AllowanceDeductionMstEntity> getAllallowanceDeductionWiseMst() {
		// TODO Auto-generated method stub
		List<AllowanceDeductionMstEntity> lstAllowanceDeductionMstEntity=new ArrayList<>();
		List<Object[]> lstObj=allowanceDeductionWiseMstRepo.getAllallowanceDeductionWiseMst();
 		for(Object object[]:lstObj) {
 			AllowanceDeductionMstEntity allowanceDeductionMstEntity=new AllowanceDeductionMstEntity(); 
 			allowanceDeductionMstEntity.setAllowanceDeductionWiseId(StringHelperUtils.isNullInt(object[0]));
 			allowanceDeductionMstEntity.setDepartmentAllowdeducCode(StringHelperUtils.isNullInt(object[4]));
 			allowanceDeductionMstEntity.setPayCommissionCode(StringHelperUtils.isNullInt(object[8]));
 			allowanceDeductionMstEntity.setIsType(StringHelperUtils.isNullInt(object[7]));
 			allowanceDeductionMstEntity.setStartDate(StringHelperUtils.isNullDate(object[10]));
 			
 			if(object[5]!=null) {
 			allowanceDeductionMstEntity.setEndDate(StringHelperUtils.isNullDate(object[5]));
 			}
 			allowanceDeductionMstEntity.setCreatedUserId(StringHelperUtils.isNullInt(object[3]));
 			//allowanceDeductionMstEntity.setEndDate(StringHelperUtils.isNullDate(object[5]));
 			allowanceDeductionMstEntity.setAmount(StringHelperUtils.isNullDouble(object[1]));  //10 amt
 			allowanceDeductionMstEntity.setPercentage(StringHelperUtils.isNullInt(object[9]));  //11 perc  
 			allowanceDeductionMstEntity.setIsActive(StringHelperUtils.isNullChar(object[6]));
 			allowanceDeductionMstEntity.setDepartmentAlloDeducName(StringHelperUtils.isNullString(object[13]));     //12
 			allowanceDeductionMstEntity.setCommisionName(StringHelperUtils.isNullString(object[14]));    //13
 			lstAllowanceDeductionMstEntity.add(allowanceDeductionMstEntity);
		}
		return lstAllowanceDeductionMstEntity;
	}


	@Override
	public int isAllowanceDeductionWiseMasterDataPresent(AllowanceDeductionMstEntity allowanceDeductionMstEntity) {
		List<Object[]> lstObj=allowanceDeductionWiseMstRepo.isAllowanceDeductionWiseMasterDataPresent(allowanceDeductionMstEntity);
		return lstObj.size();
	}


	

	@Override
	public AllowanceDeductionMstEntity findAllowanceDeductionWiseMasterById(int id) {
		return allowanceDeductionWiseMstRepo.findAllowanceDeductionWiseMasterById(id);
	}


	@Override
	public AllowanceDeductionMstEntity deleteAllowanceDeductionWiseMasterById(int id,char status) {
		AllowanceDeductionMstEntity allowanceDeductionMstEntity= allowanceDeductionWiseMstRepo.deleteAllowanceDeductionWiseMasterById(id,status);
		 return allowanceDeductionMstEntity;
	}


	@Override
	public int checkComponentAlreadyPresent(AllowanceDeductionMstEntity allowanceDeductionMstEntity) {
		// TODO Auto-generated method stub
		return allowanceDeductionWiseMstRepo.checkComponentAlreadyPresent(allowanceDeductionMstEntity);
	}


	@Override
	public void updateAllowanceDeductionMstEntity(AllowanceDeductionMstEntity allowanceDeductionMstEntity1) {
		// TODO Auto-generated method stub
		 allowanceDeductionWiseMstRepo.updateAllowanceDeductionMstEntity(allowanceDeductionMstEntity1);
	}


	@Override
	public List<Long> validateAllowDeduct(String allowdeductName, Integer isType) {
		// TODO Auto-generated method stub
		return allowanceDeductionWiseMstRepo.validateAllowDeduct(allowdeductName,isType);
	}

}
