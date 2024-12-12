package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.PayslipReportModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class EmpPayslipRepoImpl implements EmpPayslipRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> isPaybillGenerated(Long billNumber, int monthName, int yearName, OrgUserMst messages) {
		Session currentSession = entityManager.unwrap(Session.class);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT  a.paybill_generation_trn_id,a.is_active FROM paybill_generation_trn a ").append(
				"INNER JOIN paybill_generation_trn_details b ON a.paybill_generation_trn_id = b.paybill_generation_trn_id ")
				.append(" INNER JOIN employee_mst c ON c.employee_id = b.emp_id ")
				.append(" INNER JOIN mst_dcps_bill_group d ON a.scheme_billgroup_id = d.bill_group_id ")
				.append(" WHERE a.paybill_month = :monthName ").append("AND a.paybill_year = :yearName ")
				.append(" AND a.is_active = 14 ") // Assuming '1' is the intended value for active
				.append(" AND c.billgroup_id = :billNumber");

		String hql = stringBuilder.toString();
		Query query = currentSession.createNativeQuery(hql);
		query.setParameter("monthName", monthName);
		query.setParameter("yearName", yearName);
		query.setParameter("billNumber", billNumber);
		return query.getResultList();
	}

	@Override
	public List<Object[]> getEmployeeData1(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			OrgUserMst orgUserMst) {
		Session currentSession = entityManager.unwrap(Session.class);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append("SELECT a.sevaarth_id, employee_full_name_en, designation_name, doj, dob, mobile_no1, pan_no, ")
				.append("a.pay_commission_code, uid_no, a.ifsc_code, bank_acnt_no, a.ddo_code, ")
				.append("b.gross_amt, b.payslip_total_deduction, b.payslip_net, ")
				.append("CASE WHEN a.dcps_gpf_flag = 'Y' THEN a.dcps_no ELSE a.pfdescription END, ")
				.append("f.description, a.super_ann_date, ")
				.append("b.seven_pc_lvl, b.basic_pay, d.voucher_no, d.voucher_date, d.paybill_generation_trn_id ")
				.append("FROM employee_mst a ")
				.append("INNER JOIN paybill_generation_trn_details b ON b.sevaarth_id = a.sevaarth_id ")
				.append("INNER JOIN designation_mst c ON c.designation_code = b.desg_code ")
				.append("INNER JOIN paybill_generation_trn d ON d.paybill_generation_trn_id = b.paybill_generation_trn_id ")
				.append("INNER JOIN mst_dcps_bill_group f ON d.scheme_billgroup_id = f.bill_group_id ")
				.append("WHERE d.paybill_month = :paybillMonth ").append("AND d.paybill_year = :paybillYear ")
				.append("AND scheme_billgroup_id IN (SELECT bill_group_id FROM mst_dcps_bill_group a ")
				.append("LEFT JOIN mst_scheme b ON b.scheme_code = a.scheme_code ")
				.append("WHERE bill_group_id = :schemeBillgroupId) ").append("AND b.sevaarth_id = :sevaarthId ")
				.append("AND d.is_active = 14");

		String hql = stringBuilder.toString();

		Query query = currentSession.createNativeQuery(hql);
		query.setParameter("paybillMonth", paybillMonth);
		query.setParameter("paybillYear", paybillYear);
		query.setParameter("schemeBillgroupId", new BigInteger(schemeBillgroupId));
		query.setParameter("sevaarthId", orgUserMst.getUserName());

		return query.getResultList();
	}

	@Override
	public List<PayslipReportModel> getAllDataForinnernew(String sevaarthId) {

		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				"SELECT DISTINCT COALESCE(d.department_allowdeduc_col_nm, d.department_allowdeduc_name) AS allded, ")
				.append("d.is_type, d.department_allowdeduc_id, '0' AS tempvalue, ' ' AS tempempty, ")
				.append(" department_allowdeduc_seq ").append("FROM employee_mst a ")
				.append(" INNER JOIN employee_allowdeduc_mpg b ON b.sevaarth_id = a.sevaarth_id ")
				.append(" INNER JOIN paybill_generation_trn_details c ON c.sevaarth_id = a.sevaarth_id ")
				.append(" INNER JOIN paybill_generation_trn e ON e.paybill_generation_trn_id = c.paybill_generation_trn_id ")
				.append(" INNER JOIN department_allowdeduc_mst d ON b.department_allowdeduc_code = d.department_allowdeduc_code ")
				.append("WHERE d.is_active = '1' ").append("AND c.sevaarth_id = :sevaarthId ")
				.append(" ORDER BY department_allowdeduc_seq");
		Query query = currentSession.createNativeQuery(stringBuilder.toString());
		query.setParameter("sevaarthId", sevaarthId);
		List<Object[]> lstprop = query.getResultList();

		List<PayslipReportModel> lstObj = new ArrayList<>();
		int i = 1;
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				PayslipReportModel obj = new PayslipReportModel();
				obj.setDeptalldetNm(StringHelperUtils.isNullString(objLst[0]));
				obj.setType(StringHelperUtils.isNullInt(objLst[1]));
				obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[2]));
				obj.setTempvalue(StringHelperUtils.isNullString(objLst[3]));

				if (objLst[3] != null) {
					double yearVal = 0;

					Object obj1 = objLst[3];
					String str = obj1.toString();
					double d = Double.valueOf(str).doubleValue();
					yearVal = d * 12;
					obj.setTempempty(Double.toString(yearVal));
				}
				if (objLst[4] != null) {
					obj.setTempvalue(StringHelperUtils.isNullString(objLst[4]));
				}
				if (objLst[5] != null) {
					obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[5]));
				}
				lstObj.add(obj);
				i++;
			}
		}
		return lstObj;

	}

	@Override
	public List<Map<String, Object>> getempDetails(String schemeBillgroupId, Integer paybillYear, Integer paybillMonth,
			String userName) {
		Session currentSession = entityManager.unwrap(Session.class);

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT a.* ")
				.append("FROM paybill_generation_trn_details a ")
				.append("INNER JOIN paybill_generation_trn b ON a.paybill_generation_trn_id = b.paybill_generation_trn_id ")
				.append("LEFT JOIN mst_dcps_bill_group c ON b.scheme_billgroup_id = c.bill_group_id ")
				.append("WHERE b.paybill_month = :paybillMonth AND b.paybill_year = :paybillYear ")
				.append("AND scheme_billgroup_id IN (SELECT bill_group_id FROM mst_dcps_bill_group a ")
				.append("LEFT JOIN mst_scheme b ON b.scheme_code = a.scheme_code ")
				.append("WHERE bill_group_id = :schemeBillgroupId) AND lower(a.sevaarth_id)=lower(:sevaarthId)")
				.append("AND b.is_active = 14");

		String hql = stringBuilder.toString();

		org.hibernate.query.NativeQuery<Map<String, Object>> hibernateQuery = currentSession.createNativeQuery(hql);
		hibernateQuery.setParameter("paybillMonth", paybillMonth);
		hibernateQuery.setParameter("paybillYear", paybillYear);
		hibernateQuery.setParameter("schemeBillgroupId", new BigInteger(schemeBillgroupId));
		hibernateQuery.setParameter("sevaarthId", userName);
		hibernateQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

		List<Map<String, Object>> resvalue = hibernateQuery.list();
		return resvalue;

	}

	@Override
	public List<Object[]> lstBillDesc(OrgUserMst messages) {
		Session currentSession = entityManager.unwrap(Session.class);

		StringBuilder sqlBuilder = new StringBuilder();

		sqlBuilder.append("SELECT a.bill_group_id, a.description FROM MST_DCPS_BILL_GROUP a ")
				.append(" INNER JOIN EMPLOYEE_MST b ON a.bill_group_id = b.BILLGROUP_ID ")
				.append(" WHERE a.ddo_code=b.ddo_code AND  lower(b.sevaarth_id)=lower(:sevaarthId)")
				.append(" GROUP BY a.bill_group_id, a.description ").append(" ORDER BY a.description DESC");

		String sql = sqlBuilder.toString();

		Query query = currentSession.createNativeQuery(sql).setParameter("sevaarthId", messages.getUserName());

		List<Object[]> lstprop = query.getResultList();
		return lstprop; 
	}
}
