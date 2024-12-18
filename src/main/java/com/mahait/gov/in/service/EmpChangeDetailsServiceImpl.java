package com.mahait.gov.in.service;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.entity.ChangeDtlsHst;
import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstGisdetailsEntity;
import com.mahait.gov.in.entity.MstGisdetailsHistEntity;
import com.mahait.gov.in.entity.MstGpfDetailsEntity;
import com.mahait.gov.in.entity.MstGpfDetailsHistEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsHistEntity;
import com.mahait.gov.in.model.EmpChangeDetailsModel;
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.repository.EmpChangeDetailsRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;

@Service
@Transactional
public class EmpChangeDetailsServiceImpl implements EmpChangeDetailsService {
	@Autowired
	EmpChangeDetailsRepo empChangeDetailsRepo;

	@Autowired
	private Environment environment;

	@Autowired
	EmployeeIncrementService annualIncrementService;

	@PersistenceContext
	EntityManager entityManager;

	private EmpChangeDetailsModel employeeinfofordetails;

	@Override
	public List<EmpChangeDetailsModel> findEmpforChangeDtls(String userName) {
		// TODO Auto-generated method stub

		List<EmpChangeDetailsModel> lstObj = new ArrayList<>();
		List<Object[]> lstGenerateBillDetails = empChangeDetailsRepo.findEmpforChangeDtls(userName);
		lstObj = new ArrayList<>();

		// employee_full_name_en,sevaarth_id,seven_pc_basic
		if (!lstGenerateBillDetails.isEmpty()) {
			/*
			 * for (Object[] objLst : lstGenerateBillDetails) { EmpChangeDetailsModel obj =
			 * new EmpChangeDetailsModel(); if(objLst[0]!=null) {
			 * obj.setEmpName(StringHelperUtils.isNullString(objLst[0])); }
			 * if(objLst[1]!=null) {
			 * obj.setSevaarthId(StringHelperUtils.isNullString(objLst[1])); }else {
			 * obj.setSevaarthId(""); } SimpleDateFormat dateFormat = new
			 * SimpleDateFormat("dd-MM-yyyy"); String DOB = dateFormat.format(objLst[2]);
			 * obj.setDOB(StringHelperUtils.isNullString(DOB));
			 * obj.setGender(StringHelperUtils.isNullChar(objLst[3]));
			 * obj.setEmpId(StringHelperUtils.isNullInt(objLst[4]));
			 * 
			 * 
			 * 
			 * 
			 * 
			 * lstObj.add(obj); }
			 */}
		return lstObj;
	}

	@Override
	public MstEmployeeModel getEmpData(int empId) {
		// TODO Auto-generated method stub
		return empChangeDetailsRepo.getEmpData(empId);
	}

	@Override
	public List<Object[]> getEmpSignPhoto(Long employeeId) {
		// TODO Auto-generated method stub
		return empChangeDetailsRepo.getEmpSignPhoto(employeeId);
	}

	/*
	 * @Override public List<MstSevenMatrixEntity> getsevenPCBasic(int payscaleId) {
	 * // TODO Auto-generated method stub return
	 * empChangeDetailsRepo.getsevenPCBasic(payscaleId); }
	 * 
	 * 
	 * 
	 * @Override public List<MstEmployeeEntity> findEmpLst(String ddocode) { // TODO
	 * Auto-generated method stub return empChangeDetailsRepo.findEmpLst(ddocode); }
	 * 
	 * 
	 * 
	 * @Override public MstEmployeeEntity getEmployeeData(int empId) { // TODO
	 * Auto-generated method stub return
	 * empChangeDetailsRepo.getEmployeeData(empId); } public List<Object[]>
	 * GetCurrentPost(int designationId, String ddocode){ List<Object[]>
	 * result=empChangeDetailsRepo.GetCurrentPost(designationId, ddocode); return
	 * result; }
	 */

