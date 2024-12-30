package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.ZpRltDdoMap;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class CreateAdminOfficeRepoImpl implements CreateAdminOfficeRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	MstDesignationRepository mstDesignationRepository;

	private static final Logger logger = LoggerFactory.getLogger(CreateAdminOfficeRepoImpl.class);

	@Override
	public List<Object[]> getAllDDOOfficeDtlsData(String districtName, String talukaNametName, String adminType) {
		List list = null;

		Session hibSession = entityManager.unwrap(Session.class);
		StringBuffer strQuery = new StringBuffer();
		strQuery.append(
				"SELECT zp.ZP_DDO_CODE,zp.REPT_DDO_CODE,zp.FINAL_DDO_CODE,zp.SPECIAL_DDO_CODE,zp.ZPLEVEL,zp.STATUS,zp.ZP_MAP_ID,zp.created_date FROM  RLT_ZP_DDO_MAP zp ");
		strQuery.append(" inner join org_ddo_mst office on zp.zp_ddo_code=office.ddo_code ");
		strQuery.append(" where zp.status is not null "); // zp.LANG_ID =1 and
		if ((districtName != null) && (districtName != "") && (Long.parseLong(districtName) != -1)) {
			strQuery.append(" and office.district='" + districtName + "'");
		}
		if ((talukaNametName != null) && (talukaNametName != "") && (Long.parseLong(talukaNametName) != -1)) {
			strQuery.append(" and office.taluka='" + talukaNametName + "'");
		}

		if ((adminType != null) && (adminType != "") && (Long.parseLong(adminType) != -1)) {
			strQuery.append(" and zp.ZP_DDO_CODE like '" + adminType + "%'");
		}
		strQuery.append(" order by ZP_MAP_ID desc");

		Query query = hibSession.createNativeQuery(strQuery.toString());
		list = query.getResultList();
		return list;
	}

	@Override
	public List<Object[]> findPostLocationByDdoCode(String ddoCode) {
		String sql = "WITH ddo_info AS (" + "    SELECT post_id, LOCATION_CODE " + "    FROM org_ddo_mst "
				+ "    WHERE ddo_code = :ddoCode " + ") " + "SELECT opd.POST_SHORT_NAME, clm.loc_name "
				+ "FROM ORG_POST_DETAILS_RLT opd " + "JOIN ddo_info di ON opd.post_ID = di.post_id "
				+ "JOIN CMN_LOCATION_MST clm ON clm.LOC_ID = cast(di.LOCATION_CODE as bigint)";
		Query query = (Query) entityManager.createNativeQuery(sql);
		query.setParameter("ddoCode", ddoCode);
		return query.getResultList();
	}

	public List getTreasuryName(Long TOCode) {
		Session hibSession = entityManager.unwrap(Session.class);
		List temp = null;
		try {
			String branchQuery = "select CM.loc_id,CM.loc_name from rlt_ddo_org RL join cmn_location_mst CM on cast(RL.location_code as bigint) = CM.loc_id  where RL.ddo_code = '"
					+ String.valueOf(TOCode) + "'";
			Query sqlQuery = hibSession.createNativeQuery(branchQuery);
			temp = sqlQuery.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public List getAdminOfcCode(String AdminOfcID) {
		Session hibSession = entityManager.unwrap(Session.class);
		List temp = null;
		try {
			String branchQuery = "select aa.OFFICE_CODE from ZP_ADMIN_OFFICE_MST aa where OFC_ID='" + AdminOfcID + "'";
			Query sqlQuery = hibSession.createNativeQuery(branchQuery);
			temp = sqlQuery.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public List getCountofDDOCode(String CountCode) {
		Session hibSession = entityManager.unwrap(Session.class);
		List temp = null;
		try {
			String branchQuery = "select count(qq.DDO_CODE) from ORG_DDO_MST qq where DDO_CODE like '" + CountCode
					+ "%'";
			Query sqlQuery = hibSession.createNativeQuery(branchQuery);
			temp = sqlQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public List getSubTreasury(Long valueOf) {
		List temp = null;
		Session hibSession = entityManager.unwrap(Session.class);
		try {
			String branchQuery = "SELECT loc_id,loc_name from CMN_LOCATION_MST where DEPARTMENT_ID= 100006 and PARENT_LOC_ID="
					+ valueOf;
			Query sqlQuery = hibSession.createNativeQuery(branchQuery);
			temp = sqlQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public int getUniqeInstituteIdCount(String strDdoCode) {
		Session hibSession = entityManager.unwrap(Session.class);
		int uniqeInstituteIdCount = 0;
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(1) from MST_DCPS_DDO_OFFICE where UNIQUE_INSTITUTE_NO is not null ");
		Query selectQuery = hibSession.createNativeQuery(sb.toString());
		uniqeInstituteIdCount = Integer.parseInt(selectQuery.getSingleResult().toString());
		return uniqeInstituteIdCount;
	}

	@Override
	public List<Object[]> retriveDisctOfcList(OrgUserMst messages, String ofcId) {
		if (ofcId == null || ofcId.equals("")) {
			String nativeQuery = "select DIST_MST_OFC_NAME, DIST_ID,ADMIN_OFFICE_CODE from ZP_ADMIN_OFFICE_DISTRICT_MPG WHERE ADMIN_OFFICE_CODE='02' and LANG_ID=1";
			Query query = (Query) entityManager.createNativeQuery(nativeQuery);
			// query.setParameter("ofcId",ofcId);
			List<Object[]> resultList = query.getResultList();
			return resultList;
		} else {
			String nativeQuery = "select DIST_MST_OFC_NAME, DIST_ID,ADMIN_OFFICE_CODE from ZP_ADMIN_OFFICE_DISTRICT_MPG where admin_OFFICE_CODE = :ofcId";
			Query query = (Query) entityManager.createNativeQuery(nativeQuery);
			query.setParameter("ofcId", ofcId);
			List<Object[]> resultList = query.getResultList();
			return resultList;
		}
	}

	@Override
	public List<Object[]> retrieveDistrictOfficeList(OrgUserMst messages, Long ofcId) {
		String nativeQuery = "select DIST_MST_OFC_NAME, DIST_CODE from ZP_ADMIN_OFFICE_DISTRICT_MPG where admin_OFFICE_CODE = :ofcId";
		Query query = (Query) entityManager.createNativeQuery(nativeQuery);
		query.setParameter("ofcId", ofcId);
		List<Object[]> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public List<MstDesignationEntity> findByDsgnNameIgnoreCaseContaining(String desgn) {
		return mstDesignationRepository.findByDsgnNameIgnoreCaseContaining(desgn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> lstAllDepartment() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = " select  d.department_code,d.department_name_en,d.department_name_mr from department_mst d where d.is_active = '1' order by d.department_name_en";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.getResultList();
	}

	@Override
	public List<Object[]> employeeMappingList(String logUserId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String ddocode = logUserId.substring(0, 11);
//		String hql1 = " select location_code from org_ddo_mst where ddo_code = '"+logUserId+"'";
//		Query query12 = currentSession.createNativeQuery(hql1);
//		int department = (int) query12.list().get(0);
		String hql = "select  d.location_code ,loc.loc_name,b.department_allowdeduc_mpg_id,c.department_allowdeduc_name,c.is_type,"
				+ "c.department_allowdeduc_code from department_allowdeduc_mpg b "
				+ " inner join department_allowdeduc_mst c on b.department_allowdeduc_code = c.department_allowdeduc_code inner join org_ddo_mst d on substring(d.ddo_code,0,12) = b.ddo_code "
				+ " inner join cmn_location_mst loc on loc.loc_id = cast(d.location_code as bigint) where b.is_active = '1'and b.ddo_code ='"
				+ ddocode + "'";// changed from department_allowdeduc_Id to department_allowdeduc_code
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.getResultList();
	}

	@Override
	public int ddoCodeAlreadyExists(String level1DdoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "From ZpRltDdoMap where zpDdoCode='" + level1DdoCode + "'";
		Query query = currentSession.createQuery(hql);
		List<ZpRltDdoMap> lstZpRltDdoMap = query.getResultList();
		int count = lstZpRltDdoMap.size();

		hql = "From OrgDdoMst where ddoCode='" + level1DdoCode + "'";
		query = currentSession.createQuery(hql);
		List<OrgDdoMst> lstOrgDdoMst = query.getResultList();
		int count1 = lstOrgDdoMst.size();

		hql = "From DdoOffice where dcpsDdoCode='" + level1DdoCode + "'";
		query = currentSession.createQuery(hql);
		List<DdoOffice> lstDdoOffice = query.getResultList();
		int count2 = lstDdoOffice.size();

		hql = "From OrgUserMst where ddoCode='" + level1DdoCode + "'";
		query = currentSession.createQuery(hql);
		List<OrgUserMst> lstOrgUsermst = query.getResultList();
		int count3 = lstOrgUsermst.size();

		if (count > 0 || count1 > 0 || count2 > 0 || count3 > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public List<Object[]> findDeptByDistOfcCode(String distOfcId) {
		String nativeQuery = "select dept_name, dept_id from MST_ZP_DEPT where ADMIN_OFF_TYPE_CODE = :distOfcId";
		Query query = (Query) entityManager.createNativeQuery(nativeQuery);
		query.setParameter("distOfcId", distOfcId);
		List<Object[]> resultList = query.getResultList();
		return resultList;
	}

	@Override
	public String findLevel3DdoCode(String distOfcId) {
		String ddoCode = null;
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append("select DDO_CODE from ZP_LVL3_MST where"
				+ " DIST_ID=(select  cast(DIST_CODE as integer) from ZP_ADMIN_OFFICE_DISTRICT_MPG where DIST_ID=" + distOfcId + ")");
		Query query = currentSession.createNativeQuery(sb.toString());
		ddoCode = query.getSingleResult().toString();
		logger.info("level3ddocode" + ddoCode);
		return ddoCode;
	}

}
