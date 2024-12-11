package com.mahait.gov.in.service;

import java.util.List;
import java.util.Map;

import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpAdminNameMst;
import com.mahait.gov.in.model.ZpRltDdoMapModel;

public interface CreateAdminOfficeService {

	List<ZpRltDdoMapModel> findAllDdoMappedlist(OrgUserMst messages);

	List<ZpAdminNameMst> fetchAllOfficeList(OrgUserMst messages);

	List<CmnTalukaMst> findAllTalukaList(OrgUserMst messages);

	List<CmnDistrictMst> findAllDistrictList(OrgUserMst messages);

	List<Object[]> findZpRltDtls(OrgUserMst messages, String districtName, String talukaNametName, String adminType);

	String saveCreateAdminOffice(ZpRltDdoMapModel zpRltDdoMapModel, OrgUserMst messages);

	List<CmnTalukaMst> getAllTalukaByDistrictId(Long districtId);

	List<Object[]> retriveDisctOfcList(OrgUserMst messages, String ofcId);

	List<Object[]> fetchDdoDetails(OrgUserMst messages, String ddoCode);

	String generateDDOCode(String cmbAdminOffice, String cmbSubTreasury);

	Map<String, Object> findTrasuryDetails(String ddoCode);

	int getUniqeInstituteIdCount(String DDOCode);

	List<MstDesignationEntity> findDesignation(String desgn);

	 public List<Object[]> lstAllDepartment();

	List<Object[]> employeeMappingList(String userName);

}