	@Override
	public int updateChangeEmpDtls(/*
									 * @Valid MstEmployeeEntity mstEmployeeEntity, MultipartFile[] files, int
									 * roleid,UserInfo userInfo
									 */) {
		return 0;
		/*
		 * 
		 * if(files.length!=0) { String[] saveimage = savePhotoSignature(files,
		 * mstEmployeeEntity.getFieldDepartmentCode(),
		 * mstEmployeeEntity.getEmployeeId(), mstEmployeeEntity.getPhotoAttachmentId(),
		 * mstEmployeeEntity.getSignatureAttachmentId());
		 * 
		 * mstEmployeeEntity.setPhotoAttachmentId(saveimage[0].toString());
		 * mstEmployeeEntity.setSignatureAttachmentId(saveimage[1]); }
		 * 
		 * 
		 * MstEmployeeEntity mstEmployeeEntity2 =
		 * empChangeDetailsRepo.findempid(mstEmployeeEntity.getEmployeeId());
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * if(roleid==1) { //add entry into annual increment try { Calendar cal =
		 * Calendar.getInstance(); Date today = cal.getTime(); cal.add(Calendar.YEAR,
		 * 1); // to get previous year add -1 cal.set(Calendar.DATE, 1); Date nextYear =
		 * cal.getTime();
		 * 
		 * Date date1 =new Date(); date1.setDate(1);
		 * 
		 * Date toIncDate =nextYear;
		 * 
		 * Integer oldBasic=0; Integer newBasic=0;
		 * 
		 * if(mstEmployeeEntity2.getPayCommissionCode()==2) {
		 * oldBasic=mstEmployeeEntity2.getBasicPay().intValue();
		 * newBasic=mstEmployeeEntity.getBasicPay().intValue(); }else
		 * if(mstEmployeeEntity2.getPayCommissionCode()==8) {
		 * oldBasic=mstEmployeeEntity2.getSevenPcBasic().intValue();
		 * newBasic=mstEmployeeEntity.getSevenPcBasic().intValue(); }
		 * 
		 * 
		 * Integer currentBasicLevelId
		 * =Integer.parseInt(mstEmployeeEntity2.getPayscalelevelId());
		 * 
		 * Integer incrementBasicLevelId =
		 * Integer.parseInt(mstEmployeeEntity.getPayscalelevelId());
		 * 
		 * EmployeeIncrementEntity employeeIncrementEntity = new
		 * EmployeeIncrementEntity();
		 * 
		 * 
		 * // employeeIncrementEntity.setBasicPayIncrementId(basicPayIncrementId1);
		 * employeeIncrementEntity.setCurrentBasicPay(oldBasic);
		 * employeeIncrementEntity.setEffective_from_date(date1);
		 * employeeIncrementEntity.setEmployeeId(mstEmployeeEntity.getEmployeeId());
		 * employeeIncrementEntity.setIncrementBasicPaySal(newBasic);
		 * employeeIncrementEntity.setIncrementOrderDate(date1);
		 * employeeIncrementEntity.setIncrementOrderNo("");
		 * employeeIncrementEntity.setPreBasicPay(oldBasic);
		 * employeeIncrementEntity.setRemark("");
		 * employeeIncrementEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
		 * employeeIncrementEntity.setCreatedDate(new Date());
		 * employeeIncrementEntity.setMonth(today.getMonth()+1);
		 * employeeIncrementEntity.setIsActive('3');
		 * employeeIncrementEntity.setDdoCode(mstEmployeeEntity.getDdoCode());
		 * employeeIncrementEntity.setTo_increment_date(nextYear);
		 * employeeIncrementEntity.setCurrentBasicLevelId(currentBasicLevelId);
		 * employeeIncrementEntity.setIncrementBasicLevelId(incrementBasicLevelId);
		 * 
		 * EmployeeIncrementEntity previousIncrementDtlsObj =
		 * annualIncrementService.getPreIncDtsByempId(mstEmployeeEntity.getEmployeeId())
		 * ; if (previousIncrementDtlsObj != null) {
		 * previousIncrementDtlsObj.setTo_increment_date(nextYear);
		 * annualIncrementService.updateEmpIncrementToDate(previousIncrementDtlsObj,
		 * userInfo); }
		 * 
		 * annualIncrementService.saveAnnualIncrement(employeeIncrementEntity);
		 * 
		 * //annualIncrementService.saveAnnualIncrementBasicMst(mstEmployeeEntity); }
		 * catch (Exception e) { System.out.println(e.getMessage()); } //end annula
		 * increment entry }else if(roleid==2) { EmployeeIncrementEntity
		 * employeeIncrementEntity=annualIncrementService.findOldAnnualIncrement(
		 * mstEmployeeEntity.getEmployeeId()); if(employeeIncrementEntity!=null) {
		 * employeeIncrementEntity.setIsActive('1');
		 * annualIncrementService.updateEmpIncrementToDate(employeeIncrementEntity,
		 * userInfo); } }
		 * 
		 * 
		 * ChangeDtlsHst changeDtlsHst = new ChangeDtlsHst();
		 * 
		 * 
		 * 
		 * if(roleid==1) { if(mstEmployeeEntity2!=null) {
		 * 
		 * 
		 * 
		 * 
		 * //History maintainance start//
		 * changeDtlsHst.setEmployeeId(mstEmployeeEntity2.getEmployeeId());
		 * changeDtlsHst.setUidNo(mstEmployeeEntity2.getUidNo());
		 * changeDtlsHst.setEidNo(mstEmployeeEntity2.getEidNo());
		 * changeDtlsHst.setSalutation(mstEmployeeEntity2.getSalutation());
		 * changeDtlsHst.setSevaarthId(mstEmployeeEntity2.getSevaarthId());
		 * changeDtlsHst.setEmployeeFullNameEn(mstEmployeeEntity2.getEmployeeFullNameEn(
		 * )); changeDtlsHst.setEmployeeFNameEn(mstEmployeeEntity2.getEmployeeFNameEn().
		 * toUpperCase());
		 * changeDtlsHst.setEmployeeMNameEn(mstEmployeeEntity2.getEmployeeMNameEn().
		 * toUpperCase());
		 * changeDtlsHst.setEmployeeLNameEn(mstEmployeeEntity2.getEmployeeLNameEn().
		 * toUpperCase());
		 * changeDtlsHst.setEmployeeFullNameMr(mstEmployeeEntity2.getEmployeeFullNameMr(
		 * ));
		 * changeDtlsHst.setEmployeeFNameMr(mstEmployeeEntity2.getEmployeeFNameMr());
		 * changeDtlsHst.setEmployeeLNameMr(mstEmployeeEntity2.getEmployeeLNameMr());
		 * changeDtlsHst.setEmployeeMotherName(mstEmployeeEntity2.getEmployeeMotherName(
		 * )); changeDtlsHst.setBuckleNo(mstEmployeeEntity2.getBuckleNo());
		 * changeDtlsHst.setGender(mstEmployeeEntity2.getGender());
		 * changeDtlsHst.setReligionCode(mstEmployeeEntity2.getReligionCode());
		 * changeDtlsHst.setMaritalStatus(mstEmployeeEntity2.getMaritalStatus());
		 * changeDtlsHst.setEmployeeMNameMr(mstEmployeeEntity2.getEmployeeMNameMr());
		 * changeDtlsHst.setDob(mstEmployeeEntity2.getDob());
		 * changeDtlsHst.setDoj(mstEmployeeEntity2.getDoj());
		 * changeDtlsHst.setAddress1(mstEmployeeEntity2.getAddress1().toUpperCase());
		 * changeDtlsHst.setAddress2(mstEmployeeEntity2.getAddress2().toUpperCase()); //
		 * changeDtlsHst.setAddress3(mstEmployeeEntity2.getAddress3().toUpperCase()); //
		 * changeDtlsHst.setLocality(mstEmployeeEntity2.getLocality());
		 * changeDtlsHst.setStateCode(mstEmployeeEntity2.getStateCode());
		 * changeDtlsHst.setDistrictCode(mstEmployeeEntity2.getDistrictCode()); //
		 * changeDtlsHst.setVillageName(mstEmployeeEntity2.getVillageName().toUpperCase(
		 * )); changeDtlsHst.setPinCode(mstEmployeeEntity2.getPinCode());
		 * changeDtlsHst.setPhysicallyHandicapped(mstEmployeeEntity2.
		 * getPhysicallyHandicapped());
		 * changeDtlsHst.setMobileNo1(mstEmployeeEntity2.getMobileNo1());
		 * changeDtlsHst.setEmailId(mstEmployeeEntity2.getEmailId());
		 * changeDtlsHst.setPanNo(mstEmployeeEntity2.getPanNo());
		 * changeDtlsHst.setCreatedDate(new Date());
		 * changeDtlsHst.setRemark(mstEmployeeEntity2.getRemark());
		 * 
		 * //Employee Details End
		 * 
		 * 
		 * //Department Details Start
		 * changeDtlsHst.setParentAdminDepartmentId(mstEmployeeEntity2.
		 * getParentAdminDepartmentCode());
		 * changeDtlsHst.setParentFieldDepartmentId(mstEmployeeEntity2.
		 * getParentFieldDepartmentCode());
		 * changeDtlsHst.setSubDeptId(mstEmployeeEntity2.getSubDeptId());
		 * changeDtlsHst.setSubCorporationId(mstEmployeeEntity2.getSubCorporationId());
		 * changeDtlsHst.setAdminDepartmentCode(mstEmployeeEntity2.
		 * getParentAdminDepartmentCode());
		 * changeDtlsHst.setFieldDepartmentCode(mstEmployeeEntity2.
		 * getFieldDepartmentCode());
		 * changeDtlsHst.setIsChangeParentDepartment(mstEmployeeEntity2.
		 * getIsChangeParentDepartment());
		 * changeDtlsHst.setReasonForChngParentFieldDept(mstEmployeeEntity2.
		 * getReasonForChngParentFieldDept());
		 * changeDtlsHst.setCadreCode(mstEmployeeEntity2.getCadreCode());
		 * changeDtlsHst.setEmpClass(mstEmployeeEntity2.getEmpClass());
		 * if(mstEmployeeEntity2.getSuperAnnAge()!=null)
		 * changeDtlsHst.setSuperAnnAge(Integer.valueOf(mstEmployeeEntity2.
		 * getSuperAnnAge()));
		 * changeDtlsHst.setEmpServiceEndDate(mstEmployeeEntity2.getSuperAnnDate());
		 * //by default set to retirement date added by sudhir
		 * changeDtlsHst.setSuperAnnDate(mstEmployeeEntity2.getSuperAnnDate());
		 * changeDtlsHst.setPayCommissionCode(mstEmployeeEntity2.getPayCommissionCode())
		 * ; changeDtlsHst.setFirstDesignationCode(mstEmployeeEntity2.
		 * getFirstDesignationCode());
		 * changeDtlsHst.setDesignationCode(mstEmployeeEntity2.getDesignationCode());
		 * changeDtlsHst.setPayscalelevelId(mstEmployeeEntity2.getPayscalelevelId());
		 * changeDtlsHst.setBegisCatg(mstEmployeeEntity2.getBegisCatg());
		 * 
		 * if(mstEmployeeEntity2.getPayscalelevelId()!=null)
		 * changeDtlsHst.setSevenPcLevel(Integer.parseInt(mstEmployeeEntity2.
		 * getPayscalelevelId())); else changeDtlsHst.setSevenPcLevel(0);
		 * 
		 * 
		 * changeDtlsHst.setSvnthpaybasic(mstEmployeeEntity2.getSvnthpaybasic());
		 * 
		 * if (mstEmployeeEntity2.getPayCommissionCode() == 2) {
		 * changeDtlsHst.setBasicPay( mstEmployeeEntity2.getBasicPay() == null ? 0 :
		 * mstEmployeeEntity2.getBasicPay().doubleValue());
		 * changeDtlsHst.setGradePay(mstEmployeeEntity2.getGradePay()); } else {
		 * changeDtlsHst.setSevenPcBasic(mstEmployeeEntity2.getSevenPcBasic() == null ?
		 * 0 : mstEmployeeEntity2.getSevenPcBasic().doubleValue()); }
		 * 
		 * changeDtlsHst.setPayScaleCode(mstEmployeeEntity2.getPayScaleCode());
		 * changeDtlsHst.setPayInPayBand(mstEmployeeEntity2.getPayInPayBand()); //
		 * changeDtlsHst.setBasicPay(mstEmployeeEntity2.getBasicPay() == null ? 0 :
		 * mstEmployeeEntity2.getBasicPay().doubleValue());
		 * 
		 * changeDtlsHst.setPostdetailid(mstEmployeeEntity2.getPostdetailid());
		 * changeDtlsHst.setDepartmentNameEn(mstEmployeeEntity2.getDepartmentNameEn());
		 * changeDtlsHst.setDtInitialAppointmentParentInst(mstEmployeeEntity2.
		 * getDtInitialAppointmentParentInst());
		 * changeDtlsHst.setInstituteAdd(mstEmployeeEntity2.getInstituteAdd());
		 * changeDtlsHst.setInstName(mstEmployeeEntity2.getInstName());
		 * changeDtlsHst.setMobileNo2(mstEmployeeEntity2.getMobileNo2());
		 * changeDtlsHst.setInstemail(mstEmployeeEntity2.getInstemail());
		 * changeDtlsHst.setDtJoinCurrentPost(mstEmployeeEntity2.getDtJoinCurrentPost())
		 * ; changeDtlsHst.setRemark(mstEmployeeEntity2.getRemark());
		 * changeDtlsHst.setCityClass(mstEmployeeEntity2.getCityClass());
		 * changeDtlsHst.setIndiApproveOrderNo(mstEmployeeEntity2.getIndiApproveOrderNo(
		 * ));
		 * changeDtlsHst.setApprovalByDdoDate(mstEmployeeEntity2.getApprovalByDdoDate())
		 * ; changeDtlsHst.setHraBasic(mstEmployeeEntity2.getHraBasic()); //Department
		 * Details End
		 * 
		 * 
		 * 
		 * //Bank/DCPS/NPS/GPF Details Start
		 * changeDtlsHst.setBankCode(mstEmployeeEntity2.getBankCode());
		 * changeDtlsHst.setIfscCode(mstEmployeeEntity2.getIfscCode());
		 * changeDtlsHst.setBankAcntNo(mstEmployeeEntity2.getBankAcntNo());
		 * changeDtlsHst.setBankBranchCode(mstEmployeeEntity2.getBankBranchCode());
		 * changeDtlsHst.setDcpsgpfflag(mstEmployeeEntity2.getDcpsgpfflag());
		 * changeDtlsHst.setDcpsaccountmaintainby(mstEmployeeEntity2.
		 * getDcpsaccountmaintainby());
		 * changeDtlsHst.setPranNo(mstEmployeeEntity2.getPranNo());
		 * changeDtlsHst.setAccountmaintainby(mstEmployeeEntity2.getAccountmaintainby())
		 * ; changeDtlsHst.setPfseries(mstEmployeeEntity2.getPfseries());
		 * changeDtlsHst.setPfacno(mstEmployeeEntity2.getPfacno());
		 * changeDtlsHst.setPfdescription(mstEmployeeEntity2.getPfdescription());
		 * 
		 * //Bank/DCPS/NPS/GPF Details End
		 * 
		 * 
		 * //GIS Details Start
		 * changeDtlsHst.setGisapplicable(mstEmployeeEntity2.getGisapplicable());
		 * changeDtlsHst.setGisgroup(mstEmployeeEntity2.getGisgroup());
		 * changeDtlsHst.setMembership_date(mstEmployeeEntity2.getMembership_date());
		 * changeDtlsHst.setGisRemark(mstEmployeeEntity2.getGisRemark());
		 * changeDtlsHst.setGiscatagory(mstEmployeeEntity2.getGiscatagory()); //GIS
		 * Details End
		 * 
		 * 
		 * ///History maintainance end//
		 * 
		 * 
		 * 
		 * 
		 * mstEmployeeEntity2.setUidNo(mstEmployeeEntity.getUidNo());
		 * mstEmployeeEntity2.setEidNo(mstEmployeeEntity.getEidNo());
		 * mstEmployeeEntity2.setSalutation(mstEmployeeEntity.getSalutation());
		 * mstEmployeeEntity2.setEmployeeFullNameEn(mstEmployeeEntity.
		 * getEmployeeFullNameEn());
		 * mstEmployeeEntity2.setEmployeeFNameEn(mstEmployeeEntity.getEmployeeFNameEn().
		 * toUpperCase());
		 * mstEmployeeEntity2.setEmployeeMNameEn(mstEmployeeEntity.getEmployeeMNameEn().
		 * toUpperCase());
		 * mstEmployeeEntity2.setEmployeeLNameEn(mstEmployeeEntity.getEmployeeLNameEn().
		 * toUpperCase()); mstEmployeeEntity2.setEmployeeFullNameMr(mstEmployeeEntity.
		 * getEmployeeFullNameMr());
		 * mstEmployeeEntity2.setEmployeeFNameMr(mstEmployeeEntity.getEmployeeFNameMr())
		 * ;
		 * mstEmployeeEntity2.setEmployeeLNameMr(mstEmployeeEntity.getEmployeeLNameMr())
		 * ; mstEmployeeEntity2.setEmployeeMotherName(mstEmployeeEntity.
		 * getEmployeeMotherName());
		 * mstEmployeeEntity2.setBuckleNo(mstEmployeeEntity.getBuckleNo());
		 * mstEmployeeEntity2.setGender(mstEmployeeEntity.getGender());
		 * mstEmployeeEntity2.setReligionCode(mstEmployeeEntity.getReligionCode());
		 * mstEmployeeEntity2.setMaritalStatus(mstEmployeeEntity.getMaritalStatus());
		 * mstEmployeeEntity2.setEmployeeMNameMr(mstEmployeeEntity.getEmployeeMNameMr())
		 * ; mstEmployeeEntity2.setDob(mstEmployeeEntity.getDob());
		 * mstEmployeeEntity2.setDoj(mstEmployeeEntity.getDoj());
		 * mstEmployeeEntity2.setAddress1(mstEmployeeEntity.getAddress1().toUpperCase())
		 * ;
		 * mstEmployeeEntity2.setAddress2(mstEmployeeEntity.getAddress2().toUpperCase())
		 * ; //
		 * mstEmployeeEntity2.setAddress3(mstEmployeeEntity.getAddress3().toUpperCase())
		 * ; // mstEmployeeEntity2.setLocality(mstEmployeeEntity.getLocality());
		 * mstEmployeeEntity2.setStateCode(mstEmployeeEntity.getStateCode());
		 * mstEmployeeEntity2.setDistrictCode(mstEmployeeEntity.getDistrictCode()); //
		 * mstEmployeeEntity2.setVillageName(mstEmployeeEntity.getVillageName().
		 * toUpperCase());
		 * mstEmployeeEntity2.setPinCode(mstEmployeeEntity.getPinCode());
		 * mstEmployeeEntity2.setPhysicallyHandicapped(mstEmployeeEntity.
		 * getPhysicallyHandicapped());
		 * mstEmployeeEntity2.setMobileNo1(mstEmployeeEntity.getMobileNo1());
		 * mstEmployeeEntity2.setEmailId(mstEmployeeEntity.getEmailId());
		 * mstEmployeeEntity2.setPanNo(mstEmployeeEntity.getPanNo());
		 * 
		 * //Employee Details End
		 * 
		 * 
		 * //Department Details Start
		 * mstEmployeeEntity2.setParentAdminDepartmentId(mstEmployeeEntity.
		 * getParentAdminDepartmentCode());
		 * mstEmployeeEntity2.setParentFieldDepartmentId(mstEmployeeEntity.
		 * getParentFieldDepartmentCode());
		 * mstEmployeeEntity2.setSubDeptId(mstEmployeeEntity.getSubDeptId());
		 * mstEmployeeEntity2.setSubCorporationId(mstEmployeeEntity.getSubCorporationId(
		 * )); mstEmployeeEntity2.setAdminDepartmentCode(mstEmployeeEntity.
		 * getParentAdminDepartmentCode());
		 * mstEmployeeEntity2.setFieldDepartmentCode(mstEmployeeEntity.
		 * getFieldDepartmentCode());
		 * mstEmployeeEntity2.setIsChangeParentDepartment(mstEmployeeEntity.
		 * getIsChangeParentDepartment());
		 * mstEmployeeEntity2.setReasonForChngParentFieldDept(mstEmployeeEntity.
		 * getReasonForChngParentFieldDept());
		 * mstEmployeeEntity2.setCadreCode(mstEmployeeEntity.getCadreCode());
		 * mstEmployeeEntity2.setEmpClass(mstEmployeeEntity.getEmpClass());
		 * mstEmployeeEntity2.setSuperAnnAge(Integer.valueOf(mstEmployeeEntity.
		 * getSuperAnnAge()));
		 * mstEmployeeEntity2.setEmpServiceEndDate(mstEmployeeEntity.getSuperAnnDate());
		 * //by default set to retirement date added by sudhir
		 * mstEmployeeEntity2.setSuperAnnDate(mstEmployeeEntity.getSuperAnnDate());
		 * mstEmployeeEntity2.setPayCommissionCode(mstEmployeeEntity.
		 * getPayCommissionCode());
		 * mstEmployeeEntity2.setFirstDesignationCode(mstEmployeeEntity.
		 * getFirstDesignationCode());
		 * mstEmployeeEntity2.setDesignationCode(mstEmployeeEntity.getDesignationCode())
		 * ;
		 * mstEmployeeEntity2.setPayscalelevelId(mstEmployeeEntity.getPayscalelevelId())
		 * ; if(mstEmployeeEntity.getPayscalelevelId()!=null)
		 * mstEmployeeEntity2.setSevenPcLevel(Integer.parseInt(mstEmployeeEntity.
		 * getPayscalelevelId())); else mstEmployeeEntity2.setSevenPcLevel(0);
		 * 
		 * 
		 * mstEmployeeEntity2.setSvnthpaybasic(mstEmployeeEntity.getSvnthpaybasic());
		 * 
		 * if (mstEmployeeEntity.getPayCommissionCode() == 2) {
		 * mstEmployeeEntity2.setBasicPay( mstEmployeeEntity.getBasicPay() == null ? 0 :
		 * mstEmployeeEntity.getBasicPay().doubleValue());
		 * mstEmployeeEntity2.setGradePay(mstEmployeeEntity.getGradePay()); } else {
		 * mstEmployeeEntity2.setSevenPcBasic(mstEmployeeEntity.getSevenPcBasic() ==
		 * null ? 0 : mstEmployeeEntity.getSevenPcBasic().doubleValue()); }
		 * 
		 * mstEmployeeEntity2.setPayScaleCode(mstEmployeeEntity.getPayScaleCode());
		 * mstEmployeeEntity2.setPayInPayBand(mstEmployeeEntity.getPayInPayBand()); //
		 * mstEmployeeEntity2.setBasicPay(mstEmployeeEntity.getBasicPay() == null ? 0 :
		 * mstEmployeeEntity.getBasicPay().doubleValue());
		 * 
		 * mstEmployeeEntity2.setPostdetailid(mstEmployeeEntity.getPostdetailid());
		 * mstEmployeeEntity2.setDepartmentNameEn(mstEmployeeEntity.getDepartmentNameEn(
		 * )); mstEmployeeEntity2.setDtInitialAppointmentParentInst(mstEmployeeEntity.
		 * getDtInitialAppointmentParentInst());
		 * mstEmployeeEntity2.setInstituteAdd(mstEmployeeEntity.getInstituteAdd());
		 * mstEmployeeEntity2.setInstName(mstEmployeeEntity.getInstName());
		 * mstEmployeeEntity2.setMobileNo2(mstEmployeeEntity.getMobileNo2());
		 * mstEmployeeEntity2.setInstemail(mstEmployeeEntity.getInstemail());
		 * mstEmployeeEntity2.setDtJoinCurrentPost(mstEmployeeEntity.
		 * getDtJoinCurrentPost());
		 * mstEmployeeEntity2.setRemark(mstEmployeeEntity.getRemark());
		 * mstEmployeeEntity2.setCityClass(mstEmployeeEntity.getCityClass());
		 * mstEmployeeEntity2.setIndiApproveOrderNo(mstEmployeeEntity.
		 * getIndiApproveOrderNo());
		 * mstEmployeeEntity2.setApprovalByDdoDate(mstEmployeeEntity.
		 * getApprovalByDdoDate());
		 * mstEmployeeEntity2.setHraBasic(mstEmployeeEntity.getHraBasic()); //Department
		 * Details End
		 * 
		 * 
		 * 
		 * //Bank/DCPS/NPS/GPF Details Start
		 * mstEmployeeEntity2.setBankCode(mstEmployeeEntity.getBankCode());
		 * mstEmployeeEntity2.setIfscCode(mstEmployeeEntity.getIfscCode());
		 * mstEmployeeEntity2.setBankAcntNo(mstEmployeeEntity.getBankAcntNo());
		 * mstEmployeeEntity2.setBankBranchCode(mstEmployeeEntity.getBankBranchCode());
		 * mstEmployeeEntity2.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
		 * mstEmployeeEntity2.setDcpsaccountmaintainby(mstEmployeeEntity.
		 * getDcpsaccountmaintainby());
		 * mstEmployeeEntity2.setPranNo(mstEmployeeEntity.getPranNo());
		 * mstEmployeeEntity2.setAccountmaintainby(mstEmployeeEntity.
		 * getAccountmaintainby());
		 * mstEmployeeEntity2.setPfseries(mstEmployeeEntity.getPfseries());
		 * mstEmployeeEntity2.setPfacno(mstEmployeeEntity.getPfacno());
		 * mstEmployeeEntity2.setPfdescription(mstEmployeeEntity.getMstGpfDetailsEntity(
		 * ).getPfdescription());
		 * 
		 * //Bank/DCPS/NPS/GPF Details End
		 * 
		 * 
		 * //GIS Details Start
		 * mstEmployeeEntity2.setGisapplicable(mstEmployeeEntity.getGisapplicable());
		 * mstEmployeeEntity2.setGisgroup(mstEmployeeEntity.getGisgroup());
		 * mstEmployeeEntity2.setMembership_date(mstEmployeeEntity.getMembership_date())
		 * ; mstEmployeeEntity2.setGisRemark(mstEmployeeEntity.getGisRemark());
		 * mstEmployeeEntity2.setGiscatagory(mstEmployeeEntity.getGiscatagory()); //GIS
		 * Details End
		 * 
		 * 
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * 
		 * 
		 * mstEmployeeEntity2.setDtInitialAppointmentParentInst(StringHelperUtils.
		 * isNullDate(mstEmployeeEntity.getDtInitialAppointmentParentInst()));
		 * mstEmployeeEntity2.setOrderDate(StringHelperUtils.isNullDate(
		 * mstEmployeeEntity.getOrderDate())); if(roleid==1) {
		 * mstEmployeeEntity2.setIsActive(5); } else {
		 * if(mstEmployeeEntity.getGender()=='1') { mstEmployeeEntity2.setGender('M'); }
		 * else if(mstEmployeeEntity.getGender()=='2') {
		 * mstEmployeeEntity2.setGender('F'); } else {
		 * mstEmployeeEntity2.setGender('T'); } mstEmployeeEntity2.setIsActive(1); }
		 * mstEmployeeEntity2.setUpdatedDate(new Date());
		 * mstEmployeeEntity2.setParentFieldDepartmentCode(BigInteger.valueOf(51)); int
		 * updated=0; if(mstEmployeeEntity!=null) {
		 * empChangeDetailsRepo.updateChangeEmpDtls(mstEmployeeEntity2); if(roleid==1)
		 * empChangeDetailsRepo.updateChangeEmpHstDtls(changeDtlsHst); updated=1; }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * return updated;
		 */}

