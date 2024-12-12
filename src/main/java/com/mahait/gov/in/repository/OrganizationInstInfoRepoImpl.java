package com.mahait.gov.in.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.InstituteType;
import com.mahait.gov.in.entity.OrgDdoMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class OrganizationInstInfoRepoImpl implements OrganizationInstInfoRepo {

	@PersistenceContext
	EntityManager manager;
		
/*	@Autowired
	SessionFactory sessionFactory;
*/
	

	@SuppressWarnings("unchecked")
	@Override
	public OrgDdoMst findDDOInfo(String userName) {
		String HQL = "FROM OrgDdoMst as t where t.ddoCode='" + userName + "'";
		List<OrgDdoMst> lst = manager.createQuery(HQL).getResultList();
		if (lst.size() > 0) {
			return lst.get(0);
		} else {
			return null;
		}
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<InstituteType> lstInstType() {
		String HQL = "FROM InstituteType as t ";
		return (List<InstituteType>)manager.createQuery(HQL).getResultList();
		
		
	}

	@Override
	public List<Object[]> getBankBranch(String valueOf) {
		Session hibSession = manager.unwrap(Session.class);
		Query query = hibSession.createNativeQuery(
				"select bank_branch_id,bank_branch_name,bank_branch_code from bank_branch_mst where bank_code="
						+ valueOf);
		List<Object[]> lstbankbranchdata = query.list();
		return lstbankbranchdata;
	}

	@Override
	public int saveorgInstituteInfo(OrgDdoMst objForSave) {
		Session currentSession = manager.unwrap(Session.class);
		Serializable saveId = (Serializable) currentSession.save(objForSave);
		return (Integer) saveId;
	}

	@Override
	public void updateorgInstituteInfo(OrgDdoMst objForSave) {
		Session currentSession = manager.unwrap(Session.class);
		currentSession.update(objForSave);
	}
}
