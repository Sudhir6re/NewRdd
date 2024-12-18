package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.AllowanceDeductionRuleMstEntity;
import com.mahait.gov.in.repository.AllowDeducRuleMasterRepo;

import jakarta.transaction.Transactional;


@Transactional
@Service
public class AllowDeducRuleMasterServiceImpl implements AllowDeducRuleMasterService{
	
	
	@Autowired 
	AllowDeducRuleMasterRepo allowDeducRuleMasterRepo;

	@Override
	public List<AllowanceDeductionRuleMstEntity> findAllRules() {
		List<AllowanceDeductionRuleMstEntity> lstAllowanceDeductionMstEntity=new ArrayList<>();
		List<Object[]> lstObj=allowDeducRuleMasterRepo.findAllRules(0);
		  
 		for(Object object[]:lstObj) {
 			AllowanceDeductionRuleMstEntity allowanceDeductionMstEntity=new AllowanceDeductionRuleMstEntity(); 
 			allowanceDeductionMstEntity.setAllowanceDeductionWiseRuleId(StringHelperUtils.isNullInt(object[0]));
 			allowanceDeductionMstEntity.setAmount(StringHelperUtils.isNullDouble(object[1]));  //10 amt
 			allowanceDeductionMstEntity.setDepartmentAllowdeducCode(StringHelperUtils.isNullInt(object[4]));
 	 		
 	 		if(object[5]!=null) {
 				allowanceDeductionMstEntity.setEndDate(StringHelperUtils.isNullDate(object[5]));
 			}
 	 		
 			allowanceDeductionMstEntity.setIsActive(StringHelperUtils.isNullChar(object[6]));
 			
 			allowanceDeductionMstEntity.setIsType(StringHelperUtils.isNullInt(object[7]));
 			allowanceDeductionMstEntity.setPayCommissionCode(StringHelperUtils.isNullInt(object[8]));
 			allowanceDeductionMstEntity.setPercentage(StringHelperUtils.isNullInt(object[9]));  //11 perc  
 			allowanceDeductionMstEntity.setStartDate(StringHelperUtils.isNullDate(object[10]));
 			allowanceDeductionMstEntity.setPremiumAmount(StringHelperUtils.isNullDouble(object[11]));
 			allowanceDeductionMstEntity.setCityClass(StringHelperUtils.isNullCharacter(object[12]));
 			allowanceDeductionMstEntity.setMaxBasic(StringHelperUtils.isNullDouble(object[13]));
 			allowanceDeductionMstEntity.setMinBasic(StringHelperUtils.isNullDouble(object[14]));
 			allowanceDeductionMstEntity.setCityGroup(StringHelperUtils.isNullString(object[15]));
 			allowanceDeductionMstEntity.setGradePayHigher(StringHelperUtils.isNullInt(object[16]));
 			allowanceDeductionMstEntity.setGradePayLower(StringHelperUtils.isNullInt(object[17]));
 		
 			allowanceDeductionMstEntity.setDepartmentAllowdeducName(StringHelperUtils.isNullString(object[18]));     //12
 			allowanceDeductionMstEntity.setPayCommisionName(StringHelperUtils.isNullString(object[19]));
 			lstAllowanceDeductionMstEntity.add(allowanceDeductionMstEntity);
		}
		return lstAllowanceDeductionMstEntity;
	}

