package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.BrokenPeriodAllowDeducEntity;
import com.mahait.gov.in.entity.BrokenPeriodEntity;
import com.mahait.gov.in.entity.MstDcpsDetailsEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstGisdetailsEntity;
import com.mahait.gov.in.entity.MstGpfDetailsEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.model.MstEmployeeModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class BrokenPeriodRepoImpl implements BrokenPeriodRepo {

//	@PersistenceUnit(unitName = "readwrite.config")
	@PersistenceContext
	EntityManager entityManager;

//	protected final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("unchecked")
	@Override
	public MstEmployeeModel getEmployeeinfo(String sevaarthId, String ddocode) {

		Session currentSession = entityManager.unwrap(Session.class);
		// String hql = "Select
		// a.sevaarth_id,a.employee_full_name_en,b.designation_name,c.department_name_en,a.employee_id
		// FROM employee_mst a,designation_mst b,department_mst c where a.designation_id
		// = b.designation_id and a.admin_department_id = c.department_id and
		// a.billgroup_id is not null order by a.employee_full_name_en";
		List<MstEmployeeEntity> result = null;
		List<MstDcpsDetailsEntity> result1 = null;
		List<MstGpfDetailsEntity> result2 = null;
		List<MstGisdetailsEntity> result3 = null;
		List<MstNomineeDetailsEntity> result4 = null;
		MstEmployeeModel mstEmployeeModel = new MstEmployeeModel();

		try {
			result = entityManager.createQuery(
					"FROM MstEmployeeEntity WHERE UPPER(sevaarthId) = UPPER(:sevaarthId) AND ddoCode = :ddoCode",
					MstEmployeeEntity.class).setParameter("sevaarthId", sevaarthId.trim())
					.setParameter("ddoCode", ddocode).getResultList();

			// sevaarthId='" + sevaarthid.trim()+"' or

			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				MstEmployeeEntity mstEmployeeEntity = (MstEmployeeEntity) iterator.next();
				mstEmployeeModel.setEmployeeId(mstEmployeeEntity.getEmployeeId());
				mstEmployeeModel.setSevaarthId(mstEmployeeEntity.getSevaarthId());
//				mstEmployeeModel.setSevaarthId("0");
				mstEmployeeModel.setGender(mstEmployeeEntity.getGender());
				mstEmployeeModel.setSalutation(mstEmployeeEntity.getSalutation());
				mstEmployeeModel.setEmployeeFullNameEn(mstEmployeeEntity.getEmployeeFullNameEn());
				mstEmployeeModel.setEmployeeFNameEn(mstEmployeeEntity.getEmployeeFNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeMNameEn(mstEmployeeEntity.getEmployeeMNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeLNameEn(mstEmployeeEntity.getEmployeeLNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeFullNameMr(mstEmployeeEntity.getEmployeeFullNameMr());
				mstEmployeeModel.setEmployeeFNameMr(mstEmployeeEntity.getEmployeeFNameMr());
				mstEmployeeModel.setEmployeeMNameMr(mstEmployeeEntity.getEmployeeMNameMr());
				mstEmployeeModel.setEmployeeLNameMr(mstEmployeeEntity.getEmployeeLNameMr());
				mstEmployeeModel.setEmployeeMotherName(mstEmployeeEntity.getEmployeeMotherName());
				mstEmployeeModel.setMaritalStatus(mstEmployeeEntity.getMaritalStatus());
				mstEmployeeModel.setDob(mstEmployeeEntity.getDob());
				mstEmployeeModel.setBloodGroup(mstEmployeeEntity.getBloodGroup());
				mstEmployeeModel.setMobileNo1(mstEmployeeEntity.getMobileNo1());
				mstEmployeeModel.setMobileNo2(mstEmployeeEntity.getMobileNo2());
				mstEmployeeModel.setLandlineNo(mstEmployeeEntity.getLandlineNo());
				mstEmployeeModel.setEmailId(mstEmployeeEntity.getEmailId());
				mstEmployeeModel.setReligionCode(mstEmployeeEntity.getReligionCode());
				mstEmployeeModel.setEidNo(mstEmployeeEntity.getEidNo());
				mstEmployeeModel.setUidNo(mstEmployeeEntity.getUidNo());
				if (mstEmployeeEntity.getUidNo() != null) {
					String uidNoArray = String.valueOf(mstEmployeeEntity.getUidNo());
					if (uidNoArray.length() == 12) {
						mstEmployeeModel.setUidNo1(uidNoArray.substring(0, Math.min(0 + 4, uidNoArray.length())));
						mstEmployeeModel.setUidNo2(uidNoArray.substring(4, Math.min(4 + 4, uidNoArray.length())));
						mstEmployeeModel.setUidNo3(uidNoArray.substring(8, Math.min(8 + 4, uidNoArray.length())));
					}
				}
				mstEmployeeModel.setPanNo(mstEmployeeEntity.getPanNo());
				mstEmployeeModel.setAddress1(mstEmployeeEntity.getAddress1());
				mstEmployeeModel.setAddress2(mstEmployeeEntity.getAddress2());
				mstEmployeeModel.setAddress3(mstEmployeeEntity.getAddress3());
				mstEmployeeModel.setPinCode(mstEmployeeEntity.getPinCode());
				mstEmployeeModel.setVillageCode(mstEmployeeEntity.getVillageCode());
				mstEmployeeModel.setVillageName(mstEmployeeEntity.getVillageName());
				mstEmployeeModel.setTalukaCode(mstEmployeeEntity.getVillageCode());
				mstEmployeeModel.setDistrictCode(mstEmployeeEntity.getDistrictCode());
				mstEmployeeModel.setStateCode(mstEmployeeEntity.getStateCode());
				mstEmployeeModel.setCountryCode(mstEmployeeEntity.getCountryCode());
				// mstEmployeeModel.setAppointmentDate(mstEmployeeEntity.getAppointmentDate());
				mstEmployeeModel.setDoj(mstEmployeeEntity.getDoj());
				mstEmployeeModel.setUserId(mstEmployeeEntity.getUserId());
				mstEmployeeModel.setCadreId(mstEmployeeEntity.getCadreCode());
				mstEmployeeModel.setEmpClass(mstEmployeeEntity.getEmpClass());
				mstEmployeeModel.setFirstDesignationId(mstEmployeeEntity.getFirstDesignationCode());
				mstEmployeeModel.setDesignationId(mstEmployeeEntity.getDesignationCode());
				mstEmployeeModel.setParentAdminDepartmentId(mstEmployeeEntity.getParentAdminDepartmentCode());
				mstEmployeeModel.setParentFieldDepartmentId(mstEmployeeEntity.getParentFieldDepartmentCode());
				mstEmployeeModel.setAdminDepartmentId(mstEmployeeEntity.getAdminDepartmentCode());
				mstEmployeeModel.setFieldDepartmentId(mstEmployeeEntity.getFieldDepartmentCode());
				mstEmployeeModel.setCurrentOfficeId(mstEmployeeEntity.getCurrentOfficeCode());
				mstEmployeeModel.setPayCommissionCode(mstEmployeeEntity.getPayCommissionCode());
				mstEmployeeModel.setPayScaleCode(mstEmployeeEntity.getPayScaleCode());
				mstEmployeeModel.setPayInPayBand(mstEmployeeEntity.getPayInPayBand());
				mstEmployeeModel.setGradePay(mstEmployeeEntity.getGradePay());
				mstEmployeeModel.setBankId(mstEmployeeEntity.getBankCode());
				mstEmployeeModel.setIfscCode(mstEmployeeEntity.getIfscCode());
				mstEmployeeModel.setBankAcntNo(mstEmployeeEntity.getBankAcntNo());
				mstEmployeeModel.setBankBranchId(mstEmployeeEntity.getBankBranchCode());
				mstEmployeeModel.setDdoCode(mstEmployeeEntity.getDdoCode());
				mstEmployeeModel.setApprovalByDdoDate(mstEmployeeEntity.getApprovalByDdoDate());
				mstEmployeeModel.setBillgroupId(mstEmployeeEntity.getBillGroupId());

				if (mstEmployeeEntity.getPayCommissionCode() == 700016)
					mstEmployeeModel.setBasicPay(mstEmployeeEntity.getBasicPay());
				if (mstEmployeeEntity.getPayCommissionCode() == 700005)
					mstEmployeeModel.setSevenPcBasic(mstEmployeeEntity.getSevenPcBasic());

				mstEmployeeModel.setPercentageOfBasic(mstEmployeeEntity.getPercentageOfBasic());
				mstEmployeeModel.setHeadActCode(mstEmployeeEntity.getHeadActCode());
				mstEmployeeModel.setEmployeeType(mstEmployeeEntity.getEmployeeType());
				mstEmployeeModel.setIsActive(mstEmployeeEntity.getIsActive());
				mstEmployeeModel.setEmpServiceEndDate(mstEmployeeEntity.getEmpServiceEndDate());
				mstEmployeeModel.setSuperAnnDate(mstEmployeeEntity.getSuperAnnDate());
				mstEmployeeModel.setWithEffectFromDate(mstEmployeeEntity.getWithEffectFromDate());
				mstEmployeeModel.setGradeId(mstEmployeeEntity.getGradeId());
				mstEmployeeModel.setPhotoAttachmentId(mstEmployeeEntity.getPhotoAttachmentId());
				mstEmployeeModel.setSignatureAttachmentId(mstEmployeeEntity.getSignatureAttachmentId());
				mstEmployeeModel.setCreatedUserId(mstEmployeeEntity.getCreatedUserId());
				mstEmployeeModel.setCreatedDate(new Date());
				mstEmployeeModel.setUpdatedDate(mstEmployeeEntity.getUpdatedDate());
				mstEmployeeModel.setUpdatedUserId(mstEmployeeEntity.getUpdatedUserId());
				mstEmployeeModel.setSvnthpaybasic(mstEmployeeEntity.getSvnthpaybasic());

				mstEmployeeModel.setPayscalelevelId(mstEmployeeEntity.getPayscalelevelId());
				mstEmployeeModel.setRemark(mstEmployeeEntity.getRemark());
				mstEmployeeModel.setPostdetailid(mstEmployeeEntity.getPostdetailid());
				mstEmployeeModel.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeModel.setSevenPCLevel(mstEmployeeEntity.getSevenPcLevel());
				mstEmployeeModel.setPhysicallyHandicapped(mstEmployeeEntity.getPhysicallyHandicapped());
				mstEmployeeModel.setGiscatagory(mstEmployeeEntity.getGiscatagory());
				mstEmployeeModel.setBegisCatg(mstEmployeeEntity.getBegisCatg());
				mstEmployeeModel.setSuperAnnDate(mstEmployeeEntity.getSuperAnnDate());

				mstEmployeeModel.setCityClass(mstEmployeeEntity.getCityClass());

				System.out.println("mstEmployeeEntity.getSuperAnnDate()------" + mstEmployeeEntity.getSuperAnnDate());

				mstEmployeeModel
						.setDtInitialAppointmentParentInst(mstEmployeeEntity.getDtInitialAppointmentParentInst());
				mstEmployeeModel
						.setDesignationName(getDesignationName(mstEmployeeEntity.getDesignationCode().toString()));

			}

			result1 = entityManager
					.createQuery(" FROM MstDcpsDetailsEntity WHERE UPPER(sevaarthId) = UPPER(:sevaarthId)",
							MstDcpsDetailsEntity.class)
					.setParameter("sevaarthId", sevaarthId.trim()).getResultList();
			for (Iterator iterator = result1.iterator(); iterator.hasNext();) {
				MstDcpsDetailsEntity mstEmployeeEntity = (MstDcpsDetailsEntity) iterator.next();
				mstEmployeeModel.setDcpsid(mstEmployeeEntity.getDcpsid());

				mstEmployeeModel.setDcpsaccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				mstEmployeeModel.setDcpsno(mstEmployeeEntity.getDcpsno());
			}
			result2 = entityManager
					.createQuery(" FROM MstGpfDetailsEntity WHERE UPPER(sevaarthId) = UPPER(:sevaarthId)",
							MstGpfDetailsEntity.class)
					.setParameter("sevaarthId", sevaarthId.trim()).getResultList();

			for (Iterator iterator = result2.iterator(); iterator.hasNext();) {
				MstGpfDetailsEntity mstEmployeeEntity = (MstGpfDetailsEntity) iterator.next();

				mstEmployeeModel.setGpf_id(mstEmployeeEntity.getGpf_id());
				mstEmployeeModel.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				mstEmployeeModel.setPfseries(mstEmployeeEntity.getPfdescription());
				mstEmployeeModel.setPfdescription("");

			}
			result3 = entityManager.createQuery("FROM MstGisdetailsEntity WHERE UPPER(sevaarthId) = UPPER(:sevaarthId)",
					MstGisdetailsEntity.class).setParameter("sevaarthId", sevaarthId.trim()).getResultList();

			for (Iterator iterator = result3.iterator(); iterator.hasNext();) {
				MstGisdetailsEntity mstEmployeeEntity = (MstGisdetailsEntity) iterator.next();
				mstEmployeeModel.setGisid(mstEmployeeEntity.getGisid());
				mstEmployeeModel.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstEmployeeModel.setGisgroup(mstEmployeeEntity.getGisgroup());
				mstEmployeeModel.setMembership_date(mstEmployeeEntity.getMembership_date());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return mstEmployeeModel;
	}

	@Override
	public String getDesignationName(String strDesignationCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String strDesignationName = "";

		String sql = "SELECT a.designation_id, " + "a.designation_code, " + "a.designation_name, "
				+ "a.designation_short_name, " + "a.is_active " + "FROM designation_mst a "
				+ "WHERE a.designation_code = :designationCode";

		List<Object[]> lstres = currentSession.createNativeQuery(sql)
				.setParameter("designationCode", Long.valueOf(strDesignationCode)).getResultList();

		if (!lstres.isEmpty()) {
			Object[] objects = lstres.get(0); // Assuming the query returns at least one record.
			if (objects[2] != null) {
				strDesignationName = objects[2].toString();
			}
		}

		return strDesignationName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> fetchAllowDeducName(String sevaarthId, int billTyp) { // 1 -regular ,2 broken
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = null;
		if (billTyp != 2) {
			HQL = "select  COALESCE(deptallmt.department_allowdeduc_col_nm, deptallmt.department_allowdeduc_name) allded , deptallmt.is_type,deptallmt.department_allowdeduc_code,"
					+ "cgmst.group_name_en,cgmst.gis_amount,deptallmt.method_name,deptallmt.formulas,deptallmt.is_rule_based,deptallmt.is_non_computation_component,deptallmt.is_non_government,deptallmt.is_loan_adv"
					+ "  from  department_allowdeduc_mst deptallmt inner join employee_allowdeduc_mpg empalldecmpg on deptallmt.department_allowdeduc_code =  empalldecmpg.department_allowdeduc_code  inner join  employee_mst empmst on empmst.employee_id = empalldecmpg.employee_id inner join cadre_group_mst  cgmst    on empmst.emp_class = cgmst.id "
					+ "where UPPER(empalldecmpg.sevaarth_id)= UPPER(:sevaarthId) and deptallmt.is_type in (1,2,4,3) order by  deptallmt.department_allowdeduc_seq ";
		} else {
			HQL = "select  COALESCE(deptallmt.department_allowdeduc_col_nm, deptallmt.department_allowdeduc_name) allded , deptallmt.is_type,deptallmt.department_allowdeduc_code,"
					+ "cgmst.group_name_en,cgmst.gis_amount,deptallmt.method_name,deptallmt.formulas,deptallmt.is_rule_based,deptallmt.is_non_computation_component,deptallmt.is_non_government,deptallmt.is_loan_adv"
					+ "  from  department_allowdeduc_mst deptallmt inner join employee_allowdeduc_mpg empalldecmpg on deptallmt.department_allowdeduc_code =  empalldecmpg.department_allowdeduc_code  inner join  employee_mst empmst on empmst.employee_id = empalldecmpg.employee_id inner join cadre_group_mst  cgmst    on empmst.emp_class = cgmst.id "
					+ " where UPPER(empalldecmpg.sevaarth_id)= UPPER(:sevaarthId) and deptallmt.is_type in (1,2,4,3) and (deptallmt.is_non_government!=1) and deptallmt.department_allowdeduc_code not in(51,52,46) and deptallmt.broken_method_name is not null order by  deptallmt.department_allowdeduc_seq ";
		}
		Query query = currentSession.createNativeQuery(HQL).setParameter("sevaarthId", sevaarthId.trim());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> fetchAllowDeducNameForCalcEmpSalary(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);

		String hql = "SELECT COALESCE(deptallmt.department_allowdeduc_col_nm, deptallmt.department_allowdeduc_name) AS allded, "
				+ "deptallmt.is_type, deptallmt.department_allowdeduc_code, " + "cgmst.group_name_en, cgmst.gis_amount "
				+ "FROM department_allowdeduc_mst deptallmt "
				+ "INNER JOIN employee_allowdeduc_mpg empalldecmpg ON deptallmt.department_allowdeduc_code = empalldecmpg.department_allowdeduc_code "
				+ "INNER JOIN employee_mst empmst ON empmst.employee_id = empalldecmpg.employee_id "
				+ "INNER JOIN cadre_group_mst cgmst ON empmst.emp_class = cgmst.id "
				+ "WHERE UPPER(empalldecmpg.sevaarth_id) = UPPER(:sevaarthId) AND deptallmt.is_type IN (1, 2, 3, 4) "
				+ "ORDER BY deptallmt.department_allowdeduc_seq";

		Query query = currentSession.createNativeQuery(hql);
		query.setParameter("sevaarthId", sevaarthId.trim());
		return query.getResultList();
	}

	@Override
	public List saveBrokenPeriodPay(BrokenPeriodEntity[] lArrMstBrokenPeriodPay,
			List<BrokenPeriodAllowDeducEntity> lListRltBrokenPeriodAllow,
			List<BrokenPeriodAllowDeducEntity> lListRltBrokenPeriodDeduc) {

		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = null;
		Integer brokenperiodid = null;

		for (Integer lInt = 0; lInt < lArrMstBrokenPeriodPay.length; lInt++) {
			saveId = (Serializable) currentSession.save(lArrMstBrokenPeriodPay[lInt]);
		}

		for (Integer lInt = 0; lInt < lListRltBrokenPeriodAllow.size(); lInt++) {
			currentSession.save(lListRltBrokenPeriodAllow.get(lInt));

		}

		for (Integer lInt = 0; lInt < lListRltBrokenPeriodDeduc.size(); lInt++) {
			currentSession.save(lListRltBrokenPeriodDeduc.get(lInt));

		}
		List result = new ArrayList();
		result.add(saveId);
		result.add(brokenperiodid);

		return result;
	}

	@Override
	public Boolean checkBrokenPeriodPayExistsOrNot(Long lLongEmpId, Long lLongYearId, Long lLongMonthId,
			String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		List<BrokenPeriodEntity> finalList = new ArrayList();
		Boolean flag = true;
		lSBQuery.append(
				" FROM BrokenPeriodEntity WHERE empId = :empId AND yearId = :year AND monthId = :month AND ddoCode= :ddoCode");
		Query lQuery = currentSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("empId",lLongEmpId);
		lQuery.setParameter("year", Integer.valueOf(lLongYearId.intValue()));
		lQuery.setParameter("month", Integer.valueOf(lLongMonthId.intValue()));
		lQuery.setParameter("ddoCode", ddoCode.toString());

		finalList = lQuery.getResultList();

		if (finalList.size() == 0) {
			flag = false;
		}
		return flag;

	}

	@Override
	public List<BrokenPeriodEntity> getAddedBrokenPeriodPaysForEmp(Long lLongEmpId, Long lLongYearId, Long lLongMonthId,
			String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		List<BrokenPeriodEntity> finalList = new ArrayList();
		lSBQuery.append(
				" FROM BrokenPeriodEntity WHERE empId = :empId AND yearId = :year AND monthId = :month AND ddoCode= :ddoCode");

		Query lQuery = currentSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("empId", lLongEmpId);
		lQuery.setParameter("year", Integer.valueOf(lLongYearId.intValue()));
		lQuery.setParameter("month", Integer.valueOf(lLongMonthId.intValue()));
		lQuery.setParameter("ddoCode", ddoCode.toString());

		finalList = lQuery.getResultList();

		return finalList;
	}

	@Override
	public void deleteAllBrokenPeriodPaysForPk(Long lLongBrokenPrdId) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(" delete from BrokenPeriodEntity where brokenPeriodId = :brokenPeriodId");

		Query lQuery = currentSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("brokenPeriodId", lLongBrokenPrdId);
		lQuery.executeUpdate();

	}

	@Override
	public void deleteAllBrokenPeriodAllowancesForBrknPrdId(Long lLongBrokenPrdId) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(
				" delete from BrokenPeriodAllowDeducEntity where brokenPeriodId = :brokenPeriodId and istype=1");

		Query lQuery = currentSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("brokenPeriodId", lLongBrokenPrdId);
		lQuery.executeUpdate();

	}

	@Override
	public void deleteAllBrokenPeriodDeductionsForBrknPrdId(Long lLongBrokenPrdId) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();

		lSBQuery.append(
				" delete from BrokenPeriodAllowDeducEntity where brokenPeriodId = :brokenPeriodId and istype=2");

		Query lQuery = currentSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("brokenPeriodId", lLongBrokenPrdId);
		lQuery.executeUpdate();

	}

	@Override
	public List getAddedAllowancesForEmp(Long lLongRltBrokenPeriodId) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		List<Object[]> finalList = new ArrayList<Object[]>();

		lSBQuery.append(
				" SELECT RA.brokenPeriodAllowDeducId, RA.brokenPeriodEntity.brokenPeriodId, RA.allowDeducCode, RA.allowDeducAmt ");
		lSBQuery.append(" FROM BrokenPeriodAllowDeducEntity RA ");
		lSBQuery.append(" WHERE RA.brokenPeriodEntity.brokenPeriodId = :brokenPeriodId AND RA.istype = 1 ");
		lSBQuery.append(
				" AND   RA.deptEligibilityForAllowAndDeductEntity.isNonGovernment!=1 AND RA.deptEligibilityForAllowAndDeductEntity.departmentAllowdeducCode NOT IN(51,52,46)");
		lSBQuery.append(" ORDER BY RA.deptEligibilityForAllowAndDeductEntity.deptAllowDeducSeq  "); // Use DE for the
																									// ordering

		Query lQuery = currentSession.createQuery(lSBQuery.toString());
		lQuery.setParameter("brokenPeriodId", lLongRltBrokenPeriodId);
		finalList = lQuery.getResultList();
		for (Iterator iterator = finalList.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();

		}

		return finalList;
	}

	@Override
	public List getAddedDeductionsForEmp(Long lLongRltBrokenPeriodId) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		List finalList = new ArrayList();

		lSBQuery.append(
				" SELECT RD.brokenPeriodAllowDeducId,RD.brokenPeriodEntity.brokenPeriodId,RD.allowDeducCode,RD.allowDeducAmt");
		lSBQuery.append(" FROM BrokenPeriodAllowDeducEntity RD");
		lSBQuery.append(" WHERE   RD.brokenPeriodEntity.brokenPeriodId = :brokenPeriodId  and RD.istype in (2,3,4) ");
		lSBQuery.append(
				" AND   RD.deptEligibilityForAllowAndDeductEntity.isNonGovernment!=1 AND RD.deptEligibilityForAllowAndDeductEntity.departmentAllowdeducCode NOT IN(51,52,46)");
		lSBQuery.append(" ORDER BY RD.deptEligibilityForAllowAndDeductEntity.deptAllowDeducSeq ");

		Query lQuery = currentSession.createQuery(lSBQuery.toString());

		lQuery.setParameter("brokenPeriodId", lLongRltBrokenPeriodId);
		
		finalList = lQuery.getResultList();
		return finalList;

	}

	@Override
	public List getAllowancesListForGivenEmp(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		List listAllowances = null;
		StringBuilder lSBQuery = new StringBuilder();

		String HQL = "select  deptallmt.department_allowdeduc_code, COALESCE(deptallmt.department_allowdeduc_col_nm, deptallmt.department_allowdeduc_name) allded  "
				+ " from  department_allowdeduc_mst deptallmt inner join employee_allowdeduc_mpg empalldecmpg on deptallmt.department_allowdeduc_code =  empalldecmpg.department_allowdeduc_code "
				+ " where UPPER(empalldecmpg.sevaarth_id)= UPPER(:sevaarthId) and deptallmt.is_type in (1) and (deptallmt.is_non_government!=1) and deptallmt.department_allowdeduc_code not in(51,52,46) order by  deptallmt.department_allowdeduc_seq";
		Query lQuery = currentSession.createNativeQuery(HQL);
		lQuery.setParameter("sevaarthId", sevaarthId.trim());
		listAllowances = lQuery.getResultList();

		return listAllowances;
	}

	@Override
	public List getDeductionsListForGivenEmp(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		List listDeductions = null;
		StringBuilder lSBQuery = new StringBuilder();

		String HQL = "select  deptallmt.department_allowdeduc_code,COALESCE(deptallmt.department_allowdeduc_col_nm, deptallmt.department_allowdeduc_name) allded  from  "
				+ " department_allowdeduc_mst deptallmt inner join employee_allowdeduc_mpg empalldecmpg on deptallmt.department_allowdeduc_code =  empalldecmpg.department_allowdeduc_code "
				+ " where UPPER(empalldecmpg.sevaarth_id)= UPPER(:sevaarthId) and deptallmt.is_type in (2,4,3) and (deptallmt.is_non_government!=1) and deptallmt.department_allowdeduc_code not in(51,52,46) order by  deptallmt.department_allowdeduc_seq";
		Query lQuery = currentSession.createNativeQuery(HQL);
		lQuery.setParameter("sevaarthId", sevaarthId.trim());
		listDeductions =  lQuery.getResultList();

		return listDeductions;
	}

	@Override
	public Long getBrokenPeriodNextID() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = " select nextval('broken_period_pay_mst_broken_period_id_seq') ";
		Query query = currentSession.createNativeQuery(hql);
		Long result = ((Long) query.getResultList().get(0));
		return result.longValue();
	}

	@Override
	public String CheckBrkPrdMonthExitOrNot(String monthYear, String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		List finalList = new ArrayList();
		lSBQuery.append(" select count(*) from broken_period_pay_mst ");
		lSBQuery.append(" where to_char(from_date, 'MM-YYYY') = :monthYear and UPPER(sevaarth_id)=UPPER(:sevaarthId)");
		Query lQuery = currentSession.createNativeQuery(lSBQuery.toString());
		lQuery.setParameter("monthYear", monthYear);
		lQuery.setParameter("sevaarth_id", sevaarthId.trim());
		String result = (lQuery.getResultList().get(0)).toString();
		return result;
	}

	@Override
	public String isEmpMappedWithBillGroup(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		List finalList = new ArrayList();
		lSBQuery.append(
				"select coalesce(billgroup_id, 0) from employee_mst where UPPER(sevaarth_id)=UPPER(:sevaarthId)");
		Query lQuery = currentSession.createNativeQuery(lSBQuery.toString());
		lQuery.setParameter("sevaarth_id", sevaarthId.trim());
		String result = (lQuery.getResultList().get(0)).toString();
		return result;
	}

	@Override
	public int getSevaarthIdMappedWithPayBillProcessed(String sevaarthId, int month, int year, String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select count(*) from mst_dcps_bill_group  a\r\n"
				+ " inner join employee_mst b on a.bill_group_id =  b.billgroup_id and b.ddo_code = :ddoCode   "
				+ " inner join paybill_generation_trn_details c on b.sevaarth_id = c.sevaarth_id and paybill_month =:month  and paybill_year=:year "
				+ " inner join paybill_generation_trn d on c.paybill_generation_trn_id = d.paybill_generation_trn_id and d.is_active in(5,6) "
				+ " where UPPER(b.sevaarth_id)=UPPER(:sevaarthId)";
		Query query = currentSession.createNativeQuery(hql);
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("sevaarthId", sevaarthId.trim());
		query.setParameter("ddoCode", ddoCode);

		int result = ((Long) (query.getResultList().get(0))).intValue();
		return result;
	}

	@Override
	public int getSevaarthIdMappedWithPayBillInprogress(String sevaarthId, int month, int year, String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select count(*) from mst_dcps_bill_group  a\r\n"
				+ " inner join employee_mst b on a.bill_group_id =  b.billgroup_id    "
				+ " inner join paybill_generation_trn_details c on b.sevaarth_id = c.sevaarth_id  "
				+ " inner join paybill_generation_trn d on c.paybill_generation_trn_id = d.paybill_generation_trn_id "
				+ "where UPPER(b.sevaarth_id)=UPPER(:sevaarthId) and d.paybill_month =:month  and d.paybill_year=:year and d.is_active not in (14,8) and b.ddo_code = :ddoCode";
		Query query = currentSession.createNativeQuery(hql);
		query.setParameter("month", month);
		query.setParameter("year", year);
		query.setParameter("sevaarthId", sevaarthId.trim());
		query.setParameter("ddoCode", ddoCode);
		int result = ((Long) (query.getResultList().get(0))).intValue();
		return result;
	}

	@Override
	public List<Object[]> fetchAllowDeducNameDaArray(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select  COALESCE(deptallmt.department_allowdeduc_col_nm, deptallmt.department_allowdeduc_name) allded , "
				+ "deptallmt.is_type,deptallmt.department_allowdeduc_code,cgmst.group_name_en,cgmst.gis_amount  from  "
				+ "department_allowdeduc_mst deptallmt inner join employee_allowdeduc_mpg empalldecmpg on"
				+ " deptallmt.department_allowdeduc_code =  empalldecmpg.department_allowdeduc_code  inner join"
				+ "  employee_mst empmst on empmst.employee_id = empalldecmpg.employee_id inner join cadre_group_mst  cgmst "
				+ "   on empmst.emp_class = cgmst.id where UPPER(empalldecmpg.sevaarth_id)=UPPER(:sevaarthId) and deptallmt.department_allowdeduc_code  in (7,82,83,95,200) and deptallmt.is_type in (1,2,4,3) order by  deptallmt.department_allowdeduc_code ";
		Query query = currentSession.createNativeQuery(HQL);
		query.setParameter("sevaarthId", sevaarthId.trim());
		//System.out.println("rqw query>>" + query.getQueryString());
		/* List<Object[]> lstprop = query.list(); */
		return query.getResultList();
	}

	@Override
	public List getAllowancesListForGivenEmpDAArray(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		List listAllowances = null;
		StringBuilder lSBQuery = new StringBuilder();

		String HQL = "select  deptallmt.department_allowdeduc_code, COALESCE(deptallmt.department_allowdeduc_col_nm, deptallmt.department_allowdeduc_name) allded   from"
				+ "  department_allowdeduc_mst deptallmt inner join employee_allowdeduc_mpg empalldecmpg on "
				+ "deptallmt.department_allowdeduc_code =  empalldecmpg.department_allowdeduc_code "
				+ " where UPPER(empalldecmpg.sevaarth_id)=UPPER(:sevaarthId) and  deptallmt.is_type=1 and deptallmt.department_allowdeduc_code  in (7,95) order by  deptallmt.department_allowdeduc_code";
		Query lQuery = currentSession.createNativeQuery(HQL);
		lQuery.setParameter("sevaarthId", sevaarthId.trim());
		listAllowances = lQuery.getResultList();

		return listAllowances;
	}

	@Override
	public List getDeductionsListForGivenEmpDAArray(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		List listDeductions = null;
		StringBuilder lSBQuery = new StringBuilder();

		String HQL = "select  deptallmt.department_allowdeduc_code,COALESCE(deptallmt.department_allowdeduc_col_nm, deptallmt.department_allowdeduc_name) allded  from"
				+ "  department_allowdeduc_mst deptallmt inner join employee_allowdeduc_mpg"
				+ " empalldecmpg on deptallmt.department_allowdeduc_code =  empalldecmpg.department_allowdeduc_code where"
				+ " UPPER(empalldecmpg.sevaarth_id)=UPPER(:sevaarthId) and   deptallmt.is_type in (1,2,3,4) and deptallmt.department_allowdeduc_code  in (82,83) order by  deptallmt.department_allowdeduc_code";
		Query lQuery = currentSession.createNativeQuery(HQL);
		lQuery.setParameter("sevaarthId", sevaarthId.trim());
		listDeductions = lQuery.getResultList();

		return listDeductions;
	}

	@Override
	public Object CheckBrkPrdMonthExitOrNot(String monthyear, String sevaarthid, Date fromDate, Date toDate) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "FROM BrokenPeriodEntity t " + "WHERE t.sevaarthid = :sevaarthid "
				+ "AND ((t.fromDate >= :fromDate AND t.toDate <= :toDate) "
				+ "OR t.fromDate BETWEEN :fromDate AND :toDate " + "OR t.toDate BETWEEN :fromDate AND :toDate)";

		Query query = currentSession.createQuery(hql).setParameter("sevaarthid", sevaarthid)
				.setParameter("fromDate", fromDate).setParameter("toDate", toDate);

		List<BrokenPeriodEntity> lstBrokenPeriodEntity = query.getResultList();

		return lstBrokenPeriodEntity.size() == 0 ? "0" : "1";
	}

	@Override
	public List saveBrokenPeriodDAArrayPay(BrokenPeriodEntity[] lArrMstBrokenPeriodPay,
			List<BrokenPeriodAllowDeducEntity> lListRltBrokenPeriodAllow,
			List<BrokenPeriodAllowDeducEntity> lListRltBrokenPeriodDeduc) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = null;
		Integer brokenperiodid = null;
