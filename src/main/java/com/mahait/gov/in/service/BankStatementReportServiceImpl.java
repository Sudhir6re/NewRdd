package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.BankStatementReportModel;
import com.mahait.gov.in.repository.BankStatementReportRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class BankStatementReportServiceImpl implements BankStatementReportService{
	
	@Autowired
	
	BankStatementReportRepo bankStatementReportRepo;

	@Override
	public List<BankStatementReportModel> findbankstatementinfo(int monthName, int yearName, String ddoCode,
			String billNo) {
		
		List<Object[]> lstprop = bankStatementReportRepo.getAbstractReport(monthName,yearName,ddoCode,billNo);
		List<BankStatementReportModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				BankStatementReportModel obj = new BankStatementReportModel();
				///bank_acnt_no,bank_name,bank_branch_name,employee_full_name_en as employee,total_net_amt,bbm.ifsc_code
				obj.setAccno(StringHelperUtils.isNullString(objLst[0]));
				obj.setBankname(StringHelperUtils.isNullString(objLst[1]));
				obj.setBankBranchName(StringHelperUtils.isNullString(objLst[2]));
				obj.setEmpName(StringHelperUtils.isNullString(objLst[3]));
				obj.setNetamt(StringHelperUtils.isNullDouble(objLst[4]));
				obj.setIfsccode(StringHelperUtils.isNullString(objLst[5]));
			   
				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}

}
