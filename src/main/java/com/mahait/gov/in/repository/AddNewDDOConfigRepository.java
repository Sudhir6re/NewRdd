package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.CmnLanguageMst;
import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgGradeMst;
import com.mahait.gov.in.entity.OrgPostDetailsRlt;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.OrgUserpostRlt;
import com.mahait.gov.in.entity.RltDdoOrg;
import com.mahait.gov.in.entity.ZpRltDdoMap;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Transactional
@Repository
public class AddNewDDOConfigRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	CmnLanguageMstRepository cmnLanguageMstRepository;

	@Autowired
	CmnLookupMstRepository cmnLookupMstRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	CmnLocationMstRepository cmnLocationMstRepository;

	@Autowired
	OrgGradeMstRepository orgGradeMstRepository;

	@Autowired
	UserInfoRepoImpl userInfoRepoImpl;

	@Autowired
	OrgPostMstRepository orgPostMstRepository;

	@Autowired
	MstDesignationRepository orgDesignationMstRepository;

	@Autowired
	ZpRltDdoMapRepository zpRltDdoMapRepository;

	@Autowired
	MstRoleRepo mstRoleRepo;

	private Serializable save;

	public List<Object[]> getAllAdminDepartment() {
		Session ghibSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("SELECT locId, locName FROM CmnLocationMst \n");
		lSBQuery.append("WHERE departmentId = 100001");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		return lQuery.getResultList(); // Updated to use getResultList()
	}

	public List getAllFieldDepartment() {
		Session hibSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("SELECT locId, locName FROM CmnLocationMst \n");
		lSBQuery.append("WHERE departmentId = 100011");
		Query lQuery = hibSession.createQuery(lSBQuery.toString());
		return lQuery.getResultList();

	}

	public List getAllTreasury() {
		Session ghibSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("SELECT locId, locName FROM CmnLocationMst \n");
		lSBQuery.append("WHERE departmentId in (100003,100006) ORDER BY locName ");
		Query lQuery = ghibSession.createQuery(lSBQuery.toString());
		return lQuery.getResultList();

	}

	public String chkDdoCode(String lStrDdoCode) {
		Session hibSession = entityManager.unwrap(Session.class);
		String lStrResData = "";
		List lLstResData = null;

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("FROM OrgDdoMst WHERE ddoCode=:ddoCode \n");
		Query lQuery = hibSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("ddoCode", lStrDdoCode);
		lLstResData = lQuery.getResultList();

		if (lLstResData.size() > 0) {
			lStrResData = "Y";
		} else {
			lStrResData = "N";
		}

		return lStrResData;
	}

	public CmnLocationMst insertLocation(String lStrLocationName, Long lLngUserIdCrtd, Long lLngPostIdCrtd,
			Long lLngFieldDeptId, String lStrLocPin, OrgUserMst orgUserMst, String strDistCode) {
		Long lLngLocId = null;

		Session ghibSession = entityManager.unwrap(Session.class);
		CmnLocationMst lObjCmnLocationMst = new CmnLocationMst();

		CmnLanguageMst lObjCmnLanguageMst = cmnLanguageMstRepository.findByLangId(1l);

		CmnLookupMst lObjCmnLookupMst = cmnLookupMstRepository.findByLookupId(1l);

		lLngLocId = getNextSeqNoLoc();

		// lObjCmnLocationMst.setLocId(lLngLocId);

		lObjCmnLocationMst.setLocName(lStrLocationName);

		if (lStrLocationName.length() >= 15) {
			lObjCmnLocationMst.setLocShortName(lStrLocationName.substring(0, 15));
		} else {
			lObjCmnLocationMst.setLocShortName(".");
		}
		lObjCmnLocationMst.setCmnLanguageMst(lObjCmnLanguageMst);
		lObjCmnLocationMst.setDepartmentId(100007l);
		lObjCmnLocationMst.setParentLocId(lLngFieldDeptId);
		lObjCmnLocationMst.setLocPin("1");
		lObjCmnLocationMst.setCmnLookupMst(lObjCmnLookupMst);
		lObjCmnLocationMst.setStartDate(new Date());
		lObjCmnLocationMst.setActivateFlag(1l);
		lObjCmnLocationMst.setCreatedBy(orgUserMst);
		lObjCmnLocationMst.setCreatedByPost(orgUserMst.getCreatedByPost());
		lObjCmnLocationMst.setCreatedDate(new Date());
		lObjCmnLocationMst.setLocationCode(lLngLocId.toString());
		Long distCode = (strDistCode != null && Long.parseLong(strDistCode) > 0) ? Long.parseLong(strDistCode) : -1;
		lObjCmnLocationMst.setLocDistrictId(distCode);
		lObjCmnLocationMst.setLocStateId(15L);
		Long id = (Long) ghibSession.save(lObjCmnLocationMst);

		CmnLocationMst save = ghibSession.get(CmnLocationMst.class, id);

		return save;
		// ghibSession.flush();
		// return lLngLocId.toString();
	}

	public OrgUserMst insertUserMst(String lStrDdoCode, Long lLngUserIdCrtd, Long lLngPostIdCrtd,
			OrgUserMst orgUserMst) {
		Session ghibSession = entityManager.unwrap(Session.class);
		String ddoc = lStrDdoCode;
		Long lLngUserId = null;
		CmnLocationMst lObjCmnLocationMst = new CmnLocationMst();
		CmnLanguageMst lObjCmnLanguageMst = cmnLanguageMstRepository.findByLangId(1l);
		CmnLookupMst lObjCmnLookupMst = cmnLookupMstRepository.findByLookupId(1l);

		OrgUserMst lObjUserMst = new OrgUserMst();
		// lLngUserId = 0l;//getNextSeqNoLocForUserMst();
		// lObjUserMst.setUserId(lLngUserId);

		lObjUserMst.setUserName(ddoc + "_AST");
		lObjUserMst.setDdoCode(ddoc);

		lObjUserMst.setPassword(passwordEncoder.encode("ifms123"));

		Optional<MstRoleEntity> findById = mstRoleRepo.findById(3);
		lObjUserMst.setMstRoleEntity(findById.get());

		lObjUserMst.setCmnLookupMst(lObjCmnLookupMst);

		lObjUserMst.setStartDate(new Timestamp(new Date().getTime()));

		lObjUserMst.setActivateFlag(0l);
		lObjUserMst.setAppCode(1);
		lObjUserMst.setCreatedDate(new Timestamp(new Date().getTime()));

		lObjUserMst.setCreatedBy(orgUserMst);

		lObjUserMst.setCreatedByPost(orgUserMst.getCreatedByPost());

		lObjUserMst.setSecretQueCode("Secret_Other");
		lObjUserMst.setSecretQueOther("Secret_Other");// TODO -- Needs to Change
		lObjUserMst.setSecretAnswer("ifms");
		// Serializable save = ghibSession.save(lObjUserMst);

		Long id = (Long) ghibSession.save(lObjUserMst);

		OrgUserMst save = ghibSession.get(OrgUserMst.class, id);

		return save;

		// ghibSession.flush();

		// return (Long) save;
	}

	public void insertEmpMst(Long lLngUserId, String lStrFname, Long lLngUserIdCrtd, Long lLngPostIdCrtd,
			String lStrGendr, OrgUserMst orgUserMst, String mobNo, String email) {
		Session ghibSession = entityManager.unwrap(Session.class);
		SimpleDateFormat lObjDateFormate = new SimpleDateFormat("dd/MM/yyyy");
		Date lObjEmpDob = new Date("01/01/9999");
		OrgGradeMst lObjOrgGradeMst = orgGradeMstRepository.findByGradeId(100064l);
		CmnLanguageMst lObjCmnLanguageMst = cmnLanguageMstRepository.findByLangId(1l);

		OrgUserMst lObjOrgUserMst = orgUserMst;
		OrgUserMst lObjOrgUserMstCrtd = userInfoRepoImpl.getUserByUserId(lLngUserId);

		OrgPostMst postId = orgUserMst.getCreatedByPost();
		MstEmployeeEntity mstEmployeeEntity = new MstEmployeeEntity();
		mstEmployeeEntity.setEmployeeFullNameEn(lStrFname);
		mstEmployeeEntity.setEmployeeFNameEn(lStrFname);
		mstEmployeeEntity.setGender(lStrGendr.charAt(0));
		mstEmployeeEntity.setDob(lObjEmpDob);
		mstEmployeeEntity.setDtJoinCurrentPost(new Date());
		mstEmployeeEntity.setMobileNo1(Long.valueOf(mobNo));
		mstEmployeeEntity.setEmailId(email);
		mstEmployeeEntity.setUserId(lLngUserId);
		mstEmployeeEntity.setPostdetailid(lLngUserId);
		mstEmployeeEntity.setCreatedDate(new Date());
		// mstEmployeeEntity.setGradePay(100064l);
		if (lStrGendr.equals("M")) {
			mstEmployeeEntity.setSalutation(1l);
		} else if (lStrGendr.equals("F")) {
			mstEmployeeEntity.setSalutation(1l);
		}

		mstEmployeeEntity.setIsActive(1l);

		ghibSession.save(mstEmployeeEntity);
		
	}

	public OrgPostMst insertOrgPostMst(Long lLngPostId, String lStrLocationCode, Long lLngUserIdCrtd,
			Long lLngPostIdCrtd, String lStrDsgnCode, OrgUserMst orgUserMst) {
		Session ghibSession = entityManager.unwrap(Session.class);
		CmnLookupMst lObjCmnLookupMst = cmnLookupMstRepository.findByLookupId(13l);

		OrgUserMst lObjOrgUserMstCrtdUsr = orgUserMst;

		OrgPostMst postId = orgUserMst.getCreatedByPost();

		OrgPostMst lObjOrgPostMst = new OrgPostMst();
		lObjOrgPostMst.setPostId(lLngPostId);
		lObjOrgPostMst.setParentPostId(-1l);
		lObjOrgPostMst.setPostLevelId(1l);
		lObjOrgPostMst.setCmnLookupMst(lObjCmnLookupMst);
		lObjOrgPostMst.setPostTypeLookupId(lObjCmnLookupMst);
		lObjOrgPostMst.setActivateFlag(1l);
		lObjOrgPostMst.setCreatedBy(lObjOrgUserMstCrtdUsr);
		lObjOrgPostMst.setCreatedByPost(postId);
		lObjOrgPostMst.setCreatedDate(new Timestamp(new Date().getTime()));
		lObjOrgPostMst.setLocationCode(lStrLocationCode);
		lObjOrgPostMst.setDsgnCode(lStrDsgnCode);
		lObjOrgPostMst.setStatusLookupId(13l);
		Long id = (Long) ghibSession.save(lObjOrgPostMst);
		OrgPostMst save = ghibSession.get(OrgPostMst.class, lLngPostId);

		return save;

	}

	public void insertPostDtlsRlt(String lstrLocCode, Long lLngPostId, String lStrDesignName, Long lLngDsgnId,
			Long lLngUserIdCrtd, Long lLngPostIdCrtd, OrgUserMst orgUserMst, OrgPostMst newOrgPostMst,
			CmnLocationMst cmnLocationMst) {
		Session ghibSession = entityManager.unwrap(Session.class);
		Long lLngPostDtlsId = null;
		OrgPostMst postId = newOrgPostMst;
		OrgPostMst postIdCrtd = orgUserMst.getCreatedByPost();

		CmnLocationMst lObjCmnLocationMst = cmnLocationMst;// cmnLocationMstRepository.findByLocId(Long.parseLong(lstrLocCode));

		MstDesignationEntity lObjOrgDesigmMst = orgDesignationMstRepository.findByDesginationId(lLngDsgnId);
		CmnLanguageMst lObjCmnLanguageMst = cmnLanguageMstRepository.findByLangId(1l);

		OrgUserMst lObjOrgUserMstCrtd = orgUserMst;

		OrgPostDetailsRlt lObjPostDtldRlt = new OrgPostDetailsRlt();

		// lLngPostDtlsId = getNextSeqNoLocForPostDtlsRlt();
		// lObjPostDtldRlt.setPostDetailId(lLngPostDtlsId);
		lObjPostDtldRlt.setOrgPostMst(postId);
		lObjPostDtldRlt.setPostName(lStrDesignName);
		lObjPostDtldRlt.setPostShortName(lStrDesignName);
		lObjPostDtldRlt.setCmnLocationMst(lObjCmnLocationMst);
		lObjPostDtldRlt.setOrgDesignationMst(lObjOrgDesigmMst);
		// lObjPostDtldRlt.setCmnLookupMst(newOrgPostMst.getCmnLookupMst());

		// lObjPostDtldRlt.setCmnLookupMst(postId.getCmnLookupMst());
		lObjPostDtldRlt.setCmnLanguageMst(lObjCmnLanguageMst);
		lObjPostDtldRlt.setCreatedBy(lObjOrgUserMstCrtd);
		lObjPostDtldRlt.setCreatedByPost(postIdCrtd);
		// lObjPostDtldRlt.set
		lObjPostDtldRlt.setCreatedDate(new Timestamp(new Date().getTime()));
		ghibSession.save(lObjPostDtldRlt);
		ghibSession.flush();

	}

	public void insertPostRoleRlt(Long lLngPostId, Long lLngUserIdCrtd, Long lLngPostIdCrtd, OrgUserMst orgUserMst,
			String lStrUserType, OrgPostMst newOrgPostMst) {
		Session ghibSession = entityManager.unwrap(Session.class);
		Long lLngPostRoleId = null;
		/*
		 * AclRoleMst lObjAclRoleMst = null; OrgPostMst postId =
		 * newOrgPostMst;//orgPostMstRepository.findByPostId(lLngPostId); OrgPostMst
		 * postIdCrtd = orgUserMst.getCreatedByPost();
		 * 
		 * 
		 * if (lStrUserType.trim().equals("DDO")) { lObjAclRoleMst = (AclRoleMst)
		 * aclRoleMstRepository.findByRoleId(700002l); } else if
		 * (lStrUserType.trim().equals("ASST")) { lObjAclRoleMst = (AclRoleMst)
		 * aclRoleMstRepository.findByRoleId(700001l); }
		 * 
		 * CmnLookupMst activeFlag = cmnLookupMstRepository.findByLookupId(1l);
		 * 
		 * OrgUserMst lObjOrgUserMstCrtd = orgUserMst;
		 * 
		 * AclPostroleRlt lObjAclPostRoleRlt = new AclPostroleRlt(); //lLngPostRoleId =
		 * IFMSCommonServiceImpl.getNextSeqNum("acl_postrole_rlt", inputMap);
		 * //lObjAclPostRoleRlt.setPostRoleId(lLngPostRoleId);
		 * lObjAclPostRoleRlt.setOrgPostMst(postId);
		 * lObjAclPostRoleRlt.setAclRoleMst(lObjAclRoleMst);
		 * lObjAclPostRoleRlt.setStartDate(new Timestamp(new Date().getTime()));
		 * lObjAclPostRoleRlt.setCmnLookupMstByActivate(activeFlag);
		 * lObjAclPostRoleRlt.setOrgPostMstByCreatedByPost(postIdCrtd);
		 * lObjAclPostRoleRlt.setOrgUserMstByCreatedBy(lObjOrgUserMstCrtd);
		 * lObjAclPostRoleRlt.setCreatedDate(new Timestamp(new Date().getTime()));
		 * ghibSession.save(lObjAclPostRoleRlt); ghibSession.flush();
		 */

	}

	public void insertUserPostRlt(Long lLngPostId, Long lLngUserId, Long lLngUserIdCrtd, Long lLngPostIdCrtd,
			OrgUserMst orgUserMst, OrgPostMst newOrgPostMst, OrgUserMst orgUserMst2) {
		Long lLngEmpPostId = null;
		Session ghibSession = entityManager.unwrap(Session.class);
		try {

			OrgPostMst postId = newOrgPostMst;// orgPostMstRepository.findByPostId(lLngPostId);
			OrgPostMst postIdCrtd = orgUserMst.getCreatedByPost();

			OrgUserMst lObjOrgUserMstCrtd = orgUserMst;
			OrgUserMst lObjOrgUserMst = orgUserMst2;// userInfoRepoImpl.getUserByUserId(lLngUserId);

			CmnLookupMst lObjCmnLookupMst = cmnLookupMstRepository.findByLookupId(13l);

			OrgUserpostRlt lObjOrgUserpostRlt = new OrgUserpostRlt();
			// lLngEmpPostId = IFMSCommonServiceImpl.getNextSeqNum("ORG_USERPOST_RLT",
			// inputMap);
			// lLngEmpPostId = getNextSeqNoLocForOrgUserpostRlt();
			// lObjOrgUserpostRlt.setEmpPostId(lLngEmpPostId);
			lObjOrgUserpostRlt.setOrgUserMst(lObjOrgUserMst);
			// lObjOrgUserpostRlt.setOrgPostMst(newOrgPostMst);
			lObjOrgUserpostRlt.setStartDate(new Timestamp(new Date().getTime()));
			lObjOrgUserpostRlt.setActivateFlag(1l);
			lObjOrgUserpostRlt.setOrgPostMstByPostId(newOrgPostMst);
			lObjOrgUserpostRlt.setCreatedByPost(postIdCrtd);
			lObjOrgUserpostRlt.setCreatedBy(orgUserMst);
			lObjOrgUserpostRlt.setCreatedDate(new Timestamp(new Date().getTime()));
			lObjOrgUserpostRlt.setCmnLookupUserPostType(lObjCmnLookupMst);
			// lObjOrgUserpostRlt.setCmnLocationMst(postId.getLookupId());
			ghibSession.save(lObjOrgUserpostRlt);
			ghibSession.flush();
		} catch (Exception e) {
			throw e;
		}
	}

	public void insertOrgDdoMst(String lStrDdoCode, String lStrDdoName, String lStrDdoPrsnlName, Long lLngPostId,
			Long lLngUserIdCrtd, String lStrLocationCode, Long lLngPostIdCrtd, String lstrDeptLocCode,
			String lstrDdoType, String lstrDept_Code, String lstrHOD_Code, String lstrDeptType, OrgUserMst orgUserMst,
			Long desginationId, String lStrDesgnName, String lStrDdoOfficeName) {
		Session ghibSession = entityManager.unwrap(Session.class);
		Long lLndDdoId = null;
		try {
			OrgDdoMst lObjOrgDdoMst = new OrgDdoMst();
			lObjOrgDdoMst.setDdoCode(lStrDdoCode);
			lObjOrgDdoMst.setDdoName(lStrDdoName);
			lObjOrgDdoMst.setDdoPersonalName(lStrDdoPrsnlName);
			lObjOrgDdoMst.setPostId(lLngPostId);
			lObjOrgDdoMst.setLangId((short) 1);
			lObjOrgDdoMst.setDeptLocCode(lstrDeptLocCode);
			lObjOrgDdoMst.setCreatedBy(lLngUserIdCrtd);
			lObjOrgDdoMst.setCreatedByPost(lLngPostIdCrtd);
			lObjOrgDdoMst.setCreatedDate(new Timestamp(new Date().getTime()));
			lObjOrgDdoMst.setDbId((short) 99);
			lObjOrgDdoMst.setLocationCode(lStrLocationCode);
			lObjOrgDdoMst.setDdoOffice(lStrDdoOfficeName);
			lObjOrgDdoMst.setHodLocCode(lstrHOD_Code);
			lObjOrgDdoMst.setDsgnCode(desginationId.toString());
			lObjOrgDdoMst.setDsgnName(lStrDesgnName);
			ghibSession.save(lObjOrgDdoMst);
			ghibSession.flush();
		} catch (Exception e) {
			throw e;
		}

	}

	public void insertMstDcpsDdoOffice(String lStrDdoCode, String lStrDdoOffice, String lStrDistCode, Long lLngLocId,
			Long lLngUserIdCrtd, Long lLngPostIdCrtd, OrgUserMst orgUserMst, String uniqeInstituteId) {
		Session ghibSession = entityManager.unwrap(Session.class);
		Long lLngMstOfficeDdoId = null;

		try {

			DdoOffice lObjDdoOffice = new DdoOffice();
			lObjDdoOffice.setDcpsDdoCode(lStrDdoCode);
			lObjDdoOffice.setDcpsDdoOfficeName(lStrDdoOffice);
			lObjDdoOffice.setDcpsDdoOfficeDdoFlag("Yes");
			lObjDdoOffice.setDcpsDdoOfficeState("15");
			lObjDdoOffice.setDcpsDdoOfficeDistrict(lStrDistCode);
			lObjDdoOffice.setLangId(1l);
			lObjDdoOffice.setLocId(lLngLocId);

			lObjDdoOffice.setDbId(99l);
			lObjDdoOffice.setPostId(lLngPostIdCrtd);
			lObjDdoOffice.setUserId(lLngUserIdCrtd);
			lObjDdoOffice.setCreatedDate(new Timestamp(new Date().getTime()));
			lObjDdoOffice.setStatusFlag(0l);
			lObjDdoOffice.setUniqueInstituteNo(uniqeInstituteId);

			lObjDdoOffice.setDcpsDdoCode(lStrDdoCode);
			ghibSession.save(lObjDdoOffice);
		} catch (Exception e) {
			System.out.println("Error is : " + e);
		}

	}

	public void insertRltDdoOrg(Long lLngUserIdCrtd, Long lLngPostIdCrtd, String lStrDdoCode, String lStrTrsryCode,
			OrgUserMst orgUserMst) throws Exception {
		Session ghibSession = entityManager.unwrap(Session.class);
		Long lLngDdoOrgId = null;
		RltDdoOrg lObjRltDdoOrg = new RltDdoOrg();
		// lLngDdoOrgId = IFMSCommonServiceImpl.getNextSeqNum("rlt_ddo_org", inputMap);
		// lLngDdoOrgId = getNextSeqNoLocForRltDdoOrg();
		// lObjRltDdoOrg.setDdoOrgId(lLngDdoOrgId);
		lObjRltDdoOrg.setActivateFlag(1l);
		lObjRltDdoOrg.setCreatedDate(new Timestamp(new Date().getTime()));
		lObjRltDdoOrg.setCreatedPostId(lLngPostIdCrtd);
		lObjRltDdoOrg.setCreatedUserId(lLngUserIdCrtd);
		lObjRltDdoOrg.setDdoCode(lStrDdoCode);
		lObjRltDdoOrg.setLocationCode(lStrTrsryCode);
		lObjRltDdoOrg.setTrnCounter(1);
		ghibSession.save(lObjRltDdoOrg);
		ghibSession.flush();
	}

	

	public void insertUserMstAsst(Long lLngUserId, String lStrDdoCodeAsst, Long lLngUserIdCrtd, Long lLngPostIdCrtd,
			OrgUserMst orgUserMst) throws Exception {
		try {
			Session ghibSession = entityManager.unwrap(Session.class);
			CmnLookupMst lObjCmnLookupMst = cmnLookupMstRepository.findByLookupId(1l);

			OrgUserMst lObjOrgUserMstCrtdUsr = orgUserMst;

			OrgPostMst postId = orgUserMst.getCreatedByPost();

			OrgUserMst lObjUserMst = new OrgUserMst();
			// lLngUserId = IFMSCommonServiceImpl.getNextSeqNum("org_user_mst", inputMap);
			// lObjUserMst.setUserId(lLngUserId);
			lObjUserMst.setUserName(lStrDdoCodeAsst);
			lObjUserMst.setPassword(passwordEncoder.encode("ifms123"));
			lObjUserMst.setCmnLookupMst(lObjCmnLookupMst);
			lObjUserMst.setStartDate(new Timestamp(new Date().getTime()));
			lObjUserMst.setActivateFlag(0l);
			lObjUserMst.setCreatedDate(new Timestamp(new Date().getTime()));
			lObjUserMst.setCreatedBy(lObjOrgUserMstCrtdUsr);
			lObjUserMst.setCreatedByPost(postId);
			lObjUserMst.setSecretQueCode("Secret_Other");
			lObjUserMst.setSecretQueOther("Secret_Other");
			lObjUserMst.setSecretAnswer("ifms");
			lObjUserMst.setCreatedByPost(orgUserMst.getCreatedByPost());
			Optional<MstRoleEntity> findById = mstRoleRepo.findById(1);
			lObjUserMst.setMstRoleEntity(findById.get());

			ghibSession.save(lObjUserMst);
			ghibSession.flush();
		} catch (Exception e) {
			throw e;
		}
	}

	public synchronized Long getNextSeqNoLoc() {
		return cmnLocationMstRepository.findMaxLocId() + 1;
		}

	
	public Long getPostIdOfTOAsstForTreasuryCode(String lStrTreasuryCode) {
		Session ghibSession = entityManager.unwrap(Session.class);     
		StringBuilder lSBQuery = new StringBuilder();
		List tempList = new ArrayList();
		Long lLongTOAsstPostId = null;

		lSBQuery.append(
				" SELECT post_id from org_post_mst where post_id in (select post_id from ORG_USERPOST_RLT where user_id in (select user_id from org_user_mst where trim(user_name)='"
						+ lStrTreasuryCode.trim() + "_TO_ASST' ))");

		Query lQuery = ghibSession.createNativeQuery(lSBQuery.toString());
		tempList = lQuery.getResultList();
		if (tempList != null && tempList.size() != 0) {
			if (tempList.get(0) != null) {
				if (!"".equals(tempList.get(0).toString())) {
					lLongTOAsstPostId = Long.valueOf(tempList.get(0).toString());
				}
			}
		}
		return lLongTOAsstPostId;
	}

	public Long getPostIdOfTOForTreasuryCode(String lStrTreasuryCode) {
		Session ghibSession = entityManager.unwrap(Session.class);     
		StringBuilder lSBQuery = new StringBuilder();
		List tempList = new ArrayList();
		Long lLongTOPostId = null;

		lSBQuery.append(
				" SELECT post_id from org_post_mst where post_id in (select post_id from ORG_USERPOST_RLT where user_id in (select user_id from org_user_mst where trim(user_name)='"
						+ lStrTreasuryCode.trim() + "_TO' ))");

		Query lQuery = ghibSession.createNativeQuery(lSBQuery.toString());

		tempList = lQuery.getResultList();
		if (tempList != null && tempList.size() != 0) {
			if (tempList.get(0) != null) {
				if (!"".equals(tempList.get(0).toString())) {
					lLongTOPostId = Long.valueOf(tempList.get(0).toString());
				}
			}

		}
		return lLongTOPostId;

	}

	public void insertWfHierarchyTableSeqMst(String lStrLocCode, OrgUserMst orgUserMst) throws Exception {
		StringBuilder lSBQueryInner = null;
		Query lQueryInner = null;
		Long lLongTableSeqMstId = null;
		Session ghibSession = entityManager.unwrap(Session.class);     
		String[] lArrStrTableName = { "wf_job_mst", "wf_job_mvmnt_mst", "wf_audit_trail_mst", "wf_marked_hierachy_mst",
				"wf_notification", "wf_job_attribute", "wf_job_grp_mst", "wf_table_seq_mst", "wf_doc_mvmnt_mst" };
		Long[] lArrLongPrivMaxId = { 1l, 1l, 1l, 1l, 54004l, 909l, 927196l, 89l, 1l };

		try {

			for (Integer lInt = 0; lInt < 9; lInt++) {
				lLongTableSeqMstId = 00000l;//IFMSCommonServiceImpl.getNextSeqNum("wf_table_seq_mst", inputMap);

				lSBQueryInner = new StringBuilder();

				lSBQueryInner.append("INSERT INTO WF_TABLE_SEQ_MST VALUES \n");
				lSBQueryInner.append(
						"(:seqMstId,:tableName,:privMaxId,:crtdUser,:crtdPost,:createdDate,:lstUpdUser,:lstUpdPost,:lstUpdDate,:dbId,:locId,:pkLength) \n");

				lQueryInner = ghibSession.createNativeQuery(lSBQueryInner.toString());
				lQueryInner.setParameter("seqMstId", lLongTableSeqMstId);
				lQueryInner.setParameter("tableName", lArrStrTableName[lInt]);
				lQueryInner.setParameter("privMaxId", lArrLongPrivMaxId[lInt]);
				lQueryInner.setParameter("crtdUser", "1");
				lQueryInner.setParameter("crtdPost", "1");
				lQueryInner.setParameter("createdDate", new Timestamp(new Date().getTime()));
				lQueryInner.setParameter("lstUpdUser", null);
				lQueryInner.setParameter("lstUpdPost", null);
				lQueryInner.setParameter("lstUpdDate", null);
				lQueryInner.setParameter("dbId", 99);
				lQueryInner.setParameter("locId", lStrLocCode);
				lQueryInner.setParameter("pkLength", 20);

				lQueryInner.executeUpdate();
			}

		} catch (Exception e) {
			throw e;
		}
	}

	
	public List RetirveParentdtlfrmReptCode(String RptDDOCode)
	{
		Session ghibSession = entityManager.unwrap(Session.class);     
		List temp=null;
		try
		{		
			String branchQuery = "SELECT DEPT_LOC_CODE||'##'||HOD_LOC_CODE FROM ORG_DDO_MST where DDO_CODE like '%"+RptDDOCode+"%'";
			Query sqlQuery= ghibSession.createNativeQuery(branchQuery);
			temp= sqlQuery.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}
	public List RetirveAdminDeptType(String lstrAdminDeptType)
	{
		Session ghibSession = entityManager.unwrap(Session.class);     
		List temp=null;
		try
		{		
			String branchQuery = "SELECT b.DEPT_ID FROM MST_ZP_DEPT b where DEPT_ID like '%"+lstrAdminDeptType+"%'";
			Query sqlQuery= ghibSession.createNativeQuery(branchQuery);
			temp= sqlQuery.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}
	
	


public List retriveDepts(String OfcCode) 
	{
		List temp=null;
		//hibSession = getSession();
		try
		{		
			Session ghibSession = entityManager.unwrap(Session.class);   
			String branchQuery = "select aa.DEPT_ID,aa.DEPT_NAME,aa.DEPT_CODE FROM MST_ZP_DEPT aa where ADMIN_OFF_TYPE_CODE='"+OfcCode+"'";
			Query sqlQuery= ghibSession.createNativeQuery(branchQuery);
			temp= sqlQuery.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}
	public List retirveDdoType(String adminOfcId)
	{
		Session ghibSession = entityManager.unwrap(Session.class);     
		List temp=null;
		try
		{		
			String branchQuery = "SELECT OFFICE_CODE FROM ZP_ADMIN_OFFICE_MST where OFC_ID like '%"+adminOfcId+"%'";
			Query sqlQuery= ghibSession.createNativeQuery(branchQuery);
			temp= sqlQuery.getResultList();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return temp;
	}

	
	//added by samdahan
		public String generateUniqeInstituteId(String strDdoCode, String districtCode,OrgUserMst orgUserMst) 
		{
			String uniqeInstituteId="";
			int uniqeInstituteIdCount=0;
			String distName="";
			
			uniqeInstituteIdCount=getUniqeInstituteIdCount(strDdoCode);
			distName=getDistName(districtCode);
			
			String tmp = String.valueOf((uniqeInstituteIdCount+1));
			if(tmp.length()<8)
			{
				String prefix="";
				for(int i=0;i<(7-tmp.length());i++)
				{
					prefix = prefix+"0";
				}
				tmp=prefix+tmp;
			}
			
			uniqeInstituteId = distName.substring(0, 3) + tmp; 
			
			
			return uniqeInstituteId;
		}
		
		
		public String getDistName(String districtCode) {
			Session ghibSession = entityManager.unwrap(Session.class);     
			String distName="";
			StringBuilder sb = new StringBuilder();
			sb.append(" SELECT upper(DISTRICT_NAME) FROM CMN_DISTRICT_MST where DISTRICT_ID = '"+districtCode+"' ");
			Query selectQuery = ghibSession.createNativeQuery(sb.toString());
			distName = selectQuery.getSingleResult().toString();
			return distName;
		}

		public int getUniqeInstituteIdCount(String strDdoCode) 
		{
			Session ghibSession = entityManager.unwrap(Session.class);     
			int uniqeInstituteIdCount=0;
			StringBuilder sb = new StringBuilder();
			sb.append(" select count(1) from MST_DCPS_DDO_OFFICE where UNIQUE_INSTITUTE_NO is not null ");
			Query selectQuery = ghibSession.createNativeQuery(sb.toString());
			uniqeInstituteIdCount = Integer.parseInt(selectQuery.getSingleResult().toString());
			return uniqeInstituteIdCount;
		}

		public void insertOrgDdoMst(String lStrDdoCode, String lStrDdoName, String lStrDdoPersonalName, Long lLngPostId,
				Long gLngUserId, String lStrLocCode, Long gLngPostId, String string, String lstrDdoType,
				String lstrDept_Code, String lstrHOD_Code, String lstrDeptType, OrgUserMst messages, Long desginationId, String lStrDesgnName) {
			Long lLndDdoId = null;
			try {
				Session ghibSession = entityManager.unwrap(Session.class);     
				OrgDdoMst lObjOrgDdoMst = new OrgDdoMst();
				lObjOrgDdoMst.setDdoCode(lStrDdoCode);
				lObjOrgDdoMst.setDdoName(lStrDdoName);
				lObjOrgDdoMst.setDdoPersonalName(lStrDdoPersonalName);
				lObjOrgDdoMst.setPostId(lLngPostId);
				lObjOrgDdoMst.setLangId((short)1);
				lObjOrgDdoMst.setDeptLocCode(lstrDept_Code);
				lObjOrgDdoMst.setCreatedBy(messages.getUserId());
				lObjOrgDdoMst.setCreatedByPost(messages.getUserId());
				lObjOrgDdoMst.setCreatedDate(new Timestamp(new Date().getTime()));
				lObjOrgDdoMst.setDbId((short)99);
				lObjOrgDdoMst.setLocationCode(lStrLocCode);
				lObjOrgDdoMst.setDsgnCode(desginationId.toString());
				lObjOrgDdoMst.setDsgnName(lStrDesgnName);
				lObjOrgDdoMst.setHodLocCode(lstrHOD_Code);
				ghibSession.save(lObjOrgDdoMst);
				//ghibSession.flush();
			} catch (Exception e) {
				throw e;
			}
		}

	
		
		public void insertRltZpDdoMap(Long ZP_DDO_POST_ID,Long REPT_DDO_POST_ID, Long FINAL_DDO_POST_ID, Long SPECIAL_DDO_POST_ID, String ZP_DDO_CODE,String REPT_DDO_CODE,String FINAL_DDO_CODE,String SPECIAL_DDO_CODE,String lstrLevel,Long CreatedUser,Long CreatedPost,OrgUserMst orgUserMst)
		{
			Long ZP_MAP_ID = null;
			try{
				CmnLanguageMst lObjCmnLanguageMst = cmnLanguageMstRepository.findByLangId(1l);
				Session ghibSession = entityManager.unwrap(Session.class);     
				ZpRltDdoMap objZpRltDdoMap=new ZpRltDdoMap();
				//ZP_MAP_ID = IFMSCommonServiceImpl.getNextSeqNum("RLT_ZP_DDO_MAP", inputMap);
			//	ZP_MAP_ID = getNextSeqNoLocForRltZpDdoMap();
				objZpRltDdoMap.setZpDdoPostId(ZP_DDO_POST_ID);
				//objZpRltDdoMap.setZP_MAP_ID(ZP_MAP_ID);
				objZpRltDdoMap.setReptDdoPostId(REPT_DDO_POST_ID);
				objZpRltDdoMap.setFinalDdoPostId(FINAL_DDO_POST_ID);
				objZpRltDdoMap.setSpecialDdoPostId(SPECIAL_DDO_POST_ID);
				objZpRltDdoMap.setZpDdoCode(ZP_DDO_CODE);
				objZpRltDdoMap.setReptDdoCode(REPT_DDO_CODE);
				objZpRltDdoMap.setFinalDdoCode(FINAL_DDO_CODE);
				objZpRltDdoMap.setSpecialDdoCode(SPECIAL_DDO_CODE);
				objZpRltDdoMap.setLangId(1l);;
				objZpRltDdoMap.setStatus(0l);
				objZpRltDdoMap.setCreatedDate(new Timestamp(new Date().getTime()));
				objZpRltDdoMap.setCreatedUserId(CreatedPost);
				objZpRltDdoMap.setCreatedPostId(CreatedUser);
				objZpRltDdoMap.setZplevel(Long.valueOf(lstrLevel));
				ghibSession.save(objZpRltDdoMap);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public List retirveRepoDDOPostId(String DDOCode)
		{
			List temp=null;
			try
			{		
				Session ghibSession = entityManager.unwrap(Session.class);   
				String branchQuery = "SELECT POST_ID FROM ORG_DDO_MST where DDO_CODE="+DDOCode;
				Query sqlQuery= ghibSession.createNativeQuery(branchQuery);
				temp= sqlQuery.getResultList();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return temp;
		}
		
		public List retirveFinalDDOPostId(Long DDOCode)
		{
			List temp=null;
			try
			{		
				Session ghibSession = entityManager.unwrap(Session.class);   
				String branchQuery = "SELECT POST_ID FROM ORG_DDO_MST where DDO_CODE="+DDOCode;
				Query sqlQuery= ghibSession.createNativeQuery(branchQuery);
				temp= sqlQuery.getResultList();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return temp;
		}

		
		
	
	

}
