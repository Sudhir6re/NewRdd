package com.mahait.gov.in.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.DeptEligibilityForAllowAndDeductEntity;
import com.mahait.gov.in.entity.EmployeeAllowDeducComponentAmtEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.DeptEligibilityForAllowAndDeductModel;
import com.mahait.gov.in.model.EmployeeAllowDeducComponentAmtModel;
import com.mahait.gov.in.repository.DeptEligibilityForAllowAndDeductRepo;

import jakarta.validation.Valid;

@Service
@Transactional
public class DeptEligibilityForAllowAndDeductServiceImpl implements DeptEligibilityForAllowAndDeductService{
	
	@Autowired
	DeptEligibilityForAllowAndDeductRepo deptEligibilityForAllowAndDeductRepo;
	
	@Override
	public List<DeptEligibilityForAllowAndDeductEntity> findDeptEligibilityForAllowAndDeductList() {
		return deptEligibilityForAllowAndDeductRepo.findDeptEligibilityForAllowAndDeductList();
	}

		@Override
		public int deleteMpgDdoAllowDeduc( int action, Object object) {
			deptEligibilityForAllowAndDeductRepo.deleteMpgDdoAllowDeduc(action,object);
			return 0;
		}

		@Override
		public int saveMpgDdoAllowDeduc(Object object, int action, Object[] serialid, String effectiveDate,
				Object object2) {
			deptEligibilityForAllowAndDeductRepo.saveEmpMpgDdoAllowDeduc(object,action,serialid,effectiveDate,object2);
			return 0;
		}
		
		@Override
		public List<Object[]> findallowDeductLevel2(String ddoCode2) {
			List<Object[]> deptEligibilityForAllowAndDeductEntity = deptEligibilityForAllowAndDeductRepo.findallowDeductLevel2(ddoCode2);
			return deptEligibilityForAllowAndDeductEntity;
		}
		
		@Override
		public List<Object[]> findlevel1DDOAgaintlevel2(String userName) {
			List<Object[]> deptEligibilityForAllowAndDeductEntity = deptEligibilityForAllowAndDeductRepo.findlevel1DDOAgaintlevel2(userName);
			return deptEligibilityForAllowAndDeductEntity;
		}

		@Override
		public List<DeptEligibilityForAllowAndDeductEntity> findDeptNonGovDeductList() {
			return deptEligibilityForAllowAndDeductRepo.findDeptNonGovDeductList();
		}

