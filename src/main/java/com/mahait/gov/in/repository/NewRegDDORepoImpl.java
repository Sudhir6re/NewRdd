package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class NewRegDDORepoImpl implements NewRegDDORepo {
	
	@PersistenceContext
	EntityManager entityManager;


	@Override
	public List<Object[]> findAllEmployee(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select employee_id,sevaarth_id,employee_full_name_en,district_code,taluka_code,city_class from employee_mst where ddo_code = '"+userName+"' and is_active = '1'";
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}


/*	@Override
	public MstEmployeeEntity findEmpData(Integer employeeId) {
		System.out.println("----------Inside findEMpdata------------");
		
		MstEmployeeEntity objDept = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objDept = currentSession.get(MstEmployeeEntity.class, employeeId);
		return objDept;
	}


	@Override
	public Serializable updateheadOfAccCodeHistory(HeadOfAccCodeHistory headOfAccCodeHistory) {

		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId=0;
		saveId =currentSession.save(headOfAccCodeHistory);
		return (Integer) saveId;
	}


	@Override
	public Serializable headofAccCode(MstEmployeeEntity mstEmployeeEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId=1;
		currentSession.update(mstEmployeeEntity);
		return (Integer) saveId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MstCityClassEntity> findCityClasssLst() {
		String HQL = "FROM MstCityClassEntity t  where t.isActive='1' order by t.cityId asc";
		return (List<MstCityClassEntity>) entityManager.createQuery(HQL).getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MstDistrictEntity> lstGetAllDistrict() {
		// TODO Auto-generated method stub
		String HQL = "FROM MstDistrictEntity t  where t.isActive='1' order by t.districtId asc";
		return (List<MstDistrictEntity>) entityManager.createQuery(HQL).getResultList();
	}

*/
	@Override
	public List<Object[]> getTalukaByDistrictId(int districtId) {
		Session hibSession = entityManager.unwrap(Session.class);
	/*	Query query = hibSession.createNativeQuery(
				"select bank_branch_id,bank_branch_name,bank_branch_code from bank_branch_mst where bank_code="
						+ strbankid);
		List<Object[]> lstbankbranchdata = query.list();
		return lstbankbranchdata;*/
		return null;
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<MstTalukaEntity> lstGetAllTaluka() {
		// TODO Auto-generated method stub
		String HQL = "FROM MstTalukaEntity t  where t.isActive='1' order by t.talukaCode asc";
		return (List<MstTalukaEntity>) entityManager.createQuery(HQL).getResultList();
	}


	@Override
	public Serializable updateCityClass(MstEmployeeEntity mstEmployeeEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId=1;
		currentSession.update(mstEmployeeEntity);
		return (Integer) saveId;
	}*/


	@Override
	public List<Object[]> findLvl1DDOCode(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select b.ddo_code_level1,a.office_name ||'(' || b.ddo_code_level1 ||')' from ddo_reg_mst a inner join ddo_map_rlt b on a.ddo_code=b.ddo_code_level2 where\r\n" + 
				" b.ddo_code_level2 = '"+userName+"'";
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}


	@Override
	public List<Object[]> findEmpLst(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select a.employee_id,a.employee_full_name_en,a.dob,a.gender,a.ddo_code,c.designation_name from employee_mst a inner join ddo_map_rlt b\r\n" + 
				"on a.ddo_code=b.ddo_code_level1 inner join designation_mst c on c.designation_code=a.designation_code where  b.ddo_code_level2 = '"+userName+"'";
		System.out.println("HQL:"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}

}
