package com.mahait.gov.in.nps.service;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

public interface NpsLegacyFileGenerationService {

	List<DcpsLegacyModel> findLegacyEmployeeList(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages,
			String locationId);

	String findLocId(String string);

	String getBatchId(String locId);


	List<Object[]> findDdoWiseTotalAmnt(String locId, String treasuryyno, String strDDOCode);


	List<Object[]> getEmployeeListDdoregNsdl(String locId, String treasuryyno, String ddoRegNo);

	String getEmployeeRecordCountDdoregNsdl(String locId, String treasuryyno, String ddoRegNo);

	List<Object[]> findDtoRegWiseAmnt(String locId, String treasuryyno, String ddoRegNo);



}
