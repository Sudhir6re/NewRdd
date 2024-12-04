package com.mahait.gov.in.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class LimitLoginRepoImpl implements LimitLoginRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void updateFailedAttempts(OrgUserMst user) {
		Session session = entityManager.unwrap(Session.class);
		session.update(user);
	}

	@Override
	public OrgUserMst findUserbyUsername(String userName) {
		String sql = "FROM  OrgUserMst e Where e.userName = :userName  ";
		Query query = entityManager.createQuery(sql, OrgUserMst.class);
		query.setParameter("userName", userName);
		return (OrgUserMst) query.getSingleResult();
	}

}
