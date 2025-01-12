package com.mahait.gov.in.repository;

import java.util.List;

import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgUserMst;

public interface CreateAdminOfficeRepo {

	List<Object[]> getAllDDOOfficeDtlsData(String districtName, String talukaNametName, String adminType);

	List<Object[]> findPostLocationByDdoCode(String ddoCode);

	List getAdminOfcCode(String adminOfc);

	List getCountofDDOCode(String createdDDOCode);

	List getTreasuryName(String dDOCode);

	List getSubTreasury(Long valueOf);
	
	public int getUniqeInstituteIdCount(String strDdoCode) ;

	List<Object[]> retriveDisctOfcList(OrgUserMst messages, String ofcId);
	
	public List<Object[]> retrieveDistrictOfficeList(OrgUserMst messages, Long ofcId);

	List<MstDesignationEntity> findByDsgnNameIgnoreCaseContaining(String desgn);

	List<Object[]> lstAllDepartment();

	List<Object[]> employeeMappingList(String logUserId);

	int ddoCodeAlreadyExists(String level1DdoCode);

	List<Object[]> findDeptByDistOfcCode(String distOfcId);

	String findLevel3DdoCode(String distOfcId, String reptDdoCode);


}
