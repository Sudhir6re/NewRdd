package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class UserInfoRepoImpl implements UserInfoRepo {

	@PersistenceContext
	private EntityManager entityManager;
	
//	private final Logger logger=LoggerFactory.getLogger(getClass());

	public OrgUserMst getActiveUser(String userName,int appCode) {
		try {
			
			String sql = "Select e from " + OrgUserMst.class.getName() + " e " //
					+ " Where e.userName = :userName and e.appCode ="+appCode+" AND e.activateFlag=1";
			
			Query query = entityManager.createQuery(sql, OrgUserMst.class);
			/* Query query = entityManager.createQuery(sql); */
			query.setParameter("userName", userName);

			return (OrgUserMst) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveUserInfo(OrgUserMst objuserInfo) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.save(objuserInfo);
	}

	@Override
	public MstRoleEntity getRoleByRoleId(int role_id) {
		try {
			String sql = "Select e from " + MstRoleEntity.class.getName() + " e  " + "  where  e.roleId = " + role_id;

			Query query = entityManager.createQuery(sql, MstRoleEntity.class);
//			logger.info("Queryyyyy   " + query);
			return (MstRoleEntity) query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	@Override
	public OrgUserMst getUserIdbyUserName(String userName) {
		String sql = "Select e from " + OrgUserMst.class.getName() + " e " //
				+ " Where e.userName = :userName  ";

			Query query = entityManager.createQuery(sql, OrgUserMst.class);
			 /*Query query = entityManager.createQuery(sql); */
			 query.setParameter("userName", userName);
			 /*query.setParameter("appCode", appCode);*/

			return (OrgUserMst) query.getSingleResult();
		
	}

	@Override
	public OrgUserMst findUser(String userName, String domain) {
		
		String sql = "Select e from " + OrgUserMst.class.getName() + " e " //
				+ " Where e.userName = :userName  ";

			Query query = entityManager.createQuery(sql, OrgUserMst.class);
			 /*Query query = entityManager.createQuery(sql); */
			 query.setParameter("userName", userName);
			 /*query.setParameter("appCode", appCode);*/

			return (OrgUserMst) query.getSingleResult();
		
	
	}
	
	public String getNameAndDesignation(int user_id) {

		Session currentSession = entityManager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select e.employee_full_name_en || ' | ' || d.designation_name from user_mst u, employee_mst e, designation_mst d where  u.employee_id = e.employee_id  and e.designation_code=d.designation_code and u.user_id ="+user_id);
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.getResultList();

		if (list != null && list.size() > 0)
			rtnStr = list.get(0).toString();

		return rtnStr;
	
	}

	

	@Override
	public OrgUserMst getUserByUserId(Long updatedUserId) {
		Session currentSession = entityManager.unwrap(Session.class);
		return currentSession.find(OrgUserMst.class, updatedUserId);
	}

	@Override
	public OrgUserMst createDdoAst(OrgUserMst orgUserMst) {
		Session currentSession = entityManager.unwrap(Session.class);
	    return 	(OrgUserMst) currentSession.save(orgUserMst);
	}

	
	

	

}
