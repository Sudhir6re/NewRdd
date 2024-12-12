package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class HouseRentRecoveryReportRepoImpl implements HouseRentRecoveryReportRepo {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Object[]> findHRRDetails(Integer yearId, Integer monthId, Long billGroup, String ddoCode) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = " select a.sevaarth_id || ' - ' || a.employee_full_name_en || '(' ||d.designation_name || ')' ,a.pan_no,b.gross_amt,b.hrr" + 
				" from employee_mst a inner join paybill_generation_trn_details b" + 
				" on a.sevaarth_id=b.sevaarth_id\r\n" + 
				" inner join paybill_generation_trn c on c.paybill_generation_trn_id=b.paybill_generation_trn_id " + 
				" inner join designation_mst d on d.designation_code=b.desg_code " + 
				" inner join bank_mst e on b.bank_id=e.bank_code " + 
				" inner join bank_branch_mst f on f.bank_branch_code = b.bank_branch_id where " + 
				" c.paybill_month = "+monthId+" and c.paybill_year = "+yearId+" and c.scheme_billgroup_id = '"+billGroup+"' and c.ddo_code='"+ddoCode+"' " + 
				" and b.hrr>0";
		Query query = currentSession.createNativeQuery(HQL);
		System.out.println("HQL:"+HQL);
		return query.list();
	}

}
