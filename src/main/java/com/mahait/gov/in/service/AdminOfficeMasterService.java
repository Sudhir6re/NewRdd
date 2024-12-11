package com.mahait.gov.in.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpAdminOfficeMst;
import com.mahait.gov.in.model.ZpAdminOfficeMstModel;

public interface AdminOfficeMasterService {

	List<ZpAdminOfficeMstModel> findAllZpAdminOfficeMstModel();

	ZpAdminOfficeMst createOffice(ZpAdminOfficeMstModel zpAdminOfficeMstModel);

	ZpAdminOfficeMstModel findOfficebyId(Long officeId);

	Integer deleteOfficeById(Long officeId, OrgUserMst messages);

	String generateOfficeCode();

	boolean officeNameExists(String officeName);

}
