package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.InstituteType;
import com.mahait.gov.in.model.OrgDdoMstModel;

import jakarta.validation.Valid;


public interface OrganizationInstInfoService {
	

	public OrgDdoMstModel findDDOInfo(String userName);

	public List<InstituteType> lstInstType();

	public List<Object[]> getBankBranch(String valueOf);

	public Long SaveorgInstituteInfo(@Valid OrgDdoMstModel orgDdoMstModel);

	public int updateorgInstituteInfo(OrgDdoMstModel orgDdoMstModel);
	 
}
