package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.HrPayEkuberRecordMst;
import com.mahait.gov.in.entity.HrPayEkuberRecordMstDtls;
import com.mahait.gov.in.entity.OrgUserMst;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class EkuberPayRepoImpl implements EkuberPayRepo {

	@PersistenceContext
	EntityManager entityManager;


	public Long getNxtSequenceNo(String treasuryCode, int noOfPayees) {
	    Session currentSession = getSession();
	    String hql = "SELECT nextval('EKUBER_PNR_SEQ')";
	    Query query = currentSession.createQuery(hql);
	    return (Long) query.getSingleResult();
	}


	private Session getSession() {
		Session session=entityManager.unwrap(Session.class);
		return session;
	}
	

	@Override
	public List getPaybillIdFromAuthNo(String authNo) {
		final Session session = this.getSession();
		final StringBuffer str = new StringBuffer();
		str.append("select a.paybill_month,a.paybill_month,a.consolidate_paybill_trn_id,a.is_active,a.CMP_FILE_STATUS from consolidate_paybill_trn a   ");
		str.append("inner join CONSOLIDATE_PAYBILL_TRN_MPG b  on a.consolidate_paybill_trn_id = b.consolidate_paybill_trn_id ");
		str.append("where a.auth_no="+authNo);
		final Query query = session.createNativeQuery(str.toString());
		return query.getResultList();
	}
 

	@Override
	public List getCommonDetails(String authNo) {
		final Session session = this.getSession();
		List commonDtls = null;
		final StringBuffer str = new StringBuffer();
		str.append("SELECT distinct rlt.LOCATION_CODE,rlt.TO_DDO_CODE,bill.scheme_code, to_char(cons.UPDATED_DATE,'DD/MM/YYYY') as Bill_Date,");
		str.append("cons.NET_AMT, paybill.paybill_gnt_trn_detail_id,cons.consolidate_paybill_trn_id||' '||consBillMpg.paybill_generation_trn_id as DDO_BILL_NUMBER,cons.auth_no,emp.employee_full_name_en,emp.bank_acnt_no,emp.IFSC_CODE,  ");
		str.append(
				"paybill.NET_TOTAL,emp.uid_no,emp.uid_no as uidseeded,emp.email_id,emp.mobile_no1,bill.ddo_code,emp.employee_id FROM consolidate_paybill_trn cons  ");
		str.append("INNER JOIN CONSOLIDATE_PAYBILL_TRN_MPG consBillMpg  ON cons.consolidate_paybill_trn_id = consBillMpg.consolidate_paybill_trn_id  ");
		str.append("INNER JOIN paybill_generation_trn head ON head.paybill_generation_trn_id = consBillMpg.paybill_generation_trn_id ");
		str.append("INNER JOIN paybill_generation_trn_details paybill  ON paybill.paybill_generation_trn_id = head.paybill_generation_trn_id ");
		str.append("INNER JOIN employee_mst emp ON emp.sevaarth_id = paybill.sevaarth_id  ");
		str.append("INNER JOIN MST_DCPS_BILL_GROUP bill    ON head.scheme_billgroup_id = bill.bill_group_id  ");
		str.append("inner join RLT_ZP_DDO_MAP map on bill.ddo_code=map.ZP_DDO_CODE ");
		str.append("inner join MST_DCPS_DDO_OFFICE office on office.ddo_code=map.rept_DDO_CODE ");
		str.append("inner join RLT_DDO_ORG rlt on rlt.DDO_CODE=map.REPT_DDO_CODE ");
		str.append(
				"where cons.auth_no="+authNo+" and  office.district in ('343','346','355','357','358','364','365','366','369','371','344','348','350','352','354','356','362','363','100080','367','368','370','372','374','375','351','345','347','349','353','373','377','376') and cons.AUTH_NUMBER ="+authNo);
		str.append("order by bill.ddo_code ");
		log.info("getTotalEmpConfig DAO------" + str.toString());
		final Query query = session.createNativeQuery(str.toString());
		return query.getResultList();
	}
	
	
	
	@Override
	public List getNonGovDeductionforDDO(String authNo) {
		final Session session = this.getSession();
		List commonDtls = null;
		final StringBuffer str = new StringBuffer();
			str.append("SELECT  DISTINCT SUM(  COALESCE(cons.con_store, 0) + COALESCE(cons.mantralaya_bank, 0) +  COALESCE(cons.mis, 0) + COALESCE(cons.mrt_cop_soc, 0) +   COALESCE(cons.other_Deduc, 0)");
			str.append("+ COALESCE(cons.other_Recovery, 0) ) AS NGR,bill.DDO_CODE,cons.consolidate_paybill_trn_id FROM   consolidate_paybill_trn cons ");
		str.append("INNER JOIN CONSOLIDATE_PAYBILL_TRN_MPG consBillMpg  ON cons.consolidate_paybill_trn_id = consBillMpg.consolidate_paybill_trn_id  ");
		str.append("INNER JOIN paybill_generation_trn head ON head.paybill_generation_trn_id = consBillMpg.paybill_generation_trn_id ");
		str.append("INNER JOIN paybill_generation_trn_details paybill  ON paybill.paybill_generation_trn_id = head.paybill_generation_trn_id ");
		str.append("INNER JOIN employee_mst emp ON emp.sevaarth_id = paybill.sevaarth_id  ");
		str.append("INNER JOIN MST_DCPS_BILL_GROUP bill    ON head.scheme_billgroup_id = bill.bill_group_id  ");
		str.append("inner join RLT_ZP_DDO_MAP map on bill.ddo_code=map.ZP_DDO_CODE ");
		str.append("inner join MST_DCPS_DDO_OFFICE office on office.ddo_code=map.rept_DDO_CODE ");
		str.append("inner join RLT_DDO_ORG rlt on rlt.DDO_CODE=map.REPT_DDO_CODE ");
		str.append(
				"where cons.auth_no="+authNo+" and  office.district in ('343','346','355','357','358','364','365','366','369','371','344','348','350','352','354','356','362','363','100080','367','368','370','372','374','375','351','345','347','349','353','373','377','376') and cons.AUTH_NUMBER ="+authNo);
		str.append("order by bill.ddo_code ");
		log.info("getTotalEmpConfig DAO------" + str.toString());
		final Query query = session.createNativeQuery(str.toString());
		return query.getResultList();
	}

	@Override
	public Long getNPSAmount(String authNo) {
		final Session session = this.getSession();
		Long npsAmt = 0l;
		StringBuffer sbDDO = new StringBuffer();
		sbDDO.append(
				"select sum(paybill.DCPS)+sum(paybill.DCPS_DA)+sum(paybill.DCPS_DELAY)+sum(paybill.DCPS_PAY)+sum(paybill.DED_ADJUST)+sum(paybill.JANJULGISARR) from paybill_generation_trn pay  ");
		sbDDO.append("inner join CONSOLIDATE_PAYBILL_TRN_MPG cmpg on pay.paybill_generation_trn_id=cmpg.paybill_generation_trn_id ");
		sbDDO.append("inner join CONSOLIDATE_PAYBILL_TRN_MPG cnos on cnos.consolidate_paybill_trn_id=cmpg.consolidate_paybill_trn_id ");
		sbDDO.append("inner join paybill_generation_trn_details paybill on pay.paybill_generation_trn_id=paybill.paybill_generation_trn_id ");
		sbDDO.append("where cnos.AUTH_NO = '" + authNo + "'    ");
		log.info("---- getCount DAo---" + sbDDO.toString());
		Query queryDDO = session.createNativeQuery(sbDDO.toString());
		npsAmt = Long.parseLong(queryDDO.getSingleResult().toString());
		return npsAmt;
	}

	@Override
	public String getFinancialYear(String authNo) {
		Session session = this.getSession();
		String hql = "SELECT FIN_YEAR_1 || '-' || FIN_YEAR_2 FROM TRN_IFMS_BEAMS_INTEGRATION WHERE AUTH_NO = :authNo";
		Query query = session.createNativeQuery(hql);
		query.setParameter("authNo", authNo);
		List<String> results = query.getResultList();
		String result = results.isEmpty() ? null : results.get(0);
		return result;
	}

	@Override
	public int checkBenificiaryCount(String authNo) {
		Session session = this.getSession();
		int count = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT NO_OF_BENEFICIARY FROM TRN_IFMS_BEAMS_INTEGRATION where AUTH_NO ='" + authNo +"'");
		log.info("---- getCount DAo---" + sb.toString());
		Query query1 = session.createNativeQuery(sb.toString());
		count = Integer.parseInt(query1.getSingleResult().toString());
		return count;
	}

	@Override
	public void modifyEkuberFile(String authNo) {
		Session session = this.getSession();
		StringBuilder str1 = new StringBuilder();
		str1.append("delete from HR_PAY_EKUBER_RECORD_MST where AUTH_No='"+authNo+"'");
		final Query query = session.createNativeQuery(str1.toString());
		log.info("delete records DAO------" + str1.toString());
		query.executeUpdate();

	}

	@Override
	public List getCMPAuthCount(String authNo) {
		final Session session = this.getSession();
		List commonDtls = null;
		final StringBuffer str = new StringBuffer();
		str.append("select DISTINCT cmp.PAYMENT_REF_NO,cmp.SEVARTH_ID,cmp.AMOUNT,cmp.BENEF_NAME,cmp.acc_no,cmp.IFSC_CODE,cmp.DDO_CODE,cmp.NO_OF_PAYEES,cmp.SCHEME_CODE,cmp.BILL_NET_AMT FROM consolidate_paybill_trn cons  ");
		str.append("INNER JOIN CONSOLIDATE_PAYBILL_TRN_MPG consBillMpg  ON cons.consolidate_paybill_trn_id = consBillMpg.consolidate_paybill_trn_id  ");
		str.append("INNER JOIN paybill_generation_trn head ON head.paybill_generation_trn_id = consBillMpg.paybill_generation_trn_id ");
		str.append("INNER JOIN paybill_generation_trn_details paybill  ON paybill.paybill_generation_trn_id = head.paybill_generation_trn_id ");
		str.append("INNER JOIN employee_mst emp ON emp.sevaarth_id = paybill.sevaarth_id  ");
		str.append("INNER JOIN MST_DCPS_BILL_GROUP bill    ON head.scheme_billgroup_id = bill.bill_group_id  ");
		str.append("inner join RLT_DDO_ORG rlt on rlt.DDO_CODE=map.REPT_DDO_CODE ");
		str.append("inner join HR_PAY_EKUBER_RECORD_MST cmp on cmp.AUTH_NO=cons.AUTH_NUMBER ");
		str.append("where cmp.auth_no="+authNo);
		final Query query = session.createNativeQuery(str.toString());
		return query.getResultList();
	}

	@Override
	public void save(HrPayEkuberRecordMst ekuberRecord) {
		final Session session = this.getSession();
		session.merge(ekuberRecord);
	}

	@Override
	public void save(HrPayEkuberRecordMstDtls ekuberRecordDtls) {
		final Session session = this.getSession();
		session.merge(ekuberRecordDtls);
	}

	@Override
	public Long getPaybillFlag(String authNo) {
		final Session session = this.getSession();
		Long flag = 0L;
		StringBuffer sb = new StringBuffer();
		//sb.append("select paybill_flag  from consolidated_bill_mst where  ");
		sb.append("select IS_ACTIVE  from CONSOLIDATE_PAYBILL_TRN  where  ");
		sb.append(" Auth_no=" + authNo + "");
		log.info("---- getCount DAo---" + sb.toString());
		Query query1 = session.createNativeQuery(sb.toString());

		flag = Long.parseLong(query1.getSingleResult().toString());
		return flag;

	}

	@Override
	public List getOtherDedForDDO(String authNo, String tableName, Long paybillFlag, int paybillMonth,
			int paybillYear) {

		final Session session = this.getSession();
		List otherDedList = null;
		final String authNoValue = authNo.trim();

		int count = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from CONSOLIDATE_PAYBILL_TRN where ((paybill_month<8 and paybill_year=16) or paybill_year<16) and   ");
		sb.append(" Auth_no='" + authNoValue + "' ");
		log.info("---- getCount DAo---" + sb.toString());
		Query query1 = session.createNativeQuery(sb.toString());
		count = Integer.parseInt(query1.getSingleResult().toString());
		StringBuffer str = new StringBuffer();
		if (count == 0) {
			str.append(
					"SELECT sum(pay.TOTAL_DED-pay.pt-pay.GPF_GRP_ABC-pay.GPF_GRP_D-pay.GPF_ADV_GRP_ABC-pay.GPF_ADV_GRP_D-pay.GPF_ABC_ARR_MR-pay.GPF_D_ARR_MR-pay.dcps-pay.DCPS_DA-pay.DCPS_DELAY-pay.DCPS_PAY-pay.JANJULGISARR-pay.ACC_POLICY-pay.GIS-pay.REVENUE_STAMP) as other_ded ,bill.DDO_CODE  from paybill_generation_trn_details pay ");
		} else {
			str.append("SELECT sum(pay.total_ded_effective)  as other_ded ,bill.DDO_CODE  from paybill_generation_trn_details pay ");
		}
		str.append("inner join paybill_generation_trn paybill on pay.paybill_generation_trn_id=paybill.paybill_generation_trn_id ");
		str.append("inner join CONSOLIDATE_PAYBILL_TRN_MPG cmpg on paybill.paybill_generation_trn_id=cmpg.paybill_generation_trn_id ");
		str.append("INNER join CONSOLIDATE_PAYBILL_TRN cmst on cmpg.consolidate_paybill_trn_id= cmst.consolidate_paybill_trn_id ");
		str.append("INNER join MST_DCPS_BILL_GROUP bill on paybill.scheme_billgroup_id=bill.BILL_GROUP_ID ");
		str.append("where cmst.auth_no='" + authNoValue + "' and paybill.is_active>8");
		str.append("group by bill.DDO_CODE order by bill.DDO_CODE ");
		log.info("getOtherDED DAO------" + str.toString());
		final Query query = session.createNativeQuery(str.toString());
		return query.getResultList();

	}

	@Override
	public List getDdoDtls(String level1Ddocode, String authNo) {
		final Session session = this.getSession();
		final StringBuffer str = new StringBuffer();
		str.append(
				"SELECT ddo.ddo_name,ddo.account_no,ddo.ifs_code,office.EMAIL,office.TEL_NO2,cons.consolidate_paybill_trn_id||' '||consBillMpg.paybill_generation_trn_id ");
		str.append(" FROM MST_DCPS_DDO_OFFICE office inner join org_ddo_mst ddo on ddo.DDO_CODE=office.DDO_CODE ");
		str.append(" inner join paybill_generation_trn head on (head.loc_id = office.LOC_ID or  head.ddo_code = office.ddo_code");
		str.append("inner join CONSOLIDATE_PAYBILL_TRN_MPG consBillMpg on head.paybill_generation_trn_id =consBillMpg.paybill_generation_trn_id ");
		str.append("inner JOIN CONSOLIDATE_PAYBILL_TRN cons on cons.consolidate_paybill_trn_id = consBillMpg.consolidate_paybill_trn_id ");
		str.append("and upper(office.DDO_OFFICE)='YES' and ddo.DDO_CODE='" + level1Ddocode + "' and cons.auth_no ="
				+ authNo.trim() + " ");
		str.append(
				" group by ddo.ddo_name,ddo.account_no,ddo.ifs_code,office.EMAIL,office.TEL_NO2,cons.consolidate_paybill_trn_id,consBillMpg.paybill_generation_trn_id ");
		final Query query = session.createNativeQuery(str.toString());
		return query.getResultList();
	}

	@Override
	public List getCAFODtls(String level1Ddocode) {
		final Session session = this.getSession();
		List ddoDtls = null;
		final StringBuffer str = new StringBuffer();
		str.append(
				"SELECT ddo.ddo_name,ddo.NPS_ACCOUNT_NUMBER,ddo.NPS_IFSC_CODE,office.EMAIL,office.TEL_NO2,ddo.ddo_code ");
		str.append("FROM MST_DCPS_DDO_OFFICE office inner join org_ddo_mst ddo on ddo.DDO_CODE=office.DDO_CODE ");
		str.append(
				" and upper(office.DDO_OFFICE)='YES' and ddo.dDO_CODE = (select final_ddo_code from rlt_zp_ddo_map where zp_ddo_code ='+level1Ddocode+') ");
		final Query query = session.createNativeQuery(str.toString());
		System.out.println("ddocode^^^^^^^^^^^^" + str.toString());
		return query.getResultList();
	}

	@Override
	public void updateCMPFlag(String authNo, String flag, String downloadDate) {
		StringBuilder str = new StringBuilder();
		Session hibSession = getSession();
		str.append(
				"UPDATE CONSOLIDATE_PAYBILL_TRN SET CMP_DOWNLOAD_STATUS = :flag, CMP_DOWNLOAD_DATE = :downloadDate WHERE AUTH_No = :authNo");
		Query query = hibSession.createNativeQuery(str.toString());
		query.setParameter("flag", flag);
		query.setParameter("downloadDate", downloadDate);
		query.setParameter("authNo", authNo);

		log.info("Executing updateCMPFlag with query: " + str.toString());
		query.executeUpdate();
		log.info("Updation done");
	}


	@Override
	public List<Object[]> getEkuberDataForExcel(String authNo) {
		Session session = this.getSession();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append("SELECT EMPLOYEE_TYPE, DDO_CODE, BENEF_NAME, SEVARTH_ID, ACC_NO, IFSC_CODE, AMOUNT  ")
				.append("FROM HR_PAY_EKUBER_RECORD_MST ").append("WHERE AUTH_No = :authNo ")
				.append("ORDER BY SEVARTH_ID ASC");
		Query query = session.createNativeQuery(lSBQuery.toString());
		query.setParameter("authNo", authNo);
		List resultList = query.getResultList();
		return resultList;
	}


	@Override
	public List<Object[]> getToDdoCodeAndNoOfPayees(String authNo) {
		Session session = this.getSession();
		StringBuilder lSBQuery = new StringBuilder();
		lSBQuery.append(
				"SELECT DISTINCT TO_DDO_CODE, NO_OF_PAYEES FROM HR_PAY_EKUBER_RECORD_MST WHERE AUTH_NO = :authNo");
		Query query = session.createNativeQuery(lSBQuery.toString());
		query.setParameter("authNo", authNo);
		List<Object[]> resultList = query.getResultList();
		return resultList;
	}


}