	public String[] savePhotoSignature(MultipartFile[] files, Long long1, Long long2, String existphotpath,
			String existsignpath) {
		// department name/photo/employee_id/photo.jpg

		System.out.println("Image Uploading-----" + files.length);
		String[] res = new String[2];
		if (files.length != 0) {
			int width = 963;
			int height = 640;

			try {
				byte[] bytes = files[0].getBytes();

				if (bytes.length != 0) {
					BufferedImage image = null;
					File f = null;
					InputStream in = new ByteArrayInputStream(bytes);
					//    //read image
					////    try{
					////      f = new File(strmagepath); //image file path
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
					////      logger.info("Reading complete.");
					////    }catch(IOException e){
					////      logger.info("Error: "+e);
					////    }
					//  //write image
					// String stroutputimagepath="D:\\Image\\Output.jpg";
					//
					// f = new File(stroutputimagepath);
					// ImageIO.write(image, "jpg", f);
					// logger.info("Writing complete.");

					// Creating the directory to store file
					// String rootPath = System.getProperty("catalina.home");
					String key = "";
					String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					if (strOSName.contains("Windows")) {
						key = "serverempconfigimagepath";
					} else {
						key = "serverempconfigimagepathLinuxOS";
					}
					rootPath = environment.getRequiredProperty(key);
					rootPath += long1 + File.separator + long2;
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();

					String name = "photo.jpg";
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					res[0] = dir.getAbsolutePath() + File.separator + name;

				} else {
					res[0] = existphotpath;
				}
			} catch (Exception e) {
				e.printStackTrace();
				res[0] = "";
			}
		}

		// signature image code started
		if (files.length != 0) {
			try {
				byte[] bytes = files[1].getBytes();
				boolean var = bytes.length != 0;

				if (bytes.length != 0) {
					BufferedImage image = null;
					File f = null;
					int width = 963;
					int height = 640;
					InputStream in = new ByteArrayInputStream(bytes);
					//    //read image
					////    try{
					////      f = new File(strmagepath); //image file path
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
					// BufferedImage scaleimg = Scalr
					////      logger.info("Reading complete.");
					////    }catch(IOException e){
					////      logger.info("Error: "+e);
					////    }
					//  //write image
					// String stroutputimagepath="D:\\Image\\Signature.jpg";

					// f = new File(stroutputimagepath);
					// ImageIO.write(image, "jpg", f);
					// logger.info("Writing complete.");
					// Creating the directory to store file
					// String rootPath = System.getProperty("catalina.home");
					String key = "";
					String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					if (strOSName.contains("Windows")) {
						key = "serverempconfigimagepath";
					} else {
						key = "serverempconfigimagepathLinuxOS";
					}
					rootPath = environment.getRequiredProperty(key);
					rootPath += long1 + File.separator + long2;
					// String rootPath ="C:\\Users\\jjman\\OneDrive\\Pictures\\server";
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();
					String name = "signature.jpg";
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					res[1] = dir.getAbsolutePath() + File.separator + name;
				} else {
					res[1] = existsignpath;
				}
			} catch (Exception e) {
				e.printStackTrace();
				res[0] = "";
			}
		}
		// signature code ended
		return res;
	}

