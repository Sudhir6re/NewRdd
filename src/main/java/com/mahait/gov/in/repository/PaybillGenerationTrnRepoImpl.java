package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.AllowanceDeductionRuleMstEntity;
import com.mahait.gov.in.entity.CentralGovtDAMasterEntity;
import com.mahait.gov.in.entity.DcpsContributionEntity;
import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.MstDcpsContriVoucherDtlEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.PaybillGenerationTrnDetails;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.entity.PaybillStatusEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class PaybillGenerationTrnRepoImpl implements PaybillGenerationTrnRepo {
	// protected final Log logger = LogFactory.getLog(getClass());

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	PaybillGenerationTrnBulkSaveRepository paybillGenerationTrnBulkSaveRepository;

	/*
	 * @Autowired SessionFactory sessionFactory;
	 */

	@Override
	public Long savePaybillHeadMpg(PaybillGenerationTrnEntity objEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(objEntity);
		return (Long) saveId;
	}

	@Override
	public Long saveHrPayPaybill(PaybillGenerationTrnDetails paybillGenerationTrnDetails) {

		// logger.info(" inside the saved saveHrPayPaybill- ");
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(paybillGenerationTrnDetails);
		return (Long) saveId;
	}

	@Override
	public Long getPaybillGenerationTrnId() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT coalesce(max(ch.paybillGenerationTrnId), 0) FROM PaybillGenerationTrnEntity ch";
		Query query = currentSession.createQuery(hql);
		return (Long) query.list().get(0);
	}

	@Override
	public PaybillGenerationTrnEntity findForwardChangeStatementById(Long paybillGenerationTrnId) {
		PaybillGenerationTrnEntity objCadre = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objCadre = currentSession.get(PaybillGenerationTrnEntity.class, paybillGenerationTrnId);
		return objCadre;
	}

	@Override
	public void updateForwardChangeStatementStatus(PaybillGenerationTrnEntity paybillGenerationTrnEntity) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(paybillGenerationTrnEntity);
	}

	@Override
	public PaybillGenerationTrnEntity forwardPayBillToLevel2(Long paybillGenerationTrnId) {
		PaybillGenerationTrnEntity objCadre = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objCadre = currentSession.get(PaybillGenerationTrnEntity.class, paybillGenerationTrnId);
		return objCadre;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int isPaybillExists(BigInteger billGroup, int paybillMonth, int paybillYear) {
		Session currentSession = entityManager.unwrap(Session.class);
		String sql = "select * from paybill_generation_trn_details where paybillMonth ='" + paybillMonth
				+ "' and paybillYear ='" + paybillYear + "'";
		Query query = currentSession.createNativeQuery(sql);
		return (int) query.getMaxResults();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findpaybill(BigInteger billGroup, int paybillMonth, int paybillYear, String ddo) {

		Session currentSession = entityManager.unwrap(Session.class);

		String HQL = " select a.paybill_generation_trn_id,a.scheme_billgroup_id,bb.bill_group_id  from paybill_generation_trn  a inner join mst_dcps_bill_group bb on a.scheme_billgroup_id = bb.bill_group_id   "
				+ " inner join org_ddo_mst dd on dd.ddo_code = a.ddo_code and a.ddo_code='" + ddo
				+ "' inner join rlt_zp_ddo_map ddd on dd.ddo_code = ddd.zp_ddo_code   "
				+ " where a.is_Active not in (14,8) and bb.bill_group_id='" + billGroup + "'";
		System.out.println("Findpaybill Quer +++++" + HQL);

		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	// @SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getChangeStatementReport(String paybillGenerationTrnId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "  select\r\n" + "        temp.sevaarth_id,\r\n" + "        temp.employee_full_name_en,\r\n"
				+ "        temp.designation_name,\r\n" + "        sum(temp.cur_year) as cur_year,\r\n"
				+ "        sum(temp.cur_month) as cur_month,\r\n" + "        sum(temp.pre_year) as pre_year,\r\n"
				+ "        sum(temp.pre_month) as pre_month,\r\n"
				+ "        cast(sum(cur_basic_pay)  as bigint)  as cur_basic,\r\n"
				+ "        cast(sum(pre_basic_pay) as bigint) as pre_basic_pay,\r\n"
				+ "		 cast(sum(curr_SPL_PAY) as bigint) as curr_SPL_PAY,\r\n"
				+ "		 cast(sum(pre_SPL_PAY)as bigint) as pre_SPL_PAY,\r\n"
				+ "		  cast(sum(curr_PO) as bigint) as curr_PO,\r\n"
				+ "		  cast(sum(pre_PO) as bigint) as pre_PO,\r\n"
				+ "cast(sum(curr_D_PAY) as bigint) as curr_D_PAY,\r\n"
				+ "cast(sum(pre_D_PAY) as bigint) as pre_D_PAY,	 \r\n" + "cast(sum(curr_DA) as bigint) as curr_DA,\r\n"
				+ "cast(sum(pre_DA) as bigint) as pre_DA,\r\n" + "cast(sum(curr_HRA) as bigint) as curr_HRA,\r\n"
				+ "cast(sum(pre_HRA) as bigint) as pre_HRA,\r\n" + "cast(sum(curr_CLA) as bigint) as curr_CLA,\r\n"
				+ "cast(sum(pre_CLA) as bigint) as pre_CLA,\r\n" + "cast(sum(curr_MA) as bigint) as curr_MA,\r\n"
				+ "cast(sum(pre_MA) as bigint) as pre_MA,\r\n" + "cast(sum(curr_WA) as bigint) as curr_WA,\r\n"
				+ "cast(sum(pre_WA) as bigint) as pre_WA,\r\n"
				+ "cast(sum(curr_TRANS_ALL) as bigint) as curr_TRANS_ALL,\r\n"
				+ "cast(sum(pre_TRANS_ALL) as bigint) as pre_TRANS_ALL,\r\n"
				+ "cast(sum(curr_PAY_RECOVER) as bigint) as curr_PAY_RECOVER,\r\n"
				+ "cast(sum(pre_PAY_RECOVER) as bigint) as pre_PAY_RECOVER,\r\n"
				+ "cast(sum(curr_GROSS_AMT) as bigint) as curr_GROSS_AMT,\r\n"
				+ "cast(sum(pre_GROSS_AMT) as bigint) as pre_GROSS_AMT,\r\n"
				+ "cast(sum(curr_IT) as bigint) as curr_IT,\r\n" + "cast(sum(pre_IT) as bigint) as pre_IT,\r\n"
				+ "cast(sum(curr_HRR) as bigint) as curr_HRR,\r\n" + "cast(sum(pre_HRR) as bigint) as pre_HRR,\r\n"
				+ "cast(sum(curr_PLI) as bigint) as curr_PLI,\r\n" + "cast(sum(pre_PLI) as bigint) as pre_PLI,\r\n"
				+ "cast(sum(curr_PT) as bigint) as curr_PT,\r\n" + "cast(sum(pre_PT) as bigint) as pre_PT,\r\n"
				+ "cast(sum(curr_HBA) as bigint) as curr_HBA,\r\n" + "cast(sum(pre_HBA) as bigint) as pre_HBA,\r\n"
				+ "cast(sum(curr_FAN_ADV) as bigint) as curr_FAN_ADV,\r\n"
				+ "cast(sum(pre_FAN_ADV) as bigint) as pre_FAN_ADV,\r\n"
				+ "cast(sum(curr_JEEP_R) as bigint) as curr_JEEP_R,\r\n"
				+ "cast(sum(pre_JEEP_R) as bigint) as pre_JEEP_R,\r\n"
				+ "cast(sum(curr_GPF_IV) as bigint) as curr_GPF_IV,\r\n"
				+ "cast(sum(pre_GPF_IV) as bigint) as pre_GPF_IV,\r\n"
				+ "cast(sum(curr_TOTAL_DED) as bigint) as curr_TOTAL_DED,\r\n"
				+ "cast(sum(pre_TOTAL_DED) as bigint) as pre_TOTAL_DED,\r\n"
				+ "cast(sum(curr_NET_TOTAL) as bigint) as curr_NET_TOTAL,\r\n"
				+ "cast(sum(pre_NET_TOTAL) as bigint) as pre_NET_TOTAL,\r\n"
				+ "cast(sum(curr_PER_PAY) as bigint) as curr_PER_PAY,\r\n"
				+ "cast(sum(pre_PER_PAY) as bigint) as pre_PER_PAY,\r\n"
				+ "cast(sum(curr_PE) as bigint) as curr_PE,\r\n" + "cast(sum(pre_PE) as bigint) as pre_PE,\r\n"
				+ "cast(sum(curr_OTHER_ALLOW) as bigint) as curr_OTHER_ALLOW,\r\n"
				+ "cast(sum(pre_OTHER_ALLOW) as bigint) as pre_OTHER_ALLOW,\r\n"
				+ "cast(sum(curr_BONUS) as bigint) as curr_BONUS,\r\n"
				+ "cast(sum(pre_BONUS) as bigint) as pre_BONUS,\r\n"
				+ "cast(sum(curr_SURCHARGE) as bigint) as curr_SURCHARGE,\r\n"
				+ "cast(sum(pre_SURCHARGE) as bigint) as pre_SURCHARGE,\r\n"
				+ "cast(sum(curr_RENT_B) as bigint) as curr_RENT_B,\r\n"
				+ "cast(sum(pre_RENT_B) as bigint) as pre_RENT_B,\r\n"
				+ "cast(sum(curr_GPF_ADV) as bigint) as curr_GPF_ADV,\r\n"
				+ "cast(sum(pre_GPF_ADV) as bigint) as pre_GPF_ADV,\r\n"
				+ "cast(sum(curr_MISC) as bigint) as curr_MISC,\r\n" + "cast(sum(pre_MISC) as bigint) as pre_MISC,\r\n"
				+ "cast(sum(curr_TRN_COUNTER) as bigint) as curr_TRN_COUNTER,\r\n"
				+ "cast(sum(pre_TRN_COUNTER) as bigint) as pre_TRN_COUNTER,\r\n"
				+ "cast(sum(curr_DP_GAZZETED) as bigint) as curr_DP_GAZZETED,\r\n"
				+ "cast(sum(pre_DP_GAZZETED) as bigint) as pre_DP_GAZZETED,\r\n"
				+ "cast(sum(curr_GPF_IV_ADV) as bigint) as curr_GPF_IV_ADV,\r\n"
				+ "cast(sum(pre_GPF_IV_ADV) as bigint) as pre_GPF_IV_ADV,\r\n"
				+ "cast(sum(curr_DCPS) as bigint) as curr_DCPS,\r\n" + "cast(sum(pre_DCPS) as bigint) as pre_DCPS,\r\n"
				+ "cast(sum(curr_PSR_NO) as bigint) as curr_PSR_NO,\r\n"
				+ "cast(sum(pre_PSR_NO) as bigint) as pre_PSR_NO,\r\n"
				+ "cast(sum(curr_DA_GPF) as bigint) as curr_DA_GPF,\r\n"
				+ "cast(sum(pre_DA_GPF) as bigint) as pre_DA_GPF,\r\n"
				+ "cast(sum(curr_DA_GPFIV) as bigint) as curr_DA_GPFIV,\r\n"
				+ "cast(sum(pre_DA_GPFIV) as bigint) as pre_DA_GPFIV,\r\n"
				+ "cast(sum(curr_OTHER_TRN_CNTR) as bigint) as curr_OTHER_TRN_CNTR,\r\n"
				+ "cast(sum(pre_OTHER_TRN_CNTR) as bigint) as pre_OTHER_TRN_CNTR,\r\n"
				+ "cast(sum(curr_GPAY) as bigint) as curr_GPAY,\r\n" + "cast(sum(pre_GPAY) as bigint) as pre_GPAY,\r\n"
				+ "cast(sum(curr_TECH_ALLOW) as bigint) as curr_TECH_ALLOW,\r\n"
				+ "cast(sum(pre_TECH_ALLOW) as bigint) as pre_TECH_ALLOW,\r\n"
				+ "cast(sum(curr_HILLY_ALLOWANCE) as bigint) as curr_HILLY_ALLOWANCE,\r\n"
				+ "cast(sum(pre_HILLY_ALLOWANCE) as bigint) as pre_HILLY_ALLOWANCE,\r\n"
				+ "cast(sum(curr_ATS_INCENTIVE_30) as bigint) as curr_ATS_INCENTIVE_30,\r\n"
				+ "cast(sum(pre_ATS_INCENTIVE_30) as bigint) as pre_ATS_INCENTIVE_30,\r\n"
				+ "cast(sum(curr_ATS_INCENTIVE_50) as bigint) as curr_ATS_INCENTIVE_50,\r\n"
				+ "cast(sum(pre_ATS_INCENTIVE_50) as bigint) as pre_ATS_INCENTIVE_50,\r\n"
				+ "cast(sum(curr_PG_ALLOWANCE) as bigint) as curr_PG_ALLOWANCE,\r\n"
				+ "cast(sum(pre_PG_ALLOWANCE) as bigint) as pre_PG_ALLOWANCE,\r\n"
				+ "cast(sum(curr_TAA) as bigint) as curr_TAA,\r\n" + "cast(sum(pre_TAA) as bigint) as pre_TAA,\r\n"
				+ "cast(sum(curr_FORCE_1_100) as bigint) as curr_FORCE_1_100,\r\n"
				+ "cast(sum(pre_FORCE_1_100) as bigint) as pre_FORCE_1_100,\r\n"
				+ "cast(sum(curr_FORCE_1_25) as bigint) as curr_FORCE_1_25,\r\n"
				+ "cast(sum(pre_FORCE_1_25) as bigint) as pre_FORCE_1_25,\r\n"
				+ "cast(sum(curr_ARM_ALLOWANCE) as bigint) as curr_ARM_ALLOWANCE,\r\n"
				+ "cast(sum(pre_ARM_ALLOWANCE) as bigint) as pre_ARM_ALLOWANCE,\r\n"
				+ "cast(sum(curr_ARMOURER) as bigint) as curr_ARMOURER,\r\n"
				+ "cast(sum(pre_ARMOURER) as bigint) as pre_ARMOURER,\r\n"
				+ "cast(sum(curr_BMI) as bigint) as curr_BMI,\r\n" + "cast(sum(pre_BMI) as bigint) as pre_BMI,\r\n"
				+ "cast(sum(curr_CASH_ALLOWANCE) as bigint) as curr_CASH_ALLOWANCE,\r\n"
				+ "cast(sum(pre_CASH_ALLOWANCE) as bigint) as pre_CASH_ALLOWANCE,\r\n"
				+ "cast(sum(curr_CID) as bigint) as curr_CID,\r\n" + "cast(sum(pre_CID) as bigint) as pre_CID,\r\n"
				+ "cast(sum(curr_CONVEYANCE) as bigint) as curr_CONVEYANCE,\r\n"
				+ "cast(sum(pre_CONVEYANCE) as bigint) as pre_CONVEYANCE,\r\n"
				+ "cast(sum(curr_EMERGENCY_ALLOW) as bigint) as curr_EMERGENCY_ALLOW,\r\n"
				+ "cast(sum(pre_EMERGENCY_ALLOW) as bigint) as pre_EMERGENCY_ALLOW,\r\n"
				+ "cast(sum(curr_ESIS) as bigint) as curr_ESIS,\r\n" + "cast(sum(pre_ESIS) as bigint) as pre_ESIS,\r\n"
				+ "cast(sum(curr_ELA) as bigint) as curr_ELA,\r\n" + "cast(sum(pre_ELA) as bigint) as pre_ELA,\r\n"
				+ "cast(sum(curr_FITNESS_ALLOW) as bigint) as curr_FITNESS_ALLOW,\r\n"
				+ "cast(sum(pre_FITNESS_ALLOW) as bigint) as pre_FITNESS_ALLOW,\r\n"
				+ "cast(sum(curr_GALLANTRY_AWARDS) as bigint) as curr_GALLANTRY_AWARDS,\r\n"
				+ "cast(sum(pre_GALLANTRY_AWARDS) as bigint) as pre_GALLANTRY_AWARDS,\r\n"
				+ "cast(sum(curr_KIT_MAINTENANCE) as bigint) as curr_KIT_MAINTENANCE,\r\n"
				+ "cast(sum(pre_KIT_MAINTENANCE) as bigint) as pre_KIT_MAINTENANCE,\r\n"
				+ "cast(sum(curr_LISENCE_FEE) as bigint) as curr_LISENCE_FEE,\r\n"
				+ "cast(sum(pre_LISENCE_FEE) as bigint) as pre_LISENCE_FEE,\r\n"
				+ "cast(sum(curr_MECHANICAL_ALLOW) as bigint) as curr_MECHANICAL_ALLOW,\r\n"
				+ "cast(sum(pre_MECHANICAL_ALLOW) as bigint) as pre_MECHANICAL_ALLOW,\r\n"
				+ "cast(sum(curr_MEDICAL_EDUCATION_ALLOW) as bigint) as curr_MEDICAL_EDUCATION_ALLOW,\r\n"
				+ "cast(sum(pre_MEDICAL_EDUCATION_ALLOW) as bigint) as pre_MEDICAL_EDUCATION_ALLOW,\r\n"
				+ "cast(sum(curr_MESS_ALLOW) as bigint) as curr_MESS_ALLOW,\r\n"
				+ "cast(sum(pre_MESS_ALLOW) as bigint) as pre_MESS_ALLOW,\r\n"
				+ "cast(sum(curr_NAXEL_AREA_ALLOW) as bigint) as curr_NAXEL_AREA_ALLOW,\r\n"
				+ "cast(sum(pre_NAXEL_AREA_ALLOW) as bigint) as pre_NAXEL_AREA_ALLOW,\r\n"
				+ "cast(sum(curr_NON_PRAC_ALLOW) as bigint) as curr_NON_PRAC_ALLOW,\r\n"
				+ "cast(sum(pre_NON_PRAC_ALLOW) as bigint) as pre_NON_PRAC_ALLOW,\r\n"
				+ "cast(sum(curr_SUMPTUARY) as bigint) as curr_SUMPTUARY,\r\n"
				+ "cast(sum(pre_SUMPTUARY) as bigint) as pre_SUMPTUARY,\r\n"
				+ "cast(sum(curr_PROJECT_ALLOW) as bigint) as curr_PROJECT_ALLOW,\r\n"
				+ "cast(sum(pre_PROJECT_ALLOW) as bigint) as pre_PROJECT_ALLOW,\r\n"
				+ "cast(sum(curr_SDA) as bigint) as curr_SDA,\r\n" + "cast(sum(pre_SDA) as bigint) as pre_SDA,\r\n"
				+ "cast(sum(curr_ADD_PAY) as bigint) as curr_ADD_PAY,\r\n"
				+ "cast(sum(pre_ADD_PAY) as bigint) as pre_ADD_PAY,\r\n"
				+ "cast(sum(curr_UNIFORM_ALLOW) as bigint) as curr_UNIFORM_ALLOW,\r\n"
				+ "cast(sum(pre_UNIFORM_ALLOW) as bigint) as pre_UNIFORM_ALLOW,\r\n"
				+ "cast(sum(curr_FAMILY_PALNNING) as bigint) as curr_FAMILY_PALNNING,\r\n"
				+ "cast(sum(pre_FAMILY_PALNNING) as bigint) as pre_FAMILY_PALNNING,\r\n"
				+ "cast(sum(curr_CENTRAL_GIS) as bigint) as curr_CENTRAL_GIS,\r\n"
				+ "cast(sum(pre_CENTRAL_GIS) as bigint) as pre_CENTRAL_GIS,\r\n"
				+ "cast(sum(curr_GIS_IFS) as bigint) as curr_GIS_IFS,\r\n"
				+ "cast(sum(pre_GIS_IFS) as bigint) as pre_GIS_IFS,\r\n"
				+ "cast(sum(curr_GIS_IAS) as bigint) as curr_GIS_IAS,\r\n"
				+ "cast(sum(pre_GIS_IAS) as bigint) as pre_GIS_IAS,\r\n"
				+ "cast(sum(curr_GIS_IPS) as bigint) as curr_GIS_IPS,\r\n"
				+ "cast(sum(pre_GIS_IPS) as bigint) as pre_GIS_IPS,\r\n"
				+ "cast(sum(curr_GPF_IAS_OTHER) as bigint) as curr_GPF_IAS_OTHER,\r\n"
				+ "cast(sum(pre_GPF_IAS_OTHER) as bigint) as pre_GPF_IAS_OTHER,\r\n"
				+ "cast(sum(curr_GPF_IAS) as bigint) as curr_GPF_IAS,\r\n"
				+ "cast(sum(pre_GPF_IAS) as bigint) as pre_GPF_IAS,\r\n"
				+ "cast(sum(curr_GPF_IPS) as bigint) as curr_GPF_IPS,\r\n"
				+ "cast(sum(pre_GPF_IPS) as bigint) as pre_GPF_IPS,\r\n"
				+ "cast(sum(curr_GPF_IFS) as bigint) as curr_GPF_IFS,\r\n"
				+ "cast(sum(pre_GPF_IFS) as bigint) as pre_GPF_IFS,\r\n"
				+ "cast(sum(curr_GPF_GRP_ABC) as bigint) as curr_GPF_GRP_ABC,\r\n"
				+ "cast(sum(pre_GPF_GRP_ABC) as bigint) as pre_GPF_GRP_ABC,\r\n"
				+ "cast(sum(curr_GPF_GRP_D) as bigint) as curr_GPF_GRP_D,\r\n"
				+ "cast(sum(pre_GPF_GRP_D) as bigint) as pre_GPF_GRP_D,\r\n"
				+ "cast(sum(curr_SERVICE_CHARGE) as bigint) as curr_SERVICE_CHARGE,\r\n"
				+ "cast(sum(pre_SERVICE_CHARGE) as bigint) as pre_SERVICE_CHARGE,\r\n"
				+ "cast(sum(curr_OTHER_DEDUCTION) as bigint) as curr_OTHER_DEDUCTION,\r\n"
				+ "cast(sum(pre_OTHER_DEDUCTION) as bigint) as pre_OTHER_DEDUCTION,\r\n"
				+ "cast(sum(curr_MAHA_STATE_LIFE_INSURANCE) as bigint) as curr_MAHA_STATE_LIFE_INSURANCE,\r\n"
				+ "cast(sum(pre_MAHA_STATE_LIFE_INSURANCE) as bigint) as pre_MAHA_STATE_LIFE_INSURANCE,\r\n"
				+ "cast(sum(curr_LTC) as bigint) as curr_LTC,\r\n" + "cast(sum(pre_LTC) as bigint) as pre_LTC,\r\n"
				+ "cast(sum(curr_HBA_CONSTRUCTION) as bigint) as curr_HBA_CONSTRUCTION,\r\n"
				+ "cast(sum(pre_HBA_CONSTRUCTION) as bigint) as pre_HBA_CONSTRUCTION,\r\n"
				+ "cast(sum(curr_HBA_LAND) as bigint) as curr_HBA_LAND,\r\n"
				+ "cast(sum(pre_HBA_LAND) as bigint) as pre_HBA_LAND,\r\n"
				+ "cast(sum(curr_PAY_ADVANCE) as bigint) as curr_PAY_ADVANCE,\r\n"
				+ "cast(sum(pre_PAY_ADVANCE) as bigint) as pre_PAY_ADVANCE,\r\n"
				+ "cast(sum(curr_FESTIVAL_ADVANCE) as bigint) as curr_FESTIVAL_ADVANCE,\r\n"
				+ "cast(sum(pre_FESTIVAL_ADVANCE) as bigint) as pre_FESTIVAL_ADVANCE,\r\n"
				+ "cast(sum(curr_TRAVEL_ADVANCE) as bigint) as curr_TRAVEL_ADVANCE,\r\n"
				+ "cast(sum(pre_TRAVEL_ADVANCE) as bigint) as pre_TRAVEL_ADVANCE,\r\n"
				+ "cast(sum(curr_GPF_ADV_GRP_ABC) as bigint) as curr_GPF_ADV_GRP_ABC,\r\n"
				+ "cast(sum(pre_GPF_ADV_GRP_ABC) as bigint) as pre_GPF_ADV_GRP_ABC,\r\n"
				+ "cast(sum(curr_GPF_ADV_GRP_D) as bigint) as curr_GPF_ADV_GRP_D,\r\n"
				+ "cast(sum(pre_GPF_ADV_GRP_D) as bigint) as pre_GPF_ADV_GRP_D,\r\n"
				+ "cast(sum(curr_MOTOR_CYCLE_ADV) as bigint) as curr_MOTOR_CYCLE_ADV,\r\n"
				+ "cast(sum(pre_MOTOR_CYCLE_ADV) as bigint) as pre_MOTOR_CYCLE_ADV,\r\n"
				+ "cast(sum(curr_OTHER_VEH_ADV) as bigint) as curr_OTHER_VEH_ADV,\r\n"
				+ "cast(sum(pre_OTHER_VEH_ADV) as bigint) as pre_OTHER_VEH_ADV,\r\n"
				+ "cast(sum(curr_COMPUTER_ADV) as bigint) as curr_COMPUTER_ADV,\r\n"
				+ "cast(sum(pre_COMPUTER_ADV) as bigint) as pre_COMPUTER_ADV,\r\n"
				+ "cast(sum(curr_GIS_ZP) as bigint) as curr_GIS_ZP,\r\n"
				+ "cast(sum(pre_GIS_ZP) as bigint) as pre_GIS_ZP,\r\n"
				+ "cast(sum(curr_GPF_ABC_ARR_MR) as bigint) as curr_GPF_ABC_ARR_MR,\r\n"
				+ "cast(sum(pre_GPF_ABC_ARR_MR) as bigint) as pre_GPF_ABC_ARR_MR,\r\n"
				+ "cast(sum(curr_GPF_D_ARR_MR) as bigint) as curr_GPF_D_ARR_MR,\r\n"
				+ "cast(sum(pre_GPF_D_ARR_MR) as bigint) as pre_GPF_D_ARR_MR,\r\n"
				+ "cast(sum(curr_GPF_IAS_ARR_MR) as bigint) as curr_GPF_IAS_ARR_MR,\r\n"
				+ "cast(sum(pre_GPF_IAS_ARR_MR) as bigint) as pre_GPF_IAS_ARR_MR,\r\n"
				+ "cast(sum(curr_GPF_IFS_ARR_MR) as bigint) as curr_GPF_IFS_ARR_MR,\r\n"
				+ "cast(sum(pre_GPF_IFS_ARR_MR) as bigint) as pre_GPF_IFS_ARR_MR,\r\n"
				+ "cast(sum(curr_GPF_IPS_ARR_MR) as bigint) as curr_GPF_IPS_ARR_MR,\r\n"
				+ "cast(sum(pre_GPF_IPS_ARR_MR) as bigint) as pre_GPF_IPS_ARR_MR,\r\n"
				+ "cast(sum(curr_HRR_ARR) as bigint) as curr_HRR_ARR,\r\n"
				+ "cast(sum(pre_HRR_ARR) as bigint) as pre_HRR_ARR,\r\n"
				+ "cast(sum(curr_JANJULGISARR) as bigint) as curr_JANJULGISARR,\r\n"
				+ "cast(sum(pre_JANJULGISARR) as bigint) as pre_JANJULGISARR,\r\n"
				+ "cast(sum(curr_OTHER_REC) as bigint) as curr_OTHER_REC,\r\n"
				+ "cast(sum(pre_OTHER_REC) as bigint) as pre_OTHER_REC,\r\n"
				+ "cast(sum(curr_PT_ARR) as bigint) as curr_PT_ARR,\r\n"
				+ "cast(sum(pre_PT_ARR) as bigint) as pre_PT_ARR,\r\n"
				+ "cast(sum(curr_MCA_LAND) as bigint) as curr_MCA_LAND,\r\n"
				+ "cast(sum(pre_MCA_LAND) as bigint) as pre_MCA_LAND,\r\n"
				+ "cast(sum(curr_ADD_DA) as bigint) as curr_ADD_DA,\r\n"
				+ "cast(sum(pre_ADD_DA) as bigint) as pre_ADD_DA,\r\n"
				+ "cast(sum(curr_ADD_HRA) as bigint) as curr_ADD_HRA,\r\n"
				+ "cast(sum(pre_ADD_HRA) as bigint) as pre_ADD_HRA,\r\n"
				+ "cast(sum(curr_DA_ARR) as bigint) as curr_DA_ARR,\r\n"
				+ "cast(sum(pre_DA_ARR) as bigint) as pre_DA_ARR,\r\n" + "\r\n"
				+ "cast(sum(curr_TEMP_CLA_5THPAY) as bigint) as curr_TEMP_CLA_5THPAY,\r\n"
				+ "cast(sum(pre_TEMP_CLA_5THPAY) as bigint) as pre_TEMP_CLA_5THPAY,\r\n"
				+ "cast(sum(curr_FRANKING_ALLOW) as bigint) as curr_FRANKING_ALLOW,\r\n"
				+ "cast(sum(pre_FRANKING_ALLOW) as bigint) as pre_FRANKING_ALLOW,\r\n"
				+ "cast(sum(curr_TEMP_HRA_5THPAY) as bigint) as curr_TEMP_HRA_5THPAY,\r\n"
				+ "cast(sum(pre_TEMP_HRA_5THPAY) as bigint) as pre_TEMP_HRA_5THPAY,\r\n"
				+ "cast(sum(curr_LEAVE_TRAVEL_ASSISTANCE) as bigint) as curr_LEAVE_TRAVEL_ASSISTANCE,\r\n"
				+ "cast(sum(pre_LEAVE_TRAVEL_ASSISTANCE) as bigint) as pre_LEAVE_TRAVEL_ASSISTANCE,\r\n"
				+ "cast(sum(curr_MEDICAL_STUDY_ALLOW) as bigint) as curr_MEDICAL_STUDY_ALLOW,\r\n"
				+ "cast(sum(pre_MEDICAL_STUDY_ALLOW) as bigint) as pre_MEDICAL_STUDY_ALLOW,\r\n"
				+ "cast(sum(curr_OTHER_ALLOWANCES) as bigint) as curr_OTHER_ALLOWANCES,\r\n"
				+ "cast(sum(pre_OTHER_ALLOWANCES) as bigint) as pre_OTHER_ALLOWANCES,\r\n"
				+ "cast(sum(curr_PERMANENT_TRAVELLING) as bigint) as curr_PERMANENT_TRAVELLING,\r\n"
				+ "cast(sum(pre_PERMANENT_TRAVELLING) as bigint) as pre_PERMANENT_TRAVELLING,\r\n"
				+ "cast(sum(curr_TEMP_TA_5THPAY) as bigint) as curr_TEMP_TA_5THPAY,\r\n"
				+ "cast(sum(pre_TEMP_TA_5THPAY) as bigint) as pre_TEMP_TA_5THPAY,\r\n"
				+ "cast(sum(curr_WASH_ALLOW) as bigint) as curr_WASH_ALLOW,\r\n"
				+ "cast(sum(pre_WASH_ALLOW) as bigint) as pre_WASH_ALLOW,\r\n"
				+ "cast(sum(curr_WRITER_PAY_ALLOW) as bigint) as curr_WRITER_PAY_ALLOW,\r\n"
				+ "cast(sum(pre_WRITER_PAY_ALLOW) as bigint) as pre_WRITER_PAY_ALLOW,\r\n"
				+ "cast(sum(curr_TRIBAL_ALLOW) as bigint) as curr_TRIBAL_ALLOW,\r\n"
				+ "cast(sum(pre_TRIBAL_ALLOW) as bigint) as pre_TRIBAL_ALLOW,\r\n"
				+ "cast(sum(curr_CO_HSG_SOC) as bigint) as curr_CO_HSG_SOC,\r\n"
				+ "cast(sum(pre_CO_HSG_SOC) as bigint) as pre_CO_HSG_SOC,\r\n"
				+ "cast(sum(curr_COMP_AIS) as bigint) as curr_COMP_AIS,\r\n"
				+ "cast(sum(pre_COMP_AIS) as bigint) as pre_COMP_AIS,\r\n"
				+ "cast(sum(curr_EXC_PAYRC) as bigint) as curr_EXC_PAYRC,\r\n"
				+ "cast(sum(pre_EXC_PAYRC) as bigint) as pre_EXC_PAYRC,\r\n"
				+ "cast(sum(curr_GPF_OTHER_STATE) as bigint) as curr_GPF_OTHER_STATE,\r\n"
				+ "cast(sum(pre_GPF_OTHER_STATE) as bigint) as pre_GPF_OTHER_STATE,\r\n"
				+ "cast(sum(curr_HBA_AIS) as bigint) as curr_HBA_AIS,\r\n"
				+ "cast(sum(pre_HBA_AIS) as bigint) as pre_HBA_AIS,\r\n"
				+ "cast(sum(curr_HBA_HOUSE) as bigint) as curr_HBA_HOUSE,\r\n"
				+ "cast(sum(pre_HBA_HOUSE) as bigint) as pre_HBA_HOUSE,\r\n"
				+ "cast(sum(curr_MCA_AIS) as bigint) as curr_MCA_AIS,\r\n"
				+ "cast(sum(pre_MCA_AIS) as bigint) as pre_MCA_AIS,\r\n"
				+ "cast(sum(curr_OTHER_ADV) as bigint) as curr_OTHER_ADV,\r\n"
				+ "cast(sum(pre_OTHER_ADV) as bigint) as pre_OTHER_ADV,\r\n"
				+ "cast(sum(curr_GPF_IAS_LOAN) as bigint) as curr_GPF_IAS_LOAN,\r\n"
				+ "cast(sum(pre_GPF_IAS_LOAN) as bigint) as pre_GPF_IAS_LOAN,\r\n"
				+ "cast(sum(curr_DCPS_DELAY) as bigint) as curr_DCPS_DELAY,\r\n"
				+ "cast(sum(pre_DCPS_DELAY) as bigint) as pre_DCPS_DELAY,\r\n"
				+ "cast(sum(curr_DCPS_PAY) as bigint) as curr_DCPS_PAY,\r\n"
				+ "cast(sum(pre_DCPS_PAY) as bigint) as pre_DCPS_PAY,\r\n"
				+ "cast(sum(curr_REFRESHMENT_ALLOW) as bigint) as curr_REFRESHMENT_ALLOW,\r\n"
				+ "cast(sum(pre_REFRESHMENT_ALLOW) as bigint) as pre_REFRESHMENT_ALLOW,\r\n"
				+ "cast(sum(curr_JANJULGIS) as bigint) as curr_JANJULGIS,\r\n"
				+ "cast(sum(pre_JANJULGIS) as bigint) as pre_JANJULGIS,\r\n"
				+ "cast(sum(curr_CDA) as bigint) as curr_CDA,\r\n" + "cast(sum(pre_CDA) as bigint) as pre_CDA,\r\n"
				+ "cast(sum(curr_CTA) as bigint) as curr_CTA,\r\n" + "cast(sum(pre_CTA) as bigint) as pre_CTA,\r\n"
				+ "cast(sum(curr_PEON_ALLOWANCE) as bigint) as curr_PEON_ALLOWANCE,\r\n"
				+ "cast(sum(pre_PEON_ALLOWANCE) as bigint) as pre_PEON_ALLOWANCE,\r\n"
				+ "cast(sum(curr_INCENTIVE_BDDS) as bigint) as curr_INCENTIVE_BDDS,\r\n"
				+ "cast(sum(pre_INCENTIVE_BDDS) as bigint) as pre_INCENTIVE_BDDS,\r\n"
				+ "cast(sum(curr_RT_PILOT) as bigint) as curr_RT_PILOT,\r\n"
				+ "cast(sum(pre_RT_PILOT) as bigint) as pre_RT_PILOT,\r\n"
				+ "cast(sum(curr_CHPL_PILOT) as bigint) as curr_CHPL_PILOT,\r\n"
				+ "cast(sum(pre_CHPL_PILOT) as bigint) as pre_CHPL_PILOT,\r\n"
				+ "cast(sum(curr_KIT_PILOT) as bigint) as curr_KIT_PILOT,\r\n"
				+ "cast(sum(pre_KIT_PILOT) as bigint) as pre_KIT_PILOT,\r\n"
				+ "cast(sum(curr_FLYING_PAY_PILOT) as bigint) as curr_FLYING_PAY_PILOT,\r\n"
				+ "cast(sum(pre_FLYING_PAY_PILOT) as bigint) as pre_FLYING_PAY_PILOT,\r\n"
				+ "cast(sum(curr_INSTRUCTIONAL_PILOT) as bigint) as curr_INSTRUCTIONAL_PILOT,\r\n"
				+ "cast(sum(pre_INSTRUCTIONAL_PILOT) as bigint) as pre_INSTRUCTIONAL_PILOT,\r\n"
				+ "cast(sum(curr_QUALIFICATION_PILOT) as bigint) as curr_QUALIFICATION_PILOT,\r\n"
				+ "cast(sum(pre_QUALIFICATION_PILOT) as bigint) as pre_QUALIFICATION_PILOT,\r\n"
				+ "cast(sum(curr_INSPECTION_PILOT) as bigint) as curr_INSPECTION_PILOT,\r\n"
				+ "cast(sum(pre_INSPECTION_PILOT) as bigint) as pre_INSPECTION_PILOT,\r\n"
				+ "cast(sum(curr_FLYING_ALLOW_PILOT) as bigint) as curr_FLYING_ALLOW_PILOT,\r\n"
				+ "cast(sum(pre_FLYING_ALLOW_PILOT) as bigint) as pre_FLYING_ALLOW_PILOT,\r\n"
				+ "cast(sum(curr_OUTFIT_PILOT) as bigint) as curr_OUTFIT_PILOT,\r\n"
				+ "cast(sum(pre_OUTFIT_PILOT) as bigint) as pre_OUTFIT_PILOT,\r\n"
				+ "cast(sum(curr_MILITERY_PILOT) as bigint) as curr_MILITERY_PILOT,\r\n"
				+ "cast(sum(pre_MILITERY_PILOT) as bigint) as pre_MILITERY_PILOT,\r\n"
				+ "cast(sum(curr_SPECIAL_PAY_PILOT) as bigint) as curr_SPECIAL_PAY_PILOT,\r\n"
				+ "cast(sum(pre_SPECIAL_PAY_PILOT) as bigint) as pre_SPECIAL_PAY_PILOT,\r\n"
				+ "cast(sum(curr_CPF) as bigint) as curr_CPF,\r\n" + "cast(sum(pre_CPF) as bigint) as pre_CPF,\r\n"
				+ "cast(sum(curr_BASIC_ARR) as bigint) as curr_BASIC_ARR,\r\n"
				+ "cast(sum(pre_BASIC_ARR) as bigint) as pre_BASIC_ARR,\r\n"
				+ "cast(sum(curr_DA_ON_TA) as bigint) as curr_DA_ON_TA,\r\n"
				+ "cast(sum(pre_DA_ON_TA) as bigint) as pre_DA_ON_TA,\r\n"
				+ "cast(sum(curr_TRANS_ARREAR) as bigint) as curr_TRANS_ARREAR,\r\n"
				+ "cast(sum(pre_TRANS_ARREAR) as bigint) as pre_TRANS_ARREAR,\r\n"
				+ "cast(sum(curr_OVERTIME_ALLOW) as bigint) as curr_OVERTIME_ALLOW,\r\n"
				+ "cast(sum(pre_OVERTIME_ALLOW) as bigint) as pre_OVERTIME_ALLOW,\r\n"
				+ "cast(sum(curr_CPF_CONTRIBUTION) as bigint) as curr_CPF_CONTRIBUTION,\r\n"
				+ "cast(sum(pre_CPF_CONTRIBUTION) as bigint) as pre_CPF_CONTRIBUTION,\r\n"
				+ "cast(sum(curr_CPF_EMPLOYEE) as bigint) as curr_CPF_EMPLOYEE,\r\n"
				+ "cast(sum(pre_CPF_EMPLOYEE) as bigint) as pre_CPF_EMPLOYEE,\r\n"
				+ "cast(sum(curr_CPF_EMPLOYER) as bigint) as curr_CPF_EMPLOYER,\r\n"
				+ "cast(sum(pre_CPF_EMPLOYER) as bigint) as pre_CPF_EMPLOYER,\r\n"
				+ "cast(sum(curr_ACC_POLICY) as bigint) as curr_ACC_POLICY,\r\n"
				+ "cast(sum(pre_ACC_POLICY) as bigint) as pre_ACC_POLICY,\r\n"
				+ "cast(sum(curr_SVNPC_DA) as bigint) as curr_SVNPC_DA,\r\n"
				+ "cast(sum(pre_SVNPC_TA) as bigint) as pre_SVNPC_TA,\r\n"
				+ "cast(sum(curr_SVNPC_GPF_ARR) as bigint) as curr_SVNPC_GPF_ARR,\r\n"
				+ "cast(sum(pre_SVNPC_GPF_ARR) as bigint) as pre_SVNPC_GPF_ARR,\r\n"
				+ "cast(sum(curr_SVNPC_DCPS_ARR) as bigint) as curr_SVNPC_DCPS_ARR,\r\n"
				+ "cast(sum(pre_SVNPC_DCPS_ARR) as bigint) as pre_SVNPC_DCPS_ARR,\r\n"
				+ "cast(sum(curr_SVNPC_TA_ARR) as bigint) as curr_SVNPC_TA_ARR,\r\n"
				+ "cast(sum(pre_SVNPC_TA_ARR) as bigint) as pre_SVNPC_TA_ARR,\r\n"
				+ "cast(sum(curr_SVNPC_GPF_ARR_DEDU) as bigint) as curr_SVNPC_GPF_ARR_DEDU,\r\n"
				+ "cast(sum(pre_SVNPC_GPF_ARR_DEDU) as bigint) as pre_SVNPC_GPF_ARR_DEDU,\r\n"
				+ "cast(sum(curr_SVNPC_GPF_RECO) as bigint) as curr_SVNPC_GPF_RECO,\r\n"
				+ "cast(sum(pre_SVNPC_GPF_RECO) as bigint) as pre_SVNPC_GPF_RECO,\r\n"
				+ "cast(sum(curr_SVNPC_DCPS_RECO) as bigint) as curr_SVNPC_DCPS_RECO,\r\n"
				+ "cast(sum(pre_SVNPC_DCPS_RECO) as bigint) as pre_SVNPC_DCPS_RECO,\r\n"
				+ "cast(sum(curr_NPS_EMPLR) as bigint) as curr_NPS_EMPLR,\r\n"
				+ "cast(sum(pre_NPS_EMPLR) as bigint) as pre_NPS_EMPLR,\r\n"
				+ "cast(sum(curr_NPS_EMPLR_CONTRI_DED) as bigint) as curr_NPS_EMPLR_CONTRI_DED,\r\n"
				+ "cast(sum(pre_NPS_EMPLR_CONTRI_DED) as bigint) as pre_NPS_EMPLR_CONTRI_DED,\r\n"
				+ "cast(sum(curr_REVENUE_STAMP) as bigint) as curr_REVENUE_STAMP,\r\n"
				+ "cast(sum(pre_REVENUE_STAMP) as bigint) as pre_REVENUE_STAMP,\r\n"
				+ "cast(sum(curr_lic) as bigint) as curr_lic,\r\n" + "cast(sum(pre_lic) as bigint) as pre_lic,\r\n"
				+ "cast(sum(curr_COP_Bank) as bigint) as curr_COP_Bank,\r\n"
				+ "cast(sum(pre_COP_Bank) as bigint) as pre_COP_Bank,\r\n"
				+ "cast(sum(curr_Recurring_deposite) as bigint) as curr_Recurring_deposite,\r\n"
				+ "cast(sum(pre_Recurring_deposite) as bigint) as pre_Recurring_deposite,\r\n"
				+ "cast(sum(curr_credit_soc) as bigint) as curr_credit_soc,\r\n"
				+ "cast(sum(pre_credit_soc) as bigint) as pre_credit_soc,\r\n"
				+ "cast(sum(curr_con_store) as bigint) as curr_con_store,\r\n"
				+ "cast(sum(pre_con_store) as bigint) as pre_con_store,\r\n"
				+ "cast(sum(curr_mantralaya_bank) as bigint) as curr_mantralaya_bank,\r\n"
				+ "cast(sum(pre_mantralaya_bank) as bigint) as pre_mantralaya_bank,\r\n"
				+ "cast(sum(curr_mis) as bigint) as curr_mis,\r\n" + "cast(sum(pre_mis) as bigint) as pre_mis,\r\n"
				+ "cast(sum(curr_mrt_cop_soc) as bigint) as curr_mrt_cop_soc,\r\n"
				+ "cast(sum(pre_mrt_cop_soc) as bigint) as pre_mrt_cop_soc,\r\n"
				+ "cast(sum(curr_other_Deduc) as bigint) as curr_other_Deduc,\r\n"
				+ "cast(sum(pre_other_Deduc) as bigint) as pre_other_Deduc,\r\n"
				+ "cast(sum(curr_other_Recovery) as bigint) as curr_other_Recovery,\r\n"
				+ "cast(sum(pre_other_Recovery) as bigint) as pre_other_Recovery\r\n" + "\r\n" + "    from\r\n"
				+ "        (select\r\n" + "            c.sevaarth_id,\r\n" + "            c.employee_full_name_en,\r\n"
				+ "            deg.designation_name,\r\n" + "            d.year_english as cur_year,\r\n"
				+ "            e.month_id as cur_month ,\r\n" + "            0 as pre_year,\r\n"
				+ "            0 as pre_month ,\r\n" + "            b.basic_pay  as cur_basic_pay,\r\n"
				+ "            0  as pre_basic_pay,\r\n" + "          b.SPL_PAY as curr_SPL_PAY,\r\n"
				+ "		  0 as pre_SPL_PAY,\r\n" + "		  b.PO as curr_PO,\r\n" + "		  0 as pre_PO,\r\n"
				+ "		  b.D_PAY as curr_D_PAY,\r\n" + "		  0 as pre_D_PAY,\r\n" + "		  b.DA as curr_DA,\r\n"
				+ "		  0 as pre_DA,\r\n" + "		  b.HRA as curr_HRA,\r\n" + "		  0 as pre_HRA,\r\n"
				+ "		  b.CLA as curr_CLA,\r\n" + "		  0 as pre_CLA,\r\n" + "		  b.MA as curr_MA,\r\n"
				+ "		  0 as pre_MA,\r\n" + "		  b.WA as curr_WA,\r\n" + "		  0 as pre_WA,\r\n"
				+ "		  b.TRANS_ALL as curr_TRANS_ALL,\r\n" + "		  0 as pre_TRANS_ALL,\r\n"
				+ "		  b.PAY_RECOVER as curr_PAY_RECOVER,\r\n" + "		  0 as pre_PAY_RECOVER,\r\n"
				+ "		  b.GROSS_AMT as curr_GROSS_AMT,\r\n" + "		  0 as pre_GROSS_AMT,\r\n"
				+ "		  b.IT as curr_IT,\r\n" + "		  0 as pre_IT,\r\n" + "		  b.HRR as curr_HRR,\r\n"
				+ "		  0 as pre_HRR,\r\n" + "		  b.PLI as curr_PLI,\r\n" + "		  0 as pre_PLI,\r\n"
				+ "		  b.PT as curr_PT,\r\n" + "		  0 as pre_PT,\r\n" + "		  b.HBA as curr_HBA,\r\n"
				+ "		  0 as pre_HBA,\r\n" + "		  b.FAN_ADV as curr_FAN_ADV,\r\n"
				+ "		  0 as pre_FAN_ADV,\r\n" + "		  b.JEEP_R as curr_JEEP_R,\r\n"
				+ "		  0 as pre_JEEP_R,\r\n" + "		  b.GPF_IV as curr_GPF_IV,\r\n" + "		  0 as pre_GPF_IV,\r\n"
				+ "		  b.TOTAL_DED as curr_TOTAL_DED,\r\n" + "		  0 as pre_TOTAL_DED,\r\n"
				+ "		  b.NET_TOTAL as curr_NET_TOTAL,\r\n" + "		  0 as pre_NET_TOTAL,\r\n"
				+ "		  b.PER_PAY as curr_PER_PAY,\r\n" + "		  0 as pre_PER_PAY,\r\n"
				+ "		  b.PE as curr_PE,\r\n" + "		  0 as pre_PE,\r\n"
				+ "		  b.OTHER_ALLOW as curr_OTHER_ALLOW,\r\n" + "		  0 as pre_OTHER_ALLOW,\r\n"
				+ "		  b.BONUS as curr_BONUS,\r\n" + "		  0 as pre_BONUS,\r\n"
				+ "		  b.SURCHARGE as curr_SURCHARGE,\r\n" + "		  0 as pre_SURCHARGE,\r\n"
				+ "		  b.RENT_B as curr_RENT_B,\r\n" + "		  0 as pre_RENT_B,\r\n"
				+ "		  b.GPF_ADV as curr_GPF_ADV,\r\n" + "		  0 as pre_GPF_ADV,\r\n"
				+ "		  b.MISC as curr_MISC,\r\n" + "		  0 as pre_MISC,\r\n"
				+ "		  b.TRN_COUNTER as curr_TRN_COUNTER,\r\n" + "		  0 as pre_TRN_COUNTER,\r\n"
				+ "		  b.DP_GAZZETED as curr_DP_GAZZETED,\r\n" + "		  0 as pre_DP_GAZZETED,\r\n"
				+ "		  b.GPF_IV_ADV as curr_GPF_IV_ADV,\r\n" + "		  0 as pre_GPF_IV_ADV,\r\n"
				+ "		  b.DCPS as curr_DCPS,\r\n" + "		  0 as pre_DCPS,\r\n"
				+ "		  b.PSR_NO as curr_PSR_NO,\r\n" + "		  0 as pre_PSR_NO,\r\n"
				+ "		  b.DA_GPF as curr_DA_GPF,\r\n" + "		  0 as pre_DA_GPF,\r\n"
				+ "		  b.DA_GPFIV as curr_DA_GPFIV,\r\n" + "		  0 as pre_DA_GPFIV,\r\n"
				+ "		  b.OTHER_TRN_CNTR as curr_OTHER_TRN_CNTR,\r\n" + "		  0 as pre_OTHER_TRN_CNTR,\r\n"
				+ "		  b.GPAY as curr_GPAY,\r\n" + "		  0 as pre_GPAY,\r\n"
				+ "		  b.TECH_ALLOW as curr_TECH_ALLOW,\r\n" + "		  0 as pre_TECH_ALLOW,\r\n"
				+ "		  b.HILLY_ALLOWANCE as curr_HILLY_ALLOWANCE,\r\n" + "		  0 as pre_HILLY_ALLOWANCE,\r\n"
				+ "		  b.ATS_INCENTIVE_30 as curr_ATS_INCENTIVE_30,\r\n" + "		  0 as pre_ATS_INCENTIVE_30,\r\n"
				+ "		  b.ATS_INCENTIVE_50 as curr_ATS_INCENTIVE_50,\r\n" + "		  0 as pre_ATS_INCENTIVE_50,\r\n"
				+ "		  b.PG_ALLOWANCE as curr_PG_ALLOWANCE,\r\n" + "		  0 as pre_PG_ALLOWANCE,\r\n"
				+ "		  b.TAA as curr_TAA,\r\n" + "		  0 as pre_TAA,\r\n"
				+ "		  b.FORCE_1_100 as curr_FORCE_1_100,\r\n" + "		  0 as pre_FORCE_1_100,\r\n"
				+ "		  b.FORCE_1_25 as curr_FORCE_1_25,\r\n" + "		  0 as pre_FORCE_1_25,\r\n"
				+ "		  b.ARM_ALLOWANCE as curr_ARM_ALLOWANCE,\r\n" + "		  0 as pre_ARM_ALLOWANCE,\r\n"
				+ "		  b.ARMOURER as curr_ARMOURER,\r\n" + "		  0 as pre_ARMOURER,\r\n"
				+ "		  b.BMI as curr_BMI,\r\n" + "		  0 as pre_BMI,\r\n"
				+ "		  b.CASH_ALLOWANCE as curr_CASH_ALLOWANCE,\r\n" + "		  0 as pre_CASH_ALLOWANCE,\r\n"
				+ "		  b.CID as curr_CID,\r\n" + "		  0 as pre_CID,\r\n"
				+ "		  b.CONVEYANCE as curr_CONVEYANCE,\r\n" + "		  0 as pre_CONVEYANCE,\r\n"
				+ "		  b.EMERGENCY_ALLOW as curr_EMERGENCY_ALLOW,\r\n" + "		  0 as pre_EMERGENCY_ALLOW,\r\n"
				+ "		  b.ESIS as curr_ESIS,\r\n" + "		  0 as pre_ESIS,\r\n" + "		  b.ELA as curr_ELA,\r\n"
				+ "		  0 as pre_ELA,\r\n" + "		  b.FITNESS_ALLOW as curr_FITNESS_ALLOW,\r\n"
				+ "		  0 as pre_FITNESS_ALLOW,\r\n" + "		  b.GALLANTRY_AWARDS as curr_GALLANTRY_AWARDS,\r\n"
				+ "		  0 as pre_GALLANTRY_AWARDS,\r\n" + "		  b.KIT_MAINTENANCE as curr_KIT_MAINTENANCE,\r\n"
				+ "		  0 as pre_KIT_MAINTENANCE,\r\n" + "		  b.LISENCE_FEE as curr_LISENCE_FEE,\r\n"
				+ "		  0 as pre_LISENCE_FEE,\r\n" + "		  b.MECHANICAL_ALLOW as curr_MECHANICAL_ALLOW,\r\n"
				+ "		  0 as pre_MECHANICAL_ALLOW,\r\n"
				+ "		  b.MEDICAL_EDUCATION_ALLOW as curr_MEDICAL_EDUCATION_ALLOW,\r\n"
				+ "		  0 as pre_MEDICAL_EDUCATION_ALLOW,\r\n" + "		  b.MESS_ALLOW as curr_MESS_ALLOW,\r\n"
				+ "		  0 as pre_MESS_ALLOW,\r\n" + "		  b.NAXEL_AREA_ALLOW as curr_NAXEL_AREA_ALLOW,\r\n"
				+ "		  0 as pre_NAXEL_AREA_ALLOW,\r\n" + "		  b.NON_PRAC_ALLOW as curr_NON_PRAC_ALLOW,\r\n"
				+ "		  0 as pre_NON_PRAC_ALLOW,\r\n" + "		  b.SUMPTUARY as curr_SUMPTUARY,\r\n"
				+ "		  0 as pre_SUMPTUARY,\r\n" + "		  b.PROJECT_ALLOW as curr_PROJECT_ALLOW,\r\n"
				+ "		  0 as pre_PROJECT_ALLOW,\r\n" + "		  b.SDA as curr_SDA,\r\n" + "		  0 as pre_SDA,\r\n"
				+ "		  b.ADD_PAY as curr_ADD_PAY,\r\n" + "		  0 as pre_ADD_PAY,\r\n"
				+ "		  b.UNIFORM_ALLOW as curr_UNIFORM_ALLOW,\r\n" + "		  0 as pre_UNIFORM_ALLOW,\r\n"
				+ "		  b.FAMILY_PALNNING as curr_FAMILY_PALNNING,\r\n" + "		  0 as pre_FAMILY_PALNNING,\r\n"
				+ "		  b.GIS as curr_GIS,\r\n" + "		  0 as pre_GIS,\r\n"
				+ "		  b.CENTRAL_GIS as curr_CENTRAL_GIS,\r\n" + "		  0 as pre_CENTRAL_GIS,\r\n"
				+ "		  b.GIS_IFS as curr_GIS_IFS,\r\n" + "		  0 as pre_GIS_IFS,\r\n"
				+ "		  b.GIS_IAS as curr_GIS_IAS,\r\n" + "		  0 as pre_GIS_IAS,\r\n"
				+ "		  b.GIS_IPS as curr_GIS_IPS,\r\n" + "		  0 as pre_GIS_IPS,\r\n"
				+ "		  b.GPF_IAS_OTHER as curr_GPF_IAS_OTHER,\r\n" + "		  0 as pre_GPF_IAS_OTHER,\r\n"
				+ "		  b.GPF_IAS as curr_GPF_IAS,\r\n" + "		  0 as pre_GPF_IAS,\r\n"
				+ "		  b.GPF_IPS as curr_GPF_IPS,\r\n" + "		  0 as pre_GPF_IPS,\r\n"
				+ "		  b.GPF_IFS as curr_GPF_IFS,\r\n" + "		  0 as pre_GPF_IFS,\r\n"
				+ "		  b.GPF_GRP_ABC as curr_GPF_GRP_ABC,\r\n" + "		  0 as pre_GPF_GRP_ABC,\r\n"
				+ "		  b.GPF_GRP_D as curr_GPF_GRP_D,\r\n" + "		  0 as pre_GPF_GRP_D,\r\n"
				+ "		  b.SERVICE_CHARGE as curr_SERVICE_CHARGE,\r\n" + "		  0 as pre_SERVICE_CHARGE,\r\n"
				+ "		  b.OTHER_DEDUCTION as curr_OTHER_DEDUCTION,\r\n" + "		  0 as pre_OTHER_DEDUCTION,\r\n"
				+ "		  b.MAHA_STATE_LIFE_INSURANCE as curr_MAHA_STATE_LIFE_INSURANCE,\r\n"
				+ "		  0 as pre_MAHA_STATE_LIFE_INSURANCE,\r\n" + "		  b.LTC as curr_LTC,\r\n"
				+ "		  0 as pre_LTC,\r\n" + "		  b.HBA_CONSTRUCTION as curr_HBA_CONSTRUCTION,\r\n"
				+ "		  0 as pre_HBA_CONSTRUCTION,\r\n" + "		  b.HBA_LAND as curr_HBA_LAND,\r\n"
				+ "		  0 as pre_HBA_LAND,\r\n" + "		  b.PAY_ADVANCE as curr_PAY_ADVANCE,\r\n"
				+ "		  0 as pre_PAY_ADVANCE,\r\n" + "		  b.FESTIVAL_ADVANCE as curr_FESTIVAL_ADVANCE,\r\n"
				+ "		  0 as pre_FESTIVAL_ADVANCE,\r\n" + "		  b.TRAVEL_ADVANCE as curr_TRAVEL_ADVANCE,\r\n"
				+ "		  0 as pre_TRAVEL_ADVANCE,\r\n" + "		  b.GPF_ADV_GRP_ABC as curr_GPF_ADV_GRP_ABC,\r\n"
				+ "		  0 as pre_GPF_ADV_GRP_ABC,\r\n" + "		  b.GPF_ADV_GRP_D as curr_GPF_ADV_GRP_D,\r\n"
				+ "		  0 as pre_GPF_ADV_GRP_D,\r\n" + "		  b.MOTOR_CYCLE_ADV as curr_MOTOR_CYCLE_ADV,\r\n"
				+ "		  0 as pre_MOTOR_CYCLE_ADV,\r\n" + "		  b.OTHER_VEH_ADV as curr_OTHER_VEH_ADV,\r\n"
				+ "		  0 as pre_OTHER_VEH_ADV,\r\n" + "		  b.COMPUTER_ADV as curr_COMPUTER_ADV,\r\n"
				+ "		  0 as pre_COMPUTER_ADV,\r\n" + "		  b.GIS_ZP as curr_GIS_ZP,\r\n"
				+ "		  0 as pre_GIS_ZP,\r\n" + "		  b.GPF_ABC_ARR_MR as curr_GPF_ABC_ARR_MR,\r\n"
				+ "		  0 as pre_GPF_ABC_ARR_MR,\r\n" + "		  b.GPF_D_ARR_MR as curr_GPF_D_ARR_MR,\r\n"
				+ "		  0 as pre_GPF_D_ARR_MR,\r\n" + "		  b.GPF_IAS_ARR_MR as curr_GPF_IAS_ARR_MR,\r\n"
				+ "		  0 as pre_GPF_IAS_ARR_MR,\r\n" + "		  b.GPF_IFS_ARR_MR as curr_GPF_IFS_ARR_MR,\r\n"
				+ "		  0 as pre_GPF_IFS_ARR_MR,\r\n" + "		  b.GPF_IPS_ARR_MR as curr_GPF_IPS_ARR_MR,\r\n"
				+ "		  0 as pre_GPF_IPS_ARR_MR,\r\n" + "		  b.HRR_ARR as curr_HRR_ARR,\r\n"
				+ "		  0 as pre_HRR_ARR,\r\n" + "		  b.JANJULGISARR as curr_JANJULGISARR,\r\n"
				+ "		  0 as pre_JANJULGISARR,\r\n" + "		  b.OTHER_REC as curr_OTHER_REC,\r\n"
				+ "		  0 as pre_OTHER_REC,\r\n" + "		  b.PT_ARR as curr_PT_ARR,\r\n"
				+ "		  0 as pre_PT_ARR,\r\n" + "		  b.MCA_LAND as curr_MCA_LAND,\r\n"
				+ "		  0 as pre_MCA_LAND,\r\n" + "		  b.ADD_DA as curr_ADD_DA,\r\n"
				+ "		  0 as pre_ADD_DA,\r\n" + "		  b.ADD_HRA as curr_ADD_HRA,\r\n"
				+ "		  0 as pre_ADD_HRA,\r\n" + "		  b.DA_ARR as curr_DA_ARR,\r\n"
				+ "		  0 as pre_DA_ARR,\r\n" + "		  b.TEMP_CLA_5THPAY as curr_TEMP_CLA_5THPAY,\r\n"
				+ "		  0 as pre_TEMP_CLA_5THPAY,\r\n" + "		  b.FRANKING_ALLOW as curr_FRANKING_ALLOW,\r\n"
				+ "		  0 as pre_FRANKING_ALLOW,\r\n" + "		  b.TEMP_HRA_5THPAY as curr_TEMP_HRA_5THPAY,\r\n"
				+ "		  0 as pre_TEMP_HRA_5THPAY,\r\n"
				+ "		  b.LEAVE_TRAVEL_ASSISTANCE as curr_LEAVE_TRAVEL_ASSISTANCE,\r\n"
				+ "		  0 as pre_LEAVE_TRAVEL_ASSISTANCE,\r\n"
				+ "		  b.MEDICAL_STUDY_ALLOW as curr_MEDICAL_STUDY_ALLOW,\r\n"
				+ "		  0 as pre_MEDICAL_STUDY_ALLOW,\r\n"
				+ "		  b.OTHER_ALLOWANCES as curr_OTHER_ALLOWANCES,\r\n" + "		  0 as pre_OTHER_ALLOWANCES,\r\n"
				+ "		  b.PERMANENT_TRAVELLING as curr_PERMANENT_TRAVELLING,\r\n"
				+ "		  0 as pre_PERMANENT_TRAVELLING,\r\n" + "		  b.TEMP_TA_5THPAY as curr_TEMP_TA_5THPAY,\r\n"
				+ "		  0 as pre_TEMP_TA_5THPAY,\r\n" + "		  b.WASH_ALLOW as curr_WASH_ALLOW,\r\n"
				+ "		  0 as pre_WASH_ALLOW,\r\n" + "		  b.WRITER_PAY_ALLOW as curr_WRITER_PAY_ALLOW,\r\n"
				+ "		  0 as pre_WRITER_PAY_ALLOW,\r\n" + "		  b.TRIBAL_ALLOW as curr_TRIBAL_ALLOW,\r\n"
				+ "		  0 as pre_TRIBAL_ALLOW,\r\n" + "		  b.CO_HSG_SOC as curr_CO_HSG_SOC,\r\n"
				+ "		  0 as pre_CO_HSG_SOC,\r\n" + "		  b.COMP_AIS as curr_COMP_AIS,\r\n"
				+ "		  0 as pre_COMP_AIS,\r\n" + "		  b.EXC_PAYRC as curr_EXC_PAYRC,\r\n"
				+ "		  0 as pre_EXC_PAYRC,\r\n" + "		  b.GPF_OTHER_STATE as curr_GPF_OTHER_STATE,\r\n"
				+ "		  0 as pre_GPF_OTHER_STATE,\r\n" + "		  b.HBA_AIS as curr_HBA_AIS,\r\n"
				+ "		  0 as pre_HBA_AIS,\r\n" + "		  b.HBA_HOUSE as curr_HBA_HOUSE,\r\n"
				+ "		  0 as pre_HBA_HOUSE,\r\n" + "		  b.MCA_AIS as curr_MCA_AIS,\r\n"
				+ "		  0 as pre_MCA_AIS,\r\n" + "		  b.OTHER_ADV as curr_OTHER_ADV,\r\n"
				+ "		  0 as pre_OTHER_ADV,\r\n" + "		  b.GPF_IAS_LOAN as curr_GPF_IAS_LOAN,\r\n"
				+ "		  0 as pre_GPF_IAS_LOAN,\r\n" + "		  b.DCPS_DELAY as curr_DCPS_DELAY,\r\n"
				+ "		  0 as pre_DCPS_DELAY,\r\n" + "		  b.DCPS_PAY as curr_DCPS_PAY,\r\n"
				+ "		  0 as pre_DCPS_PAY,\r\n" + "		  b.REFRESHMENT_ALLOW as curr_REFRESHMENT_ALLOW,\r\n"
				+ "		  0 as pre_REFRESHMENT_ALLOW,\r\n" + "		  b.JANJULGIS as curr_JANJULGIS,\r\n"
				+ "		  0 as pre_JANJULGIS,\r\n" + "		  b.CDA as curr_CDA,\r\n" + "		  0 as pre_CDA,\r\n"
				+ "		  b.CTA as curr_CTA,\r\n" + "		  0 as pre_CTA,\r\n"
				+ "		  b.PEON_ALLOWANCE as curr_PEON_ALLOWANCE,\r\n" + "		  0 as pre_PEON_ALLOWANCE,\r\n"
				+ "		  b.INCENTIVE_BDDS as curr_INCENTIVE_BDDS,\r\n" + "		  0 as pre_INCENTIVE_BDDS,\r\n"
				+ "		  b.RT_PILOT as curr_RT_PILOT,\r\n" + "		  0 as pre_RT_PILOT,\r\n"
				+ "		  b.CHPL_PILOT as curr_CHPL_PILOT,\r\n" + "		  0 as pre_CHPL_PILOT,\r\n"
				+ "		  b.KIT_PILOT as curr_KIT_PILOT,\r\n" + "		  0 as pre_KIT_PILOT,\r\n"
				+ "		  b.FLYING_PAY_PILOT as curr_FLYING_PAY_PILOT,\r\n" + "		  0 as pre_FLYING_PAY_PILOT,\r\n"
				+ "		  b.INSTRUCTIONAL_PILOT as curr_INSTRUCTIONAL_PILOT,\r\n"
				+ "		  0 as pre_INSTRUCTIONAL_PILOT,\r\n"
				+ "		  b.QUALIFICATION_PILOT as curr_QUALIFICATION_PILOT,\r\n"
				+ "		  0 as pre_QUALIFICATION_PILOT,\r\n"
				+ "		  b.INSPECTION_PILOT as curr_INSPECTION_PILOT,\r\n" + "		  0 as pre_INSPECTION_PILOT,\r\n"
				+ "		  b.FLYING_ALLOW_PILOT as curr_FLYING_ALLOW_PILOT,\r\n"
				+ "		  0 as pre_FLYING_ALLOW_PILOT,\r\n" + "		  b.OUTFIT_PILOT as curr_OUTFIT_PILOT,\r\n"
				+ "		  0 as pre_OUTFIT_PILOT,\r\n" + "		  b.MILITERY_PILOT as curr_MILITERY_PILOT,\r\n"
				+ "		  0 as pre_MILITERY_PILOT,\r\n" + "		  b.SPECIAL_PAY_PILOT as curr_SPECIAL_PAY_PILOT,\r\n"
				+ "		  0 as pre_SPECIAL_PAY_PILOT,\r\n" + "		  b.CPF as curr_CPF,\r\n"
				+ "		  0 as pre_CPF,\r\n" + "		  b.BASIC_ARR as curr_BASIC_ARR,\r\n"
				+ "		  0 as pre_BASIC_ARR,\r\n" + "		  b.DA_ON_TA as curr_DA_ON_TA,\r\n"
				+ "		  0 as pre_DA_ON_TA,\r\n" + "		  b.TRANS_ARREAR as curr_TRANS_ARREAR,\r\n"
				+ "		  0 as pre_TRANS_ARREAR,\r\n" + "		  b.OVERTIME_ALLOW as curr_OVERTIME_ALLOW,\r\n"
				+ "		  0 as pre_OVERTIME_ALLOW,\r\n" + "		  b.CPF_CONTRIBUTION as curr_CPF_CONTRIBUTION,\r\n"
				+ "		  0 as pre_CPF_CONTRIBUTION,\r\n" + "		  b.CPF_EMPLOYEE as curr_CPF_EMPLOYEE,\r\n"
				+ "		  0 as pre_CPF_EMPLOYEE,\r\n" + "		  b.CPF_EMPLOYER as curr_CPF_EMPLOYER,\r\n"
				+ "		  0 as pre_CPF_EMPLOYER,\r\n" + "		  b.ACC_POLICY as curr_ACC_POLICY,\r\n"
				+ "		  0 as pre_ACC_POLICY,\r\n" + "		  b.SVNPC_DA as curr_SVNPC_DA,\r\n"
				+ "		  0 as pre_SVNPC_DA,\r\n" + "		  b.SVNPC_TA as curr_SVNPC_TA,\r\n"
				+ "		  0 as pre_SVNPC_TA,\r\n" + "		  b.SVNPC_GPF_ARR as curr_SVNPC_GPF_ARR,\r\n"
				+ "		  0 as pre_SVNPC_GPF_ARR,\r\n" + "		  b.SVNPC_DCPS_ARR as curr_SVNPC_DCPS_ARR,\r\n"
				+ "		  0 as pre_SVNPC_DCPS_ARR,\r\n" + "		  b.SVNPC_TA_ARR as curr_SVNPC_TA_ARR,\r\n"
				+ "		  0 as pre_SVNPC_TA_ARR,\r\n" + "		  b.SVNPC_GPF_ARR_DEDU as curr_SVNPC_GPF_ARR_DEDU,\r\n"
				+ "		  0 as pre_SVNPC_GPF_ARR_DEDU,\r\n" + "		  b.SVNPC_GPF_RECO as curr_SVNPC_GPF_RECO,\r\n"
				+ "		  0 as pre_SVNPC_GPF_RECO,\r\n" + "		  b.SVNPC_DCPS_RECO as curr_SVNPC_DCPS_RECO,\r\n"
				+ "		  0 as pre_SVNPC_DCPS_RECO,\r\n" + "		  b.NPS_EMPLR as curr_NPS_EMPLR,\r\n"
				+ "		  0 as pre_NPS_EMPLR,\r\n" + "		  b.NPS_EMPLR_CONTRI_DED as curr_NPS_EMPLR_CONTRI_DED,\r\n"
				+ "		  0 as pre_NPS_EMPLR_CONTRI_DED,\r\n" + "		  b.REVENUE_STAMP as curr_REVENUE_STAMP,\r\n"
				+ "		  0 as pre_REVENUE_STAMP,\r\n" + "		  b.lic as curr_lic,\r\n" + "		  0 as pre_lic,\r\n"
				+ "		  b.COP_Bank as curr_COP_Bank,\r\n" + "		  0 as pre_COP_Bank,\r\n"
				+ "		  b.Recurring_deposite as curr_Recurring_deposite,\r\n"
				+ "		  0 as pre_Recurring_deposite,\r\n" + "		  b.credit_soc as curr_credit_soc,\r\n"
				+ "		  0 as pre_credit_soc,\r\n" + "		  b.con_store as curr_con_store,\r\n"
				+ "		  0 as pre_con_store,\r\n" + "		  b.mantralaya_bank as curr_mantralaya_bank,\r\n"
				+ "		  0 as pre_mantralaya_bank,\r\n" + "		  b.mis as curr_mis,\r\n"
				+ "		  0 as pre_mis,\r\n" + "		  b.mrt_cop_soc as curr_mrt_cop_soc,\r\n"
				+ "		  0 as pre_mrt_cop_soc,\r\n" + "		  b.other_Deduc as curr_other_Deduc,\r\n"
				+ "		  0 as pre_other_Deduc,\r\n" + "		  b.other_Recovery as curr_other_Recovery,\r\n"
				+ "		  0 as pre_other_Recovery\r\n" + "		          from\r\n"
				+ "            paybill_generation_trn a                                               \r\n"
				+ "        inner join\r\n"
				+ "            paybill_generation_trn_details b                                                                             \r\n"
				+ "                on a.paybill_generation_trn_id = b.paybill_generation_trn_id                                               \r\n"
				+ "        inner join\r\n"
				+ "            employee_mst  c                                                                             \r\n"
				+ "                on b.sevaarth_id = c.sevaarth_id                                               \r\n"
				+ "        inner join\r\n"
				+ "            year_mst d                                                                             \r\n"
				+ "                on b.paybill_year = d.year_id                                               \r\n"
				+ "        inner join\r\n"
				+ "            month_mst e                                                                             \r\n"
				+ "                on b.paybill_month = e.month_id                                               \r\n"
				+ "        inner join\r\n"
				+ "            designation_mst as deg                                                                             \r\n"
				+ "                on deg.designation_code=c.designation_code                                              \r\n"
				+ "        where\r\n" + "            a.is_active<>8                                 \r\n"
				+ "            and               a.paybill_generation_trn_id = '" + paybillGenerationTrnId
				+ "'                                              \r\n" + "        union\r\n"
				+ "        all   select\r\n" + "            c.sevaarth_id,\r\n"
				+ "            c.employee_full_name_en,\r\n" + "            deg.designation_name,\r\n"
				+ "            d.year_english as cur_year,\r\n" + "            e.month_id as cur_month ,\r\n"
				+ "            0  as cur_year,\r\n" + "            0  as cur_month ,\r\n"
				+ "			0  as cur_basic_pay,\r\n" + "            be.basic_pay  as pre_basic_pay,         \r\n"
				+ " 0  as cur_SPL_PAY,         \r\n" + "		 be.SPL_PAY as pre_SPL_PAY,\r\n"
				+ "		  0  as cur_PO,\r\n" + "		  be.PO as pre_PO,\r\n" + "		  0  as cur_D_PAY,\r\n"
				+ "		  be.D_PAY as pre_D_PAY,\r\n" + "		  0  as cur_DA,\r\n" + "		  be.DA as pre_DA,\r\n"
				+ "		  0  as cur_HRA,\r\n" + "		  be.HRA as pre_HRA,\r\n" + "		 0  as cur_CLA,\r\n"
				+ "		  be.CLA as pre_CLA,\r\n" + "		  0  as cur_MA,\r\n" + "		  be.MA as pre_MA,\r\n"
				+ "		  	  0  as cur_WA,\r\n" + "		  be.WA as pre_WA,\r\n" + "	0  as cur_TRANS_ALL,\r\n"
				+ "		  be.TRANS_ALL as pre_TRANS_ALL,\r\n" + "		   0  as cur_PAY_RECOVER,\r\n"
				+ "		  be.PAY_RECOVER as pre_PAY_RECOVER,\r\n" + "		  0  as cur_GROSS_AMT,\r\n"
				+ "		  be.GROSS_AMT as pre_GROSS_AMT,\r\n" + "		  0  as cur_IT,\r\n"
				+ "		  be.IT as pre_IT,\r\n" + "		  0  as cur_HRR,\r\n" + "		  be.HRR as pre_HRR,\r\n"
				+ "		  0  as cur_PLI,\r\n" + "		  be.PLI as pre_PLI,\r\n" + "		  0  as cur_PT,\r\n"
				+ "		  be.PT as pre_PT,\r\n" + "		 0  as cur_HBA,\r\n" + "		  be.HBA as pre_HBA,\r\n"
				+ "		  0  as cur_FAN_ADV,\r\n" + "		  be.FAN_ADV as pre_FAN_ADV,\r\n"
				+ "		   0  as cur_JEEP_R,\r\n" + "		  be.JEEP_R as pre_JEEP_R,\r\n"
				+ "		 0  as cur_GPF_IV,\r\n" + "		  be.GPF_IV as pre_GPF_IV,\r\n"
				+ "		   0  as cur_TOTAL_DED,\r\n" + "		  be.TOTAL_DED as pre_TOTAL_DED,\r\n"
				+ "		 0  as cur_NET_TOTAL,\r\n" + "		  be.NET_TOTAL as pre_NET_TOTAL,\r\n"
				+ "		    0  as cur_PER_PAY,\r\n" + "		  be.PER_PAY as pre_PER_PAY,\r\n"
				+ "		  0  as cur_PE,\r\n" + "		  be.PE as pre_PE,\r\n" + "		  0  as cur_OTHER_ALLOW,\r\n"
				+ "		  be.OTHER_ALLOW as pre_OTHER_ALLOW,\r\n" + "		 0  as cur_BONUS,\r\n"
				+ "		  be.BONUS as pre_BONUS,\r\n" + "		  0  as cur_SURCHARGE,\r\n"
				+ "		  be.SURCHARGE as pre_SURCHARGE,\r\n" + "		  0  as cur_RENT_B,\r\n"
				+ "		  be.RENT_B as pre_RENT_B,\r\n" + "		 0  as cur_GPF_ADV,\r\n"
				+ "		  be.GPF_ADV as pre_GPF_ADV,\r\n" + "		   0  as cur_MISC,\r\n"
				+ "		  be.MISC as pre_MISC,\r\n" + "		  0  as cur_TRN_COUNTER,\r\n"
				+ "		  be.TRN_COUNTER as pre_TRN_COUNTER,\r\n" + "		  0  as cur_DP_GAZZETED,\r\n"
				+ "		  be.DP_GAZZETED as pre_DP_GAZZETED,\r\n" + "		 0  as cur_GPF_IV_ADV,\r\n"
				+ "		  be.GPF_IV_ADV as pre_GPF_IV_ADV,\r\n" + "		  0  as cur_DCPS,\r\n"
				+ "		  be.DCPS as pre_DCPS,\r\n" + "		   0  as cur_PSR_NO,\r\n"
				+ "		  be.PSR_NO as pre_PSR_NO,\r\n" + "		  0  as cur_DA_GPF,\r\n"
				+ "		  be.DA_GPF as pre_DA_GPF,\r\n" + "		 0  as cur_DA_GPFIV,\r\n"
				+ "		  be.DA_GPFIV as pre_DA_GPFIV,\r\n" + "		  0  as cur_OTHER_TRN_CNTR,\r\n"
				+ "		  be.OTHER_TRN_CNTR as pre_OTHER_TRN_CNTR,\r\n" + "		  0  as cur_GPAY,\r\n"
				+ "		  be.GPAY as pre_GPAY,\r\n" + "		  0  as cur_TECH_ALLOW,\r\n"
				+ "		  be.TECH_ALLOW as pre_TECH_ALLOW,\r\n" + "		   0  as cur_HILLY_ALLOWANCE,\r\n"
				+ "		  be.HILLY_ALLOWANCE as pre_HILLY_ALLOWANCE,\r\n" + "		 0  as cur_ATS_INCENTIVE_30,\r\n"
				+ "		  be.ATS_INCENTIVE_30  as cur_ATS_INCENTIVE_30,\r\n"
				+ "		  0  as cur_ATS_INCENTIVE_50,\r\n" + "		  be.ATS_INCENTIVE_50  as cur_ATS_INCENTIVE_50,\r\n"
				+ "		  0  as cur_PG_ALLOWANCE,\r\n" + "		  be.PG_ALLOWANCE as pre_PG_ALLOWANCE,\r\n"
				+ "		   0  as cur_TAA,\r\n" + "		  be.TAA as pre_TAA,\r\n"
				+ "		  0  as cur_FORCE_1_100,\r\n" + "		  be.FORCE_1_100  as cur_FORCE_1_100,\r\n"
				+ "		   0  as cur_FORCE_1_25,\r\n" + "		  be.FORCE_1_25 as pre_FORCE_1_25,\r\n"
				+ "		 0  as cur_ARM_ALLOWANCE,\r\n" + "		  be.ARM_ALLOWANCE as pre_ARM_ALLOWANCE,\r\n"
				+ "		   0  as cur_ARMOURER,\r\n" + "		  be.ARMOURER as pre_ARMOURER,\r\n"
				+ "		 0  as cur_BMI,\r\n" + "		  be.BMI as pre_BMI,\r\n"
				+ "		 0  as cur_CASH_ALLOWANCE,\r\n" + "		  be.CASH_ALLOWANCE as pre_CASH_ALLOWANCE,\r\n"
				+ "		   0  as cur_CID,\r\n" + "		  be.CID as pre_CID,\r\n" + "		  0  as cur_CONVEYANCE,\r\n"
				+ "		  be.CONVEYANCE as pre_CONVEYANCE,\r\n" + "		  0  as cur_EMERGENCY_ALLOW,\r\n"
				+ "		  be.EMERGENCY_ALLOW as pre_EMERGENCY_ALLOW,\r\n" + "		   0  as cur_ESIS,\r\n"
				+ "		  be.ESIS as pre_ESIS,\r\n" + "		 0  as cur_ELA,\r\n" + "		  be.ELA as pre_ELA,\r\n"
				+ "		 0  as cur_FITNESS_ALLOW,\r\n" + "		  be.FITNESS_ALLOW as pre_FITNESS_ALLOW,\r\n"
				+ "		  0  as cur_GALLANTRY_AWARDS,\r\n" + "		  be.GALLANTRY_AWARDS as pre_GALLANTRY_AWARDS,\r\n"
				+ "		  0  as cur_KIT_MAINTENANCE,\r\n" + "		  be.KIT_MAINTENANCE as pre_KIT_MAINTENANCE,\r\n"
				+ "		   0  as cur_LISENCE_FEE,\r\n" + "		  be.LISENCE_FEE as pre_LISENCE_FEE,\r\n"
				+ "		  0  as cur_MECHANICAL_ALLOW,\r\n" + "		  be.MECHANICAL_ALLOW as pre_MECHANICAL_ALLOW,\r\n"
				+ "		  0  as cur_MEDICAL_EDUCATION_ALLOW,\r\n"
				+ "		  be.MEDICAL_EDUCATION_ALLOW as pre_MEDICAL_EDUCATION_ALLOW,\r\n"
				+ "		 0  as cur_MESS_ALLOW,\r\n" + "		  be.MESS_ALLOW as pre_MESS_ALLOW,\r\n"
				+ "		  0  as cur_NAXEL_AREA_ALLOW,\r\n" + "		  be.NAXEL_AREA_ALLOW as pre_NAXEL_AREA_ALLOW,\r\n"
				+ "		    0  as cur_NON_PRAC_ALLOW,\r\n" + "		  be.NON_PRAC_ALLOW as pre_NON_PRAC_ALLOW,\r\n"
				+ "		0  as cur_SUMPTUARY,\r\n" + "		  be.SUMPTUARY as pre_SUMPTUARY,\r\n"
				+ "		   0  as cur_PROJECT_ALLOW,\r\n" + "		  be.PROJECT_ALLOW as pre_PROJECT_ALLOW,\r\n"
				+ "		 0  as cur_SDA,\r\n" + "		  be.SDA as pre_SDA,\r\n" + "		   0  as cur_ADD_PAY,\r\n"
				+ "		  be.ADD_PAY as pre_ADD_PAY,\r\n" + "		   0  as cur_UNIFORM_ALLOW,\r\n"
				+ "		  be.UNIFORM_ALLOW as pre_UNIFORM_ALLOW,\r\n" + "		 0  as cur_FAMILY_PALNNING,\r\n"
				+ "		  be.FAMILY_PALNNING as pre_FAMILY_PALNNING,\r\n" + "		  0  as cur_GIS,\r\n"
				+ "		  be.GIS as pre_GIS,\r\n" + "		  0  as cur_CENTRAL_GIS,\r\n"
				+ "		  be.CENTRAL_GIS as pre_CENTRAL_GIS,\r\n" + "		  0  as cur_GIS_IFS,\r\n"
				+ "		  be.GIS_IFS as pre_GIS_IFS,\r\n" + "		  0  as cur_GIS_IAS,\r\n"
				+ "		  be.GIS_IAS as pre_GIS_IAS,\r\n" + "		  0  as cur_GIS_IPS,\r\n"
				+ "		  be.GIS_IPS as pre_GIS_IPS,\r\n" + "		 0  as cur_GPF_IAS_OTHER,\r\n"
				+ "		  be.GPF_IAS_OTHER as pre_GPF_IAS_OTHER,\r\n" + "		  0  as cur_GPF_IAS,\r\n"
				+ "		  be.GPF_IAS as pre_GPF_IAS,\r\n" + "		   0  as cur_GPF_IPS,\r\n"
				+ "		  be.GPF_IPS as pre_GPF_IPS,\r\n" + "		  0  as cur_GPF_IFS,\r\n"
				+ "		  be.GPF_IFS as pre_GPF_IFS,\r\n" + "		   0  as cur_GPF_GRP_ABC,\r\n"
				+ "		  be.GPF_GRP_ABC as pre_GPF_GRP_ABC,\r\n" + "		0  as cur_GPF_GRP_D,\r\n"
				+ "		  be.GPF_GRP_D as pre_GPF_GRP_D,\r\n" + "		  0  as cur_SERVICE_CHARGE,\r\n"
				+ "		  be.SERVICE_CHARGE as pre_SERVICE_CHARGE,\r\n" + "		   0  as cur_OTHER_DEDUCTION,\r\n"
				+ "		  be.OTHER_DEDUCTION as pre_OTHER_DEDUCTION,\r\n"
				+ "		   0  as cur_MAHA_STATE_LIFE_INSURANCE,\r\n"
				+ "		  be.MAHA_STATE_LIFE_INSURANCE as pre_MAHA_STATE_LIFE_INSURANCE,\r\n"
				+ "		 0  as cur_LTC,\r\n" + "		  be.LTC as pre_LTC,\r\n"
				+ "		 0  as cur_HBA_CONSTRUCTION,\r\n" + "		  be.HBA_CONSTRUCTION as pre_HBA_CONSTRUCTION,\r\n"
				+ "		  0  as cur_HBA_LAND,\r\n" + "		  be.HBA_LAND as pre_HBA_LAND,\r\n"
				+ "		   0  as cur_PAY_ADVANCE,\r\n" + "		  be.PAY_ADVANCE as pre_PAY_ADVANCE,\r\n"
				+ "		  0  as cur_FESTIVAL_ADVANCE,\r\n" + "		  be.FESTIVAL_ADVANCE as pre_FESTIVAL_ADVANCE,\r\n"
				+ "		   0  as cur_TRAVEL_ADVANCE,\r\n" + "		  be.TRAVEL_ADVANCE as pre_TRAVEL_ADVANCE,\r\n"
				+ "		 0  as cur_GPF_ADV_GRP_ABC,\r\n" + "		  be.GPF_ADV_GRP_ABC as pre_GPF_ADV_GRP_ABC,\r\n"
				+ "		  0  as cur_GPF_ADV_GRP_D,\r\n" + "		  be.GPF_ADV_GRP_D as pre_GPF_ADV_GRP_D,\r\n"
				+ "		  0  as cur_MOTOR_CYCLE_ADV,\r\n" + "		  be.MOTOR_CYCLE_ADV as pre_MOTOR_CYCLE_ADV,\r\n"
				+ "		  0  as cur_OTHER_VEH_ADV,\r\n" + "		  be.OTHER_VEH_ADV as pre_OTHER_VEH_ADV,\r\n"
				+ "		 0  as cur_COMPUTER_ADV,\r\n" + "		  be.COMPUTER_ADV as pre_COMPUTER_ADV,\r\n"
				+ "		  0  as cur_GIS_ZP,\r\n" + "		  be.GIS_ZP as pre_GIS_ZP,\r\n"
				+ "		   0  as cur_GPF_ABC_ARR_MR,\r\n" + "		  be.GPF_ABC_ARR_MR as pre_GPF_ABC_ARR_MR,\r\n"
				+ "		 0  as cur_GPF_D_ARR_MR,\r\n" + "		  be.GPF_D_ARR_MR as pre_GPF_D_ARR_MR,\r\n"
				+ "		  0  as cur_GPF_IAS_ARR_MR,\r\n" + "		  be.GPF_IAS_ARR_MR as pre_GPF_IAS_ARR_MR,\r\n"
				+ "		  0  as cur_GPF_IFS_ARR_MR,\r\n" + "		  be.GPF_IFS_ARR_MR as pre_GPF_IFS_ARR_MR,\r\n"
				+ "		  0  as cur_GPF_IPS_ARR_MR,\r\n" + "		  be.GPF_IPS_ARR_MR as pre_GPF_IPS_ARR_MR,\r\n"
				+ "		   0  as cur_HRR_ARR,\r\n" + "		  be.HRR_ARR as pre_HRR_ARR,\r\n"
				+ "		  0  as cur_JANJULGISARR,\r\n" + "		  be.JANJULGISARR as pre_JANJULGISARR,\r\n"
				+ "		 0  as cur_OTHER_REC,\r\n" + "		  be.OTHER_REC as pre_OTHER_REC,\r\n"
				+ "		  0  as cur_PT_ARR,\r\n" + "		  be.PT_ARR as pre_PT_ARR,\r\n"
				+ "		  0  as cur_MCA_LAND,\r\n" + "		  be.MCA_LAND as pre_MCA_LAND,\r\n"
				+ "		   0  as cur_ADD_DA,\r\n" + "		  be.ADD_DA as pre_ADD_DA,\r\n"
				+ "		  0  as cur_ADD_HRA,\r\n" + "		  be.ADD_HRA as pre_ADD_HRA,\r\n"
				+ "		  0  as cur_DA_ARR,\r\n" + "		  be.DA_ARR as pre_DA_ARR,\r\n"
				+ "		 0  as cur_TEMP_CLA_5THPAY,\r\n" + "		  be.TEMP_CLA_5THPAY as pre_TEMP_CLA_5THPAY,\r\n"
				+ "		   0  as cur_FRANKING_ALLOW,\r\n" + "		  be.FRANKING_ALLOW as pre_FRANKING_ALLOW,\r\n"
				+ "		  0  as cur_TEMP_HRA_5THPAY,\r\n" + "		  be.TEMP_HRA_5THPAY as pre_TEMP_HRA_5THPAY,\r\n"
				+ "		  0  as cur_LEAVE_TRAVEL_ASSISTANCE,\r\n"
				+ "		  be.LEAVE_TRAVEL_ASSISTANCE as pre_LEAVE_TRAVEL_ASSISTANCE,\r\n"
				+ "		   0  as cur_MEDICAL_STUDY_ALLOW,\r\n"
				+ "		  be.MEDICAL_STUDY_ALLOW as pre_MEDICAL_STUDY_ALLOW,\r\n"
				+ "		0  as cur_OTHER_ALLOWANCES,\r\n" + "		  be.OTHER_ALLOWANCES as pre_OTHER_ALLOWANCES,\r\n"
				+ "		    0  as cur_PERMANENT_TRAVELLING,\r\n"
				+ "		  be.PERMANENT_TRAVELLING as pre_PERMANENT_TRAVELLING,\r\n"
				+ "		0  as cur_TEMP_TA_5THPAY,\r\n" + "		  be.TEMP_TA_5THPAY as pre_TEMP_TA_5THPAY,\r\n"
				+ "		    0  as cur_WASH_ALLOW,\r\n" + "		  be.WASH_ALLOW as pre_WASH_ALLOW,\r\n"
				+ "		 0  as cur_WRITER_PAY_ALLOW,\r\n" + "		  be.WRITER_PAY_ALLOW as pre_WRITER_PAY_ALLOW,\r\n"
				+ "		 0  as cur_TRIBAL_ALLOW,\r\n" + "		  be.TRIBAL_ALLOW as pre_TRIBAL_ALLOW,\r\n"
				+ "		  0  as cur_CO_HSG_SOC,\r\n" + "		  be.CO_HSG_SOC as pre_CO_HSG_SOC,\r\n"
				+ "		   0  as cur_COMP_AIS,\r\n" + "		  be.COMP_AIS as pre_COMP_AIS,\r\n"
				+ "		 0  as cur_EXC_PAYRC,\r\n" + "		  be.EXC_PAYRC as pre_EXC_PAYRC,\r\n"
				+ "		   0  as cur_GPF_OTHER_STATE,\r\n" + "		  be.GPF_OTHER_STATE as pre_GPF_OTHER_STATE,\r\n"
				+ "		  0  as cur_HBA_AIS,\r\n" + "		  be.HBA_AIS as pre_HBA_AIS,\r\n"
				+ "		 0  as cur_HBA_HOUSE,\r\n" + "		  be.HBA_HOUSE as pre_HBA_HOUSE,\r\n"
				+ "		   0  as cur_MCA_AIS,\r\n" + "		  be.MCA_AIS as pre_MCA_AIS,\r\n"
				+ "		   0  as cur_OTHER_ADV,\r\n" + "		  be.OTHER_ADV as pre_OTHER_ADV,\r\n"
				+ "		0  as cur_GPF_IAS_LOAN,\r\n" + "		  be.GPF_IAS_LOAN as pre_GPF_IAS_LOAN,\r\n"
				+ "		  0  as cur_DCPS_DELAY,\r\n" + "		  be.DCPS_DELAY as pre_DCPS_DELAY,\r\n"
				+ "		  0  as cur_DCPS_PAY,\r\n" + "		  be.DCPS_PAY as pre_DCPS_PAY,\r\n"
				+ "		  0  as cur_REFRESHMENT_ALLOW,\r\n"
				+ "		  be.REFRESHMENT_ALLOW as pre_REFRESHMENT_ALLOW,\r\n" + "		   0  as cur_JANJULGIS,\r\n"
				+ "		  be.JANJULGIS as pre_JANJULGIS,\r\n" + "		 0  as cur_CDA,\r\n"
				+ "		  be.CDA as pre_ADD_DA,\r\n" + "		   0  as cur_CTA,\r\n"
				+ "		  be.CTA as pre_CTA,\r\n" + "		  0  as cur_PEON_ALLOWANCE,\r\n"
				+ "		  be.PEON_ALLOWANCE as pre_PEON_ALLOWANCE,\r\n" + "		  0  as cur_INCENTIVE_BDDS,\r\n"
				+ "		  be.INCENTIVE_BDDS as pre_INCENTIVE_BDDS,\r\n" + "		  0  as cur_RT_PILOT,\r\n"
				+ "		  be.RT_PILOT as pre_RT_PILOT,\r\n" + "		  0  as cur_CHPL_PILOT,\r\n"
				+ "		  be.CHPL_PILOT as pre_CHPL_PILOT,\r\n" + "		 0  as cur_KIT_PILOT,\r\n"
				+ "		  be.KIT_PILOT as pre_KIT_PILOT,\r\n" + "		  0  as cur_FLYING_PAY_PILOT,\r\n"
				+ "		  be.FLYING_PAY_PILOT as pre_FLYING_PAY_PILOT,\r\n"
				+ "		  0  as cur_INSTRUCTIONAL_PILOT,\r\n"
				+ "		  be.INSTRUCTIONAL_PILOT as pre_INSTRUCTIONAL_PILOT,\r\n"
				+ "		  0  as cur_QUALIFICATION_PILOT,\r\n"
				+ "		  be.QUALIFICATION_PILOT as pre_QUALIFICATION_PILOT,\r\n"
				+ "		  0  as cur_INSPECTION_PILOT,\r\n" + "		  be.INSPECTION_PILOT as pre_INSPECTION_PILOT,\r\n"
				+ "		  0  as cur_FLYING_ALLOW_PILOT,\r\n"
				+ "		  be.FLYING_ALLOW_PILOT as pre_FLYING_ALLOW_PILOT,\r\n" + "		  0  as cur_OUTFIT_PILOT,\r\n"
				+ "		  be.OUTFIT_PILOT as pre_OUTFIT_PILOT,\r\n" + "		  0  as cur_MILITERY_PILOT,\r\n"
				+ "		  be.MILITERY_PILOT as pre_MILITERY_PILOT,\r\n" + "		  0  as cur_SPECIAL_PAY_PILOT,\r\n"
				+ "		  be.SPECIAL_PAY_PILOT as pre_SPECIAL_PAY_PILOT,\r\n" + "		   0  as cur_CPF,\r\n"
				+ "		  be.CPF as pre_CPF,\r\n" + "		  0  as cur_BASIC_ARR,\r\n"
				+ "		  be.BASIC_ARR as pre_BASIC_ARR,\r\n" + "		   0  as cur_DA_ON_TA,\r\n"
				+ "		  be.DA_ON_TA as pre_DA_ON_TA,\r\n" + "		  0  as cur_TRANS_ARREAR,\r\n"
				+ "		  be.TRANS_ARREAR as pre_TRANS_ARREAR,\r\n" + "		  0  as cur_OVERTIME_ALLOW,\r\n"
				+ "		  be.OVERTIME_ALLOW as pre_OVERTIME_ALLOW,\r\n" + "		  0  as cur_CPF_CONTRIBUTION,\r\n"
				+ "		  be.CPF_CONTRIBUTION as pre_CPF_CONTRIBUTION,\r\n" + "		 0  as cur_CPF_EMPLOYEE,\r\n"
				+ "		  be.CPF_EMPLOYEE as pre_CPF_EMPLOYEE,\r\n" + "		  0  as cur_CPF_EMPLOYER,\r\n"
				+ "		  be.CPF_EMPLOYER as pre_CPF_EMPLOYER,\r\n" + "		  0  as cur_ACC_POLICY,\r\n"
				+ "		  be.ACC_POLICY as pre_ACC_POLICY,\r\n" + "		  0  as cur_SVNPC_DA,\r\n"
				+ "		  be.SVNPC_DA as pre_SVNPC_DA,\r\n" + "		  0  as cur_SVNPC_TA,\r\n"
				+ "		  be.SVNPC_TA as pre_SVNPC_TA,\r\n" + "		   0  as cur_SVNPC_GPF_ARR,\r\n"
				+ "		  be.SVNPC_GPF_ARR as pre_SVNPC_GPF_ARR,\r\n" + "		0  as cur_SVNPC_DCPS_ARR,\r\n"
				+ "		  be.SVNPC_DCPS_ARR as pre_SVNPC_DCPS_ARR,\r\n" + "		    0  as cur_SVNPC_TA_ARR,\r\n"
				+ "		  be.SVNPC_TA_ARR as pre_SVNPC_TA_ARR,\r\n" + "		 0  as cur_SVNPC_GPF_ARR_DEDU,\r\n"
				+ "		  be.SVNPC_GPF_ARR_DEDU as pre_SVNPC_GPF_ARR_DEDU,\r\n" + "		 0  as cur_SVNPC_GPF_RECO,\r\n"
				+ "		  be.SVNPC_GPF_RECO as pre_SVNPC_GPF_RECO,\r\n" + "		  0  as cur_SVNPC_DCPS_RECO,\r\n"
				+ "		  be.SVNPC_DCPS_RECO as pre_SVNPC_DCPS_RECO,\r\n" + "		   0  as cur_NPS_EMPLR,\r\n"
				+ "		  be.NPS_EMPLR as pre_NPS_EMPLR,\r\n" + "		 0  as cur_NPS_EMPLR_CONTRI_DED,\r\n"
				+ "		  be.NPS_EMPLR_CONTRI_DED as pre_NPS_EMPLR_CONTRI_DED,\r\n"
				+ "		  0  as cur_REVENUE_STAMP,\r\n" + "		  be.REVENUE_STAMP as pre_REVENUE_STAMP,\r\n"
				+ "		  0  as cur_lic,\r\n" + "		  be.lic as pre_lic,\r\n" + "		   0  as cur_COP_Bank,\r\n"
				+ "		  be.COP_Bank as pre_COP_Bank,\r\n" + "		 0  as cur_Recurring_deposite,\r\n"
				+ "		  be.Recurring_deposite as pre_Recurring_deposite,\r\n" + "		  0  as cur_credit_soc,\r\n"
				+ "		  be.credit_soc as pre_credit_soc,\r\n" + "		  0  as cur_con_store,\r\n"
				+ "		  be.con_store as pre_con_store,\r\n" + "		  0  as cur_mantralaya_bank,\r\n"
				+ "		  be.mantralaya_bank as pre_mantralaya_bank,\r\n" + "		  0  as cur_mis,\r\n"
				+ "		  be.mis as pre_mis,\r\n" + "		   0  as cur_mrt_cop_soc,\r\n"
				+ "		  be.mrt_cop_soc as pre_mrt_cop_soc,\r\n" + "		   0  as cur_other_Deduc,\r\n"
				+ "		  be.other_Deduc as pre_other_Deduc,\r\n" + "		 0  as cur_other_Recovery,\r\n"
				+ "		  be.other_Recovery as pre_other_Recovery\r\n"
				+ "	                                                                                                 \r\n"
				+ "        from\r\n"
				+ "            paybill_generation_trn a                                                \r\n"
				+ "        inner join\r\n"
				+ "            paybill_generation_trn_details be                                                                             \r\n"
				+ "                on a.paybill_generation_trn_id = be.paybill_generation_trn_id                                                \r\n"
				+ "        inner join\r\n"
				+ "            employee_mst  c                                                                             \r\n"
				+ "                on be.sevaarth_id = c.sevaarth_id                                                 \r\n"
				+ "        inner join\r\n"
				+ "            year_mst d                                                                             \r\n"
				+ "                on be.paybill_year = d.year_id                                                 \r\n"
				+ "        inner join\r\n"
				+ "            month_mst e                                                                             \r\n"
				+ "                on be.paybill_month = e.month_id                                                 \r\n"
				+ "        inner join\r\n"
				+ "            designation_mst as deg                                                                             \r\n"
				+ "                on deg.designation_code=c.designation_code                                                \r\n"
				+ "        where\r\n" + "            a.is_active<>8                                 \r\n"
				+ "            and               cast (e.month_id as varchar)||d.year_english||be.sevaarth_id in (\r\n"
				+ "                select\r\n"
				+ "                    cast (case                                                                                                             \r\n"
				+ "                        when (e.month_id= 1) then 12                                                                                                             \r\n"
				+ "                        else (e.month_id -1)                                                                                             \r\n"
				+ "                    end as varchar)||case                                                                                             \r\n"
				+ "                    when (e.month_id= 1) then (d.year_english-1)                                                                                             \r\n"
				+ "                    else d.year_english  end||sevaarth_id                                                                             \r\n"
				+ "                from\r\n"
				+ "                    paybill_generation_trn_details be                                                                                 \r\n"
				+ "                inner join\r\n"
				+ "                    year_mst d                                                                                                             \r\n"
				+ "                        on be.paybill_year = d.year_id                                                                                \r\n"
				+ "                inner join\r\n"
				+ "                    month_mst e                                                                                                             \r\n"
				+ "                        on be.paybill_month = e.month_id                                                                                 \r\n"
				+ "                where\r\n" + "                    be.paybill_generation_trn_id = '"
				+ paybillGenerationTrnId + "'                                                           \r\n"
				+ "            )                                            \r\n"
				+ "        ) as temp                          \r\n" + "    group by\r\n"
				+ "        temp.sevaarth_id,\r\n" + "        temp.employee_full_name_en,\r\n"
				+ "        temp.designation_name     \r\n" + "	";
		Query query = currentSession.createNativeQuery(HQL);
		System.out.println("============change statement============" + HQL);
		// logger.info(" "+query.getQueryString());
		/*
		 * String HQL11 =
		 * "FROM MpgSchemeBillGroupEntity as t where t.isActive = '1' and t.ddoMapId = "
		 * +query.uniqueResult()+" ORDER BY t.billDescription ";
		 */
		return query.list();

	}

	// @SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getChangeStatementReportFromPreviousMonth(String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select concat((select employee_full_name_en from employee_mst where sevaarth_id=c.sevaarth_id),'(',(select designation_name from designation_mst where designation_code=(select designation_code from employee_mst where sevaarth_id=c.sevaarth_id)),')')"
				+ "								,d.year_english,e.month_id,be.*  from paybill_generation_trn a"
				+ "								inner join paybill_generation_trn_details be on a.paybill_generation_trn_id = be.paybill_generation_trn_id"
				+ "								inner join employee_mst  c on be.sevaarth_id = c.sevaarth_id"
				+ "								inner join year_mst d on be.paybill_year = d.year_id"
				+ "								inner join month_mst e on be.paybill_month = e.month_id"
				+ "								where cast (e.month_id as varchar)||d.year_english||be.sevaarth_id in ("
				+ "								select  cast (case when (e.month_id= 1) then 12 else (e.month_id -1) end as varchar)||case when (e.month_id= 1) then (d.year_english-1) else d.year_english  end||sevaarth_id from  paybill_generation_trn_details be"
				+ "								inner join year_mst d on be.paybill_year = d.year_id"
				+ "						        inner join month_mst e on be.paybill_month = e.month_id where  be.paybill_generation_trn_id = '"
				+ ddoCode + "')";
		Query query = currentSession.createNativeQuery(HQL);
		/*
		 * String HQL11 =
		 * "FROM MpgSchemeBillGroupEntity as t where t.isActive = '1' and t.ddoMapId = "
		 * +query.uniqueResult()+" ORDER BY t.billDescription ";
		 */
		return query.list();

	}

	@Override
	public PaybillGenerationTrnEntity consolidatedPaybill(Long paybillGenerationTrnId) {
		PaybillGenerationTrnEntity objCadre = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objCadre = currentSession.get(PaybillGenerationTrnEntity.class, paybillGenerationTrnId);
		return objCadre;
	}

	@Override
	public List<Object[]> findDDOinfo(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select ddo_name||' '||ddo_code,dept_loc_code  from org_ddo_mst where ddo_code= '" + userName
				+ "'";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();

	}

	@Override
	public List<Object[]> findregIdinfo(Long regid) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select * from org_inst_mst   where ddo_reg_id = " + regid;
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> findmonthinfo(BigInteger currmonth) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select * from month_mst     where month_id   ='" + currmonth + "' ";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> findofcIdinfo(Long ofcid) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select * from treasury_office_mst   where treasury_office_id = " + ofcid;
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> getAbstractReport(String paybillGenerationTrnId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select bill.ddo_code||' ('|| off.off_name || ')' as office,billgroup.description as billName,bill.bill_gross_amt as TOTAL_SALARY, "
				+ " sum(billdtl.FESTIVAL_ADVANCE) as FA,sum(billdtl.EXC_PAYRC) as Recovery,sum(billdtl.GPF_IV)+sum(billdtl.GPF_ADV)+sum(billdtl.GPF_ADV_GRP_ABC)+sum(billdtl.GPF_ADV_GRP_D)+sum(billdtl.GPF_GRP_D)+sum(billdtl.GPF_GRP_ABC) as GPF, "
				+ " sum(billdtl.DCPS) as dcpsReg,sum(billdtl.DCPS_DELAY) as dcpsDelay,sum(billdtl.DCPS_PAY) as dcpsPay,sum(billdtl.DCPS_DA) as dcpsDa, "
				+ " sum(billdtl.NPS_EMPLR_CONTRI_DED) as npsEmprDeduc,sum(billdtl.IT) as IT,sum(billdtl.pt) as PT,sum (billdtl.COMPUTER_ADV)as ComputerAdv, "
				+ " sum(billdtl.OTHER_DEDUCTION) as OTHER_DEDUCTION,sum(billdtl.PLI) as pli,SUM(billdtl.gis) AS GIS,sum(billdtl.ACC_POLICY) as accPolicy, "
				+ " sum (billdtl.revenue_stamp)as Revenue_stamp,sum(billdtl.TOTAL_DED) as TOTAL_DED,bill.bill_net_amount as NET_PAY,sum(billdtl.Recurring_deposite)as recurring_dep, "
				+ " sum(billdtl.lic)as lic,sum(billdtl.misc)as MISC,0 as bankLoan,0 as societyLoan,sum(billdtl.TOTAL_DED) as ngrtotaldeduc,sum(billdtl.NET_TOTAL) as salpay from "
				+ " paybill_generation_trn  bill inner join paybill_generation_trn_details billdtl ON billdtl.paybill_generation_trn_id=bill.paybill_generation_trn_id    "
				+ " inner join consolidate_paybill_trn_mpg conbillmpg ON  conbillmpg.paybill_generation_trn_id=bill.paybill_generation_trn_id   "
				+ " inner join consolidate_paybill_trn conbill ON conbillmpg.consolidate_paybill_trn_id =conbill.consolidate_paybill_trn_id  "
				+ " inner join  mst_dcps_bill_group billgroup ON billgroup.bill_group_id =bill.scheme_billgroup_id  "
				+ " inner join org_ddo_mst ddo ON ddo.ddo_code =bill.ddo_code and conbill.consolidate_paybill_trn_id  ='"
				+ paybillGenerationTrnId + "'"
				+ " inner join mst_dcps_ddo_office off ON off.ddo_code =ddo.ddo_code inner join month_mst month ON month.month_id =bill.paybill_month  "
				+ " inner join year_mst year ON year.year_id =bill.paybill_year   "
				+ " GROUP BY billgroup.bill_group_id,billgroup.description,month_english,year_english,ddo.ddo_code,ddo.account_no,off.off_name, "
				+ " bill.bill_gross_amt,bill.bill_net_amount,bill.ddo_code  "; // conbill.consolidate_paybill_trn_id
		// =3
		Query query = currentSession.createNativeQuery(HQL);
		System.out.println("------HQL---" + HQL);
		return query.list();
	}

	@Override
	public List<Object[]> findyearinfo(BigInteger yearcurr) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select * from year_mst  where year_id  = '" + yearcurr + "' ";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();

	}

	// Created by Manikandan for Supplimentory Paybill
	@Override
	public int isBrokenPeriodEmpty(String sevaarthid, String monthid, String yearid) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select count(*)  from broken_period_pay_mst where sevaarth_id ='" + sevaarthid + "' and month_id="
				+ monthid + " and year_id=" + yearid;
		Query query = currentSession.createNativeQuery(HQL);
		// logger.info("query="+query.getQueryString());
		// logger.info("query.list().get(0)="+query.list().get(0));
		int result = ((BigInteger) query.list().get(0)).intValue();
		return result;

	}

	// Created by Manikandan for Supplimentory Paybill
	@Override
	public List<Object[]> getBrokenPeriodData(String sevaarthid, String monthid, String yearid, String Username) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append(
				"select bppay.emp_id,bppay.sevaarth_id,sum(bppay.basic_pay) basicpay,sum(bppay.net_pay) netpay,bpallded.allow_deduc_code, ");
		sb.append(
				"sum(bpallded.allow_deduc_amt) allow_ded_amt , deptallmst.department_allowdeduc_col_nm,deptallmst.broken_method_name,deptallmst.is_allowdeduc_type_sum,deptallmst.is_rule_based,deptallmst.is_non_computation_component,deptallmst.is_non_government ");
		sb.append(
				"from broken_period_pay_mst bppay inner join broken_period_allow_deduc_mst bpallded on bppay.broken_period_id=bpallded.broken_period_id ");
		sb.append(
				"inner join department_allowdeduc_mst deptallmst on deptallmst.department_allowdeduc_code=bpallded.allow_deduc_code ");
		sb.append("where bppay.sevaarth_id='" + sevaarthid + "' and bppay.month_id=" + monthid + " and bppay.year_id="
				+ yearid + " and bppay.ddo_code='" + Username + "' ");
		sb.append(
				" group by  bppay.emp_id,bppay.sevaarth_id,bpallded.allow_deduc_code ,deptallmst.department_allowdeduc_col_nm,deptallmst.broken_method_name,deptallmst.is_allowdeduc_type_sum,deptallmst.is_rule_based,deptallmst.is_non_computation_component,deptallmst.is_non_government order by bppay.emp_id");
		Query query = currentSession.createNativeQuery(sb.toString());
		return query.list();
	}

	@Override
	public List<Object[]> getViewDetialsReport(Integer consolidatedId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select c.paybill_generation_trn_id,d.bill_description,office_name,c.bill_gross_amt as groamt,c.bill_gross_amt-c.bill_net_amount as ded,c.bill_net_amount from consolidate_paybill_trn a "
				+ " inner join consolidate_paybill_trn_mpg b on a.consolidate_paybill_trn_id = b.consolidate_paybill_trn_id inner join paybill_generation_trn c on b.paybill_generation_trn_id = c.paybill_generation_trn_id "
				+ "inner join bill_group_mst d on c.scheme_billgroup_id = d.bill_group_id inner join ddo_reg_mst e ON e.ddo_code =c.ddo_code where a.consolidate_paybill_trn_id  = "
				+ consolidatedId;
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public int getDaPercentageByMonthYear(String startDate, int commoncodePaycommission7pc, int allowDeducCode) {
		Session currentSession = entityManager.unwrap(Session.class);

		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(" FROM AllowanceDeductionRuleMstEntity as t WHERE ");
		lSBQuery.append(" t.departmentAllowdeducCode=" + allowDeducCode);
		lSBQuery.append(" AND t.isActive='1' and  t.payCommissionCode=" + commoncodePaycommission7pc);
		lSBQuery.append(" AND (to_char(t.startDate,'YY-MM-DD')<='" + startDate
				+ "'   OR  to_char(t.startDate,'YYYY-MM-DD')<='" + startDate + "')");
		lSBQuery.append(" AND (t.endDate is null OR to_char(t.endDate,'YY-MM-DD')>='" + startDate
				+ "'  OR to_char(t.endDate,'YYYY-MM-DD')>='" + startDate + "') ");
		lSBQuery.append("  ORDER BY t.startDate DESC");

		/*
		 * String HQL =
		 * "FROM AllowanceDeductionRuleMstEntity as t where   t.departmentAllowdeducCode="
		 * + allowDeducCode + " and t.isActive='1' and  t.payCommissionCode=" +
		 * commoncodePaycommission7pc + "   and to_char(t.startDate,'YY-MM-DD')<='" +
		 * startDate + "' " +
		 * " and (t.endDate is null OR to_char(t.endDate,'YY-MM-DD')>='" + startDate +
		 * "')  ORDER BY t.startDate DESC";
		 */

		// getDaPercentageByMonthYear
		System.out.println("--------------------------------" + lSBQuery);

		List<AllowanceDeductionRuleMstEntity> lstAllowanceDeductionMstEntity = (List<AllowanceDeductionRuleMstEntity>) entityManager
				.createQuery(lSBQuery.toString()).getResultList();
		Integer percentage = lstAllowanceDeductionMstEntity.stream().map(m -> m.getPercentage()).findFirst().orElse(0);
		return percentage;

	}

	@Override
	public String getHRAPercentageByMonthYear(String startDate, int commoncodePaycommission7pc, String cityClass) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select t.payCommissionCode,t.cityClass" + cityClass + ""
				+ " FROM HRAAllowanceMstEntity as t where t.payCommissionCode=" + commoncodePaycommission7pc
				+ "   and to_char(t.startDate,'YY-MM-DD')<='" + startDate + "' "
				+ " and t.endDate is null OR to_char(t.endDate,'YY-MM-DD')>='" + startDate
				+ "'   ORDER BY t.startDate DESC";
		System.out.println("---------------getHRAPercentageByMonthYear-----------------" + HQL);

		String result = null;
		List<Object[]> lstAllowanceDeductionMstEntity = (List<Object[]>) entityManager.createQuery(HQL).getResultList();
		for (Object lst[] : lstAllowanceDeductionMstEntity) {
			result = lst[1].toString();
		}
		return result;
	}

	@Override
	public Integer isPaybillExistsForCurrentMonth(Long schemeBillgroupId, int paybillMonth, int paybillYear) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select count(*)  from paybill_generation_trn where  paybill_month ='" + paybillMonth
				+ "' and paybill_year ='" + paybillYear + "' and scheme_billgroup_id ='" + schemeBillgroupId
				+ "' and is_active in(1,2,3,4,5,6,7,9,10,11,12,13,14)";
		Query query = currentSession.createNativeQuery(HQL);
		// logger.info("query="+query.getQueryString());
		// logger.info("query.list().get(0)="+query.list().get(0));
		int result = ((BigInteger) query.list().get(0)).intValue();
		return result;

	}

	@Override
	public int getCheckIsBillInProcess(int monthName, int yearName, BigInteger schemeBillGroupId, int paybillType) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select count(*)  from paybill_generation_trn where scheme_billgroup_id ='" + schemeBillGroupId
				+ "' and bill_type_id =" + paybillType + " and is_active in(1,2,3,4,5,6,7,9,10,11,12,13)";
		System.out.println(HQL);
		Query query = currentSession.createNativeQuery(HQL);
		int result = ((BigInteger) query.list().get(0)).intValue();
		return result;
	}

	@Override
	public String getgradePay7PC(Long gradelevel) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select grade_pay from rlt_payband_gp_state_7pc where level = '" + gradelevel + "'";
		System.out.println("---------------getgradePay7PC-----------------" + HQL);
		Query query = currentSession.createNativeQuery(HQL);
		String result = (String) query.list().get(0);
		return result;
	}

	@Override
	public String isEmpRetired(int monthName, int yearName, BigInteger schemeBillGroupId, int paybillType,
			String ddoCode) {
		// TODO Auto-generated method stub
		yearName = 2000 + yearName - 1;
		String month1 = null;
		int numDays = 0;
		int numDays1 = 0;
		int numDays2 = 0;
		int totalnumDays2 = 0;
		if (monthName < 10) {
			month1 = "0" + monthName;
		} else {
			month1 = String.valueOf(monthName);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, yearName);
		calendar.set(Calendar.MONTH, monthName - 1);
		numDays = calendar.getActualMaximum(Calendar.DATE);

		Calendar cal = Calendar.getInstance();
		cal.setTime(calendar.getTime());
		cal.set(Calendar.MONTH, monthName);
		numDays1 = cal.getActualMaximum(Calendar.DATE);

		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(calendar.getTime());
		calendar2.set(Calendar.MONTH, monthName + 1);
		numDays2 = calendar2.getActualMaximum(Calendar.DATE);

		totalnumDays2 = numDays + numDays1 + numDays2;

		Session currentSession = entityManager.unwrap(Session.class);
		String Hql = "select emst.employee_id,emst.sevaarth_id,emst.super_ann_date,emst.emp_service_end_date from employee_mst emst "
				+ " where emst.is_active = 1 and  emst.ddo_code = '" + ddoCode + "' and emst.billgroup_id = '"
				+ schemeBillGroupId + "' " + " and to_char(emst.doj,'YYYY-MM')<='" + yearName + "-" + month1
				+ "'  and  (to_char(emst.super_ann_date,'YYYY-MM')>='" + yearName + "-" + month1 + "' "
				+ " and   to_char(emst.emp_service_end_date,'YYYY-MM')>='" + yearName + "-" + month1 + "') "
				+ "and (super_ann_date-cast('" + yearName + "-" + monthName + "-" + 01 + "' as date) < " + totalnumDays2
				+ " or  emp_service_end_date-cast('" + yearName + "-" + monthName + "-" + 01 + "' as date) < "
				+ totalnumDays2 + ")"; //

		Query query = currentSession.createNativeQuery(Hql);
		System.out.println("isEmpRetired" + query);
		List<Object[]> lstemp = query.list();
		String msg = "";
		if (lstemp.size() > 0) {
			for (Object lst[] : lstemp) {
				if (msg.equals(""))
					msg = msg + isEmpRetiredBySevaarthId(lst[1].toString(), StringHelperUtils.isNullDate(lst[2]));
				else
					msg = "\n" + msg
							+ isEmpRetiredBySevaarthId(lst[1].toString(), StringHelperUtils.isNullDate(lst[2]));
			}
		}
		return msg;
	}

	@Override
	public String isEmpRetiredBySevaarthId(String sevaarthId, Date suppAnnDate) {
		// TODO Auto-generated method stub
		String msg = "";
		Session currentSession = entityManager.unwrap(Session.class);
		String sqlQuery = "select e.department_allowdeduc_code,c.department_allowdeduc_name,e.sevaarth_id,e.with_effective_date from employee_mst d"
				+ " inner join employee_allowdeduc_mpg e on d.sevaarth_id = e.sevaarth_id  and e.is_active = '1' inner join department_allowdeduc_mst c "
				+ "on e.department_allowdeduc_code = c.department_allowdeduc_code and c.is_active = '1' "
				+ "where c.department_allowdeduc_code  in(37,38,89,90,102,103,111,112) and e.sevaarth_id =  '"
				+ sevaarthId + "' ";
		Query query = currentSession.createNativeQuery(sqlQuery);
		List<Object[]> lstemp = query.list();
		if (lstemp.size() > 0) {
			msg = sevaarthId + " Employee Retirement date is " + suppAnnDate
					+ " please update employee eligibility !!!";
		}
		return msg;
	}

	@Override
	public PaybillGenerationTrnEntity findPaybillById(Long paybillGenerationTrnId) {
		PaybillGenerationTrnEntity objCadre = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objCadre = currentSession.get(PaybillGenerationTrnEntity.class, paybillGenerationTrnId);
		return objCadre;
	}

	@Override
	public void updateVoucherEntry(PaybillGenerationTrnEntity objPaybillGeberationTrnEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(objPaybillGeberationTrnEntity);

	}

	@Override
	public List<MstEmployeeEntity> checkedBgisAndGisCatNull(String schemeBillGroupId, String userName) {
		// TODO Auto-generated method stub
		String HQL = "FROM MstEmployeeEntity as  t  where t.ddoCode = '" + userName.trim() + "' and t.billGroupId = "
				+ schemeBillGroupId
				+ " AND t.isActive='1' and t.sevaarthId!=null and ((t.begisCatg is null or t.begisCatg='0') or  (t.giscatagory is null or t.giscatagory=0)) "
				+ " ORDER BY t.employeeFullNameEn";

		HQL = "FROM MstEmployeeEntity as  t  where t.ddoCode = '" + userName.trim() + "' and t.billGroupId = "
				+ schemeBillGroupId + " AND t.isActive='1'   ORDER BY t.employeeFullNameEn";
		return (List<MstEmployeeEntity>) entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public Integer getannualincment(String sevaarthId, String startDate) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "FROM EmployeeIncrementEntity  where sevaarthId='" + sevaarthId
				+ "'   and to_char(effective_from_date,'YY-MM-DD')<='" + startDate + "' "
				+ " and (to_increment_date is null OR to_char(to_increment_date,'YY-MM-DD')>='" + startDate
				+ "')  ORDER BY basic_pay_increment_id DESC";
		System.out.println("--------------------------------" + HQL);

		List<EmployeeIncrementEntity> lstAllowanceDeductionMstEntity = (List<EmployeeIncrementEntity>) entityManager
				.createQuery(HQL).getResultList();
		Double percentage = lstAllowanceDeductionMstEntity.stream().map(m -> m.getIncrementBasicPaySal()).findFirst()
				.orElse((double) 0);
		return percentage.intValue();
	}

	@Override
	public Integer getamtbeforeannualincment(String sevaarthId, String startDate) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "FROM EmployeeIncrementEntity  where sevaarthId='" + sevaarthId
				+ "'   and to_char(effective_from_date,'YY-MM-DD')<='" + startDate + "' "
				+ " and (to_increment_date is null OR to_char(to_increment_date,'YY-MM-DD')>='" + startDate
				+ "')  ORDER BY basic_pay_increment_id DESC";
		System.out.println("--------------------------------" + HQL);

		List<EmployeeIncrementEntity> lstAllowanceDeductionMstEntity = (List<EmployeeIncrementEntity>) entityManager
				.createQuery(HQL).getResultList();
		Double percentage = lstAllowanceDeductionMstEntity.stream().map(m -> m.getPreBasicPay()).findFirst()
				.orElse((double) 0);
		return percentage.intValue();
	}

	@Override
	public int getDaCentralPercentageByMonthYear(String startDate, int commoncodePaycommission7pc) {
		Session currentSession = entityManager.unwrap(Session.class);

		String HQL1 = "FROM CentralGovtDAMasterEntity as t where  t.isActive='1' and t.payCommissionCode="
				+ commoncodePaycommission7pc + "   and to_char(t.startDate,'YY-MM-DD')>='" + startDate + "' "
				+ "  ORDER BY t.startDate DESC";

		List<CentralGovtDAMasterEntity> lstAllowanceDeductionMstEntity1 = (List<CentralGovtDAMasterEntity>) entityManager
				.createQuery(HQL1).getResultList();

		if (lstAllowanceDeductionMstEntity1.size() > 0 && lstAllowanceDeductionMstEntity1 != null) {
			Integer percentage = lstAllowanceDeductionMstEntity1.stream().map(m -> m.getPercentage()).findFirst()
					.orElse(0);
			return percentage;
		} else {
			String HQL = "FROM CentralGovtDAMasterEntity as t where   t.isActive='1' and  t.payCommissionCode="
					+ commoncodePaycommission7pc + "   and to_char(t.startDate,'YY-MM-DD')<='" + startDate + "' "
					+ " and (t.endDate is null OR to_char(t.endDate,'YY-MM-DD')>='" + startDate
					+ "')  ORDER BY t.startDate DESC";

			System.out.println("--------------------------------" + HQL);

			List<CentralGovtDAMasterEntity> lstAllowanceDeductionMstEntity = (List<CentralGovtDAMasterEntity>) entityManager
					.createQuery(HQL).getResultList();
			Integer percentage = lstAllowanceDeductionMstEntity.stream().map(m -> m.getPercentage()).findFirst()
					.orElse(0);
			return percentage;
		}
	}

	@Override
	public int savePaybillStatus(PaybillStatusEntity paybillStatusEntity) {

		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(paybillStatusEntity);
		return (Integer) saveId;
	}

	@Override
	public List<AllowanceDeductionRuleMstEntity> fetchComponentDtlsByCompoId(int CompoId) {
		Session currentSession = entityManager.unwrap(Session.class);

		String HQL1 = "FROM AllowanceDeductionRuleMstEntity as t where  t.isActive='1' and t.departmentAllowdeducCode="
				+ CompoId + " ";

		List<AllowanceDeductionRuleMstEntity> lstAllowanceDeductionMstEntity1 = (List<AllowanceDeductionRuleMstEntity>) entityManager
				.createQuery(HQL1).getResultList();

		return lstAllowanceDeductionMstEntity1;
	}

	@Override
	public List<AllowanceDeductionRuleMstEntity> getClaAmaountDtls(Long sevenPcLevel, Double basic, String citygroup,
			Long payCommissionCode, int allowDeducCode) {
		Session currentSession = entityManager.unwrap(Session.class);

		String HQL1 = "FROM AllowanceDeductionRuleMstEntity as t where  t.isActive='1' and t.minBasic<=" + basic
				+ " and (maxBasic is null or maxBasic >= " + basic + ") and cityGroup='" + citygroup.trim()
				+ "'  and t.departmentAllowdeducCode=" + allowDeducCode;

		List<AllowanceDeductionRuleMstEntity> claMstLst = (List<AllowanceDeductionRuleMstEntity>) entityManager
				.createQuery(HQL1).getResultList();

		return claMstLst;
	}

	@Override
	public List<AllowanceDeductionRuleMstEntity> fetchhraDtls(int allowDeducCode, String startDate, String citygroup,
			Double basic, int payComm) {
		Session currentSession = entityManager.unwrap(Session.class);

		String HQL1 = "FROM AllowanceDeductionRuleMstEntity as t where   t.isActive='1' and to_char(t.startDate,'YY-MM-DD')<='"
				+ startDate + "' and t.endDate is null OR to_char(t.endDate,'YY-MM-DD')>='" + startDate
				+ "'  and departmentAllowdeducCode =" + allowDeducCode + " and t.cityClass ='" + citygroup + "' and "
				+ basic + ">=22500 and t.payCommissionCode=" + payComm + " ORDER BY t.startDate DESC";

		List<AllowanceDeductionRuleMstEntity> hraLst = (List<AllowanceDeductionRuleMstEntity>) entityManager
				.createQuery(HQL1).getResultList();

		return hraLst;
	}

	@Override
	public Double findGisComponentValue(String gisgroup, Date doj, String startDate, int allowD) {
		Session currentSession = entityManager.unwrap(Session.class);

		String sql = "select  CASE  WHEN DATE_PART('month',cast(:doj as date)) = 1 AND DATE_PART('day',cast( :doj as date))=1 THEN "
				+ "  CASE WHEN EXTRACT(YEAR FROM AGE(cast(:startDate as date), cast(:doj as date))) >1 THEN p.premium_amount else  p.amount end else p.premium_amount  "
				+ "  END AS selected_amount from allowance_deduction_wise_rule_mst p WHERE city_group = :groupName and   is_Active='1'  and department_allowdeduc_code=:allowD";

		NativeQuery<Double> query = currentSession.createNativeQuery(sql);
		query.setParameter("doj", doj);
		query.setParameter("groupName", gisgroup);
		query.setParameter("startDate", startDate);
		query.setParameter("allowD", allowD);

		query.addScalar("selected_amount", StandardBasicTypes.DOUBLE);

		return (Double) query.uniqueResult();
	}

	@Override
	public Double fetchHraDtls(Double basic, String startDate, String cityClass, int allowDeducCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String sql = "SELECT CASE WHEN :basic < min_basic THEN min_basic ELSE :basic * percentage / 100 END AS amount "
				+ "FROM allowance_deduction_wise_rule_mst " + "WHERE city_class = :cityClass   and  is_Active='1' "
				+ "AND to_char(start_date, 'YY-MM-DD') <= :startDate "
				+ "AND (end_date IS NULL OR to_char(end_date, 'YY-MM-DD') >= :startDate)  and  department_allowdeduc_code=:allowDeducCode";

		NativeQuery<Double> query = currentSession.createNativeQuery(sql);
		query.setParameter("basic", basic);
		query.setParameter("cityClass", cityClass);
		query.setParameter("startDate", startDate);
		query.setParameter("allowDeducCode", allowDeducCode);

		query.addScalar("amount", StandardBasicTypes.DOUBLE);
		return (Double) query.uniqueResult();
	}

	@Override
	public Double fetchAccidentialPilocyDtls(String startDate, String citygroup, int allowDeducCode) {
		Session currentSession = entityManager.unwrap(Session.class);

		String sql = "SELECT amount FROM allowance_deduction_wise_rule_mst "
				+ "WHERE   is_Active='1' and department_allowdeduc_code=:allowDeducCode AND city_group = :citygroup "
				+ "AND to_char(start_date, 'YY-MM-DD') <= :startDate "
				+ "AND (end_date IS NULL OR to_char(end_date, 'YY-MM-DD') >= :startDate)";

		NativeQuery<Double> query = currentSession.createNativeQuery(sql);

		query.setParameter("citygroup", citygroup);
		query.setParameter("startDate", startDate);
		query.setParameter("allowDeducCode", allowDeducCode);

		query.addScalar("amount", StandardBasicTypes.DOUBLE);

		return (Double) query.uniqueResult();
	}

	@Override
	public Double calculatePt(Double basic, int paybillMonth) {
		Session currentSession = entityManager.unwrap(Session.class);

		String sql = "SELECT calculate_pt(:basic, :paybillMonth) AS pt";

		NativeQuery<Double> query = currentSession.createNativeQuery(sql);

		query.setParameter("basic", basic);
		query.setParameter("paybillMonth", paybillMonth);

		query.addScalar("pt", StandardBasicTypes.DOUBLE);

		return (Double) query.uniqueResult();
	}

	@Override
	public Double calculateFamilyAllow(Long payCommission, Long sevenPcLevel, int allowDeducCode) {
		Session currentSession = entityManager.unwrap(Session.class);

		String sql = " select ALLOWANCE_DEDUCTION_WISE_RULE_ID,amount from allowance_deduction_wise_rule_mst where   is_Active='1' and department_allowdeduc_code = :allowDeducCode and "
				+ " :sevenPcLevel BETWEEN grade_pay_lower AND COALESCE(grade_pay_higher, :sevenPcLevel) and pay_commission_code = :payCommission ";

		Query query = currentSession.createNativeQuery(sql);
		query.setParameter("payCommission", payCommission);
		query.setParameter("sevenPcLevel", sevenPcLevel);
		query.setParameter("allowDeducCode", allowDeducCode);

		query.list();

		List<Object[]> lstemp = query.list();
		if (lstemp.size() > 1) {
			for (Object[] objects : lstemp) {
				if (sevenPcLevel >= StringHelperUtils.isNullDouble(objects[1])) {
					return StringHelperUtils.isNullDouble(objects[1]);
				}
			}
		} else if (lstemp.size() == 1) {
			for (Object[] objects : lstemp) {
				return StringHelperUtils.isNullDouble(objects[1]);
			}

		}

		return 0d;
	}

	@Override
	public Double fetchtaDtls(Double basic, Long payCommission, int allowDeducCode, Long gradelevel, String cityClass,
			String physicallyHandicapped) {
		Session currentSession = entityManager.unwrap(Session.class);

		String sql = "SELECT calculate_ta(" + basic + "," + payCommission + "," + allowDeducCode + "," + gradelevel
				+ ",'" + cityClass.trim() + "','" + physicallyHandicapped + "') AS ta";

		NativeQuery<Double> query = currentSession.createNativeQuery(sql);
		/*
		 * query.setParameter("basic", basic); query.setParameter("payCommission",
		 * payCommission); query.setParameter("allowDeducCode", allowDeducCode);
		 * query.setParameter("gradelevel", gradelevel); query.setParameter("cityClass",
		 * cityClass); query.setParameter("physicallyHandicapped",
		 * physicallyHandicapped);
		 */

		query.addScalar("ta", StandardBasicTypes.DOUBLE);

		return (Double) query.uniqueResult();
	}

	@Override
	public String getEmpCadre(String sevaarthId, Long empClass) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select group_name_en from cadre_group_mst a inner join employee_mst b on a.id=b.emp_class where b.sevaarth_id='"
				+ sevaarthId + "'";
		System.out.println("---------------getEmpCadre-----------------" + HQL);
		Query query = currentSession.createNativeQuery(HQL);
		String result = (String) query.list().get(0);
		return result;
	}

	@Override
	public Long saveBulkPaybillDetail(List<PaybillGenerationTrnDetails> lstPaybillGenerationTrnDetails) {
		int batchSize = 1000;
		if (lstPaybillGenerationTrnDetails.size() > batchSize) {
			for (int i = 0; i < lstPaybillGenerationTrnDetails.size(); i += batchSize) {
				int endIndex = Math.min(i + batchSize, lstPaybillGenerationTrnDetails.size());
				List<PaybillGenerationTrnDetails> sublist = lstPaybillGenerationTrnDetails.subList(i, endIndex);
				paybillGenerationTrnBulkSaveRepository.saveAll(sublist);
			}
		} else {
			paybillGenerationTrnBulkSaveRepository.saveAll(lstPaybillGenerationTrnDetails);
		}
		return 1l;
	}

	@Override
	public double findSumContribution(String sevaarthId, String paymentType, Integer monthId, Integer yearId,
			String component) {
		Session currentSession = entityManager.unwrap(Session.class);

		String sql = "SELECT COALESCE(SUM(contribution),0) AS totalContribution, COALESCE(SUM(contribution_empr),0) as totalEmprContribution "
				+ " FROM TRN_DCPS_CONTRIBUTION  WHERE sevaarth_id = :sevaarthId "
				+ " AND FIN_YEAR_ID = :yearId AND MONTH_ID = :monthId " + " AND TYPE_OF_PAYMENT = :paymentType "
				+ "AND REG_STATUS = 0  GROUP BY TYPE_OF_PAYMENT,FIN_YEAR_ID,MONTH_ID";

		Query query = currentSession.createNativeQuery(sql);
		query.setParameter("sevaarthId", sevaarthId);
		query.setParameter("yearId", yearId);
		query.setParameter("monthId", monthId);
		query.setParameter("paymentType", paymentType);

		List<Object[]> result = query.list();
		if (result.size() > 0) {
			if (component.equals("EMPR")) {
				return (double) Float.parseFloat(result.get(0)[1].toString());
			} else {
				return (double) result.get(0)[0];
			}
		}
		return 0.0;

	}

	@Override
	public Optional<MstDcpsContriVoucherDtlEntity> findMstDcpsContriVoucherDtlEntity(
			PaybillGenerationTrnEntity paybillGenerationTrnEntity) {

		Session ghibSession = entityManager.unwrap(Session.class);
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(
				"FROM MstDcpsContriVoucherDtlEntity WHERE ddoCode = :ddoCode AND yearId = :yearId AND monthId = :monthId AND billGroupId = :billGroupId");
		Query<MstDcpsContriVoucherDtlEntity> lQuery = ghibSession.createQuery(lSBQuery.toString(),
				MstDcpsContriVoucherDtlEntity.class);
		lQuery.setParameter("yearId", paybillGenerationTrnEntity.getPaybillYear());
		lQuery.setParameter("monthId", paybillGenerationTrnEntity.getPaybillMonth());
		lQuery.setParameter("ddoCode", paybillGenerationTrnEntity.getDdoCode());
		lQuery.setParameter("billGroupId", paybillGenerationTrnEntity.getSchemeBillgroupId());

		List<MstDcpsContriVoucherDtlEntity> lstMstDcpsContriVoucherDtlEntity = lQuery.getResultList();
		return lstMstDcpsContriVoucherDtlEntity.stream().findFirst();

	}

	@Override
	public void updateMstDcpsContriVoucherDtlEntity(MstDcpsContriVoucherDtlEntity mstDcpsContriVoucherDtlEntity) {
		// TODO Auto-generated method stub
		Session ghibSession = entityManager.unwrap(Session.class);
		ghibSession.update(mstDcpsContriVoucherDtlEntity);
	}

	@Override
	public void updateMstDcpsContriVoucherDtlEntity(DcpsContributionEntity dcpsContributionEntity) {
		// TODO Auto-generated method stub
		Session ghibSession = entityManager.unwrap(Session.class);
		ghibSession.update(dcpsContributionEntity);
	}

	@Override
	public LoanEmployeeDtlsEntity fetchLoanDtls(String sevaarthId, int allowDeducCode) {
		try {
			String HQL = "FROM LoanEmployeeDtlsEntity as  t  where t.sevaarthid = '" + sevaarthId
					+ "' and t.departmentallowdeduccode = " + allowDeducCode + " and t.totalRecoveredInst < loanprininstno";
			return (LoanEmployeeDtlsEntity) entityManager.createQuery(HQL).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
