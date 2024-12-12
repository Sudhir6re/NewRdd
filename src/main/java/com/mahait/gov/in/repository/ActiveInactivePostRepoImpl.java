package com.mahait.gov.in.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class ActiveInactivePostRepoImpl implements ActiveInactivePostRepo {

	@PersistenceContext
	EntityManager entityManager;

	public Session getSession() {
		Session session = entityManager.unwrap(Session.class);
		return session;
	}

	@Override
	public List getPostNameForDisplay(String ddoCode) {
		List postNameList = new ArrayList();
		Session hibSession = getSession();
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.post_name, a.post_id,f.employee_full_name_en,c.designation_name,e.PSR_NO, ");
		sb.append(
				" i.DESCRIPTION,b.post_type_lookup_id,d.lookup_name,g.ddo_code,b.activate_flag  from org_post_details_rlt a  ");
		sb.append(" inner join org_post_mst b on a.post_id=b.post_id ");
		sb.append(" inner join designation_mst c on a.dsgn_id = c.designation_id ");
		sb.append(" inner join cmn_lookup_mst d on d.lookup_id=b.post_type_lookup_id  ");
		sb.append(" inner join HR_PAY_POST_PSR_MPG e on e.POST_ID=b.post_id  ");
		sb.append(" left join employee_mst f on f.post_detail_id=a.post_id  ");
		sb.append(" inner join org_ddo_mst g on a.loc_id = cast(g.location_code as bigint)  ");
		sb.append(" inner join cmn_lookup_mst  h on h.lookup_id=b.post_type_lookup_id   ");
		sb.append(" left join mst_dcps_bill_group  i on i.BILL_GROUP_ID=e.bill_no   ");
		sb.append("inner join rlt_zp_ddo_map j on j.zp_ddo_code=f.ddo_code where j.rept_ddo_code='"+ddoCode+"'");
		sb.append(" order by a.CREATED_DATE desc  ");
		
		System.out.println(sb.toString());
		Query query = hibSession.createNativeQuery(sb.toString());
		postNameList = query.getResultList();
		return postNameList;
	}

	@Override
	public OrgPostMst updatePostStatus(Long postId, Long status,OrgUserMst orgUserMst ) {
		Session currentSession = entityManager.unwrap(Session.class);
		OrgPostMst orgPostMst=currentSession.find(OrgPostMst.class, postId);
		
		if(status==1) {
			orgPostMst.setActivateFlag(0l);
		}else {
			orgPostMst.setActivateFlag(1l);
		}
		orgPostMst.setUpdatedBy(orgUserMst);
		orgPostMst.setUpdatedDate(new Timestamp(new Date().getTime()));
		
		return orgPostMst;
	}

	@Override
	public List<Object[]> getddolst() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "select distinct rept_ddo_code,rept_ddo_post_id from rlt_zp_ddo_map ";
					//	+ " AND (d.sevaarth_id NOT IN (SELECT sevaarth_id    FROM employee_increment  WHERE effective_from_date <= '2023-07-01' AND sevaarth_id != ''  AND sevaarth_id != '0') OR d.sevaarth_id IS NULL) and a.emp_service_end_date >= current_date";
		Query query = currentSession.createNativeQuery(hql);
		return query.getResultList();
	}

}