	@Override
	public List<Object[]> GetCurrentPostDesigation(Integer postdetailid) {
		List<Object[]> result = empChangeDetailsRepo.GetCurrentPostDesigation(postdetailid);
		return result;
	}

	@Override
	public List<MstEmployeeEntity> getEmployeeDetails(String ddoCode, String language) {
		// List<MstEmployeeEntity> listempentity =
		// empChangeDetailsRepo.getEmployeeDetails(ddoCode);
		// List<EmpChangeDetailsModel> result = new ArrayList<EmpChangeDetailsModel>();
		//
		// for (Iterator iterator = listempentity.iterator(); iterator.hasNext();) {
		// MstEmployeeEntity mstEmployeeEntity = (MstEmployeeEntity) iterator.next();
		// EmpChangeDetailsModel emmpChangeDetailsModel = new EmpChangeDetailsModel();
		// emmpChangeDetailsModel.setEmployeeId(mstEmployeeEntity.getEmployeeId());
		// emmpChangeDetailsModel.setEmployeeFullName(mstEmployeeEntity.getEmployeeFullNameEn().toUpperCase());
		// emmpChangeDetailsModel.setSevaarthId(mstEmployeeEntity.getSevaarthId());
		// emmpChangeDetailsModel.setDesignationName(empChangeDetailsRepo.getDesignationName(
		// mstEmployeeEntity.getDesignationCode() != null ?
		// mstEmployeeEntity.getDesignationCode().toString()
		// : "0".toString())
		// .toUpperCase());
		//
		//
		// result.add(emmpChangeDetailsModel);
		// }
		//
		//
		// return result;

		return empChangeDetailsRepo.getEmployeeDetails(ddoCode);
	}

	@Override
	public EmpChangeDetailsModel getEmployeeinfo(Long employeeId) {
		// TODO Auto-generated method stub
		return empChangeDetailsRepo.getEmployeeinfo(employeeId);
	}

