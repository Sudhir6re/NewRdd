package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.AllowanceDeductionRuleMstEntity;

public interface AllowDeducRuleMasterService {

	List<AllowanceDeductionRuleMstEntity> findAllRules();

	List<AllowanceDeductionRuleMstEntity> findAllRules(int departmentAllowdeducCode);


	int saveAllowanceDeductionRulesMaster(AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity);

	int updateAllowanceDeductionRulesMaster(AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity);

	AllowanceDeductionRuleMstEntity findRuleByRuleId(int allowanceDeductionWiseRuleId);


	AllowanceDeductionRuleMstEntity deleteRule(int id, char status);

	AllowanceDeductionRuleMstEntity permanentDeleteRule(int id);

}
