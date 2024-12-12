package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.HRAAllowanceMstEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;

public interface HRAAllowanceMstRepo {
	public int SaveHRAAllowanceMaster(HRAAllowanceMstEntity hraAllowanceMstEntity);

	public List<MstPayCommissionEntity> getlstddcPayCommission();

	public List<Object[]> getlstAllowanceDeductionMst();

	public int checkComponentAlreadyPresent(HRAAllowanceMstEntity hraAllowanceMstEntity);

	public HRAAllowanceMstEntity findAllowanceDeductionWiseMasterById(int checkIsDataAlreadyPresent);

	public void updateAllowanceDeductionMstEntity(HRAAllowanceMstEntity hRAAllowanceMstEntity);

	public HRAAllowanceMstEntity findEntrybyId(int allowanceHRAId);

	public HRAAllowanceMstEntity findbyhraid(Integer allowanceHRAId);

	public void updateHRAStatus(HRAAllowanceMstEntity hraAllowanceMstEntity);

	public HRAAllowanceMstEntity findAllowanceHRAByIdForReject(int allowanceHRAId);

	public void updateAllowanceHRAStatus(HRAAllowanceMstEntity objCadre);


}
