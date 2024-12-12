package com.mahait.gov.in.nps.repository;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.nps.model.DcpsLegacyModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class NpsLegacyFileGenerationRepoImpl implements NpsLegacyFileGenerationRepo {

	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findLegacyEmployeeList(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages, Long locId) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(
				"SELECT a.employee_full_name_en, a.dcps_no,a.pran_no,b.emp_contri,b.employer_contri,b.emp_int,");
		stringBuilder.append(
				"b.employer_int,f.loc_name,e.DTO_REG_NO,e.ddo_reg_no ,b.NPS_ID,a.ddo_code,b.total FROM EMPLOYEE_MST a ");
		stringBuilder.append("INNER JOIN DCPS_LEGACY_DATA b on a.sevaarth_id=b.SEVARTH_ID  ");
		stringBuilder.append("INNER JOIN RLT_ZP_DDO_MAP c on c.zp_ddo_code=a.ddo_code ");
		stringBuilder.append("INNER JOIN ORG_DDO_MST d on d.DDO_CODE = c.REPT_DDO_CODE ");
		stringBuilder.append("INNER JOIN MST_DTO_REG e on e.ddo_code = c.ZP_DDO_CODE ");
		if ((locId != null) && (locId.toString().equalsIgnoreCase("2222"))) {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		} else {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		}
		stringBuilder.append("INNER JOIN CMN_LOCATION_MST f on cast(f.LOC_ID as varchar)=substr(d.DDO_CODE,1,4) ");
		stringBuilder.append(
				"WHERE a.pran_no is not null AND a.is_active=1 AND  b.batch_id is  null  AND  a.dcps_gpf_flag='Y' ");
		stringBuilder.append("AND  b.STATUS = '1'  AND b.period = '" + dcpsLegacyModel.getPeriod()
				+ "' AND substr(d.ddo_code,1,4)='" + locId.toString() + "' AND d.LOCATION_CODE='"
				+ messages.getLocId().toString() + "' order by  e.ddo_reg_no ");
		Query query = session.createNativeQuery(stringBuilder.toString());
		return query.getResultList();
	}

	@Override
	public String getBatchId(String treasuryCode) {
		Session session = entityManager.unwrap(Session.class);
		List temp = null;
		String data = "0";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder
				.append(" SELECT cast(max(substr(FILE_NAME,2,14)) as bigint)+1 FROM NSDL_BH where FILE_NAME like 'R"
						+ treasuryCode + "%' and  IS_LEGACY_DATA='Y'");
		Query lQuery = session.createNativeQuery(stringBuilder.toString());
		temp = lQuery.getResultList();
		if ((temp != null) && (temp.size() > 0) && (temp.get(0) != null)) {
			data = temp.get(0).toString();
		}
		return data;
	}

	@Override
	public List<Object[]> findDdoWiseTotalAmnt(String locId, String treasuryyno, String ddoCode) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT ");
		stringBuilder.append("COALESCE(SUM(b.emp_contri), 0.0) as emp_contri, ");
		stringBuilder.append("COALESCE(SUM(b.EMP_INT), 0.0) as EMP_INT, ");
		stringBuilder.append("COALESCE(SUM(b.EMPLOYER_CONTRI), 0.0) as EMPLOYER_CONTRI, ");
		stringBuilder.append("COALESCE(SUM(b.EMPLOYER_INT), 0.0) as EMPLOYER_INT ");
		stringBuilder.append("FROM EMPLOYEE_MST a ");
		stringBuilder.append("INNER JOIN DCPS_LEGACY_DATA b on a.sevaarth_id=b.SEVARTH_ID  ");
		stringBuilder.append("INNER JOIN RLT_ZP_DDO_MAP c on c.zp_ddo_code=a.ddo_code ");
		stringBuilder.append("INNER JOIN ORG_DDO_MST d on d.DDO_CODE = c.REPT_DDO_CODE ");
		stringBuilder.append("INNER JOIN MST_DTO_REG e on e.ddo_code = c.ZP_DDO_CODE ");
		if ((locId != null) && (locId.toString().equalsIgnoreCase("2222"))) {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		} else {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		}
		stringBuilder.append("INNER JOIN CMN_LOCATION_MST f on cast(f.LOC_ID as varchar)=substr(d.DDO_CODE,1,4) ");
		stringBuilder.append("WHERE   c.REPT_DDO_CODE='" + ddoCode
				+ "' AND  a.pran_no is not null AND a.is_active=1 AND  b.batch_id is  null  AND  a.dcps_gpf_flag='Y' ");
		stringBuilder.append("AND  b.STATUS = '1'  AND b.period = '" + treasuryyno + "' AND substr(d.ddo_code,1,4)='"
				+ locId.toString() + "'  group by  e.ddo_reg_no   order by  e.ddo_reg_no ");
		Query query = session.createNativeQuery(stringBuilder.toString());
		return query.getResultList();
	}

	@Override
	public String getEmployeeRecordCountDdoregNsdl(String locId, String treasuryyno, String ddoRegNo) {
		Session session = entityManager.unwrap(Session.class);
		List empLst = null;
		String empDdoLst = null;
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("select sum(a.count1)+sum(a.count2)  from  (SELECT ");
		stringBuilder.append("case when b.emp_contri = 0 then 0 else 1 end as count1, case when b.emp_int <= 0 then 0 else 1 end as count2  ");
		stringBuilder.append("FROM EMPLOYEE_MST a ");
		stringBuilder.append("INNER JOIN DCPS_LEGACY_DATA b on a.sevaarth_id=b.SEVARTH_ID  ");
		stringBuilder.append("INNER JOIN RLT_ZP_DDO_MAP c on c.zp_ddo_code=a.ddo_code ");
		stringBuilder.append("INNER JOIN ORG_DDO_MST d on d.DDO_CODE = c.REPT_DDO_CODE ");
		stringBuilder.append("INNER JOIN MST_DTO_REG e on e.ddo_code = c.ZP_DDO_CODE ");
		if ((locId != null) && (locId.toString().equalsIgnoreCase("2222"))) {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		} else {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		}
		stringBuilder.append("INNER JOIN CMN_LOCATION_MST f on cast(f.LOC_ID as varchar)=substr(d.DDO_CODE,1,4) ");
		stringBuilder.append("WHERE   e.DDO_REG_NO='" + ddoRegNo
				+ "' AND  a.pran_no is not null AND a.is_active=1 AND  b.batch_id is  null  AND  a.dcps_gpf_flag='Y' ");
		stringBuilder.append("AND  b.STATUS = '1'  AND b.period = '" + treasuryyno + "' AND substr(d.ddo_code,1,4)='"
				+ locId.toString() + "' group by  e.ddo_reg_no,b.emp_contri,b.emp_int   order by  e.ddo_reg_no) as a ");
		Query query = session.createNativeQuery(stringBuilder.toString());
		
		empLst = query.getResultList();
		if ((empLst != null) && (empLst.size() > 0)) {
			empDdoLst = empLst.get(0).toString();
		}
		return empDdoLst;
	}

	@Override
	public List<Object[]> getEmployeeListDdoregNsdl(String locId, String treasuryyno, String ddoRegNo) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT ");
		stringBuilder.append("COALESCE(SUM(b.emp_contri), 0.0) as emp_contri, ");
		stringBuilder.append("COALESCE(SUM(b.EMP_INT), 0.0) as EMP_INT, ");
		stringBuilder.append("COALESCE(SUM(b.EMPLOYER_CONTRI), 0.0) as EMPLOYER_CONTRI, ");
		stringBuilder.append("COALESCE(SUM(b.EMPLOYER_INT), 0.0) as EMPLOYER_INT ");
		stringBuilder.append("FROM EMPLOYEE_MST a ");
		stringBuilder.append("INNER JOIN DCPS_LEGACY_DATA b on a.sevaarth_id=b.SEVARTH_ID  ");
		stringBuilder.append("INNER JOIN RLT_ZP_DDO_MAP c on c.zp_ddo_code=a.ddo_code ");
		stringBuilder.append("INNER JOIN ORG_DDO_MST d on d.DDO_CODE = c.REPT_DDO_CODE ");
		stringBuilder.append("INNER JOIN MST_DTO_REG e on e.ddo_code = c.ZP_DDO_CODE ");
		if ((locId != null) && (locId.toString().equalsIgnoreCase("2222"))) {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		} else {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		}
		stringBuilder.append("INNER JOIN CMN_LOCATION_MST f on cast(f.LOC_ID as varchar)=substr(d.DDO_CODE,1,4) ");
		stringBuilder.append("WHERE   e.DDO_REG_NO='" + ddoRegNo
				+ "' AND  a.pran_no is not null AND a.is_active=1 AND  b.batch_id is  null  AND  a.dcps_gpf_flag='Y' ");
		stringBuilder.append("AND  b.STATUS = '1'  AND b.period = '" + treasuryyno + "' AND substr(d.ddo_code,1,4)='"
				+ locId.toString() + "'  order by  e.ddo_reg_no ");
		Query query = session.createNativeQuery(stringBuilder.toString());
		return query.getResultList();
	}

	
	@Override
	public List<Object[]> findDtoRegWiseAmnt(String locId, String treasuryyno, String ddoRegNo) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT ");
		stringBuilder.append("COALESCE(SUM(b.emp_contri), 0.0) as emp_contri, ");
		stringBuilder.append("COALESCE(SUM(b.emp_int), 0.0) as emp_int, ");
		stringBuilder.append("COALESCE(SUM(b.EMPLOYER_CONTRI), 0.0) as EMPLOYER_CONTRI, ");
		stringBuilder.append("COALESCE(SUM(b.EMPLOYER_INT), 0.0) as EMPLOYER_INT ");
		stringBuilder.append("FROM EMPLOYEE_MST a ");
		stringBuilder.append("INNER JOIN DCPS_LEGACY_DATA b on a.sevaarth_id=b.SEVARTH_ID  ");
		stringBuilder.append("INNER JOIN RLT_ZP_DDO_MAP c on c.zp_ddo_code=a.ddo_code ");
		stringBuilder.append("INNER JOIN ORG_DDO_MST d on d.DDO_CODE = c.REPT_DDO_CODE ");
		stringBuilder.append("INNER JOIN MST_DTO_REG e on e.ddo_code = c.ZP_DDO_CODE ");
		if ((locId != null) && (locId.toString().equalsIgnoreCase("2222"))) {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		} else {
			stringBuilder.append("  and substr(e.ddo_code,1,4) <> '2222' ");
		}
		stringBuilder.append("INNER JOIN CMN_LOCATION_MST f on cast(f.LOC_ID as varchar)=substr(d.DDO_CODE,1,4) ");
		stringBuilder.append("WHERE   e.DDO_REG_NO='" + ddoRegNo
				+ "' AND  a.pran_no is not null AND a.is_active=1 AND  b.batch_id is  null  AND  a.dcps_gpf_flag='Y' ");
		stringBuilder.append("AND  b.STATUS = '1'  AND b.period = '" + treasuryyno + "' AND substr(d.ddo_code,1,4)='"
				+ locId.toString() + "' group by  e.ddo_reg_no   order by  e.ddo_reg_no ");
		Query query = session.createNativeQuery(stringBuilder.toString());
		return query.getResultList();
	
	}
	
}
