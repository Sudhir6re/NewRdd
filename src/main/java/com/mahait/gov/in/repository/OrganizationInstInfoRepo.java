package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.InstituteType;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.model.OrgDdoMstModel;

public interface OrganizationInstInfoRepo {


	public OrgDdoMst findDDOInfo(String userName);

	public List<InstituteType> lstInstType();

	public List<Object[]> getBankBranch(String valueOf);

	public int saveorgInstituteInfo(OrgDdoMst objForSave);

	public void updateorgInstituteInfo(OrgDdoMst objForSave);

	public String getDeptNameByLocCode(String deptLocCode, String language);
	
	

}
