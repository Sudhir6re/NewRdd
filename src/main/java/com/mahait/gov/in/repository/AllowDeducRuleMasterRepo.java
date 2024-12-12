package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.AllowanceDeductionRuleMstEntity;

public interface AllowDeducRuleMasterRepo {


	List<Object[]> findAllRules(int departmentAllowdeducCode);

	int saveAllowanceDeductionRulesMaster(AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity);

	void updateAllowanceDeductionRulesMaster(AllowanceDeductionRuleMstEntity allowanceDeductionRuleMstEntity);

	AllowanceDeductionRuleMstEntity findRuleByComponentCode(Integer allowanceDeductionWiseRuleId);

	AllowanceDeductionRuleMstEntity findRuleByRuleId(int allowanceDeductionWiseRuleId);


	AllowanceDeductionRuleMstEntity deleteRule(int id, char status);

	AllowanceDeductionRuleMstEntity permanentDeleteRule(int id);


}
