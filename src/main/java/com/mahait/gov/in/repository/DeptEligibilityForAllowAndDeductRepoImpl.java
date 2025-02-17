package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.EmployeeAllowDeducComponentAmtEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class DeptEligibilityForAllowAndDeductRepoImpl implements DeptEligibilityForAllowAndDeductRepo{
//	protected final Log logger = LogFactory.getLog(getClass());
	
	@PersistenceContext
	EntityManager entityManager;
	
		
//	@Autowired
//	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> findDeptEligibilityForAllowAndDeductList() {
		String HQL = "FROM DeptEligibilityForAllowAndDeductEntity as t ORDER BY t.departmentAllowdeducName ASC";// WHERE t.isActive='1'
		return (List<DeptEligibilityForAllowAndDeductEntity>) entityManager.createQuery(HQL).getResultList();
	}
	

	@Override
	public int saveAllowDeductionMst(DeptEligibilityForAllowAndDeductEntity mstDeptEligibilityForAllowAndDeductEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(mstDeptEligibilityForAllowAndDeductEntity);
		return (Integer) saveId;
		
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> findDeptAllowAndDeductList() {
	/*	String HQL = "FROM DeptEligibilityForAllowAndDeductEntity as t where t.departmentAllowdeducCode in (4,7,13,15,18,24,37,38,41,42,47,50,66,76,78,89,90,100,102,105,108,110,111,112,113,114,115,116,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,\r\n" + 
				"151,152,153,154,155,156,157,158,159,160,162,163,"
				+ "164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,198,199,200,201,202)   ORDER BY t.departmentAllowdeducName ASC"; //changed from departmentAllowdeducId to departmentAllowdeducCode 
		*/
		String HQL = "FROM DeptEligibilityForAllowAndDeductEntity as t where t.isNonComputationComponent=0   ORDER BY t.departmentAllowdeducName ASC"; //changed from departmentAllowdeducId to departmentAllowdeducCode 
		
		return (List<DeptEligibilityForAllowAndDeductEntity>) entityManager.createQuery(HQL).getResultList();
	}

	@Override
	public int deleteMpgDdoAllowDeduc(int action, Object object) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String hql1 = "delete from department_allowdeduc_mpg where ddo_code = '"+object.toString()+"' ";
		
     	Query query = currentSession.createNativeQuery(hql1);

		return query.executeUpdate();
     	
	}

	@Override
	public int saveEmpMpgDdoAllowDeduc(Object object, int action, Object[] serialid, String effectiveDate,
			Object object2) {
		// TODO Auto-generated method stub
		String hql="";
				Session currentSession = entityManager.unwrap(Session.class);
				try {
					 SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
					 Date date = dt.parse(effectiveDate); // *** same for the format String below 
					 SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd"); 
				if(action==1)
				{
					 hql = " insert into department_allowdeduc_mpg (department_allowdeduc_code,is_active,created_date,with_effective_date,ddo_code) values ("+object+","+"1"+",current_timestamp,'"+dt1.format(date)+"','"+object2.toString()+"')";
					Query query = currentSession.createNativeQuery(hql);
					query.executeUpdate();
				//effective_date
					
					hql = " insert into department_allowdeduc_mpg_hst (department_allowdeduc_code,is_active,created_date,with_effective_date,ddo_code) values ("+object+","+"1"+",current_timestamp,'"+dt1.format(date)+"','"+object2.toString()+"')";
					query = currentSession.createNativeQuery(hql);
					query.executeUpdate();
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
				return  1;
	}

	@Override
	public List<Object[]> findallowDeductLevel2(String ddoCode2) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql =  "select  department_allowdeduc_mpg_id,department_allowdeduc_code,ddo_code,with_effective_date from department_allowdeduc_mpg where ddo_code ='"+ddoCode2+"'";
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public List<Object[]> findlevel1DDOAgaintlevel2(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = " select t.ddo_reg_id,t.ddo_code,t.office_name as office_name,t.sub_department_code,t.ddo_code_level_2,t.parent_district_code,"
				+ "t.ddo_name,t.level_hierarchy from ddo_reg_mst t  where t.ddo_code_level_2= '"+userName+"' and t.is_active = '1' order by ddo_code";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	
}

	@Override
	public List<Object[]> getAllowDeductComponentByDDO(String ddoCode) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select * from department_allowdeduc_mpg where ddo_code = '"+ddoCode+"' and is_active = '1'";
	//	String hql = "select department_allowdeduc_code,department_allowdeduc_mpg_id from department_allowdeduc_mpg where ddo_code='"+ddoCode+"' and is_active = '1'";
		System.out.println(hql);
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}


	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> findDeptNonGovDeductList() {
		//String HQL = "FROM DeptEligibilityForAllowAndDeductEntity as t where t.departmentAllowdeducCode in (48,49,50,52,71,74,75)   ORDER BY t.departmentAllowdeducName ASC"; //changed from departmentAllowdeducId to departmentAllowdeducCode
		String HQL = "FROM DeptEligibilityForAllowAndDeductEntity as t where (t.isNonComputationComponent=1 or t.isNonGovernment=1)  ORDER BY t.departmentAllowdeducName ASC"; //changed from departmentAllowdeducId to departmentAllowdeducCode
		return (List<DeptEligibilityForAllowAndDeductEntity>) entityManager.createQuery(HQL).getResultList();
	}


	@Override
	public EmployeeAllowDeducComponentAmtEntity findMstDeptByDeptId(String sevaarthId, int deptallowcode) { // for checking sevaarth id for grp components
	
		try {
		String hql1 = " select e from EmployeeAllowDeducComponentAmtEntity e where sevaarthId='"+sevaarthId+"' and deptallowcode= "+deptallowcode;
		Query query = (Query) entityManager.createQuery(hql1,EmployeeAllowDeducComponentAmtEntity.class);
		return (EmployeeAllowDeducComponentAmtEntity) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	@Override
	public void updateComponent(EmployeeAllowDeducComponentAmtEntity empdata) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(empdata);
	}


	@Override
	public Serializable saveEmployeeNonGovDuesDeduct(EmployeeAllowDeducComponentAmtEntity empAllDedCompEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId=0;
		saveId =(Serializable) currentSession.save(empAllDedCompEntity);
		return (Integer) saveId;
	}


	@Override
	public List<Object[]> getEmployeeAgainstId(int allowDeducComponentId, String ddoCode, String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql=null;
		if(sevaarthId.equals("1"))
		{
			hql = "select  a.employee_full_name_en,a.sevaarth_id,c.is_type,case when a.pay_commission_code = 8  then a.seven_pc_basic else a.basic_pay end as basic_pay,a.employee_id,b.department_allowdeduc_code,a.field_department_code," + 
					"case when d.net_amt <> 0  then d.net_amt else 0 end as net_amt, case when d.existing_amt <> 0  then d.existing_amt else 0 end as existing_amt ,case when d.net_amt <> 0  then d.net_amt else 0 end\r\n" + 
					"from employee_mst a\r\n" + 
					"inner join employee_allowdeduc_mpg b on a.sevaarth_id = b.sevaarth_id  \r\n" + 
					"left join department_allowdeduc_mst c on b.department_allowdeduc_code = c.department_allowdeduc_code\r\n" + 
					"left join employee_allowdeduc_component_amt d on a.sevaarth_id = d.sevaarth_id  and  d.department_allowdeduc_code = "+allowDeducComponentId+" \r\n" + 
					"where a.ddo_code = '"+ddoCode.trim()+"' and  b.department_allowdeduc_code = '"+allowDeducComponentId+"' and a.is_active='1'";
		}
		else {
			
	
		 //hql =  "select DISTINCT c.employee_full_name_en,c.sevaarth_id,a.is_type,c.basic_pay,c.employee_id,b.department_allowdeduc_code,c.field_department_code from department_allowdeduc_mst a inner join employee_allowdeduc_mpg b on a.department_allowdeduc_code = b.department_allowdeduc_code inner join employee_mst c on b.sevaarth_id = c.sevaarth_id where c.ddo_code = '"+ddoCode+"' and a.department_allowdeduc_code = "+allowDeducComponentId+" and c.sevaarth_id = '"+sevaarthId+"'"; //changed from department_allowdeduc_Id to department_allowdeduc_code 
			hql = "select  a.employee_full_name_en,a.sevaarth_id,c.is_type,case when a.pay_commission_code = 8  then a.seven_pc_basic else a.basic_pay end as basic_pay,a.employee_id,b.department_allowdeduc_code,a.field_department_code," + 
					"case when d.net_amt <> 0  then d.net_amt else 0 end as net_amt, case when d.existing_amt <> 0  then d.existing_amt else 0 end as existing_amt ,case when d.net_amt <> 0  then d.net_amt else 0 end\r\n" + 
					"from employee_mst a\r\n" + 
					"inner join employee_allowdeduc_mpg b on a.sevaarth_id = b.sevaarth_id  \r\n" + 
					"left join department_allowdeduc_mst c on b.department_allowdeduc_code = c.department_allowdeduc_code\r\n" + 
					"left join employee_allowdeduc_component_amt d on a.sevaarth_id = d.sevaarth_id  and  d.department_allowdeduc_code = "+allowDeducComponentId+" \r\n" + 
					"where a.ddo_code = '"+ddoCode.trim()+"' and  b.department_allowdeduc_code = "+allowDeducComponentId+" and a.sevaarth_id = '"+sevaarthId+"' and a.is_active='1'"; 
		}
		System.out.println("query >>>>"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}


	@Override
	public  List<DeptEligibilityForAllowAndDeductEntity> findAll() {
		   Session currentSession = entityManager.unwrap(Session.class);
	        
	        // Define the HQL query
	        String hql = "FROM DeptEligibilityForAllowAndDeductEntity";
	        
	        // Execute the HQL query and get the result list
	        List<DeptEligibilityForAllowAndDeductEntity> results = currentSession.createQuery(hql, DeptEligibilityForAllowAndDeductEntity.class)
	                                                                            .getResultList();
	        return results;
	    }

}
