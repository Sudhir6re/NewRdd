package com.mahait.gov.in.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.PayslipReportModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
@SuppressWarnings("unchecked")
@Repository 
public class PayslipReportRepoImpl  implements PayslipReportRepo{
	@PersistenceContext
	EntityManager entityManager;
	


	@Override
	public List<Object[]> getEmployeeData(String schemeBillGroupId, int monname, int curryear) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL ="select a.sevaarth_id,employee_full_name_en,designation_name,doj,dob,mobile_no1,pan_no,pay_commission_code,uid_no,a.ifsc_code,bank_acnt_no,a.ddo_code,b.gross_total_amt,b.total_deduction,b.total_net_amt,fnNumberToWords(cast(b.total_net_amt as bigint)) from employee_mst a "
				+ "  inner join designation_mst c on c.designation_code=a.designation_code inner join paybill_generation_trn_details b on b.sevaarth_id=a.sevaarth_id inner join paybill_generation_trn d "
				+ " on d.paybill_generation_trn_id=b.paybill_generation_trn_id where d.paybill_month ="+monname+"  and  d.paybill_year ="+curryear+" and scheme_billgroup_id in(select bill_group_id from scheme_billgroup_mpg  a left join scheme_mst b on b.scheme_code=a.scheme_code where bill_group_id ="+schemeBillGroupId+") and d.is_active=14";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<PayslipReportModel> getAllDataForinnernew(String sevaarthId) {

		Session currentSession = entityManager.unwrap(Session.class);
		String HQL=" select distinct COALESCE(d.department_allowdeduc_col_nm, d.department_allowdeduc_name) allded,d.is_type,d.department_allowdeduc_id,'0' " + 
				" tempvalue,' ' tempempty,department_allowdeduc_seq " + 
				" from employee_mst a inner join department_allowdeduc_mpg b ON b.ddo_code=a.ddo_code " + 
				" inner join paybill_generation_trn_details c ON c.sevaarth_id=a.sevaarth_id " + 
				" inner join paybill_generation_trn e ON e.paybill_generation_trn_id=c.paybill_generation_trn_id  " + 
				" inner join department_allowdeduc_mst d ON b.department_allowdeduc_code=d.department_allowdeduc_code  where  d.is_active='1' " + 
				" and c.sevaarth_id ='"+sevaarthId+"'  order by department_allowdeduc_seq";
		Query query = currentSession.createNativeQuery(HQL);
		List<Object[]> lstprop = query.list();
		List<PayslipReportModel> lstObj = new ArrayList<>();
		int i=1;
		if (!lstprop.isEmpty()) {
            for (Object[] objLst : lstprop) {
            	PayslipReportModel obj = new PayslipReportModel();
            	obj.setDeptalldetNm(StringHelperUtils.isNullString(objLst[0]));
                obj.setType(StringHelperUtils.isNullInt(objLst[1]));
                obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[2]));
                obj.setTempvalue(StringHelperUtils.isNullString(objLst[3]));
                
                if(objLst[3]!=null) {
                double yearVal=0;
                
                Object obj1 = objLst[3];
                String str = obj1.toString(); 
                double d = Double.valueOf(str).doubleValue();
                yearVal=d*12;
                obj.setTempempty( Double.toString(yearVal));
                }
                if(objLst[4]!=null) { obj.setTempvalue(StringHelperUtils.isNullString(objLst[4]));}
                if(objLst[5]!=null) {  obj.setDeptallowdeducid(StringHelperUtils.isNullInt(objLst[5]));}
                lstObj.add(obj);
                i++;
            }
        }
		return lstObj;
	
	}

	@Override
	public List<Map<String, Object>> getempDetails(String schemeBillgroupId, int payBillYear, int payBillMonth,String savaarthid) {

		Session currentSession = entityManager.unwrap(Session.class);
		
		String HQL = " select a.* from paybill_generation_trn_details a inner join paybill_generation_trn d on a.paybill_generation_trn_id=d.paybill_generation_trn_id" + 
				" where d.paybill_month ="+payBillMonth+"  and  d.paybill_year ="+payBillYear+" and scheme_billgroup_id in(select bill_group_id" + 
				" from mst_dcps_bill_group a left join mst_scheme b on b.scheme_code=a.scheme_code where bill_group_id ='"+schemeBillgroupId+"') and" + 
				" a.sevaarth_id='"+savaarthid+"' and d.is_active=14   ";
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	@Override
	public List<Object[]> getEmployeeData(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			String sevaarthId) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL ="select a.sevaarth_id,employee_full_name_en,designation_name,doj,dob,mobile_no1,pan_no,pay_commission_code,uid_no,ifsc_code,bank_acnt_no,a.ddo_code,b.gross_total_amt,b.total_deduction,b.total_net_amt,a.dcps_no from employee_mst a "
				+ "  inner join designation_mst c on c.designation_code=a.designation_code inner join paybill_generation_trn_details b on b.sevaarth_id=a.sevaarth_id inner join paybill_generation_trn d "
				+ " on d.paybill_generation_trn_id=b.paybill_generation_trn_id where d.paybill_month ="+paybillMonth+"  and  d.paybill_year ="+paybillYear+" and scheme_billgroup_id in(select bill_group_id from scheme_billgroup_mpg  a left join scheme_mst b on b.scheme_code=a.scheme_code where scheme_billgroup_id ="+schemeBillgroupId+") and d.is_active=14 and a.sevaarth_id='"+sevaarthId+"'";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> getEmpDataBySevaarthid(Integer paybillMonth, Integer paybillYear, String savaarthid) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL ="select a.sevaarth_id,employee_full_name_en,designation_name,doj,dob,mobile_no1,pan_no,pay_commission_code,uid_no,a.ifsc_code,bank_acnt_no,a.ddo_code,b.gross_total_amt,b.total_deduction,b.total_net_amt,fnNumberToWords(cast(b.total_net_amt as bigint))word,sub_department_name_en,case when a.dcps_gpf_flag='Y' then a.dcps_no else a.pfdescription end,f.bill_group_name from employee_mst a "
				+ "  inner join designation_mst c on c.designation_code=a.designation_code inner join paybill_generation_trn_details b on b.sevaarth_id=a.sevaarth_id inner join paybill_generation_trn d "
				+ " on d.paybill_generation_trn_id=b.paybill_generation_trn_id inner join sub_department_mst e on a.sub_department_id=e.sub_department_id"
				+" inner join  bill_group_mst f on a.billgroup_id=f.bill_group_id "
				+ " where d.paybill_month ="+paybillMonth+"  and  d.paybill_year ="+paybillYear+" and scheme_billgroup_id in(select bill_group_id from scheme_billgroup_mpg  a left join scheme_mst b on b.scheme_code=a.scheme_code) and d.is_active=14 and a.sevaarth_id='"+savaarthid+"'";
		Query query = currentSession.createNativeQuery(HQL);
		System.out.println("-------getEmpDataBySevaarthid----"+HQL);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> getempDetailsBySevaarthId(Integer paybillYear, Integer paybillMonth,
			String savaarthid) {
		// TODO Auto-generated method stub
        Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select a.* from paybill_generation_trn_details a inner join paybill_generation_trn d on a.paybill_generation_trn_id=d.paybill_generation_trn_id    "
				+ "   where d.paybill_month ="+paybillMonth+"  and  d.paybill_year ="+paybillYear+" and scheme_billgroup_id in(select bill_group_id from scheme_billgroup_mpg  "
						+ " a left join scheme_mst b on b.scheme_code=a.scheme_code) and a.sevaarth_id='"+savaarthid+"' and d.is_active=14   ";
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	@Override
	public List<Object[]> getEmployeeData1(String schemeBillgroupId, Integer paybillMonth, Integer paybillYear,
			String userName) {
			Session currentSession = entityManager.unwrap(Session.class);
			String HQL =" select a.sevaarth_id,employee_full_name_en,designation_name,doj,dob,mobile_no1,pan_no,a.pay_commission_code,uid_no,a.ifsc_code," + 
					" bank_acnt_no,a.ddo_code,b.gross_amt,b.payslip_total_deduction,b.payslip_net,case when a.dcps_gpf_flag='Y' then a.dcps_no else a.pfdescription end,f.description,a.super_ann_date," + 
					" b.seven_pc_lvl,b.basic_pay,d.voucher_no,d.voucher_date,d.paybill_generation_trn_id from  " + 
					" employee_mst a inner join paybill_generation_trn_details b on b.sevaarth_id=a.sevaarth_id inner join designation_mst c on c.designation_code=b.desg_code " + 
					" inner join paybill_generation_trn d on d.paybill_generation_trn_id=b.paybill_generation_trn_id  inner join mst_dcps_bill_group f on d.scheme_billgroup_id=f.bill_group_id where " + 
					" d.paybill_month = "+paybillMonth+" and  d.paybill_year = "+paybillYear+" and scheme_billgroup_id in(select bill_group_id from mst_dcps_bill_group  a " + 
					" left join mst_scheme b on b.scheme_code=a.scheme_code where bill_group_id ='"+schemeBillgroupId+"')  " + 
					" and d.ddo_code='"+userName+"' and d.is_active=14 ";
			Query query = currentSession.createNativeQuery(HQL);
			return query.list();
	}

	@Override
	public List<Object[]> lstBillGroupUserWise(String userName) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL ="select distinct a.scheme_billgroup_id,c.bill_description from paybill_generation_trn a inner join paybill_generation_trn_details b on" + 
				"a.paybill_generation_trn_id=b.paybill_generation_trn_id inner join scheme_billgroup_mpg c  on c.bill_group_id=a.scheme_billgroup_id where b.sevaarth_id='"+userName+"' and a.is_active in(5,14)";
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	@Override
	public List<Object[]> fetchBillGorupPaySlip(int paybillmonth, int paybillYear, String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL ="select a.scheme_billgroup_id,c.bill_description from paybill_generation_trn a inner join paybill_generation_trn_details b on " + 
				"a.paybill_generation_trn_id=b.paybill_generation_trn_id inner join scheme_billgroup_mpg c  on c.bill_group_id=a.scheme_billgroup_id " + 
				"where a.paybill_month="+paybillmonth+" and a.paybill_year="+paybillYear+" and a.is_active in(14)and b.sevaarth_id='"+ddoCode+"' ";
		System.out.println("fetchBillGorupPaySlip"+HQL);
		Query query = currentSession.createNativeQuery(HQL);
		return query.list();
	}

	

	@Override
	public List<Object[]> getEmpDataBySevaarthidBillGroup(Integer paybillMonth, Integer paybillYear, String savaarthid,
			String schemeBillgroupId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		String HQL ="select a.sevaarth_id,employee_full_name_en,designation_name,doj,dob,mobile_no1,pan_no,a.pay_commission_code,uid_no,a.ifsc_code," + 
				"bank_acnt_no,a.ddo_code,b.gross_total_amt,b.total_deduction,b.total_net_amt,fnNumberToWords(cast(b.total_net_amt as bigint))word," + 
				"sub_department_name_en,case when a.dcps_gpf_flag='Y' then a.dcps_no else a.pfdescription end,f.bill_group_name,a.super_ann_date,b.hra_percentage, b.da_percentage,b.seven_pc_level,b.basic_pay,d.voucher_no,d.voucher_date,d.paybill_generation_trn_id from employee_mst" + 
				"a inner join paybill_generation_trn_details b on b.sevaarth_id=a.sevaarth_id   inner join designation_mst c " + 
				"on c.designation_code=b.desg_code inner join paybill_generation_trn d  on d.paybill_generation_trn_id=b.paybill_generation_trn_id " + 
				"inner join sub_department_mst e on b.sub_department_id=e.sub_department_id inner join  bill_group_mst f" + 
				"on d.scheme_billgroup_id=f.bill_group_id  where d.paybill_month = "+paybillMonth+" and  d.paybill_year = "+paybillYear+" and scheme_billgroup_id = "+schemeBillgroupId+" and " + 
				"d.is_active=14 and a.sevaarth_id='"+savaarthid+"'  ";
		Query query = currentSession.createNativeQuery(HQL);
		System.out.println("-------getEmpDataBySevaarthid----"+HQL);
		return query.list();
	}

	@Override
	public List<Map<String, Object>> getempDetailsBySevaarthIduserPayslip(Integer paybillYear, Integer paybillMonth,
			String savaarthid, String schemeBillgroupId) {
		// TODO Auto-generated method stub
        Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "select a.* from paybill_generation_trn_details a inner join paybill_generation_trn d on a.paybill_generation_trn_id=d.paybill_generation_trn_id    "
				+ "   where d.paybill_month ="+paybillMonth+"  and  d.paybill_year ="+paybillYear+" and scheme_billgroup_id ='"+schemeBillgroupId+"' and a.sevaarth_id='"+savaarthid+"' and d.is_active=14   ";
		NativeQuery<Map<String, Object>> nativeQuery = currentSession.createNativeQuery(HQL);
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> resvalue = nativeQuery.list();
		return resvalue;
	}

	
}
