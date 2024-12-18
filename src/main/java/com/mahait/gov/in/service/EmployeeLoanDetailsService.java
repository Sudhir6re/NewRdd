package com.mahait.gov.in.service;

import java.util.List;

import java.math.BigInteger;
import java.util.List;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpLoanModel;

public interface EmployeeLoanDetailsService {
	
	List<EmpLoanModel> findAllEmpLoanDtls(String ddoCode);
	Long saveEmployeeLoanDtls(EmpLoanModel empLoanModel);

	List<EmpLoanModel> getEmpInfoBySevaarthId(String sevaarthId, String ddoCode);


	Integer checkloanAlreadyTaken(BigInteger empId, BigInteger advId);

	List<LoanEmployeeDtlsEntity> getGpfAdvAppNo(String sevaarthId, OrgUserMst messages);

	List<LoanEmployeeDtlsEntity> getGpfAppTrnDtlsByAppId(String sevaarthId, Integer appId, OrgUserMst messages);

	List<LoanEmployeeDtlsEntity> mergeMultipleGpfApp(String sevaarthId, OrgUserMst messages);

	List<DeptEligibilityForAllowAndDeductEntity> findLoanNames();

	EmpLoanModel findSavedEmpLoanDtls(String sevaarthId);

}
