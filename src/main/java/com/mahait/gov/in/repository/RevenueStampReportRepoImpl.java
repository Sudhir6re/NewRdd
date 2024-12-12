package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RevenueStampReportRepoImpl implements RevenueStampReportRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findRevenueStampEmpDtls(int monthName, int yearName, String ddoCode, Long billNumber) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select  '('|| emp.sevaarth_id ||')'|| employee_full_name_en ||' ('|| designation_name ||')' as employee,pan_no,gross_amt,revenue_stamp "
				+ " from paybill_generation_trn  bill inner join paybill_generation_trn_details billdtl"
				+ " ON billdtl.paybill_generation_trn_id=bill.paybill_generation_trn_id  inner join employee_mst  emp  "
				+ " on billdtl.sevaarth_id = emp.sevaarth_id  inner join designation_mst as deg on deg.designation_code=emp.designation_code"
				+ " where bill.paybill_month= " + monthName + " and bill.paybill_year=" + yearName
				+ " and bill.ddo_code='" + ddoCode + "' " + " and bill.paybill_generation_trn_id ='" + billNumber
				+ "' and revenue_stamp>0 ";
		Query query = currentSession.createNamedQuery(HQL);
		System.out.println("-----------" + HQL);
		return query.list();
	}

	@Override
	public List<Object[]> getddoLvl2byddo(String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select rept_ddo_code,b.ddo_name,b.tan_no from rlt_zp_ddo_map a inner join org_ddo_mst b on a.rept_ddo_code=b.ddo_code\r\n"
				+ "where zp_ddo_code = '" + ddoCode + "'";
		Query query = currentSession.createNamedQuery(HQL);
		System.out.println("-----------" + HQL);
		return query.list();
	}

}
