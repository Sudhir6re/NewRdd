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
public class LegacyValidationRepoImpl implements LegacyValidationRepo {

	@PersistenceContext
	EntityManager entityManager;

	
	@Override
	public List<Object[]> findNsdlLegacyList(DcpsLegacyModel dcpsLegacyModel, OrgUserMst messages, Long locId) {
		Session session = entityManager.unwrap(Session.class);
		List legacyList = null;
		Query lQuery = null;

		if (dcpsLegacyModel.getYear().toString().length() == 2) {
			dcpsLegacyModel.setYear(Integer.parseInt(String.valueOf("20" + (dcpsLegacyModel.getYear() - 1))));
		}
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("Select distinct bh.file_name,bh.bh_emp_amount,bh.bh_emplr_amount,bh.transaction_id, ")
				.append("Case When bh.file_status = '0' then cast('File not validated' as varchar(20)) ")
				.append("When bh.file_status = '1' then cast('File is validated' as varchar(20)) ")
				.append("When bh.file_status = '2' then cast('File is rejected' as varchar(20)) ")
				.append("When bh.file_status = '5' then cast('Contribution file send' as varchar(25)) ")
				.append("When bh.file_status = '11' then cast('Transaction id updated' as varchar(25)) ")
				.append("When bh.file_status = '12' then cast('Bill locked' as varchar(25)) End, ")
				.append("bh.file_status,bh.bh_batch_fix_id,bh.voucher_no,bh.voucher_date,bh.bds_no,bh.bank_refno, ")
				.append("(Select lookup_name From cmn_lookup_mst Where cast(lookup_id as varchar) = ( ")
				.append("Select distinct period From dcps_legacy_data Where batch_id = substr(bh.file_name, 2, length(bh.file_name)) )) ")
				.append("From nsdl_bh bh ").append("Inner join rlt_zp_ddo_map as rlt ")
				.append("On rlt.zp_ddo_code = bh.ddo_code ")
				.append("Where bh.month = '" + dcpsLegacyModel.getMonth() + "' And bh.year = '"
						+ dcpsLegacyModel.getYear() + "' ")
				.append("And rlt.rept_ddo_code = '" + messages.getDdoCode() + "' And bh.file_name ilike 'R" + locId
						+ "%' And bh.status <> '-1' And bh.is_legacy_data = 'Y'");

		lQuery = session.createNativeQuery(queryBuilder.toString());
		legacyList = lQuery.getResultList();
		return legacyList;
	}

	@Override
	public String getDtoRegNo(String fileNumber) {
		Session session = entityManager.unwrap(Session.class);
		List temp = null;
		String data = "";
		StringBuilder Strbld = new StringBuilder();
		Strbld.append("SELECT DTO_REG_NO FROM NSDL_DH as a ");
		Strbld.append(
				"inner join MST_DTO_REG as b  on a.DH_DDO_REG_NO=b.DDO_REG_NO where a.FILE_NAME='" + fileNumber + "'");
		Query lQuery = session.createNativeQuery(Strbld.toString());
		temp = lQuery.getResultList();
		if ((temp != null) && (temp.size() > 0) && (temp.get(0) != null)) {
			data = temp.get(0).toString();
		}
		return data;
	}

	@Override
	public String getBatchData(String fileNumber) {
		Session session = entityManager.unwrap(Session.class);
		String lLstReturnList = "";
		StringBuilder sb = new StringBuilder();
		sb.append(
				" select SR_NO||'^'||HEADER_NAME||'^'||BH_NO||'^'||BH_COL2||'^'||BH_FIX_NO||'^'||BH_DATE||'^'||BH_BATCH_FIX_ID||'^^'||BH_DDO_COUNT||'^'||BH_PRAN_COUNT||'^'||BH_EMPLR_AMOUNT||'^'||BH_EMP_AMOUNT||'^^'||BH_TOTAL_AMT||'^' from NSDL_BH ");
		sb.append(" where FILE_NAME='" + fileNumber + "' and IS_LEGACY_DATA='Y' ");
		Query selectQuery = session.createNativeQuery(sb.toString());
		lLstReturnList = selectQuery.getSingleResult().toString();
		return lLstReturnList;
	}

	@Override
	public List<Object[]> getDHData(String fileNumber) {
		Session session = entityManager.unwrap(Session.class);
		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(
				" SELECT SR_NO||'^'||HEADER_NAME||'^'||DH_NO||'^'||DH_COL2||'^'||DH_DDO_REG_NO||'^'||BH_SD_COUNT||'^'||DH_EMPLR_AMOUNT||'^'||DH_EMP_AMOUNT||'^^',DH_DDO_REG_NO FROM NSDL_DH ");
		sb.append(" where FILE_NAME='" + fileNumber + "' and IS_LEGACY_DATA='Y' order by SR_NO asc");
		Query selectQuery = session.createNativeQuery(sb.toString());
		lLstReturnList = selectQuery.getResultList();
		return lLstReturnList;
	}

