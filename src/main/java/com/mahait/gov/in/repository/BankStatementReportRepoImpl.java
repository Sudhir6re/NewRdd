package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class BankStatementReportRepoImpl implements BankStatementReportRepo{

	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Object[]> getAbstractReport(int monthName, int yearName, String ddoCode, String billNo) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select bank_acnt_no,bank_name,bank_branch_name,employee_full_name_en as employee,net_total,bbm.ifsc_code from paybill_generation_trn  bill "
				+ " inner join paybill_generation_trn_details billdtl ON billdtl.paybill_generation_trn_id=bill.paybill_generation_trn_id  inner join employee_mst  emp  on billdtl.sevaarth_id = emp.sevaarth_id "
				+ "  inner join designation_mst as deg on deg.designation_code=emp.designation_code inner join bank_mst b on emp.bank_code=b.bank_code inner join bank_branch_mst bbm on emp.bank_branch_code=bbm.bank_branch_id "
				+ " where bill.paybill_month= "+monthName+" and bill.paybill_year="+yearName+" and bill.ddo_code='"+ddoCode+"' and bill.paybill_generation_trn_id ='"+billNo+"' order by emp.emp_class,deg.designation_code" ;
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

}
