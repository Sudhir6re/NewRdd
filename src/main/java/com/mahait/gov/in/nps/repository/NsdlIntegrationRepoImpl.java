package com.mahait.gov.in.nps.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class NsdlIntegrationRepoImpl implements NsdlIntegrationRepo{
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<TrnNpsRegFileEntity> getBatchList() {
		List<TrnNpsRegFileEntity> lst=new ArrayList<TrnNpsRegFileEntity>();
		String sqlQuery="SELECT DISTINCT ON (batch_fixed_id) batch_fixed_id,nsdl_ref_no,total_emp_in_batch,ack_no,is_pran_generated,created_date,id,nsdl_status_code FROM trn_nps_reg_file ORDER BY batch_fixed_id";
		Session session=entityManager.unwrap(Session.class);
		Query query=session.createNativeQuery(sqlQuery);
		List<Object[]> lstObj=query.getResultList();
		for(Object obj[]:lstObj ) {
			 Integer  batchFixId=StringHelperUtils.isNullInt(obj[0]);
			 Integer nsdlRefNo=StringHelperUtils.isNullInt(obj[1]);
			 Integer totalEmpInBatch=StringHelperUtils.isNullInt(obj[2]);
			 BigInteger ackNo=StringHelperUtils.isNullBigInteger(obj[3]);
			// Integer npsId=StringHelperUtils.isNullInt(obj[1]);
			 //Integer refSeq=StringHelperUtils.isNullInt(obj[3]);
			 Integer isPranGenerated=StringHelperUtils.isNullInt(obj[4]);
			 Date createdDate=StringHelperUtils.isNullDate(obj[5]);
			 
			 TrnNpsRegFileEntity trnNpsRegFileEntity=new TrnNpsRegFileEntity();
			 trnNpsRegFileEntity.setBatchFixId(batchFixId.intValue());
			 trnNpsRegFileEntity.setNsdlRefNo(nsdlRefNo);
			 trnNpsRegFileEntity.setTotalEmpInBatch(totalEmpInBatch);
			 trnNpsRegFileEntity.setAckNo(ackNo);
			 trnNpsRegFileEntity.setIsPranGenerated(isPranGenerated);
			 trnNpsRegFileEntity.setCreatedDate(createdDate);
			 trnNpsRegFileEntity.setId(StringHelperUtils.isNullInt(obj[6]));
			 trnNpsRegFileEntity.setNsdlStatusCode(StringHelperUtils.isNullString(obj[7]));
			 lst.add(trnNpsRegFileEntity);
		}
		return lst;
	}

	@Override
	public List<MstEmployeeNPSEntity> findNpsEmpDtlById(int id) {
		String sqlQuery="select a.ddo_code,a.sevaarth_id,a.pran_no,a.employee_full_name,b.ack_no,a.employee_id from employee_nps_mst a inner join trn_nps_reg_file b on a.employee_nps_id=b.nps_id where b.batch_fixed_id="+id;
		List<MstEmployeeNPSEntity> lstMstEmployeeNPSEntity=new ArrayList<MstEmployeeNPSEntity>();
		Session session=entityManager.unwrap(Session.class);
		Query query=session.createNativeQuery(sqlQuery);
		List<Object[]> lstObj=query.getResultList();
		for(Object obj[]:lstObj ) {
			String ddoCode=StringHelperUtils.isNullString(obj[0]);
			String sevaarthId=StringHelperUtils.isNullString(obj[1]);
			String pranNo=StringHelperUtils.isNullString(obj[2]);
			String empFName=StringHelperUtils.isNullString(obj[3]);
			BigInteger ackNo=StringHelperUtils.isNullBigInteger(obj[4]);
			Long employeeId=StringHelperUtils.isNullLong(obj[5]);
			
			MstEmployeeNPSEntity mstEmployeeNPSEntity=new MstEmployeeNPSEntity();
			mstEmployeeNPSEntity.setDdoCode(ddoCode);
			mstEmployeeNPSEntity.setSevaarthId(sevaarthId);
			mstEmployeeNPSEntity.setPranNo(pranNo);
			mstEmployeeNPSEntity.setEmployeeFullName(empFName);
			mstEmployeeNPSEntity.setAckNo(ackNo);
			mstEmployeeNPSEntity.setEmployeeId(employeeId);
			lstMstEmployeeNPSEntity.add(mstEmployeeNPSEntity);
		}
		return lstMstEmployeeNPSEntity;
	}


}