	@Override
	public List<String> getSDDtls(String fileNumber, String ddoRegNo) {
		Session session = entityManager.unwrap(Session.class);
		List lLstReturnList = null;
		StringBuilder sb = new StringBuilder();
		sb.append(
				" SELECT SR_NO||'^'||HEADER_NAME||'^'||SD_NO||'^'||SD_NO_2||'^'||SD_NO_3||'^'||SD_PRAN_NO||'^'||SD_EMPLR_AMOUNT||'^'||SD_EMP_AMOUNT||'^'||'^'||SD_TOTAL_AMT||'^'||SD_STATUS||'^'||SD_REMARK||'^' FROM NSDL_SD  ");
		sb.append(" where   FILE_NAME='" + fileNumber + "'and DDO_REG_NO='" + ddoRegNo
				+ "' and IS_LEGACY_DATA='Y' order by SR_NO asc ");
		Query selectQuery = session.createNativeQuery(sb.toString());
		lLstReturnList = selectQuery.getResultList();
		return lLstReturnList;
	}

	@Override
	public String getDdoCode(String batchId, String fileNo) {
		Session session = entityManager.unwrap(Session.class);
		List ddocode = null;

		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT DDO_CODE FROM NSDL_BH where BH_BATCH_FIX_ID='" + batchId + "' and FILE_NAME = '"
				+ fileNo + "' and IS_LEGACY_DATA='Y' ");

		Query lQuery = session.createNativeQuery(Strbld.toString());

		ddocode = lQuery.getResultList();
		if ((ddocode.size() > 0) || (ddocode != null)) {
			amountTotal = ddocode.get(0).toString();
		}

		return amountTotal;
	}

	@Override
	public String getFileStatus(String ddoCode, String fileNo, String batchId) {
		Session session = entityManager.unwrap(Session.class);
		List dtouserId = null;

		String file_status = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT file_status FROM NSDL_BH where FILE_NAME = '" + fileNo + "' and DDO_CODE = '"
				+ ddoCode + "' ");
		Query lQuery = session.createNativeQuery(Strbld.toString());
		dtouserId = lQuery.getResultList();
		if ((dtouserId.size() > 0) || (dtouserId != null)) {
			file_status = dtouserId.get(0).toString();
		}
		return file_status;
	}

	@Override
	public List<Object[]> getLegacyDataDetailsHis(String fileNo, String ddoCode, String batchId) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append("Select * from DCPS_LEGACY_DATA where BATCH_ID = '" + batchId + "' and ddo_code = '" + ddoCode + "' ");
		Query lQuery = session.createNativeQuery(sb.toString());
		return lQuery.getResultList();
	}

	@Override
	public void deleteFileDetails(String fileNo, String ddoCode, String batchId) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		StringBuffer str2 = new StringBuffer();
		str2.append("update NSDL_BH set status='-1' where FILE_NAME = '"+fileNo+"' and DDO_CODE = '"+ddoCode+"' and IS_LEGACY_DATA='Y'");
		Query query3 = session.createNativeQuery(str2.toString());
		query3.executeUpdate();
		

		StringBuffer str3 = new StringBuffer();
		str3.append("update NSDL_DH  set dh_status='-1' where FILE_NAME = '"+fileNo+"' and IS_LEGACY_DATA='Y'");
		Query query4 = session.createNativeQuery(str3.toString());
		query4.executeUpdate();
		

		StringBuffer str4 = new StringBuffer();
		str4.append("update NSDL_SD  set status='-1' where FILE_NAME = '"+fileNo+"' and IS_LEGACY_DATA='Y'");
		Query query5 = session.createNativeQuery(str4.toString());
		query5.executeUpdate();
		

		StringBuffer str5 = new StringBuffer();
		str5.append("Delete from DCPS_LEGACY_DATA where BATCH_ID = '"+batchId+"' and ddo_code = '"+ddoCode+"' ");
		Query query6 = session.createNativeQuery(str5.toString());
		query6.executeUpdate();
		
	}

	@Override
	public String getDdoCode(String fileno) {
		Session session = entityManager.unwrap(Session.class);
		List ddocode = null;
		
		String amountTotal = null;
		StringBuilder Strbld = new StringBuilder();

		Strbld.append(" SELECT DDO_CODE FROM NSDL_BH where FILE_NAME = '"+fileno+"' ");

		Query lQuery = session.createNativeQuery(Strbld.toString());

		ddocode = lQuery.getResultList();
		if ((ddocode.size() > 0) || (ddocode != null)) {
			amountTotal = ddocode.get(0).toString();
		}

		return amountTotal;
}

	@Override
	public void updateFileStatus(int fileStatus, String fileno, String errorData) {
		Session session = entityManager.unwrap(Session.class);
		StringBuilder sb = new StringBuilder();
		errorData = errorData.replace("'", "");
		sb.append("  update NSDL_BH set file_status='" + fileStatus
				+ "'  ");
		if ((errorData != null) && (!errorData.equals(""))) {
			sb.append(" , error_data='" + errorData + "' ");
		}
		sb.append("   where FILE_NAME='" + fileno + "' and IS_LEGACY_DATA='Y' ");
		Query updateQuery = session.createNativeQuery(sb.toString());
		updateQuery.executeUpdate();		
	}

}
