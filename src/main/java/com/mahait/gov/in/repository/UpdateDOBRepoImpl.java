package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstEmployeeEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class UpdateDOBRepoImpl implements UpdateDOBRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findAllEmployee(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select a.employee_id,a.sevaarth_id, a.employee_full_name_en,a.dob,b.designation_name from employee_mst\r\n"
				+ "a inner join designation_mst b on b.designation_code=a.designation_code where a.is_active = '1'";
		System.out.println("HQL:" + hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}

	@Override
	public MstEmployeeEntity findEmpData(Long employeeId) {
		System.out.println("----------Inside findEMpdata------------");

		MstEmployeeEntity objDept = null;
		Session currentSession = entityManager.unwrap(Session.class);
		objDept = currentSession.get(MstEmployeeEntity.class, employeeId);
		return objDept;
	}

	@Override
	public Serializable saveupdatedob(MstEmployeeEntity mstEmployeeEntity) {
		Session currentSession = entityManager.unwrap(Session.class);
		Serializable saveId = 1;
		currentSession.merge(mstEmployeeEntity);
		return (Integer) saveId;
	}

	@Override
	public List<Object[]> getEmpDobBySevaarthId(String sevaarthId) {
		Session session = entityManager.unwrap(Session.class);
		String queryString = " select a.sevaarth_id,b.designation_name,a.dob,a.employee_full_name_en,a.employee_id from employee_mst a inner join designation_mst b on a.designation_code=b.designation_code "
				+ " inner join org_ddo_mst c on c.ddo_code=a.ddo_code where a.sevaarth_id || a.employee_full_name_en "
				+ " ilike '%" + sevaarthId + "%'";
		Query query = session.createNativeQuery(queryString);
		return query.getResultList();
	}

}
