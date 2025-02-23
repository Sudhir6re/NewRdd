package com.mahait.gov.in.repository;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DDOCityCategoryRepoImpl implements DDOCityCategoryRepo {
	@PersistenceContext
	EntityManager entityManager;

	// protected final Log logger = LogFactory.getLog(getClass());
	public String getCityCategoryMappedWithDDo(String ddoCode) {

		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select d.office_city_class from mst_dcps_ddo_office d where d.ddo_code='" + ddoCode + "'";

		Query query = currentSession.createNativeQuery(hql);
		return query.list().get(0).toString();
	}

	public List<Object[]> getEmployeeMappedWithAllowanceDeduction(BigInteger schemeBillGroupId, String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select bb.sevaarth_id,aa.bill_group_id from mst_dcps_bill_group  aa inner join employee_mst bb on aa.bill_group_id = bb.billgroup_id  \r\n" + 
				"    WHERE NOT EXISTS (select 1 from employee_allowdeduc_mpg c where bb.sevaarth_id = c.sevaarth_id) and  "
				+ "aa.bill_group_id = '"+schemeBillGroupId+"' and bb.ddo_code = '"+ddoCode+"' and bb.is_active=1";
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	public List<Object[]> getSevaarthIdMappedWithBill(int schemeBillGroupId, int month, int year, String ddoName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select b.employee_full_name_en,b.sevaarth_id from scheme_billgroup_mpg  a\r\n"
				+ "inner join employee_mst b on a.bill_group_id =  b.billgroup_id and b.ddo_code = '" + ddoName
				+ "' and b.billgroup_id =" + schemeBillGroupId + "  \r\n"
				+ "inner join paybill_generation_trn_details c on b.sevaarth_id = c.sevaarth_id "
				+ "inner join paybill_generation_trn d on c.paybill_generation_trn_id = d.paybill_generation_trn_id where  d.is_active in(5,6) and d.ddo_code='"
				+ ddoName + "' and  d.paybill_month =" + month + "  and d.paybill_year=" + year;
		Query query = currentSession.createNativeQuery(hql);
		// logger.info("query size" + hql);
		return query.list();
	}

	public List<Object[]> getNumberOfPaybillProcessed(int schemeBillGroupId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select b.* from scheme_billgroup_mpg  a\r\n"
				+ "inner join employee_mst b on a.bill_group_id =  b.billgroup_id\r\n"
				+ "inner join paybill_generation_trn_details c on b.sevaarth_id = c.sevaarth_id"
				+ "inner join paybill_generation_trn d on c.paybill_generation_trn_id = d.paybill_generation_trn_id and d.is_active in(8,14)";
		Query query = currentSession.createNativeQuery(hql);
		// logger.info("query size" + hql);
		return query.list();
	}

	@Override
	public List<Object[]> getSevaarthIdMappedWithBillUpdate(int monthName, int yearName, Long schemeBillGroupId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select b.employee_full_name_en,b.sevaarth_id from mst_dcps_bill_group  a\r\n"
				+ "inner join employee_mst b on a.bill_group_id =  b.billgroup_id  \r\n"
				+ "inner join paybill_generation_trn_details c on b.sevaarth_id = c.sevaarth_id "
				+ "inner join paybill_generation_trn d on c.paybill_generation_trn_id = d.paybill_generation_trn_id where  d.is_active not in(8)"
				+ "  and  d.paybill_month =" + monthName + "  and d.paybill_year=" + yearName
				+ " and b.sevaarth_id in (select sevaarth_id from employee_mst where billgroup_id='" + schemeBillGroupId
				+ "') ";
		Query query = currentSession.createNativeQuery(hql);
		// logger.info("query size" + hql);
		return query.list();
	}
}
