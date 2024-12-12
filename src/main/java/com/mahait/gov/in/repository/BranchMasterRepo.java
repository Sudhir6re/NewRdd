package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import com.mahait.gov.in.entity.MstBankBranchEntity;

import jakarta.validation.Valid;

public interface BranchMasterRepo {

	List<MstBankBranchEntity> listOfBranch();

	int saveBankBranch(@Valid MstBankBranchEntity mstBankBranchEntity);

	MstBankBranchEntity findBankBranchById(Long bankBranchId);

	Serializable updateBankBranch(MstBankBranchEntity brachobject);

	List<Long> validateIFSCCode(Integer bankcode, String ifscCode);

}
