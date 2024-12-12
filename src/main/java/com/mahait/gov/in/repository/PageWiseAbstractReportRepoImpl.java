package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.DisplayPageWiseAbstractReportModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class PageWiseAbstractReportRepoImpl implements PageWiseAbstractReportRepo{

	
	@PersistenceContext
	EntityManager manager;
	
	
	@Override
	public List<DisplayPageWiseAbstractReportModel> getAllDataForinnernew(String strddo, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		
		String HQL = "select distinct COALESCE(d.department_allowdeduc_col_nm, d.department_allowdeduc_name) allded, d.is_type,d.department_allowdeduc_id,'0' tempvalue,' ' tempempty,department_allowdeduc_seq "
				+ " from employee_mst a inner join employee_allowdeduc_mpg b ON b.sevaarth_id=a.sevaarth_id inner join paybill_generation_trn_details c ON c.sevaarth_id=a.sevaarth_id "
				+ " inner join department_allowdeduc_mst d ON b.department_allowdeduc_code=d.department_allowdeduc_code where a.ddo_code= '"+strddo+"' and paybill_generation_trn_id  = "+billNumber+"   order by department_allowdeduc_seq";
					//	+ " and  d.department_allowdeduc_col_nm <> 'COP_BANK' and  d.department_allowdeduc_col_nm <> 'COP_Bank' and  d.department_allowdeduc_col_nm <> 'CREDIT_SOC' and  d.department_allowdeduc_col_nm <> 'RECURRING_DEP' and  d.department_allowdeduc_col_nm <> 'RD' and  d.department_allowdeduc_col_nm <> 'LIC'  ";
		// logger.info("page wise departmentt .." +HQL);
		Query query = currentSession.createNativeQuery(HQL);
		List<Object[]> lstprop = query.getResultList();
		List<DisplayPageWiseAbstractReportModel> lstObj = new ArrayList<>();
 if (!lstprop.isEmpty()) {
     for (Object[] objLst : lstprop) {
     	DisplayPageWiseAbstractReportModel obj = new DisplayPageWiseAbstractReportModel();
  obj.setDeptalldetNm(StringHelperUtils.isNullString(objLst[0]));
  obj.setType(StringHelperUtils.isNullInt(objLst[1]));
  obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[2]));
  obj.setTempvalue(StringHelperUtils.isNullString(objLst[3]));
  obj.setTempempty(StringHelperUtils.isNullString(objLst[4]));
  lstObj.add(obj);
     }
 }
		
		return lstObj;
	}

	@Override
	public List<Map<String, Object>> getempDetails(String billNo) {
		Session currentSession = manager.unwrap(Session.class);
		// TODO Auto-generated method stub
	String HQL = " select sum(temp1.basic_pay) as basic_pay," + 
			"        sum(temp1.SPL_PAY)as SPL_PAY, " + 
			"        sum(temp1.PO)as PO, " + 
			"        sum(temp1.D_PAY)as D_PAY, " + 
			"        sum(temp1.DA)as DA, " + 
			"        sum(temp1.HRA)as HRA, " + 
			"        sum(temp1.CLA)as CLA, " + 
			"        sum(temp1.CLA)as MA, " + 
			"        sum(temp1.WA)as WA, " + 
			"        sum(temp1.TRANS_ALL)as TRANS_ALL, " + 
			"        sum(temp1.PAY_RECOVER)as PAY_RECOVER, " + 
			"        sum(temp1.GROSS_AMT) as GROSS_AMT, " + 
			"        sum(temp1.IT)as IT, " + 
			"        sum(temp1.HRR) as HRR, " + 
			"        sum(temp1.PLI)as PLI, " + 
			"        sum(temp1.PT)as PT, " + 
			"        sum(temp1.HBA)as HBA, " + 
			"        sum(temp1.FAN_ADV)as FAN_ADV, " + 
			"        sum(temp1.JEEP_R)as JEEP_R, " + 
			"        sum(temp1.GPF_IV)as GPF_IV, " + 
			"        sum(temp1.net_total)as net_total, " + 
			"        sum(temp1.total_ded)as total_ded, " + 
			"        sum(temp1.PER_PAY)as PER_PAY, " + 
			"        sum(temp1.PE)as PE, " + 
			"        sum(temp1.OTHER_ALLOW)as OTHER_ALLOW, " + 
			"        sum(temp1.BONUS)as BONUS, " + 
			"        sum(temp1.SURCHARGE)as SURCHARGE, " + 
			"        sum(temp1.RENT_B)as RENT_B, " + 
			"        sum(temp1.GPF_ADV)as GPF_ADV, " + 
			"        sum(temp1.MISC)as MISC, " + 
			"        sum(temp1.TRN_COUNTER)as TRN_COUNTER, " + 
			"        sum(temp1.DP_GAZZETED)as DP_GAZZETED, " + 
			"        sum(temp1.GPF_IV_ADV)as GPF_IV_ADV, " + 
			"        sum(temp1.DCPS)as DCPS, " + 
			"        sum(temp1.PSR_NO)as PSR_NO, " + 
			"        sum(temp1.DA_GPF)as DA_GPF, " + 
			"        sum(temp1.DA_GPFIV)as DA_GPFIV, " + 
			"        sum(temp1.OTHER_TRN_CNTR)as OTHER_TRN_CNTR, " + 
			"        sum(temp1.GPAY)as GPAY, " + 
			"        sum(temp1.TECH_ALLOW)as TECH_ALLOW, " + 
			"        sum(temp1.HILLY_ALLOWANCE)as HILLY_ALLOWANCE, " + 
			"        sum(temp1.ATS_INCENTIVE_30)as ATS_INCENTIVE_30, " + 
			"        sum(temp1.ATS_INCENTIVE_50)as ATS_INCENTIVE_50, " + 
			"        sum(temp1.PG_ALLOWANCE)as PG_ALLOWANCE, " + 
			"        sum(temp1.TAA)as TAA, " + 
			"        sum(temp1.FORCE_1_100)as FORCE_1_100, " + 
			"        sum(temp1.FORCE_1_25)as FORCE_1_25, " + 
			"        sum(temp1.ARM_ALLOWANCE)as ARM_ALLOWANCE, " + 
			"        sum(temp1.ARMOURER)as ARMOURER, " + 
			"        sum(temp1.BMI)as BMI, " + 
			"        sum(temp1.CASH_ALLOWANCE)as CASH_ALLOWANCE, " + 
			"        sum(temp1.CID)as CID, " + 
			"        sum(temp1.CONVEYANCE)as CONVEYANCE, " + 
			"        sum(temp1.EMERGENCY_ALLOW)as EMERGENCY_ALLOW, " + 
			"        sum(temp1.ESIS)as ESIS, " + 
			"        sum(temp1.ELA)as ELA, " + 
			"        sum(temp1.FITNESS_ALLOW)as FITNESS_ALLOW, " + 
			"        sum(temp1.GALLANTRY_AWARDS)as GALLANTRY_AWARDS, " + 
			"        sum(temp1.KIT_MAINTENANCE)as KIT_MAINTENANCE, " + 
			"        sum(temp1.LISENCE_FEE)as LISENCE_FEE, " + 
			"        sum(temp1.MECHANICAL_ALLOW)as MECHANICAL_ALLOW, " + 
			"        sum(temp1.MEDICAL_EDUCATION_ALLOW)as MEDICAL_EDUCATION_ALLOW, " + 
			"        sum(temp1.deduc_adj_ag)as deduct_adj_ag, " + 
			"        sum(temp1.deduct_adj_otr)as deduct_adj_otr, " + 
			"        sum(temp1.MESS_ALLOW)as MESS_ALLOW, " + 
			"        sum(temp1.NAXEL_AREA_ALLOW)as NAXEL_AREA_ALLOW, " + 
			"        sum(temp1.NON_PRAC_ALLOW)as NON_PRAC_ALLOW, " + 
			"        sum(coalesce(temp1.SUMPTUARY, " + 
			"        0))as SUMPTUARY, " + 
			"        sum(coalesce(temp1.PROJECT_ALLOW, " + 
			"        0))as PROJECT_ALLOW, " + 
			"        sum(temp1.SDA)as SDA, " + 
			"        sum(coalesce(temp1.ADD_PAY, " + 
			"        0))as ADD_PAY, " + 
			"        sum(coalesce(temp1.UNIFORM_ALLOW, " + 
			"        0))as UNIFORM_ALLOW, " + 
			"        sum(temp1.FAMILY_PALNNING)as FAMILY_PALNNING, " + 
			"        sum(temp1.GIS)as GIS, " + 
			"        sum(coalesce(temp1.CENTRAL_GIS, " + 
			"        0))as CENTRAL_GIS, " + 
			"        sum(temp1.GIS_IFS)as GIS_IFS, " + 
			"        sum(temp1.GIS_IAS)as GIS_IAS, " + 
			"        sum(temp1.GIS_IPS)as GIS_IPS, " + 
			"        sum(temp1.GPF_IAS_OTHER)as GPF_IAS_OTHER, " + 
			"        sum(temp1.GPF_IAS)as GPF_IAS, " + 
			"        sum(temp1.GPF_IPS)as GPF_IPS, " + 
			"        sum(temp1.GPF_IFS)as GPF_IFS, " + 
			"        sum(temp1.GPF_GRP_ABC)as GPF_GRP_ABC, " + 
			"        sum(temp1.GPF_GRP_D)as GPF_GRP_D, " + 
			"        sum(temp1.SERVICE_CHARGE)as SERVICE_CHARGE, " + 
			"        sum(temp1.OTHER_DEDUCTION)as OTHER_DEDUCTION, " + 
			"        sum(temp1.MAHA_STATE_LIFE_INSURANCE)as MAHA_STATE_LIFE_INSURANCE, " + 
			"        sum(temp1.LTC)as LTC, " + 
			"        sum(temp1.HBA_CONSTRUCTION)as HBA_CONSTRUCTION, " + 
			"        sum(temp1.HBA_LAND)as HBA_LAND, " + 
			"        sum(temp1.PAY_ADVANCE)as PAY_ADVANCE, " + 
			"        sum(temp1.FESTIVAL_ADVANCE)as FESTIVAL_ADVANCE, " + 
			"        sum(temp1.TRAVEL_ADVANCE) as TRAVEL_ADVANCE, " + 
			"        sum(temp1.GPF_ADV_GRP_ABC) as GPF_ADV_GRP_ABC, " + 
			"        sum(temp1.GPF_ADV_GRP_D) as GPF_ADV_GRP_D, " + 
			"        sum(temp1.MOTOR_CYCLE_ADV) as MOTOR_CYCLE_ADV, " + 
			"        sum(temp1.OTHER_VEH_ADV) as OTHER_VEH_ADV, " + 
			"        sum(temp1.COMPUTER_ADV) as COMPUTER_ADV, " + 
			"        sum(temp1.HBA_CONSTRUCTION_INT) as HBA_CONSTRUCTION_INT, " + 
			"        sum(temp1.HBA_LAND_INT) as HBA_LAND_INT, " + 
			"        sum(temp1.PAY_ADVANCE_INT) as PAY_ADVANCE_INT, " + 
			"        sum(temp1.TRAVEL_ADVANCE_INT) as TRAVEL_ADVANCE_INT, " + 
			"        sum(temp1.GPF_ADV_GRP_ABC_INT) as GPF_ADV_GRP_ABC_INT, " + 
			"        sum(temp1.GPF_ADV_GRP_D_INT) as GPF_ADV_GRP_D_INT, " + 
			"        sum(temp1.MOTOR_CYCLE_ADV_INT) as MOTOR_CYCLE_ADV_INT, " + 
			"        sum(temp1.OTHER_VEH_ADV_INT) as OTHER_VEH_ADV_INT, " + 
			"        sum(temp1.COMPUTER_ADV_INT) as COMPUTER_ADV_INT, " + 
			"        sum(temp1.GIS_ZP) as GIS_ZP, " + 
			"        sum(temp1.GPF_ABC_ARR_MR) as GPF_ABC_ARR_MR, " + 
			"        sum(temp1.GPF_D_ARR_MR) as GPF_D_ARR_MR, " + 
			"        sum(temp1.GPF_IAS_ARR_MR) as GPF_IAS_ARR_MR, " + 
			"        sum(temp1.GPF_IFS_ARR_MR) as GPF_IFS_ARR_MR, " + 
			"        sum(temp1.GPF_IPS_ARR_MR) as GPF_IPS_ARR_MR, " + 
			"        sum(temp1.HRR_ARR) as HRR_ARR, " + 
			"        sum(temp1.JANJULGISARR) as JANJULGISARR, " + 
			"        sum(temp1.OTHER_REC) as OTHER_REC, " + 
			"        sum(temp1.PT_ARR) as PT_ARR, " + 
			"        sum(temp1.OTHER_DED_TRY) as OTHER_DED_TRY, " + 
			"        sum(temp1.OTHER_ADV_INT) as OTHER_ADV_INT, " + 
			"        sum(temp1.MCA_LAND_INT) as MCA_LAND_INT, " + 
			"        sum(temp1.MCA_LAND) as MCA_LAND, " + 
			"        sum(temp1.ADD_DA) as ADD_DA, " + 
			"        sum(temp1.ADD_HRA) as ADD_HRA, " + 
			"        sum(temp1.DA_ARR) as DA_ARR, " + 
			"        sum(temp1.TEMP_CLA_5THPAY) as TEMP_CLA_5THPAY, " + 
			"        sum(temp1.FRANKING_ALLOW) as FRANKING_ALLOW, " + 
			"        sum(temp1.TEMP_HRA_5THPAY) as TEMP_HRA_5THPAY, " + 
			"        sum(temp1.LEAVE_TRAVEL_ASSISTANCE) as LEAVE_TRAVEL_ASSISTANCE, " + 
			"        sum(temp1.MEDICAL_STUDY_ALLOW) as MEDICAL_STUDY_ALLOW, " + 
			"        sum(temp1.OTHER_ALLOWANCES) as OTHER_ALLOWANCES, " + 
			"        sum(temp1.PERMANENT_TRAVELLING) as PERMANENT_TRAVELLING, " + 
			"        sum(temp1.TEMP_TA_5THPAY) as TEMP_TA_5THPAY, " + 
			"        sum(temp1.WASH_ALLOW) as WASH_ALLOW, " + 
			"        sum(temp1.WRITER_PAY_ALLOW) as WRITER_PAY_ALLOW, " + 
			"        sum(temp1.TRIBAL_ALLOW) as TRIBAL_ALLOW, " + 
			"        sum(temp1.CO_HSG_SOC) as CO_HSG_SOC, " + 
			"        sum(temp1.CO_HSG_SOC_INT) as CO_HSG_SOC_INT, " + 
			"        sum(temp1.COMP_AIS) as COMP_AIS, " + 
			"        sum(temp1.COMP_AIS_INT) as COMP_AIS_INT, " + 
			"        sum(temp1.EXC_PAYRC) as EXC_PAYRC, " + 
			"        sum(temp1.GPF_OTHER_STATE) as GPF_OTHER_STATE, " + 
			"        sum(temp1.HBA_AIS) as HBA_AIS, " + 
			"        sum(temp1.HBA_AIS_INT)as HBA_AIS_INT, " + 
			"        sum(temp1.HBA_HOUSE) as HBA_HOUSE, " + 
			"        sum(temp1.HBA_HOUSE_INT) as HBA_HOUSE_INT, " + 
			"        sum(temp1.MCA_AIS) as MCA_AIS, " + 
			"        sum(temp1.MCA_AIS_INT) as MCA_AIS_INT, " + 
			"        sum(temp1.OTHER_ADV) as OTHER_ADV, " + 
			"        sum(temp1.GPF_IAS_LOAN) as GPF_IAS_LOAN, " + 
			"        sum(temp1.DCPS_DELAY) as DCPS_DELAY, " + 
			"        sum(temp1.DCPS_PAY) as DCPS_PAY, " + 
			"        sum(temp1.DCPS_DA) as DCPS_DA, " + 
			"        sum(temp1.REFRESHMENT_ALLOW) as REFRESHMENT_ALLOW, " + 
			"        sum(temp1.JANJULGIS) as JANJULGIS, " + 
			"        sum(temp1.CDA) as CDA, " + 
			"        sum(temp1.CTA) as CTA, " + 
			"        sum(temp1.PEON_ALLOWANCE) as PEON_ALLOWANCE, " + 
			"        sum(temp1.INCENTIVE_BDDS) as INCENTIVE_BDDS, " + 
			"        sum(temp1.RT_PILOT) as RT_PILOT, " + 
			"        sum(temp1.CHPL_PILOT) as CHPL_PILOT, " + 
			"        sum(temp1.KIT_PILOT) as KIT_PILOT, " + 
			"        sum(temp1.FLYING_PAY_PILOT) as FLYING_PAY_PILOT, " + 
			"        sum(temp1.INSTRUCTIONAL_PILOT) as INSTRUCTIONAL_PILOT, " + 
			"        sum(temp1.QUALIFICATION_PILOT) as QUALIFICATION_PILOT, " + 
			"        sum(temp1.INSPECTION_PILOT) as INSPECTION_PILOT, " + 
			"        sum(temp1.FLYING_ALLOW_PILOT) as FLYING_ALLOW_PILOT, " + 
			"        sum(temp1.OUTFIT_PILOT) as OUTFIT_PILOT, " + 
			"        sum(temp1.MILITERY_PILOT) as MILITERY_PILOT, " + 
			"        sum(temp1.SPECIAL_PAY_PILOT) as SPECIAL_PAY_PILOT, " + 
			"        sum(temp1.CPF) as CPF, " + 
			"        sum(temp1.BASIC_ARR) as BASIC_ARR, " + 
			"        sum(temp1.DA_ON_TA) as DA_ON_TA, " + 
			"        sum(temp1.TRANS_ARREAR) as TRANS_ARREAR, " + 
			"        sum(temp1.OVERTIME_ALLOW) as OVERTIME_ALLOW, " + 
			"        sum(temp1.CPF_CONTRIBUTION) as CPF_CONTRIBUTION, " + 
			"        sum(temp1.CPF_EMPLOYEE) as CPF_EMPLOYEE, " + 
			"        sum(temp1.CPF_EMPLOYER) as CPF_EMPLOYER, " + 
			"        sum(temp1.ACC_POLICY) as ACC_POLICY, " + 
			"        sum(temp1.SVNPC_DA) as SVNPC_DA, " + 
			"        sum(temp1.GROSS_NEW) as GROSS_NEW, " + 
			"        sum(temp1.TOTAL_DED_NEW) as TOTAL_DED_NEW, " + 
			"        sum(temp1.GROSS_SAL) as GROSS_SAL, " + 
			"        sum(temp1.SVNPC_TA) as SVNPC_TA, " + 
			"        sum(temp1.SVNPC_GPF_ARR) as SVNPC_GPF_ARR, " + 
			"        sum(temp1.SVNPC_DCPS_ARR) as SVNPC_DCPS_ARR, " + 
			"        sum(temp1.SVNPC_TA_ARR) as SVNPC_TA_ARR, " + 
			"        sum(temp1.SVNPC_GPF_ARR_DEDU) as SVNPC_GPF_ARR_DEDU, " + 
			"        sum(temp1.SVNPC_GPF_RECO) as SVNPC_GPF_RECO, " + 
			"        sum(temp1.SVNPC_DCPS_RECO) as SVNPC_DCPS_RECO, " + 
			"        sum(temp1.NPS_EMPLR) as NPS_EMPLR, " + 
			"        sum(temp1.NPS_EMPLR_CONTRI_DED) as NPS_EMPLR_CONTRI_DED, " + 
			"        sum(temp1.REVENUE_STAMP) as REVENUE_STAMP, " + 
			"        sum(temp1.deduc_adj_ag) as deduc_adj_ag, " + 
			"        temp1.rn  " + 
			"    from " + 
			"        (select " + 
			"            temp.*, " + 
			"            (ROW_NUMBER()  OVER(  " + 
			"        ORDER BY " + 
			"            temp.emp_class)-1)/8+1 as rn  " + 
			"        from " + 
			"            (select " + 
			"                distinct c.sevaarth_id sevaarthid, " + 
			"                c.employee_full_name_en, " + 
			"                c.employee_l_name_en, " + 
			"                d.designation_name, " + 
			"                c.emp_class, " + 
			"                b.*  " + 
			"            from " + 
			"                paybill_generation_trn a  " + 
			"            inner join " + 
			"                paybill_generation_trn_details b  " + 
			"                    on a.paybill_generation_trn_id = b.paybill_generation_trn_id   " + 
			"            inner join " + 
			"                employee_mst c  " + 
			"                    on b.sevaarth_id = c.sevaarth_id  " + 
			"            inner join " + 
			"                designation_mst d  " + 
			"                    on d.designation_code = c.designation_code  " + 
			"            where " + 
			"                a.paybill_generation_trn_id ='"+billNo+"'  " + 
			"            order by " + 
			"                c.emp_class ) as temp  " + 
			"        order by " + 
			"            temp.emp_class ) as temp1  " + 
			"        group by " + 
			"            temp1.rn  " + 
			"        order by " + 
			"            temp1.rn ";
	
	NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
	nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	List<Map<String, Object>> resvalue = nativeQuery.list();
	return resvalue;
	}

}
