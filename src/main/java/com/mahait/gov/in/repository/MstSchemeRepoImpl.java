package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.entity.MstScheme;
import com.mahait.gov.in.model.MstSchemeModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class MstSchemeRepoImpl implements MstSchemeRepo {
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<MstScheme> findAllScheme() {
		String HQL = "FROM MstScheme as t ORDER BY t.schemeId DESC";
		return (List<MstScheme>) manager.createQuery(HQL).getResultList();

	}

	@Override
	public List<MstSchemeModel> findAllSchemename(String username) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "SELECT mst.SCHEME_CODE,mst.SCHEME_NAME FROM MST_SCHEME mst  "
				+ " inner join  RLT_DCPS_DDO_SCHEMES rlt on rlt.SCHEME_CODE = mst.SCHEME_CODE where rlt.ddo_code='"
				+ username + "'";
		Query query = currentSession.createNativeQuery(HQL);

		List<Object[]> lstprop = query.getResultList();
		List<MstSchemeModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstSchemeModel obj = new MstSchemeModel();
				obj.setDcpsDdoSchemeCode(StringHelperUtils.isNullString(objLst[0]));
				obj.setSchemeName(StringHelperUtils.isNullString(objLst[1]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<MstDcpsBillGroup> findAllMpgSchemeBillGroupByDDOCode(String dDOCode, int roleid) {

		String HQL = "FROM MstDcpsBillGroup as t where dcpsDdoCode ='" + dDOCode
				+ "'ORDER BY t.dcpsDdoBillGroupId DESC";
		return (List<MstDcpsBillGroup>) manager.createQuery(HQL).getResultList();

	}

	@Override
	public Long findNumberOfEmployeeInBillGroup(String ddoCode, BigInteger billGroupId, int monthName,
			int yearName, int paybillType) {
		Session currentSession = manager.unwrap(Session.class);
		yearName = yearName - 1;
		String HQL;

		if (monthName >= 1 && monthName < 10) {
			HQL = "select count(emst.employee_id) from employee_mst emst where emst.is_active = 1 and  emst.ddo_code = '"
					+ ddoCode.trim() + "' and emst.billgroup_id = '" + billGroupId
					+ "' and to_char(emst.doj,'YYYY-MM')<='20" + yearName + "-0" + monthName + "' "
					+ " and  (to_char(emst.super_ann_date,'YYYY-MM')>='20" + yearName + "-0" + monthName
					+ "' and   to_char(emst.emp_service_end_date,'YYYY-MM')>='20" + yearName + "-0" + monthName + "') ";
		} else {
			HQL = "select count(emst.employee_id) from employee_mst emst where emst.is_active = 1 and  emst.ddo_code = '"
					+ ddoCode.trim() + "' and emst.billgroup_id = '" + billGroupId
					+ "' and to_char(emst.doj,'YYYY-MM')<='20" + yearName + "-" + monthName + "' "
					+ " and  (to_char(emst.super_ann_date,'YYYY-MM')>='20" + yearName + "-" + monthName
					+ "' and   to_char(emst.emp_service_end_date,'YYYY-MM')>='20" + yearName + "-" + monthName + "') ";
		}

		System.out.println(HQL);
		Query query = currentSession.createNativeQuery(HQL);
		return (Long) query.getResultList().get(0);
	}

	public List<MstSchemeModel> findAllSchemename() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MstSchemeModel> findAllMpgSchemeBillGroupBylvl2DDOCode(String userName) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select bill_group_id,description from mst_dcps_bill_group a inner join rlt_zp_ddo_map b on a.ddo_code=b.zp_ddo_code where rept_ddo_code = '"
				+ userName + "' ";

		Query query = currentSession.createNativeQuery(HQL);

		List<Object[]> lstprop = query.getResultList();
		List<MstSchemeModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstSchemeModel obj = new MstSchemeModel();
				obj.setBillGrpId(StringHelperUtils.isNullBigInteger(objLst[0]));
				obj.setBillDesc(StringHelperUtils.isNullString(objLst[1]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<MstSchemeModel> findAllSchemeforConsolidate(String ddoCode) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select scheme_code,scheme_name from mst_dcps_bill_group a inner join rlt_zp_ddo_map b on a.ddo_code=b.zp_ddo_code where b.rept_ddo_code = '"
				+ ddoCode + "'";

		Query query = currentSession.createNativeQuery(HQL);

		List<Object[]> lstprop = query.getResultList();
		List<MstSchemeModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstSchemeModel obj = new MstSchemeModel();
				obj.setSchemeCode(StringHelperUtils.isNullString(objLst[0]));
				obj.setSchemeName(StringHelperUtils.isNullString(objLst[1]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

}
