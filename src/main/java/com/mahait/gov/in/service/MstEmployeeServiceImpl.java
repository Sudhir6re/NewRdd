package com.mahait.gov.in.service;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.AppoinmentEntity;
import com.mahait.gov.in.entity.CmnLocationMst;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.EmployeeAllowDeducComponentAmtEntity;
import com.mahait.gov.in.entity.LoanEmployeeDtlsEntity;
import com.mahait.gov.in.entity.MstCadreGroupEntity;
import com.mahait.gov.in.entity.MstDcpsDetailsEntity;
import com.mahait.gov.in.entity.MstDesignationEntity;
import com.mahait.gov.in.entity.MstEmployeeDetailEntity;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstGisdetailsEntity;
import com.mahait.gov.in.entity.MstGpfDetailsEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.entity.MstRoleEntity;
import com.mahait.gov.in.entity.OrgPostMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.entity.OrgUserpostRlt;
import com.mahait.gov.in.entity.QualificationEntity;
import com.mahait.gov.in.entity.ZpRltDdoMap;
import com.mahait.gov.in.model.DDOScreenModel;
import com.mahait.gov.in.model.MstCadreModel;
import com.mahait.gov.in.model.MstEmployeeModel;
import com.mahait.gov.in.repository.CmnLookupMstRepository;
import com.mahait.gov.in.repository.MstEmployeeRepo;
import com.mahait.gov.in.repository.MstRoleRepo;
import com.mahait.gov.in.repository.OrgPostMstRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;

@Service
@Transactional
@PropertySource(value = { "classpath:application.properties" })
public class MstEmployeeServiceImpl implements MstEmployeeService {

	@Autowired
	MstRoleRepo mstRoleRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	CmnLookupMstRepository cmnLookupMstRepository;
	@Autowired
	private MstEmployeeRepo mstEmployeeRepo;

	@Autowired
	private Environment environment;
	@Autowired
	private OrgPostMstRepository orgPostMstRepository;
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public DdoOffice findAllGroup(String ddoCode) {
		return mstEmployeeRepo.findAllGroup(ddoCode);
	}

	@Override
	public List<MstEmployeeEntity> findAllWorkingEmployeeByDDOCodeAndBillGroup(String ddoCode, Long billGroupId,
			int month, int year) {
		return mstEmployeeRepo.findAllWorkingEmployeeByDDOCodeAndBillGroup(ddoCode, billGroupId, month, year);
	}

	public EmployeeAllowDeducComponentAmtEntity findGRPComponentsData(String sevaarthId, int allowDedCode) {
		return mstEmployeeRepo.findGRPComponentsData(sevaarthId, allowDedCode);
	}

	@Override
	public LoanEmployeeDtlsEntity findGPFADetails(String sevaarthid, int commoncodeComponentGpfaCode) {
		return mstEmployeeRepo.findGPFADetails(sevaarthid, commoncodeComponentGpfaCode);
	}

	@Override
	public LoanEmployeeDtlsEntity findGPFAdvDetails(String sevaarthid, int commoncodeComponentGpfaCode) {
		return mstEmployeeRepo.findGPFAdvDetails(sevaarthid, commoncodeComponentGpfaCode);
	}

	@Override
	public int getpayCommissionAgainstEmployee(String sevaarthId) {
		return mstEmployeeRepo.getpayCommissionAgainstEmployee(sevaarthId);
	}

	@Override
	public List<Object[]> employeeAllowDeduction(String sevaarthId) {
		return mstEmployeeRepo.findEmployeeAllowanceDeduction(sevaarthId);

	}

