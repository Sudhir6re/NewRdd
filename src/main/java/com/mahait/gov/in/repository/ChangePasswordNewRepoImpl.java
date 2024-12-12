package com.mahait.gov.in.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ChangePasswordNewRepoImpl implements changePasswordNewRepo  {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void updatePassword(OrgUserMst orgUserMst) {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		session.merge(orgUserMst);
	}

}
