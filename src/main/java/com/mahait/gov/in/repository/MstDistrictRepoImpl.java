package com.mahait.gov.in.repository;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.CmnDistrictMst;
import com.mahait.gov.in.model.MstStateModel;
import com.sun.jdi.LongType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MstDistrictRepoImpl implements MstDistrictRepo{
	
//	protected final Log logger = LogFactory.getLog(getClass());
	
	@PersistenceContext
	EntityManager manager;
	
	/*@Autowired
	SessionFactory sessionFactory;*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CmnDistrictMst> findAllDistrict() {
		String HQL = "FROM CmnDistrictMst as t where t.langId = '1'and t.stateId = 15";
		//logger.info("HQL"+HQL);
		return (List<CmnDistrictMst>) manager.createQuery(HQL).getResultList();
	
	}
	
	//Added for state list
	@SuppressWarnings("unchecked")
	@Override
	public List<MstStateModel> fetchStateByCountry(int countryId) {
		Session currentSession = manager.unwrap(Session.class);
/*		String HQL = "select sm.state_name_en as statename from STATE_MST sm inner join country_mst cm on cm.country_code = sm.country_code where sm.country_code = "+countryId+" ";*/
		/*String HQL = "FROM MstStateEntity as t where t.isActive = '1' and t.CountryMasterEntity.countryCode = "+countryId;
		logger.info("Query is >>> "+HQL);
		Query query = currentSession.createQuery(HQL);
		//logger.info("HQL"+HQL);
		return (List<MstStateEntity>) manager.createQuery(HQL).getResultList();*/
		
		String HQL = "SELECT new com.mahait.gov.in.model.MstStateModel(e.id, e.stateNameEn, e.stateNameMr, d.countryCode, d.countryNameEn, d.countryNameMr, e.stateCode, e.isActive) FROM MstStateEntity e INNER JOIN e.mstCountryEntity d where d.countryCode ="+countryId;
		return (List<MstStateModel>) manager.createQuery(HQL).getResultList();
	
	}

	@Override
	public List<Long> validateDistrictName(String districtname) {
		Session currentSession =  manager.unwrap(Session.class);

		String hql = "select count(*) as count from district_mst where district_name_en ='"+ districtname+"'";
		
		Query query = currentSession.createNativeQuery(hql).addScalar("count", LongType.class);
		
		List<Long> lstresult = query.list();
		return lstresult;
	}
}

	

