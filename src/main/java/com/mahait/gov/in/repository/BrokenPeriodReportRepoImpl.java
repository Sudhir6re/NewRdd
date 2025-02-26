package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.DisplayBrokenPeriodReportModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class BrokenPeriodReportRepoImpl implements BrokenPeriodReportRepo {

	@PersistenceContext
	EntityManager manager;

	@Override
	public List<DisplayBrokenPeriodReportModel> getAllDataForinnernew(String strddo, Integer month, Integer year,
			Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select distinct COALESCE(d.department_allowdeduc_col_nm, d.department_allowdeduc_name) allded, d.is_type,d.department_allowdeduc_id,'0' tempvalue,' ' tempempty,department_allowdeduc_seq "
				+ " from employee_mst a inner join employee_allowdeduc_mpg b ON b.sevaarth_id=a.sevaarth_id inner join broken_period_pay_mst c ON c.sevaarth_id=a.sevaarth_id inner join broken_period_allow_deduc_mst e "
				+ " ON e.broken_period_id=c.broken_period_id inner join department_allowdeduc_mst d ON b.department_allowdeduc_code=d.department_allowdeduc_code  where a.ddo_code='"
				+ strddo + "' and billgroup_id=" + billNumber + " and c.month_id=" + month + " " + " and c.year_id="
				+ year + "   order by department_allowdeduc_seq  ";
		Query query = currentSession.createNativeQuery(HQL);
		List<Object[]> lstprop = query.getResultList();
		List<DisplayBrokenPeriodReportModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				DisplayBrokenPeriodReportModel obj = new DisplayBrokenPeriodReportModel();
				obj.setDeptalldetNm(StringHelperUtils.isNullString(objLst[0]));
				obj.setType(StringHelperUtils.isNullInt(objLst[1]));
				obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[2]));
				obj.setTempvalue(StringHelperUtils.isNullString(objLst[3]));
				obj.setTempempty(StringHelperUtils.isNullString(objLst[4]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<Map<String, Object>> getbrokenPeriodDetails(Integer month, Integer year, String strddo,
			Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		String HQL = "select b.sevaarth_id,a.employee_full_name_en,to_char(from_date, 'dd/MM/yyyy')as from_date,to_char(to_date, 'dd/MM/yyyy')as to_date,no_of_days,b.basic_pay,reason,remarks,b.net_pay,b.broken_period_id "
				+ " from employee_mst  a inner join broken_period_pay_mst b ON b.sevaarth_id=a.sevaarth_id  where month_id="
				+ month + " and year_id=" + year + " and a.ddo_code='" + strddo + "' and billgroup_id=" + billNumber
				+ " order by b.broken_period_id";
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	@Override
	public List<Object[]> getbrokenPeriodvalue(Long brokenid, String sevaarthId) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = " select allow_deduc_code,allow_deduc_amt from broken_period_allow_deduc_mst a inner join  employee_allowdeduc_mpg b ON a.allow_deduc_code=b.department_allowdeduc_code where broken_period_id  ="
				+ brokenid + " and b.sevaarth_id ='" + sevaarthId + "' ";

		Query query = currentSession.createNativeQuery(HQL);
		return query.getResultList();
	}

	@Override
	public Long findbillsize(int monthName, int yearName, Long billNumber, String ddoCode) {

		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		Long rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select * from broken_period_pay_mst  where   year_id=" + yearName + "  and  month_id ="
				+ monthName + " and sevaarth_id in(select sevaarth_id from employee_mst  where ddo_code  ='" + ddoCode
				+ "' and billgroup_id=" + billNumber + ")  ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.getResultList();
		if (list != null && list.size() > 0)
			rtnStr = (Long) list.get(0);
		else
			rtnStr = 0l;
		return rtnStr;

	}

}
