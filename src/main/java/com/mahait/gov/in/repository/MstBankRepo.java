package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.MstBankPay;

public interface MstBankRepo {

	List<MstBankEntity> lstAllBank();
	
 public List<Object[]> findAllBankBranchList(int bankCode);
	 
	 public int saveBank(MstBankEntity mstBankEntity);
	 
	 public void deleteBankById(MstBankEntity mstBankEntity);
	 
	 public MstBankEntity findBankById(int bankId);

	public void updatebankStatus(MstBankEntity objbank);

	public List<Long> validateBankName(String bankname);

}
