package com.mahait.gov.in.repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository
public class EmplyeeComponentMappingloginRepositoryImpl implements EmplyeeComponentMappingloginRepository{

	
	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> findAllComponentDetails() {
		Session session=entityManager.unwrap(Session.class);
		Query query=session.createQuery("From DeptEligibilityForAllowAndDeductEntity");
		return query.getResultList();
	}


	@Override
	public List<Map<String, Object>> getEmployeeList() {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "WITH LatestEntries AS (\r\n" + 
				"    SELECT\r\n" + 
				"        c.sevaarth_id AS sevaarthid,\r\n" + 
				"        c.employee_id,\r\n" + 
				"        b.*,\r\n" + 
				"        ROW_NUMBER() OVER (PARTITION BY c.sevaarth_id ORDER BY a.created_date DESC) AS rn\r\n" + 
				"    FROM\r\n" + 
				"        paybill_generation_trn a\r\n" + 
				"        INNER JOIN paybill_generation_trn_details b ON a.paybill_generation_trn_id = b.paybill_generation_trn_id\r\n" + 
				"        INNER JOIN employee_mst c ON b.sevaarth_id = c.sevaarth_id\r\n" + 
				//"        INNER JOIN designation_mst d ON d.designation_code = c.designation_code\r\n" + 
			//	"        LEFT JOIN pay_scale_sixpc_mst e ON e.pay_scale_code = c.pay_scale_code\r\n" + 
				//"        LEFT JOIN payband_gp_state_7pc f ON f.level_id = c.seven_pc_level\r\n" + 
				"        INNER JOIN designation_mst d ON d.designation_code = c.designation_code\r\n" + 
				"        LEFT JOIN pay_scale_sixpc_mst e ON e.pay_scale_code = c.pay_scale_code\r\n" + 
				"        LEFT JOIN payband_gp_state_7pc f ON f.level_id = c.seven_pc_level where  c.sevaarth_id NOT IN (SELECT sevaarth_id FROM employee_allowdeduc_mpg1) \r\n" + 
				")\r\n" + 
				"SELECT *\r\n" + 
				"FROM LatestEntries\r\n" + 
				"WHERE rn = 1;\r\n" ;
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}


	
	@Override
	public void mapComonent(Long empId, String sevaarthId, Integer deptAllowDeducCode) {
			
			Session currentSession = entityManager.unwrap(Session.class);
			 SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
			 Date date1 =new Date();// dt.parse(effectiveDate); // *** same for the format String below 
			 SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); 
				String hql = "insert into employee_allowdeduc_mpg1 (sevaarth_id,department_allowdeduc_code,is_active,created_date,employee_id,with_effective_date) values ('"+sevaarthId+"',"+deptAllowDeducCode+","+"1"+",current_timestamp,'"+empId+"','"+dt1.format(date1)+"')";
				Query query = currentSession.createNativeQuery(hql);
				query.executeUpdate();
				
				/*hql = "insert into employee_allowdeduc_mpg_hst (sevaarth_id,department_allowdeduc_code,is_active,created_date,employee_id,with_effective_date) values ('"+sevaarthId+"',"+deptAllowDeducCode+","+"1"+",current_timestamp,'"+empId+"','"+dt1.format(date1)+"')";
				query = currentSession.createNativeQuery(hql);
				query.executeUpdate();*/

	}
	
	
	

}
