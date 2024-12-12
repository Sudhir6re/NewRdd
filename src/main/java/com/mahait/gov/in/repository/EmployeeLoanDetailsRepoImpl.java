package com.mahait.gov.in.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpLoanModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class EmployeeLoanDetailsRepoImpl implements EmployeeLoanDetailsRepo {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Long saveEmployeeLoanDtls(LoanEmployeeDtlsEntity loanEmployeeDtlsEntity) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		return (Long) session.save(loanEmployeeDtlsEntity);
	}

	@Override
	public List<Object[]> getEmpInfoBySevaarthId(String sevaarthId, String ddoCode) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		String queryString = " select a.sevaarth_id,b.designation_name,c.ddo_office,a.pfacno,a.employee_full_name_en from employee_mst a inner join designation_mst b on a.designation_code=b.designation_code "
				+ " inner join org_ddo_mst c on c.ddo_code=a.ddo_code where a.sevaarth_id || a.employee_full_name_en "
				+ " ilike '%" + sevaarthId + "%' and dcps_gpf_flag = 'N' and a.ddo_code = '" + ddoCode + "'";
		Query query = session.createNativeQuery(queryString);
		System.out.println("-------" + queryString);
		return query.getResultList();
	}

	@Override
	public List<Object[]> findAllEmpLoanDtls(String ddoCode) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		String queryString = " select a.employee_full_name_en,c.department_allowdeduc_col_nm,b.loan_prin_amt,b.loan_interest_amt,b.loan_prin_emi_amt,a.sevaarth_id from employee_mst a inner join loan_employee_dtls b on a.sevaarth_id = b.sevaarth_id "
				+ " inner join department_allowdeduc_mst c on c.department_allowdeduc_code=b.department_allowdeduc_code where a.ddo_code ='"
				+ ddoCode + "'";
		Query query = session.createNativeQuery(queryString);
		return query.getResultList();
	}

	@Override
	public Integer checkloanAlreadyTaken(BigInteger empId, BigInteger advId) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		String queryString = "select * from loan_employee_dtls where loan_type_id=" + empId + " and employee_id="
				+ advId;
		Query query = session.createNativeQuery(queryString);
		return query.getResultList().size();
	}

	@Override
	public List<LoanEmployeeDtlsEntity> getGpfAdvAppNo(String sevaarthId, OrgUserMst messages) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		String hqlQuery = "From LoanEmployeeDtlsEntity as t where t.sevaarthid='" + sevaarthId + "'"; // and
																										// t.loan_activate_flag=1";
		System.out.println(hqlQuery);
		return (List<LoanEmployeeDtlsEntity>) entityManager.createQuery(hqlQuery).getResultList();
	}

	@Override
	public List<LoanEmployeeDtlsEntity> getGpfAppTrnDtlsByAppId(String sevaarthId, Integer appId, OrgUserMst messages) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		String hqlQuery = "From LoanEmployeeDtlsEntity as t where t.sevaarthid='" + sevaarthId + "'  and t.id=" + appId; // and
																															// t.loanactivateflag=1
		return (List<LoanEmployeeDtlsEntity>) entityManager.createQuery(hqlQuery).getResultList();
	}

	@Override
	public LoanEmployeeDtlsEntity findLoanDetailsById(Integer loanEmpAdvId) {
		// TODO Auto-generated method stub
		return entityManager.find(LoanEmployeeDtlsEntity.class, loanEmpAdvId);
	}

	@Override
	public void updateLoanDetail(LoanEmployeeDtlsEntity loanEmployeeDtlsEntity) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		session.update(loanEmployeeDtlsEntity);
	}

	@Override
	public List<LoanEmployeeDtlsEntity> mergeMultipleGpfApp(String sevaarthId, OrgUserMst messages) {
		Session session = entityManager.unwrap(Session.class);
		String hqlQuery = "From LoanEmployeeDtlsEntity as t where t.sevaarthid='" + sevaarthId
				+ "' and t.loanactivateflag=1"; // and loanprininstno
		return (List<LoanEmployeeDtlsEntity>) entityManager.createQuery(hqlQuery).getResultList();
	}

	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> findLoanNames() {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		String hqlQuery = "From DeptEligibilityForAllowAndDeductEntity as t where t.isLoanAdv = 1"; // and
																																		// t.loan_activate_flag=1";
		System.out.println(hqlQuery);
		return (List<DeptEligibilityForAllowAndDeductEntity>) entityManager.createQuery(hqlQuery).getResultList();
	}

	@Override
	public EmpLoanModel findSavedEmpLoanDtls(String sevaarthId) {

		Session currentSession = entityManager.unwrap(Session.class);

		String HQL = " select a.sevaarth_id,b.designation_name,c.ddo_office,a.pfacno,a.employee_full_name_en,d.department_allowdeduc_code,d.loan_prin_amt, "
				+ " d.loan_interest_amt,d.start_date,d.loan_prin_inst_no,d.loan_int_inst_no,d.loan_account_no,d.loan_prin_emi_amt,d.loan_int_emi_amt,d.loan_sanc_order_no,"
				+ " d.loan_sanc_order_date,d.total_recovered_inst,d.odd_inst_no,d.odd_inst_amt,d.voucher_no,d.voucher_date "
				+ " from employee_mst a inner join designation_mst b on a.designation_code=b.designation_code "
				+ " inner join org_ddo_mst c on c.ddo_code=a.ddo_code "
				+ " inner join  loan_employee_dtls d on a.sevaarth_id = d.sevaarth_id "
				+ " inner join department_allowdeduc_mst e on e.department_allowdeduc_code=d.department_allowdeduc_code "
				+ " where a.sevaarth_id = '" + sevaarthId + "'";

		Query query = currentSession.createNativeQuery(HQL);
		List<Object[]> lstprop = query.getResultList();
		EmpLoanModel lstObj = new EmpLoanModel();
		int i = 1;
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				lstObj.setSevaarthId(StringHelperUtils.isNullString(objLst[0]));
				lstObj.setDesignName(StringHelperUtils.isNullString(objLst[1]));
				lstObj.setOfficeName(StringHelperUtils.isNullString(objLst[2]));
				lstObj.setGpfNo(StringHelperUtils.isNullString(objLst[3]));
				lstObj.setEmployeeName(StringHelperUtils.isNullString(objLst[4]));
				int deptAllowdedCode = (int) objLst[5];
				lstObj.setDeptAllowdeducCode(Long.valueOf(deptAllowdedCode));
				BigDecimal loanPrinAmt = (BigDecimal) objLst[6];

				lstObj.setLoanprinamt(loanPrinAmt.doubleValue());

				BigDecimal loanIntPrinAmt = (BigDecimal) objLst[7];

				lstObj.setLoaninterestamt(loanIntPrinAmt.doubleValue());
				lstObj.setLoanDate(StringHelperUtils.isNullDate(objLst[8]));
				lstObj.setLoanprininstno(StringHelperUtils.isNullInt(objLst[9]));
				lstObj.setLoanintinstno(StringHelperUtils.isNullInt(objLst[10]));
				lstObj.setLoanaccountno(StringHelperUtils.isNullString(objLst[11]));
				if (objLst[12] != null) {
					BigDecimal loanPrinEmiAmt = (BigDecimal) objLst[12];
					lstObj.setLoanprinemiamt(loanPrinEmiAmt.doubleValue());
				}
				if (objLst[13] != null) {
					BigDecimal loanIntPrinEmiAmt = (BigDecimal) objLst[13];

					lstObj.setLoanintemiamt(loanIntPrinEmiAmt.doubleValue());
				}
				lstObj.setLoansancorderno(StringHelperUtils.isNullString(objLst[14]));
				lstObj.setLoansancorderdate(StringHelperUtils.isNullDate(objLst[15]));
				lstObj.setTotalRecoveredInstallment(StringHelperUtils.isNullInt(objLst[16]));
				if (objLst[17] != null) {
					BigDecimal oddinstNo = (BigDecimal) objLst[17];
					lstObj.setOddinstno(oddinstNo.doubleValue());
				}
				if (objLst[18] != null) {
					BigDecimal oddinstAmt = (BigDecimal) objLst[18];
					lstObj.setOddinstamt(oddinstAmt.doubleValue());
				}

				lstObj.setVoucherno(StringHelperUtils.isNullString(objLst[19]));
				lstObj.setVoucherdate(StringHelperUtils.isNullDate(objLst[20]));

				i++;
			}
		}
		return lstObj;
	}

}
