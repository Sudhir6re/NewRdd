package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.AllowanceDeductionMstEntity;

public interface AllowanceDeductionWiseMstRepo {

	public int saveAllowanceDeductionWiseMaster(AllowanceDeductionMstEntity allowanceDeductionMstEntity);

	public List<Object[]> fetchLstDeptEligibilityForAllowAndDeductEntityByType(
			int isType);

	public List<Object[]> getAllallowanceDeductionWiseMst();

	public List<Object[]> isAllowanceDeductionWiseMasterDataPresent(AllowanceDeductionMstEntity allowanceDeductionMstEntity);

	public AllowanceDeductionMstEntity findAllowanceDeductionWiseMasterById(int id);

	public AllowanceDeductionMstEntity deleteAllowanceDeductionWiseMasterById(int id, char status);

	public int checkComponentAlreadyPresent(AllowanceDeductionMstEntity allowanceDeductionMstEntity);

	public void updateAllowanceDeductionMstEntity(AllowanceDeductionMstEntity allowanceDeductionMstEntity1);

	public List<Long> validateAllowDeduct(String allowdeductName, Integer isType);
}
