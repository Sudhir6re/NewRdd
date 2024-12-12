package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstEmployeeEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EmpBasicDtlsReportRepoImpl implements EmpBasicDtlsReportRepo{

	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<MstEmployeeEntity> lstDDOWiseEmp(String ddoCode) {
		String HQL = "FROM MstEmployeeEntity as t  WHERE t.ddoCode='"+ ddoCode+"' ORDER BY t.employeeId ";
		return (List<MstEmployeeEntity>) manager.createQuery(HQL).getResultList();
	}

	@Override
	public List<Object[]> findEmpBasicDtls(Integer yearId, Integer monthId, Long billGroup, String ddoCode, String sevaarthId) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		String HQL = " select a.employee_full_name_en,d.designation_name, case when a.dcps_gpf_flag = 'Y' then a.dcps_no else a.pfdescription " + 
				" end as gpfdcpsno,a.pan_no,B.bank_account_no,f.bank_branch_name from employee_mst a inner join paybill_generation_trn_details b on a.sevaarth_id=b.sevaarth_id " + 
				" inner join paybill_generation_trn c on c.paybill_generation_trn_id=b.paybill_generation_trn_id " + 
				" inner join designation_mst d on d.designation_code=b.desg_code " + 
				" inner join bank_mst e on b.bank_id=e.bank_code " + 
				" inner join bank_branch_mst f on f.bank_branch_code = b.bank_branch_id where " + 
				" c.paybill_month = "+monthId+" and c.paybill_year = "+yearId+" and c.is_active <> 8 and c.scheme_billgroup_id = '"+billGroup+"' and c.ddo_code='"+ddoCode+"' and b.sevaarth_id ='"+sevaarthId+"' ";
		Query query = currentSession.createNativeQuery(HQL);
		System.out.println("HQL:"+HQL);
		return query.list();
	}

}
