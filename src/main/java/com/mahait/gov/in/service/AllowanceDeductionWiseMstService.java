package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.AllowanceDeductionMstEntity;
import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;

public interface AllowanceDeductionWiseMstService {

	public int saveAllowanceDeductionWiseMaster(AllowanceDeductionMstEntity allowanceDeductionMstEntity);

	public List<DeptEligibilityForAllowAndDeductEntity> fetchLstDeptEligibilityForAllowAndDeductEntityByType(int isType);

	public List<AllowanceDeductionMstEntity> getAllallowanceDeductionWiseMst();

	public int isAllowanceDeductionWiseMasterDataPresent(AllowanceDeductionMstEntity allowanceDeductionMstEntity);

	public AllowanceDeductionMstEntity findAllowanceDeductionWiseMasterById(int id);

	public AllowanceDeductionMstEntity deleteAllowanceDeductionWiseMasterById(int id, char status);

	public int checkComponentAlreadyPresent(AllowanceDeductionMstEntity allowanceDeductionMstEntity);

	public void updateAllowanceDeductionMstEntity(AllowanceDeductionMstEntity allowanceDeductionMstEntity1);

	public List<Long> validateAllowDeduct(String allowdeductName, Integer allowdeductCode);

}
