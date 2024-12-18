package com.mahait.gov.in.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.AnnualIncrementModel;
import com.mahait.gov.in.repository.EmployeeIncrementRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class EmployeeIncrementServiceImpl implements EmployeeIncrementService{

	
	@Autowired
	EmployeeIncrementRepo employeeIncrementRepo;
	

	@Override
	public String officeName(String userName) {
		return employeeIncrementRepo.officeName(userName);
	}

	@Override
	public List<AnnualIncrementModel> lstEmpforMTR21(String orderNo,int roleId,String ddoCode) {///, String locId
		List<Object[]> lstprop = employeeIncrementRepo.lstEmpforMTR21(orderNo,roleId,ddoCode);//,locId
		List<AnnualIncrementModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				AnnualIncrementModel obj = new AnnualIncrementModel();
				//o.EMP_FNAME,o.EMP_MNAME,o.EMP_LNAME,d.DSGN_NAME,fix.PAY_FIX_DATE,fix.OLD_BASIC,fix.NEW_BASIC,fix.NXT_INCR_DATE,fix.INCR_ORDER_DATE,fix.PAY_COMMISSION_ID
				String empName=objLst[0]+" "+objLst[1]+" "+objLst[2]; 
				obj.setEmpname(empName);
				obj.setPostName(StringHelperUtils.isNullString(objLst[3]));
				obj.setPresentIncreDate(StringHelperUtils.isNullDate(objLst[4]));
				obj.setCurrbasic(StringHelperUtils.isNullInt(objLst[5]));
				obj.setIncrBasic(StringHelperUtils.isNullInt(objLst[6]));
				obj.setIncrDate(StringHelperUtils.isNullDate(objLst[8]));
				obj.setPayCommId(StringHelperUtils.isNullInt(objLst[9]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}



	@Override
	public List<AnnualIncrementModel> getIncrementDataForReptDDO(String userName, String currYear) {
		List<Object[]> lstprop = employeeIncrementRepo.getIncrementDataForReptDDO(userName,currYear);
		List<AnnualIncrementModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				AnnualIncrementModel obj = new AnnualIncrementModel();
		        obj.setOrderNo(StringHelperUtils.isNullString(objLst[0]));
				obj.setIncrementOrderDate(StringHelperUtils.isNullDate(objLst[1]));
				obj.setIsActive(StringHelperUtils.isNullChar(objLst[2]));
				obj.setDdoCode(StringHelperUtils.isNullString(objLst[3]));
				obj.setOfficeName(StringHelperUtils.isNullString(objLst[4]));
				// obj.setBasicPayIncrementId(StringHelperUtils.isNullInt(objLst[4]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public int approveAnnualIncrement(@Valid AnnualIncrementModel annualIncrementModel, OrgUserMst messages) {
		int id = 0;
		List<EmployeeIncrementEntity> obj = employeeIncrementRepo.findEmp(annualIncrementModel.getOrderNo());
		if (obj.size() > 0) {
			for (EmployeeIncrementEntity employeeIncrementEntity : obj) {

				employeeIncrementEntity.setIsActive('2');
				employeeIncrementEntity.setUpdatedDate(new Date());
				employeeIncrementEntity.setUpdatedUserId(messages.getUserId());

				MstEmployeeEntity mstEmployeeEntity = employeeIncrementRepo
						.findEmpByEmpId(employeeIncrementEntity.getEmployeeId());
				if (mstEmployeeEntity.getPayCommissionCode() == 8) { // seven pc
					mstEmployeeEntity.setSevenPcBasic(employeeIncrementEntity.getIncrementBasicPaySal().doubleValue());
				} else if (mstEmployeeEntity.getPayCommissionCode() == 2) { // six pay basic
					mstEmployeeEntity.setBasicPay(employeeIncrementEntity.getIncrementBasicPaySal().doubleValue());
				}

				employeeIncrementRepo.updateEmpBasicPay(mstEmployeeEntity);

				Serializable approveGPF = employeeIncrementRepo.approveAnnualIncrement(employeeIncrementEntity);
				id = (int) approveGPF;
			}
		}
		return id;
	}


}
