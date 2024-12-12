package com.mahait.gov.in.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ChangePasswordRepoImpl implements ChangePasswordRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void updateUser(OrgUserMst orgUserMst) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.update(orgUserMst);
	}

}
