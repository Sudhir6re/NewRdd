package com.mahait.gov.in.nps.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.entity.DcpsLegacyDataEntity;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Repository
public class EntryDcpsLegacyRepoImpl  implements EntryDcpsLegacyRepo{
	
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findDcpsEmployeeDetails(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages) {
         Session session=entityManager.unwrap(Session.class);
         StringBuilder stringBuilder=new StringBuilder();
         stringBuilder.append("SELECT a.dcps_no,a.employee_full_name_en,a.sevaarth_id,a.dob,a.super_ann_date,");
         stringBuilder.append("cast(24*(0.12*((BASIC_PAY) + (1.19*BASIC_PAY) + (0.05*(1.19*BASIC_PAY)))) as bigint) as twoYearValue, ");
         stringBuilder.append("cast (12*(0.12*((BASIC_PAY) + (1.19*BASIC_PAY) + (0.05*(1.19*BASIC_PAY))))as bigint) as oneYearValue,");
         stringBuilder.append("a.pran_no,a.EMP_SERVICE_END_DATE,a.pran_no as remark,a.ddo_code,a.doj ");
         stringBuilder.append("FROM EMPLOYEE_MST a INNER JOIN RLT_ZP_DDO_MAP b on a.ddo_code=b.zp_ddo_code  ");
         stringBuilder.append("where a.pran_no is not null and  a.pran_no!=''  and a.is_active=1  and a.dcps_gpf_flag='Y'   and b.rept_ddo_code='"+messages.getDdoCode()+"' ");
         stringBuilder.append("and (a.sevaarth_id='"+dcpsLegacyModel.getTxtSevaarthId()+"'  OR a.employee_full_name_en='"+dcpsLegacyModel.getTxtSevaarthId()+"' OR a.dcps_no='"+dcpsLegacyModel.getTxtSevaarthId()+"' )");
		 Query query=session.createNativeQuery(stringBuilder.toString());
		return query.getResultList();
	}

	@Override
	public Long saveDcpsLegacyData(DcpsLegacyDataEntity dcpsLegacyDataEntity) {
         Session session=entityManager.unwrap(Session.class);
		return (Long) session.save(dcpsLegacyDataEntity);
	}

	@Override
	public Integer checkDataExistsForPeriod(OrgUserMst messages, String period, String sevaarthId) {
		Session session=entityManager.unwrap(Session.class);
		String strCount="";
		try{
		StringBuffer sb = new StringBuffer();
		Query lQuery = null;
	
		
		 if(period.equalsIgnoreCase("10001198258")){
			sb.append("select * from dcps_legacy_data  where sevarth_id='"+sevaarthId+"' and period='"+period+"' and contri_start_date between '2011-04-01' "
					+ "and '2021-03-31' and CONTRI_END_DATE <= '2021-03-31'  and status!='0'  ");
			
			lQuery = session.createNativeQuery(sb.toString());
			
			return lQuery.getResultList().size();
						
		} else if(period.equalsIgnoreCase("10001198259")){
			sb.append("select * from dcps_legacy_data  where sevarth_id='"+sevaarthId+"' and period='"+period+"' and contri_start_date between '2021-04-01' "
					+ "and '2022-03-31' and CONTRI_END_DATE <= '2022-03-31'  and status!='0'  ");
			
			lQuery = session.createNativeQuery(sb.toString());
			
			return lQuery.getResultList().size();
						
		} else if(period.equalsIgnoreCase("10001198260")){
			sb.append("select * from dcps_legacy_data  where sevarth_id='"+sevaarthId+"' and period='"+period+"' and contri_start_date between '2022-04-01' "
					+ "and '2023-03-31' and CONTRI_END_DATE <= '2023-03-31'  and status!='0' ");
			
			lQuery = session.createNativeQuery(sb.toString());
			
			return lQuery.getResultList().size();
						
		}else if(period.equalsIgnoreCase("10001198261")){
			sb.append("select * from dcps_legacy_data  where sevarth_id='"+sevaarthId+"' and period='"+period+"' and contri_start_date between '2006-04-01' "
					+ "and '2011-03-31' and CONTRI_END_DATE <= '2011-03-31'  and status!='0'  ");
			
			lQuery = session.createNativeQuery(sb.toString());
			
			return lQuery.getResultList().size();
						
		}
	
		}
		catch (Exception e) {

			e.printStackTrace();
		}
		return 0;
		
	}

	
	
	

}
