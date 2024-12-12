package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;



@Repository
public class LocationMasterRepoImpl implements LocationMasterRepo{

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAllStates(int countryId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		//String hql =  "select a.country_name,b.state_id,b.state_name from country_mst  a inner join state_mst b on a.country_id = b.country_id  where a.is_active = '1' and a.country_id = '"+countryId+"' order by b.state_name asc";
		//String hql =  "select state_id,state_name from cmn_state_mst where state_id = 15 ";
		String hql = "select state_id,state_name from cmn_state_mst where country_id = '1' ";
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}

	@Override
	public List<Object[]> findAllDistricts(long stateId) {
		Session currentSession = entityManager.unwrap(Session.class);
		//String hql =  "select a.country_name,b.state_id,b.state_name,c.district_code,c.district_name from country_mst  a inner join state_mst b on a.country_id = b.country_id and b.is_active = '1' inner join district_mst c on b.state_id = c.state_id and b.country_id = c.country_id and c.is_active = '1'  where a.is_active = '1' and b.state_id = '"+stateId+"' order by c.district_name asc";
		String hql =  "select c.district_id,c.district_name from  cmn_state_mst b inner join cmn_district_mst c on b.state_id = c.state_id  where b.state_id ='"+stateId+"' and c.lang_id = '1'";
		System.out.println("-------------findAllDistricts-----------------"+hql);
		Query query = currentSession.createNativeQuery(hql);
		return query.list();
	}
}