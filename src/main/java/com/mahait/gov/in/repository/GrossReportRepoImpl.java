package com.mahait.gov.in.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class GrossReportRepoImpl implements GrossReportRepo {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> lstGetDeptLst() {
		Session currentSession = entityManager.unwrap(Session.class);
		String hql = "SELECT\r\n" + 
				"loc_id,\r\n" + 
				"loc_name \r\n" + 
				"FROM\r\n" + 
				"CMN_LOCATION_MST  \r\n" + 
				"where\r\n" + 
				"loc_id in (\r\n" + 
				"Select\r\n" + 
				"distinct(field_department_code) \r\n" + 
				"from\r\n" + 
				"employee_mst \r\n" + 
				"where\r\n" + 
				"is_active = 1\r\n" + 
				")";
		Query query = currentSession.createNamedQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> grossReport(int yearId, long locId) {
		Session currentSession = entityManager.unwrap(Session.class);
		Integer nextYear = yearId+1;
		String HQL = " SELECT\r\n" + 
				"    loc.LOC_NAME,\r\n" + 
				"    cm.district_name,\r\n" + 
				"    off.OFF_NAME,\r\n" + 
				"    dc.sevaarth_id,\r\n" + 
				"    CASE \r\n" + 
				"        WHEN dc.GENDER = 'M' THEN 'Male'\r\n" + 
				"        WHEN dc.GENDER = 'F' THEN 'Female'\r\n" + 
				"        ELSE 'Other'\r\n" + 
				"    END AS gender,\r\n" + 
				"    dsgn.designation_name,\r\n" + 
				"    dc.dob,\r\n" + 
				"    EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM dc.dob) - \r\n" + 
				"    CASE \r\n" + 
				"        WHEN EXTRACT(MONTH FROM CURRENT_DATE) < EXTRACT(MONTH FROM dc.dob) \r\n" + 
				"            OR (EXTRACT(MONTH FROM CURRENT_DATE) = EXTRACT(MONTH FROM dc.dob) \r\n" + 
				"                AND EXTRACT(DAY FROM CURRENT_DATE) < EXTRACT(DAY FROM dc.dob)) \r\n" + 
				"        THEN 1\r\n" + 
				"        ELSE 0\r\n" + 
				"    END AS AGE,\r\n" + 
				"    SUM(pay.GROSS_AMT) AS total_gross_amt,\r\n" + 
				"    SUM(pay.basic_arr) AS total_basic_arr,\r\n" + 
				"    SUM(pay.da_arr) AS total_da_arr,\r\n" + 
				"    SUM(pay.SVNPC_GPF_ARR) AS total_svnpc_gpf_arr,\r\n" + 
				"    SUM(pay.SVNPC_DCPS_ARR) AS total_svnpc_dcps_arr,\r\n" + 
				"    SUM(pay.TRANS_ARREAR) AS total_trans_arrear,\r\n" + 
				"    SUM(pay.SVNPC_TA_ARR) AS total_svnpc_ta_arr,\r\n" + 
				"    SUM(pay.Temp_HRA_5thPay) AS total_temp_hra_5thpay\r\n" + 
				"FROM\r\n" + 
				"    paybill_generation_trn mpg\r\n" + 
				"INNER JOIN\r\n" + 
				"    paybill_generation_trn_details pay \r\n" + 
				"        ON pay.paybill_generation_trn_id = mpg.paybill_generation_trn_id\r\n" + 
				"INNER JOIN\r\n" + 
				"    ORG_DDO_MST ddo \r\n" + 
				"        ON ddo.ddo_code = mpg.ddo_code\r\n" + 
				"INNER JOIN\r\n" + 
				"    MST_DCPS_DDO_OFFICE off \r\n" + 
				"        ON off.DDO_CODE = ddo.DDO_CODE\r\n" + 
				"        AND off.DDO_OFFICE ILIKE 'YES' \r\n" + 
				"INNER JOIN\r\n" + 
				"    CMN_LOCATION_MST loc \r\n" + 
				"        ON loc.LOC_ID =  cast(ddo.HOD_LOC_CODE as bigint)\r\n" + 
				"INNER JOIN\r\n" + 
				"    employee_mst dc \r\n" + 
				"        ON pay.sevaarth_id = dc.sevaarth_id\r\n" + 
				"LEFT JOIN\r\n" + 
				"    CMN_DISTRICT_MST cm \r\n" + 
				"        ON cm.DISTRICT_ID = cast(dc.DISTRICT_Code as bigint)\r\n" + 
				"INNER JOIN\r\n" + 
				"    designation_mst dsgn \r\n" + 
				"        ON dsgn.designation_id = dc.designation_code\r\n" + 
				"WHERE\r\n" + 
				"    (\r\n" + 
				"        (mpg.PAYBILL_YEAR = "+yearId+" AND mpg.PAYBILL_MONTH IN (4, 5, 6, 7, 8, 9, 10, 11, 12))\r\n" + 
				"        OR (mpg.PAYBILL_YEAR = "+nextYear+" AND mpg.PAYBILL_MONTH IN (1, 2, 3))\r\n" + 
				"    )\r\n" + 
				"    AND mpg.is_active = 14\r\n" + 
				"    AND pay.gross_amt > 0\r\n" + 
				"    AND loc.LOC_ID = '"+locId+"' \r\n" + 
				"GROUP BY\r\n" + 
				"    loc.LOC_NAME,\r\n" + 
				"    cm.district_name,\r\n" + 
				"    off.OFF_NAME,\r\n" + 
				"    dc.sevaarth_id,\r\n" + 
				"    dsgn.designation_name,\r\n" + 
				"    dc.dob,\r\n" + 
				"    dc.GENDER;\r\n" + 
				"";
		Query query = currentSession.createNamedQuery(HQL);
		System.out.println("HQL:"+HQL);
		return query.list();
	}

}
