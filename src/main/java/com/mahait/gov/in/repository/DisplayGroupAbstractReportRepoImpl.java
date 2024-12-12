package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.DisplayGroupAbstractReportModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DisplayGroupAbstractReportRepoImpl implements DisplayGroupAbstractReportRepo {

//	protected final Log logger = LogFactory.getLog(getClass());
	@PersistenceContext
	EntityManager manager;

	public List getReportHeaderDetails(String bill_no) {
		List list = new ArrayList();
		Session hibSession = manager.unwrap(Session.class);

		StringBuffer query = new StringBuffer();

		query.append(
				"select drm.ddo_code ddocode,sm.scheme_name schemename,sm.scheme_code schemecode,pgt.bill_gross_amt grossamount,");
		query.append("pgt.paybill_month paymonth ,pgt.bill_creation_date billcreationdate , ");
		query.append(
				"sm.demand_code demandcode,sm.major_head majorhead,sm.sub_major_head submajorhead,sm.minor_head minorhead,sm.sub_minor_head subminorhead,sm.sub_head subhead");
		query.append(
				",trsy.treasury_office_code teasurycode,trsy.treasury_office_name teasuryname, pgt.bill_net_amount netamount,pgt.paybill_year ");
		query.append("from paybill_generation_trn pgt ");
		query.append("inner join scheme_billgroup_mpg sbm on pgt.scheme_billgroup_id = sbm.bill_group_id ");
		query.append("inner join ddo_map_rlt dmr on sbm.ddo_map_id = dmr.ddo_map_id ");
		query.append("inner join ddo_reg_mst drm  on drm.ddo_reg_id = dmr.ddo_code_user_id2 ");
		query.append("inner join scheme_mst sm on sm.scheme_id = sbm.scheme_id ");
		query.append("inner join bill_group_mst bgm on sbm.bill_group_id = bgm.bill_group_id ");
		query.append("inner join YEAR_MST ym on ym.year_id=pgt.paybill_year ");
		query.append("inner join treasury_office_mst trsy on drm.district_code=trsy.city_code ");
		query.append("where pgt.paybill_generation_trn_id = " + bill_no);
		query.append(" order by pgt.paybill_generation_trn_id ");

		Query sqlQuery = hibSession.createNativeQuery(query.toString());
		list = sqlQuery.list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getTotalDeductionGroupAbstract(double billno) {

		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select sum(total_ded) from paybill_generation_trn_details where paybill_generation_trn_id = "
				+ billno;
		Query query = currentSession.createNativeQuery(HQL);
		List<Double> lstprop = query.list();
		int totalded = 0;
		if (!lstprop.isEmpty()) {
			for (Double objLst : lstprop) {
				if (objLst != null)
					totalded = (int) objLst.intValue();
			}
		}
		return totalded;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DisplayGroupAbstractReportModel> findBillDescription(String ddoCode, int month, int year) {

		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select a.paybill_generation_trn_id,ddd.bill_description,a.paybill_month,a.paybill_year from paybill_generation_trn a inner join \r\n"
				+ "scheme_billgroup_mpg b on a.scheme_billgroup_id = b.bill_group_id \r\n"
				+ "inner join ddo_map_rlt c on b.ddo_map_id = c.ddo_map_id \r\n"
				+ "inner join scheme_mst dd on dd.scheme_id = b.scheme_id \r\n"
				+ "inner join bill_group_mst ddd on b.bill_group_id = ddd.bill_group_id inner join ddo_reg_mst cccc on a.ddo_code = cccc.ddo_code and cccc.ddo_reg_id = c.ddo_code_user_id1  where a.ddo_code = '"
				+ ddoCode + "' and a.is_active <>'8'and a.paybill_month = " + month + " and a.paybill_year = " + year
				+ "  order by paybill_generation_trn_id desc";
		Query query = currentSession.createNativeQuery(HQL);
		List<Object[]> lstprop = query.list();
		List<DisplayGroupAbstractReportModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				DisplayGroupAbstractReportModel obj = new DisplayGroupAbstractReportModel();
				obj.setPaybillgenerationtrnid(StringHelperUtils.isNullInt(objLst[0]));
				obj.setBilldescription(StringHelperUtils.isNullString(objLst[1]));
				lstObj.add(obj);
			}
		}
		return lstObj;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllDataForAbstractnew(String ddocode, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select distinct COALESCE(d.department_allowdeduc_col_nm, d.department_allowdeduc_name) allded, d.is_type,d.department_allowdeduc_id,b.head_account_code,department_allowdeduc_seq from "
				+ " employee_mst a inner join employee_allowdeduc_mpg b ON b.sevaarth_id=a.sevaarth_id inner join paybill_generation_trn_details c ON c.sevaarth_id=a.sevaarth_id  inner join department_allowdeduc_mst d ON  "
				+ " b.department_allowdeduc_code=d.department_allowdeduc_code where a.ddo_code= '" + ddocode
				+ "' and paybill_generation_trn_id  = '" + billNumber
				+ "' and b.is_active = '1' order by department_allowdeduc_seq ";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> getempDetailsGroupAbstract(String bill_no) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select distinct c.sevaarth_id sevaarthid,c.employee_full_name_en,c.employee_l_name_en,d.designation_name,f.id,b.* from paybill_generation_trn a\r\n"
				+ "inner join paybill_generation_trn_details b on a.paybill_generation_trn_id = b.paybill_generation_trn_id\r\n"
				+ "inner join employee_mst c on b.sevaarth_id = c.sevaarth_id\r\n"
				+ "inner join designation_mst d on d.designation_code = c.designation_code inner join cadre_mst e on c.cadre_code = e.cadre_code inner join cadre_group_mst f on e.group_id = f.id\r\n"
				+ "where a.paybill_generation_trn_id =" + bill_no;
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	public List<Map<String, Object>> getempDetailsGroupAbstractA(String bill_no) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select distinct c.sevaarth_id sevaarthid,c.employee_full_name_en,c.employee_l_name_en,d.designation_name,f.id,b.* from paybill_generation_trn a\r\n"
				+ "inner join paybill_generation_trn_details b on a.paybill_generation_trn_id = b.paybill_generation_trn_id\r\n"
				+ "inner join employee_mst c on b.sevaarth_id = c.sevaarth_id\r\n"
				+ "inner join designation_mst d on d.designation_code = c.designation_code inner join cadre_mst e on c.cadre_code = e.cadre_id inner join cadre_group_mst f on e.group_id = f.id\r\n"
				+ "where  f.id='700057' and a.paybill_generation_trn_id =" + bill_no;
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	public List<Map<String, Object>> getempDetailsGroupAbstractB(String bill_no) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select distinct c.sevaarth_id sevaarthid,c.employee_full_name_en,c.employee_l_name_en,d.designation_name,f.id,b.* from paybill_generation_trn a\r\n"
				+ "inner join paybill_generation_trn_details b on a.paybill_generation_trn_id = b.paybill_generation_trn_id\r\n"
				+ "inner join employee_mst c on b.sevaarth_id = c.sevaarth_id\r\n"
				+ "inner join designation_mst d on d.designation_code = c.designation_code inner join cadre_mst e on c.cadre_code = e.cadre_id inner join cadre_group_mst f on e.group_id = f.id\r\n"
				+ "where f.id='700058' and a.paybill_generation_trn_id =" + bill_no;
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	public List<Map<String, Object>> getempDetailsGroupAbstractC(String bill_no) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select distinct c.sevaarth_id sevaarthid,c.employee_full_name_en,c.employee_l_name_en,d.designation_name,f.id,b.* from paybill_generation_trn a\r\n"
				+ "inner join paybill_generation_trn_details b on a.paybill_generation_trn_id = b.paybill_generation_trn_id\r\n"
				+ "inner join employee_mst c on b.sevaarth_id = c.sevaarth_id\r\n"
				+ "inner join designation_mst d on d.designation_code = c.designation_code inner join cadre_mst e on c.cadre_code = e.cadre_id inner join cadre_group_mst f on e.group_id = f.id\r\n"
				+ "where f.id='700059' and a.paybill_generation_trn_id =" + bill_no;
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	public List<Map<String, Object>> getempDetailsGroupAbstractD(String bill_no) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select distinct c.sevaarth_id sevaarthid,c.employee_full_name_en,c.employee_l_name_en,d.designation_name,f.id,b.* from paybill_generation_trn a\r\n"
				+ "inner join paybill_generation_trn_details b on a.paybill_generation_trn_id = b.paybill_generation_trn_id\r\n"
				+ "inner join employee_mst c on b.sevaarth_id = c.sevaarth_id\r\n"
				+ "inner join designation_mst d on d.designation_code = c.designation_code inner join cadre_mst e on c.cadre_code = e.cadre_id inner join cadre_group_mst f on e.group_id = f.id\r\n"
				+ "where f.id='700060' and a.paybill_generation_trn_id =" + bill_no;
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	public List<Map<String, Object>> getempDetailsGroupAbstractE(String bill_no) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select distinct c.sevaarth_id sevaarthid,c.employee_full_name_en,c.employee_l_name_en,d.designation_name,f.id,b.* from paybill_generation_trn a\r\n"
				+ "inner join paybill_generation_trn_details b on a.paybill_generation_trn_id = b.paybill_generation_trn_id\r\n"
				+ "inner join employee_mst c on b.sevaarth_id = c.sevaarth_id\r\n"
				+ "inner join designation_mst d on d.designation_code = c.designation_code inner join cadre_mst e on c.cadre_code = e.cadre_id inner join cadre_group_mst f on e.group_id = f.id\r\n"
				+ "where f.id='700061' and a.paybill_generation_trn_id =" + bill_no;
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

}
