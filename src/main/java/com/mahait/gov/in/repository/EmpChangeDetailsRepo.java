package com.mahait.gov.in.repository;

import java.util.List;
import java.util.Optional;

import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstGisdetailsEntity;
import com.mahait.gov.in.entity.MstGisdetailsHistEntity;
import com.mahait.gov.in.entity.MstGpfDetailsEntity;
import com.mahait.gov.in.entity.MstGpfDetailsHistEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsHistEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpChangeDetailsModel;
import com.mahait.gov.in.model.MstEmployeeModel;

import jakarta.validation.Valid;

public interface EmpChangeDetailsRepo {

	List<Object[]> findEmpforChangeDtls(String userName);

	MstEmployeeModel getEmpData(int empId);

	List<Object[]> getEmpSignPhoto(Long employeeId);

	/*List<MstSevenMatrixEntity> getsevenPCBasic(int payscaleId);

	List<MstEmployeeEntity> findEmpLst(String ddocode);

	MstEmployeeEntity getEmployeeData(int empId);

	List<Object[]> GetCurrentPost(int designationId, String ddocode);

	void updateChangeEmpDtls(@Valid MstEmployeeEntity mstEmployeeEntity);

	List<MstEmployeeEntity> findEmpLstforApprovChngDtls();

	MstEmployeeEntity findempid(Integer employeeId);
*/
	List<Object[]> GetCurrentPostDesigation(Integer postdetailid);

	List<MstEmployeeEntity> getEmployeeDetails(OrgUserMst orgUserMst);

	public String getDesignationName(String strDesgId);

	EmpChangeDetailsModel getEmployeeinfo(Long employeeId);

	Optional<MstEmployeeDetailEntity> findbyemplidForChangeDetails(Long employeeId);

	public long updateChangeEmployeeDetails(MstEmployeeDetailEntity objEntity,
			@Valid EmpChangeDetailsModel empChangeDetailsModel, MstNomineeDetailsEntity[] lArrNomineeDtls);

	MstGpfDetailsHistEntity findbyGPFid(Long gpf_id);

	MstGisdetailsHistEntity findbyGisid(Long gisid);

		public long updateEmployeeConfiguration(MstEmployeeDetailEntity objEntity,
			@Valid EmpChangeDetailsModel empChangeDetailsModel, MstNomineeDetailsHistEntity[] lArrNomineeDtls);

		List<MstEmployeeDetailEntity> findEmpLstforApprovChngDtls(String ddoCode);

		EmpChangeDetailsModel getEmployeeinfofordetails(long empId);

		List<MstNomineeDetailsHistEntity> getNominees(String empId);

		MstEmployeeEntity findempid(Long employeeId);

		MstGpfDetailsEntity findbyGPFiddeatils(Long gpf_id);

		MstGisdetailsEntity findbyGisiddetails(Long gisid);

		

		public long updateChangeDetails(MstEmployeeEntity objEntity,
				@Valid EmpChangeDetailsModel empChangeDetailsModel, MstNomineeDetailsEntity[] lArrNomineeDtls);

		List<Object[]> GetCurrentPostByLvlTwoDetails(long designationId, String ddocode, long locId);

		void updateFormStatus(Long employeeId);

		
	
	/*void updateChangeEmpHstDtls(ChangeDtlsHst changeDtlsHst);
*/
}