	@Override
	public List<AllowanceDeductionRuleMstEntity> findAllRules(int departmentAllowdeducCode) {
		List<AllowanceDeductionRuleMstEntity> lstAllowanceDeductionMstEntity=new ArrayList<>();
		List<Object[]> lstObj=allowDeducRuleMasterRepo.findAllRules(departmentAllowdeducCode);
 		for(Object object[]:lstObj) {
 			AllowanceDeductionRuleMstEntity allowanceDeductionMstEntity=new AllowanceDeductionRuleMstEntity(); 
 			allowanceDeductionMstEntity.setAllowanceDeductionWiseRuleId(StringHelperUtils.isNullInt(object[0]));
 			allowanceDeductionMstEntity.setAmount(StringHelperUtils.isNullDouble(object[1]));  //10 amt
 			allowanceDeductionMstEntity.setDepartmentAllowdeducCode(StringHelperUtils.isNullInt(object[4]));
 			
 			if(object[5]!=null) {
 				allowanceDeductionMstEntity.setEndDate(StringHelperUtils.isNullDate(object[5]));
 			}
 	 	
 	 		
 			allowanceDeductionMstEntity.setIsActive(StringHelperUtils.isNullChar(object[6]));
 			allowanceDeductionMstEntity.setIsType(StringHelperUtils.isNullInt(object[7]));
 			allowanceDeductionMstEntity.setPayCommissionCode(StringHelperUtils.isNullInt(object[8]));
 			allowanceDeductionMstEntity.setPercentage(StringHelperUtils.isNullInt(object[9]));  //11 perc  
 			allowanceDeductionMstEntity.setStartDate(StringHelperUtils.isNullDate(object[10]));
 			allowanceDeductionMstEntity.setPremiumAmount(StringHelperUtils.isNullDouble(object[11]));
 			allowanceDeductionMstEntity.setCityClass(StringHelperUtils.isNullCharacter(object[12]));
 			allowanceDeductionMstEntity.setMaxBasic(StringHelperUtils.isNullDouble(object[13]));
 			allowanceDeductionMstEntity.setMinBasic(StringHelperUtils.isNullDouble(object[14]));
 			allowanceDeductionMstEntity.setCityGroup(StringHelperUtils.isNullString(object[15]));
 			allowanceDeductionMstEntity.setGradePayHigher(StringHelperUtils.isNullInt(object[16]));
 			allowanceDeductionMstEntity.setGradePayLower(StringHelperUtils.isNullInt(object[17]));
 		
 			allowanceDeductionMstEntity.setDepartmentAllowdeducName(StringHelperUtils.isNullString(object[18]));     //12
 			allowanceDeductionMstEntity.setPayCommisionName(StringHelperUtils.isNullString(object[19]));
 			lstAllowanceDeductionMstEntity.add(allowanceDeductionMstEntity);
		}
		return lstAllowanceDeductionMstEntity;
	}


	@Override
	public int saveAllowanceDeductionRulesMaster(AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity) {
		allowanceDeductionRuleMstEntity.setIsActive('1');
		return allowDeducRuleMasterRepo.saveAllowanceDeductionRulesMaster(allowanceDeductionRuleMstEntity);
		}

	@Override
	public int updateAllowanceDeductionRulesMaster(AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity) {
		AllowanceDeductionRuleMstEntity 	allowanceDeductionRuleMstEntity1=allowDeducRuleMasterRepo.findRuleByRuleId(allowanceDeductionRuleMstEntity.getAllowanceDeductionWiseRuleId());
		if(allowanceDeductionRuleMstEntity1!=null) {
			allowanceDeductionRuleMstEntity1.setStartDate(allowanceDeductionRuleMstEntity.getStartDate());
			allowanceDeductionRuleMstEntity1.setEndDate(allowanceDeductionRuleMstEntity.getEndDate());
			allowanceDeductionRuleMstEntity1.setMinBasic(allowanceDeductionRuleMstEntity.getMinBasic());
			allowanceDeductionRuleMstEntity1.setMaxBasic(allowanceDeductionRuleMstEntity.getMaxBasic());
			allowanceDeductionRuleMstEntity1.setPremiumAmount(allowanceDeductionRuleMstEntity.getPremiumAmount());
			allowanceDeductionRuleMstEntity1.setCityClass(allowanceDeductionRuleMstEntity.getCityClass());
			allowanceDeductionRuleMstEntity1.setGradePayHigher(allowanceDeductionRuleMstEntity.getGradePayHigher());
			allowanceDeductionRuleMstEntity1.setGradePayLower(allowanceDeductionRuleMstEntity.getGradePayLower());
			allowanceDeductionRuleMstEntity1.setCityGroup(allowanceDeductionRuleMstEntity.getCityGroup());
			allowanceDeductionRuleMstEntity1.setAmount(allowanceDeductionRuleMstEntity.getAmount());
			allowanceDeductionRuleMstEntity1.setPercentage(allowanceDeductionRuleMstEntity.getPercentage());
			allowanceDeductionRuleMstEntity1.setPayCommissionCode(allowanceDeductionRuleMstEntity.getPayCommissionCode());
			allowDeducRuleMasterRepo.updateAllowanceDeductionRulesMaster(allowanceDeductionRuleMstEntity1);
		}
		return allowanceDeductionRuleMstEntity1.getAllowanceDeductionWiseRuleId();
		
	}

	@Override
	public AllowanceDeductionRuleMstEntity findRuleByRuleId(int allowanceDeductionWiseRuleId) {
		return allowDeducRuleMasterRepo.findRuleByRuleId(allowanceDeductionWiseRuleId);
	}

	

	@Override
	public AllowanceDeductionRuleMstEntity deleteRule(int id, char status) {
		return allowDeducRuleMasterRepo.deleteRule(id,status);
		}

	@Override
	public AllowanceDeductionRuleMstEntity permanentDeleteRule(int id) {
		return allowDeducRuleMasterRepo.permanentDeleteRule(id);
	}

}
