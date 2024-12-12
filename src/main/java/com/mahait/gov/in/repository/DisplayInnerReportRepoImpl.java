package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*import org.hibernate.Query;*/
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.PaybillGenerationTrnEntity;
import com.mahait.gov.in.model.DisplayInnerReportModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class DisplayInnerReportRepoImpl implements DisplayInnerReportRepo {
	@PersistenceContext
	EntityManager manager;

	@Override
	public List<DisplayInnerReportModel> getAllDataForinnernew(String strddo, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);

		String split[] = strddo.split("_");
		strddo = split[0];

		String HQL = "select distinct COALESCE(d.department_allowdeduc_col_nm, d.department_allowdeduc_name) allded, d.is_type,d.department_allowdeduc_id,'0' tempvalue,' ' tempempty,department_allowdeduc_col_nm,d.is_allowdeduc_type_sum "
				+ " from employee_mst a inner join employee_allowdeduc_mpg b ON b.sevaarth_id=a.sevaarth_id inner join paybill_generation_trn_details c ON c.sevaarth_id=a.sevaarth_id "
				+ " inner join department_allowdeduc_mst d ON b.department_allowdeduc_code=d.department_allowdeduc_code where a.ddo_code= '"
				+ strddo + "' and paybill_generation_trn_id  = '" + billNumber + "'  " 
				+ "   and d.is_active='1' and d.is_non_government <> 1 order by department_allowdeduc_col_nm  ";
		Query query = currentSession.createNativeQuery(HQL);

		List<Object[]> lstprop = query.list();
		List<DisplayInnerReportModel> lstObj = new ArrayList<>();
		int i = 1;
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				DisplayInnerReportModel obj = new DisplayInnerReportModel();
				obj.setDeptalldetNm(StringHelperUtils.isNullString(objLst[0]));
				obj.setType(StringHelperUtils.isNullInt(objLst[1]));
				obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[2]));
				obj.setTempvalue(StringHelperUtils.isNullString(objLst[3]));
				obj.setTempempty(StringHelperUtils.isNullString(objLst[4]));
				obj.setCompoType(StringHelperUtils.isNullInt(objLst[6]));
				lstObj.add(obj);
				i++;
			}
		}
		return lstObj;
	}

	@Override
	public List<Map<String, Object>> getempDetails(String bill_no) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select distinct c.sevaarth_id sevaarthid,c.employee_full_name_en,c.employee_l_name_en,d.designation_name,c.emp_class, "
				+ " case when c.pay_commission_code=8 then CAST (c.pay_commission_code AS varchar) else e.scale_start_amt ||'-'||e.scale_end_amt end as payComm, "
				+ " case when c.seven_pc_level is not null then 'Level'||' '||f.levels else c.basic_pay-c.grade_pay ||'+'|| c.grade_pay end as payband,b.basic_pay as basic,d.designation_code,b.* from paybill_generation_trn a "
				+ " inner join paybill_generation_trn_details b on a.paybill_generation_trn_id = b.paybill_generation_trn_id inner join employee_mst c on b.sevaarth_id = c.sevaarth_id "
				+ " inner join designation_mst d on d.designation_code = c.designation_code left join  pay_scale_sixpc_mst as e on e.pay_scale_code=c.pay_scale_code "
				+ " left join payband_gp_state_7pc as f on f.level_id=c.seven_pc_level where a.paybill_generation_trn_id ="
				+ bill_no + "  order by c.emp_class,d.designation_code";
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	@Override
	public Date findbillCreateDate(Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		Date rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select created_date from paybill_generation_trn  where    paybill_generation_trn_id  ='"
				+ billNumber + "' limit 1 ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (Date) list.get(0);
		return rtnStr;
	}

	@Override
	public String getLoanDtls(String empId, Long month, int year) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"select total_principal_loan_recover_installment ||'/'|| total_principal_loan_installment as loan  from paybill_loan_recovery_dtls"
						+ " where sevaarth_id = '" + empId + "' and paybill_month  =" + month + " and  paybill_year  ="
						+ year + "   ");

		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getDcpsGpfNoDtls(String sevaarthid) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select dcps_no from employee_mst where sevaarth_id  = '" + sevaarthid + "'   ");

		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getGpfNoDtls(String sevaarthid) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select  pfdescription from employee_mst  where    sevaarth_id ='" + sevaarthid + "'   ");

		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getfaLoanDtls(String sevaarthid, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select fa_inst ||' '||fa from paybill_generation_trn_details  where sevaarth_id= '" + sevaarthid
				+ "' and paybill_generation_trn_id ='" + billNumber + "'");
		/*
		 * query.
		 * append("select no_of_installments_paid+1  ||'/'|| sanctioned_no_of_installment  ||'        '|| installment_amount from lna_fa_employee_request_mst "
		 * + " where sevaarth_id = '"
		 * +sevaarthid+"' and no_of_installments_paid <= sanctioned_no_of_installment  "
		 * );
		 */
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getcaLoanDtls(String sevaarthid, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select comp_adv_inst ||' '||comp_adv from paybill_generation_trn_details  where sevaarth_id= '"
				+ sevaarthid + "' and paybill_generation_trn_id ='" + billNumber + "'");

		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getvaLoanDtls(String sevaarthid, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"select other_veh_adv_inst ||' '||other_rec from paybill_generation_trn_details  where      sevaarth_id= '"
						+ sevaarthid + "' " + "and paybill_generation_trn_id ='" + billNumber + "'");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String gethbaLoanDtls(String sevaarthid, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append("select hba_house_inst ||' '||hba_house from paybill_generation_trn_details  where  sevaarth_id= '"
				+ sevaarthid + "' and paybill_generation_trn_id ='" + billNumber + "'");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getgpfLoanDtls(String sevaarthid, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"select gpf_advance_inst ||' '||gpf_advance from paybill_generation_trn_details  where  sevaarth_id= '"
						+ sevaarthid + "' and paybill_generation_trn_id ='" + billNumber + "'");

		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getgpfIILoanDtls(String sevaarthid, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"select gpf_advance_ii_inst ||' '||gpf_advance_ii from paybill_generation_trn_details  where  sevaarth_id= '"
						+ sevaarthid + "' and paybill_generation_trn_id ='" + billNumber + "'");

		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getbillDetails(Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"select description from mst_dcps_bill_group where bill_group_id in (select scheme_billgroup_id from paybill_generation_trn \r\n"
						+ " where paybill_generation_trn_id='" + billNumber + "') ");
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();

		if (list != null && list.size() > 0)
			rtnStr = list.get(0).toString();
		return rtnStr;
	}

	@Override
	public String getPayFixDiffLoanDtls(String sevaarthid, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"select pay_fixadv_diff_inst ||' '||pay_fix_diff from paybill_generation_trn_details where  sevaarth_id= '"
						+ sevaarthid + "' and paybill_generation_trn_id ='" + billNumber + "'");

		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String gethbaLoanIntsDtls(String string, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"select hba_house_inst ||' '||hba_house_int_amt from paybill_generation_trn_details  where  sevaarth_id= '"
						+ string + "' and paybill_generation_trn_id ='" + billNumber + "'");
		/*
		 * query.
		 * append("select no_of_installments_paid+1  ||'/'|| sanctioned_no_of_installment  ||'        '|| installment_amount from lna_hba_employee_request_mst "
		 * + " where sevaarth_id = '"
		 * +sevaarthid+"' and no_of_installments_paid <= sanctioned_no_of_installment and is_active='1' limit 1 "
		 * );
		 */
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public String getexPayRecDtls(String string, Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		List list = new ArrayList();
		String rtnStr = null;
		StringBuffer query = new StringBuffer();
		query.append(
				"select excess_pay_rec_int ||' '||excess_pay_rec from paybill_generation_trn_details  where  sevaarth_id= '"
						+ string + "' and paybill_generation_trn_id ='" + billNumber + "'");
		/*
		 * query.
		 * append("select no_of_installments_paid+1  ||'/'|| sanctioned_no_of_installment  ||'        '|| installment_amount from lna_hba_employee_request_mst "
		 * + " where sevaarth_id = '"
		 * +sevaarthid+"' and no_of_installments_paid <= sanctioned_no_of_installment and is_active='1' limit 1 "
		 * );
		 */
		Query hsqlQuery = currentSession.createNativeQuery(query.toString());
		list = hsqlQuery.list();
		if (list != null && list.size() > 0)
			rtnStr = (String) list.get(0);
		return rtnStr;

	}

	@Override
	public PaybillGenerationTrnEntity findPayBilldetailByPaybillid(Long billNumber) {
		Session currentSession = manager.unwrap(Session.class);
		return currentSession.find(PaybillGenerationTrnEntity.class, billNumber);
	}
}