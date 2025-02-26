package com.mahait.gov.in.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.CmnTalukaMst;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpAdminNameMst;
import com.mahait.gov.in.mapper.ZpRltDdoMapMapper;
import com.mahait.gov.in.model.ZpRltDdoMapModel;
import com.mahait.gov.in.repository.AddNewDDOConfigRepository;
import com.mahait.gov.in.repository.CmnDistrictMstRepository;
import com.mahait.gov.in.repository.CmnLocationMstRepository;
import com.mahait.gov.in.repository.CmnTalukaMstRepository;
import com.mahait.gov.in.repository.CreateAdminOfficeRepo;
import com.mahait.gov.in.repository.OrgDdoMstRepository;
import com.mahait.gov.in.repository.OrgPostMstRepository;
import com.mahait.gov.in.repository.UserInfoRepo;
import com.mahait.gov.in.repository.ZpAdminNameMstRepository;
import com.mahait.gov.in.repository.ZpRltDdoMapRepository;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class CreateAdminOfficeServiceImpl implements CreateAdminOfficeService {

	@Autowired
	CmnTalukaMstRepository cmnTalukaMstRepository;

	@Autowired
	CmnDistrictMstRepository cmnDistrictMstRepository;

	@Autowired
	ZpRltDdoMapRepository zpRltDdoMapRepository;

	@Autowired
	ZpAdminNameMstRepository zpAdminNameMstRepository;

	@Autowired
	ZpRltDdoMapMapper zpRltDdoMapMapper;

	@Autowired
	CreateAdminOfficeRepo createAdminOfficeRepo;

	@Autowired
	OrgDdoMstRepository orgDdoMstRepository;

	@Autowired
	CmnLocationMstRepository cmnLocationMstRepository;

	@Autowired
	UserInfoRepo userInfoRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	AddNewDDOConfigRepository objZpDDOOfficeMstDAOImpl;

	@Autowired
	OrgPostMstRepository orgPostMstRepository;

	@Override
	public List<ZpRltDdoMapModel> findAllDdoMappedlist(OrgUserMst messages) {
		// TODO Auto-generated method stub
		return zpRltDdoMapMapper.convertEntityListToModelList(zpRltDdoMapRepository.findAll());
	}

	@Override
	public List<ZpAdminNameMst> fetchAllOfficeList(OrgUserMst messages) {
		List<String> adminCodes = Arrays.asList("06", "07", "19", "20", "31");
		return zpAdminNameMstRepository.findByAdminCodeInOrderByAdminCode(adminCodes);
	}

	@Override
	public List<CmnTalukaMst> findAllTalukaList(OrgUserMst messages) {
		// TODO Auto-generated method stub
		return cmnTalukaMstRepository.findAll();
	}

	@Override
	public List<CmnDistrictMst> findAllDistrictList(OrgUserMst messages) {
		// TODO Auto-generated method stub
		return cmnDistrictMstRepository.findByLangIdAndStateIdOrderByDistrictName(1, 15l);
	}

	@Override
	public List<Object[]> findZpRltDtls(OrgUserMst messages, String districtName, String talukaNametName,
			String adminType) {
		return createAdminOfficeRepo.getAllDDOOfficeDtlsData(districtName, talukaNametName, adminType);
	}

	@Override
	public String saveCreateAdminOffice(ZpRltDdoMapModel zpRltDdoMapModel, OrgUserMst messages) {

		Long gLngPostId = messages.getUserId();
		Long gLngUserId = messages.getUserId();
		String strAdminOfc = zpRltDdoMapModel.getCmbAdminOffice();

		String lLngFieldDept = null;

		String strDistCode = zpRltDdoMapModel.getCmbDistOffice();
		String strDeptCode = zpRltDdoMapModel.getCmbAdminOffice();
		String strRepoDDOCode = zpRltDdoMapModel.getTxtRepDDOCode();
		String lStrDdoName = zpRltDdoMapModel.getTxtDDOName();
		String lstrFinalDDOCode = zpRltDdoMapModel.getTxtFinalDDOCode();
		String lstrSpecialDDOCode = zpRltDdoMapModel.getTxtSpecialDDOCode();
		String lstrLevel = zpRltDdoMapModel.getRadioFinalLevel();
		String lStrDdoPersonalName = zpRltDdoMapModel.getTxtDDOName();

		Long desginationId = zpRltDdoMapModel.getDesginationId();
		Long lLngDesignID = desginationId; // Long.parseLong(StringUtility.getParameter("1", request).trim());// TODO --
											// It will Change in future
		Long lLngAdminDept = 0l;

		lLngAdminDept = zpRltDdoMapModel.getDept();

		String lLngDistrictCode = zpRltDdoMapModel.getCmbDistOffice();

		String lStrGender = zpRltDdoMapModel.getRadioGender();
		String strTOName = zpRltDdoMapModel.getTxtTreasuryName();
		String lLngTreasuryCode = zpRltDdoMapModel.getTxtTreasuryCode();
		String strSubTOName = zpRltDdoMapModel.getCmbSubTreasury();
		String lStrDesgnName = zpRltDdoMapModel.getTxtDDODsgn();
		String lStrDdoOfficeName = zpRltDdoMapModel.getTxtOfficeName();
		lStrDdoName = lStrDdoOfficeName;
		String lStrDdoCode = zpRltDdoMapModel.getTxtDDOCode();
		lStrDdoCode = lStrDdoCode;

		lStrDdoCode = lStrDdoCode;
		String mobNo = zpRltDdoMapModel.getTxtMobileNo();
		String email = zpRltDdoMapModel.getTxtEmailId();
		Long lLngLocPin = 1l;// Long.parseLong(StringUtility.getParameter("1", request).trim());// TODO --
								// Need Change Temporary "1" is added.

		List<OrgDdoMst> ParentDtls1 = orgDdoMstRepository.findAllByDdoCode(strRepoDDOCode);

		String lstrHOD_Code1 = ParentDtls1.get(0).getDeptLocCode();
		lLngFieldDept = ParentDtls1.get(0).getHodLocCode();

		CmnLocationMst cmnLocationMst = objZpDDOOfficeMstDAOImpl.insertLocation(lStrDdoOfficeName, gLngUserId,
				gLngPostId, Long.valueOf(lLngFieldDept), String.valueOf(lLngLocPin), messages, strDistCode);

		String lStrLocCode = cmnLocationMst.getLocId().toString();

		OrgUserMst orgUserMst = objZpDDOOfficeMstDAOImpl.insertUserMst(lStrDdoCode, gLngUserId, gLngPostId, messages);

		Long lLngUserId = orgUserMst.getUserId();
		Long lLngPostId = orgPostMstRepository.findMaxPostId() + 1; // orgUserMst.getUserId();//o

		// objZpDDOOfficeMstDAOImpl.insertEmpMst(lLngUserId, lStrDdoPersonalName,
		// gLngUserId, gLngPostId, lStrGender, messages,mobNo,email);

		OrgPostMst newOrgPostMst = objZpDDOOfficeMstDAOImpl.insertOrgPostMst(lLngPostId, lStrLocCode, gLngUserId,
				gLngPostId, lLngDesignID.toString(), messages);

		objZpDDOOfficeMstDAOImpl.insertPostDtlsRlt(lStrLocCode, lLngPostId, lStrDesgnName, lLngDesignID, gLngUserId,
				gLngPostId, messages, newOrgPostMst, cmnLocationMst);

		// lObjAddNewDdoConfig.insertPostRoleRlt(lLngPostId, gLngUserId, gLngPostId,
		// objectArgs,"DDO");
		// objZpDDOOfficeMstDAOImpl.insertPostRoleRlt(lLngPostId, gLngUserId,
		// gLngPostId, messages,"DDO",newOrgPostMst);

		objZpDDOOfficeMstDAOImpl.insertUserPostRlt(lLngPostId, lLngUserId, gLngUserId, gLngPostId, messages,
				newOrgPostMst, orgUserMst);

		String lstrDdoType = "";
		// List ofcCode=objZpDDOOfficeMstDAOImpl.retirveDdoType(strAdminOfc);
		lstrDdoType = strAdminOfc;// ofcCode.get(0).toString();//Note : Column Added in ORG_DDO_MST-DDOType

		List ParentDtls = objZpDDOOfficeMstDAOImpl.RetirveParentdtlfrmReptCode(strRepoDDOCode);

		String tmp1[] = ParentDtls.get(0).toString().split("##");
		String lstrDept_Code = tmp1[0].toString();
		String lstrHOD_Code = tmp1[1].toString();

		List DeptCode = objZpDDOOfficeMstDAOImpl.retriveDepts(zpRltDdoMapModel.getDept());
		String lstrDeptType = null;
		if (DeptCode != null && DeptCode.size() > 0)
			lstrDeptType = DeptCode.get(0).toString();

		String lstrAdminDeptType = null;
		objZpDDOOfficeMstDAOImpl.insertOrgDdoMst(lStrDdoCode, lStrDdoName, lStrDdoPersonalName, lLngPostId, gLngUserId,
				lStrLocCode, gLngPostId, lLngAdminDept.toString(), lstrDdoType, lstrDept_Code, lstrHOD_Code,
				lstrDeptType, messages, desginationId, lStrDesgnName, lStrDdoOfficeName);

		// String
		// uniqeInstituteId=objZpDDOOfficeMstDAOImpl.generateUniqeInstituteId(lStrDdoCode,lLngDistrictCode.toString(),
		// messages);
		objZpDDOOfficeMstDAOImpl.insertMstDcpsDdoOffice(lStrDdoCode, lStrDdoOfficeName, lLngDistrictCode.toString(),
				Long.parseLong(lStrLocCode), gLngUserId, gLngPostId, messages, mobNo, email);

		objZpDDOOfficeMstDAOImpl.insertRltZpDdoMap(lLngUserId, gLngPostId, 0l, 0l, lStrDdoCode, strRepoDDOCode,
				lstrFinalDDOCode, lstrSpecialDDOCode, lstrLevel, gLngUserId, gLngPostId, messages);

		try {
			objZpDDOOfficeMstDAOImpl.insertRltDdoOrg(gLngUserId, gLngPostId, lStrDdoCode, lLngTreasuryCode.toString(),
					messages);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	@Override
	public List<CmnTalukaMst> getAllTalukaByDistrictId(Long districtId) {
		return cmnTalukaMstRepository.findByDistrictIdOrderByTalukaName(districtId);
	}

	@Override
	public List<Object[]> retriveDisctOfcList(OrgUserMst messages, String ofcId) {
		// TODO Auto-generated method stub
		return createAdminOfficeRepo.retriveDisctOfcList(messages, ofcId);
	}

	@Override
	public List<Object[]> fetchDdoDetails(OrgUserMst messages, String ddoCode) {
		// TODO Auto-generated method stub
		List<Object[]> lst = createAdminOfficeRepo.findPostLocationByDdoCode(ddoCode);
		for (Object[] obj : lst) {
			if (obj[0] instanceof String) {
				String firstElement = (String) obj[0];
				if (firstElement.equals("1;") || firstElement.equals("1")) {
					String dsgDtl = "1";
					Long dsg = Long.valueOf(dsgDtl);
					dsgDtl = (dsg <= 2) ? "BEO" : "Superintendent";
					obj[0] = dsgDtl;
				}
			}
		}
		return lst;
	}

	public String generateDDOCode(String AdminOfc, String SubTOCode) {
		boolean foundUniqueCode = false;
		int attempt = 1;
		String FinalpreFixed = "";
		do {
			String CreatedDDOCode = AdminOfc;
			CreatedDDOCode += SubTOCode;
			List getCountCode = createAdminOfficeRepo.getCountofDDOCode(CreatedDDOCode);
			// String FinalpreFixed = "";
			String suffix = "";
			String midfix = "";
			if (getCountCode.get(0) != null) {
				Long temp = Long.parseLong(getCountCode.get(0).toString()) + attempt;
				suffix = temp.toString();
			}
			if (suffix.length() == 1)
				midfix = "0000";
			else if (suffix.length() == 2)
				midfix = "000";
			else if (suffix.length() == 3)
				midfix = "00";
			else if (suffix.length() == 4)
				midfix = "0";

			FinalpreFixed = CreatedDDOCode + midfix + suffix;
			if (ddoCodeAlreadyExists(FinalpreFixed) == 0) {
				foundUniqueCode = true;
			} else {
				attempt++;
			}
		} while (!foundUniqueCode);
		return FinalpreFixed;
	}

	@Override
	public Map<String, Object> findTrasuryDetails(String DDOCode) {
		Map<String, Object> response = new HashMap<>();

		try {
			/*
			 * Long TDDOCode = 0l; if (!DDOCode.equalsIgnoreCase("")) { TDDOCode =
			 * Long.valueOf(DDOCode); }
			 */

			List RepoDDO = orgDdoMstRepository.findByDdoCodeLike(DDOCode);
			List TODetail = createAdminOfficeRepo.getTreasuryName(DDOCode);

			if (TODetail.size() > 0 && TODetail != null) {
				Object[] o = (Object[]) TODetail.get(0);
				String TOName = o[1] != null ? o[1].toString() : "";
				String TOCode = o[0] != null ? o[0].toString() : "";

				List lstSubTO = createAdminOfficeRepo.getSubTreasury(Long.valueOf(TOCode));

				response.put("trasuryDetails", TODetail);
				response.put("subTreasuryList", lstSubTO);
				return response;
			} else {
				return response;
			}
		} catch (DataIntegrityViolationException e) {

			e.printStackTrace();

			Throwable cause = e.getCause();
			if (cause instanceof SQLException) {
				SQLException sqlException = (SQLException) cause;

				System.err.println("SQLState: " + sqlException.getSQLState());
				System.err.println("Error Code: " + sqlException.getErrorCode());
				System.err.println("Message: " + sqlException.getMessage());

				sqlException.printStackTrace();
			}

			System.out.println("DataIntegrityViolationException occurred" + e);
		}
		return response;
	}

	@Override
	public int getUniqeInstituteIdCount(String DDOCode) {
		return createAdminOfficeRepo.getUniqeInstituteIdCount(DDOCode);
	}

	@Override
	public List<MstDesignationEntity> findDesignation(String desgn) {
		// TODO Auto-generated method stub
		return createAdminOfficeRepo.findByDsgnNameIgnoreCaseContaining(desgn);
	}

	@Override
	public List<Object[]> lstAllDepartment() {
		return createAdminOfficeRepo.lstAllDepartment();
	}

	@Override
	public List<Object[]> employeeMappingList(String logUserId) {
		return createAdminOfficeRepo.employeeMappingList(logUserId);
	}

	private int ddoCodeAlreadyExists(String level1DdoCode) {
		return createAdminOfficeRepo.ddoCodeAlreadyExists(level1DdoCode);
	}

	@Override
	public List<Object[]> findDeptByDistOfcCode(String distOfcId) {
		return createAdminOfficeRepo.findDeptByDistOfcCode(distOfcId);
	}

	@Override
	public String findLevel3DdoCode(String distOfcId, String reptDdoCode) {
		return createAdminOfficeRepo.findLevel3DdoCode(distOfcId, reptDdoCode);
	}

	@Override
	public Long isValidLevel2Ddo(String reptDdoCode) {
		return createAdminOfficeRepo.isValidLevel2Ddo(reptDdoCode);
	}

	@Override
	public Long isValidLevel3Ddo(String finalDdoCode) {
		return createAdminOfficeRepo.isValidLevel3Ddo(finalDdoCode);
	}

	@Override
	public Long validateMobNo(String mobNo, OrgUserMst messages) {
		return createAdminOfficeRepo.validateMobNo(mobNo);
	}

	@Override
	public Long validateEmailAdd(String email, OrgUserMst messages) {
		return createAdminOfficeRepo.validateEmailAdd(email);

	}
}
