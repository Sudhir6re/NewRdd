package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.mahait.gov.in.entity.BillStatusMstEntity;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.MstBankBranchEntity;
import com.mahait.gov.in.entity.MstBankEntity;
import com.mahait.gov.in.entity.MstCommonEntity;
import com.mahait.gov.in.entity.MstMonthEntity;
import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.MstYearEntity;
import com.mahait.gov.in.entity.ReligionMstEntity;
import com.mahait.gov.in.model.MstDesnModel;

public interface CommonHomeMethodsRepo {


	public List<Object[]> findRoleLevelMstList();

	public List<Object[]> findMenuNameByRoleID(int levelRoleVal);

	public List<Object[]> findSubMenuByRoleID(int levelRoleVal);

	public List<Object[]> findAllMenu();

	public List<Object[]> findAllRole();

	public List<Object[]> findAllSubMenu();

	public List<Object[]> findAllMenuRoleMapping();																						
						
	public int saveMstRole(MstRoleEntity mstRoleEntity);


	public MstRoleEntity findMstRoleId(int roleId);

	public void updateMstRoleStatus(MstRoleEntity objDeptForReject);


	public MstRoleEntity findroleById(Integer roleId);

	public void updaterole(MstRoleEntity objrole);

	
	public List<MstCommonEntity> findCommonMstByCommonCode(String commoncodeStatus);

	public List<MstBankEntity> findBankName();

	public List<Object[]> findDesignation();



	public List<Object[]> lstGetAllTaluka();

	public List<Object[]> lstGetAllVillage();

	public List<Object[]> lstGetAllCity();

	public List<Object[]> lstGetAllDistrict();

	public List<Object[]> findAllBankBranchList(int bankCode);

	public Object getIfscCodeByBranchId(int branchId);

	public List<Object[]> getBankBranch(String bankId);

	public List<Object[]> retriveUserdetails(Long userId);

	public List<ReligionMstEntity> fetchAllReligions();

	public List<Object[]> findLookUpNameDesc(String commoncodeSalutations);

	public List<MstDesnModel> findDesignation(String userName);
	
	public List<MstMonthEntity> lstGetAllMonths();

	public List<MstYearEntity> lstGetAllYears();

	public Date findbillCreateDate(Long billNumber);

	public List<Object[]> findyearinfo(BigInteger yearcurr);

	public List<Object[]> findDetailsBillNumber(Long billNumber);

	public List<Object[]> findmonthinfo(BigInteger month);

	public String getOffice(String userName);

	public List<BillStatusMstEntity> lstGetAllBillStatusForConsolidatePaybill();

	public List<Object[]> getBillsForConsolidation(String billStatus, Integer roleId, String userName, int parseInt,
			int parseInt2);

	public String findCodeSeq(String deptAllowDedCode, String deptAllowDedMst);

	public List<Object[]> getCityClassByCity(String city);

	public String findbillGrpname(Long billNumber);

	public List<MstBankBranchEntity> findbankBranch();

	public List<CmnLookupMst> getLookupValues(String lookupName, int english);
	
}
