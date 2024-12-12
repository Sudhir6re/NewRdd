package com.mahait.gov.in.nps.repository;

import java.util.List;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

public interface NpsLegacyFileGenerationRepo {

	List<Object[]> findLegacyEmployeeList(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages, Long locId);

	String getBatchId(String locId);

	List<Object[]> findDdoWiseTotalAmnt(String locId, String treasuryyno, String strDDOCode);

	String getEmployeeRecordCountDdoregNsdl(String locId, String treasuryyno, String ddoRegNo);

	List<Object[]> getEmployeeListDdoregNsdl(String locId, String treasuryyno, String ddoRegNo);

	List<Object[]> findDtoRegWiseAmnt(String locId, String treasuryyno, String ddoRegNo);


}
