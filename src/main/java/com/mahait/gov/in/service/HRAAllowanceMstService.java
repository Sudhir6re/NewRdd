package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.HRAAllowanceMstEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;

public interface HRAAllowanceMstService {
	
	public int SaveHRAAllowanceMaster(HRAAllowanceMstEntity hraAllowanceMstEntity);

	public List<MstPayCommissionEntity> getlstddcPayCommission();

	public List<HRAAllowanceMstEntity> getlstAllowanceDeductionMst();

	public int checkComponentAlreadyPresent(HRAAllowanceMstEntity hraAllowanceMstEntity);

	public HRAAllowanceMstEntity findAllowanceDeductionWiseMasterById(int checkIsDataAlreadyPresent);

	public void updateRRAAllowanceMstEntity(HRAAllowanceMstEntity hRAAllowanceMstEntity);

	public HRAAllowanceMstEntity findEntrybyId(int allowanceHRAId);

	public String hRAAllowanceMstUpdate(HRAAllowanceMstEntity hraAllowanceMstEntity);

	public HRAAllowanceMstEntity findAllowanceHRAByIdForReject(int allowanceHRAId);
	



	

}
