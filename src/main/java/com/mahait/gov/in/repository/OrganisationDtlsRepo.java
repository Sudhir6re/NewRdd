package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.DdoOffice;

public interface OrganisationDtlsRepo {
	
	DdoOffice lstGetOfficeDtls(String userName);

	Long saveorgInstInfo(DdoOffice objForSave);

	void updateorgInstituteInfo(DdoOffice objForSave);

	List findtalukalist(String districtId);

	List findcitylist(String districtId);

	void updateddoOfficeDetails(DdoOffice lstprop);


}