	@Override
	public List<DDOScreenModel> findDDOScreenDataTable(String locale, long loc_id) {
		List<Object[]> lstprop = null;
		try {
			lstprop = mstEmployeeRepo.findDDOScreenDataTable(loc_id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<DDOScreenModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				DDOScreenModel obj = new DDOScreenModel();
				obj.setSubDepartmentId(StringHelperUtils.isNullInt(objLst[0]));
				obj.setSubDeptName(StringHelperUtils.isNullString(objLst[1]));

				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<Object[]> getInstitueDtls(String ddocode) {
		List<Object[]> lstprop = mstEmployeeRepo.getInstitueDtls(ddocode);
		return lstprop;
	}

	@Override
	public List<MstCadreModel> getCadreMstData(String locale, long loc_id) {
		long fielddeptId = mstEmployeeRepo.getFieldDeptId(loc_id);
		List<Object[]> lstprop = mstEmployeeRepo.getCadreMstData(fielddeptId);
		List<MstCadreModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstCadreModel obj = new MstCadreModel();
				obj.setCadreId(StringHelperUtils.isNullBigInteger(objLst[0]).longValue());
				obj.setCadreDescription(StringHelperUtils.isNullString(objLst[1]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<Object[]> getAccountMaintainby() {
		List<Object[]> result = new ArrayList<Object[]>();
		Object[] object = new Object[2];
		object[0] = "1";
		object[1] = "A.G Mumbai";
		result.add(object);
		Object[] object1 = new Object[2];
		object1[0] = "2";
		object1[1] = "A.G  Nagpur";
		result.add(object1);

		/*
		 * Object[] object2 = new Object[2]; object2[0] = "3"; object2[1] =
		 * "Zilla Parishad"; result.add(object2);
		 */

		Object[] object3 = new Object[2];
		object3[0] = "3";
		object3[1] = "MJP";
		result.add(object3);
		Object[] object4 = new Object[2];
		object4[0] = "4";
		object4[1] = "Not Applicable";
		result.add(object4);

		Object[] object5 = new Object[2];
		object5[0] = "5";
		object5[1] = "Others";
		result.add(object5);
		return result;
	}

	// protected final Log logger = LogFactory.getLog(getClass());
	@Override
	public List<Object[]> getDcpsAccnMaintainby() {
		List<Object[]> result = new ArrayList<Object[]>();

		/*
		 * Object[] object = new Object[2]; object[0] = "1"; object[1] =
		 * "a/C Maintained by Zilla Parishad"; result.add(object);
		 */
		Object[] object2 = new Object[2];
		object2[0] = "1";
		object2[1] = "a/C Maintained by MJP";
		result.add(object2);

		Object[] object1 = new Object[2];
		object1[0] = "2";
		object1[1] = "a/C Maintained by Others";
		result.add(object1);
		return result;
	}

	@Override
	public List<MstCadreGroupEntity> getGISGroup() {
		return mstEmployeeRepo.getGISGroup();
	}

	@Override
	public List<Object[]> getGISApplicable() {
		List<Object[]> result = new ArrayList<Object[]>();

		Object[] temp = { "test", "NA", "Central Govt (CGEGIS)", "I.A.S (GIS)", "I.F.S(GIS)", "I.P.S (GIS)", "ZP(GIS)",
				"MJP(GIS)", "State Govt (GIS)", "Other" };
		for (int i = 1; i < temp.length; i++) {
			Object[] object2 = new Object[2];
			object2[0] = i;
			object2[1] = temp[i];
			result.add(object2);
		}
		return result;
	}

	@Override
	public List<Object[]> getRelation() {
		List<Object[]> result = new ArrayList<Object[]>();
		Object[] temp = { "Husband", "Wife", "Son", "Daughter", "Other", "Father", "Mother", "Brother" };
		for (int i = 0; i < temp.length; i++) {
			Object[] object2 = new Object[2];
			object2[0] = temp[i];
			object2[1] = temp[i];
			result.add(object2);
		}
		return result;
	}

	@Override
	public List<Object[]> findEmployeeConfigurationGetSixPayScale(int payCommission) {
		// TODO Auto-generated method stub
		return mstEmployeeRepo.findEmployeeConfigurationGetSixPayScale(payCommission);
	}

	@Override
	public List<Object[]> findEmployeeConfigurationGetpayscale(int payCommission) {
		// TODO Auto-generated method stub
		List<Object[]> deptEligibilityForAllowAndDeductEntity = mstEmployeeRepo.getSvnPayscale();
		return deptEligibilityForAllowAndDeductEntity;
	}

	@Override
	public List<Object[]> findEmployeeConfigurationGetCurrentPost(Long designationId, String ddocode,
			String currpostcode, long loc_id) {
		// TODO Auto-generated method stub
		List<Object[]> deptEligibilityForAllowAndDeductEntity = mstEmployeeRepo
				.findEmployeeConfigurationGetCurrentPost(designationId, ddocode, currpostcode, loc_id);
		return deptEligibilityForAllowAndDeductEntity;
	}

	@Override
	public List<Object[]> getCadreGroupMstData(String locale, String strCadreId) {
		List<Object[]> lstprop = mstEmployeeRepo.getgroupname(strCadreId);
		return lstprop;
	}

	@Override
	public List<MstEmployeeModel> getCadreGroupMstDataNew(String cadreid, String dob) {
		List<MstEmployeeModel> lstlstDeptEligibilityForAllowAndDeductEntity = new ArrayList<>();
		List<Object[]> lstObj = mstEmployeeRepo.getCadreGroupMstDataNew(cadreid);
		Integer ag = null;
		int age = 0;
		for (Object obj[] : lstObj) {
			MstEmployeeModel mstEmployeeModel = new MstEmployeeModel();
			ag =StringHelperUtils.isNullBigInteger(obj[1]).intValue();

			age = ag.intValue();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			Date birthdt = null;
			try {
				birthdt = sd.parse(dob);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(birthdt);
			int yer = cal.getTime().getYear();
			yer = yer + 1900;
			Date dobt = cal.getTime();
			Date enhFamPensDate = null;
			if (dobt.getDate() == 1 && dobt.getMonth() == 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(birthdt);
				calendar.set(Calendar.YEAR, yer + age - 1);
				calendar.set(Calendar.MONTH, 11);
				calendar.set(Calendar.DATE, 31);
				// Calendar calendar1 = Calendar.getInstance();
				enhFamPensDate = calendar.getTime();
			} else if (dobt.getDate() == 1 && dobt.getMonth() != 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(birthdt);
				int reage = yer + age;
				calendar.set(Calendar.YEAR, yer + age);
				if (dobt.getMonth() == 2) {
					if (reage % 4 == 0) {
						calendar.set(Calendar.MONTH, 0);
						calendar.set(Calendar.DATE, 29);
					} else if (reage % 4 != 0) {
						calendar.set(Calendar.MONTH, 0);
						calendar.set(Calendar.DATE, 28);
					}
				} else {
					calendar.set(Calendar.MONTH, dobt.getMonth() - 1);
					int day = calendar.getActualMaximum(Calendar.DATE);
					calendar.set(Calendar.DATE, day);
				}

				enhFamPensDate = calendar.getTime();
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(birthdt);
				calendar.set(Calendar.YEAR, yer + age);
				if (dobt.getMonth() == 1) {
					int reage = yer + age;
					calendar.set(Calendar.YEAR, yer + age);
					if (reage % 4 == 0) {
						calendar.set(Calendar.MONTH, 1);
						calendar.set(Calendar.DATE, 29);
					} else {
						calendar.set(Calendar.MONTH, 1);
						calendar.set(Calendar.DATE, 28);
					}
				} else {
					calendar.set(Calendar.MONTH, dobt.getMonth());
					int day = calendar.getActualMaximum(Calendar.DATE);
					calendar.set(Calendar.DATE, day);
				}

				enhFamPensDate = calendar.getTime();
			}

			mstEmployeeModel.setSuperAnnDate(enhFamPensDate);
			mstEmployeeModel.setEmpServiceEndDate(enhFamPensDate);
			lstlstDeptEligibilityForAllowAndDeductEntity.add(mstEmployeeModel);
		}
		return lstlstDeptEligibilityForAllowAndDeductEntity;
	}

	@Override
	public List<Object[]> findEmployeeConfigurationpayScaleSeven(int payScaleSeven) {
		return mstEmployeeRepo.findEmployeeConfigurationpayScaleSeven(payScaleSeven);
	}

	@Override
	public List<AppoinmentEntity> getAppoitnment(String language) {
		// TODO Auto-generated method stub
		return mstEmployeeRepo.getAppoitnment(language);
	}

	@Override
	public List<Object[]> findEmployeeConfigurationGetsvnbasicpay(String payscale) {
		List<Object[]> deptEligibilityForAllowAndDeductEntity = mstEmployeeRepo.getSvnPcData(payscale);
		return deptEligibilityForAllowAndDeductEntity;
	}

	@Override
	public List<MstDesignationEntity> getDesignationMstData(String locale, long locId) {
		// TODO Auto-generated method stub
		long fielddeptId = mstEmployeeRepo.getFieldDeptId(locId);
		List<Object[]> lstprop = mstEmployeeRepo.getDesignationMstData(fielddeptId);
		List<MstDesignationEntity> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstDesignationEntity obj = new MstDesignationEntity();
				obj.setDesginationId(StringHelperUtils.isNullBigInteger(objLst[0]).longValue());
				obj.setDesgination(StringHelperUtils.isNullString(objLst[1]));
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<Object[]> employeeConfigurationGetCurrenOffice(long postdetailid, String userName, long locId) {
		// TODO Auto-generated method stub
		List<Object[]> deptEligibilityForAllowAndDeductEntity = mstEmployeeRepo
				.employeeConfigurationGetCurrenOffice(postdetailid, userName, locId);
		return deptEligibilityForAllowAndDeductEntity;
	}

	@Override
	public List<Object[]> employeeConfigurationGetCurrenOfficeAddress(long adminDepartmentId, String userName,
			long locId) {
		// TODO Auto-generated method stub
		List<Object[]> deptEligibilityForAllowAndDeductEntity = mstEmployeeRepo
				.employeeConfigurationGetCurrenOfficeAddress(adminDepartmentId, userName, locId);
		return deptEligibilityForAllowAndDeductEntity;
	}

	@Override
	public List<Object[]> getBankBranch(String bankid) {
		// TODO Auto-generated method stub
		List<Object[]> lstbranchname = mstEmployeeRepo.getBankBranch(bankid);
		return lstbranchname;
	}

	@Override
	public List<Object[]> getPfSeries(String accmainby) {
		// TODO Auto-generated method stub
		List<Object[]> getPfSeries = mstEmployeeRepo.getPfSeries(accmainby);
		return getPfSeries;
	}
	// @Override
	// public List<MstBankBranchEntity> getIfscCodeByBranchId(int branchId) {
	// // TODO Auto-generated method stub
	// return mstEmployeeRepo.getIfscCodeByBranchId(branchId);
	// }

	@Override
	public long updateEmployeeConfiguration(MstEmployeeModel mstEmployeeModel, MultipartFile[] files) {
		MstEmployeeEntity objEntity = new MstEmployeeEntity();
		if (mstEmployeeModel.getEmployeeId() != null) {
			objEntity = mstEmployeeRepo.findbyemplid(mstEmployeeModel.getEmployeeId());
		}

		else {
			objEntity = new MstEmployeeEntity();
		}
		Session currentSession = entityManager.unwrap(Session.class);
		// objEntity.setEmployeeId(mstEmployeeModel.getEmployeeId());
		// objEntity.setSevaarthId(mstEmployeeModel.getSevaarthId());
		// objEntity.setSevaarthId("0");
		MstNomineeDetailsEntity lObjNomineeDtls = null;
		MstNomineeDetailsEntity[] lArrNomineeDtls = null;
		if (objEntity != null) {
			objEntity.setUidNo(mstEmployeeModel.getUidNo());
			objEntity.setEidNo(mstEmployeeModel.getEidNo());
			objEntity.setSalutation(mstEmployeeModel.getSalutation());
			objEntity.setEmployeeFullNameEn(mstEmployeeModel.getEmployeeFullNameEn().toUpperCase());
			objEntity.setEmployeeFNameEn(mstEmployeeModel.getEmployeeFNameEn().toUpperCase());
			objEntity.setEmployeeMNameEn(mstEmployeeModel.getEmployeeMNameEn().toUpperCase());
			objEntity.setEmployeeLNameEn(mstEmployeeModel.getEmployeeLNameEn().toUpperCase());
			objEntity.setEmployeeFullNameMr(mstEmployeeModel.getEmployeeFullNameMr());
			objEntity.setEmployeeFNameMr(mstEmployeeModel.getEmployeeFNameMr());
			objEntity.setEmployeeLNameMr(mstEmployeeModel.getEmployeeLNameMr());
			objEntity.setEmployeeMotherName(mstEmployeeModel.getEmployeeMotherName());
			objEntity.setBuckleNo(mstEmployeeModel.getBuckleNo());
			objEntity.setGender(mstEmployeeModel.getGender());
			// if (mstEmployeeModel.getGender() == '1') {
			// objEntity.setGender('M');
			// } else if (mstEmployeeModel.getGender() == '2') {
			// objEntity.setGender('F');
			// } else {
			// objEntity.setGender('T');
			// }
			objEntity.setReligionCode(mstEmployeeModel.getReligionCode());
			objEntity.setMaritalStatus(mstEmployeeModel.getMaritalStatus());
			objEntity.setEmployeeMNameMr(mstEmployeeModel.getEmployeeMNameMr());
			objEntity.setDob(mstEmployeeModel.getDob());
			objEntity.setDoj(mstEmployeeModel.getDoj());
			objEntity.setAddress1(mstEmployeeModel.getAddress1().toUpperCase());
			objEntity.setAddress2(mstEmployeeModel.getAddress2().toUpperCase());
			/*
			 * objEntity.setAddress3(mstEmployeeModel.getAddress3().toUpperCase());
			 * objEntity.setLocality(mstEmployeeModel.getLocality());
			 */
			objEntity.setStateCode(mstEmployeeModel.getStateCode());
			objEntity.setDistrictCode(mstEmployeeModel.getDistrictCode());
			// objEntity.setVillageName(mstEmployeeModel.getVillageName().toUpperCase());
			objEntity.setPinCode(mstEmployeeModel.getPinCode());
			objEntity.setPhysicallyHandicapped(mstEmployeeModel.getPhysicallyHandicapped());
			objEntity.setMobileNo1(mstEmployeeModel.getMobileNo1());
			objEntity.setEmailId(mstEmployeeModel.getEmailId());
			objEntity.setPanNo(mstEmployeeModel.getPanNo().toUpperCase());

			// Employee Details End

			// Department Details Start
			// objEntity.setParentAdminDepartmentId(mstEmployeeModel.getParentAdminDepartmentId());
			objEntity.setParentFieldDepartmentId(mstEmployeeModel.getParentFieldDepartmentId());
			objEntity.setIsChangeParentDepartment(mstEmployeeModel.getIsChangeParentDepartment());
			objEntity.setReasonForChngParentFieldDept(mstEmployeeModel.getReasonForChngParentFieldDept());
			objEntity.setCadreCode(mstEmployeeModel.getCadreId());
			objEntity.setEmpClass(mstEmployeeModel.getEmpClass());
			objEntity.setSuperAnnAge(mstEmployeeModel.getSuperannuationage());
			objEntity.setEmpServiceEndDate(mstEmployeeModel.getSuperAnnDate()); // by default set to retirement date
			objEntity.setPercentageOfBasic(100l); // added by sudhir
			objEntity.setSuperAnnDate(mstEmployeeModel.getSuperAnnDate());
			objEntity.setPayCommissionCode(mstEmployeeModel.getPayCommissionCode());
			objEntity.setFirstDesignationCode(mstEmployeeModel.getFirstDesignationId());
			objEntity.setDesignationCode(mstEmployeeModel.getDesignationId());
			objEntity.setPayscalelevelId(mstEmployeeModel.getPayscalelevelId());
			if (mstEmployeeModel.getPayscalelevelId() != null)
				objEntity.setSevenPcLevel(Long.valueOf(mstEmployeeModel.getPayscalelevelId()));
			else
				objEntity.setSevenPcLevel(0l);
			objEntity.setSvnthpaybasic(mstEmployeeModel.getSvnthpaybasic());
			objEntity.setPayScaleCode(mstEmployeeModel.getPayScaleCode());
			objEntity.setPayInPayBand(mstEmployeeModel.getPayInPayBand());
			objEntity.setGradePay(mstEmployeeModel.getGradePay());

			if (objEntity.getPayCommissionCode() == 700016) {
				objEntity.setBasicPay(
						mstEmployeeModel.getBasicPay() == null ? 0 : mstEmployeeModel.getBasicPay().doubleValue());
			} else {
				objEntity.setSevenPcBasic(mstEmployeeModel.getSevenPcBasic() == null ? 0
						: mstEmployeeModel.getSevenPcBasic().doubleValue());
			}

			objEntity.setPostdetailid(mstEmployeeModel.getPostdetailid());
			objEntity.setDepartmentNameEn(mstEmployeeModel.getDepartmentNameEn());
			objEntity.setDtInitialAppointmentParentInst(mstEmployeeModel.getDtInitialAppointmentParentInst());
			objEntity.setInstituteAdd(mstEmployeeModel.getInstituteAdd());
			objEntity.setInstName(mstEmployeeModel.getInstName());
			objEntity.setMobileNo2(mstEmployeeModel.getMobileNo2());
			objEntity.setInstemail(mstEmployeeModel.getInstemail());
			objEntity.setDtJoinCurrentPost(mstEmployeeModel.getDtJoinCurrentPost());
			objEntity.setRemark(mstEmployeeModel.getRemark());
			objEntity.setCityClass(mstEmployeeModel.getCityClass());
			objEntity.setIndiApproveOrderNo(mstEmployeeModel.getIndiApproveOrderNo());
			objEntity.setApprovalByDdoDate(mstEmployeeModel.getApprovalByDdoDate());
			objEntity.setHraBasic(mstEmployeeModel.getHraBasic());
			// Department Details End

			// Bank/DCPS/NPS/GPF Details Start
			objEntity.setBankCode(mstEmployeeModel.getBankId());
			objEntity.setIfscCode(mstEmployeeModel.getIfscCode());
			objEntity.setBankAcntNo(mstEmployeeModel.getBankAcntNo());
			objEntity.setBankBranchCode(mstEmployeeModel.getBankBranchId());
			objEntity.setDcpsgpfflag(mstEmployeeModel.getDcpsgpfflag());
			objEntity.setDcpsaccountmaintainby(mstEmployeeModel.getDcpsaccountmaintainby());
			objEntity.setPranNo(mstEmployeeModel.getPranNo());
			objEntity.setAccountmaintainby(mstEmployeeModel.getAccountmaintainby());
			objEntity.setPfseries(mstEmployeeModel.getPfseries());
			objEntity.setPfacno(mstEmployeeModel.getPfacno());
			System.out.println("pfdescription---------" + mstEmployeeModel.getPfdescription());
			objEntity.setPfdescription(mstEmployeeModel.getPfdescription());

			// Bank/DCPS/NPS/GPF Details End

			// GIS Details Start
			objEntity.setGisapplicable(mstEmployeeModel.getGisapplicable());
			objEntity.setGisgroup(mstEmployeeModel.getGisgroup());
			objEntity.setMembership_date(mstEmployeeModel.getMembership_date());
			objEntity.setGisRemark(mstEmployeeModel.getGisRemark());
			// GIS Details End

			// DCPS/NPS Nominee Details Start

			String[] lArrNomName = mstEmployeeModel.getStrArrNomineeName().split("~");
			String[] lArrAddress1 = mstEmployeeModel.getStrArrAddress().split("~");
			String[] lArrDateOfBirth = mstEmployeeModel.getStrArrDob().split("~");
			String[] lArrPercentShare = mstEmployeeModel.getStrArrPercentShare().split("~");
			String[] lArrRelationship = mstEmployeeModel.getStrArrRelationship().split("~");

			lArrNomineeDtls = new MstNomineeDetailsEntity[lArrNomName.length];

			for (int i = 0; i < lArrNomName.length; i++) {
				if (!lArrNomName[i].equals("")) {
					lObjNomineeDtls = new MstNomineeDetailsEntity();

					// lObjNomineeDtls.setDcpsEmpId(lObjEmpData);
					lObjNomineeDtls.setNomineename(lArrNomName[i]);
					lObjNomineeDtls.setNomineeaddress(lArrAddress1[i]);
					Date dtBirthDate = null;

					if (mstEmployeeModel.getStrArrDob() != null && !"".equals(mstEmployeeModel.getStrArrDob().trim())) {
						MstEmployeeModel mstEmployeeModel1 = new MstEmployeeModel();
						// String pattern = "yyyy-MM-dd";
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						try {
							Date date = formatter.parse(lArrDateOfBirth[i]);
							mstEmployeeModel1.setRdob(date);
							dtBirthDate = mstEmployeeModel1.getRdob();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}

					lObjNomineeDtls.setDob(dtBirthDate);
					long lLngPercentShare = Long.parseLong(lArrPercentShare[i]);
					lObjNomineeDtls.setPercent_share(Integer.parseInt(lArrPercentShare[i]));
					lObjNomineeDtls.setRelation(lArrRelationship[i]);
					lObjNomineeDtls.setCreateddate(new Date());
					lObjNomineeDtls.setCreatedid(mstEmployeeModel.getCreatedUserId());
					lObjNomineeDtls.setIsactive("Y");
					lObjNomineeDtls.setUpdatedate(mstEmployeeModel.getUpdatedDate());
					lObjNomineeDtls.setUpdateid(mstEmployeeModel.getUpdatedUserId());
					// lObjNomineeDtls.setEmployeeId(objEntity.getEmployeeId());

					lArrNomineeDtls[i] = lObjNomineeDtls;
				}

			}

			// DCPS/NPS Nominee Details End

			objEntity.setEmpType("1");
			objEntity.setIsMappedWithNps('0');
			// objEntity.setDdoCode(mstEmployeeModel.getDdoCode());
			objEntity.setBillGroupId(mstEmployeeModel.getBillgroupId());
			objEntity.setIsActive(3l);
			mstEmployeeModel.setIsActive(3l);
			objEntity.setSignatureAttachmentId(mstEmployeeModel.getSignatureAttachmentId());
			objEntity.setCreatedUserId(mstEmployeeModel.getCreatedUserId());
			objEntity.setCreatedDate(new Date());

			if (mstEmployeeModel.getSevaarthId() == null || mstEmployeeModel.getSevaarthId().equals("")) {

				String sevaarthGenration = getCrearteSevaartIh(mstEmployeeModel);
				objEntity.setSevaarthId(sevaarthGenration);

			}

		}

		if (mstEmployeeModel.getDcpsid() != null) {
			MstDcpsDetailsEntity objEntity1 = mstEmployeeRepo.findbyDcpsid(mstEmployeeModel.getDcpsid());
			// objEntity1.setDcpsid(mstEmployeeModel.getDcpsid());
			objEntity1.setIsactive(mstEmployeeModel.getDcpsgpfflag());
			objEntity1.setAccountmaintainby(mstEmployeeModel.getDcpsaccountmaintainby());
			objEntity1.setCreateddate(new Date());
			objEntity1.setCreatedid(1l);
			objEntity1.setPfacno(mstEmployeeModel.getPfacno());
			// objEntity1.setUpdatedate(mstEmployeeModel.getUpdatedDate());
			// objEntity1.setUpdateid(mstEmployeeModel.getUpdatedUserId());
			objEntity1.setEmployeeId(objEntity.getEmployeeId());
			currentSession.update(objEntity1);
		} else if (mstEmployeeModel.getDcpsaccountmaintainby() != null)
			if (!mstEmployeeModel.getDcpsaccountmaintainby().equals("0")) {
				MstDcpsDetailsEntity objEntity1 = new MstDcpsDetailsEntity();
				objEntity1.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				objEntity1.setAccountmaintainby(mstEmployeeModel.getDcpsaccountmaintainby());
				objEntity1.setCreateddate(new Date());
				objEntity1.setCreatedid(1l);
				objEntity1.setPfacno(mstEmployeeModel.getPfacno());
				// objEntity1.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				// objEntity1.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity1.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity1);
			}

		if (mstEmployeeModel.getGpf_id() != null) {
			MstGpfDetailsEntity objEntity2 = mstEmployeeRepo.findbyGPFid(mstEmployeeModel.getGpf_id());
			// objEntity2.setGpf_id(mstEmployeeModel.getGpf_id());
			objEntity2.setAccountmaintainby(mstEmployeeModel.getAccountmaintainby());
			objEntity2.setCreateddate(new Date());
			objEntity2.setCreatedid(1l);
			objEntity2.setIsactive(mstEmployeeModel.getDcpsgpfflag());
			objEntity2.setPfacno(mstEmployeeModel.getPfacno());
			objEntity2.setPfdescription(mstEmployeeModel.getPfdescription());
			// objEntity2.setUpdatedate(mstEmployeeModel.getUpdatedDate());
			objEntity2.setCreatedid(mstEmployeeModel.getUpdatedUserId());
			objEntity2.setCreateddate(new Date());
			// objEntity2.setUpdateid(mstEmployeeModel.getUpdatedUserId());
			objEntity2.setEmployeeId(objEntity.getEmployeeId());
			currentSession.update(objEntity2);
		} else if (mstEmployeeModel.getAccountmaintainby() != null && mstEmployeeModel.getPfacno() != null
				&& mstEmployeeModel.getPfseries() != null)
			if (!mstEmployeeModel.getAccountmaintainby().equals("0") && !mstEmployeeModel.getPfacno().equals("")
					&& !mstEmployeeModel.getPfseries().equals("0")) {
				MstGpfDetailsEntity objEntity2 = new MstGpfDetailsEntity();
				objEntity2.setAccountmaintainby(mstEmployeeModel.getAccountmaintainby());
				objEntity2.setCreateddate(new Date());
				objEntity2.setCreatedid(1l);
				objEntity2.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				objEntity2.setPfacno(mstEmployeeModel.getPfacno());
				objEntity2.setPfdescription(mstEmployeeModel.getPfseries());
				objEntity2.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				objEntity2.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity2.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity2);
			}
		if (mstEmployeeModel.getGisid() != null) {
			MstGisdetailsEntity objEntity3 = mstEmployeeRepo.findbyGisid(mstEmployeeModel.getGisid());
			// objEntity3.setGisid(mstEmployeeModel.getGisid());
			objEntity3.setCreateddate(new Date());
			objEntity3.setCreatedid(1l);
			objEntity3.setGisapplicable(mstEmployeeModel.getGisapplicable());
			objEntity3.setGisgroup(mstEmployeeModel.getGisgroup());
			objEntity3.setIsactive("Y");
			objEntity3.setMembership_date(mstEmployeeModel.getMembership_date());
			// objEntity3.setUpdatedate(mstEmployeeModel.getUpdatedDate());
			// objEntity3.setUpdateid(mstEmployeeModel.getUpdatedUserId());
			objEntity3.setEmployeeId(objEntity.getEmployeeId());
			currentSession.update(objEntity3);
		} else if (mstEmployeeModel.getGisapplicable() != null && mstEmployeeModel.getGisgroup() != null
				&& mstEmployeeModel.getMembership_date() != null)
			if (!mstEmployeeModel.getGisapplicable().equals("0") && !mstEmployeeModel.getGisgroup().equals("0")
					&& !mstEmployeeModel.getMembership_date().equals("0")) {
				MstGisdetailsEntity objEntity3 = new MstGisdetailsEntity();
				objEntity3.setCreateddate(new Date());
				objEntity3.setCreatedid(mstEmployeeModel.getCreatedUserId());
				objEntity3.setGisapplicable(mstEmployeeModel.getGisapplicable());
				objEntity3.setGisgroup(mstEmployeeModel.getGisgroup());
				objEntity3.setIsactive("Y");
				objEntity3.setMembership_date(mstEmployeeModel.getMembership_date());
				objEntity3.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				objEntity3.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity3.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity3);
			}

		// Extra
		/*
		 * private String employeeFullName; private String designationName; private
		 * String departmentNameEn;
		 */

		String[] saveimage = savePhotoSignature(files, mstEmployeeModel.getDeptNm(), mstEmployeeModel.getEmployeeId(),
				mstEmployeeModel.getPhotoAttachmentId(), mstEmployeeModel.getSignatureAttachmentId());
		objEntity.setPhotoAttachmentId(saveimage[0].toString());
		objEntity.setSignatureAttachmentId(saveimage[1]);

		// Serializable id=(Integer)reuslt.get(0);

		Serializable id = mstEmployeeRepo.updateEmployeeConfiguration(objEntity, mstEmployeeModel, lArrNomineeDtls);

		// EmployeeIncrementEntity employeeIncrementEntity = new
		// EmployeeIncrementEntity();
		//
		// boolean incrementOrderExists =
		// mstEmployeeRepo.annualIncrementExists(objEntity.getEmployeeId());
		//
		// if (!incrementOrderExists) {
		// // Annual Increment entry for employee Start
		// employeeIncrementEntity.setSevaarthId(objEntity.getSevaarthId());
		// employeeIncrementEntity.setEmployeeId(objEntity.getEmployeeId());
		// employeeIncrementEntity.setCurrentBasicPay(0);
		// employeeIncrementEntity.setCurrentBasicLevelId(0);
		// employeeIncrementEntity.setPreBasicPay(0);
		// employeeIncrementEntity.setIncrementOrderNo("0");
		// employeeIncrementEntity.setIncrementOrderDate(new Date());
		// if (objEntity.getPayCommissionCode() == 8) {
		// employeeIncrementEntity.setIncrementBasicPaySal(objEntity.getSevenPcBasic().intValue());
		// } else {
		// employeeIncrementEntity.setIncrementBasicPaySal(objEntity.getBasicPay().intValue());
		// }
		// employeeIncrementEntity.setIncrementBasicLevelId(mstEmployeeModel.getSvnthpaybasic());
		// employeeIncrementEntity.setEffective_from_date(new Date());
		// employeeIncrementEntity.setTo_increment_date(new Date());
		// employeeIncrementEntity.setCreatedDate(new Date());
		// employeeIncrementEntity.setUpdatedDate(new Date());
		// employeeIncrementEntity.setRemark(null);
		// employeeIncrementEntity.setIsActive('1');
		// employeeIncrementEntity.setMonth(1);
		// employeeIncrementEntity.setCreatedUserId(1);
		// employeeIncrementEntity.setUpdatedUserId(1);
		// employeeIncrementEntity.setDdoCode(objEntity.getDdoCode());
		// // Annual Increment entry for employee End
		//
		// mstEmployeeRepo.saveEmployeeIncrement(employeeIncrementEntity);
		// }

		return (int) id;
	}

	private String getCrearteSevaartIh(MstEmployeeModel mstEmployeeModel) {
		SimpleDateFormat sdf = new SimpleDateFormat("yy");

		String dept = mstEmployeeRepo.getCmnLocationMst(mstEmployeeModel.getDdoCode());
		String lStrOfficeName = dept.substring(0, 3).toUpperCase();
		String adminOfficeMst = mstEmployeeModel.getDdoCode();
		adminOfficeMst = adminOfficeMst.substring(0, 2);
		char fname;
		char mname;
		char lname;
		if (mstEmployeeModel.getEmployeeMNameEn() != null) {
			fname = mstEmployeeModel.getEmployeeFNameEn().trim().charAt(0);
			mname = mstEmployeeModel.getEmployeeMNameEn().trim().charAt(0);
			lname = mstEmployeeModel.getEmployeeLNameEn().trim().charAt(0);
		} else {
			fname = mstEmployeeModel.getEmployeeFNameEn().trim().charAt(0);
			mname = mstEmployeeModel.getEmployeeFNameEn().trim().charAt(1);
			lname = mstEmployeeModel.getEmployeeLNameEn().trim().charAt(0);
		}
		String name = String.valueOf(fname) + String.valueOf(mname) + String.valueOf(lname);
		String gender = mstEmployeeModel.getGender().toString();
		// if (gender.equals("1")) {
		// gender = "M";
		// } else if (gender.equals("2")) {
		// gender = "F";
		// } else if (gender.equals("3")) {
		// gender = "T";
		// } else {
		gender = mstEmployeeModel.getGender().toString();
		// }
		String year = sdf.format(mstEmployeeModel.getDob());
		String index = "01";
		String sevaarth = adminOfficeMst + lStrOfficeName + name.toUpperCase() + gender + year;

		// String sevaarth = dept + name.toUpperCase() + gender + year + index;
		BigInteger indexnew = null;
		if (sevaarth != null) {
			indexnew = mstEmployeeRepo.findbySevaarthCount(sevaarth);
		}

		if (!indexnew.equals(BigInteger.valueOf(0))) {
			if (String.valueOf(indexnew).length() == 1) {
				index = "0" + indexnew.add(BigInteger.valueOf(1));
			} else {
				index = indexnew.add(BigInteger.valueOf(1)).toString();
			}
			sevaarth = adminOfficeMst + lStrOfficeName + name.toUpperCase() + gender + year + index;
		} else if (indexnew.equals(BigInteger.valueOf(0))) {
			sevaarth = sevaarth + index;
		}
		return sevaarth;
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
	public long saveEmployeeConfiguration(MstEmployeeModel mstEmployeeModel, MultipartFile[] files) {
		MstEmployeeEntity objEntity = new MstEmployeeEntity();

		// objEntity.setEmployeeId(mstEmployeeModel.getEmployeeId());
		// objEntity.setSevaarthId(mstEmployeeModel.getSevaarthId());

		if (!mstEmployeeModel.getAction().equals("saveAsDraft")) {
			if (mstEmployeeModel.getSevaarthId() == null || mstEmployeeModel.getSevaarthId().equalsIgnoreCase(""))
				if (mstEmployeeModel.getSevaarthId() == null) {
					String sevaarthGenration = getCrearteSevaartIh(mstEmployeeModel);
					objEntity.setSevaarthId(sevaarthGenration);
				} else {
					objEntity.setSevaarthId(mstEmployeeModel.getSevaarthId());
				}
		}

		// Employee Details Start
		objEntity.setUidNo(mstEmployeeModel.getUidNo());
		objEntity.setEidNo(mstEmployeeModel.getEidNo());
		objEntity.setSalutation(mstEmployeeModel.getSalutation());
		objEntity.setEmployeeFullNameEn(mstEmployeeModel.getEmployeeFullNameEn().toUpperCase());
		objEntity.setEmployeeFNameEn(mstEmployeeModel.getEmployeeFNameEn().toUpperCase());
		objEntity.setEmployeeMNameEn(mstEmployeeModel.getEmployeeMNameEn().toUpperCase());
		objEntity.setEmployeeLNameEn(mstEmployeeModel.getEmployeeLNameEn().toUpperCase());
		objEntity.setEmployeeFullNameMr(mstEmployeeModel.getEmployeeFullNameMr());
		objEntity.setEmployeeFNameMr(mstEmployeeModel.getEmployeeFNameMr());
		objEntity.setEmployeeLNameMr(mstEmployeeModel.getEmployeeLNameMr());
		objEntity.setEmployeeMotherName(mstEmployeeModel.getEmployeeMotherName());
		objEntity.setGender(mstEmployeeModel.getGender());
		objEntity.setPercentageOfBasic(100l);

		objEntity.setBuckleNo(mstEmployeeModel.getBuckleNo());
		// if (mstEmployeeModel.getGender() == '1') {
		// objEntity.setGender('M');
		// } else if (mstEmployeeModel.getGender() == '2') {
		// objEntity.setGender('F');
		// } else {
		// objEntity.setGender('T');
		// }
		objEntity.setReligionCode(mstEmployeeModel.getReligionCode());
		objEntity.setMaritalStatus(mstEmployeeModel.getMaritalStatus());
		objEntity.setEmployeeMNameMr(mstEmployeeModel.getEmployeeMNameMr());
		objEntity.setDob(mstEmployeeModel.getDob());
		objEntity.setDoj(mstEmployeeModel.getDoj());
		objEntity.setAddress1(mstEmployeeModel.getAddress1().toUpperCase());
		objEntity.setAddress2(mstEmployeeModel.getAddress2().toUpperCase());
		//objEntity.setQualification(mstEmployeeModel.getQualification().toString());
		//objEntity.setAppointment(mstEmployeeModel.getAppointmentId().toString());
		// objEntity.setAddress3(mstEmployeeModel.getAddress3().toUpperCase());
		// objEntity.setLocality(mstEmployeeModel.getLocality());
		objEntity.setStateCode(mstEmployeeModel.getStateCode());
		objEntity.setDistrictCode(mstEmployeeModel.getDistrictCode());
		// objEntity.setVillageName(mstEmployeeModel.getVillageName().toUpperCase());
		objEntity.setPinCode(mstEmployeeModel.getPinCode());
		objEntity.setPhysicallyHandicapped(mstEmployeeModel.getPhysicallyHandicapped());
		objEntity.setMobileNo1(mstEmployeeModel.getMobileNo1());
		objEntity.setEmailId(mstEmployeeModel.getEmailId());
		objEntity.setPanNo(mstEmployeeModel.getPanNo().toUpperCase());
		// objEntity.setQualification(mstEmployeeModel.getQualification());
		//objEntity.setSecqualification(mstEmployeeModel.getSecqualification());
		//objEntity.setMorequalification(mstEmployeeModel.getMorequalification());

		// Employee Details End

		// Department Details Start
		// objEntity.setParentAdminDepartmentId(mstEmployeeModel.getParentAdminDepartmentId());
		objEntity.setParentFieldDepartmentId(mstEmployeeModel.getParentFieldDepartmentId());
//		objEntity.setSubDeptId(mstEmployeeModel.getParentFieldDepartmentId().intValue());
		// objEntity.setSubDeptId(mstEmployeeModel.getParentFieldDepartmentId().intValue());
		objEntity.setSubCorporationId(mstEmployeeModel.getSubCorporationId());
		objEntity.setAdminDepartmentCode(mstEmployeeModel.getParentAdminDepartmentId());
		objEntity.setFieldDepartmentCode(mstEmployeeModel.getFieldDepartmentId());
		objEntity.setIsChangeParentDepartment(mstEmployeeModel.getIsChangeParentDepartment());
		objEntity.setReasonForChngParentFieldDept(mstEmployeeModel.getReasonForChngParentFieldDept());
		objEntity.setCadreCode(mstEmployeeModel.getCadreId());
		objEntity.setEmpClass(mstEmployeeModel.getEmpClass());
		if (mstEmployeeModel.getSuperannuationage() != null)
			objEntity.setSuperAnnAge(Long.valueOf(mstEmployeeModel.getSuperannuationage()));
		objEntity.setEmpServiceEndDate(mstEmployeeModel.getSuperAnnDate()); // by default set to retirement date added
		objEntity.setCurrentOfficeCode(mstEmployeeModel.getCurrentOfficeId()); // by sudhir
		objEntity.setSuperAnnDate(mstEmployeeModel.getSuperAnnDate());
		objEntity.setPayCommissionCode(mstEmployeeModel.getPayCommissionCode());
		objEntity.setFirstDesignationCode(mstEmployeeModel.getFirstDesignationId());
		objEntity.setDesignationCode(mstEmployeeModel.getDesignationId());
		objEntity.setPayscalelevelId(mstEmployeeModel.getPayscalelevelId());
		objEntity.setPercentageOfBasic(100l);
		if (mstEmployeeModel.getPayscalelevelId() != null)
			objEntity.setSevenPcLevel(Long.valueOf(mstEmployeeModel.getPayscalelevelId()));
		else
			objEntity.setSevenPcLevel(0l);

		objEntity.setSvnthpaybasic(mstEmployeeModel.getSvnthpaybasic());

		objEntity.setPayScaleCode(mstEmployeeModel.getPayScaleCode());
		objEntity.setPayInPayBand(mstEmployeeModel.getPayInPayBand());
		objEntity.setGradePay(mstEmployeeModel.getGradePay());

		// objEntity.setBasicPay(mstEmployeeModel.getBasicPay() == null ? 0 :
		// mstEmployeeModel.getBasicPay().doubleValue());

		if (objEntity.getPayCommissionCode() == 700016) {
			objEntity.setBasicPay(
					mstEmployeeModel.getBasicPay() == null ? 0 : mstEmployeeModel.getBasicPay().doubleValue());
		} else {
			objEntity.setSevenPcBasic(
					mstEmployeeModel.getSevenPcBasic() == null ? 0 : mstEmployeeModel.getSevenPcBasic().doubleValue());
		}

		objEntity.setPostdetailid(mstEmployeeModel.getPostdetailid());
		objEntity.setDepartmentNameEn(mstEmployeeModel.getDepartmentNameEn());
		objEntity.setDtInitialAppointmentParentInst(mstEmployeeModel.getDtInitialAppointmentParentInst());
		objEntity.setInstituteAdd(mstEmployeeModel.getInstituteAdd());
		objEntity.setInstName(mstEmployeeModel.getInstName());
		objEntity.setMobileNo2(mstEmployeeModel.getMobileNo2());
		objEntity.setInstemail(mstEmployeeModel.getInstemail());
		objEntity.setCityClass(mstEmployeeModel.getCityClass());
		objEntity.setDtJoinCurrentPost(mstEmployeeModel.getDtJoinCurrentPost());
		objEntity.setRemark(mstEmployeeModel.getRemark());
		objEntity.setIndiApproveOrderNo(mstEmployeeModel.getIndiApproveOrderNo());
		objEntity.setApprovalByDdoDate(mstEmployeeModel.getApprovalByDdoDate());
		objEntity.setHraBasic(mstEmployeeModel.getHraBasic());
		// objEntity.setAppointment(mstEmployeeModel.getAppointment());
		objEntity.setTeaching(mstEmployeeModel.getTeaching());
		// Department Details End

		// Bank/DCPS/NPS/GPF Details Start
		objEntity.setBankCode(mstEmployeeModel.getBankId());
		objEntity.setIfscCode(mstEmployeeModel.getIfscCode());
		objEntity.setBankAcntNo(mstEmployeeModel.getBankAcntNo());
		objEntity.setBankBranchCode(mstEmployeeModel.getBankBranchId());
		objEntity.setDcpsgpfflag(mstEmployeeModel.getDcpsgpfflag());
		objEntity.setDcpsaccountmaintainby(mstEmployeeModel.getDcpsaccountmaintainby());
		objEntity.setPranNo(mstEmployeeModel.getPranNo());
		objEntity.setAccountmaintainby(mstEmployeeModel.getAccountmaintainby());
		objEntity.setPfseries(mstEmployeeModel.getPfseries());
		objEntity.setPfacno(mstEmployeeModel.getPfacno());
		objEntity.setPfdescription(mstEmployeeModel.getPfdescription());

		// Bank/DCPS/NPS/GPF Details End

		// GIS Details Start
		objEntity.setGisapplicable(mstEmployeeModel.getGisapplicable());
		objEntity.setGisgroup(mstEmployeeModel.getGisgroup());
		objEntity.setMembership_date(mstEmployeeModel.getMembership_date());
		objEntity.setGisRemark(mstEmployeeModel.getGisRemark());


		// GIS Details End

		// DCPS/NPS Nominee Details Start

		String[] lArrNomName = mstEmployeeModel.getStrArrNomineeName().split("~");
		String[] lArrAddress1 = mstEmployeeModel.getStrArrAddress().split("~");
		String[] lArrDateOfBirth = mstEmployeeModel.getStrArrDob().split("~");
		String[] lArrPercentShare = mstEmployeeModel.getStrArrPercentShare().split("~");
		String[] lArrRelationship = mstEmployeeModel.getStrArrRelationship().split("~");

		MstNomineeDetailsEntity[] lArrNomineeDtls = new MstNomineeDetailsEntity[lArrNomName.length];

		for (int i = 0; i < lArrNomName.length; i++) {
			if (!lArrNomName[i].equals("")) {
				MstNomineeDetailsEntity lObjNomineeDtls = new MstNomineeDetailsEntity();

				// lObjNomineeDtls.setDcpsEmpId(lObjEmpData);
				lObjNomineeDtls.setNomineename(lArrNomName[i]);
				lObjNomineeDtls.setNomineeaddress(lArrAddress1[i]);
				Date dtBirthDate = null;

				if (mstEmployeeModel.getStrArrDob() != null && !"".equals(mstEmployeeModel.getStrArrDob().trim())) {
					MstEmployeeModel mstEmployeeModel1 = new MstEmployeeModel();
					// String pattern = "yyyy-MM-dd";
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					try {
						Date date = formatter.parse(lArrDateOfBirth[i]);
						mstEmployeeModel1.setRdob(date);
						dtBirthDate = mstEmployeeModel1.getRdob();
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}

				lObjNomineeDtls.setDob(dtBirthDate);
				long lLngPercentShare = Long.parseLong(lArrPercentShare[i]);
				lObjNomineeDtls.setPercent_share(Integer.parseInt(lArrPercentShare[i]));
				lObjNomineeDtls.setRelation(lArrRelationship[i]);
				lObjNomineeDtls.setCreateddate(new Date());
				lObjNomineeDtls.setCreatedid(mstEmployeeModel.getCreatedUserId());
				lObjNomineeDtls.setIsactive("Y");
				lObjNomineeDtls.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				lObjNomineeDtls.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				// lObjNomineeDtls.setEmployeeId(objEntity.getEmployeeId());

				lArrNomineeDtls[i] = lObjNomineeDtls;
			}

		}

		// DCPS/NPS Nominee Details End

		objEntity.setEmpType("1");
		objEntity.setIsMappedWithNps('0');
		objEntity.setDdoCode(mstEmployeeModel.getDdoCode());
		objEntity.setBillGroupId(mstEmployeeModel.getBillgroupId());
		objEntity.setEmpClass(mstEmployeeModel.getEmpClass());

		objEntity.setDcpsAccMainAuthority(mstEmployeeModel.getDcpsAccMainAuthority());
		objEntity.setDcpsAccNo(mstEmployeeModel.getDcpsAccNo());
		
		if (!mstEmployeeModel.getAction().equals("saveAsDraft")) {
			objEntity.setIsActive(3l);
		} else {
			objEntity.setIsActive(0l);
		}
		objEntity.setSignatureAttachmentId(mstEmployeeModel.getSignatureAttachmentId());
		objEntity.setCreatedUserId(mstEmployeeModel.getCreatedUserId());
		objEntity.setCreatedDate(new Date());

		List reuslt = mstEmployeeRepo.saveEmployeeConfiguration(objEntity, mstEmployeeModel, lArrNomineeDtls);

		String[] saveimage = savePhotoSignature(files, mstEmployeeModel.getDeptNm(), (Long) reuslt.get(1));
		String imgres = mstEmployeeRepo.updateImagePath(saveimage[0].toString(), saveimage[1].toString(),
				(Long) reuslt.get(1));

		Serializable id = (Long) reuslt.get(0);

		// Annual Increment entry for employee Start
		// EmployeeIncrementEntity employeeIncrementEntity = new
		// EmployeeIncrementEntity();
		// employeeIncrementEntity.setSevaarthId(objEntity.getSevaarthId());
		// employeeIncrementEntity.setEmployeeId(objEntity.getEmployeeId());
		// employeeIncrementEntity.setCurrentBasicPay(0);
		// employeeIncrementEntity.setCurrentBasicLevelId(0);
		// employeeIncrementEntity.setPreBasicPay(0);
		// employeeIncrementEntity.setIncrementOrderNo("0");
		// employeeIncrementEntity.setIncrementOrderDate(new Date());
		// if (objEntity.getPayCommissionCode() == 8) {
		// employeeIncrementEntity.setIncrementBasicPaySal(objEntity.getSevenPcBasic().intValue());
		// } else {
		// employeeIncrementEntity.setIncrementBasicPaySal(objEntity.getBasicPay().intValue());
		// }
		// employeeIncrementEntity.setIncrementBasicLevelId(mstEmployeeModel.getSvnthpaybasic());
		// employeeIncrementEntity.setEffective_from_date(new Date());
		// employeeIncrementEntity.setTo_increment_date(new Date());
		// employeeIncrementEntity.setCreatedDate(new Date());
		// employeeIncrementEntity.setUpdatedDate(new Date());
		// employeeIncrementEntity.setRemark(null);
		// employeeIncrementEntity.setIsActive('1');
		// employeeIncrementEntity.setMonth(1);
		// employeeIncrementEntity.setCreatedUserId(1);
		// employeeIncrementEntity.setUpdatedUserId(1);
		// employeeIncrementEntity.setDdoCode(objEntity.getDdoCode());
		// // Annual Increment entry for employee End
		// mstEmployeeRepo.saveEmployeeIncrement(employeeIncrementEntity);

		return (long) id;
	}

	@Override
	public String[] savePhotoSignature(MultipartFile[] files, String DeptNm, Long empid) {
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
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
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
					rootPath += DeptNm + File.separator + empid;
					File dir = new File(rootPath);
					if (!dir.exists())
						dir.mkdirs();

					String name = "photo.jpg";
					File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

					res[0] = dir.getAbsolutePath() + File.separator + name;

				} else {
					res[0] = "";
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
					image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					image = ImageIO.read(in);
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
					rootPath += DeptNm + File.separator + empid;
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
					res[1] = "";
				}
			} catch (Exception e) {
				e.printStackTrace();
				res[1] = "";
			}
		}
		// signature code ended
		return res;
	}

	@Override
	public List<MstEmployeeModel> getApproveEmployeeDetails(String strddo, String language, long locid) {
		List<MstEmployeeEntity> listempentity = mstEmployeeRepo.getApproveEmployeeDetails(strddo);
		List<MstEmployeeModel> result = new ArrayList<MstEmployeeModel>();
		String DeptName = "";
		String SubDeptName = "";
		List<DDOScreenModel> lstDepartment = findDDOScreenDataTable(language, locid);
		for (Iterator iterator = lstDepartment.iterator(); iterator.hasNext();) {
			DDOScreenModel ddoScreenModel = (DDOScreenModel) iterator.next();
			DeptName = ddoScreenModel.getDeptName();
			SubDeptName = ddoScreenModel.getSubDeptName();
		}
		for (Iterator iterator = listempentity.iterator(); iterator.hasNext();) {
			MstEmployeeEntity mstEmployeeEntity = (MstEmployeeEntity) iterator.next();
			MstEmployeeModel mstEmployeeModel = new MstEmployeeModel();
			mstEmployeeModel.setEmployeeId(mstEmployeeEntity.getEmployeeId());
			mstEmployeeModel.setEmployeeFullName(mstEmployeeEntity.getEmployeeFullNameEn().toUpperCase());
			mstEmployeeModel.setSevaarthId(mstEmployeeEntity.getSevaarthId());
			mstEmployeeModel.setDesignationName(mstEmployeeRepo.getDesignationName(
					mstEmployeeEntity.getDesignationCode() != null ? mstEmployeeEntity.getDesignationCode().toString()
							: "0".toString())
					.toUpperCase());
			mstEmployeeModel.setDdoCode(mstEmployeeEntity.getDdoCode());
			mstEmployeeModel.setDepartmentNameEn(DeptName);
			mstEmployeeModel.setSubdeptNm(SubDeptName);
			result.add(mstEmployeeModel);
		}

		return result;

	}

	@Override
	public MstEmployeeModel getEmployeeinfo(Long employeeId) {
		// TODO Auto-generated method stub
		return mstEmployeeRepo.getEmployeeinfo(employeeId);
	}

	@Override
	public List<MstNomineeDetailsEntity> getNominees(String empId) {
		List<MstNomineeDetailsEntity> result = mstEmployeeRepo.getNominees(empId);
		return result;
	}

	@Override
	public List<Object[]> GetCurrentPostByLvlTwo(long designationId, String ddocode, long locId) {
		// TODO Auto-generated method stub
		List<Object[]> result = mstEmployeeRepo.GetCurrentPostByLvlTwo(designationId, ddocode, locId);
		return result;
	}

	@Override
	public long getLocationCode(String getLocationCode) {
		// TODO Auto-generated method stub
		return mstEmployeeRepo.getLocationCode(getLocationCode);
	}

	@Override
	public void saveGpfDetails(MstGpfDetailsEntity mstGpfDetailsEntity) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getCount(String tempSevarthEmpCode) {
		long res = mstEmployeeRepo.getCount(tempSevarthEmpCode);
		return res;
	}

	@Override
	public List<Long> approveEmployeeConfiguration(String empid, String sevaarthid, String dcpsgpfflg) {
		return mstEmployeeRepo.approveEmployeeConfiguration(empid, sevaarthid, dcpsgpfflg);
	}

	@Override
	public int getSevaarthid(String sevaarthid) {
		return mstEmployeeRepo.getSevaarthid(sevaarthid);
	}

	@Override
	public int checkSevaarthIdExistInGpfDetailMst(String sevaarthid) {
		// TODO Auto-generated method stub
		return mstEmployeeRepo.checkSevaarthIdExistInGpfDetailMst(sevaarthid);
	}

	@Override
	public List<QualificationEntity> getQualification(String language) {
		// TODO Auto-generated method stub
		return mstEmployeeRepo.getQualification(language);
	}

	@Override
	public List<MstEmployeeModel> getDcpsEmployeeDetails(String strddo, String language, long locId,OrgUserMst messages) {
		// TODO Auto-generated method stub
		List<MstEmployeeEntity> listempentity = mstEmployeeRepo.getDcpsEmployeeDetails(strddo,messages);
		List<MstEmployeeModel> result = new ArrayList<MstEmployeeModel>();
		String DeptName = "";
		List<DDOScreenModel> lstDepartment = findDDOScreenDataTable(language, locId);
		for (Iterator iterator = lstDepartment.iterator(); iterator.hasNext();) {
			DDOScreenModel ddoScreenModel = (DDOScreenModel) iterator.next();
			DeptName = ddoScreenModel.getDeptName();
		}
		for (Iterator iterator = listempentity.iterator(); iterator.hasNext();) {
			MstEmployeeEntity mstEmployeeEntity = (MstEmployeeEntity) iterator.next();
			MstEmployeeModel mstEmployeeModel = new MstEmployeeModel();
			mstEmployeeModel.setEmployeeId(mstEmployeeEntity.getEmployeeId());
			mstEmployeeModel.setEmployeeFullName(mstEmployeeEntity.getEmployeeFullNameEn());
			mstEmployeeModel.setSevaarthId(mstEmployeeEntity.getSevaarthId());
			mstEmployeeModel.setDesignationName(mstEmployeeRepo.getDesignationName(
					mstEmployeeEntity.getDesignationCode() != null ? mstEmployeeEntity.getDesignationCode().toString()
							: "0".toString())
					.toUpperCase());
			mstEmployeeModel.setDdoCode(mstEmployeeEntity.getDdoCode());
			mstEmployeeModel.setDob(mstEmployeeEntity.getDob());
			mstEmployeeModel.setGender(mstEmployeeEntity.getGender());
			mstEmployeeModel.setDepartmentNameEn(DeptName);
			mstEmployeeModel.setReptDdoCode(mstEmployeeEntity.getReptDdoCode());

			result.add(mstEmployeeModel);
		}
		// List<MstEmployeeEntisty>
		// listempentity=mtEmployeeRepo.getEmployeeDetails(strddo);

		return result;

	}

	@Override
	public Character getLastDigit(String lStrDCPSId) {

		Character LastDigit = null;
		Map<String, Integer> MappingData = getMappingData();

		char[] lArrStrDcpsId = lStrDCPSId.toCharArray();

		Integer[] lArrIntMultiplication = { 1, 3, 5, 7, 1, 3, 5, 7, 1, 3, 5, 7, 1, 3, 5, 7, 1, 3, 5 };

		Integer lIntTotal = 0;

		for (Integer index = 0; index < lArrStrDcpsId.length - 1; index++) {
			String lStrMappedKey = Character.toString(lArrStrDcpsId[index]);
			Integer lIntMap = MappingData.get(lStrMappedKey);
			lIntTotal = lIntTotal + (lIntMap * lArrIntMultiplication[index]);
		}

		Integer lIntLastDigit = 26 - (lIntTotal % 26);
		LastDigit = (char) (64 + lIntLastDigit);

		return LastDigit;
	}

	@Override
	public Map getMappingData() {

		Map<String, Integer> MappingData = new HashMap<String, Integer>();

		MappingData.put("0", 0);
		MappingData.put("1", 1);
		MappingData.put("2", 2);
		MappingData.put("3", 3);
		MappingData.put("4", 4);
		MappingData.put("5", 5);
		MappingData.put("6", 6);
		MappingData.put("7", 7);
		MappingData.put("8", 8);
		MappingData.put("9", 9);
		MappingData.put("A", 10);
		MappingData.put("B", 11);
		MappingData.put("C", 12);
		MappingData.put("D", 13);
		MappingData.put("E", 14);
		MappingData.put("F", 15);
		MappingData.put("G", 16);
		MappingData.put("H", 17);
		MappingData.put("I", 18);
		MappingData.put("J", 19);
		MappingData.put("K", 20);
		MappingData.put("L", 21);
		MappingData.put("M", 22);
		MappingData.put("N", 23);
		MappingData.put("O", 24);
		MappingData.put("P", 25);
		MappingData.put("Q", 26);
		MappingData.put("R", 27);
		MappingData.put("S", 28);
		MappingData.put("T", 29);
		MappingData.put("U", 30);
		MappingData.put("V", 31);
		MappingData.put("W", 32);
		MappingData.put("X", 33);
		MappingData.put("Y", 34);
		MappingData.put("Z", 35);
		MappingData.put(".", 36);

		return MappingData;
	}

	@Override
	public List<Long> approveDcpsEmployeeConfiguration(String empid, String Dcpsnumber, String sevaarthid,
			String dcpsgpfflg) {
		List<Long> result = mstEmployeeRepo.approveDcpsEmployeeConfiguration(empid, Dcpsnumber, sevaarthid, dcpsgpfflg);
		return result;
	}

	@Override
	public String createNewUser(String sevaarthId, OrgUserMst message, MstEmployeeModel mstEmployeeModel) {
		OrgUserMst lObjUserMst = new OrgUserMst();
		CmnLookupMst lObjCmnLookupMst = cmnLookupMstRepository.findByLookupId(1l);

		Session ghibSession = entityManager.unwrap(Session.class);

		Long lLngUserId = null;
		CmnLocationMst lObjCmnLocationMst = new CmnLocationMst();

		lObjUserMst.setUserName(sevaarthId);
		lObjUserMst.setDdoCode(sevaarthId);
		lObjUserMst.setPassword(passwordEncoder.encode("ifms123"));
		Optional<MstRoleEntity> findById = mstRoleRepo.findById(4);
		lObjUserMst.setMstRoleEntity(findById.get());
		lObjUserMst.setCmnLookupMst(lObjCmnLookupMst);

		lObjUserMst.setStartDate(new Timestamp(new Date().getTime()));
		lObjUserMst.setActivateFlag(1l);
		lObjUserMst.setAppCode(1);

		lObjUserMst.setCreatedDate(new Timestamp(new Date().getTime()));

		lObjUserMst.setCreatedBy(message.getCreatedBy());
		lObjUserMst.setCreatedByPost(message.getCreatedByPost());
		lObjUserMst.setSecretQueCode("Secret_Other");
		lObjUserMst.setSecretQueOther("Secret_Other");// TODO -- Needs to Change
		lObjUserMst.setSecretAnswer("ifms");
		// Serializable save = ghibSession.save(lObjUserMst);

		// Serializable save = ghibSession.save(lObjUserMst);
		OrgUserMst savedetails = mstEmployeeRepo.saveUserInfo(lObjUserMst);
		OrgPostMst orgpostmst = orgPostMstRepository.findByPostId(mstEmployeeModel.getPostdetailid());
		OrgUserpostRlt lObjOrgUserpostRlt = new OrgUserpostRlt();

		lObjOrgUserpostRlt.setOrgUserMst(savedetails);
		lObjOrgUserpostRlt.setStartDate(new Timestamp(new Date().getTime()));
		lObjOrgUserpostRlt.setActivateFlag(1l);
		lObjOrgUserpostRlt.setOrgPostMstByPostId(orgpostmst);
		lObjOrgUserpostRlt.setCreatedByPost(message.getCreatedByPost());
		lObjOrgUserpostRlt.setCreatedBy(message);
		lObjOrgUserpostRlt.setCreatedDate(new Timestamp(new Date().getTime()));
		lObjOrgUserpostRlt.setCmnLookupUserPostType(lObjCmnLookupMst);
		// lObjOrgUserpostRlt.setCmnLocationMst(postId.getLookupId());
		ghibSession.save(lObjOrgUserpostRlt);

		long user_id = savedetails.getUserId();

		String save = mstEmployeeRepo.saveUserId(sevaarthId, user_id);
		return sevaarthId;
	}
	@Override
	public MstEmployeeDetailEntity updateEmployeeDetails(Long empid) {
		// TODO Auto-generated method stub
		return mstEmployeeRepo.updateEmployeesDetails(empid);
	}
	

	@Override
	public Object findAllEmployeesByDDOName(String userName) {
		List<Object[]> lstprop = mstEmployeeRepo.findAllEmployeesByDDOName(userName);
		List<MstEmployeeModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstEmployeeModel obj = new MstEmployeeModel();
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[0]));
				obj.setEmployeeFullName(StringHelperUtils.isNullString(objLst[1]));
				obj.setDesignationName(StringHelperUtils.isNullString(objLst[2]));
				obj.setDepartmentNameEn(StringHelperUtils.isNullString(objLst[3]));
				obj.setEmployeeId(StringHelperUtils.isNullLong(objLst[4]));
				obj.setPayCommissionCode(StringHelperUtils.isNullLong(objLst[5]));
				obj.setPayCommissionName(StringHelperUtils.isNullString(objLst[6]));
				obj.setEmpServiceEndDate(StringHelperUtils.isNullDate(objLst[8]));
				obj.setBillDesc(StringHelperUtils.isNullString(objLst[9]));
				if (objLst[7] != null && !objLst[7].equals("")) {
					if (objLst[7].equals('Y')) {
						obj.setDcpsgpfflag("DCPS");
					} else {
						obj.setDcpsgpfflag("GPF");
					}
				}
				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<MstEmployeeModel> findDraftCaseList(OrgUserMst messages, Long CASE_STATUS) {
		List<Object[]> lstprop = mstEmployeeRepo.findDraftCaseList(messages, CASE_STATUS);
		List<MstEmployeeModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				MstEmployeeModel obj = new MstEmployeeModel();
				obj.setEmployeeFullName(StringHelperUtils.isNullString(objLst[0]));
				obj.setDob(StringHelperUtils.isNullDate(objLst[1]));
				if (objLst[2] instanceof BigInteger) {
					obj.setEmployeeId(StringHelperUtils.isNullBigInteger(objLst[2]).longValue());
				}else {
					obj.setEmployeeId(StringHelperUtils.isNullLong(objLst[2]));
				}
				obj.setRemark(StringHelperUtils.isNullString(objLst[3]));
				if (objLst[4] instanceof Integer) {
				    obj.setIsActive(StringHelperUtils.isNullLong(Long.valueOf((Integer) objLst[4])));
				} else if (objLst[4] instanceof BigInteger) {
				    obj.setIsActive(StringHelperUtils.isNullBigInteger((BigInteger) objLst[4]).longValue());
				} else {
				    obj.setIsActive(StringHelperUtils.isNullLong(objLst[4]));
				}

				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public long updateDraftCase(@Valid MstEmployeeModel mstEmployeeModel, MultipartFile[] files) {
		MstEmployeeEntity objEntity = new MstEmployeeEntity();
		if (mstEmployeeModel.getEmployeeId() != null) {
			objEntity = mstEmployeeRepo.findbyemplid(mstEmployeeModel.getEmployeeId());
		}

		Session currentSession = entityManager.unwrap(Session.class);
		MstNomineeDetailsEntity lObjNomineeDtls = null;
		MstNomineeDetailsEntity[] lArrNomineeDtls = null;
		if (objEntity != null) {
			objEntity.setUidNo(mstEmployeeModel.getUidNo());
			objEntity.setEidNo(mstEmployeeModel.getEidNo());
			objEntity.setSalutation(mstEmployeeModel.getSalutation());
			objEntity.setEmployeeFullNameEn(mstEmployeeModel.getEmployeeFullNameEn().toUpperCase());
			objEntity.setEmployeeFNameEn(mstEmployeeModel.getEmployeeFNameEn().toUpperCase());
			objEntity.setEmployeeMNameEn(mstEmployeeModel.getEmployeeMNameEn().toUpperCase());
			objEntity.setEmployeeLNameEn(mstEmployeeModel.getEmployeeLNameEn().toUpperCase());
			objEntity.setEmployeeFullNameMr(mstEmployeeModel.getEmployeeFullNameMr());
			objEntity.setEmployeeFNameMr(mstEmployeeModel.getEmployeeFNameMr());
			objEntity.setEmployeeLNameMr(mstEmployeeModel.getEmployeeLNameMr());
			objEntity.setEmployeeMotherName(mstEmployeeModel.getEmployeeMotherName());
			objEntity.setBuckleNo(mstEmployeeModel.getBuckleNo());
			objEntity.setGender(mstEmployeeModel.getGender());
			objEntity.setReligionCode(mstEmployeeModel.getReligionCode());
			objEntity.setMaritalStatus(mstEmployeeModel.getMaritalStatus());
			objEntity.setEmployeeMNameMr(mstEmployeeModel.getEmployeeMNameMr());
			objEntity.setDob(mstEmployeeModel.getDob());
			objEntity.setDoj(mstEmployeeModel.getDoj());
			objEntity.setAddress1(mstEmployeeModel.getAddress1().toUpperCase());
			objEntity.setAddress2(mstEmployeeModel.getAddress2().toUpperCase());
			objEntity.setStateCode(mstEmployeeModel.getStateCode());
			objEntity.setDistrictCode(mstEmployeeModel.getDistrictCode());
			objEntity.setPinCode(mstEmployeeModel.getPinCode());
			objEntity.setPhysicallyHandicapped(mstEmployeeModel.getPhysicallyHandicapped());
			objEntity.setMobileNo1(mstEmployeeModel.getMobileNo1());
			objEntity.setEmailId(mstEmployeeModel.getEmailId());
			objEntity.setPanNo(mstEmployeeModel.getPanNo().toUpperCase());

			// Employee Details End

			// Department Details Start
			objEntity.setParentFieldDepartmentId(mstEmployeeModel.getParentFieldDepartmentId());
			objEntity.setSubDeptId(mstEmployeeModel.getParentFieldDepartmentId());
			objEntity.setIsChangeParentDepartment(mstEmployeeModel.getIsChangeParentDepartment());
			objEntity.setReasonForChngParentFieldDept(mstEmployeeModel.getReasonForChngParentFieldDept());
			objEntity.setCadreCode(mstEmployeeModel.getCadreId());
			objEntity.setEmpClass(mstEmployeeModel.getEmpClass());
			objEntity.setSuperAnnAge(mstEmployeeModel.getSuperannuationage());
			objEntity.setEmpServiceEndDate(mstEmployeeModel.getSuperAnnDate()); // by default set to retirement date
			objEntity.setPercentageOfBasic(100l);
			objEntity.setSuperAnnDate(mstEmployeeModel.getSuperAnnDate());
			objEntity.setPayCommissionCode(mstEmployeeModel.getPayCommissionCode());
			objEntity.setFirstDesignationCode(mstEmployeeModel.getFirstDesignationId());
			objEntity.setDesignationCode(mstEmployeeModel.getDesignationId());
			objEntity.setPayscalelevelId(mstEmployeeModel.getPayscalelevelId());
			if (mstEmployeeModel.getPayscalelevelId() != null)
				objEntity.setSevenPcLevel(Long.valueOf(mstEmployeeModel.getPayscalelevelId()));
			else
				objEntity.setSevenPcLevel(0l);
			objEntity.setSvnthpaybasic(mstEmployeeModel.getSvnthpaybasic());
			objEntity.setPayScaleCode(mstEmployeeModel.getPayScaleCode());
			objEntity.setPayInPayBand(mstEmployeeModel.getPayInPayBand());
			objEntity.setGradePay(mstEmployeeModel.getGradePay());

			if (objEntity.getPayCommissionCode() == 700016) {
				objEntity.setBasicPay(
						mstEmployeeModel.getBasicPay() == null ? 0 : mstEmployeeModel.getBasicPay().doubleValue());
			} else {
				objEntity.setSevenPcBasic(mstEmployeeModel.getSevenPcBasic() == null ? 0
						: mstEmployeeModel.getSevenPcBasic().doubleValue());
			}

			objEntity.setPostdetailid(mstEmployeeModel.getPostdetailid());
			objEntity.setDepartmentNameEn(mstEmployeeModel.getDepartmentNameEn());
			objEntity.setDtInitialAppointmentParentInst(mstEmployeeModel.getDtInitialAppointmentParentInst());
			objEntity.setInstituteAdd(mstEmployeeModel.getInstituteAdd());
			objEntity.setInstName(mstEmployeeModel.getInstName());
			objEntity.setMobileNo2(mstEmployeeModel.getMobileNo2());
			objEntity.setInstemail(mstEmployeeModel.getInstemail());
			objEntity.setDtJoinCurrentPost(mstEmployeeModel.getDtJoinCurrentPost());
			objEntity.setRemark(mstEmployeeModel.getRemark());
			objEntity.setCityClass(mstEmployeeModel.getCityClass());
			objEntity.setIndiApproveOrderNo(mstEmployeeModel.getIndiApproveOrderNo());
			objEntity.setApprovalByDdoDate(mstEmployeeModel.getApprovalByDdoDate());
			objEntity.setHraBasic(mstEmployeeModel.getHraBasic());
			// Department Details End

			// Bank/DCPS/NPS/GPF Details Start
			objEntity.setBankCode(mstEmployeeModel.getBankId());
			objEntity.setIfscCode(mstEmployeeModel.getIfscCode());
			objEntity.setBankAcntNo(mstEmployeeModel.getBankAcntNo());
			objEntity.setBankBranchCode(mstEmployeeModel.getBankBranchId());
			objEntity.setDcpsgpfflag(mstEmployeeModel.getDcpsgpfflag());
			objEntity.setDcpsaccountmaintainby(mstEmployeeModel.getDcpsaccountmaintainby());
			objEntity.setPranNo(mstEmployeeModel.getPranNo());
			objEntity.setAccountmaintainby(mstEmployeeModel.getAccountmaintainby());
			objEntity.setPfseries(mstEmployeeModel.getPfseries());
			objEntity.setPfacno(mstEmployeeModel.getPfacno());
			objEntity.setPfdescription(mstEmployeeModel.getPfdescription());

			// Bank/DCPS/NPS/GPF Details End

			// GIS Details Start
			objEntity.setGisapplicable(mstEmployeeModel.getGisapplicable());
			objEntity.setGisgroup(mstEmployeeModel.getGisgroup());
			objEntity.setMembership_date(mstEmployeeModel.getMembership_date());
			objEntity.setGisRemark(mstEmployeeModel.getGisRemark());
			objEntity.setGiscatagory(mstEmployeeModel.getGiscatagory());
			objEntity.setBegisCatg(mstEmployeeModel.getBegisCatg());
			// GIS Details End

			// DCPS/NPS Nominee Details Start

			String[] lArrNomName = mstEmployeeModel.getStrArrNomineeName().split("~");
			String[] lArrAddress1 = mstEmployeeModel.getStrArrAddress().split("~");
			String[] lArrDateOfBirth = mstEmployeeModel.getStrArrDob().split("~");
			String[] lArrPercentShare = mstEmployeeModel.getStrArrPercentShare().split("~");
			String[] lArrRelationship = mstEmployeeModel.getStrArrRelationship().split("~");

			lArrNomineeDtls = new MstNomineeDetailsEntity[lArrNomName.length];

			for (int i = 0; i < lArrNomName.length; i++) {
				if (!lArrNomName[i].equals("")) {
					lObjNomineeDtls = new MstNomineeDetailsEntity();

					// lObjNomineeDtls.setDcpsEmpId(lObjEmpData);
					lObjNomineeDtls.setNomineename(lArrNomName[i]);
					lObjNomineeDtls.setNomineeaddress(lArrAddress1[i]);
					Date dtBirthDate = null;

					if (mstEmployeeModel.getStrArrDob() != null && !"".equals(mstEmployeeModel.getStrArrDob().trim())) {
						MstEmployeeModel mstEmployeeModel1 = new MstEmployeeModel();
						// String pattern = "yyyy-MM-dd";
						SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						try {
							Date date = formatter.parse(lArrDateOfBirth[i]);
							mstEmployeeModel1.setRdob(date);
							dtBirthDate = mstEmployeeModel1.getRdob();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}

					}
					lObjNomineeDtls.setDob(dtBirthDate);
					long lLngPercentShare = Long.parseLong(lArrPercentShare[i]);
					lObjNomineeDtls.setPercent_share(Integer.parseInt(lArrPercentShare[i]));
					lObjNomineeDtls.setRelation(lArrRelationship[i]);
					lObjNomineeDtls.setCreateddate(new Date());
					lObjNomineeDtls.setCreatedid(mstEmployeeModel.getCreatedUserId());
					lObjNomineeDtls.setIsactive("Y");
					lObjNomineeDtls.setUpdatedate(mstEmployeeModel.getUpdatedDate());
					lObjNomineeDtls.setUpdateid(mstEmployeeModel.getUpdatedUserId());
					lObjNomineeDtls.setEmployeeId(objEntity.getEmployeeId());
					lArrNomineeDtls[i] = lObjNomineeDtls;
				}

			}

			// DCPS/NPS Nominee Details End

			
			
			objEntity.setEmpType("1");
			objEntity.setIsMappedWithNps('0');
			objEntity.setBillGroupId(mstEmployeeModel.getBillgroupId());
			if (mstEmployeeModel.getAction().equals("saveAsDraft")) {
				objEntity.setIsActive(0l);
			}else {
				objEntity.setIsActive(3l);
			}
		
			objEntity.setSignatureAttachmentId(mstEmployeeModel.getSignatureAttachmentId());
			objEntity.setCreatedUserId(mstEmployeeModel.getCreatedUserId());
			objEntity.setCreatedDate(new Date());

			if (mstEmployeeModel.getSevaarthId() == null || mstEmployeeModel.getSevaarthId().equals("")) {
				String sevaarthGenration = getCrearteSevaartIh(mstEmployeeModel);
				objEntity.setSevaarthId(sevaarthGenration);
			}

		}

		if (mstEmployeeModel.getDcpsid() != null) {
			MstDcpsDetailsEntity objEntity1 = mstEmployeeRepo.findbyDcpsid(mstEmployeeModel.getDcpsid());
			objEntity1.setIsactive(mstEmployeeModel.getDcpsgpfflag());
			objEntity1.setAccountmaintainby(mstEmployeeModel.getDcpsaccountmaintainby());
			objEntity1.setCreateddate(new Date());
			objEntity1.setCreatedid(1l);
			objEntity1.setPfacno(mstEmployeeModel.getPfacno());
			objEntity1.setEmployeeId(objEntity.getEmployeeId());
			currentSession.update(objEntity1);
		} else if (mstEmployeeModel.getDcpsaccountmaintainby() != null)
			if (!mstEmployeeModel.getDcpsaccountmaintainby().equals("0")) {
				MstDcpsDetailsEntity objEntity1 = new MstDcpsDetailsEntity();
				objEntity1.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				objEntity1.setAccountmaintainby(mstEmployeeModel.getDcpsaccountmaintainby());
				objEntity1.setCreateddate(new Date());
				objEntity1.setCreatedid(1l);
				objEntity1.setPfacno(mstEmployeeModel.getPfacno());
				objEntity1.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity1);
			}

		if (mstEmployeeModel.getGpf_id() != null) {
			MstGpfDetailsEntity objEntity2 = mstEmployeeRepo.findbyGPFid(mstEmployeeModel.getGpf_id());
			objEntity2.setAccountmaintainby(mstEmployeeModel.getAccountmaintainby());
			objEntity2.setCreateddate(new Date());
			objEntity2.setCreatedid(1l);
			objEntity2.setIsactive(mstEmployeeModel.getDcpsgpfflag());
			objEntity2.setPfacno(mstEmployeeModel.getPfacno());
			objEntity2.setPfdescription(mstEmployeeModel.getPfdescription());
			objEntity2.setCreatedid(mstEmployeeModel.getUpdatedUserId());
			objEntity2.setCreateddate(new Date());
			objEntity2.setEmployeeId(objEntity.getEmployeeId());
			currentSession.update(objEntity2);
		} else if (mstEmployeeModel.getAccountmaintainby() != null && mstEmployeeModel.getPfacno() != null
				&& mstEmployeeModel.getPfseries() != null)
			if (!mstEmployeeModel.getAccountmaintainby().equals("0") && !mstEmployeeModel.getPfacno().equals("")
					&& !mstEmployeeModel.getPfseries().equals("0")) {
				MstGpfDetailsEntity objEntity2 = new MstGpfDetailsEntity();
				objEntity2.setAccountmaintainby(mstEmployeeModel.getAccountmaintainby());
				objEntity2.setCreateddate(new Date());
				objEntity2.setCreatedid(1l);
				objEntity2.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				objEntity2.setPfacno(mstEmployeeModel.getPfacno());
				objEntity2.setPfdescription(mstEmployeeModel.getPfseries());
				objEntity2.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				objEntity2.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity2.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity2);
			}
		if (mstEmployeeModel.getGisid() != null) {
			MstGisdetailsEntity objEntity3 = mstEmployeeRepo.findbyGisid(mstEmployeeModel.getGisid());
			objEntity3.setCreateddate(new Date());
			objEntity3.setCreatedid(1l);
			objEntity3.setGisapplicable(mstEmployeeModel.getGisapplicable());
			objEntity3.setGisgroup(mstEmployeeModel.getGisgroup());
			objEntity3.setIsactive("Y");
			objEntity3.setMembership_date(mstEmployeeModel.getMembership_date());
			objEntity3.setEmployeeId(objEntity.getEmployeeId());
			currentSession.update(objEntity3);
		} else if (mstEmployeeModel.getGisapplicable() != null && mstEmployeeModel.getGisgroup() != null
				&& mstEmployeeModel.getMembership_date() != null)
			if (!mstEmployeeModel.getGisapplicable().equals("0") && !mstEmployeeModel.getGisgroup().equals("0")
					&& !mstEmployeeModel.getMembership_date().equals("0")) {
			
				MstGisdetailsEntity objEntity3 = new MstGisdetailsEntity();
				objEntity3.setCreateddate(new Date());
				objEntity3.setCreatedid(mstEmployeeModel.getCreatedUserId());
				objEntity3.setGisapplicable(mstEmployeeModel.getGisapplicable());
				objEntity3.setGisgroup(mstEmployeeModel.getGisgroup());
				objEntity3.setIsactive("Y");
				objEntity3.setMembership_date(mstEmployeeModel.getMembership_date());
				objEntity3.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				objEntity3.setUpdateid(mstEmployeeModel.getUpdatedUserId());
				objEntity3.setEmployeeId(objEntity.getEmployeeId());
				currentSession.save(objEntity3);
			}
		String[] saveimage = savePhotoSignature(files, mstEmployeeModel.getDeptNm(), mstEmployeeModel.getEmployeeId(),
				mstEmployeeModel.getPhotoAttachmentId(), mstEmployeeModel.getSignatureAttachmentId());
		objEntity.setPhotoAttachmentId(saveimage[0].toString());
		objEntity.setSignatureAttachmentId(saveimage[1]);

		Serializable id = mstEmployeeRepo.updateEmployeeConfiguration(objEntity, mstEmployeeModel, lArrNomineeDtls);

		return (int) id;
	}

	

	@Override
	public Integer deleteEmployeesByIds(List<Long> employeeIds, OrgUserMst orgUserMst) {
		return mstEmployeeRepo.deleteEmployeesByIds(employeeIds, orgUserMst);
	}

	@Override
	public List<Long> rejectEmployeeConfiguration(String empid) {
		return mstEmployeeRepo.rejectEmployeeConfiguration(empid);
	}


	@Override
	public List<CmnLookupMst> getLookupValuesForParentAG(Long agType) {
		return mstEmployeeRepo.getLookupValuesForParentAG(agType);
	}

	@Override
	public String approveDcpsEmpByDdo(String empid, OrgUserMst message) {
		return mstEmployeeRepo.approveDcpsEmpByDdo(empid,message);
	}

	@Override
	public List<ZpRltDdoMap> findDdoByReptDdoCode(String reptDdoCode) {
		return mstEmployeeRepo.findDdoByReptDdoCode(reptDdoCode);
	}

	
	
	
}