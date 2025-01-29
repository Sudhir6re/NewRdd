package com.mahait.gov.in.repository;

import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.AppoinmentEntity;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.EmployeeAllowDeducComponentAmtEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.MstCadreGroupEntity;
import com.mahait.gov.in.entity.MstDcpsDetailsEntity;
import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstGisdetailsEntity;
import com.mahait.gov.in.entity.MstGisdetailsHistEntity;
import com.mahait.gov.in.entity.MstGpfDetailsEntity;
import com.mahait.gov.in.entity.MstGpfDetailsHistEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.OrgPostDetailsRlt;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.QualificationEntity;
import com.mahait.gov.in.entity.ZpRltDdoMap;
import com.mahait.gov.in.model.MstEmployeeModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class MstEmployeeRepoImpl implements MstEmployeeRepo {
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public DdoOffice findAllGroup(String ddocode) {
		String HQL = "FROM DdoOffice as  t  where  t.dcpsDdoCode = '" + ddocode + "'";
		return (DdoOffice) entityManager.createQuery(HQL).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MstEmployeeEntity> findAllWorkingEmployeeByDDOCodeAndBillGroup(String ddoCode, Long billGroupId,
			int month, int year) {
		String HQL = null;
		year = year - 1;
		try {
			if (month >= 1 && month < 10) {

				HQL = "FROM MstEmployeeEntity as  t  where t.ddoCode = '" + ddoCode.trim() + "' and t.billGroupId = "
						+ billGroupId + " and to_char(t.doj,'YYYY-MM')<='20" + year + "-0" + month
						+ "' and  (to_char(t.superAnnDate,'YYYY-MM')>='20" + year + "-0" + month
						+ "' and   to_char(t.empServiceEndDate,'YYYY-MM')>='20" + year + "-0" + month
						+ "') AND t.isActive=1 ORDER BY t.employeeFullNameEn";
			} else
				HQL = "FROM MstEmployeeEntity as  t  where t.ddoCode = '" + ddoCode.trim() + "' and t.billGroupId = "
						+ billGroupId + " and to_char(t.doj,'YYYY-MM')<='20" + year + "-" + month
						+ "' and  (to_char(t.superAnnDate,'YYYY-MM')>='20" + year + "-" + month
						+ "' and   to_char(t.empServiceEndDate,'YYYY-MM')>='20" + year + "-" + month
						+ "') AND t.isActive=1 ORDER BY t.employeeFullNameEn";
			return (List<MstEmployeeEntity>) entityManager.createQuery(HQL).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public int getpayCommissionAgainstEmployee(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select b.pay_commission_code from employee_mst a inner join pay_commission_mst b on a.pay_commission_code = b.pay_commission_code where a.sevaarth_id ='"
				+ sevaarthId + "'";
		Query query = currentSession.createNativeQuery(hql);
		return (int) query.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findEmployeeAllowanceDeduction(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = " Select a.sevaarth_id,a.department_code,a.department_allowdeduc_code,b.employee_full_name_en,c.department_allowdeduc_name,c.is_type,c.department_allowdeduc_col_nm,"
				+ "cc.group_name_en,cc.gis_amount,b.grade_pay,b.pyhical_handicapped,ccc.group_name_en as gisgroup, c.method_name,c.is_allowdeduc_type_sum,c.is_rule_based,c.is_non_computation_component,c.is_non_government,c.is_loan_adv from employee_allowdeduc_mpg a inner join  employee_mst b on b.employee_id = a.employee_id "
				+ " inner join department_allowdeduc_mst c on a.department_allowdeduc_code = c.department_allowdeduc_code  inner join cadre_group_mst  cc    on b.emp_class = cc.id left outer join cadre_group_mst  ccc    on CAST(b.gisgroup AS integer)  = ccc.id  "
				+ " where UPPER(a.sevaarth_id)= UPPER(:sevaarthId)  and c.is_active='1' ORDER BY c.is_type,c.department_allowdeduc_seq ASC  ";
		Query query = currentSession.createNativeQuery(hql);
		query.setParameter("sevaarthId", sevaarthId.trim());

		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EmployeeAllowDeducComponentAmtEntity findGRPComponentsData(String sevaarthId, int allowDedCode) {
		try {
			String HQL = "FROM EmployeeAllowDeducComponentAmtEntity as  t  where t.sevaarthId = '" + sevaarthId
					+ "' and t.deptallowcode = " + allowDedCode + "";
			return (EmployeeAllowDeducComponentAmtEntity) entityManager.createQuery(HQL).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public LoanEmployeeDtlsEntity findGPFADetails(String sevaarthid, int commoncodeComponentGpfaCode) {
		try {
			String HQL = "FROM LoanEmployeeDtlsEntity as  t  where t.totalRecoveredInst<=t.loanprininstno and t.sevaarthid ='"
					+ sevaarthid + "' and t.loanactivateflag = 1";
			return (LoanEmployeeDtlsEntity) entityManager.createQuery(HQL).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public LoanEmployeeDtlsEntity findGPFAdvDetails(String sevaarthid, int commoncodeComponentGpfaCode) {
		try {
			String HQL = "FROM LoanEmployeeDtlsEntity as  t  where t.totalRecoveredInstGpfII<=t.sancInstGpfII and t.sevaarthid ='"
					+ sevaarthid + "' and t.loanactivateflag = 1";
			System.out.println("-----------" + HQL);
			return (LoanEmployeeDtlsEntity) entityManager.createQuery(HQL).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findDDOScreenDataTable(long loc_id) {

		Session currentSession = entityManager.unwrap(Session.class);

		String hql = "SELECT cast(CM.loc_id as int),CM.loc_name FROM org_ddo_mst DM,cmn_location_mst CM \r\n"
				+ "WHERE DM.location_code = '" + loc_id + "' AND cast(CM.loc_id as varchar) = DM.hod_loc_code";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getInstitueDtls(String ddocode) {
		Session currentSession = entityManager.unwrap(Session.class);
		// String hql = "select org_inst_name,tel_no_1,tel_no_2,email_id from
		// org_inst_mst where ddo_reg_id in (select ddo_reg_id from ddo_reg_mst where
		// ddo_code='"
		// + ddocode + "')";
		String hql = "select org_inst_name,tel_no_1,tel_no_2,email_id from org_inst_mst where cast(ddo_reg_id as bigint) ='06710100040'";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
		/*
		 * String HQL = "FROM MstCadreEntity as t ORDER BY t.cadreId DESC"; return
		 * (List<MstCadreEntity>) entityManager.createQuery(HQL).getResultList();
		 */
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCadreMstData(long fielddeptid) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT cadre_id,cadre_name from mst_dcps_cadre where field_dept_id = '" + fielddeptid + "' ";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();

	}

	@Override
	public List<MstCadreGroupEntity> getGISGroup() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select DISTINCT a.LOOKUP_ID,a.LOOKUP_NAME from CMN_LOOKUP_MST a inner join MST_DCPS_CADRE b on a.LOOKUP_ID=cast(b.GROUP_ID as bigint)";
		Query query = currentSession.createNativeQuery(hql);
		List<Object[]> lstObject = query.list();
		List<MstCadreGroupEntity> lstMstCadreGroupEntity = new ArrayList<>();
		for (Object[] object : lstObject) {
			MstCadreGroupEntity mstCadreGroupEntity = new MstCadreGroupEntity();
			mstCadreGroupEntity.setId(StringHelperUtils.isNullLong(object[0]));
			mstCadreGroupEntity.setGroup_name_en(StringHelperUtils.isNullString(object[1]));
			mstCadreGroupEntity.setGroup_name_mh(StringHelperUtils.isNullString(object[1]));
			lstMstCadreGroupEntity.add(mstCadreGroupEntity);
		}
		return lstMstCadreGroupEntity;
		/*
		 * List<MstCadreGroupEntity> result = null; result =
		 * entityManager.createQuery("from MstCadreGroupEntity",
		 * MstCadreGroupEntity.class).getResultList(); return result;
		 */
	}

	@Override
	public List<MstRoleEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getFieldDeptId(long loc_id) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = " Select cast(hod_loc_code as int) from org_ddo_mst where location_code = '" + loc_id + "'";
		Query query = currentSession.createNativeQuery(HQL);
		int result = (int) query.list().get(0);
		return result;
	}

	@Override
	public List<Object[]> findEmployeeConfigurationpayScaleSeven(int payScaleSeven) {
		int payScaleId = payScaleSeven;
		Query query;
		Session hibSession = entityManager.unwrap(Session.class);

		if (payScaleId < 31) {

			String Hql = "select pay_scale_id,pay_scale_code,scale_grade_pay from pay_scale_sixpc_mst where  pay_scale_id="
					+ payScaleSeven + " order by pay_scale_id";

			// query = hibSession.createNativeQuery("select * from payband_gp_state_7pc
			// where
			// level_id = "+payScaleId+" order by level_id");
			query = hibSession.createNativeQuery(Hql);

		} else {
			query = hibSession.createNativeQuery(
					"select scale_name, commission_id,scale_grade_pay as gradePay,scale_desc,scale_id from hr_eis_scale_mst where scale_id = "
							+ payScaleId + " order by scale_id");

		}

		// select pay_scale_id,pay_scale_code,scale_grade_pay from pay_scale_sixpc_mst
		// where pay_scale_id="+payScaleSeven+"

		System.out.println(">>>" + query.getQueryString());
		List<Object[]> scales = query.list();
		return scales;

	}

	@Override
	public List<Object[]> findEmployeeConfigurationGetSixPayScale(int payCommission) {
		Session hibSession = entityManager.unwrap(Session.class);
		Query query = hibSession.createNativeQuery(
				"select scale_name, commission_id,scale_grade_pay,scale_desc,pay_scale_code from pay_scale_sixpc_mst  where commission_id= "
						+ payCommission + " and  scale_name is not null order by  scale_grade_pay  ");
		List<Object[]> scales = query.list();
		return scales;
	}

	@SuppressWarnings("unchecked")

	@Override
	public List<Object[]> getgroupname(String caderid) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT lookup.LOOKUP_ID,cad.SUPER_ANTUN_AGE,lookup.LOOKUP_NAME FROM mst_dcps_cadre cad inner join CMN_LOOKUP_MST lookup on lookup.LOOKUP_ID = cast(cad.GROUP_ID as bigint) where cad.CADRE_ID = '"
				+ caderid + "'";

		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
		/*
		 * String HQL = "FROM MstCadreEntity as t ORDER BY t.cadreId DESC"; return
		 * (List<MstCadreEntity>) entityManager.createQuery(HQL).getResultList();
		 */
	}

	@Override
	public List<Object[]> getCadreGroupMstDataNew(String cadreid) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT lookup.LOOKUP_NAME,cad.SUPER_ANTUN_AGE FROM mst_dcps_cadre cad inner join CMN_LOOKUP_MST lookup on lookup.LOOKUP_ID = cast(cad.GROUP_ID as bigint) where cad.CADRE_ID = '"
				+ cadreid + "'";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<AppoinmentEntity> getAppoitnment(String language) {
		// TODO Auto-generated method stub
		String HQL = "FROM AppoinmentEntity t ORDER BY t.appointmentName DESC";
		return (List<AppoinmentEntity>) entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public List<Object[]> getSvnPayscale() {

		Session hibSession = entityManager.unwrap(Session.class);
		Query query = hibSession.createNativeQuery("select * from payband_gp_state_7pc  order by level_id");
		List<Object[]> scales = query.list();
		return scales;
	}

	@Override

	public List<Object[]> getSvnPcData(String clmn) {

		Session hibSession = entityManager.unwrap(Session.class);
		Query query = hibSession
				.createNativeQuery("SELECT state_matrix_7pc_id,s_" + clmn + " FROM state_matrix_7pc_mst ");
		List<Object[]> lstsvnpcdata = query.list();
		return lstsvnpcdata;
	}

	@Override
	public List<Object[]> getDesignationMstData(long fielddeptId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = " SELECT ODM.designation_id,ODM.designation_name FROM designation_mst ODM, MST_DCPS_DESIGNATION MDD "
				+ "WHERE ODM.designation_id = MDD.ORG_DESIGNATION_ID AND MDD.FIELD_DEPT_ID = '" + fielddeptId
				+ "' ORDER by upper(ODM.designation_name) ";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> findEmployeeConfigurationGetCurrentPost(Long designationId, String ddocode,
			String currpostcode, long loc_id) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer();
		hql.append(
				"Select p.POST_ID,r.post_name from org_post_mst p inner join org_post_details_rlt r on r.post_id = p.post_id and p.activate_flag = 1 inner join MST_DCPS_DDO_OFFICE off on off.DCPS_DDO_OFFICE_MST_ID =p.office_id ");
		hql.append("where r.LOC_ID = '" + loc_id + "' and (p.END_DATE > now() or p.END_DATE is null)");
		hql.append("and p.post_Id not in (select RL.post_detail_Id ");
		hql.append("from employee_mst RL where RL.post_detail_Id is not null and rl.ddo_Code =(select ddo_code ");
		hql.append("from org_ddo_mst where location_code= '" + loc_id + "')) ");
		hql.append("and r.DSGN_ID = '" + designationId + "' ");
		hql.append("and p.ACTIVATE_FLAG = 1 ");
		hql.append("and p.POST_TYPE_LOOKUP_ID in (10001198130,10001198129,10001198155) ");
		System.out.println("\n " + hql);

		Query query = currentSession.createNativeQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<Object[]> employeeConfigurationGetCurrenOffice(long postdetailid, String userName, long locId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer();

		hql.append(
				"Select off.DCPS_DDO_OFFICE_MST_ID,off.OFF_NAME,off.ADDRESS1,off.TEL_NO1,off.TEL_NO2,off.fax,off.EMAIL,off.ADDRESS2,off.OFFICE_CITY_CLASS from MST_DCPS_DDO_OFFICE off ");
		hql.append("inner join org_post_mst o on off.DCPS_DDO_OFFICE_MST_ID = o.office_id ");
		hql.append("where o.POST_ID = '" + postdetailid + "'");

		Query query = currentSession.createNativeQuery(hql.toString());
		return query.list();
	}

	@Override
	public List<Object[]> employeeConfigurationGetCurrenOfficeAddress(long adminDepartmentId, String userName,
			long locId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer();

		hql.append("Select ADDRESS1,TEL_NO1,TEL_NO2,fax,EMAIL,ADDRESS2,OFFICE_CITY_CLASS from MST_DCPS_DDO_OFFICE ");
		hql.append("where DCPS_DDO_OFFICE_MST_ID = '" + adminDepartmentId + "' ");
		Query query = currentSession.createNativeQuery(hql.toString());
		return query.list();
	}

	public List<Object[]> getBankBranch(String strbankid) {
		Session hibSession = entityManager.unwrap(Session.class);
		Query query = hibSession.createNativeQuery(
				"select bank_branch_id,bank_branch_name,bank_branch_code from bank_branch_mst where bank_code="
						+ strbankid);
		List<Object[]> lstbankbranchdata = query.list();
		return lstbankbranchdata;
	}

	@Override
	public List<Object[]> getPfSeries(String accmainby) {
		// TODO Auto-generated method stub
		Long lLngParentLookupId = null;
		if (accmainby.equals("700092")) {
			lLngParentLookupId = 700098l;
		}

		if (accmainby.equals("700093")) {
			lLngParentLookupId = 700181l;
		}
		Session hibSession = entityManager.unwrap(Session.class);
		Query query = hibSession
				.createNativeQuery("SELECT Lookup_id,lookup_name FROM cmn_lookup_mst where parent_lookup_id = '"
						+ lLngParentLookupId + "' ");
		List<Object[]> lstPfSeries = query.list();
		return lstPfSeries;
	}

	// @Override
	// public List<MstBankBranchEntity> getIfscCodeByBranchId(int branchId) {
	// // TODO Auto-generated method stub
	// String HQL = "FROM MstBankBranchEntity as t where t.bankBranchId="+branchId;
	// return (List<MstBankBranchEntity>)
	// entityManager.createQuery(HQL).getResultList();
	// }

	@Override
	public MstEmployeeEntity findbyemplid(Long employeeId) {
		MstEmployeeEntity objDept = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objDept = currentSession.get(MstEmployeeEntity.class, employeeId);
		return objDept;
	}

	@Override
	public MstDcpsDetailsEntity findbyDcpsid(Long dcpsid) {
		MstDcpsDetailsEntity objDept = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objDept = currentSession.get(MstDcpsDetailsEntity.class, dcpsid);
		return objDept;
	}

	@Override
	public MstGpfDetailsEntity findbyGPFid(Long gpf_id) {
		MstGpfDetailsEntity objDept = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objDept = currentSession.get(MstGpfDetailsEntity.class, gpf_id);
		return objDept;
	}

	@Override
	public MstGisdetailsEntity findbyGisid(Long gisid) {
		MstGisdetailsEntity objDept = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objDept = currentSession.get(MstGisdetailsEntity.class, gisid);
		return objDept;
	}

	@Override
	public int updateEmployeeConfiguration(MstEmployeeEntity objEntity, MstEmployeeModel mstEmployeeModel,
			MstNomineeDetailsEntity[] lArrNomineeDtls) {
		Session currentSession = entityManager.unwrap(Session.class);
		objEntity.setDcpsgpfflag(mstEmployeeModel.getDcpsgpfflag());
		// currentSession.update(objEntity);
		currentSession.save(objEntity);

		long resNmnDtls = deleteNomineeDtls(objEntity.getEmployeeId());
		if (lArrNomineeDtls != null)
			for (Integer lInt = 0; lInt < lArrNomineeDtls.length; lInt++) {
				if (lArrNomineeDtls[lInt] != null) {
					lArrNomineeDtls[lInt].setEmployeeId(objEntity.getEmployeeId());
					currentSession.save(lArrNomineeDtls[lInt]);
				}
			}

		// if (mstEmployeeModel.getNomineeid() != null) {
		// MstNomineeDetailsEntity objEntity4 = new MstNomineeDetailsEntity();
		// objEntity4.setNomineeid(mstEmployeeModel.getNomineeid());
		// objEntity4.setCreateddate(new Date());
		// objEntity4.setCreatedid(mstEmployeeModel.getCreatedUserId());
		// objEntity4.setDob(mstEmployeeModel.getRdob());
		// objEntity4.setIsactive("Y");
		// objEntity4.setNomineeaddress(mstEmployeeModel.getNomineeaddress());
		// // objEntity4.setNomineeid(null);
		// objEntity4.setNomineename(mstEmployeeModel.getNomineename());
		// objEntity4.setPercent_share(mstEmployeeModel.getPercent_share());
		// objEntity4.setRelation(mstEmployeeModel.getRelation());
		// objEntity4.setUpdatedate(mstEmployeeModel.getUpdatedDate());
		// objEntity4.setUpdateid(mstEmployeeModel.getUpdatedUserId());
		// objEntity4.setEmployeeId(objEntity.getEmployeeId());
		// currentSession.update(objEntity4);
		// } else if (mstEmployeeModel.getRelation() != null &&
		// mstEmployeeModel.getRdob() != null
		// && mstEmployeeModel.getNomineeaddress() != null &&
		// mstEmployeeModel.getNomineename() != null
		// && mstEmployeeModel.getPercent_share() != null)
		// if (!mstEmployeeModel.getRelation().equals("0") &&
		// !mstEmployeeModel.getRdob().equals("")
		// && !mstEmployeeModel.getNomineeaddress().equals("") &&
		// !mstEmployeeModel.getNomineename().equals("")
		// && mstEmployeeModel.getPercent_share() != 0) {
		// MstNomineeDetailsEntity objEntity4 = new MstNomineeDetailsEntity();
		// objEntity4.setCreateddate(new Date());
		// objEntity4.setCreatedid(mstEmployeeModel.getCreatedUserId());
		// objEntity4.setDob(mstEmployeeModel.getRdob());
		// objEntity4.setIsactive("Y");
		// objEntity4.setNomineeaddress(mstEmployeeModel.getNomineeaddress());
		// // objEntity4.setNomineeid(null);
		// objEntity4.setNomineename(mstEmployeeModel.getNomineename());
		// objEntity4.setPercent_share(mstEmployeeModel.getPercent_share());
		// objEntity4.setRelation(mstEmployeeModel.getRelation());
		// objEntity4.setUpdatedate(mstEmployeeModel.getUpdatedDate());
		// objEntity4.setUpdateid(mstEmployeeModel.getUpdatedUserId());
		// objEntity4.setEmployeeId(objEntity.getEmployeeId());
		// currentSession.save(objEntity4);
		// }

		return (Integer) 1;
	}

	@Override
	public Long deleteNomineeDtls(Long empid) {
		Session currentSession = entityManager.unwrap(Session.class);
		long result = 0l;
		try {
			String hql4 = "delete from nominee_details_mst where employee_id = " + empid;
			Query query4 = currentSession.createNativeQuery(hql4);
			result = query4.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public BigInteger findbySevaarthCount(String sevaarth) {
		Session currentSession = entityManager.unwrap(Session.class);
		List list = new ArrayList();
		BigInteger rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select count(*) from employee_mst where sevaarth_id like '" + sevaarth + "%' ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = BigInteger.valueOf(Long.valueOf(list.get(0).toString()));
		return rtnStr;
	}

	@Override
	public List saveEmployeeConfiguration(MstEmployeeEntity objEntity, MstEmployeeModel mstEmployeeModel,
			MstNomineeDetailsEntity[] lArrNomineeDtls) {

		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(objEntity);
		if (mstEmployeeModel.getDcpsaccountmaintainby() != null && mstEmployeeModel.getPfacno() != null)
			if (!mstEmployeeModel.getDcpsaccountmaintainby().equals("0") && !mstEmployeeModel.getPfacno().equals("")) {
				MstDcpsDetailsEntity objEntity1 = new MstDcpsDetailsEntity();
				objEntity1.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				objEntity1.setAccountmaintainby(mstEmployeeModel.getDcpsaccountmaintainby());
				objEntity1.setCreateddate(new Date());
				objEntity1.setCreatedid(mstEmployeeModel.getCreatedUserId());
				objEntity1.setPfacno(mstEmployeeModel.getPfacno());
				objEntity1.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				objEntity1.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity1.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity1);
			}
		if (mstEmployeeModel.getAccountmaintainby() != null && mstEmployeeModel.getPfacno() != null
				&& mstEmployeeModel.getPfseries() != null)
			if (!mstEmployeeModel.getAccountmaintainby().equals("0") && !mstEmployeeModel.getPfacno().equals("")
					&& !mstEmployeeModel.getPfseries().equals("0")) {
				MstGpfDetailsEntity objEntity2 = new MstGpfDetailsEntity();
				objEntity2.setAccountmaintainby(mstEmployeeModel.getAccountmaintainby());
				objEntity2.setCreateddate(new Date());
				objEntity2.setCreatedid(mstEmployeeModel.getCreatedUserId());
				objEntity2.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				objEntity2.setPfacno(mstEmployeeModel.getPfacno());
				objEntity2.setPfdescription(mstEmployeeModel.getPfseries());
				objEntity2.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				objEntity2.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity2.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity2);
			}
		if (mstEmployeeModel.getGisapplicable() != null && mstEmployeeModel.getGisgroup() != null
				&& mstEmployeeModel.getMembership_date() != null)
			if (!mstEmployeeModel.getGisapplicable().equals("0") && !mstEmployeeModel.getGisgroup().equals("0")
					&& !mstEmployeeModel.getMembership_date().equals("")) {
				MstGisdetailsEntity objEntity3 = new MstGisdetailsEntity();
				objEntity3.setCreateddate(new Date());
				objEntity3.setCreatedid(mstEmployeeModel.getCreatedUserId());
				objEntity3.setGisapplicable(mstEmployeeModel.getGisapplicable());
				objEntity3.setGisgroup(mstEmployeeModel.getGisgroup());
				objEntity3.setIsactive("Y");
				objEntity3.setMembership_date(mstEmployeeModel.getMembership_date());
				objEntity3.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				objEntity3.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity3.setEmployeeId(objEntity.getEmployeeId());
				objEntity3.setRemark(mstEmployeeModel.getGisRemark());
				currentSession.save(objEntity3);
			}
		long resNmnDtls = deleteNomineeDtls(objEntity.getEmployeeId());
		if (lArrNomineeDtls != null)
			for (Integer lInt = 0; lInt < lArrNomineeDtls.length; lInt++) {
				if (lArrNomineeDtls[lInt] != null) {
					lArrNomineeDtls[lInt].setEmployeeId(objEntity.getEmployeeId());
					currentSession.save(lArrNomineeDtls[lInt]);
				}
			}
		// if (mstEmployeeModel.getRelation() != null && mstEmployeeModel.getRdob() !=
		// null
		// && mstEmployeeModel.getNomineeaddress() != null &&
		// mstEmployeeModel.getNomineename() != null
		// && mstEmployeeModel.getPercent_share() != null)
		// if (!mstEmployeeModel.getRelation().equals("0") &&
		// !mstEmployeeModel.getRdob().equals("")
		// && !mstEmployeeModel.getNomineeaddress().equals("") &&
		// !mstEmployeeModel.getNomineename().equals("")
		// && mstEmployeeModel.getPercent_share() != 0) {
		// MstNomineeDetailsEntity objEntity4 = new MstNomineeDetailsEntity();
		// objEntity4.setCreateddate(new Date());
		// objEntity4.setCreatedid(mstEmployeeModel.getCreatedUserId());
		// objEntity4.setDob(mstEmployeeModel.getRdob());
		// objEntity4.setIsactive("Y");
		// objEntity4.setNomineeaddress(mstEmployeeModel.getNomineeaddress());
		// // objEntity4.setNomineeid(null);
		// objEntity4.setNomineename(mstEmployeeModel.getNomineename());
		// objEntity4.setPercent_share(mstEmployeeModel.getPercent_share());
		// objEntity4.setRelation(mstEmployeeModel.getRelation());
		// objEntity4.setUpdatedate(mstEmployeeModel.getUpdatedDate());
		// objEntity4.setUpdateid(mstEmployeeModel.getUpdatedUserId());
		// objEntity4.setEmployeeId(objEntity.getEmployeeId());
		// currentSession.save(objEntity4);
		// }

		List result = new ArrayList();
		result.add(saveId);
		result.add(objEntity.getEmployeeId());

		// return (Integer) saveId;
		return result;
	}

	@Override
	public String updateImagePath(String photopath, String signpath, Long empid) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "update employee_mst set photo_attachment_id = '" + photopath + "' , signature_attachment_id = '"
				+ signpath + "' where employee_id = '" + empid + "'";
		Query query = currentSession.createNativeQuery(hql);
		query.executeUpdate();
		return "save";
	}

	@Override
	public List<MstEmployeeEntity> getApproveEmployeeDetails(String strddo) {
		// TODO Auto-generated method stub

		Session currentSession = entityManager.unwrap(Session.class);

		List<Object[]> result = null;
		List<MstEmployeeEntity> result1 = new ArrayList<MstEmployeeEntity>();
		StringBuffer strQuery = new StringBuffer();

		try {
			strQuery.append(
					"select employee_id,employee_full_name_en,sevaarth_id,designation_code,ddo_code from employee_mst  where is_active=3 and dcps_gpf_flag='N' and ddo_code in (");
			strQuery.append(
					"select ddo_code from org_ddo_mst where ddo_code in (select dmr.zp_ddo_code from rlt_zp_ddo_map dmr ");
			strQuery.append("inner join org_ddo_mst drm  on drm.ddo_code = dmr.rept_ddo_code  where drm.ddo_code='"
					+ strddo + "'))");
			Query query = currentSession.createNativeQuery(strQuery.toString());
			System.out.println("query---" + query);
			System.out.println("strQuery---" + strQuery);
			// result = entityManager.createQuery( strQuery.toString(),
			// MstEmployeeEntity.class ).getResultList();

			result = query.list();

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			// logger.info("stack trace exceptionend");
		}

		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			MstEmployeeEntity mstEmployeeEntity = new MstEmployeeEntity();
			// mstEmployeeEntity.setEmployeeId((Long) object[0]);
			mstEmployeeEntity.setEmployeeId(Long.valueOf(object[0].toString()));
			mstEmployeeEntity.setEmployeeFullNameEn(object[1].toString().toUpperCase());
			if (object[2] != null)
				mstEmployeeEntity.setSevaarthId(object[2].toString());
			mstEmployeeEntity.setDesignationCode(Long.valueOf(object[3].toString()));
			mstEmployeeEntity.setDdoCode(object[4].toString());
			result1.add(mstEmployeeEntity);
			// logger.info("mstEmployeeEntity="+mstEmployeeEntity);
		}
		return result1;
	}

	@Override
	public String getDesignationName(String strDesgId) {
		String strDeptNm = "";
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT a.designation_code, " + "a.designation_name, " + "a.designation_short_name "
				+ "FROM   designation_mst a where a.designation_code=" + strDesgId;
		Query query = currentSession.createNativeQuery(hql);
		List<Object[]> lstprop = query.list();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				strDeptNm = StringHelperUtils.isNullString(objLst[1]);
			}
		}
		return strDeptNm;
	}

	@Override
	public MstEmployeeModel getEmployeeinfo(Long employeeId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);

		List<MstEmployeeEntity> result = null;
		List<MstDcpsDetailsEntity> result1 = null;
		List<MstGpfDetailsEntity> result2 = null;
		List<MstGisdetailsEntity> result3 = null;
		List<MstNomineeDetailsEntity> result4 = null;
		MstEmployeeModel mstEmployeeModel = new MstEmployeeModel();

		try {
			result = entityManager
					.createQuery("from MstEmployeeEntity where employeeId=" + employeeId, MstEmployeeEntity.class)
					.getResultList();
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				MstEmployeeEntity mstEmployeeEntity = (MstEmployeeEntity) iterator.next();
				// Employee Details Start
				if (mstEmployeeEntity.getUidNo() != null) {
					String uidNoArray = String.valueOf(mstEmployeeEntity.getUidNo());
					if (uidNoArray.length() == 12) {
						mstEmployeeModel.setUidNo1(uidNoArray.substring(0, Math.min(0 + 4, uidNoArray.length())));
						mstEmployeeModel.setUidNo2(uidNoArray.substring(4, Math.min(4 + 4, uidNoArray.length())));
						mstEmployeeModel.setUidNo3(uidNoArray.substring(8, Math.min(8 + 4, uidNoArray.length())));

					}
				}
				mstEmployeeModel.setSevaarthId(mstEmployeeEntity.getSevaarthId());
				mstEmployeeModel.setEmployeeId(mstEmployeeEntity.getEmployeeId());
				mstEmployeeModel.setEidNo(mstEmployeeEntity.getEidNo());
				mstEmployeeModel.setSalutation(mstEmployeeEntity.getSalutation());
				mstEmployeeModel.setEmployeeFullNameEn(mstEmployeeEntity.getEmployeeFullNameEn());
				mstEmployeeModel.setEmployeeFNameEn(mstEmployeeEntity.getEmployeeFNameEn().toUpperCase());
				if (mstEmployeeEntity.getEmployeeMNameEn() != null)
					mstEmployeeModel.setEmployeeMNameEn(mstEmployeeEntity.getEmployeeMNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeLNameEn(mstEmployeeEntity.getEmployeeLNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeFullNameMr(mstEmployeeEntity.getEmployeeFullNameMr());
				mstEmployeeModel.setEmployeeFNameMr(mstEmployeeEntity.getEmployeeFNameMr());
				mstEmployeeModel.setEmployeeLNameMr(mstEmployeeEntity.getEmployeeLNameMr());
				mstEmployeeModel.setEmployeeMotherName(mstEmployeeEntity.getEmployeeMotherName());
				mstEmployeeModel.setBuckleNo(mstEmployeeEntity.getBuckleNo());
				// if (mstEmployeeEntity.getGender() == 'M') {
				// mstEmployeeModel.setGender('1');
				// } else if (mstEmployeeEntity.getGender() == 'F') {
				// mstEmployeeModel.setGender('2');
				// } else {
				// mstEmployeeModel.setGender('3');
				// }
				mstEmployeeModel.setGender(mstEmployeeEntity.getGender());
				mstEmployeeModel.setReligionCode(mstEmployeeEntity.getReligionCode());
				mstEmployeeModel.setMaritalStatus(mstEmployeeEntity.getMaritalStatus());
				mstEmployeeModel.setEmployeeMNameMr(mstEmployeeEntity.getEmployeeMNameMr());
				mstEmployeeModel.setDob(mstEmployeeEntity.getDob());
				mstEmployeeModel.setDoj(mstEmployeeEntity.getDoj());
				if (mstEmployeeEntity.getAddress1() != null)
					mstEmployeeModel.setAddress1(mstEmployeeEntity.getAddress1().toUpperCase());
				if (mstEmployeeEntity.getAddress2() != null)
					mstEmployeeModel.setAddress2(mstEmployeeEntity.getAddress2().toUpperCase());
				if (mstEmployeeEntity.getAddress3() != null)
					mstEmployeeModel.setAddress3(mstEmployeeEntity.getAddress3().toUpperCase());
				mstEmployeeModel.setLocality(mstEmployeeEntity.getLocality());
				mstEmployeeModel.setStateCode(mstEmployeeEntity.getStateCode());
				mstEmployeeModel.setDistrictCode(mstEmployeeEntity.getDistrictCode());
				mstEmployeeModel.setPinCode(mstEmployeeEntity.getPinCode());
				mstEmployeeModel.setPhysicallyHandicapped(mstEmployeeEntity.getPhysicallyHandicapped());
				mstEmployeeModel.setMobileNo1(mstEmployeeEntity.getMobileNo1());
				mstEmployeeModel.setEmailId(mstEmployeeEntity.getEmailId());
				mstEmployeeModel.setPanNo(mstEmployeeEntity.getPanNo());
				mstEmployeeModel.setDdoCode(mstEmployeeEntity.getDdoCode());
				mstEmployeeModel.setMorequalification(mstEmployeeEntity.getMorequalification());
				mstEmployeeModel.setSecqualification(mstEmployeeEntity.getSecqualification());

				// Employee Details End

				// Department Details Start
				mstEmployeeModel.setParentAdminDepartmentId(mstEmployeeEntity.getFieldDepartmentCode());
				mstEmployeeModel.setParentFieldDepartmentId(mstEmployeeEntity.getParentFieldDepartmentCode());
				// if (mstEmployeeEntity.getParentFieldDepartmentCode() != null)
				// mstEmployeeModel.setParentFieldDepartmentId((mstEmployeeEntity.getParentFieldDepartmentCode().longValue()));

				mstEmployeeModel.setSubCorporationId(mstEmployeeEntity.getSubCorporationId());
				mstEmployeeModel.setAdminDepartmentId(mstEmployeeEntity.getParentAdminDepartmentCode());
				mstEmployeeModel.setFieldDepartmentId(mstEmployeeEntity.getFieldDepartmentCode());
				mstEmployeeModel.setIsChangeParentDepartment(mstEmployeeEntity.getIsChangeParentDepartment());
				mstEmployeeModel.setReasonForChngParentFieldDept(mstEmployeeEntity.getReasonForChngParentFieldDept());
				mstEmployeeModel.setCadreId(mstEmployeeEntity.getCadreCode());
				mstEmployeeModel.setEmpClass(mstEmployeeEntity.getEmpClass());
				if (mstEmployeeEntity.getSuperAnnAge() != null)
					mstEmployeeModel.setSuperannuationage(mstEmployeeEntity.getSuperAnnAge());
				mstEmployeeModel.setEmpServiceEndDate(mstEmployeeEntity.getSuperAnnDate()); // by default set to
																							// retirement date added by
				//mstEmployeeModel.setAppointmentId(Long.valueOf(mstEmployeeEntity.getAppointment()));
				// mstEmployeeModel.setQid(Long.valueOf(mstEmployeeEntity.getQualification()));//
				// sudhir
				//mstEmployeeModel.setQualification(mstEmployeeEntity.getQualification());
				mstEmployeeModel.setSuperAnnDate(mstEmployeeEntity.getSuperAnnDate());
				mstEmployeeModel.setPayCommissionCode(mstEmployeeEntity.getPayCommissionCode());
				mstEmployeeModel.setFirstDesignationId(mstEmployeeEntity.getFirstDesignationCode());
				mstEmployeeModel.setDesignationId(mstEmployeeEntity.getDesignationCode());
				mstEmployeeModel.setPayscalelevelId(mstEmployeeEntity.getPayscalelevelId());
				if (mstEmployeeModel.getPayscalelevelId() != null)
					mstEmployeeModel.setSevenPCLevel(Long.valueOf(mstEmployeeEntity.getPayscalelevelId()));
				else
					mstEmployeeModel.setSevenPCLevel(0l);

				mstEmployeeModel.setSvnthpaybasic(mstEmployeeEntity.getSvnthpaybasic());

				mstEmployeeModel.setPayScaleCode(mstEmployeeEntity.getPayScaleCode());
				mstEmployeeModel.setPayScaleId(mstEmployeeEntity.getPayScaleCode());
				mstEmployeeModel.setPayInPayBand(mstEmployeeEntity.getPayInPayBand());
				mstEmployeeModel.setGradePay(mstEmployeeEntity.getGradePay());
				mstEmployeeModel.setTeaching(mstEmployeeEntity.getTeaching());
				/*
				 * if (mstEmployeeEntity.getBasicPay() != null) { Integer basic =
				 * mstEmployeeEntity.getBasicPay().intValue();
				 * mstEmployeeModel.setBasicPay(basic); } else { Integer basic =
				 * mstEmployeeEntity.getSevenPcBasic().intValue();
				 * mstEmployeeModel.setBasicPay(basic); }
				 */

				if (mstEmployeeModel.getPayCommissionCode() == 700005) {
					Integer basic = mstEmployeeEntity.getSevenPcBasic().intValue();
					mstEmployeeModel.setBasicPay(basic.doubleValue());
				}
				if (mstEmployeeModel.getPayCommissionCode() == 700016) {
					Integer basic = mstEmployeeEntity.getBasicPay().intValue();
					mstEmployeeModel.setBasicPay(basic.doubleValue());
				}

				mstEmployeeModel.setPostdetailid(mstEmployeeEntity.getPostdetailid());
				mstEmployeeModel.setDepartmentNameEn(mstEmployeeEntity.getDepartmentNameEn());
				mstEmployeeModel
						.setDtInitialAppointmentParentInst(mstEmployeeEntity.getDtInitialAppointmentParentInst());
				mstEmployeeModel.setInstituteAdd(mstEmployeeEntity.getInstituteAdd());
				mstEmployeeModel.setInstName(mstEmployeeEntity.getInstName());
				if (mstEmployeeEntity.getMobileNo2() != null)
					mstEmployeeModel.setMobileNo2(mstEmployeeEntity.getMobileNo2().longValue());
				mstEmployeeModel.setInstemail(mstEmployeeEntity.getInstemail());
				mstEmployeeModel.setDtJoinCurrentPost(mstEmployeeEntity.getDtJoinCurrentPost());
				mstEmployeeModel.setRemark(mstEmployeeEntity.getRemark());
				mstEmployeeModel.setCityClass(mstEmployeeEntity.getCityClass());
				mstEmployeeModel.setIndiApproveOrderNo(mstEmployeeEntity.getIndiApproveOrderNo());
				mstEmployeeModel.setApprovalByDdoDate(mstEmployeeEntity.getApprovalByDdoDate());
				mstEmployeeModel.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeModel.setHraBasic(mstEmployeeEntity.getHraBasic());
				// Department Details End

				// Bank/DCPS/NPS/GPF Details Start
				mstEmployeeModel.setBankId(mstEmployeeEntity.getBankCode());
				mstEmployeeModel.setIfscCode(mstEmployeeEntity.getIfscCode());
				mstEmployeeModel.setBankAcntNo(mstEmployeeEntity.getBankAcntNo());
				mstEmployeeModel.setBankBranchId(mstEmployeeEntity.getBankBranchCode());
				mstEmployeeModel.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeModel.setDcpsaccountmaintainby(mstEmployeeEntity.getDcpsaccountmaintainby());
				mstEmployeeModel.setPranNo(mstEmployeeEntity.getPranNo());
				mstEmployeeModel.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				mstEmployeeModel.setPfseries(mstEmployeeEntity.getPfseries());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				mstEmployeeModel.setPfdescription(mstEmployeeEntity.getPfdescription());

				// Bank/DCPS/NPS/GPF Details End

				// GIS Details Start
				mstEmployeeModel.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstEmployeeModel.setGisgroup(mstEmployeeEntity.getGisgroup());
				mstEmployeeModel.setMembership_date(mstEmployeeEntity.getMembership_date());
				mstEmployeeModel.setGisRemark(mstEmployeeEntity.getGisRemark());
				mstEmployeeModel.setDesignationId(mstEmployeeEntity.getDesignationCode());
				// GIS Details End

				mstEmployeeModel.setDcpsAccMainAuthority(mstEmployeeEntity.getDcpsAccMainAuthority());
				mstEmployeeModel.setDcpsAccNo(mstEmployeeEntity.getDcpsAccNo());

			}

			result1 = entityManager
					.createQuery("from MstDcpsDetailsEntity where employeeId=" + employeeId, MstDcpsDetailsEntity.class)
					.getResultList();
			for (Iterator iterator = result1.iterator(); iterator.hasNext();) {
				MstDcpsDetailsEntity mstEmployeeEntity = (MstDcpsDetailsEntity) iterator.next();
				// mstEmployeeModel.setIsactive(mstEmployeeEntity.getIsactive());
				mstEmployeeModel.setDcpsid(mstEmployeeEntity.getDcpsid());

				mstEmployeeModel.setDcpsaccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				// mstEmployeeModel.setCreateddate(new Date());
				// mstEmployeeModel.setCreatedid(mstEmployeeEntity.getCreatedUserId());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				// mstEmployeeModel.setUpdatedate(mstEmployeeEntity.getUpdatedDate());
				// mstEmployeeModel.setUpdateid(mstEmployeeEntity.getUpdatedUserId());

				// logger.info("mstEmployeeModel111="+mstEmployeeModel);
			}
			result2 = entityManager
					.createQuery("from MstGpfDetailsEntity where employeeId=" + employeeId, MstGpfDetailsEntity.class)
					.getResultList();
			for (Iterator iterator = result2.iterator(); iterator.hasNext();) {
				MstGpfDetailsEntity mstEmployeeEntity = (MstGpfDetailsEntity) iterator.next();

				mstEmployeeModel.setGpf_id(mstEmployeeEntity.getGpf_id());
				mstEmployeeModel.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				// mstEmployeeModel.setCreateddate(new Date());
				// mstEmployeeModel.setCreatedid(mstEmployeeModel.getCreatedUserId());
				// mstEmployeeModel.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				// mstEmployeeModel.setPfseries(mstEmployeeModel.getPfseries());
				mstEmployeeModel.setPfdescription("");

				// mstEmployeeModel.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				// mstEmployeeModel.setUpdateid(mstEmployeeModel.getUpdatedUserId());

			}
			result3 = entityManager
					.createQuery("from MstGisdetailsEntity where employeeId=" + employeeId, MstGisdetailsEntity.class)
					.getResultList();

			for (Iterator iterator = result3.iterator(); iterator.hasNext();) {
				MstGisdetailsEntity mstEmployeeEntity = (MstGisdetailsEntity) iterator.next();
				// mstEmployeeModel.setCreateddate(new Date());
				// mstEmployeeModel.setCreatedid(mstEmployeeModel.getCreatedUserId());
				mstEmployeeModel.setGisid(mstEmployeeEntity.getGisid());
				mstEmployeeModel.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstEmployeeModel.setGisgroup(mstEmployeeEntity.getGisgroup());
				// mstEmployeeModel.setIsactive("Y");
				mstEmployeeModel.setMembership_date(mstEmployeeEntity.getMembership_date());
				// mstEmployeeModel.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				// mstEmployeeModel.setUpdateid(mstEmployeeModel.getUpdatedUserId());

			}

			System.out.println("designation id >>>>>" + mstEmployeeModel.getDesignationId());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// logger.info("stack trace exceptionend");
		}

		return mstEmployeeModel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MstNomineeDetailsEntity> getNominees(String empId) {
		Session hibSession = entityManager.unwrap(Session.class);
		List<MstNomineeDetailsEntity> result = null;
		result = entityManager.createQuery("from MstNomineeDetailsEntity where employeeId =" + Long.valueOf(empId),
				MstNomineeDetailsEntity.class).getResultList();
		return result;
	}

	@Override
	public List<Object[]> GetCurrentPostByLvlTwo(long designationId, String ddocode, long loc_id) {
		// TODO Auto-generated method stub

		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer();
		hql.append(
				"Select p.POST_ID,r.post_name from org_post_mst p inner join org_post_details_rlt r on r.post_id = p.post_id and p.activate_flag = 1 inner join MST_DCPS_DDO_OFFICE off on off.DCPS_DDO_OFFICE_MST_ID =p.office_id ");
		hql.append("where r.LOC_ID = '" + loc_id + "' and (p.END_DATE > now() or p.END_DATE is null)");
		hql.append("and p.post_Id in (select RL.post_detail_Id ");
		hql.append("from employee_mst RL where RL.post_detail_Id is not null and rl.ddo_Code =(select ddo_code ");
		hql.append("from org_ddo_mst where location_code= '" + loc_id + "')) ");
		hql.append("and r.DSGN_ID = '" + designationId + "' ");
		hql.append("and p.ACTIVATE_FLAG = 1 ");
		hql.append("and p.POST_TYPE_LOOKUP_ID in (10001198130,10001198129,10001198155) ");
		System.out.println("\n " + hql);

		Query query = currentSession.createNativeQuery(hql.toString());
		return query.list();
	}

	@Override
	public String getCmnLocationMst(String ddoCode) {

		Session currentSession = entityManager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"SELECT loc.LOC_SHORT_NAME FROM CMN_LOCATION_MST loc inner join ORG_DDO_MST ddo on cast(ddo.HOD_LOC_CODE  as bigint) = loc.LOC_ID  where ddo.DDO_CODE= '"
						+ ddoCode + "' ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;
	}

	@Override
	public long getLocationCode(String getLocationCode) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = " Select cast(location_code as bigint) from org_ddo_mst where ddo_code  = '" + getLocationCode
				+ "'";
		Query query = currentSession.createNativeQuery(HQL);
		Long result = (Long) StringHelperUtils.isNullBigInteger(query.list().get(0)).longValue();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getCount(String tempSevarthEmpCode) {
		List list = new ArrayList();
		long count = 0;
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select max(sevaarth_id) from employee_mst hem where sevaarth_id like  '%" + tempSevarthEmpCode
				+ "%'";
		Query query = currentSession.createNativeQuery(hql);
		list = query.list();
		if (list != null && list.size() > 0) {
			if (list.get(0) != null) {
				String maxSevaarthCode = list.get(0).toString();
				count = Long
						.parseLong(maxSevaarthCode.substring(maxSevaarthCode.length() - 2, maxSevaarthCode.length()));
			}
		} else {
			count = 0;
		}
		return count;
	}

	@Modifying(clearAutomatically = true)
	@Override
	public List<Long> approveEmployeeConfiguration(String empid, String sevaarthid, String dcpsgpfflg) {
		Session currentSession = entityManager.unwrap(Session.class);
		// MstEmployeeEntity objEntity=new MstEmployeeEntity();
		// objEntity.setEmployeeId(empid);
		// currentSession.delete(objEntity);
		String hql = "update employee_mst set is_active=1 , sevaarth_id='" + sevaarthid + "' where employee_id = "
				+ empid;
		Query query = currentSession.createNativeQuery(hql);
		Integer result = query.executeUpdate();

		// Nominee Details
		String hql1 = "update nominee_details_mst set  sevaarth_id='" + sevaarthid + "' where employee_id = " + empid;
		Query query1 = currentSession.createNativeQuery(hql1);
		Integer result1 = query1.executeUpdate();

		// Gis Details
		String hql2 = "update gis_details_mst set  sevaarth_id='" + sevaarthid + "' where employee_id = " + empid;
		Query query2 = currentSession.createNativeQuery(hql2);
		Integer result2 = query2.executeUpdate();

		// DCPS and GPF Details
		String hql3 = "";
		if (dcpsgpfflg.equals("Y"))
			hql3 = "update dcps_details_mst  set  sevaarth_id='" + sevaarthid + "' where employee_id = " + empid;
		else
			hql3 = "update gpf_mst set sevaarth_id='" + sevaarthid + "' where employee_id = " + empid;

		Query query3 = currentSession.createNativeQuery(hql3);
		Integer result3 = query3.executeUpdate();

		String hql4 = "update employee_mst_details set is_active=1 , sevaarth_id='" + sevaarthid
				+ "' where employee_id = " + empid;
		Query query4 = currentSession.createNativeQuery(hql4);
		Integer result4 = query4.executeUpdate();

		List<Long> res = new ArrayList<Long>();
		res.add((long) result);
		return res;
	}

	@Override
	public int getSevaarthid(String sevaarthid) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		List list = new ArrayList();
		Long bg = null;
		int rtnStr = 0;
		StringBuffer query = new StringBuffer();
		query.append("select count(*) from org_user_mst where user_name ='" + sevaarthid + "' ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0) {
			bg = (Long) list.get(0);
			rtnStr = bg.intValue();
		}
		return rtnStr;
	}

	@Override
	public int checkSevaarthIdExistInGpfDetailMst(String empId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		List list = new ArrayList();
		Long bg = null;
		int rtnStr = 0;
		StringBuffer query = new StringBuffer();
		query.append("select count(*) from gpf_mst where employee_id=" + empId);

		System.out.println("select count(*) from gpf_mst where employee_id=" +empId);
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0) {
			bg = (Long) list.get(0);
			rtnStr = bg.intValue();
		}
		return rtnStr;
	}

	@Override
	public List<QualificationEntity> getQualification(String language) {
		// TODO Auto-generated method stub
		String HQL = "FROM QualificationEntity t ORDER BY t.qualification DESC";
		return (List<QualificationEntity>) entityManager.createQuery(HQL).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MstEmployeeEntity> getDcpsEmployeeDetails(String ddoCode, OrgUserMst orgUserMst) {

		Session currentSession = entityManager.unwrap(Session.class);
		List<Object[]> result = null;
		List<MstEmployeeEntity> result1 = new ArrayList<MstEmployeeEntity>();
		StringBuffer strQuery = new StringBuffer();

		try {
			strQuery.append("SELECT DISTINCT emp.employee_id, emp.employee_full_name_en, emp.sevaarth_id, ")
					.append("emp.designation_code, emp.ddo_code,dmr.rept_ddo_code,emp.gender,emp.dob FROM employee_mst emp ")
					.append("INNER JOIN org_ddo_mst ddo ON emp.ddo_code = ddo.ddo_code ")
					.append("INNER JOIN rlt_zp_ddo_map dmr ON ddo.ddo_code =emp.ddo_code ");

			if (orgUserMst.getMstRoleEntity().getRoleId() == 2) {
				strQuery.append(" WHERE emp.is_active = 3 AND emp.dcps_gpf_flag = 'Y' ")
						.append("AND dmr.rept_ddo_code = :ddocode");
			} else {
				strQuery.append(" WHERE emp.is_active = 4 AND emp.dcps_gpf_flag = 'Y' ")
						.append("AND dmr.FINAL_DDO_CODE = :ddocode");
			}

			Query query = currentSession.createNativeQuery(strQuery.toString());
			query.setParameter("ddocode", ddoCode);

			System.out.println("ddo_code"+ddoCode);
			result = query.list();

		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			MstEmployeeEntity mstEmployeeEntity = new MstEmployeeEntity();
			// mstEmployeeEntity.setEmployeeId((Long) object[0]);
			mstEmployeeEntity.setEmployeeId(Long.valueOf(object[0].toString()));
			mstEmployeeEntity.setEmployeeFullNameEn(object[1].toString().toUpperCase());
			if (object[2] != null)
				mstEmployeeEntity.setSevaarthId(object[2].toString());
			mstEmployeeEntity.setDesignationCode(Long.valueOf(object[3].toString()));
			mstEmployeeEntity.setDdoCode(object[4].toString());
			mstEmployeeEntity.setReptDdoCode(object[5].toString());
			mstEmployeeEntity.setGender(StringHelperUtils.isNullCharacter(object[6]));
			mstEmployeeEntity.setDob(StringHelperUtils.isNullDate(object[7]));
			
			result1.add(mstEmployeeEntity);
		}
		return result1;
	}

	@Modifying(clearAutomatically = true)
	@Override
	public List<Long> approveDcpsEmployeeConfiguration(String empid, String Dcpsnumber, String sevaarthid,
			String dcpsgpfflg) {
		Session currentSession = entityManager.unwrap(Session.class);

		String hql = "update employee_mst set is_active=1,is_dcps_generate='Y', dcps_no = '" + Dcpsnumber
				+ "',sevaarth_id='" + sevaarthid + "'  where employee_id = " + empid;
		Query query = currentSession.createNativeQuery(hql);
		Integer result = query.executeUpdate();

		// Nominee Details
		String hql1 = "update nominee_details_mst set  sevaarth_id='" + sevaarthid + "' where employee_id = " + empid;
		Query query1 = currentSession.createNativeQuery(hql1);
		Integer result1 = query1.executeUpdate();

		// Gis Details
		String hql2 = "update gis_details_mst set  sevaarth_id='" + sevaarthid + "' where employee_id = " + empid;
		Query query2 = currentSession.createNativeQuery(hql2);
		Integer result2 = query2.executeUpdate();

		// DCPS and GPF Details
		String hql3 = "";
		if (dcpsgpfflg.equals("Y"))
			hql3 = "update dcps_details_mst  set  dcps_no='" + Dcpsnumber + "',sevaarth_id='" + sevaarthid
					+ "' where employee_id = " + empid;
		else
			hql3 = "update gpf_mst set sevaarth_id='" + sevaarthid + "' where employee_id = " + empid;

		Query query3 = currentSession.createNativeQuery(hql3);
		Integer result3 = query3.executeUpdate();

		String hql4 = "update employee_mst_details set is_active=1, dcps_no = '" + Dcpsnumber + "',sevaarth_id='"
				+ sevaarthid + "'  where employee_id = " + empid;
		Query query4 = currentSession.createNativeQuery(hql4);
		Integer result4 = query4.executeUpdate();
		List<Long> res = new ArrayList<Long>();
		res.add((long) result);
		return res;
	}

	@Override
	public OrgUserMst saveUserInfo(OrgUserMst objuserInfo) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable userId = (Serializable) currentSession.save(objuserInfo);
		OrgUserMst save = currentSession.get(OrgUserMst.class, userId);
		return save;
	}

	@Override
	public OrgPostDetailsRlt findPostdetailById(Long postdetailid) {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	// public void saveUserId(String sevaarthId, long user_id) {
	// // TODO Auto-generated method stub
	//
	// }

	@Override
	public String saveUserId(String sevaarthId, long user_id) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "update employee_mst set user_id = " + user_id + " where sevaarth_id = '" + sevaarthId + "'";
		Query query = currentSession.createNativeQuery(hql);
		query.executeUpdate();

		String hql1 = "update employee_mst_details set user_id = " + user_id + " where sevaarth_id = '" + sevaarthId
				+ "'";
		Query query1 = currentSession.createNativeQuery(hql1);
		query1.executeUpdate();

		return "save";
	}

	@Override
	public MstEmployeeDetailEntity updateEmployeesDetails(Long empid) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);

		List<MstEmployeeEntity> result = null;
		List<MstDcpsDetailsEntity> result1 = null;
		List<MstGpfDetailsEntity> result2 = null;
		List<MstGisdetailsEntity> result3 = null;
		List<MstNomineeDetailsEntity> result4 = null;
		MstGpfDetailsHistEntity mstGpfDetailsHistEntity = new MstGpfDetailsHistEntity();
		MstEmployeeDetailEntity mstEmployeeDetailEntity = new MstEmployeeDetailEntity();
		MstGisdetailsHistEntity mstGisdetailsHistEntity = new MstGisdetailsHistEntity();

		try {
			result = entityManager
					.createQuery("from MstEmployeeEntity where employeeId=" + empid, MstEmployeeEntity.class)
					.getResultList();
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				MstEmployeeEntity mstEmployeeEntity = (MstEmployeeEntity) iterator.next();
				// Employee Details Start
				mstEmployeeDetailEntity.setUidNo3(mstEmployeeEntity.getUidNo());
				mstEmployeeDetailEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
				mstEmployeeDetailEntity.setEmployeeId(mstEmployeeEntity.getEmployeeId());
				mstEmployeeDetailEntity.setEidNo(mstEmployeeEntity.getEidNo());
				mstEmployeeDetailEntity.setSalutation(mstEmployeeEntity.getSalutation());
				mstEmployeeDetailEntity.setEmployeeFullNameEn(mstEmployeeEntity.getEmployeeFullNameEn());
				mstEmployeeDetailEntity.setEmployeeFNameEn(mstEmployeeEntity.getEmployeeFNameEn().toUpperCase());
				if (mstEmployeeEntity.getEmployeeMNameEn() != null)
					mstEmployeeDetailEntity.setEmployeeMNameEn(mstEmployeeEntity.getEmployeeMNameEn().toUpperCase());
				mstEmployeeDetailEntity.setEmployeeLNameEn(mstEmployeeEntity.getEmployeeLNameEn().toUpperCase());
				mstEmployeeDetailEntity.setEmployeeFullNameMr(mstEmployeeEntity.getEmployeeFullNameMr());
				mstEmployeeDetailEntity.setEmployeeFNameMr(mstEmployeeEntity.getEmployeeFNameMr());
				mstEmployeeDetailEntity.setEmployeeLNameMr(mstEmployeeEntity.getEmployeeLNameMr());
				mstEmployeeDetailEntity.setEmployeeMotherName(mstEmployeeEntity.getEmployeeMotherName());
				mstEmployeeDetailEntity.setBuckleNo(mstEmployeeEntity.getBuckleNo());
				mstEmployeeDetailEntity.setIsActive(mstEmployeeEntity.getIsActive());
//				if (mstEmployeeEntity.getGender() == 'M') {
//					mstEmployeeModel.setGender('1');
//				} else if (mstEmployeeEntity.getGender() == 'F') {
//					mstEmployeeModel.setGender('2');
//				} else {
//					mstEmployeeModel.setGender('3');
//				}
				mstEmployeeDetailEntity.setGender(mstEmployeeEntity.getGender());
				mstEmployeeDetailEntity.setReligionCode(mstEmployeeEntity.getReligionCode());
				mstEmployeeDetailEntity.setMaritalStatus(mstEmployeeEntity.getMaritalStatus());
				mstEmployeeDetailEntity.setEmployeeMNameMr(mstEmployeeEntity.getEmployeeMNameMr());
				mstEmployeeDetailEntity.setDob(mstEmployeeEntity.getDob());
				mstEmployeeDetailEntity.setDoj(mstEmployeeEntity.getDoj());
				if (mstEmployeeEntity.getAddress1() != null)
					mstEmployeeDetailEntity.setAddress1(mstEmployeeEntity.getAddress1().toUpperCase());
				if (mstEmployeeEntity.getAddress2() != null)
					mstEmployeeDetailEntity.setAddress2(mstEmployeeEntity.getAddress2().toUpperCase());
				if (mstEmployeeEntity.getAddress3() != null)
					mstEmployeeDetailEntity.setAddress3(mstEmployeeEntity.getAddress3().toUpperCase());
				mstEmployeeDetailEntity.setLocality(mstEmployeeEntity.getLocality());
				mstEmployeeDetailEntity.setStateCode(mstEmployeeEntity.getStateCode());
				mstEmployeeDetailEntity.setDistrictCode(mstEmployeeEntity.getDistrictCode());
				mstEmployeeDetailEntity.setPinCode(mstEmployeeEntity.getPinCode());
				mstEmployeeDetailEntity.setPhysicallyHandicapped(mstEmployeeEntity.getPhysicallyHandicapped());
				mstEmployeeDetailEntity.setMobileNo1(mstEmployeeEntity.getMobileNo1());
				mstEmployeeDetailEntity.setEmailId(mstEmployeeEntity.getEmailId());
				mstEmployeeDetailEntity.setPanNo(mstEmployeeEntity.getPanNo());
				mstEmployeeDetailEntity.setDdoCode(mstEmployeeEntity.getDdoCode());
				mstEmployeeDetailEntity.setMorequalification(mstEmployeeEntity.getMorequalification());
				mstEmployeeDetailEntity.setSecqualification(mstEmployeeEntity.getSecqualification());

				// Employee Details End

				// Department Details Start

				mstEmployeeDetailEntity.setIsChangeParentDepartment(mstEmployeeEntity.getIsChangeParentDepartment());
				mstEmployeeDetailEntity
						.setReasonForChngParentFieldDept(mstEmployeeEntity.getReasonForChngParentFieldDept());
				mstEmployeeDetailEntity.setCadreCode(mstEmployeeEntity.getCadreCode());
				mstEmployeeDetailEntity.setEmpClass(mstEmployeeEntity.getEmpClass());
				if (mstEmployeeEntity.getSuperAnnAge() != null)
					mstEmployeeDetailEntity.setSuperAnnAge(mstEmployeeEntity.getSuperAnnAge());
				mstEmployeeDetailEntity.setEmpServiceEndDate(mstEmployeeEntity.getSuperAnnDate()); // by default set to
				// retirement date added by
				mstEmployeeDetailEntity.setAppointment(mstEmployeeEntity.getAppointment());
				// mstEmployeeModel.setQid(Long.valueOf(mstEmployeeEntity.getQualification()));//
				// sudhir
				mstEmployeeDetailEntity.setQualification(mstEmployeeEntity.getQualification());
				mstEmployeeDetailEntity.setSuperAnnDate(mstEmployeeEntity.getSuperAnnDate());
				mstEmployeeDetailEntity.setPayCommissionCode(mstEmployeeEntity.getPayCommissionCode());
				mstEmployeeDetailEntity.setFirstDesignationCode(mstEmployeeEntity.getFirstDesignationCode());
				mstEmployeeDetailEntity.setDesignationCode(mstEmployeeEntity.getDesignationCode());
				mstEmployeeDetailEntity.setPayscalelevelId(mstEmployeeEntity.getPayscalelevelId());

				mstEmployeeDetailEntity.setSvnthpaybasic(mstEmployeeEntity.getSvnthpaybasic());

				mstEmployeeDetailEntity.setPayScaleCode(mstEmployeeEntity.getPayScaleCode());
				mstEmployeeDetailEntity.setPayInPayBand(mstEmployeeEntity.getPayInPayBand());
				mstEmployeeDetailEntity.setGradePay(mstEmployeeEntity.getGradePay());
				mstEmployeeDetailEntity.setTeaching(mstEmployeeEntity.getTeaching());
				mstEmployeeDetailEntity.setPayCommissionCode(mstEmployeeEntity.getPayCommissionCode());
				mstEmployeeDetailEntity.setBasicPay(mstEmployeeEntity.getBasicPay());
				mstEmployeeDetailEntity.setSevenPcBasic(mstEmployeeEntity.getSevenPcBasic());
				mstEmployeeDetailEntity.setSevenPcLevel(mstEmployeeEntity.getSevenPcLevel());

				mstEmployeeDetailEntity.setPostdetailid(mstEmployeeEntity.getPostdetailid());
				mstEmployeeDetailEntity.setUserId(mstEmployeeEntity.getUserId());
				mstEmployeeDetailEntity.setDepartmentNameEn(mstEmployeeEntity.getDepartmentNameEn());
				mstEmployeeDetailEntity
						.setDtInitialAppointmentParentInst(mstEmployeeEntity.getDtInitialAppointmentParentInst());
				mstEmployeeDetailEntity.setInstituteAdd(mstEmployeeEntity.getInstituteAdd());
				mstEmployeeDetailEntity.setInstName(mstEmployeeEntity.getInstName());
				if (mstEmployeeEntity.getMobileNo2() != null)
					mstEmployeeDetailEntity.setMobileNo2(mstEmployeeEntity.getMobileNo2().longValue());
				mstEmployeeDetailEntity.setInstemail(mstEmployeeEntity.getInstemail());
				mstEmployeeDetailEntity.setDtJoinCurrentPost(mstEmployeeEntity.getDtJoinCurrentPost());
				mstEmployeeDetailEntity.setRemark(mstEmployeeEntity.getRemark());
				mstEmployeeDetailEntity.setCityClass(mstEmployeeEntity.getCityClass());
				mstEmployeeDetailEntity.setIndiApproveOrderNo(mstEmployeeEntity.getIndiApproveOrderNo());
				mstEmployeeDetailEntity.setApprovalByDdoDate(mstEmployeeEntity.getApprovalByDdoDate());
				mstEmployeeDetailEntity.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeDetailEntity.setHraBasic(mstEmployeeEntity.getHraBasic());
				// Department Details End

				// Bank/DCPS/NPS/GPF Details Start
				mstEmployeeDetailEntity.setBankCode(mstEmployeeEntity.getBankCode());
				mstEmployeeDetailEntity.setIfscCode(mstEmployeeEntity.getIfscCode());
				mstEmployeeDetailEntity.setBankAcntNo(mstEmployeeEntity.getBankAcntNo());
				mstEmployeeDetailEntity.setBankBranchCode(mstEmployeeEntity.getBankBranchCode());
				mstEmployeeDetailEntity.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeDetailEntity.setDcpsaccountmaintainby(mstEmployeeEntity.getDcpsaccountmaintainby());
				mstEmployeeDetailEntity.setPranNo(mstEmployeeEntity.getPranNo());
				mstEmployeeDetailEntity.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				mstEmployeeDetailEntity.setPfseries(mstEmployeeEntity.getPfseries());
				mstEmployeeDetailEntity.setPfacno(mstEmployeeEntity.getPfacno());
				mstEmployeeDetailEntity.setPfdescription(mstEmployeeEntity.getPfdescription());

				// Bank/DCPS/NPS/GPF Details End

				// GIS Details Start
				mstEmployeeDetailEntity.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstEmployeeDetailEntity.setGisgroup(mstEmployeeEntity.getGisgroup());
				mstEmployeeDetailEntity.setMembership_date(mstEmployeeEntity.getMembership_date());
				mstEmployeeDetailEntity.setGisRemark(mstEmployeeEntity.getGisRemark());
				mstEmployeeDetailEntity.setDesignationCode(mstEmployeeEntity.getDesignationCode());
				currentSession.save(mstEmployeeDetailEntity);

				// GIS Details End

			}

			result2 = entityManager
					.createQuery("from MstGpfDetailsEntity where employeeId=" + empid, MstGpfDetailsEntity.class)
					.getResultList();
			for (Iterator iterator = result2.iterator(); iterator.hasNext();) {
				MstGpfDetailsEntity mstEmployeeEntity = (MstGpfDetailsEntity) iterator.next();

				mstGpfDetailsHistEntity.setGpf_id(mstEmployeeEntity.getGpf_id());
				mstGpfDetailsHistEntity.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				mstGpfDetailsHistEntity.setCreateddate(mstEmployeeEntity.getCreateddate());
				mstGpfDetailsHistEntity.setCreatedid(mstEmployeeEntity.getCreatedid());
				mstGpfDetailsHistEntity.setEmployeeId(mstEmployeeEntity.getEmployeeId());
				mstGpfDetailsHistEntity.setPfacno(mstEmployeeEntity.getPfacno());
				mstGpfDetailsHistEntity.setPfdescription(mstEmployeeEntity.getPfdescription());
				mstGpfDetailsHistEntity.setIsactive(mstEmployeeEntity.getIsactive());
				mstGpfDetailsHistEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
				mstGpfDetailsHistEntity.setUpdatedate(mstEmployeeEntity.getUpdatedate());
				mstGpfDetailsHistEntity.setUpdateid(mstEmployeeEntity.getUpdateid());
				currentSession.save(mstGpfDetailsHistEntity);

			}
			result3 = entityManager
					.createQuery("from MstGisdetailsEntity where employeeId=" + empid, MstGisdetailsEntity.class)
					.getResultList();

			for (Iterator iterator = result3.iterator(); iterator.hasNext();) {
				MstGisdetailsEntity mstEmployeeEntity = (MstGisdetailsEntity) iterator.next();
				mstGisdetailsHistEntity.setGisid(mstEmployeeEntity.getGisid());
				mstGisdetailsHistEntity.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstGisdetailsHistEntity.setGisgroup(mstEmployeeEntity.getGisgroup());
				mstGisdetailsHistEntity.setMembership_date(mstEmployeeEntity.getMembership_date());
				mstGisdetailsHistEntity.setCreateddate(mstEmployeeEntity.getCreateddate());
				mstGisdetailsHistEntity.setCreatedid(mstEmployeeEntity.getCreatedid());
				mstGisdetailsHistEntity.setEmployeeId(mstEmployeeEntity.getEmployeeId());
				mstGisdetailsHistEntity.setIsactive(mstEmployeeEntity.getIsactive());
				mstGisdetailsHistEntity.setUpdatedate(mstEmployeeEntity.getUpdatedate());
				mstGisdetailsHistEntity.setUpdateid(mstEmployeeEntity.getUpdateid());
				mstGisdetailsHistEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
				currentSession.save(mstGisdetailsHistEntity);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// logger.info("stack trace exceptionend");
		}

		return mstEmployeeDetailEntity;
	}

	public List<Object[]> findAllEmployeesByDDOName(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		// String hql = "Select a.ddo_code,a.sevaarth_id,a.employee_full_name_en FROM
		// employee_mst a where a.ddo_code = '"+ ddoCode +"'";
		String hql = "Select a.sevaarth_id,a.employee_full_name_en,b.designation_name,c.department_name_en,a.employee_id,a.pay_commission_code,d.commission_name_en,a.dcps_gpf_flag,a.emp_service_end_date,e.bill_description  FROM employee_mst a,designation_mst b,department_mst c,\r\n"
				+ " pay_commission_mst d,bill_group_mst e where a.designation_code = b.designation_code and a.admin_department_code = c.department_code and a.pay_commission_code=d.pay_commission_code and e.bill_group_id = a.billgroup_id and a.billgroup_id is not null  and a.is_active='1' and  a.ddo_code = '"
				+ userName + "'  order by a.employee_full_name_en"; // and emp_service_end_date > now()
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public List<Object[]> findDraftCaseList(OrgUserMst messages, Long CASESTATUS) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT a.employee_full_name_en, a.dob, "
				+ "a.employee_id, a.remark,a.is_active  from employee_mst a "
				+ " WHERE a.is_active in (0,-1) AND a.ddo_code = '" + messages.getDdoCode() + "'"
				+ " ORDER BY a.employee_full_name_en";
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	public Integer deleteEmployeesByIds(List<Long> employeeIds, OrgUserMst orgUserMst) {

		Session currentSession = entityManager.unwrap(Session.class);

		List<MstEmployeeEntity> employees = currentSession
				.createQuery("FROM MstEmployeeEntity e WHERE e.employeeId IN (:employeeIds) AND e.ddoCode = :ddoCode",
						MstEmployeeEntity.class)
				.setParameterList("employeeIds", employeeIds).setParameter("ddoCode", orgUserMst.getDdoCode())
				.getResultList();
		if (employees.size() > 0) {
			for (MstEmployeeEntity mstEmployeeEntity : employees) {
				deleteFileIfExists(mstEmployeeEntity.getPhotoAttachmentId());
				deleteFileIfExists(mstEmployeeEntity.getSignatureAttachmentId());
			}
		}

		Query deleteEmployeesQuery = currentSession.createQuery(
				"DELETE FROM MstEmployeeEntity e WHERE e.employeeId IN (:employeeIds) AND e.ddoCode = :ddoCode");
		deleteEmployeesQuery.setParameterList("employeeIds", employeeIds);
		deleteEmployeesQuery.setParameter("ddoCode", orgUserMst.getDdoCode());
		int deletedCount = deleteEmployeesQuery.executeUpdate();

		if (deletedCount > 0) {
			String[] relatedEntities = { "MstDcpsDetailsEntity", "MstNomineeDetailsEntity", "MstGpfDetailsEntity",
					"MstGisdetailsEntity" };

			for (String entity : relatedEntities) {
				Query deleteRelatedQuery = currentSession
						.createQuery("DELETE FROM " + entity + " e WHERE e.employeeId IN (:employeeIds)");
				deleteRelatedQuery.setParameterList("employeeIds", employeeIds);
				deleteRelatedQuery.executeUpdate();
			}

		}

		return deletedCount > 0 ? 1 : 0;
	}

	private void deleteFileIfExists(String filePath) {
		if (filePath != null && !filePath.isEmpty()) {
			File file = new File(filePath);
			if (file.exists()) {
				boolean deleted = file.delete();
				if (deleted) {
					System.out.println("Deleted file: " + filePath);
				} else {
					System.err.println("Failed to delete file: " + filePath);
				}
			}
		}
	}

	@Override
	public List<Long> rejectEmployeeConfiguration(String empid) {
		Session currentSession = entityManager.unwrap(Session.class);
		Query updateRelatedQuery = currentSession
				.createQuery("UPDATE MstEmployeeEntity SET isActive = :activeStatus WHERE employeeId = :employeeId");
		updateRelatedQuery.setParameter("activeStatus", -1L);
		updateRelatedQuery.setParameter("employeeId", Long.valueOf(empid));

		int resultCount = updateRelatedQuery.executeUpdate();
		if (resultCount > 0) {
			return Collections.singletonList(Long.valueOf(empid));
		} else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<CmnLookupMst> getLookupValuesForParentAG(Long agType) {
		Session currentSession = entityManager.unwrap(Session.class);
		Long parentLookUpId = 0l;
		if (agType != null) {
			if (agType == 700092) {
				parentLookUpId = 700098l;
			}

			if (agType == 700093) {
				parentLookUpId = 700181l;
			}
		}

		String HQL = "FROM CmnLookupMst as  t  where  t.parentLookupId =" + parentLookUpId;
		return currentSession.createQuery(HQL).getResultList();
	}

	@Override
	public String approveDcpsEmpByDdo(String empid, OrgUserMst message) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "update employee_mst set is_active =4,is_dcps_generate='N' where employee_id =" + empid;
		Query query = currentSession.createNativeQuery(hql);
		query.executeUpdate();
		return "save";
	}

	@Override
	public List<ZpRltDdoMap> findDdoByReptDdoCode(String reptDdoCode) {
		String HQL = "FROM ZpRltDdoMap as  t  where  t.reptDdoCode = '" + reptDdoCode + "'";
		return  entityManager.createQuery(HQL).getResultList();
	}
}
