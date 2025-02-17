package com.mahait.gov.in.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsHistEntity;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.EmpChangeDetailsModel;
import com.mahait.gov.in.model.MstEmployeeModel;

import jakarta.validation.Valid;


public interface EmpChangeDetailsService {

	List<EmpChangeDetailsModel> findEmpforChangeDtls(String userName);


	MstEmployeeModel getEmpData(int empId);


	List<Object[]> getEmpSignPhoto(Long employeeId);


	/*List<MstSevenMatrixEntity> getsevenPCBasic(int payscaleId);


	List<MstEmployeeEntity> findEmpLst(String ddocode);


	MstEmployeeEntity getEmployeeData(int empId);

	public List<Object[]> GetCurrentPost(int designationId, String ddocode);


	int updateChangeEmpDtls(@Valid MstEmployeeEntity mstEmployeeEntity, MultipartFile[] files,int roleid, UserInfo messages);


	List<MstEmployeeEntity> findEmpLstforApprovChngDtls();*/
	int updateChangeEmpDtls();

	public List<Object[]> GetCurrentPostDesigation(Integer postdetailid);


	List<MstEmployeeEntity> getEmployeeDetails(OrgUserMst messages, String language);


	EmpChangeDetailsModel getEmployeeinfo(Long employeeId);


	long updateEmployeeChangeDetails(@Valid EmpChangeDetailsModel empChangeDetailsModel, MultipartFile[] files);


	String[] savePhotoSignature(MultipartFile[] files, String DeptNm, Long long1, String existphotpath,
			String existsignpath);


	List<MstEmployeeDetailEntity> findEmpLstforApprovChngDtls(String string);


	EmpChangeDetailsModel getEmployeeinfofordetails(long empId);


	List<MstNomineeDetailsHistEntity> getNominees(String string);


	long updateChangeEmpDtls(@Valid EmpChangeDetailsModel empChangeDetailsModel, MultipartFile[] files);


	
	public List<Object[]> GetCurrentPostByLvlTwoDetails(long designationId, String ddocode, long locId);


}
