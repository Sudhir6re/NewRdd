package com.mahait.gov.in.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgDdoMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DDOScreenRepoImpl implements DDOScreenRepo{
	
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<OrgDdoMst> findDDOByUsername(String ddoCode) {
		// TODO Auto-generated method stub
		String HQL = "FROM OrgDdoMst as t where t.ddoCode = '"+ddoCode+"' ORDER BY t.ddoCode";
		return (List<OrgDdoMst>) manager.createQuery(HQL).getResultList();
	}
	
}
