package com.mahait.gov.in.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ForgotPasswordRepoImpl implements ForgotPasswordRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findUserByEmail(String emailId) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT user_id, user_name FROM MST_DCPS_DDO_OFFICE a ")
				.append(" INNER JOIN ORG_USER_MST b ON a.ddo_code = b.ddo_code WHERE a.email = :email ")
				.append(" UNION SELECT b.user_id, b.user_name FROM employee_mst a  ")
				.append(" INNER JOIN org_user_mst b ON a.sevaarth_id = b.ddo_code ")
				.append(" WHERE a.email_id = :email ");
		Query stQuery = session.createNativeQuery(sb.toString());
		stQuery.setParameter("email", emailId);
		return stQuery.getResultList();
	}

	@Override
	public void updateTokenDetails(OrgUserMst orgUserMst) {
		Session session = entityManager.unwrap(Session.class);
		session.update(orgUserMst);
	}

	@Override
	public OrgUserMst findUserObjectByUserName(String username) {
		try {
			String sql = "Select e from " + OrgUserMst.class.getName()
					+ " e  Where e.userName = :userName  AND e.activateFlag=1";
			Query query = (Query) entityManager.createQuery(sql, OrgUserMst.class);
			query.setParameter("userName", username);
			return (OrgUserMst) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public OrgUserMst checkTokenIsValid(String token) {
		try {
			String sql = "SELECT e FROM " + OrgUserMst.class.getName() + " e WHERE e.resetToken = :token";
			Query query = (Query) entityManager.createQuery(sql, OrgUserMst.class);
			query.setParameter("token", token);
			OrgUserMst user = (OrgUserMst) query.getSingleResult();
			if (user != null && user.getResetTokenExpiry().isAfter(LocalDateTime.now())) {
				return user;
			}
			return null;
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void updatePassword(OrgUserMst orgUserMst) {
		Session session = entityManager.unwrap(Session.class);
		orgUserMst.setResetToken(null);
		orgUserMst.setResetTokenExpiry(null); // Clear expiry
		session.update(orgUserMst);
	}

}
