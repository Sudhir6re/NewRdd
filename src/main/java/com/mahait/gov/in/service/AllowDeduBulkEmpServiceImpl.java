package com.mahait.gov.in.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.repository.AllowDeduBulkEmpRepo;

import jakarta.transaction.Transactional;

@Transactional
@Service
public class AllowDeduBulkEmpServiceImpl implements AllowDeduBulkEmpService {
	
	@Autowired
	AllowDeduBulkEmpRepo allowDeduBulkEmpRepo;
	

	@Override
	public int updateAllowDeductBulkEmplComp(
			DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,String ddoCode) {
		return allowDeduBulkEmpRepo.updateAllowDeductBulkEmplComp(deptEligibilityForAllowAndDeductModel,ddoCode);
	}


	@Override
	public void checkComponentAlreadyPresent(
			DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,String ddoCode) {
		allowDeduBulkEmpRepo.checkComponentAlreadyPresent(deptEligibilityForAllowAndDeductModel,ddoCode);
	}


	@Override
	public List<MstEmployeeModel> getListEmpBySchemBillGroup(String userName, Long schemeBillGrpId,
			Integer departmentAllowdeducCode) {
		List<Object[]> lstprop = allowDeduBulkEmpRepo.getListEmpBySchemBillGroup(userName,schemeBillGrpId,departmentAllowdeducCode);
		List<MstEmployeeModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstEmployeeModel obj = new MstEmployeeModel();
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[0]));
				obj.setEmployeeFullName(StringHelperUtils.isNullString(objLst[1]));
				obj.setBillDesc(StringHelperUtils.isNullString(objLst[9]));
				obj.setEmployeeId(StringHelperUtils.isNullBigInteger(objLst[4]).longValue());
				obj.setEmpMapped(StringHelperUtils.isNullBigInteger(objLst[10]).longValue());
				lstObj.add(obj);
			}
		}
		return lstObj;
	}


	@Override
	public List<Object[]> findpaybill(String billNumber, String userName) {
		return allowDeduBulkEmpRepo.findpaybill(billNumber,userName);
	}


	@Override
	public List<MstEmployeeModel> findAllEmployeesByDDOName(String ddoCode) {
		List<Object[]> lstprop = allowDeduBulkEmpRepo.findAllEmployeesByDDOName(ddoCode);
		List<MstEmployeeModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstEmployeeModel obj = new MstEmployeeModel();
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[0]));
				obj.setEmployeeFullName(StringHelperUtils.isNullString(objLst[1]));
				obj.setBillDesc(StringHelperUtils.isNullString(objLst[9]));
				obj.setEmployeeId(StringHelperUtils.isNullBigInteger(objLst[4]).longValue());
			//	obj.setEmpMapped(StringHelperUtils.isNullLong(objLst[10]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

}
