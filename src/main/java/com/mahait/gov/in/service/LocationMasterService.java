package com.mahait.gov.in.service;

import java.util.List;


public interface LocationMasterService {
	
	
	public List<Object[]> findAllStates(int countryId);

	public List<Object[]> findAllDistricts(long stateId);
	
//	public List<Object[]> findAllDistricts(int stateId);
//	
//	public List<Object[]> findAllTaluka(int districtId,int stateId);
//	
//	public List<Object[]> findAllSubCorporation(int parentFieldDepartmentId);

}
