package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstEmployeeEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ChangeDcpsGpfRepoImpl implements ChangeDcpsGpfRepo {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> getDOJ(String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select doj from employee_mst where sevaarth_id  ='" + sevaarthId + "' ";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public MstEmployeeEntity getemp(String ddoCode) {
		String HQL = "From MstEmployeeEntity as t where t.ddoCode = '"+ddoCode+"'";
		List<MstEmployeeEntity> lst = entityManager.createQuery(HQL).getResultList();
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}

	@Override
	public void savechangeDcpsGpf(MstEmployeeEntity lstprop) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(lstprop);
	}

}