//			org.hibernate.Transaction tx = currentSession.getTransaction();

//			entityManager.getTransaction().begin();

		for (Integer lInt = 0; lInt < lArrMstBrokenPeriodPay.length; lInt++) {
//				lObjBrokenPeriodDAO.create(lArrMstBrokenPeriodPay[lInt]);
//				currentSession.persist(lArrMstBrokenPeriodPay[lInt]);
			saveId = (Serializable) currentSession.save(lArrMstBrokenPeriodPay[lInt]);
//				brokenperiodid=lArrMstBrokenPeriodPay[lInt].getBrokenPeriodId();
//				currentSession.flush();
		}
//			tx.commit();

		for (Integer lInt = 0; lInt < lListRltBrokenPeriodAllow.size(); lInt++) {
//				lObjBrokenPeriodDAO.create(lListRltBrokenPeriodAllow.get(lInt));
//				lListRltBrokenPeriodAllow.get(lInt).setBrokenPeriodId(brokenperiodid);
			currentSession.save(lListRltBrokenPeriodAllow.get(lInt));
//				currentSession.flush();

		}

		for (Integer lInt = 0; lInt < lListRltBrokenPeriodDeduc.size(); lInt++) {
//				lObjBrokenPeriodDAO.create(lListRltBrokenPeriodDeduc.get(lInt));
//				lListRltBrokenPeriodDeduc.get(lInt).setBrokenPeriodId(brokenperiodid);
			currentSession.merge(lListRltBrokenPeriodDeduc.get(lInt));
//				currentSession.flush();

		}
//			tx.commit();
//			currentSession.close();
		List result = new ArrayList();
		result.add(saveId);
		result.add(brokenperiodid);

		// return (Integer) saveId;
		return result;
	}

}
