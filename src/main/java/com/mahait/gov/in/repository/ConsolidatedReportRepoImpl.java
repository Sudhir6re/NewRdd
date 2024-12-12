package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.ConsolidatedReportModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ConsolidatedReportRepoImpl implements ConsolidatedReportRepo{
//	protected final Log logger = LogFactory.getLog(getClass());
	@PersistenceContext
	EntityManager manager;
	public String getOffice(String ddoCode) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select off_name from mst_dcps_ddo_office where ddo_code ='"+ddoCode+"' ");
		
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();

		if (list != null && list.size() > 0)
			rtnStr = list.get(0).toString();

		return rtnStr;
	}
	
	public List getReportHeaderDetails(String bill_no) {
		List list = new ArrayList();

		Session hibSession = manager.unwrap(Session.class);

		StringBuffer query = new StringBuffer();
		
		query.append("select drm.ddo_code ddocode,sm.scheme_name schemename,sm.scheme_code schemecode,pgt.bill_gross_amt grossamount,");
		query.append("pgt.paybill_month paymonth ,pgt.bill_creation_date billcreationdate , ");
		query.append("sm.demand_code demandcode,sm.major_head majorhead,sm.sub_major_head submajorhead,sm.minor_head minorhead,sm.sub_minor_head subminorhead,sm.sub_head subhead");
		query.append(",trsy.treasury_office_code teasurycode,trsy.treasury_office_name teasuryname, pgt.bill_net_amount netamount,pgt.paybill_year,d.designation_name ");
        query.append("from paybill_generation_trn pgt ");
		query.append("inner join scheme_billgroup_mpg sbm on pgt.scheme_billgroup_id = sbm.bill_group_id ");
		query.append("inner join ddo_map_rlt dmr on sbm.ddo_map_id = dmr.ddo_map_id ");
		query.append("inner join ddo_reg_mst drm  on drm.ddo_reg_id = dmr.ddo_code_user_id1 "); 
		query.append("inner join scheme_mst sm on sm.scheme_id = sbm.scheme_id ");
		query.append("inner join bill_group_mst bgm on sbm.bill_group_id = bgm.bill_group_id ");
		query.append("inner join YEAR_MST ym on ym.year_id=pgt.paybill_year ");
		query.append("inner join treasury_office_mst trsy on drm.treasury_code=trsy.treasury_office_code inner join designation_mst d on d.designation_code=drm.designation_code ");
		query.append("where pgt.paybill_generation_trn_id = "+bill_no);
		query.append(" order by pgt.paybill_generation_trn_id ");


		Query sqlQuery = hibSession.createNativeQuery(query.toString());
		list = sqlQuery.list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public int getTotalDeduction(double billno) {
		
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select sum(total_deduction) from paybill_generation_trn_details where paybill_generation_trn_id = "+billno;
		Query query = currentSession.createNativeQuery(HQL);
		List<Double> lstprop = query.list();
		int totalded=0;
		if (!lstprop.isEmpty()) {
            for (Double objLst : lstprop) {
            	totalded=(int)objLst.intValue();
            }
		}
            return totalded ;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ConsolidatedReportModel> findBillDescription(String ddoCode,int month,int year) {
		
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select a.paybill_generation_trn_id,ddd.bill_description,a.paybill_month,a.paybill_year from paybill_generation_trn a inner join \r\n" + 
				"scheme_billgroup_mpg b on a.scheme_billgroup_id = b.bill_group_id \r\n" + 
				"inner join ddo_map_rlt c on b.ddo_map_id = c.ddo_map_id \r\n" + 
				"inner join scheme_mst dd on dd.scheme_id = b.scheme_id \r\n" + 
				"inner join bill_group_mst ddd on b.bill_group_id = ddd.bill_group_id inner join ddo_reg_mst cccc on a.ddo_code = cccc.ddo_code and cccc.ddo_reg_id = c.ddo_code_user_id1  where a.ddo_code = '"+ddoCode+"' and a.is_active <>'8'and a.paybill_month = "+month+" and a.paybill_year = "+year+"  order by paybill_generation_trn_id desc";
		Query query = currentSession.createNativeQuery(HQL);
		List<Object[]> lstprop = query.list();
		List<ConsolidatedReportModel> lstObj = new ArrayList<>();
        if (!lstprop.isEmpty()) {
            for (Object[] objLst : lstprop) {
            	ConsolidatedReportModel obj = new ConsolidatedReportModel();
                obj.setPaybillgenerationtrnid(StringHelperUtils.isNullInt(objLst[0]));
                obj.setBilldescription(StringHelperUtils.isNullString(objLst[1]));
                lstObj.add(obj);
            }
        }
		
		return lstObj;
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllDataForOuternew(String ddocode)
	{
		Session currentSession = manager.unwrap(Session.class);
		String HQL="select\r\n" + 
				" distinct COALESCE(c.department_allowdeduc_col_nm, c.department_allowdeduc_name) allded ,c.is_type,c.department_allowdeduc_code," + 
				" c.head_account_code from org_ddo_mst a inner join rlt_zp_ddo_map d on d.zp_ddo_code=a.ddo_code inner join department_allowdeduc_mpg b " + 
				" on d.zp_ddo_code = b.ddo_code  inner join department_allowdeduc_mst c on  b.department_allowdeduc_code = c.department_allowdeduc_code  " + 
				" where d.rept_ddo_code = '"+ddocode+"'  ";
		Query query = currentSession.createNativeQuery(HQL);
		
		return query.list();
	}
	
	@Override
	public List<Map<String,Object>> getempDetails(String bill_no)
	{
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select c.* from consolidate_paybill_trn c  where c.consolidate_paybill_trn_id="+bill_no;
		
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	@Override
	public List<Object[]> getempDetails(int monthName, int yearName, String ddocode, String billnumber) {

		Session currentSession = manager.unwrap(Session.class);
  	String HQL="select * from paybill_generation_trn  where bill_gross_amt  >0 and ddo_code ='"+ddocode+"' and paybill_year  ="+yearName+" and paybill_month  ="+monthName+" and is_active <>'8' and scheme_billgroup_id   ="+billnumber;
    Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	
	}

	@Override
	public List<ConsolidatedReportModel> findAllSchemeBillGroupByDDOCode(String DDOCode,int roleid) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = null;
		if (roleid == 1) {
			HQL = "select ddo_map_id from ddo_reg_mst a inner join ddo_map_rlt b on a.ddo_reg_id = b.ddo_code_user_id1 and a.ddo_code = b.ddo_code_level1 where a.ddo_code = '"
					+ DDOCode + "'";
		} else {
			HQL = "select ddo_map_id from ddo_reg_mst a inner join ddo_map_rlt b on a.ddo_reg_id= b.ddo_code_user_id2 and a.ddo_code = b.ddo_code_level2 where a.ddo_code = '"
					+ DDOCode + "'";
		}
		Query query = currentSession.createNativeQuery(HQL);
		List<Long> lstIds = query.getResultList();
		if (query.getResultList().size() > 0) {
			String HQL11 = "FROM MpgSchemeBillGroupEntity as t where t.isActive = '1' and t.ddoMapId IN (:ids) ORDER BY t.billDescription ";
			Query q1 = (Query) manager.createQuery(HQL11);
			q1.setParameterList("ids", lstIds);
			return (List<ConsolidatedReportModel>) q1.getResultList();
		}

		return null;
	}

	@Override
	public List getReportHeaderCOnsolidateDetails(String consolidateId) {

		List list = new ArrayList();

		Session hibSession = manager.unwrap(Session.class);

		StringBuffer query = new StringBuffer();
		
		query.append("  select	 cpt.ddo_code ddocode,sm.scheme_name schemename,sm.scheme_code schemecode,cpt.gross_amt grossamount, ");
		query.append("	cpt.paybill_month paymonth ,cpt.created_date billcreationdate ,  ");
		query.append("	sm.demand_code demandcode,sm.major_head majorhead,sm.sub_major_head submajorhead,sm.minor_head minorhead,sm.sub_minor_head subminorhead, ");
		query.append("	sm.sub_head subhead,cpt.net_amt netamount,cpt.paybill_year   ");
	    query.append(" from consolidate_paybill_trn cpt inner join mst_scheme sm on cpt.scheme_code = sm.scheme_code  ");
	    query.append("   inner join org_ddo_mst drm  on drm.ddo_code = cpt.ddo_code ");
	    query.append(" inner join YEAR_MST ym on ym.year_id=cpt.paybill_year    ");
	    query.append("    where cpt.consolidate_paybill_trn_id = " +consolidateId); 

       
		Query sqlQuery = hibSession.createNativeQuery(query.toString());
		list = sqlQuery.list();
		
		return list;
	
	}

	@Override
	public List<Object[]> findbillBillNumber(Long consolidatePayBillTrnId) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL="select * from consolidate_paybill_trn where consolidate_paybill_trn_id = '"+consolidatePayBillTrnId+"' ";
		Query query = currentSession.createNativeQuery(HQL);
		
		return query.list();
	}

	@Override
	public Date findbillCreateConsolidateDate(Long consolidatePayBillTrnId) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		Date rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select created_date from consolidate_paybill_trn where consolidate_paybill_trn_id='"+consolidatePayBillTrnId+"'");

		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (Date) list.get(0);
		return rtnStr;
	}
	

	
	
}