	@Override
	public long updateEmployeeChangeDetails(@Valid EmpChangeDetailsModel empChangeDetailsModel, MultipartFile[] files) {
		// TODO Auto-generated method stub
		MstEmployeeDetailEntity objEntity = empChangeDetailsRepo
				.findbyemplidForChangeDetails(empChangeDetailsModel.getEmployeeId())
				.orElseGet(MstEmployeeDetailEntity::new);

		Session currentSession = entityManager.unwrap(Session.class);
		objEntity.setEmployeeId(empChangeDetailsModel.getEmployeeId());
		 objEntity.setSevaarthId(empChangeDetailsModel.getSevaarthId());
		// objEntity.setSevaarthId("0");
		MstNomineeDetailsHistEntity lObjNomineeDtls = null;
		MstNomineeDetailsHistEntity[] lArrNomineeDtls = null;
		if (objEntity != null) {
			objEntity.setUidNo(empChangeDetailsModel.getUidNo());
			objEntity.setEidNo(empChangeDetailsModel.getEidNo());
			objEntity.setSalutation(empChangeDetailsModel.getSalutation());
			objEntity.setEmployeeFullNameEn(empChangeDetailsModel.getEmployeeFullNameEn().toUpperCase());
			objEntity.setEmployeeFNameEn(empChangeDetailsModel.getEmployeeFNameEn().toUpperCase());
			objEntity.setEmployeeMNameEn(empChangeDetailsModel.getEmployeeMNameEn().toUpperCase());
			objEntity.setEmployeeLNameEn(empChangeDetailsModel.getEmployeeLNameEn().toUpperCase());
			objEntity.setEmployeeFullNameMr(empChangeDetailsModel.getEmployeeFullNameMr());
			objEntity.setEmployeeFNameMr(empChangeDetailsModel.getEmployeeFNameMr());
			objEntity.setEmployeeLNameMr(empChangeDetailsModel.getEmployeeLNameMr());
			objEntity.setEmployeeMotherName(empChangeDetailsModel.getEmployeeMotherName());
			objEntity.setBuckleNo(empChangeDetailsModel.getBuckleNo());
			objEntity.setGender(empChangeDetailsModel.getGender());
			objEntity.setReligionCode(empChangeDetailsModel.getReligionCode());
			objEntity.setMaritalStatus(empChangeDetailsModel.getMaritalStatus());
			objEntity.setEmployeeMNameMr(empChangeDetailsModel.getEmployeeMNameMr());
			objEntity.setDob(empChangeDetailsModel.getDob());
			objEntity.setDoj(empChangeDetailsModel.getDoj());
			objEntity.setAddress1(empChangeDetailsModel.getAddress1().toUpperCase());
			objEntity.setAddress2(empChangeDetailsModel.getAddress2().toUpperCase());

			objEntity.setStateCode(empChangeDetailsModel.getStateCode());
			objEntity.setDistrictCode(empChangeDetailsModel.getDistrictCode());
			// objEntity.setVillageName(mstEmployeeModel.getVillageName().toUpperCase());
			objEntity.setPinCode(empChangeDetailsModel.getPinCode());
			objEntity.setPhysicallyHandicapped(empChangeDetailsModel.getPhysicallyHandicapped());
			objEntity.setMobileNo1(empChangeDetailsModel.getMobileNo1());
			objEntity.setEmailId(empChangeDetailsModel.getEmailId());
			objEntity.setPanNo(empChangeDetailsModel.getPanNo().toUpperCase());
			objEntity.setAppointment(empChangeDetailsModel.getAppointment());

			objEntity.setParentFieldDepartmentId(empChangeDetailsModel.getParentFieldDepartmentId());
			objEntity.setIsChangeParentDepartment(empChangeDetailsModel.getIsChangeParentDepartment());
			objEntity.setReasonForChngParentFieldDept(empChangeDetailsModel.getReasonForChngParentFieldDept());
			objEntity.setCadreCode(empChangeDetailsModel.getCadreId());
			objEntity.setEmpClass(empChangeDetailsModel.getEmpClass());
			objEntity.setSuperAnnAge(empChangeDetailsModel.getSuperannuationage());
			objEntity.setEmpServiceEndDate(empChangeDetailsModel.getSuperAnnDate()); // by default set to retirement
																						// date
			// added by sudhir
			objEntity.setSuperAnnDate(empChangeDetailsModel.getSuperAnnDate());
			objEntity.setPayCommissionCode(empChangeDetailsModel.getPayCommissionCode());
			objEntity.setFirstDesignationCode(empChangeDetailsModel.getFirstDesignationId());
			objEntity.setDesignationCode(empChangeDetailsModel.getDesignationId());
			objEntity.setPayscalelevelId(empChangeDetailsModel.getPayscalelevelId());
			if (empChangeDetailsModel.getPayscalelevelId() != null)
				objEntity.setSevenPcLevel(Long.valueOf(empChangeDetailsModel.getPayscalelevelId()));
			else
				objEntity.setSevenPcLevel(0l);
			objEntity.setSvnthpaybasic(empChangeDetailsModel.getSvnthpaybasic());
			objEntity.setPayScaleCode(empChangeDetailsModel.getPayScaleCode());
			objEntity.setPayInPayBand(empChangeDetailsModel.getPayInPayBand());
			objEntity.setGradePay(empChangeDetailsModel.getGradePay());

			if (objEntity.getPayCommissionCode() == 700016) {
				objEntity.setBasicPay(empChangeDetailsModel.getBasicPay() == null ? 0
						: empChangeDetailsModel.getBasicPay().doubleValue());
			} else {
				objEntity.setSevenPcBasic(empChangeDetailsModel.getSevenPcBasic() == null ? 0
						: empChangeDetailsModel.getSevenPcBasic().doubleValue());
			}

			objEntity.setPostdetailid(empChangeDetailsModel.getPostdetailid());
			objEntity.setDepartmentNameEn(empChangeDetailsModel.getDepartmentNameEn());
			objEntity.setDtInitialAppointmentParentInst(empChangeDetailsModel.getDtInitialAppointmentParentInst());
			objEntity.setInstituteAdd(empChangeDetailsModel.getInstituteAdd());
			objEntity.setInstName(empChangeDetailsModel.getInstName());
			objEntity.setMobileNo2(empChangeDetailsModel.getMobileNo2());
			objEntity.setInstemail(empChangeDetailsModel.getInstemail());
			objEntity.setDtJoinCurrentPost(empChangeDetailsModel.getDtJoinCurrentPost());
			objEntity.setRemark(empChangeDetailsModel.getRemark());
			objEntity.setCityClass(empChangeDetailsModel.getCityClass());
			objEntity.setIndiApproveOrderNo(empChangeDetailsModel.getIndiApproveOrderNo());
			objEntity.setApprovalByDdoDate(empChangeDetailsModel.getApprovalByDdoDate());
			objEntity.setHraBasic(empChangeDetailsModel.getHraBasic());

			// Department Details End

			// Bank/DCPS/NPS/GPF Details Start
			objEntity.setBankCode(empChangeDetailsModel.getBankId());
			objEntity.setIfscCode(empChangeDetailsModel.getIfscCode());
			objEntity.setBankAcntNo(empChangeDetailsModel.getBankAcntNo());
			objEntity.setBankBranchCode(empChangeDetailsModel.getBankBranchId());
			objEntity.setDcpsgpfflag(empChangeDetailsModel.getDcpsgpfflag());
			objEntity.setDcpsaccountmaintainby(empChangeDetailsModel.getDcpsaccountmaintainby());
			objEntity.setPranNo(empChangeDetailsModel.getPranNo());
			objEntity.setAccountmaintainby(empChangeDetailsModel.getAccountmaintainby());
			objEntity.setPfseries(empChangeDetailsModel.getPfseries());
			objEntity.setPfacno(empChangeDetailsModel.getPfacno());
			System.out.println("pfdescription---------" + empChangeDetailsModel.getPfdescription());
			objEntity.setPfdescription(empChangeDetailsModel.getPfdescription());
			objEntity.setFormstatus(5l);

			// Bank/DCPS/NPS/GPF Details End

			// GIS Details Start
			objEntity.setGisapplicable(empChangeDetailsModel.getGisapplicable());
			objEntity.setGisgroup(empChangeDetailsModel.getGisgroup());
			objEntity.setMembership_date(empChangeDetailsModel.getMembership_date());
			objEntity.setGisRemark(empChangeDetailsModel.getGisRemark());
			// GIS Details End

			// DCPS/NPS Nominee Details Start

			String[] lArrNomName = empChangeDetailsModel.getStrArrNomineeName().split("~");
			String[] lArrAddress1 = empChangeDetailsModel.getStrArrAddress().split("~");
			String[] lArrDateOfBirth = empChangeDetailsModel.getStrArrDob().split("~");
			String[] lArrPercentShare = empChangeDetailsModel.getStrArrPercentShare().split("~");
			String[] lArrRelationship = empChangeDetailsModel.getStrArrRelationship().split("~");

			lArrNomineeDtls = new MstNomineeDetailsHistEntity[lArrNomName.length];

			for (int i = 0; i < lArrNomName.length; i++) {
				if (!lArrNomName[i].equals("")) {
					lObjNomineeDtls = new MstNomineeDetailsHistEntity();

					// lObjNomineeDtls.setDcpsEmpId(lObjEmpData);
					lObjNomineeDtls.setNomineename(lArrNomName[i]);
					lObjNomineeDtls.setNomineeaddress(lArrAddress1[i]);
					Date dtBirthDate = null;

					if (empChangeDetailsModel.getStrArrDob() != null
							&& !"".equals(empChangeDetailsModel.getStrArrDob().trim())) {
						EmpChangeDetailsModel empChangeDetailsModel1 = new EmpChangeDetailsModel();
						// String pattern = "yyyy-MM-dd";
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						try {
							Date date = formatter.parse(lArrDateOfBirth[i]);
							empChangeDetailsModel1.setRdob(date);
							dtBirthDate = empChangeDetailsModel1.getRdob();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}

					lObjNomineeDtls.setDob(dtBirthDate);
					long lLngPercentShare = Long.parseLong(lArrPercentShare[i]);
					lObjNomineeDtls.setPercent_share(Integer.valueOf(lArrPercentShare[i]));
					lObjNomineeDtls.setRelation(lArrRelationship[i]);
					lObjNomineeDtls.setCreateddate(new Date());
					lObjNomineeDtls.setCreatedid(empChangeDetailsModel.getCreatedUserId());
					lObjNomineeDtls.setIsactive("Y");
					lObjNomineeDtls.setUpdatedate(empChangeDetailsModel.getUpdatedDate());
					lObjNomineeDtls.setUpdateid(empChangeDetailsModel.getUpdatedUserId());
					lObjNomineeDtls.setSevaarthId(empChangeDetailsModel.getSevaarthId());

					// lObjNomineeDtls.setEmployeeId(objEntity.getEmployeeId());

					lArrNomineeDtls[i] = lObjNomineeDtls;
				}

			}

			// DCPS/NPS Nominee Details End

			objEntity.setEmpType("1");
			objEntity.setIsMappedWithNps('0');
			// objEntity.setDdoCode(mstEmployeeModel.getDdoCode());
			objEntity.setBillGroupId(empChangeDetailsModel.getBillgroupId());
			objEntity.setFormstatus(5l);
			objEntity.setDdoCode(empChangeDetailsModel.getDdoCode());
			empChangeDetailsModel.setIsActive(5l);
			objEntity.setSignatureAttachmentId(empChangeDetailsModel.getSignatureAttachmentId());
			objEntity.setCreatedUserId(empChangeDetailsModel.getCreatedUserId());
			objEntity.setCreatedDate(new Date());

		}

		if (empChangeDetailsModel.getGpf_id() != null) {
			MstGpfDetailsHistEntity objEntity2 = empChangeDetailsRepo.findbyGPFid(empChangeDetailsModel.getGpf_id());
			
			
			if (objEntity2 == null)
			// objEntity2.setGpf_id(mstEmployeeModel.getGpf_id());
			{
				 objEntity2 =new MstGpfDetailsHistEntity();
			}
				objEntity2.setAccountmaintainby(empChangeDetailsModel.getAccountmaintainby());
				objEntity2.setCreateddate(new Date());
				objEntity2.setCreatedid(1l);
				objEntity2.setIsactive(empChangeDetailsModel.getDcpsgpfflag());
				objEntity2.setPfacno(empChangeDetailsModel.getPfacno());
				objEntity2.setPfdescription(empChangeDetailsModel.getPfdescription());
				// objEntity2.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				objEntity2.setCreatedid(empChangeDetailsModel.getUpdatedUserId());
				objEntity2.setCreateddate(new Date());
				// objEntity2.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity2.setEmployeeId(objEntity.getEmployeeId());
				currentSession.saveOrUpdate(objEntity2);
			
			
			
		} else if (empChangeDetailsModel.getAccountmaintainby() != null && empChangeDetailsModel.getPfacno() != null
				&& empChangeDetailsModel.getPfseries() != null)
			if (!empChangeDetailsModel.getAccountmaintainby().equals("0")
					&& !empChangeDetailsModel.getPfacno().equals("")
					&& !empChangeDetailsModel.getPfseries().equals("0")) {
				MstGpfDetailsHistEntity objEntity2 = new MstGpfDetailsHistEntity();
				objEntity2.setAccountmaintainby(empChangeDetailsModel.getAccountmaintainby());
				objEntity2.setCreateddate(new Date());
				objEntity2.setCreatedid(1l);
				objEntity2.setIsactive(empChangeDetailsModel.getDcpsgpfflag());
				objEntity2.setPfacno(empChangeDetailsModel.getPfacno());
				objEntity2.setPfdescription(empChangeDetailsModel.getPfseries());
				objEntity2.setUpdatedate(empChangeDetailsModel.getUpdatedDate());
				objEntity2.setUpdateid(empChangeDetailsModel.getUpdatedUserId());
				objEntity2.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity2);
			}
		if (empChangeDetailsModel.getGisid() != null) {
			MstGisdetailsHistEntity objEntity3 = empChangeDetailsRepo.findbyGisid(empChangeDetailsModel.getGisid());
			// objEntity3.setGisid(mstEmployeeModel.getGisid());
			
			if (objEntity3 == null)
				// objEntity2.setGpf_id(mstEmployeeModel.getGpf_id());
				{
				objEntity3 =new MstGisdetailsHistEntity();
				}
			
				objEntity3.setCreateddate(new Date());
				objEntity3.setCreatedid(1l);
				objEntity3.setGisapplicable(empChangeDetailsModel.getGisapplicable());
				objEntity3.setGisgroup(empChangeDetailsModel.getGisgroup());
				objEntity3.setIsactive("Y");
				objEntity3.setMembership_date(empChangeDetailsModel.getMembership_date());
				// objEntity3.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				// objEntity3.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity3.setEmployeeId(objEntity.getEmployeeId());
				currentSession.saveOrUpdate(objEntity3);
		} else if (empChangeDetailsModel.getGisapplicable() != null && empChangeDetailsModel.getGisgroup() != null
				&& empChangeDetailsModel.getMembership_date() != null)
			if (!empChangeDetailsModel.getGisapplicable().equals("0")
					&& !empChangeDetailsModel.getGisgroup().equals("0")
					&& !empChangeDetailsModel.getMembership_date().equals("0")) {
				MstGisdetailsHistEntity objEntity3 = new MstGisdetailsHistEntity();
				objEntity3.setCreateddate(new Date());
				objEntity3.setCreatedid(empChangeDetailsModel.getCreatedUserId());
				objEntity3.setGisapplicable(empChangeDetailsModel.getGisapplicable());
				objEntity3.setGisgroup(empChangeDetailsModel.getGisgroup());
				objEntity3.setIsactive("Y");
				objEntity3.setMembership_date(empChangeDetailsModel.getMembership_date());
				objEntity3.setUpdatedate(empChangeDetailsModel.getUpdatedDate());
				objEntity3.setUpdateid(empChangeDetailsModel.getUpdatedUserId());
				objEntity3.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity3);
			}

		// Extra
		/*
		 * private String employeeFullName; private String designationName; private
		 * String departmentNameEn;
		 */

		String[] saveimage = savePhotoSignature(files, empChangeDetailsModel.getDeptNm(),
				empChangeDetailsModel.getEmployeeId(), empChangeDetailsModel.getPhotoAttachmentId(),
				empChangeDetailsModel.getSignatureAttachmentId());
		objEntity.setPhotoAttachmentId(saveimage[0].toString());
		objEntity.setSignatureAttachmentId(saveimage[1]);

		// Serializable id=(Integer)reuslt.get(0);

		Serializable id = empChangeDetailsRepo.updateEmployeeConfiguration(objEntity, empChangeDetailsModel,
				lArrNomineeDtls);

