package com.mahait.gov.in.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class AllowDeduBulkEmpRepoImpl implements AllowDeduBulkEmpRepo {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public int updateAllowDeductBulkEmplComp(
			DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,String ddoCode) {
		

		try {
			Session currentSession = entityManager.unwrap(Session.class);
			 SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
			 Date date1 = new Date();
			 SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); 
				String hql = "insert into employee_allowdeduc_mpg (sevaarth_id,department_code,department_allowdeduc_code,is_active,created_date,employee_id,with_effective_date) "
						+ "values ('"+deptEligibilityForAllowAndDeductModel.getSevaarthId()+"',51,"+deptEligibilityForAllowAndDeductModel.getDepartmentAllowdeducCode()+",'1',current_timestamp,"+deptEligibilityForAllowAndDeductModel.getEmployeeId()+",'"+dt1.format(date1)+"')";
				Query query = currentSession.createNativeQuery(hql);
				query.executeUpdate();
				
				hql = "insert into employee_allowdeduc_mpg_hst (sevaarth_id,department_code,department_allowdeduc_code,is_active,created_date,employee_id,with_effective_date) "
						+ "values ('"+deptEligibilityForAllowAndDeductModel.getSevaarthId()+"',51,"+deptEligibilityForAllowAndDeductModel.getDepartmentAllowdeducCode()+",'1',current_timestamp,"+deptEligibilityForAllowAndDeductModel.getEmployeeId()+",'"+dt1.format(date1)+"');";
				query = currentSession.createNativeQuery(hql);
				query.executeUpdate();

			return  1;
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		return  1;
	}

	@Override
	public void checkComponentAlreadyPresent(
			DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql1 = "delete from employee_allowdeduc_mpg where  department_allowdeduc_code="+deptEligibilityForAllowAndDeductModel.getDepartmentAllowdeducCode()+" and employee_id="+deptEligibilityForAllowAndDeductModel.getEmployeeId()+"  and  sevaarth_id = '"+deptEligibilityForAllowAndDeductModel.getSevaarthId()+"' ";
     	Query query = currentSession.createNativeQuery(hql1);
		 query.executeUpdate();
	}

	@Override
	public List<Object[]> getListEmpBySchemBillGroup(String userName, Long schemeBillGrpId,Integer departmentAllowdeducCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder sb=new StringBuilder();
		              sb.append("SELECT a.sevaarth_id,a.employee_full_name_en, b.designation_name,f.loc_name,a.employee_id,");
		              sb.append("a.pay_commission_code,d.commission_name_en,a.dcps_gpf_flag,a.emp_service_end_date,e.description,COALESCE(allowdeduc.employee_allowdeduc_id, 0) AS allowed_duc_count ");
		              sb.append("FROM EMPLOYEE_MST a INNER JOIN DESIGNATION_MST b ON a.designation_code = b.designation_code  ");
		              sb.append("INNER JOIN ORG_DDO_MST c ON a.ddo_code=c.ddo_code  ");
		              sb.append("INNER JOIN  PAY_COMMISSION_MST d ON a.pay_commission_code=d.pay_commission_code  ");
		              sb.append("INNER JOIN  MST_DCPS_BILL_GROUP e ON e.bill_group_id = a.billgroup_id  ");
		              sb.append("INNER JOIN  CMN_LOCATION_MST f ON cast(f.loc_id as varchar) = c.hod_loc_code  ");
		              sb.append("LEFT JOIN employee_allowdeduc_mpg allowdeduc ON allowdeduc.sevaarth_id = a.sevaarth_id  AND allowdeduc.department_allowdeduc_code ="+departmentAllowdeducCode);
		              sb.append(" WHERE a.billgroup_id is not null  and a.is_active='1' and  a.ddo_code = '"+ userName +"'  and a.billgroup_id="+schemeBillGrpId+" order by a.employee_full_name_en");
		Query query = currentSession.createNativeQuery(sb.toString());
		return query.list();
	}

	@Override
	public List<Object[]> findpaybill(String billNumber, String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select paybill_generation_trn_id,scheme_billgroup_id from paybill_generation_trn where scheme_billgroup_id ="+billNumber+" and is_Active not in (14,8) \r\n" + 
				"		and ddo_code='"+userName+"' ";
		System.out.println("Findpaybill Quer +++++" + HQL);
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> findAllEmployeesByDDOName(String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuilder sb=new StringBuilder();
		              sb.append("SELECT a.sevaarth_id,a.employee_full_name_en, b.designation_name,f.loc_name,a.employee_id,");
		              sb.append("a.pay_commission_code,d.commission_name_en,a.dcps_gpf_flag,a.emp_service_end_date,e.description ");
		              sb.append("FROM EMPLOYEE_MST a INNER JOIN DESIGNATION_MST b ON a.designation_code = b.designation_code ");
		              sb.append("INNER JOIN ORG_DDO_MST c ON a.ddo_code=c.ddo_code ");
		              sb.append("INNER JOIN  PAY_COMMISSION_MST d ON a.pay_commission_code=d.pay_commission_code ");
		              sb.append("INNER JOIN  MST_DCPS_BILL_GROUP e ON e.bill_group_id = a.billgroup_id ");
		              sb.append("INNER JOIN  CMN_LOCATION_MST f ON cast(f.loc_id as varchar) = c.hod_loc_code ");
		              sb.append("LEFT JOIN EMPLOYEE_ALLOWDEDUC_MPG  g ON g.sevaarth_id=a.sevaarth_id ");
//		              /insert into employee_allowdeduc_mpg (sevaarth_id,department_code,department_allowdeduc_code,
		              sb.append("WHERE a.billgroup_id is not null  and a.is_active='1' and  a.ddo_code = '"+ ddoCode +"'  order by a.employee_full_name_en");
		
		Query query = currentSession.createNativeQuery(sb.toString());
		return query.list();
	}
}
