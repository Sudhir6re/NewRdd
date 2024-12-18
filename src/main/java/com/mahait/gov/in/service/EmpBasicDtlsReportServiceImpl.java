package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.model.RegularReportModel;
import com.mahait.gov.in.repository.EmpBasicDtlsReportRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service

public class EmpBasicDtlsReportServiceImpl implements EmpBasicDtlsReportService{

	@Autowired
	EmpBasicDtlsReportRepo empBasicDtlsReportRepo;
	
	@Override
	public List<MstEmployeeEntity> lstDDOWiseEmp(String ddoCode) {
		// TODO Auto-generated method stub
		return empBasicDtlsReportRepo.lstDDOWiseEmp(ddoCode);
	}

	@Override
	public List<RegularReportModel> findEmpBasicDtls(Integer yearId, Integer monthId, Long billGroup, String ddoCode,String sevaarthId) {
		
		List<Object[]> lstprop = empBasicDtlsReportRepo.findEmpBasicDtls(yearId,monthId,billGroup,ddoCode,sevaarthId);
		List<RegularReportModel> lstObj = new ArrayList<>();
		
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				RegularReportModel obj = new RegularReportModel();
			/*	a.employee_full_name_en,d.designation_name, case when a.dcps_gpf_flag = 'Y' then a.dcps_no else a.pfdescription
						end as gpfdcpsno,a.pan_no,B.bank_account_no,f.bank_branch_name*/
				obj.setName(StringHelperUtils.isNullString(objLst[0]));
				obj.setDesgName(StringHelperUtils.isNullString(objLst[1]));
				obj.setDcpsNo(StringHelperUtils.isNullString(objLst[2]));
				obj.setPanNo(StringHelperUtils.isNullString(objLst[3]));
				obj.setBankAccNo(StringHelperUtils.isNullString(objLst[4]));
				obj.setBranchName(StringHelperUtils.isNullString(objLst[5]));
				
				lstObj.add(obj);
			}
		}
		return lstObj;
	
	}

}
