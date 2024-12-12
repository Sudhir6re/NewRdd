package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.List;

import com.mahait.gov.in.entity.MstCadreEntity;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;

public interface MstDesignationRepo {

	public int saveDesignationMst(MstDesignationEntity mstDesignationEntity);
//	public List<Object[]> getCadreDesc(Integer fieldDepartmrntID);
	public List<MstPayCommissionEntity> findAllPayCommission();
	public List<Object[]> getDesignationMstData();
	public List<Object[]> getCadre();
	public MstDesignationEntity findMstDesgByDesgId(Long designationId);
	
	public void updateDesginationStatus(MstDesignationEntity objDesg);
	public List<Long> validateDesignationName(String desgname);
	
}