		return (long) id;
	}

	@Override
	public String[] savePhotoSignature(MultipartFile[] files, String DeptNm, Long long1, String existphotpath,
			String existsignpath) {
		// department name/photo/employee_id/photo.jpg
		String[] res = new String[2];
		if (files.length != 0) {
			int width = 963;
			int height = 640;

			try {
				byte[] bytes = files[0].getBytes();

				if (bytes.length != 0) {
					BufferedImage image = null;
					File f = null;
					InputStream in = new ByteArrayInputStream(bytes);
					//    //read image
					////    try{
					////      f = new File(strmagepath); //image file path
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
					////      logger.info("Reading complete.");
					////    }catch(IOException e){
					////      logger.info("Error: "+e);
					////    }
					//  //write image
					// String stroutputimagepath="D:\\Image\\Output.jpg";
					//
					// f = new File(stroutputimagepath);
					// ImageIO.write(image, "jpg", f);
					// logger.info("Writing complete.");

					// Creating the directory to store file
					// String rootPath = System.getProperty("catalina.home");
					String key = "";
					String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					if (strOSName.contains("Windows")) {
						key = "serverempconfigimagepath";
					} else {
						key = "serverempconfigimagepathLinuxOS";
					}
					rootPath = environment.getRequiredProperty(key);
					rootPath += DeptNm + File.separator + long1;
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();

					String name = "photo.jpg";
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					res[0] = dir.getAbsolutePath() + File.separator + name;

				} else {
					res[0] = existphotpath;
				}
			} catch (Exception e) {
				e.printStackTrace();
				res[0] = "";
			}
		}

		// signature image code started
		if (files.length != 0) {
			try {
				byte[] bytes = files[1].getBytes();
				boolean var = bytes.length != 0;

				if (bytes.length != 0) {
					BufferedImage image = null;
					File f = null;
					int width = 963;
					int height = 640;
					InputStream in = new ByteArrayInputStream(bytes);
					//    //read image
					////    try{
					////      f = new File(strmagepath); //image file path
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
					// BufferedImage scaleimg = Scalr
					////      logger.info("Reading complete.");
					////    }catch(IOException e){
					////      logger.info("Error: "+e);
					////    }
					//  //write image
					// String stroutputimagepath="D:\\Image\\Signature.jpg";

					// f = new File(stroutputimagepath);
					// ImageIO.write(image, "jpg", f);
					// logger.info("Writing complete.");
					// Creating the directory to store file
					// String rootPath = System.getProperty("catalina.home");
					String key = "";
					String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					if (strOSName.contains("Windows")) {
						key = "serverempconfigimagepath";
					} else {
						key = "serverempconfigimagepathLinuxOS";
					}
					rootPath = environment.getRequiredProperty(key);
					rootPath += DeptNm + File.separator + long1;
					// String rootPath ="C:\\Users\\jjman\\OneDrive\\Pictures\\server";
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();
					String name = "signature.jpg";
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					res[1] = dir.getAbsolutePath() + File.separator + name;
				} else {
					res[1] = existsignpath;
				}
			} catch (Exception e) {
				e.printStackTrace();
				res[0] = "";
			}
		}
		// signature code ended
		return res;
	}

	@Override
	public List<MstEmployeeDetailEntity> findEmpLstforApprovChngDtls(String ddoCode) {
		// TODO Auto-generated method stub
		return empChangeDetailsRepo.findEmpLstforApprovChngDtls(ddoCode);
	}

	@Override
	public EmpChangeDetailsModel getEmployeeinfofordetails(long empId) {
		// TODO Auto-generated method stub
		return empChangeDetailsRepo.getEmployeeinfofordetails(empId);
	}

	@Override
	public List<MstNomineeDetailsHistEntity> getNominees(String empId) {
		// TODO Auto-generated method stub
		List<MstNomineeDetailsHistEntity> result = empChangeDetailsRepo.getNominees(empId);
		return result;
	}

	@Override
	public long updateChangeEmpDtls(@Valid EmpChangeDetailsModel empChangeDetailsModel, MultipartFile[] files) {
		// TODO Auto-generated method stub
		if (files.length != 0) {
			String[] saveimage = savePhotoSignature(files, empChangeDetailsModel.getParentFieldDepartmentId(),
					empChangeDetailsModel.getEmployeeId(), empChangeDetailsModel.getPhotoAttachmentId(),
					empChangeDetailsModel.getSignatureAttachmentId());

			empChangeDetailsModel.setPhotoAttachmentId(saveimage[0].toString());
			empChangeDetailsModel.setSignatureAttachmentId(saveimage[1]);
		}

		Session currentSession = entityManager.unwrap(Session.class);
		MstEmployeeEntity mstEmployeeEntity = empChangeDetailsRepo.findempid(empChangeDetailsModel.getEmployeeId());

		ChangeDtlsHst changeDtlsHst = new ChangeDtlsHst();

		if (mstEmployeeEntity != null) {

			changeDtlsHst.setEmployeeId(mstEmployeeEntity.getEmployeeId());
			changeDtlsHst.setUidNo(mstEmployeeEntity.getUidNo());
			changeDtlsHst.setEidNo(mstEmployeeEntity.getEidNo());
			changeDtlsHst.setSalutation(mstEmployeeEntity.getSalutation());
			changeDtlsHst.setSevaarthId(mstEmployeeEntity.getSevaarthId());
			changeDtlsHst.setEmployeeFullNameEn(mstEmployeeEntity.getEmployeeFullNameEn());
			changeDtlsHst.setEmployeeFNameEn(mstEmployeeEntity.getEmployeeFNameEn().toUpperCase());
			changeDtlsHst.setEmployeeMNameEn(mstEmployeeEntity.getEmployeeMNameEn().toUpperCase());
			changeDtlsHst.setEmployeeLNameEn(mstEmployeeEntity.getEmployeeLNameEn().toUpperCase());
			changeDtlsHst.setEmployeeFullNameMr(mstEmployeeEntity.getEmployeeFullNameMr());
			changeDtlsHst.setEmployeeFNameMr(mstEmployeeEntity.getEmployeeFNameMr());
			changeDtlsHst.setEmployeeLNameMr(mstEmployeeEntity.getEmployeeLNameMr());
			changeDtlsHst.setEmployeeMotherName(mstEmployeeEntity.getEmployeeMotherName());
			changeDtlsHst.setBuckleNo(mstEmployeeEntity.getBuckleNo());
			changeDtlsHst.setGender(mstEmployeeEntity.getGender());
			changeDtlsHst.setReligionCode(mstEmployeeEntity.getReligionCode());
			changeDtlsHst.setMaritalStatus(mstEmployeeEntity.getMaritalStatus());
			changeDtlsHst.setEmployeeMNameMr(mstEmployeeEntity.getEmployeeMNameMr());
			changeDtlsHst.setDob(mstEmployeeEntity.getDob());
			changeDtlsHst.setDoj(mstEmployeeEntity.getDoj());
			changeDtlsHst.setAddress1(mstEmployeeEntity.getAddress1().toUpperCase());
			changeDtlsHst.setAddress2(mstEmployeeEntity.getAddress2().toUpperCase());
			// changeDtlsHst.setAddress3(mstEmployeeEntity2.getAddress3().toUpperCase());
			// changeDtlsHst.setLocality(mstEmployeeEntity2.getLocality());
			changeDtlsHst.setStateCode(mstEmployeeEntity.getStateCode());
			changeDtlsHst.setDistrictCode(mstEmployeeEntity.getDistrictCode());
			// changeDtlsHst.setVillageName(mstEmployeeEntity2.getVillageName().toUpperCase());
			changeDtlsHst.setPinCode(mstEmployeeEntity.getPinCode());
			changeDtlsHst.setPhysicallyHandicapped(mstEmployeeEntity.getPhysicallyHandicapped());
			changeDtlsHst.setMobileNo1(mstEmployeeEntity.getMobileNo1());
			changeDtlsHst.setEmailId(mstEmployeeEntity.getEmailId());
			changeDtlsHst.setPanNo(mstEmployeeEntity.getPanNo());
			changeDtlsHst.setCreatedDate(new Date());
			changeDtlsHst.setRemark(mstEmployeeEntity.getRemark());
			changeDtlsHst.setSecqualification(mstEmployeeEntity.getSecqualification());
			changeDtlsHst.setMorequalification(mstEmployeeEntity.getMorequalification());
			// Employee Details End

			// Department Details Start
			changeDtlsHst.setParentFieldDepartmentId(mstEmployeeEntity.getParentFieldDepartmentCode());
			changeDtlsHst.setSubDeptId(mstEmployeeEntity.getSubDeptId());
			changeDtlsHst.setSubCorporationId(mstEmployeeEntity.getSubCorporationId());
			changeDtlsHst.setAdminDepartmentCode(mstEmployeeEntity.getParentAdminDepartmentCode());
			changeDtlsHst.setFieldDepartmentCode(mstEmployeeEntity.getFieldDepartmentCode());
			changeDtlsHst.setIsChangeParentDepartment(mstEmployeeEntity.getIsChangeParentDepartment());
			changeDtlsHst.setReasonForChngParentFieldDept(mstEmployeeEntity.getReasonForChngParentFieldDept());
			changeDtlsHst.setCadreCode(mstEmployeeEntity.getCadreCode());
			changeDtlsHst.setEmpClass(mstEmployeeEntity.getEmpClass());
			if (mstEmployeeEntity.getSuperAnnAge() != null)
				changeDtlsHst.setSuperAnnAge(mstEmployeeEntity.getSuperAnnAge());
			changeDtlsHst.setEmpServiceEndDate(mstEmployeeEntity.getSuperAnnDate()); // by default set to retirement
																						// date added by sudhir
			changeDtlsHst.setSuperAnnDate(mstEmployeeEntity.getSuperAnnDate());
			changeDtlsHst.setPayCommissionCode(mstEmployeeEntity.getPayCommissionCode());
			changeDtlsHst.setFirstDesignationCode(mstEmployeeEntity.getFirstDesignationCode());
			changeDtlsHst.setDesignationCode(mstEmployeeEntity.getDesignationCode());
			changeDtlsHst.setPayscalelevelId(mstEmployeeEntity.getPayscalelevelId());
			changeDtlsHst.setBegisCatg(mstEmployeeEntity.getBegisCatg());
			changeDtlsHst.setQualification(mstEmployeeEntity.getQualification().toString());
			changeDtlsHst.setAppointment(mstEmployeeEntity.getAppointment());
			changeDtlsHst.setTeaching(mstEmployeeEntity.getTeaching());
			if (mstEmployeeEntity.getPayscalelevelId() != null)
				changeDtlsHst.setSevenPcLevel(Long.valueOf(mstEmployeeEntity.getPayscalelevelId()));
			else
				changeDtlsHst.setSevenPcLevel(0l);

			changeDtlsHst.setSvnthpaybasic(mstEmployeeEntity.getSvnthpaybasic());

			if (mstEmployeeEntity.getPayCommissionCode() == 700016) {
				changeDtlsHst.setBasicPay(
						mstEmployeeEntity.getBasicPay() == null ? 0 : mstEmployeeEntity.getBasicPay().doubleValue());
				changeDtlsHst.setGradePay(mstEmployeeEntity.getGradePay());
			} else {
				changeDtlsHst.setSevenPcBasic(mstEmployeeEntity.getSevenPcBasic() == null ? 0
						: mstEmployeeEntity.getSevenPcBasic().doubleValue());
			}

			changeDtlsHst.setPayScaleCode(mstEmployeeEntity.getPayScaleCode());
			changeDtlsHst.setPayInPayBand(mstEmployeeEntity.getPayInPayBand());
			// changeDtlsHst.setBasicPay(mstEmployeeEntity2.getBasicPay() == null ? 0 :
			// mstEmployeeEntity2.getBasicPay().doubleValue());

			changeDtlsHst.setPostdetailid(mstEmployeeEntity.getPostdetailid());
			changeDtlsHst.setDepartmentNameEn(mstEmployeeEntity.getDepartmentNameEn());
			changeDtlsHst.setDtInitialAppointmentParentInst(mstEmployeeEntity.getDtInitialAppointmentParentInst());
			changeDtlsHst.setInstituteAdd(mstEmployeeEntity.getInstituteAdd());
			changeDtlsHst.setInstName(mstEmployeeEntity.getInstName());
			changeDtlsHst.setMobileNo2(mstEmployeeEntity.getMobileNo2());
			changeDtlsHst.setInstemail(mstEmployeeEntity.getInstemail());
			changeDtlsHst.setDtJoinCurrentPost(mstEmployeeEntity.getDtJoinCurrentPost());
			changeDtlsHst.setRemark(mstEmployeeEntity.getRemark());
			changeDtlsHst.setCityClass(mstEmployeeEntity.getCityClass());
			changeDtlsHst.setIndiApproveOrderNo(mstEmployeeEntity.getIndiApproveOrderNo());
			changeDtlsHst.setApprovalByDdoDate(mstEmployeeEntity.getApprovalByDdoDate());
			changeDtlsHst.setHraBasic(mstEmployeeEntity.getHraBasic());
			// Department Details End

			// Bank/DCPS/NPS/GPF Details Start
			changeDtlsHst.setBankCode(mstEmployeeEntity.getBankCode());
			changeDtlsHst.setIfscCode(mstEmployeeEntity.getIfscCode());
			changeDtlsHst.setBankAcntNo(mstEmployeeEntity.getBankAcntNo());
			changeDtlsHst.setBankBranchCode(mstEmployeeEntity.getBankBranchCode());
			changeDtlsHst.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
			changeDtlsHst.setDcpsaccountmaintainby(mstEmployeeEntity.getDcpsaccountmaintainby());
			changeDtlsHst.setPranNo(mstEmployeeEntity.getPranNo());
			changeDtlsHst.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
			changeDtlsHst.setPfseries(mstEmployeeEntity.getPfseries());
			changeDtlsHst.setPfacno(mstEmployeeEntity.getPfacno());
			changeDtlsHst.setPfdescription(mstEmployeeEntity.getPfdescription());

			// Bank/DCPS/NPS/GPF Details End

			// GIS Details Start
			changeDtlsHst.setGisapplicable(mstEmployeeEntity.getGisapplicable());
			changeDtlsHst.setGisgroup(mstEmployeeEntity.getGisgroup());
			changeDtlsHst.setMembership_date(mstEmployeeEntity.getMembership_date());
			changeDtlsHst.setGisRemark(mstEmployeeEntity.getGisRemark());
			changeDtlsHst.setGiscatagory(mstEmployeeEntity.getGiscatagory());
			currentSession.save(changeDtlsHst);
			// GIS Details End

			MstEmployeeEntity objEntity = new MstEmployeeEntity();
			if (empChangeDetailsModel.getEmployeeId() != null) {
				objEntity = empChangeDetailsRepo.findempid(empChangeDetailsModel.getEmployeeId());
			}

			// objEntity.setEmployeeId(mstEmployeeModel.getEmployeeId());
			// objEntity.setSevaarthId(mstEmployeeModel.getSevaarthId());
			// objEntity.setSevaarthId("0");
			MstNomineeDetailsEntity lObjNomineeDtls = null;
			MstNomineeDetailsEntity[] lArrNomineeDtls = null;
			if (objEntity != null) {
				objEntity.setUidNo(empChangeDetailsModel.getUidNo());
				objEntity.setEidNo(empChangeDetailsModel.getEidNo());
				objEntity.setSalutation(empChangeDetailsModel.getSalutation());
				objEntity.setEmployeeFullNameEn(empChangeDetailsModel.getEmployeeFullNameEn().toUpperCase());
				objEntity.setEmployeeFNameEn(empChangeDetailsModel.getEmployeeFNameEn().toUpperCase());
				objEntity.setEmployeeMNameEn(empChangeDetailsModel.getEmployeeMNameEn().toUpperCase());
				objEntity.setEmployeeLNameEn(empChangeDetailsModel.getEmployeeLNameEn().toUpperCase());
				objEntity.setEmployeeFullNameMr(empChangeDetailsModel.getEmployeeFullNameMr());
				objEntity.setEmployeeFNameMr(empChangeDetailsModel.getEmployeeFNameMr());
				objEntity.setEmployeeLNameMr(empChangeDetailsModel.getEmployeeLNameMr());
				objEntity.setEmployeeMotherName(empChangeDetailsModel.getEmployeeMotherName());
				objEntity.setBuckleNo(empChangeDetailsModel.getBuckleNo());
				objEntity.setGender(empChangeDetailsModel.getGender());
				objEntity.setReligionCode(empChangeDetailsModel.getReligionCode());
				objEntity.setMaritalStatus(empChangeDetailsModel.getMaritalStatus());
				objEntity.setEmployeeMNameMr(empChangeDetailsModel.getEmployeeMNameMr());
				objEntity.setDob(empChangeDetailsModel.getDob());
				objEntity.setDoj(empChangeDetailsModel.getDoj());
				objEntity.setAddress1(empChangeDetailsModel.getAddress1().toUpperCase());
				objEntity.setAddress2(empChangeDetailsModel.getAddress2().toUpperCase());

				objEntity.setStateCode(empChangeDetailsModel.getStateCode());
				objEntity.setDistrictCode(empChangeDetailsModel.getDistrictCode());
				// objEntity.setVillageName(mstEmployeeModel.getVillageName().toUpperCase());
				objEntity.setPinCode(empChangeDetailsModel.getPinCode());
				objEntity.setPhysicallyHandicapped(empChangeDetailsModel.getPhysicallyHandicapped());
				objEntity.setMobileNo1(empChangeDetailsModel.getMobileNo1());
				objEntity.setEmailId(empChangeDetailsModel.getEmailId());
				objEntity.setPanNo(empChangeDetailsModel.getPanNo().toUpperCase());

				objEntity.setParentFieldDepartmentId(empChangeDetailsModel.getParentFieldDepartmentId());
				objEntity.setIsChangeParentDepartment(empChangeDetailsModel.getIsChangeParentDepartment());
				objEntity.setReasonForChngParentFieldDept(empChangeDetailsModel.getReasonForChngParentFieldDept());
				objEntity.setCadreCode(empChangeDetailsModel.getCadreId());
				System.out.println("aaa" + empChangeDetailsModel.getCadreId());
				objEntity.setEmpClass(empChangeDetailsModel.getEmpClass());
				objEntity.setSuperAnnAge(empChangeDetailsModel.getSuperannuationage());
				objEntity.setEmpServiceEndDate(empChangeDetailsModel.getSuperAnnDate()); // by default set to retirement
																							// date
				// added by sudhir
				objEntity.setSuperAnnDate(empChangeDetailsModel.getSuperAnnDate());
				objEntity.setPayCommissionCode(empChangeDetailsModel.getPayCommissionCode());
				objEntity.setFirstDesignationCode(empChangeDetailsModel.getFirstDesignationId());
				objEntity.setDesignationCode(empChangeDetailsModel.getDesignationId());
				System.out.println("setDesignationCode" + empChangeDetailsModel.getDesignationId());
				objEntity.setPayscalelevelId(empChangeDetailsModel.getPayscalelevelId());
				if (empChangeDetailsModel.getPayscalelevelId() != null)
					objEntity.setSevenPcLevel(Long.valueOf(empChangeDetailsModel.getPayscalelevelId()));
				else
					objEntity.setSevenPcLevel(0l);
				objEntity.setSvnthpaybasic(empChangeDetailsModel.getSvnthpaybasic());
				objEntity.setPayScaleCode(empChangeDetailsModel.getPayScaleCode());
				objEntity.setPayInPayBand(empChangeDetailsModel.getPayInPayBand());
				objEntity.setGradePay(empChangeDetailsModel.getGradePay());

				if (objEntity.getPayCommissionCode() == 700016) {
					objEntity.setBasicPay(empChangeDetailsModel.getBasicPay() == null ? 0
							: empChangeDetailsModel.getBasicPay().doubleValue());
				} else {
					objEntity.setSevenPcBasic(empChangeDetailsModel.getSevenPcBasic() == null ? 0
							: empChangeDetailsModel.getSevenPcBasic().doubleValue());
				}

				objEntity.setPostdetailid(empChangeDetailsModel.getPostdetailid());
				objEntity.setDepartmentNameEn(empChangeDetailsModel.getDepartmentNameEn());
				objEntity.setDtInitialAppointmentParentInst(empChangeDetailsModel.getDtInitialAppointmentParentInst());
				objEntity.setInstituteAdd(empChangeDetailsModel.getInstituteAdd());
				objEntity.setInstName(empChangeDetailsModel.getInstName());
				objEntity.setMobileNo2(empChangeDetailsModel.getMobileNo2());
				objEntity.setInstemail(empChangeDetailsModel.getInstemail());
				objEntity.setDtJoinCurrentPost(empChangeDetailsModel.getDtJoinCurrentPost());
				objEntity.setRemark(empChangeDetailsModel.getRemark());
				objEntity.setCityClass(empChangeDetailsModel.getCityClass());

				objEntity.setIndiApproveOrderNo(empChangeDetailsModel.getIndiApproveOrderNo());
				objEntity.setApprovalByDdoDate(empChangeDetailsModel.getApprovalByDdoDate());
				objEntity.setHraBasic(empChangeDetailsModel.getHraBasic());

				// Department Details End

				// Bank/DCPS/NPS/GPF Details Start
				objEntity.setBankCode(empChangeDetailsModel.getBankId());
				objEntity.setIfscCode(empChangeDetailsModel.getIfscCode());
				objEntity.setBankAcntNo(empChangeDetailsModel.getBankAcntNo());
				objEntity.setBankBranchCode(empChangeDetailsModel.getBankBranchId());
				objEntity.setDcpsgpfflag(empChangeDetailsModel.getDcpsgpfflag());
				objEntity.setDcpsaccountmaintainby(empChangeDetailsModel.getDcpsaccountmaintainby());
				objEntity.setPranNo(empChangeDetailsModel.getPranNo());
				objEntity.setAccountmaintainby(empChangeDetailsModel.getAccountmaintainby());
				objEntity.setPfseries(empChangeDetailsModel.getPfseries());
				objEntity.setPfacno(empChangeDetailsModel.getPfacno());
				System.out.println("pfdescription---------" + empChangeDetailsModel.getPfdescription());
				objEntity.setPfdescription(empChangeDetailsModel.getPfdescription());
				System.out.println("setDesignationCode" + empChangeDetailsModel.getCityClass());
				// Bank/DCPS/NPS/GPF Details End

				// GIS Details Start
				objEntity.setGisapplicable(empChangeDetailsModel.getGisapplicable());
				objEntity.setGisgroup(empChangeDetailsModel.getGisgroup());
				objEntity.setMembership_date(empChangeDetailsModel.getMembership_date());
				objEntity.setGisRemark(empChangeDetailsModel.getGisRemark());
				// GIS Details End

				// DCPS/NPS Nominee Details Start

				String[] lArrNomName = empChangeDetailsModel.getStrArrNomineeName().split("~");
				String[] lArrAddress1 = empChangeDetailsModel.getStrArrAddress().split("~");
				String[] lArrDateOfBirth = empChangeDetailsModel.getStrArrDob().split("~");
				String[] lArrPercentShare = empChangeDetailsModel.getStrArrPercentShare().split("~");
				String[] lArrRelationship = empChangeDetailsModel.getStrArrRelationship().split("~");

				lArrNomineeDtls = new MstNomineeDetailsEntity[lArrNomName.length];

				for (int i = 0; i < lArrNomName.length; i++) {
					if (!lArrNomName[i].equals("")) {
						lObjNomineeDtls = new MstNomineeDetailsEntity();

						// lObjNomineeDtls.setDcpsEmpId(lObjEmpData);
						lObjNomineeDtls.setNomineename(lArrNomName[i]);
						lObjNomineeDtls.setNomineeaddress(lArrAddress1[i]);
						Date dtBirthDate = null;

						if (empChangeDetailsModel.getStrArrDob() != null
								&& !"".equals(empChangeDetailsModel.getStrArrDob().trim())) {
							EmpChangeDetailsModel empChangeDetailsModel1 = new EmpChangeDetailsModel();
							// String pattern = "yyyy-MM-dd";
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
							try {
								Date date = formatter.parse(lArrDateOfBirth[i]);
								empChangeDetailsModel1.setRdob(date);
								dtBirthDate = empChangeDetailsModel1.getRdob();
							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
							}

						}

						lObjNomineeDtls.setDob(dtBirthDate);
						long lLngPercentShare = Long.parseLong(lArrPercentShare[i]);
						lObjNomineeDtls.setPercent_share(Integer.valueOf(lArrPercentShare[i]));
						lObjNomineeDtls.setRelation(lArrRelationship[i]);
						lObjNomineeDtls.setCreateddate(new Date());
						lObjNomineeDtls.setCreatedid(empChangeDetailsModel.getCreatedUserId());
						lObjNomineeDtls.setIsactive("Y");
						lObjNomineeDtls.setUpdatedate(empChangeDetailsModel.getUpdatedDate());
						lObjNomineeDtls.setUpdateid(empChangeDetailsModel.getUpdatedUserId());
						lObjNomineeDtls.setSevaarthId(empChangeDetailsModel.getSevaarthId());

						// lObjNomineeDtls.setEmployeeId(objEntity.getEmployeeId());

						lArrNomineeDtls[i] = lObjNomineeDtls;
					}

				}

				// DCPS/NPS Nominee Details End

				objEntity.setEmpType("1");
				objEntity.setIsMappedWithNps('0');
				// objEntity.setDdoCode(mstEmployeeModel.getDdoCode());
				objEntity.setSignatureAttachmentId(empChangeDetailsModel.getSignatureAttachmentId());
				objEntity.setCreatedUserId(empChangeDetailsModel.getCreatedUserId());
				objEntity.setCreatedDate(new Date());

			}

			if (empChangeDetailsModel.getGpf_id() != null) {
				MstGpfDetailsEntity objEntity2 = empChangeDetailsRepo
						.findbyGPFiddeatils(empChangeDetailsModel.getGpf_id());
				if (objEntity2 != null)
				// objEntity2.setGpf_id(mstEmployeeModel.getGpf_id());
				{
					objEntity2.setAccountmaintainby(empChangeDetailsModel.getAccountmaintainby());
					objEntity2.setCreateddate(new Date());
					objEntity2.setCreatedid(1l);
					objEntity2.setIsactive(empChangeDetailsModel.getDcpsgpfflag());
					objEntity2.setPfacno(empChangeDetailsModel.getPfacno());
					objEntity2.setPfdescription(empChangeDetailsModel.getPfdescription());
					// objEntity2.setUpdatedate(mstEmployeeModel.getUpdatedDate());
					objEntity2.setCreatedid(empChangeDetailsModel.getUpdatedUserId());
					objEntity2.setCreateddate(new Date());
					// objEntity2.setUpdateid(mstEmployeeModel.getUpdatedUserId());
					objEntity2.setEmployeeId(objEntity.getEmployeeId());
					currentSession.update(objEntity2);
				}
			} else if (empChangeDetailsModel.getAccountmaintainby() != null && empChangeDetailsModel.getPfacno() != null
					&& empChangeDetailsModel.getPfseries() != null)
				if (!empChangeDetailsModel.getAccountmaintainby().equals("0")
						&& !empChangeDetailsModel.getPfacno().equals("")
						&& !empChangeDetailsModel.getPfseries().equals("0")) {
					MstGpfDetailsEntity objEntity2 = new MstGpfDetailsEntity();
					objEntity2.setAccountmaintainby(empChangeDetailsModel.getAccountmaintainby());
					objEntity2.setCreateddate(new Date());
					objEntity2.setCreatedid(1l);
					objEntity2.setIsactive(empChangeDetailsModel.getDcpsgpfflag());
					objEntity2.setPfacno(empChangeDetailsModel.getPfacno());
					objEntity2.setPfdescription(empChangeDetailsModel.getPfseries());
					objEntity2.setUpdatedate(empChangeDetailsModel.getUpdatedDate());
					objEntity2.setUpdateid(empChangeDetailsModel.getUpdatedUserId());
					objEntity2.setEmployeeId(objEntity.getEmployeeId());
					currentSession.update(objEntity2);
				}
			if (empChangeDetailsModel.getGisid() != null) {
				MstGisdetailsEntity objEntity3 = empChangeDetailsRepo
						.findbyGisiddetails(empChangeDetailsModel.getGisid());
				// objEntity3.setGisid(mstEmployeeModel.getGisid());
				if (objEntity3 != null) {
					objEntity3.setCreateddate(new Date());
					objEntity3.setCreatedid(1l);
					objEntity3.setGisapplicable(empChangeDetailsModel.getGisapplicable());
					objEntity3.setGisgroup(empChangeDetailsModel.getGisgroup());
					objEntity3.setIsactive("Y");
					objEntity3.setMembership_date(empChangeDetailsModel.getMembership_date());
					// objEntity3.setUpdatedate(mstEmployeeModel.getUpdatedDate());
					// objEntity3.setUpdateid(mstEmployeeModel.getUpdatedUserId());
					objEntity3.setEmployeeId(objEntity.getEmployeeId());
					currentSession.update(objEntity3);
				}
			} else if (empChangeDetailsModel.getGisapplicable() != null && empChangeDetailsModel.getGisgroup() != null
					&& empChangeDetailsModel.getMembership_date() != null)
				if (!empChangeDetailsModel.getGisapplicable().equals("0")
						&& !empChangeDetailsModel.getGisgroup().equals("0")
						&& !empChangeDetailsModel.getMembership_date().equals("0")) {
					MstGisdetailsEntity objEntity3 = new MstGisdetailsEntity();
					objEntity3.setCreateddate(new Date());
					objEntity3.setCreatedid(empChangeDetailsModel.getCreatedUserId());
					objEntity3.setGisapplicable(empChangeDetailsModel.getGisapplicable());
					objEntity3.setGisgroup(empChangeDetailsModel.getGisgroup());
					objEntity3.setIsactive("Y");
					objEntity3.setMembership_date(empChangeDetailsModel.getMembership_date());
					objEntity3.setUpdatedate(empChangeDetailsModel.getUpdatedDate());
					objEntity3.setUpdateid(empChangeDetailsModel.getUpdatedUserId());
					objEntity3.setEmployeeId(objEntity.getEmployeeId());
					currentSession.update(objEntity3);
				}

			// Extra
			/*
			 * private String employeeFullName; private String designationName; private
			 * String departmentNameEn;
			 */
			

			Serializable id = empChangeDetailsRepo.updateChangeDetails(objEntity, empChangeDetailsModel,
					lArrNomineeDtls);

			if (empChangeDetailsModel.getPhotoAttachmentId() != null) {
				String[] saveimage = savePhotoSignature(files, empChangeDetailsModel.getDeptNm(),
						empChangeDetailsModel.getEmployeeId(), empChangeDetailsModel.getPhotoAttachmentId(),
						empChangeDetailsModel.getSignatureAttachmentId());
				objEntity.setPhotoAttachmentId(saveimage[0].toString());
				objEntity.setSignatureAttachmentId(saveimage[1]);

				// Serializable id=(Integer)reuslt.get(0);
			}
			
			
			 empChangeDetailsRepo.updateFormStatus(empChangeDetailsModel.getEmployeeId());

			return (long) id;
		}
		return 1;
	}

	public String[] savePhotoSignature(MultipartFile[] files, BigInteger bigInteger, Integer empid,
			String existphotpath, String existsignpath) {
		// department name/photo/employee_id/photo.jpg

		System.out.println("Image Uploading-----" + files.length);
		String[] res = new String[2];
		if (files.length != 0) {
			int width = 963;
			int height = 640;

			try {
				byte[] bytes = files[0].getBytes();

				if (bytes.length != 0) {
					BufferedImage image = null;
					File f = null;
					InputStream in = new ByteArrayInputStream(bytes);
					//    //read image
					////    try{
					////      f = new File(strmagepath); //image file path
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
					////      logger.info("Reading complete.");
					////    }catch(IOException e){
					////      logger.info("Error: "+e);
					////    }
					//  //write image
					// String stroutputimagepath="D:\\Image\\Output.jpg";
					//
					// f = new File(stroutputimagepath);
					// ImageIO.write(image, "jpg", f);
					// logger.info("Writing complete.");

					// Creating the directory to store file
					// String rootPath = System.getProperty("catalina.home");
					String key = "";
					String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					if (strOSName.contains("Windows")) {
						key = "serverempconfigimagepath";
					} else {
						key = "serverempconfigimagepathLinuxOS";
					}
					rootPath = environment.getRequiredProperty(key);
					rootPath += bigInteger + File.separator + empid;
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();

					String name = "photo.jpg";
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					res[0] = dir.getAbsolutePath() + File.separator + name;

				} else {
					res[0] = existphotpath;
				}
			} catch (Exception e) {
				e.printStackTrace();
				res[0] = "";
			}
		}

		// signature image code started
		if (files.length != 0) {
			try {
				byte[] bytes = files[1].getBytes();
				boolean var = bytes.length != 0;

				if (bytes.length != 0) {
					BufferedImage image = null;
					File f = null;
					int width = 963;
					int height = 640;
					InputStream in = new ByteArrayInputStream(bytes);
					//    //read image
					////    try{
					////      f = new File(strmagepath); //image file path
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
					// BufferedImage scaleimg = Scalr
					////      logger.info("Reading complete.");
					////    }catch(IOException e){
					////      logger.info("Error: "+e);
					////    }
					//  //write image
					// String stroutputimagepath="D:\\Image\\Signature.jpg";

					// f = new File(stroutputimagepath);
					// ImageIO.write(image, "jpg", f);
					// logger.info("Writing complete.");
					// Creating the directory to store file
					// String rootPath = System.getProperty("catalina.home");
					String key = "";
					String rootPath = "";
					String strOSName = System.getProperty("os.name");
					boolean test = strOSName.contains("Windows");
					if (strOSName.contains("Windows")) {
						key = "serverempconfigimagepath";
					} else {
						key = "serverempconfigimagepathLinuxOS";
					}
					rootPath = environment.getRequiredProperty(key);
					rootPath += bigInteger + File.separator + empid;
					// String rootPath ="C:\\Users\\jjman\\OneDrive\\Pictures\\server";
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();
					String name = "signature.jpg";
					// Create the file on server
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					res[1] = dir.getAbsolutePath() + File.separator + name;
				} else {
					res[1] = existsignpath;
				}
			} catch (Exception e) {
				e.printStackTrace();
				res[0] = "";
			}
		}
		// signature code ended
		return res;
	}

	@Override
	public List<Object[]> GetCurrentPostByLvlTwoDetails(long designationId, String ddocode, long locId) {
		// TODO Auto-generated method stub
		List<Object[]> result = empChangeDetailsRepo.GetCurrentPostByLvlTwoDetails(designationId, ddocode, locId);
		return result;
	}

}
