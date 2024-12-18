package com.mahait.gov.in.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.EmployeeIncrementEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstPayCommissionEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.AnnualIncrementModel;
import com.mahait.gov.in.repository.AnnualIncrementRepo;

@Service
@Transactional
public class AnnualIncrementServiceImpl implements AnnualIncrementService {

	@Autowired
	AnnualIncrementRepo annualIncrementRepo;

	@Override
	public List<MstPayCommissionEntity> findAppPayCommssion() {
		return annualIncrementRepo.findAppPayCommssion();
	}

	@Override
	public List<Object[]> findAllEmpForDue(String id, Long payCommision,String ddoCode) {

		List<Object[]> deptEligibilityForAllowAndDeductEntity = annualIncrementRepo.findAllEmpForDue(id, payCommision,ddoCode);
		return deptEligibilityForAllowAndDeductEntity;
	}

	public Long saveAnnualIncrement(EmployeeIncrementEntity employeeIncrementEntity) {
		return annualIncrementRepo.saveAnnualIncrement(employeeIncrementEntity);
	}

	@Override
	public List<Object[]> findNextMatrix(Integer empIds, Integer basicPay, Integer level) {
		return annualIncrementRepo.findNextMatrix(empIds, basicPay, level);
	}

	@Override
	public List<AnnualIncrementModel> getEmpDataForIncrementApproval(String userName) {
		List<Object[]> lstprop = annualIncrementRepo.getEmpDataForIncrementApproval(userName);
		List<AnnualIncrementModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				AnnualIncrementModel obj = new AnnualIncrementModel();

				obj.setIncrementOrderDate(StringHelperUtils.isNullDate(objLst[0]));
				obj.setOrderNo(StringHelperUtils.isNullString(objLst[1].toString()));
				obj.setOfficeName(StringHelperUtils.isNullString(objLst[2]));
				obj.setDdoCode(StringHelperUtils.isNullString(objLst[3]));
				// obj.setBasicPayIncrementId(StringHelperUtils.isNullInt(objLst[4]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<AnnualIncrementModel> lstEmpforMTR21(String orderNo,String orderDate,String ddoCode) {
		List<Object[]> lstprop = annualIncrementRepo.lstEmpforMTR21(orderNo,orderDate,ddoCode);
		List<AnnualIncrementModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				AnnualIncrementModel obj = new AnnualIncrementModel();
				//b.employee_full_name_en,c.post_short_name,a.current_basic_pay,b.sevaarth_id,a.effective_from_date,\r\n" + 
				//"a.to_increment_date

				obj.setEmpname(StringHelperUtils.isNullString(objLst[0]));
				obj.setPostName(StringHelperUtils.isNullString(objLst[1]));
				obj.setCurrbasic(StringHelperUtils.isNullInt(objLst[2]));
				obj.setIncrDate(StringHelperUtils.isNullDate(objLst[4]));
				obj.setIncrToDate(StringHelperUtils.isNullDate(objLst[5]));
				obj.setIncrBasic(StringHelperUtils.isNullInt(objLst[6]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public String officeName(String userName) {
		return annualIncrementRepo.officeName(userName);
	}

	@Override
	public int approveAnnualIncrement(AnnualIncrementModel annualIncrementModel, OrgUserMst messages) {
		int id = 0;
		List<EmployeeIncrementEntity> obj = annualIncrementRepo.findEmp(annualIncrementModel.getOrderNo());
		if (obj.size() > 0) {
			for (EmployeeIncrementEntity employeeIncrementEntity : obj) {

				employeeIncrementEntity.setIsActive('2');
				employeeIncrementEntity.setUpdatedDate(new Date());
				employeeIncrementEntity.setUpdatedUserId(messages.getUserId());

				MstEmployeeEntity mstEmployeeEntity = annualIncrementRepo
						.findEmpByEmpId(employeeIncrementEntity.getEmployeeId());
				if (mstEmployeeEntity.getPayCommissionCode() == 8) { // seven pc
					mstEmployeeEntity.setSevenPcBasic(employeeIncrementEntity.getIncrementBasicPaySal().doubleValue());
				} else if (mstEmployeeEntity.getPayCommissionCode() == 2) { // six pay basic
					mstEmployeeEntity.setBasicPay(employeeIncrementEntity.getIncrementBasicPaySal().doubleValue());
				}

				annualIncrementRepo.updateEmpBasicPay(mstEmployeeEntity);

				Serializable approveGPF = annualIncrementRepo.approveAnnualIncrement(employeeIncrementEntity);
				id = (int) approveGPF;
			}
		}
		return id;
	}

	@Override
	public List<Object[]> findOrderNoAlreadyExists(String certificateNumber, String userName) {
		return annualIncrementRepo.findOrderNoAlreadyExists(certificateNumber);
	}

	@Override
	public List<AnnualIncrementModel> getEmpStatus(String userName) {
		List<Object[]> lstprop = annualIncrementRepo.getEmpStatus(userName);
		List<AnnualIncrementModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				AnnualIncrementModel obj = new AnnualIncrementModel();

				obj.setOrderNo(StringHelperUtils.isNullString(objLst[0].toString()));
				obj.setIncrementOrderDate(StringHelperUtils.isNullDate(objLst[1]));
				obj.setIsActive(StringHelperUtils.isNullChar(objLst[2]));
				
				String orderNo = objLst[0].toString();
				
				String empCount = annualIncrementRepo.getEmpCountAgainstOrderNo(orderNo,userName);
				obj.setEmpCount(empCount);
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public EmployeeIncrementEntity getPreIncDtsByempId(int empId1) {
		// TODO Auto-generated method stub
		EmployeeIncrementEntity employeeIncrementEntity = annualIncrementRepo.getPreIncDtsByempId(empId1);
		return employeeIncrementEntity;
	}

	@Override
	public void updateEmpIncrementToDate(EmployeeIncrementEntity previousIncrementDtlsObj, OrgUserMst messages) {
		// TODO Auto-generated method stub
		annualIncrementRepo.updateEmpIncrementToDate(previousIncrementDtlsObj);
	}

	@Override
	public String newMatrixId(String sevaarthIds1, String basicPaysIncrementes1) {
		// TODO Auto-generated method stub
		String newMatrixId = annualIncrementRepo.newMatrixId(sevaarthIds1, basicPaysIncrementes1);
		return newMatrixId;
	}

	@Override
	public String oldMatrixId(String sevaarthIds1, String basicPays1) {
		// TODO Auto-generated method stub
		String oldMatrixId = annualIncrementRepo.oldMatrixId(sevaarthIds1, basicPays1);
		return oldMatrixId;
	}

	@Override
	public void saveAnnualIncrementBasicMst(MstEmployeeEntity mstEmployeeEntity) {
		annualIncrementRepo.updateSevenPcBasicMst(mstEmployeeEntity);
	}

	@Override
	public MstEmployeeEntity getSevenPcBasicDetail(String sevaarthIds1) {
		return annualIncrementRepo.getSevenPcBasicDetails(sevaarthIds1);
	}

	@Override
	public EmployeeIncrementEntity findOldAnnualIncrement(Integer employeeId) {
		// TODO Auto-generated method stub
		return annualIncrementRepo.findOldAnnualIncrement(employeeId);
	}

}
