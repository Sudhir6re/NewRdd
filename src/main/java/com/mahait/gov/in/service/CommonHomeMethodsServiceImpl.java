package com.mahait.gov.in.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mahait.gov.in.common.CommonHelper;
import com.mahait.gov.in.common.JsonResponseHelper;
import com.mahait.gov.in.common.StringHelperUtils;
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
import com.mahait.gov.in.model.MstDistrictModel;
import com.mahait.gov.in.model.MstMenuModel;
import com.mahait.gov.in.model.MstMenuRoleMappingModel;
import com.mahait.gov.in.model.MstRoleModel;
import com.mahait.gov.in.model.MstSubMenuModel;
import com.mahait.gov.in.model.TopicModel;
import com.mahait.gov.in.repository.CommonHomeMethodsRepo;
import com.mahait.gov.in.repository.MstRoleRepo;
import com.mahait.gov.in.repository.UserInfoRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Transactional
@Service
public class CommonHomeMethodsServiceImpl implements CommonHomeMethodsService {
	// protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	CommonHomeMethodsRepo commonHomeMethodsRepo;

	@Autowired
	MstRoleRepo mstRoleRepo;

	@Autowired
	private UserInfoRepo userInfoDAO;

	@Cacheable(value = "menus", key = "#levelRoleVal + '_' + #lang")
	@Override
	public List<TopicModel> findMenuNameByRoleID(int levelRoleVal, String lang) {

		List<Object[]> lstprop = commonHomeMethodsRepo.findMenuNameByRoleID(levelRoleVal);
		List<TopicModel> lstMenuObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				TopicModel obj = new TopicModel();
				obj.setKey(StringHelperUtils.isNullInt(objLst[0]));
				if (lang.equals("en")) {
					obj.setMenuName(StringHelperUtils.isNullString(objLst[1]));
				} else {
					obj.setMenuName(StringHelperUtils.isNullString(objLst[2]));
				}
				lstMenuObj.add(obj);
			}
		}
		return lstMenuObj;
	}

	@Cacheable(value = "submenus", key = "#levelRoleVal + '_' + #lang")
	@Override
	public List<TopicModel> findSubMenuByRoleID(int levelRoleVal, String lang) {

		List<Object[]> lstprop = commonHomeMethodsRepo.findSubMenuByRoleID(levelRoleVal);
		List<TopicModel> lstSubMenuObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				TopicModel obj = new TopicModel();
				obj.setKey(StringHelperUtils.isNullInt(objLst[0]));
				obj.setMenuKey(StringHelperUtils.isNullInt(objLst[1]));
				obj.setRoleKey(StringHelperUtils.isNullInt(objLst[2]));
				if (lang.equals("en")) {
					obj.setSubMenuName(StringHelperUtils.isNullString(objLst[3]));
				} else {
					obj.setSubMenuName(StringHelperUtils.isNullString(objLst[4]));
				}

				obj.setControllerName(StringHelperUtils.isNullString(objLst[5]));
				obj.setLinkName(StringHelperUtils.isNullString(objLst[6]));

				lstSubMenuObj.add(obj);
			}
		}
		return lstSubMenuObj;

	}

	@Override
	public List<MstMenuModel> findAllMenu(String language) {

		List<Object[]> lstprop = commonHomeMethodsRepo.findAllMenu();
		List<MstMenuModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstMenuModel obj = new MstMenuModel();
				obj.setMenuId(StringHelperUtils.isNullInt(objLst[0]));
				obj.setMenuCode(StringHelperUtils.isNullInt(objLst[0]));
				obj.setMenuNameEnglish(StringHelperUtils.isNullString(objLst[2]));
				obj.setMenuNameMarathi(StringHelperUtils.isNullString(objLst[3]));
				obj.setIsActive(StringHelperUtils.isNullInt(Integer.parseInt(String.valueOf(objLst[4]))));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<MstRoleModel> findAllRole(String language) {

		List<Object[]> lstprop = commonHomeMethodsRepo.findAllRole();
		List<MstRoleModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstRoleModel obj = new MstRoleModel();
				obj.setKey(StringHelperUtils.isNullInt(objLst[0]));
				obj.setRoleId(StringHelperUtils.isNullInt(objLst[1]));
				obj.setRoleName(StringHelperUtils.isNullString(objLst[2]));
				obj.setRoleDesc(StringHelperUtils.isNullString(objLst[3]));
				obj.setIsActive(StringHelperUtils.isNullInt(Integer.parseInt(String.valueOf(objLst[4]))));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<MstSubMenuModel> findAllSubMenu(String language) {

		List<Object[]> lstprop = commonHomeMethodsRepo.findAllSubMenu();
		List<MstSubMenuModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstSubMenuModel obj = new MstSubMenuModel();
				obj.setSubMenuCode(StringHelperUtils.isNullInt(objLst[0]));
				obj.setSubMenuId(StringHelperUtils.isNullInt(objLst[0])); // key for edit sub menu
				obj.setRoleName(StringHelperUtils.isNullString(objLst[1]));
				obj.setMenuName(StringHelperUtils.isNullString(objLst[2]));
				obj.setSubMenuEnglish(StringHelperUtils.isNullString(objLst[3]));
				obj.setSubMenuMarathi(StringHelperUtils.isNullString(objLst[4]));
				obj.setControllerName(StringHelperUtils.isNullString(objLst[5]));
				obj.setLinkName(StringHelperUtils.isNullString(objLst[6]));
				obj.setIsActive(StringHelperUtils.isNullInt(Integer.parseInt(String.valueOf(objLst[7]))));
				obj.setMenuCode(StringHelperUtils.isNullInt(objLst[8]));
				obj.setRoleId(StringHelperUtils.isNullInt(objLst[9]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<MstMenuRoleMappingModel> findAllMenuRoleMapping(String language) {

		List<Object[]> lstprop = commonHomeMethodsRepo.findAllMenuRoleMapping();
		List<MstMenuRoleMappingModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstMenuRoleMappingModel obj = new MstMenuRoleMappingModel();
				obj.setMenuMapId(StringHelperUtils.isNullInt(objLst[0]));
				if (language.equals("en")) {
					obj.setMenuName(StringHelperUtils.isNullString(objLst[1]));
				} else {
					obj.setMenuName(StringHelperUtils.isNullString(objLst[2]));
				}
				obj.setRoleName(StringHelperUtils.isNullString(objLst[3]));
				obj.setIsActive(StringHelperUtils.isNullInt(Integer.parseInt(String.valueOf(objLst[4]))));
				obj.setMenuCode(StringHelperUtils.isNullInt(objLst[5]));
				obj.setRoleId(StringHelperUtils.isNullInt(objLst[6]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<MstRoleEntity> findAllRole() {
		// TODO Auto-generated method stub
		return mstRoleRepo.findAll();
	}

	@Override
	public MstRoleEntity findRole(int roleId) {
		// TODO Auto-generated method stub
		return userInfoDAO.getRoleByRoleId(roleId);
	}

	public int saveMstRole(MstRoleEntity mstRoleEntity) {
		MstRoleEntity mstRole = new MstRoleEntity();
		int saveId = 0;
		if (mstRoleEntity.getRoleName() != null) {
			mstRole.setRoleName(mstRoleEntity.getRoleName().toUpperCase());
			mstRole.setRoleDescription(mstRoleEntity.getRoleDescription().toUpperCase());
			List<MstRoleEntity> mstroleobj = mstRoleRepo.findAll();
			int role = 0;
			for (MstRoleEntity mstRoleEntity1 : mstroleobj) {
				role = mstRoleEntity1.getRoleId();
			}
			mstRole.setRoleId(role + 1);
			mstRole.setIsActive('1');
			mstRole.setCreatedUserId(1l);
			mstRole.setCreatedDate(new Date());
			saveId = commonHomeMethodsRepo.saveMstRole(mstRole);
		}

		return saveId;
	}

	@Override
	public MstRoleEntity deleteRoleById(int roleId) {
		// TODO Auto-generated method stub
		MstRoleEntity objDeptForReject = commonHomeMethodsRepo.findMstRoleId(roleId);
		if (objDeptForReject != null) {
			objDeptForReject.setIsActive('0'); // REJECTED
			objDeptForReject.setUpdatedDate(new Date());
			commonHomeMethodsRepo.updateMstRoleStatus(objDeptForReject);
		}
		return objDeptForReject;
	}

	@Override
	public String editRoleSave(@Valid MstRoleEntity mstRoleEntity) {
		// TODO Auto-generated method stub

		MstRoleEntity objrole = commonHomeMethodsRepo.findroleById(mstRoleEntity.getRoleId());
		if (objrole != null) {
			// objbank.setBankCode(mstBankEntity.getBankCode());
			objrole.setRoleName(mstRoleEntity.getRoleName());
			objrole.setRoleDescription(mstRoleEntity.getRoleDescription());
			objrole.setIsActive(mstRoleEntity.getIsActive());

			objrole.setUpdatedDate(new Date());
			commonHomeMethodsRepo.updaterole(objrole);
		}
		return "UPDATED";
	}

	@Override
	public List<MstCommonEntity> findCommonMstByCommonCode(String commoncodeStatus) {
		return (List<MstCommonEntity>) commonHomeMethodsRepo.findCommonMstByCommonCode(commoncodeStatus);
	}

	@Override
	public List<MstBankEntity> findBankName() {
		// TODO Auto-generated method stub
		return commonHomeMethodsRepo.findBankName();
	}

	@Override
	public List<MstDistrictModel> lstGetAllDistrict() {

		List<Object[]> lstprop = commonHomeMethodsRepo.lstGetAllDistrict();
		List<MstDistrictModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstDistrictModel obj = new MstDistrictModel();
				obj.setDistrictId(StringHelperUtils.isNullBigInteger(objLst[0]).toString());

				obj.setDistrictName(StringHelperUtils.isNullString(objLst[1]));
				obj.setDistrictCode(StringHelperUtils.isNullString(objLst[2]));

				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	/*
	 * @Override public List<MstTalukaModel> lstGetAllTaluka() { List<Object[]>
	 * lstprop = commonHomeMethodsRepo.lstGetAllTaluka(); List<MstTalukaModel>
	 * lstObj = new ArrayList<>(); if (!lstprop.isEmpty()) { for (Object[] objLst :
	 * lstprop) { MstTalukaModel obj = new MstTalukaModel();
	 * obj.setTalukaId(StringHelperUtils.isNullInt(objLst[0]));
	 * 
	 * obj.setTalukaName(StringHelperUtils.isNullString(objLst[1]));
	 * obj.setTalukaCode(StringHelperUtils.isNullInt(objLst[2]));
	 * 
	 * lstObj.add(obj); } } return lstObj; }
	 * 
	 * @Override public List<MstvillageModel> lstGetAllVillage() { List<Object[]>
	 * lstprop = commonHomeMethodsRepo.lstGetAllVillage(); List<MstvillageModel>
	 * lstObj = new ArrayList<>(); if (!lstprop.isEmpty()) { for (Object[] objLst :
	 * lstprop) { MstvillageModel obj = new MstvillageModel();
	 * obj.setVillageId(StringHelperUtils.isNullInt(objLst[0]));
	 * 
	 * obj.setVillageName(StringHelperUtils.isNullString(objLst[1]));
	 * obj.setVillageCode(StringHelperUtils.isNullInt(objLst[2]));
	 * 
	 * lstObj.add(obj); } } return lstObj; }
	 * 
	 * @Override public List<MstcityModel> lstGetAllCity() { List<Object[]> lstprop
	 * = commonHomeMethodsRepo.lstGetAllCity(); List<MstcityModel> lstObj = new
	 * ArrayList<>(); if (!lstprop.isEmpty()) { for (Object[] objLst : lstprop) {
	 * MstcityModel obj = new MstcityModel();
	 * obj.setCityId(StringHelperUtils.isNullInt(objLst[0]));
	 * 
	 * obj.setCityName(StringHelperUtils.isNullString(objLst[1]));
	 * obj.setCityCode(StringHelperUtils.isNullInt(objLst[2]));
	 * obj.setCityClass(StringHelperUtils.isNullString(objLst[3]));
	 * 
	 * lstObj.add(obj); } } return lstObj; }
	 */
	@Override
	public Date findbillCreateDate(Long billNumber) {
		return commonHomeMethodsRepo.findbillCreateDate(billNumber);
	}
	/*
	 * @Override public List<MstDesnModel> findDesignation(String userName) { //
	 * TODO Auto-generated method stub //return
	 * commonHomeMethodsRepo.findDesignation(userName);
	 * 
	 * List<Object[]> lstprop = commonHomeMethodsRepo.findDesignation();
	 * List<MstDesnModel> lstObj = new ArrayList<>(); if (!lstprop.isEmpty()) { for
	 * (Object[] objLst : lstprop) { MstDesnModel obj = new MstDesnModel();
	 * obj.setDesignationId(Long.valueOf(StringHelperUtils.isNullBigInteger(objLst[0
	 * ]).toString()));
	 * obj.setDesignationCode(Long.valueOf(StringHelperUtils.isNullBigInteger(objLst
	 * [1]).toString()));
	 * obj.setDesignation(StringHelperUtils.isNullString(objLst[2]));
	 * obj.setDesgShortName(StringHelperUtils.isNullString(objLst[3]));
	 * obj.setIsActive(StringHelperUtils.isNullChar(objLst[4])); if(objLst[5]!=null)
	 * {
	 * 
	 * obj.setCadreName(StringHelperUtils.isNullString(objLst[5])); }
	 * lstObj.add(obj); } } return lstObj;
	 * 
	 * }
	 * 
	 * @Override public List<MstDistrictModel> lstGetAllDistrict(String userName) {
	 * 
	 * List<Object[]> lstprop = commonHomeMethodsRepo.lstGetAllDistrict();
	 * List<MstDistrictModel> lstObj = new ArrayList<>(); if (!lstprop.isEmpty()) {
	 * for (Object[] objLst : lstprop) { MstDistrictModel obj = new
	 * MstDistrictModel();
	 * obj.setDistrictId(StringHelperUtils.isNullInt(objLst[0]));
	 * 
	 * obj.setDistrictName(StringHelperUtils.isNullString(objLst[1]));
	 * obj.setDistrictCode(StringHelperUtils.isNullInt(objLst[2]));
	 * 
	 * lstObj.add(obj); } } return lstObj; }
	 * 
	 * @Override public List<MstTalukaModel> lstGetAllTaluka() { List<Object[]>
	 * lstprop = commonHomeMethodsRepo.lstGetAllTaluka(); List<MstTalukaModel>
	 * lstObj = new ArrayList<>(); if (!lstprop.isEmpty()) { for (Object[] objLst :
	 * lstprop) { MstTalukaModel obj = new MstTalukaModel();
	 * obj.setTalukaId(StringHelperUtils.isNullInt(objLst[0]));
	 * 
	 * obj.setTalukaName(StringHelperUtils.isNullString(objLst[1]));
	 * obj.setTalukaCode(StringHelperUtils.isNullInt(objLst[2]));
	 * 
	 * lstObj.add(obj); } } return lstObj; }
	 * 
	 * @Override public List<MstvillageModel> lstGetAllVillage() { List<Object[]>
	 * lstprop = commonHomeMethodsRepo.lstGetAllVillage(); List<MstvillageModel>
	 * lstObj = new ArrayList<>(); if (!lstprop.isEmpty()) { for (Object[] objLst :
	 * lstprop) { MstvillageModel obj = new MstvillageModel();
	 * obj.setVillageId(StringHelperUtils.isNullInt(objLst[0]));
	 * 
	 * obj.setVillageName(StringHelperUtils.isNullString(objLst[1]));
	 * obj.setVillageCode(StringHelperUtils.isNullInt(objLst[2]));
	 * 
	 * lstObj.add(obj); } } return lstObj; }
	 * 
	 * @Override public List<MstcityModel> lstGetAllCity() { List<Object[]> lstprop
	 * = commonHomeMethodsRepo.lstGetAllCity(); List<MstcityModel> lstObj = new
	 * ArrayList<>(); if (!lstprop.isEmpty()) { for (Object[] objLst : lstprop) {
	 * MstcityModel obj = new MstcityModel();
	 * obj.setCityId(StringHelperUtils.isNullInt(objLst[0]));
	 * 
	 * obj.setCityName(StringHelperUtils.isNullString(objLst[1]));
	 * obj.setCityCode(StringHelperUtils.isNullInt(objLst[2]));
	 * obj.setCityClass(StringHelperUtils.isNullString(objLst[3]));
	 * 
	 * lstObj.add(obj); } } return lstObj; }
	 */

	@Override
	public List<Object[]> findAllBankBranchList(int bankCode) {
		List<Object[]> mstBankBranchList = commonHomeMethodsRepo.findAllBankBranchList(bankCode);
		return mstBankBranchList;
	}

	@Override
	public Object getIfscCodeByBranchId(int branchId) {
		// TODO Auto-generated method stub
		return commonHomeMethodsRepo.getIfscCodeByBranchId(branchId);
	}

	@Override
	public Object getBankBranch(String bankId) {
		List<Object[]> mstBankBranchList = commonHomeMethodsRepo.getBankBranch(bankId);
		return mstBankBranchList;
	}

	@Override
	public List<ReligionMstEntity> fetchAllReligions() {
		return commonHomeMethodsRepo.fetchAllReligions();
	}

	@Override
	public List<MstDesnModel> findDesignation(String userName) {
		// TODO Auto-generated method stub
		// return commonHomeMethodsRepo.findDesignation(userName);

		List<Object[]> lstprop = commonHomeMethodsRepo.findDesignation();
		List<MstDesnModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstDesnModel obj = new MstDesnModel();
				obj.setDesignationId(Long.valueOf(StringHelperUtils.isNullBigInteger(objLst[0]).toString()));
				obj.setDesignationCode(Long.valueOf(StringHelperUtils.isNullBigInteger(objLst[1]).toString()));
				obj.setDesignation(StringHelperUtils.isNullString(objLst[2]));
				obj.setDesgShortName(StringHelperUtils.isNullString(objLst[3]));
				obj.setIsActive(StringHelperUtils.isNullChar(objLst[4]));
				if (objLst[5] != null) {

					obj.setCadreName(StringHelperUtils.isNullString(objLst[5]));
				}
				lstObj.add(obj);
			}
		}
		return lstObj;

	}

	@Override
	public List<Object[]> retriveUserdetails(Long userId) {
		return commonHomeMethodsRepo.retriveUserdetails(userId);
	}

	
	@Cacheable(value = "months", key = "'months'")
	@Override
	public List<MstMonthEntity> lstGetAllMonths() {
		return commonHomeMethodsRepo.lstGetAllMonths();
	}

	@Override

	public List<CmnLookupMst> findCommonMstByLookupname(String commoncodeSalutations) {
		// TODO Auto-generated method stub
		List<Object[]> lstprop = null;
		try {
			lstprop = commonHomeMethodsRepo.findLookUpNameDesc(commoncodeSalutations);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<CmnLookupMst> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				CmnLookupMst obj = new CmnLookupMst();
				obj.setLookupId(StringHelperUtils.isNullBigInteger(objLst[0]).longValue());
				obj.setLookupName(StringHelperUtils.isNullString(objLst[1]));
				obj.setLookupDesc(StringHelperUtils.isNullString(objLst[2]));

				lstObj.add(obj);
			}
		}
		return lstObj.stream().distinct().toList();
	}

	
	@Cacheable(value = "years", key = "'years'")
	public List<MstYearEntity> lstGetAllYears() {
		return commonHomeMethodsRepo.lstGetAllYears();
	}

	@Override
	public List<Object[]> findyearinfo(BigInteger yearcurr) {
		return commonHomeMethodsRepo.findyearinfo(yearcurr);
	}

	@Override
	public List<Object[]> findDetailsBillNumber(Long billNumber) {
		return commonHomeMethodsRepo.findDetailsBillNumber(billNumber);
	}

	@Override
	public List<Object[]> findmonthinfo(BigInteger month) {
		return commonHomeMethodsRepo.findmonthinfo(month);
	}

	@Override
	public String getOffice(String userName) {
		return commonHomeMethodsRepo.getOffice(userName);
	}

	@Override
	public List<BillStatusMstEntity> lstGetAllBillStatusForConsolidatePaybill() {
		return commonHomeMethodsRepo.lstGetAllBillStatusForConsolidatePaybill();
	}

	@Override
	public String getBillsForConsolidation(String billStatus, Integer roleId, String userName, int parseInt,
			int parseInt2) {
		CommonHelper helper = new CommonHelper();
		List<Object[]> lstprop = commonHomeMethodsRepo.getBillsForConsolidation(billStatus, roleId, userName, parseInt,
				parseInt2);
		Gson gson = new Gson();
		String str = gson.toJson(helper.getBillsForConsolidationDataHelper(lstprop));
		return JsonResponseHelper.getJSONResponseString(str);

	}

	@Override
	public String findCodeSeq(String DeptAllowDedCode, String DeptAllowDedMst) {
		// TODO Auto-generated method stub
		return commonHomeMethodsRepo.findCodeSeq(DeptAllowDedCode,DeptAllowDedMst);
	}

	@Override
	public List<Object[]> getCityClassByCity(String city) {
		// TODO Auto-generated method stub
		return commonHomeMethodsRepo.getCityClassByCity(city);
	}

	@Override
	public String findbillGrpname(Long billNumber) {
		// TODO Auto-generated method stub
		return commonHomeMethodsRepo.findbillGrpname(billNumber);
	}

	@Override
	public List<MstBankBranchEntity> findbankBranch() {
		// TODO Auto-generated method stub
		return commonHomeMethodsRepo.findbankBranch();
	}

	
	@Override
	public List<CmnLookupMst> getLookupValues(String lookupName, int english) {
		return commonHomeMethodsRepo.getLookupValues(lookupName, english);
	}
}
