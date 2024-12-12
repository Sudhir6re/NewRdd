package com.mahait.gov.in.nps.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class NsdlContriRepoImpl implements NsdlContriRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> getNsdlContriDetails(Integer monthId, Integer yearId, int role_id, String userName) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		// TODO Auto-generated method stub
		String hql = "";
		switch (role_id) {
		case 2:
			hql = " select distinct a.employee_full_name_en as name, a.pran_no,d.file_name,c.sd_emplr_amount,c.sd_emp_amount,c.sd_total_amt\r\n"
					+ "	from employee_mst a left join dcps_details_mst b on b.employee_id=a.employee_id inner join nsdl_sd  c on c.sd_pran_no=a.pran_no  \r\n"
					+ "    inner join nsdl_bh d on d.file_name=c.file_name inner join rlt_zo_ddo_map e on e.zp_ddo_code=a.ddo_code where\r\n"
					+ "    d.month= " + monthId + " and  d.year= " + yearId
					+ " and transaction_id !=''and e.rept_ddo_code ='" + userName + "' ";
			break;
		case 5:
			hql = " select distinct a.employee_full_name_en as name, a.pran_no,d.file_name,c.sd_emplr_amount,c.sd_emp_amount,c.sd_total_amt\r\n"
					+ "	from employee_mst a left join dcps_details_mst b on b.employee_id=a.employee_id inner join nsdl_sd  c on c.sd_pran_no=a.pran_no  \r\n"
					+ "    inner join nsdl_bh d on d.file_name=c.file_name inner join rlt_zo_ddo_map e on e.zp_ddo_code=a.ddo_code where \r\n"
					+ "    d.month= " + monthId + " and  d.year= " + yearId + " and transaction_id !='' ";
			break;
		case 11:
			hql = " select distinct a.employee_full_name_en as name, a.pran_no,d.file_name,c.sd_emplr_amount,c.sd_emp_amount,c.sd_total_amt\r\n"
					+ "	from employee_mst a left join dcps_details_mst b on b.employee_id=a.employee_id inner join nsdl_sd  c on c.sd_pran_no=a.pran_no  \r\n"
					+ "    inner join nsdl_bh d on d.file_name=c.file_name inner join rlt_zo_ddo_map e on e.zp_ddo_code=a.ddo_code where \r\n"
					+ "    d.month= " + monthId + " and  d.year= " + yearId + " and transaction_id !='' ";
			break;
		/*default:
			hql = " select distinct a.employee_full_name_en as name, a.pran_no,d.file_name,c.sd_emplr_amount,c.sd_emp_amount,c.sd_total_amt\r\n"
					+ "	from employee_mst a left join dcps_details_mst b on b.employee_id=a.employee_id inner join nsdl_sd  c on c.sd_pran_no=a.pran_no  \r\n"
					+ "    inner join nsdl_bh d on d.file_name=c.file_name inner join ddo_map_rlt e on e.ddo_code_level1=a.ddo_code where\r\n"
					+ "    d.month= " + monthId + " and  d.year= " + yearId + " and transaction_id !='' ";
*/
		}

		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public List<Object[]> getNsdlContriDetailsDivisionWise(Integer month, Integer year, int role_id, String userName) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		// TODO Auto-generated method stub
		String hql =  "select distinct f.sub_department_name_en,sum(c.sd_emplr_amount) as emprCohtri,sum(c.sd_emp_amount) as empContri,sum(c.sd_total_amt) as total,f.sub_department_code \r\n" + 
				" from employee_mst a inner join nsdl_sd  c on c.sd_pran_no=a.pran_no \r\n" + 
				" inner join nsdl_bh d on d.file_name=c.file_name inner join rlt_zo_ddo_map e on e.zp_ddo_code=a.ddo_code\r\n" + 
				" inner join sub_department_mst f on a.sub_department_id=f.sub_department_code\r\n" + 
				" where d.month= " +month+ " and  d.year= " +year+ " \r\n" + 
				" and transaction_id !='' group by f.sub_department_name_en,f.sub_department_code";

		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public List<Object[]> getNsdlContriDetailsDeptWise(int deptCode, int month, int year) {
		Session currentSession = entityManager.unwrap(Session.class);
	// TODO Auto-generated method stub
	String hql =  "select distinct a.employee_full_name_en as name, a.pran_no,d.file_name,c.sd_emplr_amount,c.sd_emp_amount,c.sd_total_amt\r\n" + 
			"from employee_mst a left join dcps_details_mst b on b.employee_id=a.employee_id inner join nsdl_sd  c on c.sd_pran_no=a.pran_no\r\n" + 
			"inner join nsdl_bh d on d.file_name=c.file_name inner join rlt_zo_ddo_map e on e.zp_ddo_code=a.ddo_code where \r\n" + 
			"inner join sub_department_mst f on a.sub_department_id=f.sub_department_code where\r\n" + 
			"d.month=" +month+ " and  d.year= " +year+ "\r\n" + 
			"and transaction_id !=''and a.sub_department_id = "+deptCode;
	Query query = currentSession.createNativeQuery(hql);
	return query.list();
}
}