		@Override
		public int saveEmployeeNonGovDuesDeduct(
				@Valid EmployeeAllowDeducComponentAmtModel employeeAllowDeducComponentAmtModel, OrgUserMst messages) {
//			EmployeeAllowDeducComponentAmtEntity[] employeeAllowDeducComponentAmtEntityarr=new EmployeeAllowDeducComponentAmtEntity[]();
			int id = 0;
			/*EmployeeAllowDeducComponentAmtEntity[] lArrempAllDedCompEntity = new EmployeeAllowDeducComponentAmtEntity[employeeAllowDeducComponentAmtModel.getNetAmt().length];*/
			
		for (int i = 0; i < employeeAllowDeducComponentAmtModel.getNetAmt().length; i++) {
			if (employeeAllowDeducComponentAmtModel.getNetAmt()[i]>= 0.0) {
				EmployeeAllowDeducComponentAmtEntity empAllDedCompEntity =new EmployeeAllowDeducComponentAmtEntity();
				EmployeeAllowDeducComponentAmtEntity empdata = deptEligibilityForAllowAndDeductRepo.findMstDeptByDeptId(
						employeeAllowDeducComponentAmtModel.getSevaarthId()[i],
						employeeAllowDeducComponentAmtModel.getDeptallowcode());
				if (empdata != null) {
					empdata.setExistingAmt(empdata.getNetAmt());
					empdata.setNetAmt(employeeAllowDeducComponentAmtModel.getNetAmt()[i]);
					empdata.setUpdatedDate(new Date());
					empdata.setUpdatedUserId(messages.getUserId());
					deptEligibilityForAllowAndDeductRepo.updateComponent(empdata);
					id = 1;
					
				} else {
					empAllDedCompEntity
							.setEmpName(employeeAllowDeducComponentAmtModel.getEmpName()[i]);
					empAllDedCompEntity
							.setEmployeeId(employeeAllowDeducComponentAmtModel.getEmployeeId());
					empAllDedCompEntity
							.setSevaarthId(employeeAllowDeducComponentAmtModel.getSevaarthId()[i]);
					empAllDedCompEntity.setIsType(employeeAllowDeducComponentAmtModel.getIsType());
					empAllDedCompEntity.setNetAmt(employeeAllowDeducComponentAmtModel.getNetAmt()[i]);
					empAllDedCompEntity.setIsActive('1');
					empAllDedCompEntity.setDdoCode(employeeAllowDeducComponentAmtModel.getDdoCode());
					empAllDedCompEntity
							.setDepartmentAllowDeducId(employeeAllowDeducComponentAmtModel.getDepartmentAllowDeducId());
					empAllDedCompEntity
							.setDeptallowcode(employeeAllowDeducComponentAmtModel.getDeptallowcode());
					empAllDedCompEntity.setDeptcode(employeeAllowDeducComponentAmtModel.getDeptcode());
					empAllDedCompEntity.setCreatedDate(new Date());
					empAllDedCompEntity.setCreatedUserId(messages.getUserId());
					
					/*lArrempAllDedCompEntity[i]=empAllDedCompEntity;*/
					
					Serializable  saveEmployeeNonGovDuesDeduct= deptEligibilityForAllowAndDeductRepo.saveEmployeeNonGovDuesDeduct(empAllDedCompEntity);
					id = (int) saveEmployeeNonGovDuesDeduct;
						
				}
			}

		}
			return  id;
		}

		@Override
		public List<DeptEligibilityForAllowAndDeductEntity> findDeptAllowAndDeductList() {
			// TODO Auto-generated method stub
			return deptEligibilityForAllowAndDeductRepo.findDeptAllowAndDeductList();
		}

		@Override
		public List<Object[]> getEmployeeAgainstId(int allowDeducComponentId, String ddoCode, String sevaarthId) {
			// TODO Auto-generated method stub
			return deptEligibilityForAllowAndDeductRepo.getEmployeeAgainstId(allowDeducComponentId,ddoCode,sevaarthId);
		}
		public int saveAllowDeductionMst(
				@Valid DeptEligibilityForAllowAndDeductModel deptEligibilityForAllowAndDeductModel,
				OrgUserMst messages) {
			DeptEligibilityForAllowAndDeductEntity mstDeptEligibilityForAllowAndDeductEntity = new DeptEligibilityForAllowAndDeductEntity();
			
			mstDeptEligibilityForAllowAndDeductEntity.setDepartmentAllowdeducCode(deptEligibilityForAllowAndDeductModel.getDepartmentAllowdeducCode());
			mstDeptEligibilityForAllowAndDeductEntity.setIsActive('1');
			mstDeptEligibilityForAllowAndDeductEntity.setIsType(deptEligibilityForAllowAndDeductModel.getIsType());
			mstDeptEligibilityForAllowAndDeductEntity.setDepartmentAllowdeducName(deptEligibilityForAllowAndDeductModel.getDepartmentAllowdeducName());
			mstDeptEligibilityForAllowAndDeductEntity.setCreatedUserId(messages.getUserId());
			mstDeptEligibilityForAllowAndDeductEntity.setCreatedDate(new Date());
			mstDeptEligibilityForAllowAndDeductEntity.setDepartmentAllowdeducColNm(deptEligibilityForAllowAndDeductModel.getDepartmentAllowdeducName());
			mstDeptEligibilityForAllowAndDeductEntity.setIsNonComputationComponent(deptEligibilityForAllowAndDeductModel.getNonComputational());
			

			int saveId = deptEligibilityForAllowAndDeductRepo.saveAllowDeductionMst(mstDeptEligibilityForAllowAndDeductEntity);
			
			return saveId;
		}

}
