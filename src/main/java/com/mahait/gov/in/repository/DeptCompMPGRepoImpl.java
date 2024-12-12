package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class DeptCompMPGRepoImpl implements DeptCompMPGRepo {
	
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


	

}
