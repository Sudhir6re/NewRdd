package com.mahait.gov.in.nps.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.nps.entity.FormS1DetailsEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
@SuppressWarnings("unchecked")
public class CSRFFormRepositoryImplOLD implements CSRFFormRepositoryOLD {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Object[]> findAllEmployees(String ddoCode) {
		Session currentSession = entityManager.unwrap(Session.class);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT a.sevaarth_id, a.employee_full_name_en, to_char(a.doj, 'dd/MM/YYYY') as DOJ, c.designation_name, d.zp_ddo_code, e.ddo_office, f.dcps_no, a.employee_id ");
		sb.append(" FROM employee_mst a inner JOIN designation_mst c on c.designation_code = a.designation_code ");
		sb.append(" INNER JOIN  rlt_zp_ddo_map d on d.zp_ddo_code = a.ddo_code ");
		sb.append(" INNER JOIN in org_ddo_mst e on d.zp_ddo_code=e.ddo_code ");
		sb.append(" LEFT JOIN dcps_details_mst f on a.sevaarth_id=f.sevaarth_id and f.is_active='Y' ");
		sb.append(
				" WHERE a.billgroup_id is not null and dcps_gpf_flag ='Y' and a.is_mapped_with_nps='0' and a.is_active='1' and d.rept_ddo_code=:rept_ddo_code");
		sb.append(
				" and a.sevaarth_id not in ( select sevarth_id from frm_form_s1_dtls_1 ) ORDER BY a.employee_full_name_en");

		Query query = currentSession.createNativeQuery(sb.toString()); 
		query.setParameter("rept_ddo_code", ddoCode);
		
		
		
		return query.list();

	}

	@Override
	public FormS1DetailsEntity findEmployeeBySevaarthId(int empId) {
		TypedQuery<FormS1DetailsEntity> query = entityManager.createQuery(
				"SELECT a FROM FormS1DetailsEntity a INNER JOIN a.mstNomineeDetailsEntity b  where a.employeeId=" + empId,
				FormS1DetailsEntity.class);
		List<FormS1DetailsEntity> resultList = query.getResultList();

		FormS1DetailsEntity formS1DetailsEntity = new FormS1DetailsEntity();

		if (resultList.size() > 0) {
			formS1DetailsEntity = resultList.get(0);
		} else {
			formS1DetailsEntity = null;
		}

		return formS1DetailsEntity;
	}

	@Override
	public MstEmployeeEntity findEmployeeDtlsBySevaarthId(int empId) {
		MstEmployeeEntity mstEmployeeEntity = null;

		Session currentSession = entityManager.unwrap(Session.class);
		String HQL = "FROM MstNomineeDetailsEntity as t where t.employeeId='" + empId + "' ";
		List<MstEmployeeEntity> lstEmployeeDetailEntity = (List<MstEmployeeEntity>) entityManager.createQuery(HQL)
				.getResultList();
		if (lstEmployeeDetailEntity.size() > 0) {
			mstEmployeeEntity = lstEmployeeDetailEntity.get(0);
		} else {
			lstEmployeeDetailEntity = null;
		}

		return mstEmployeeEntity;
	}
}
