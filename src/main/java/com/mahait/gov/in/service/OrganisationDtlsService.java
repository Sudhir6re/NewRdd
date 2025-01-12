package com.mahait.gov.in.service;

import java.util.Map;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.OrganisationDtlsModel;

import jakarta.validation.Valid;

public interface OrganisationDtlsService {
	

	Long SaveorgInstituteInfo(@Valid OrganisationDtlsModel organisationDtlsModel, OrgUserMst messages);

	@Valid OrganisationDtlsModel lstOfficeDetails(String ddoCode);

	
	Map<String, Object> findDataByDistrict(String districtId);

	int updateddoOfficeDetails(OrganisationDtlsModel organisationDtlsModel,OrgUserMst messages);

	String getDeptNameByLocCode(String deptLocCode, String language);


}
