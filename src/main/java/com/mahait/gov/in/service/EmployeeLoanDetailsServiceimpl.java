package com.mahait.gov.in.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpLoanModel;
import com.mahait.gov.in.repository.EmployeeLoanDetailsRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeLoanDetailsServiceimpl implements EmployeeLoanDetailsService {

	@Autowired
	EmployeeLoanDetailsRepo employeeLoanDetailsRepo;

	@Override
	public Long saveEmployeeLoanDtls(EmpLoanModel empLoanModel) {
		// TODO Auto-generated method stub
		LoanEmployeeDtlsEntity loanEmployeeDtlsEntity = new LoanEmployeeDtlsEntity();

		loanEmployeeDtlsEntity.setDepartmentallowdeduccode(empLoanModel.getDeptAllowdeducCode());
		loanEmployeeDtlsEntity.setCreateddate(new Date());
		loanEmployeeDtlsEntity.setLoanactivateflag(empLoanModel.getLoanStatus());
		loanEmployeeDtlsEntity.setLoanprinamt(empLoanModel.getLoanprinamt());
		loanEmployeeDtlsEntity.setLoantypeid(empLoanModel.getLoantypeid());
		loanEmployeeDtlsEntity.setLoaninterestamt(empLoanModel.getLoaninterestamt());
		loanEmployeeDtlsEntity.setLoanaccountno(empLoanModel.getLoanaccountno());
		loanEmployeeDtlsEntity.setLoanprininstno(empLoanModel.getLoanprininstno());
		loanEmployeeDtlsEntity.setLoanintinstno(empLoanModel.getLoanintinstno());
		loanEmployeeDtlsEntity.setLoanemiamt(empLoanModel.getLoanemiamt());
		loanEmployeeDtlsEntity.setLoansancorderno(empLoanModel.getLoansancorderno());
		loanEmployeeDtlsEntity.setLoansancorderdate(empLoanModel.getLoansancorderdate());
		loanEmployeeDtlsEntity.setVoucherno(empLoanModel.getVoucherno());
		loanEmployeeDtlsEntity.setVoucherdate(empLoanModel.getVoucherdate());
		loanEmployeeDtlsEntity.setOddinstamt(empLoanModel.getOddinstamt());
		loanEmployeeDtlsEntity.setLoanintemiamt(empLoanModel.getLoanintemiamt());
		loanEmployeeDtlsEntity.setLoanprinemiamt(empLoanModel.getLoanprinemiamt());
		loanEmployeeDtlsEntity.setCreatedby(BigInteger.valueOf(1l));
		loanEmployeeDtlsEntity.setCreatedbypost(BigInteger.valueOf(1l));
		loanEmployeeDtlsEntity.setSevaarthid(empLoanModel.getSevaarthId());
		loanEmployeeDtlsEntity.setLoanaccountno(empLoanModel.getAppNo());
		loanEmployeeDtlsEntity.setStartdate(empLoanModel.getLoanDate());
		// loanEmployeeDtlsEntity.setLoanactivateflag(loanactivateflag);

		Long saveId = 0l;

		if (empLoanModel.getLoanEmpAdvId() != null) {
			employeeLoanDetailsRepo.updateLoanDetail(loanEmployeeDtlsEntity);
			saveId = 1l;
		} else {
			saveId = employeeLoanDetailsRepo.saveEmployeeLoanDtls(loanEmployeeDtlsEntity);
		}

		return saveId;
	}

	@Override
	public List<EmpLoanModel> getEmpInfoBySevaarthId(String sevaarthId,String ddoCode) {
		// TODO Auto-generated method stub
		List<EmpLoanModel> listEmpLoanModel = new ArrayList<>();
		List<Object[]> lst = employeeLoanDetailsRepo.getEmpInfoBySevaarthId(sevaarthId,ddoCode);
		for (Object[] objects : lst) {
			EmpLoanModel empLoanModel = new EmpLoanModel();
			empLoanModel.setSevaarthId(StringHelperUtils.isNullString(objects[0]));
			empLoanModel.setDesignName(StringHelperUtils.isNullString(objects[1]));
			empLoanModel.setOfficeName(StringHelperUtils.isNullString(objects[2]));
			empLoanModel.setGpfNo(StringHelperUtils.isNullString(objects[3]));
			empLoanModel.setEmployeeName(StringHelperUtils.isNullString(objects[4]));
			listEmpLoanModel.add(empLoanModel);
		}
		return listEmpLoanModel;
	}

	@Override
	public List<EmpLoanModel> findAllEmpLoanDtls(String ddoCode) {
		// TODO Auto-generated method stub
		List<Object[]> lst = employeeLoanDetailsRepo.findAllEmpLoanDtls(ddoCode);
		List<EmpLoanModel> listEmpLoanModel = new ArrayList<>();
		for (Object[] object : lst) {
			EmpLoanModel empLoanModel = new EmpLoanModel();
			
			
			//a.employee_full_name_en,c.department_allowdeduc_col_nm,b.loan_prin_amt,b.loan_interest_amt,b.loan_emi_amt
			
			empLoanModel.setEmployeeName(StringHelperUtils.isNullString(object[0]));
			empLoanModel.setLoanAdvName(StringHelperUtils.isNullString(object[1]));
			BigDecimal loanPrinAmt = (BigDecimal) object[2];
			empLoanModel.setLoanprinamt(loanPrinAmt.doubleValue());
			BigDecimal loanInstAmt = (BigDecimal) object[3];
			empLoanModel.setLoaninterestamt(loanInstAmt.doubleValue());
			BigDecimal loanEmiAmt = (BigDecimal) object[4];
			empLoanModel.setLoanemiamt(loanEmiAmt.doubleValue());
			empLoanModel.setSevaarthId(StringHelperUtils.isNullString(object[5]));
			
			listEmpLoanModel.add(empLoanModel);
		}
		return listEmpLoanModel;
	}

	@Override
	public Integer checkloanAlreadyTaken(BigInteger empId, BigInteger advId) {
		// TODO Auto-generated method stub
		return employeeLoanDetailsRepo.checkloanAlreadyTaken(empId, advId);
	}

	@Override
	public List<LoanEmployeeDtlsEntity> getGpfAdvAppNo(String sevaarthId, OrgUserMst messages) {
		// TODO Auto-generated method stub
		return employeeLoanDetailsRepo.getGpfAdvAppNo(sevaarthId, messages);
	}

	@Override
	public List<LoanEmployeeDtlsEntity> getGpfAppTrnDtlsByAppId(String sevaarthId, Integer appId, OrgUserMst messages) {
		// TODO Auto-generated method stub
		return employeeLoanDetailsRepo.getGpfAppTrnDtlsByAppId(sevaarthId, appId, messages);
	}

	@Override
	public List<LoanEmployeeDtlsEntity> mergeMultipleGpfApp(String sevaarthId, OrgUserMst messages) {
		// TODO Auto-generated method stub
		return employeeLoanDetailsRepo.mergeMultipleGpfApp(sevaarthId, messages);
	}

	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> findLoanNames() {
		// TODO Auto-generated method stub
		return employeeLoanDetailsRepo.findLoanNames();
	}

	@Override
	public EmpLoanModel findSavedEmpLoanDtls(String sevaarthId) {
		// TODO Auto-generated method stub
		return employeeLoanDetailsRepo.findSavedEmpLoanDtls(sevaarthId);
	}

}
