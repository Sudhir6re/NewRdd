package com.mahait.gov.in.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.MstDcpsDetailsEntity;
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

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;

@Repository
@SuppressWarnings("unchecked")
public class EmpChangeDetailsRepoImpl implements EmpChangeDetailsRepo {

	@PersistenceContext
	EntityManager manager;

	@Override
	public List<Object[]> findEmpforChangeDtls(String userName) {
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select employee_full_name_en,sevaarth_id,dob,gender,employee_id from employee_mst where ddo_code = '"
				+ userName + "' and is_active = '1'";
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public MstEmployeeModel getEmpData(int empId) {
		Session currentSession = manager.unwrap(Session.class);

		String HQL = "select a.salutation,a.employee_f_name_en,a.employee_m_name_en,a.employee_l_name_en,a.employee_full_name_mr,a.uid_no,a.gender,a.marital_status,a.dob,a.doj,a.address1,"
				+ "a.address2,a.address3,a.mobile_no1,a.mobile_no2,a.pan_no,a.email_id,a.pincode,a.village_name,a.pyhical_handicapped,a.cadre_code,a.emp_class,a.super_ann_date,a.pay_commission_code,"
				+ "a.designation_code,a.pay_scale_code,a.pay_scale_level_id,case when a.pay_commission_code =8 then seven_pc_level else a.grade_pay end,case when a.pay_commission_code =8 then a.seven_pc_basic else a.basic_pay end,a.post_detail_id,a.admin_department_code,a.appointment_date,b.superannuation_age,a.bank_code,a.bank_branch_code"
				+ ",a.bank_acnt_no ,a.ifsc_code,a.dcps_gpf_flag,a.eid_no,a.employee_full_name_en,trim(a.state_code) as state,trim(a.district_code) as district,a.pay_in_pay_band,c.tel_no2,c.email,c.remarks,a.order_no,a.order_date,a.employee_id,a.sevaarth_id"
				+ ",d.nominee_name,d.nominee_address,d.relation,d.percent_share,c.ddo_city_category,e.gis_applicable,e.gis_group,a.landline_no,e.membership_date from employee_mst a  "
				+ "inner join cadre_mst b on a.cadre_code = b.cadre_code inner join ddo_reg_mst c on a.ddo_code = c.ddo_code left join nominee_details_mst d on a.employee_id = d.employee_id left join gis_details_mst e on a.employee_id = e.employee_id where a.employee_id ="
				+ empId;

		Query query = currentSession.createNativeQuery(HQL);
		List<Object[]> lstprop = query.list();
		MstEmployeeModel lstObj = new MstEmployeeModel();
		int i = 1;
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				lstObj.setSalutation(StringHelperUtils.isNullLong(objLst[0]));
				lstObj.setEmployeeFNameEn(StringHelperUtils.isNullString(objLst[1]));
				lstObj.setEmployeeMNameEn(StringHelperUtils.isNullString(objLst[2]));
				lstObj.setEmployeeLNameEn(StringHelperUtils.isNullString(objLst[3]));
				lstObj.setEmployeeFullNameMr(StringHelperUtils.isNullString(objLst[4]));

				if (objLst[5] != null) {

					BigInteger b1 = StringHelperUtils.isNullBigInteger(objLst[5]);
					String uidNOs = b1.toString();
					if (uidNOs.substring(0, 4) != null) {
						lstObj.setUidNo1(uidNOs.substring(0, 4));
					}

					if (uidNOs.substring(4, 8) != null) {
						lstObj.setUidNo2(uidNOs.substring(4, 8));
					}

					if (uidNOs.substring(8, 12) != null) {
						lstObj.setUidNo3(uidNOs.substring(8, 12));
					}

					/*
					 * BigDecimal b2=(BigDecimal) objLst[5]; Long uidNo=b2.longValue();
					 * lstObj.setUidNo(StringHelperUtils.isNullLong(uidNo));
					 */

				}
				lstObj.setGender(StringHelperUtils.isNullCharacter(objLst[6]));
				lstObj.setMaritalStatus(StringHelperUtils.isNullCharacter(objLst[7]));
				lstObj.setDob(StringHelperUtils.isNullDate(objLst[8]));
				lstObj.setDoj(StringHelperUtils.isNullDate(objLst[9]));
				lstObj.setAddress1(StringHelperUtils.isNullString(objLst[10]));
				lstObj.setAddress2(StringHelperUtils.isNullString(objLst[11]));
				lstObj.setAddress3(StringHelperUtils.isNullString(objLst[12]));
				BigDecimal bg1 = (BigDecimal) (objLst[13]);
				Long mobNo1 = bg1.longValue();
				lstObj.setMobileNo1(StringHelperUtils.isNullLong(mobNo1));
				lstObj.setMobileNo2(StringHelperUtils.isNullLong(objLst[14]));
				lstObj.setPanNo(StringHelperUtils.isNullString(objLst[15]));
				lstObj.setEmailId(StringHelperUtils.isNullString(objLst[16]));
				lstObj.setPinCode(StringHelperUtils.isNullLong(Integer.parseInt((String) objLst[17])));
				lstObj.setVillageName(StringHelperUtils.isNullString(objLst[18]));
				lstObj.setPhysicallyHandicapped(StringHelperUtils.isNullString(String.valueOf(objLst[19])));
				lstObj.setPhysicallyHandicapped(StringHelperUtils.isNullString(objLst[19]));
				lstObj.setCadreId(StringHelperUtils.isNullLong(objLst[20]));
				lstObj.setEmpClass(StringHelperUtils.isNullLong(objLst[21]));
				lstObj.setSuperAnnDate(StringHelperUtils.isNullDate(objLst[22]));
				lstObj.setPayCommissionCode(StringHelperUtils.isNullLong(objLst[23]));
				lstObj.setDesignationId(StringHelperUtils.isNullLong(objLst[24]));
				lstObj.setPayScaleCode(StringHelperUtils.isNullLong(objLst[25]));
				lstObj.setPayscalelevelId(StringHelperUtils.isNullString(objLst[26]));
				// lstObj.setGradePay(StringHelperUtils.isNullInt(objLst[27]));
				BigInteger b1 = (BigInteger) (objLst[28]);
				int intValueOfb1 = b1.intValue();
				lstObj.setBasicPay(StringHelperUtils.isNullDouble(intValueOfb1));
				lstObj.setPostdetailid(StringHelperUtils.isNullLong(objLst[29]));
				lstObj.setAdminDepartmentId(StringHelperUtils.isNullLong(objLst[30]));
				lstObj.setAppointmentDate(((Date) objLst[31]));
				BigDecimal b = (BigDecimal) (objLst[32]);
				String age = b.toString();
				lstObj.setSuperannuationage(StringHelperUtils.isNullLong(age));
				lstObj.setSuperannuationage(StringHelperUtils.isNullLong(age));
				lstObj.setBankId(StringHelperUtils.isNullLong(objLst[33]));
				lstObj.setBankBranchId(StringHelperUtils.isNullLong(objLst[34]));
				lstObj.setBankAcntNo(StringHelperUtils.isNullString(objLst[35]));
				lstObj.setIfscCode(StringHelperUtils.isNullString(objLst[36]));
				lstObj.setDcpsgpfflag(StringHelperUtils.isNullString(String.valueOf(objLst[37])));
				lstObj.setEidNo(StringHelperUtils.isNullString(objLst[38]));
				lstObj.setEmployeeFullNameEn(StringHelperUtils.isNullString(objLst[39]));
				lstObj.setStateCode(StringHelperUtils.isNullLong(Integer.valueOf((String) objLst[40])));
				if (objLst[41] != null) {
					lstObj.setDistrictCode(StringHelperUtils.isNullLong(Integer.valueOf((String) objLst[41])));
					lstObj.setStateCode(StringHelperUtils.isNullLong(objLst[40]));
					if (objLst[41] != null) {
						lstObj.setDistrictCode(StringHelperUtils.isNullLong(objLst[41]));
					}
					lstObj.setCurrentOfficeId(StringHelperUtils.isNullLong(objLst[42]));

					lstObj.setInsttelnotwo(StringHelperUtils.isNullString(objLst[43].toString()));
					lstObj.setInstemail(StringHelperUtils.isNullString(objLst[44]));
					lstObj.setRemark(StringHelperUtils.isNullString(objLst[45]));

					lstObj.setOrderNo(StringHelperUtils.isNullLong(objLst[46]));
					lstObj.setApprovalByDdoDate(StringHelperUtils.isNullDate(objLst[47]));
					lstObj.setEmployeeId(StringHelperUtils.isNullLong(objLst[48]));
					lstObj.setSevaarthId(StringHelperUtils.isNullString(objLst[49]));

					lstObj.setNomineename(StringHelperUtils.isNullString(objLst[50]));
					lstObj.setNomineeaddress(StringHelperUtils.isNullString(objLst[51]));
					lstObj.setRelation(StringHelperUtils.isNullString(objLst[52]));

					if (objLst[53] != null) {
						BigDecimal d = (BigDecimal) (objLst[53]);
						int percent = d.intValue();
						lstObj.setPercent_share(StringHelperUtils.isNullLong(percent));
					}
					lstObj.setCityClass(StringHelperUtils.isNullString(objLst[54]));

					char c = (char) objLst[55];
					int gisAppl = Character.getNumericValue(c);
					lstObj.setGisApplId(StringHelperUtils.isNullLong(gisAppl));
					lstObj.setGisgroup(StringHelperUtils.isNullString(objLst[56].toString()));
					lstObj.setLandlineNo(StringHelperUtils.isNullString(objLst[57]));
					lstObj.setMembership_date(StringHelperUtils.isNullDate(objLst[58]));

					i++;
				}
			}
		}
		return lstObj;
	}

	@Override
	public List<Object[]> getEmpSignPhoto(Long employeeId) {
		// TODO Auto-generated method stub 04/08/2021
		Session currentSession = manager.unwrap(Session.class);
		String hql = "select photo_attachment_id,signature_attachment_id from employee_mst where employee_id ="
				+ employeeId;
		Query query = currentSession.createNativeQuery(hql);
		return (List<Object[]>) query.list();
	}

	@Override
	public List<Object[]> GetCurrentPostDesigation(Integer postdetailid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MstEmployeeEntity> getEmployeeDetails(String ddoCode) {
		Session currentSession = manager.unwrap(Session.class);
		List<MstEmployeeEntity> result = null;
		try {
			result = manager.createQuery(
					"from MstEmployeeEntity where (isActive=1 or isActive=4) and ddoCode='" + ddoCode + "'",
					MstEmployeeEntity.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// logger.info("stack trace exceptionend");
		}

		return result;
	}

	@Override
	public String getDesignationName(String strDesgId) {
		// TODO Auto-generated method stub
		String strDeptNm = "";
		Session currentSession = manager.unwrap(Session.class);
		String hql = "SELECT a.designation_code, " + "a.designation_name, " + "a.designation_short_name "
				+ "FROM   designation_mst a where a.designation_code=" + strDesgId;
		Query query = currentSession.createNativeQuery(hql);
		List<Object[]> lstprop = query.list();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				strDeptNm = StringHelperUtils.isNullString(objLst[1]);
			}
		}
		return strDeptNm;
	}

	@Override
	public EmpChangeDetailsModel getEmployeeinfo(Long employeeId) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);

		// List<MstEmployeeEntity> result = null;
		List<MstEmployeeEntity> result = null;
		List<MstDcpsDetailsEntity> result1 = null;
		List<MstGpfDetailsEntity> result2 = null;
		List<MstGisdetailsEntity> result3 = null;
		List<MstNomineeDetailsEntity> result4 = null;
		EmpChangeDetailsModel mstEmployeeModel = new EmpChangeDetailsModel();

		try {
			result = manager
					.createQuery("from MstEmployeeEntity where employeeId=" + employeeId, MstEmployeeEntity.class)
					.getResultList();
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				MstEmployeeEntity mstEmployeeEntity = (MstEmployeeEntity) iterator.next();
				// Employee Details Start
				if (mstEmployeeEntity.getUidNo() != null) {
					String uidNoArray = String.valueOf(mstEmployeeEntity.getUidNo());
					if (uidNoArray.length() == 12) {
						mstEmployeeModel.setUidNo1(uidNoArray.substring(0, Math.min(0 + 4, uidNoArray.length())));
						mstEmployeeModel.setUidNo2(uidNoArray.substring(4, Math.min(4 + 4, uidNoArray.length())));
						mstEmployeeModel.setUidNo3(uidNoArray.substring(8, Math.min(8 + 4, uidNoArray.length())));

					}
				}
				mstEmployeeModel.setSevaarthId(mstEmployeeEntity.getSevaarthId());
				mstEmployeeModel.setEmployeeId(mstEmployeeEntity.getEmployeeId());
				mstEmployeeModel.setEidNo(mstEmployeeEntity.getEidNo());
				mstEmployeeModel.setSalutation(mstEmployeeEntity.getSalutation());
				mstEmployeeModel.setEmployeeFullNameEn(mstEmployeeEntity.getEmployeeFullNameEn());
				mstEmployeeModel.setEmployeeFNameEn(mstEmployeeEntity.getEmployeeFNameEn().toUpperCase());
				if (mstEmployeeEntity.getEmployeeMNameEn() != null)
					mstEmployeeModel.setEmployeeMNameEn(mstEmployeeEntity.getEmployeeMNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeLNameEn(mstEmployeeEntity.getEmployeeLNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeFullNameMr(mstEmployeeEntity.getEmployeeFullNameMr());
				mstEmployeeModel.setEmployeeFNameMr(mstEmployeeEntity.getEmployeeFNameMr());
				mstEmployeeModel.setEmployeeLNameMr(mstEmployeeEntity.getEmployeeLNameMr());
				mstEmployeeModel.setEmployeeMotherName(mstEmployeeEntity.getEmployeeMotherName());
				mstEmployeeModel.setBuckleNo(mstEmployeeEntity.getBuckleNo());
				// if (mstEmployeeEntity.getGender() == 'M') {
				// mstEmployeeModel.setGender('1');
				// } else if (mstEmployeeEntity.getGender() == 'F') {
				// mstEmployeeModel.setGender('2');
				// } else {
				// mstEmployeeModel.setGender('3');
				// }
				mstEmployeeModel.setGender(mstEmployeeEntity.getGender());
				mstEmployeeModel.setReligionCode(mstEmployeeEntity.getReligionCode());
				mstEmployeeModel.setMaritalStatus(mstEmployeeEntity.getMaritalStatus());
				mstEmployeeModel.setEmployeeMNameMr(mstEmployeeEntity.getEmployeeMNameMr());
				mstEmployeeModel.setDob(mstEmployeeEntity.getDob());
				mstEmployeeModel.setDoj(mstEmployeeEntity.getDoj());
				if (mstEmployeeEntity.getAddress1() != null)
					mstEmployeeModel.setAddress1(mstEmployeeEntity.getAddress1().toUpperCase());
				if (mstEmployeeEntity.getAddress2() != null)
					mstEmployeeModel.setAddress2(mstEmployeeEntity.getAddress2().toUpperCase());
				if (mstEmployeeEntity.getAddress3() != null)
					mstEmployeeModel.setAddress3(mstEmployeeEntity.getAddress3().toUpperCase());
				mstEmployeeModel.setLocality(mstEmployeeEntity.getLocality());
				mstEmployeeModel.setStateCode(mstEmployeeEntity.getStateCode());
				mstEmployeeModel.setDistrictCode(mstEmployeeEntity.getDistrictCode());
				mstEmployeeModel.setPinCode(mstEmployeeEntity.getPinCode());
				mstEmployeeModel.setPhysicallyHandicapped(mstEmployeeEntity.getPhysicallyHandicapped());
				mstEmployeeModel.setMobileNo1(mstEmployeeEntity.getMobileNo1());
				mstEmployeeModel.setEmailId(mstEmployeeEntity.getEmailId());
				mstEmployeeModel.setPanNo(mstEmployeeEntity.getPanNo());
				mstEmployeeModel.setDdoCode(mstEmployeeEntity.getDdoCode());
				mstEmployeeModel.setMorequalification(mstEmployeeEntity.getMorequalification());
				mstEmployeeModel.setSecqualification(mstEmployeeEntity.getSecqualification());

				// Employee Details End

				// Department Details Start
				mstEmployeeModel.setParentAdminDepartmentId(mstEmployeeEntity.getFieldDepartmentCode());
				mstEmployeeModel.setFieldDepartmentId(mstEmployeeEntity.getFieldDepartmentCode());
				mstEmployeeModel.setIsChangeParentDepartment(mstEmployeeEntity.getIsChangeParentDepartment());
				mstEmployeeModel.setReasonForChngParentFieldDept(mstEmployeeEntity.getReasonForChngParentFieldDept());
				mstEmployeeModel.setCadreId(mstEmployeeEntity.getCadreCode());
				mstEmployeeModel.setEmpClass(mstEmployeeEntity.getEmpClass());
				if (mstEmployeeEntity.getSuperAnnAge() != null)
					mstEmployeeModel.setSuperannuationage(mstEmployeeEntity.getSuperAnnAge());
				mstEmployeeModel.setEmpServiceEndDate(mstEmployeeEntity.getSuperAnnDate()); // by default set to
																							// retirement date added by
				mstEmployeeModel.setAppointmentId(Long.valueOf(mstEmployeeEntity.getAppointment()));
				// mstEmployeeModel.setQid(Long.valueOf(mstEmployeeEntity.getQualification()));//
				// sudhir
				mstEmployeeModel.setQualification(mstEmployeeEntity.getQualification());
				mstEmployeeModel.setSuperAnnDate(mstEmployeeEntity.getSuperAnnDate());
				mstEmployeeModel.setPayCommissionCode(mstEmployeeEntity.getPayCommissionCode());
				mstEmployeeModel.setFirstDesignationId(mstEmployeeEntity.getFirstDesignationCode());
				mstEmployeeModel.setDesignationId(mstEmployeeEntity.getDesignationCode());
				mstEmployeeModel.setPayscalelevelId(mstEmployeeEntity.getPayscalelevelId());
				if (mstEmployeeModel.getPayscalelevelId() != null)
					mstEmployeeModel.setSevenPCLevel(Long.valueOf(mstEmployeeEntity.getPayscalelevelId()));
				else
					mstEmployeeModel.setSevenPCLevel(0l);

				mstEmployeeModel.setSvnthpaybasic(mstEmployeeEntity.getSvnthpaybasic());

				mstEmployeeModel.setPayScaleCode(mstEmployeeEntity.getPayScaleCode());
				mstEmployeeModel.setPayScaleId(mstEmployeeEntity.getPayScaleCode());
				mstEmployeeModel.setPayInPayBand(mstEmployeeEntity.getPayInPayBand());
				mstEmployeeModel.setGradePay(mstEmployeeEntity.getGradePay());
				mstEmployeeModel.setTeaching(mstEmployeeEntity.getTeaching());
				/*
				 * if (mstEmployeeEntity.getBasicPay() != null) { Integer basic =
				 * mstEmployeeEntity.getBasicPay().intValue();
				 * mstEmployeeModel.setBasicPay(basic); } else { Integer basic =
				 * mstEmployeeEntity.getSevenPcBasic().intValue();
				 * mstEmployeeModel.setBasicPay(basic); }
				 */

				if (mstEmployeeModel.getPayCommissionCode() == 700005) {
					Integer basic = mstEmployeeEntity.getSevenPcBasic().intValue();
					mstEmployeeModel.setBasicPay(basic.doubleValue());
				}
				if (mstEmployeeModel.getPayCommissionCode() == 700016) {
					Integer basic = mstEmployeeEntity.getBasicPay().intValue();
					mstEmployeeModel.setBasicPay(basic.doubleValue());
				}

				mstEmployeeModel.setPostdetailid(mstEmployeeEntity.getPostdetailid());
				mstEmployeeModel.setDepartmentNameEn(mstEmployeeEntity.getDepartmentNameEn());
				mstEmployeeModel
						.setDtInitialAppointmentParentInst(mstEmployeeEntity.getDtInitialAppointmentParentInst());
				mstEmployeeModel.setInstituteAdd(mstEmployeeEntity.getInstituteAdd());
				mstEmployeeModel.setInstName(mstEmployeeEntity.getInstName());
				if (mstEmployeeEntity.getMobileNo2() != null)
					mstEmployeeModel.setMobileNo2(mstEmployeeEntity.getMobileNo2().longValue());
				mstEmployeeModel.setInstemail(mstEmployeeEntity.getInstemail());
				mstEmployeeModel.setDtJoinCurrentPost(mstEmployeeEntity.getDtJoinCurrentPost());
				mstEmployeeModel.setRemark(mstEmployeeEntity.getRemark());
				mstEmployeeModel.setCityClass(mstEmployeeEntity.getCityClass());
				mstEmployeeModel.setIndiApproveOrderNo(mstEmployeeEntity.getIndiApproveOrderNo());
				mstEmployeeModel.setApprovalByDdoDate(mstEmployeeEntity.getApprovalByDdoDate());
				mstEmployeeModel.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeModel.setHraBasic(mstEmployeeEntity.getHraBasic());
				// Department Details End

				// Bank/DCPS/NPS/GPF Details Start
				mstEmployeeModel.setBankId(mstEmployeeEntity.getBankCode());
				mstEmployeeModel.setIfscCode(mstEmployeeEntity.getIfscCode());
				mstEmployeeModel.setBankAcntNo(mstEmployeeEntity.getBankAcntNo());
				mstEmployeeModel.setBankBranchId(mstEmployeeEntity.getBankBranchCode());
				mstEmployeeModel.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeModel.setDcpsaccountmaintainby(mstEmployeeEntity.getDcpsaccountmaintainby());
				mstEmployeeModel.setPranNo(mstEmployeeEntity.getPranNo());
				mstEmployeeModel.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				mstEmployeeModel.setPfseries(mstEmployeeEntity.getPfseries());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				mstEmployeeModel.setPfdescription(mstEmployeeEntity.getPfdescription());

				// Bank/DCPS/NPS/GPF Details End

				// GIS Details Start
				mstEmployeeModel.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstEmployeeModel.setGisgroup(mstEmployeeEntity.getGisgroup());
				mstEmployeeModel.setMembership_date(mstEmployeeEntity.getMembership_date());
				mstEmployeeModel.setGisRemark(mstEmployeeEntity.getGisRemark());
				mstEmployeeModel.setDesignationId(mstEmployeeEntity.getDesignationCode());

				// GIS Details End

			}

			// result1 = manager
			// .createQuery("from MstDcpsDetailsEntity where employeeId=" + employeeId,
			// MstDcpsDetailsEntity.class)
			// .getResultList();
			// for (Iterator iterator = result1.iterator(); iterator.hasNext();) {
			// MstDcpsDetailsEntity mstEmployeeEntity = (MstDcpsDetailsEntity)
			// iterator.next();
			// // mstEmployeeModel.setIsactive(mstEmployeeEntity.getIsactive());
			// mstEmployeeModel.setDcpsid(mstEmployeeEntity.getDcpsid());
			//
			// mstEmployeeModel.setDcpsaccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
			// // mstEmployeeModel.setCreateddate(new Date());
			// // mstEmployeeModel.setCreatedid(mstEmployeeEntity.getCreatedUserId());
			// mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
			// // mstEmployeeModel.setUpdatedate(mstEmployeeEntity.getUpdatedDate());
			// // mstEmployeeModel.setUpdateid(mstEmployeeEntity.getUpdatedUserId());
			//
			// // logger.info("mstEmployeeModel111="+mstEmployeeModel);
			// }
			result2 = manager
					.createQuery("from MstGpfDetailsEntity where employeeId=" + employeeId, MstGpfDetailsEntity.class)
					.getResultList();
			for (Iterator iterator = result2.iterator(); iterator.hasNext();) {
				MstGpfDetailsEntity mstEmployeeEntity = (MstGpfDetailsEntity) iterator.next();

				mstEmployeeModel.setGpf_id(mstEmployeeEntity.getGpf_id());
				mstEmployeeModel.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				// mstEmployeeModel.setCreateddate(new Date());
				// mstEmployeeModel.setCreatedid(mstEmployeeModel.getCreatedUserId());
				// mstEmployeeModel.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				// mstEmployeeModel.setPfseries(mstEmployeeModel.getPfseries());
				mstEmployeeModel.setPfdescription("");

				// mstEmployeeModel.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				// mstEmployeeModel.setUpdateid(mstEmployeeModel.getUpdatedUserId());

			}
			result3 = manager
					.createQuery("from MstGisdetailsEntity where employeeId=" + employeeId, MstGisdetailsEntity.class)
					.getResultList();

			for (Iterator iterator = result3.iterator(); iterator.hasNext();) {
				MstGisdetailsEntity mstEmployeeEntity = (MstGisdetailsEntity) iterator.next();
				// mstEmployeeModel.setCreateddate(new Date());
				// mstEmployeeModel.setCreatedid(mstEmployeeModel.getCreatedUserId());
				mstEmployeeModel.setGisid(mstEmployeeEntity.getGisid());
				mstEmployeeModel.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstEmployeeModel.setGisgroup(mstEmployeeEntity.getGisgroup());
				// mstEmployeeModel.setIsactive("Y");
				mstEmployeeModel.setMembership_date(mstEmployeeEntity.getMembership_date());
				// mstEmployeeModel.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				// mstEmployeeModel.setUpdateid(mstEmployeeModel.getUpdatedUserId());

			}

			System.out.println("designation id >>>>>" + mstEmployeeModel.getDesignationId());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// logger.info("stack trace exceptionend");
		}

		return mstEmployeeModel;
	}

	// @Override
	// public MstEmployeeDetailEntity findbyemplidForChangeDetails(Long employeeId)
	// {
	// // TODO Auto-generated method stub
	// MstEmployeeDetailEntity objDept = null;
	// Session currentSession = manager.unwrap(Session.class);
	// objDept = currentSession.get(MstEmployeeDetailEntity.class, employeeId);
	// return objDept;
	// }

	@Override
	public Optional<MstEmployeeDetailEntity> findbyemplidForChangeDetails(Long employeeId) {
		Session ghibSession = manager.unwrap(Session.class);

		if (employeeId == null) {
			return Optional.empty(); // Return empty if the ID is null
		}
		MstEmployeeDetailEntity entity = ghibSession.find(MstEmployeeDetailEntity.class, employeeId);
		return Optional.ofNullable(entity);
	}

	@Override
	public long updateChangeEmployeeDetails(MstEmployeeDetailEntity objEntity,
			@Valid EmpChangeDetailsModel empChangeDetailsModel, MstNomineeDetailsEntity[] lArrNomineeDtls) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		objEntity.setDcpsgpfflag(empChangeDetailsModel.getDcpsgpfflag());
		// currentSession.update(objEntity);
		currentSession.save(objEntity);

		long resNmnDtls = deleteNomineeDtls(objEntity.getEmployeeId());
		if (lArrNomineeDtls != null)
			for (Integer lInt = 0; lInt < lArrNomineeDtls.length; lInt++) {
				if (lArrNomineeDtls[lInt] != null) {
					lArrNomineeDtls[lInt].setEmployeeId(objEntity.getEmployeeId());
					currentSession.save(lArrNomineeDtls[lInt]);
				}
			}

		return (long) 1;
	}

	public Long deleteNomineeDtls(Long empid) {
		Session currentSession = manager.unwrap(Session.class);
		long result = 0l;
		try {
			String hql4 = "delete from nominee_details_mst where employee_id = " + empid;
			Query query4 = currentSession.createNativeQuery(hql4);
			result = query4.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public MstGpfDetailsHistEntity findbyGPFid(Long gpf_id) {
		// TODO Auto-generated method stub
		MstGpfDetailsHistEntity objDept = null;
		Session currentSession = manager.unwrap(Session.class);
		objDept = currentSession.get(MstGpfDetailsHistEntity.class, gpf_id);
		return objDept;
	}

	@Override
	public MstGisdetailsHistEntity findbyGisid(Long gisid) {
		// TODO Auto-generated method stub
		MstGisdetailsHistEntity objDept = null;
		Session currentSession = manager.unwrap(Session.class);
		objDept = currentSession.get(MstGisdetailsHistEntity.class, gisid);
		return objDept;
	}

	@Override
	public long updateEmployeeConfiguration(MstEmployeeDetailEntity objEntity,
			@Valid EmpChangeDetailsModel empChangeDetailsModel, MstNomineeDetailsHistEntity[] lArrNomineeDtls) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		objEntity.setDcpsgpfflag(empChangeDetailsModel.getDcpsgpfflag());
		// currentSession.update(objEntity);
		currentSession.save(objEntity);

		long resNmnDtls = deleteNomineeDtlsHst(objEntity.getEmployeeId());
		if (lArrNomineeDtls != null)
			for (Integer lInt = 0; lInt < lArrNomineeDtls.length; lInt++) {
				if (lArrNomineeDtls[lInt] != null) {
					lArrNomineeDtls[lInt].setEmployeeId(objEntity.getEmployeeId());
					currentSession.save(lArrNomineeDtls[lInt]);
				}
			}

		return (long) 1;
	}

	@Override
	public List<MstEmployeeDetailEntity> findEmpLstforApprovChngDtls(String ddocode) {
		// TODO Auto-generated method stub
		// Session currentSession = manager.unwrap(Session.class);
		// String HQL = "SELECT a FROM MstEmployeeDetailEntity a where a.isActive='3'
		// and a.ddoCode is not null ";
		// return (List<MstEmployeeDetailEntity>)
		// manager.createQuery(HQL).getResultList();

		Session currentSession = manager.unwrap(Session.class);

		List<Object[]> result = null;
		List<MstEmployeeDetailEntity> result1 = new ArrayList<MstEmployeeDetailEntity>();
		StringBuffer strQuery = new StringBuffer();

		try {
			strQuery.append(
					"select employee_id,employee_full_name_en,sevaarth_id,designation_code,ddo_code,DOJ,SUPER_ANN_DATE from employee_mst_details  where form_status=5 and ddo_code in (");
			strQuery.append(
					"select ddo_code from org_ddo_mst where ddo_code in (select dmr.zp_ddo_code from rlt_zp_ddo_map dmr ");
			strQuery.append("inner join org_ddo_mst drm  on drm.ddo_code = dmr.rept_ddo_code  where drm.ddo_code='"
					+ ddocode + "'))");
			Query query = currentSession.createNativeQuery(strQuery.toString());
			System.out.println("query---" + query);
			System.out.println("strQuery---" + strQuery);
			// result = entityManager.createQuery( strQuery.toString(),
			// MstEmployeeEntity.class ).getResultList();

			result = query.list();

		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			// logger.info("stack trace exceptionend");
		}

		for (Iterator iterator = result.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			MstEmployeeDetailEntity mstEmployeeEntity = new MstEmployeeDetailEntity();
			// mstEmployeeEntity.setEmployeeId((Long) object[0]);
			mstEmployeeEntity.setEmployeeId(Long.valueOf(object[0].toString()));
			mstEmployeeEntity.setEmployeeFullNameEn(object[1].toString().toUpperCase());
			if (object[2] != null)
				mstEmployeeEntity.setSevaarthId(object[2].toString());
			mstEmployeeEntity.setDesignationCode(Long.valueOf(object[3].toString()));
			mstEmployeeEntity.setDdoCode(object[4].toString());
			mstEmployeeEntity.setDoj(StringHelperUtils.isNullDate(object[5]));
			if ((object[6]) != null) {
				mstEmployeeEntity.setSuperAnnDate(StringHelperUtils.isNullDate(object[6]));
			}
			result1.add(mstEmployeeEntity);
			// logger.info("mstEmployeeEntity="+mstEmployeeEntity);
		}
		return result1;
	}

	@Override
	public EmpChangeDetailsModel getEmployeeinfofordetails(long empId) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);

		// List<MstEmployeeEntity> result = null;
		List<MstEmployeeDetailEntity> result = null;
		List<MstDcpsDetailsEntity> result1 = null;
		List<MstGpfDetailsHistEntity> result2 = null;
		List<MstGisdetailsHistEntity> result3 = null;
		List<MstNomineeDetailsEntity> result4 = null;
		EmpChangeDetailsModel mstEmployeeModel = new EmpChangeDetailsModel();

		try {
			result = manager.createQuery("from MstEmployeeDetailEntity where employeeId=" + empId,
					MstEmployeeDetailEntity.class).getResultList();
			for (Iterator iterator = result.iterator(); iterator.hasNext();) {
				MstEmployeeDetailEntity mstEmployeeEntity = (MstEmployeeDetailEntity) iterator.next();
				// Employee Details Start
				if (mstEmployeeEntity.getUidNo() != null) {
					String uidNoArray = String.valueOf(mstEmployeeEntity.getUidNo());
					if (uidNoArray.length() == 12) {
						mstEmployeeModel.setUidNo1(uidNoArray.substring(0, Math.min(0 + 4, uidNoArray.length())));
						mstEmployeeModel.setUidNo2(uidNoArray.substring(4, Math.min(4 + 4, uidNoArray.length())));
						mstEmployeeModel.setUidNo3(uidNoArray.substring(8, Math.min(8 + 4, uidNoArray.length())));

					}
				}
				mstEmployeeModel.setSevaarthId(mstEmployeeEntity.getSevaarthId());
				mstEmployeeModel.setEmployeeId(mstEmployeeEntity.getEmployeeId());
				mstEmployeeModel.setEidNo(mstEmployeeEntity.getEidNo());
				mstEmployeeModel.setSalutation(mstEmployeeEntity.getSalutation());
				mstEmployeeModel.setEmployeeFullNameEn(mstEmployeeEntity.getEmployeeFullNameEn());
				mstEmployeeModel.setEmployeeFNameEn(mstEmployeeEntity.getEmployeeFNameEn().toUpperCase());
				if (mstEmployeeEntity.getEmployeeMNameEn() != null)
					mstEmployeeModel.setEmployeeMNameEn(mstEmployeeEntity.getEmployeeMNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeLNameEn(mstEmployeeEntity.getEmployeeLNameEn().toUpperCase());
				mstEmployeeModel.setEmployeeFullNameMr(mstEmployeeEntity.getEmployeeFullNameMr());
				mstEmployeeModel.setEmployeeFNameMr(mstEmployeeEntity.getEmployeeFNameMr());
				mstEmployeeModel.setEmployeeLNameMr(mstEmployeeEntity.getEmployeeLNameMr());
				mstEmployeeModel.setEmployeeMotherName(mstEmployeeEntity.getEmployeeMotherName());
				mstEmployeeModel.setBuckleNo(mstEmployeeEntity.getBuckleNo());
				// if (mstEmployeeEntity.getGender() == 'M') {
				// mstEmployeeModel.setGender('1');
				// } else if (mstEmployeeEntity.getGender() == 'F') {
				// mstEmployeeModel.setGender('2');
				// } else {
				// mstEmployeeModel.setGender('3');
				// }
				mstEmployeeModel.setGender(mstEmployeeEntity.getGender());
				mstEmployeeModel.setReligionCode(mstEmployeeEntity.getReligionCode());
				mstEmployeeModel.setMaritalStatus(mstEmployeeEntity.getMaritalStatus());
				mstEmployeeModel.setEmployeeMNameMr(mstEmployeeEntity.getEmployeeMNameMr());
				mstEmployeeModel.setDob(mstEmployeeEntity.getDob());
				mstEmployeeModel.setDoj(mstEmployeeEntity.getDoj());
				if (mstEmployeeEntity.getAddress1() != null)
					mstEmployeeModel.setAddress1(mstEmployeeEntity.getAddress1().toUpperCase());
				if (mstEmployeeEntity.getAddress2() != null)
					mstEmployeeModel.setAddress2(mstEmployeeEntity.getAddress2().toUpperCase());
				if (mstEmployeeEntity.getAddress3() != null)
					mstEmployeeModel.setAddress3(mstEmployeeEntity.getAddress3().toUpperCase());
				mstEmployeeModel.setLocality(mstEmployeeEntity.getLocality());
				mstEmployeeModel.setStateCode(mstEmployeeEntity.getStateCode());
				mstEmployeeModel.setDistrictCode(mstEmployeeEntity.getDistrictCode());
				mstEmployeeModel.setPinCode(mstEmployeeEntity.getPinCode());
				mstEmployeeModel.setPhysicallyHandicapped(mstEmployeeEntity.getPhysicallyHandicapped());
				mstEmployeeModel.setMobileNo1(mstEmployeeEntity.getMobileNo1());
				mstEmployeeModel.setEmailId(mstEmployeeEntity.getEmailId());
				mstEmployeeModel.setPanNo(mstEmployeeEntity.getPanNo());
				mstEmployeeModel.setDdoCode(mstEmployeeEntity.getDdoCode());
				mstEmployeeModel.setMorequalification(mstEmployeeEntity.getMorequalification());
				mstEmployeeModel.setSecqualification(mstEmployeeEntity.getSecqualification());

				// Employee Details End

				// Department Details Start
				mstEmployeeModel.setParentAdminDepartmentId(mstEmployeeEntity.getFieldDepartmentCode());
				mstEmployeeModel.setFieldDepartmentId(mstEmployeeEntity.getFieldDepartmentCode());
				mstEmployeeModel.setIsChangeParentDepartment(mstEmployeeEntity.getIsChangeParentDepartment());
				mstEmployeeModel.setReasonForChngParentFieldDept(mstEmployeeEntity.getReasonForChngParentFieldDept());
				mstEmployeeModel.setCadreId(mstEmployeeEntity.getCadreCode());
				mstEmployeeModel.setEmpClass(mstEmployeeEntity.getEmpClass());
				if (mstEmployeeEntity.getSuperAnnAge() != null)
					mstEmployeeModel.setSuperannuationage(mstEmployeeEntity.getSuperAnnAge());
				mstEmployeeModel.setEmpServiceEndDate(mstEmployeeEntity.getSuperAnnDate()); // by default set to

				// retirement date added by
				if (mstEmployeeEntity.getAppointment() != null) {
					mstEmployeeModel.setAppointmentId(Long.valueOf(mstEmployeeEntity.getAppointment()));
				}

				// mstEmployeeModel.setQid(Long.valueOf(mstEmployeeEntity.getQualification()));//
				// sudhir
				mstEmployeeModel.setQualification(mstEmployeeEntity.getQualification());
				mstEmployeeModel.setSuperAnnDate(mstEmployeeEntity.getSuperAnnDate());
				mstEmployeeModel.setPayCommissionCode(mstEmployeeEntity.getPayCommissionCode());
				mstEmployeeModel.setFirstDesignationId(mstEmployeeEntity.getFirstDesignationCode());
				mstEmployeeModel.setDesignationId(mstEmployeeEntity.getDesignationCode());
				mstEmployeeModel.setPayscalelevelId(mstEmployeeEntity.getPayscalelevelId());
				if (mstEmployeeModel.getPayscalelevelId() != null)
					mstEmployeeModel.setSevenPCLevel(Long.valueOf(mstEmployeeEntity.getPayscalelevelId()));
				else
					mstEmployeeModel.setSevenPCLevel(0l);

				mstEmployeeModel.setSvnthpaybasic(mstEmployeeEntity.getSvnthpaybasic());

				mstEmployeeModel.setPayScaleCode(mstEmployeeEntity.getPayScaleCode());
				mstEmployeeModel.setPayScaleId(mstEmployeeEntity.getPayScaleCode());
				mstEmployeeModel.setPayInPayBand(mstEmployeeEntity.getPayInPayBand());
				mstEmployeeModel.setGradePay(mstEmployeeEntity.getGradePay());
				mstEmployeeModel.setTeaching(mstEmployeeEntity.getTeaching());
				/*
				 * if (mstEmployeeEntity.getBasicPay() != null) { Integer basic =
				 * mstEmployeeEntity.getBasicPay().intValue();
				 * mstEmployeeModel.setBasicPay(basic); } else { Integer basic =
				 * mstEmployeeEntity.getSevenPcBasic().intValue();
				 * mstEmployeeModel.setBasicPay(basic); }
				 */

				if (mstEmployeeEntity.getPayCommissionCode() == 700005) {
					Integer basic = mstEmployeeEntity.getSevenPcBasic().intValue();
					mstEmployeeModel.setBasicPay(basic.doubleValue());
				}
				if (mstEmployeeEntity.getPayCommissionCode() == 700016) {
					Integer basic = mstEmployeeEntity.getBasicPay().intValue();
					mstEmployeeModel.setBasicPay(basic.doubleValue());
				}

				mstEmployeeModel.setPostdetailid(mstEmployeeEntity.getPostdetailid());
				mstEmployeeModel.setDepartmentNameEn(mstEmployeeEntity.getDepartmentNameEn());
				mstEmployeeModel
						.setDtInitialAppointmentParentInst(mstEmployeeEntity.getDtInitialAppointmentParentInst());
				mstEmployeeModel.setInstituteAdd(mstEmployeeEntity.getInstituteAdd());
				mstEmployeeModel.setInstName(mstEmployeeEntity.getInstName());
				if (mstEmployeeEntity.getMobileNo2() != null)
					mstEmployeeModel.setMobileNo2(mstEmployeeEntity.getMobileNo2().longValue());
				mstEmployeeModel.setInstemail(mstEmployeeEntity.getInstemail());
				mstEmployeeModel.setDtJoinCurrentPost(mstEmployeeEntity.getDtJoinCurrentPost());
				mstEmployeeModel.setRemark(mstEmployeeEntity.getRemark());
				mstEmployeeModel.setCityClass(mstEmployeeEntity.getCityClass());
				mstEmployeeModel.setIndiApproveOrderNo(mstEmployeeEntity.getIndiApproveOrderNo());
				mstEmployeeModel.setApprovalByDdoDate(mstEmployeeEntity.getApprovalByDdoDate());
				mstEmployeeModel.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeModel.setHraBasic(mstEmployeeEntity.getHraBasic());
				// Department Details End

				// Bank/DCPS/NPS/GPF Details Start
				mstEmployeeModel.setBankId(mstEmployeeEntity.getBankCode());
				mstEmployeeModel.setIfscCode(mstEmployeeEntity.getIfscCode());
				mstEmployeeModel.setBankAcntNo(mstEmployeeEntity.getBankAcntNo());
				mstEmployeeModel.setBankBranchId(mstEmployeeEntity.getBankBranchCode());
				mstEmployeeModel.setDcpsgpfflag(mstEmployeeEntity.getDcpsgpfflag());
				mstEmployeeModel.setDcpsaccountmaintainby(mstEmployeeEntity.getDcpsaccountmaintainby());
				mstEmployeeModel.setPranNo(mstEmployeeEntity.getPranNo());
				mstEmployeeModel.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				mstEmployeeModel.setPfseries(mstEmployeeEntity.getPfseries());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				mstEmployeeModel.setPfdescription(mstEmployeeEntity.getPfdescription());

				// Bank/DCPS/NPS/GPF Details End

				// GIS Details Start
				mstEmployeeModel.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstEmployeeModel.setGisgroup(mstEmployeeEntity.getGisgroup());
				mstEmployeeModel.setMembership_date(mstEmployeeEntity.getMembership_date());
				mstEmployeeModel.setGisRemark(mstEmployeeEntity.getGisRemark());
				mstEmployeeModel.setDesignationId(mstEmployeeEntity.getDesignationCode());

				// GIS Details End

			}

			// result1 = manager
			// .createQuery("from MstDcpsDetailsEntity where employeeId=" + employeeId,
			// MstDcpsDetailsEntity.class)
			// .getResultList();
			// for (Iterator iterator = result1.iterator(); iterator.hasNext();) {
			// MstDcpsDetailsEntity mstEmployeeEntity = (MstDcpsDetailsEntity)
			// iterator.next();
			// // mstEmployeeModel.setIsactive(mstEmployeeEntity.getIsactive());
			// mstEmployeeModel.setDcpsid(mstEmployeeEntity.getDcpsid());
			//
			// mstEmployeeModel.setDcpsaccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
			// // mstEmployeeModel.setCreateddate(new Date());
			// // mstEmployeeModel.setCreatedid(mstEmployeeEntity.getCreatedUserId());
			// mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
			// // mstEmployeeModel.setUpdatedate(mstEmployeeEntity.getUpdatedDate());
			// // mstEmployeeModel.setUpdateid(mstEmployeeEntity.getUpdatedUserId());
			//
			// // logger.info("mstEmployeeModel111="+mstEmployeeModel);
			// }
			result2 = manager.createQuery("from MstGpfDetailsHistEntity where employeeId=" + empId,
					MstGpfDetailsHistEntity.class).getResultList();
			for (Iterator iterator = result2.iterator(); iterator.hasNext();) {
				MstGpfDetailsHistEntity mstEmployeeEntity = (MstGpfDetailsHistEntity) iterator.next();

				mstEmployeeModel.setGpf_id(mstEmployeeEntity.getGpf_id());
				mstEmployeeModel.setAccountmaintainby(mstEmployeeEntity.getAccountmaintainby());
				// mstEmployeeModel.setCreateddate(new Date());
				// mstEmployeeModel.setCreatedid(mstEmployeeModel.getCreatedUserId());
				// mstEmployeeModel.setIsactive(mstEmployeeModel.getDcpsgpfflag());
				mstEmployeeModel.setPfacno(mstEmployeeEntity.getPfacno());
				// mstEmployeeModel.setPfseries(mstEmployeeModel.getPfseries());
				mstEmployeeModel.setPfdescription("");

				// mstEmployeeModel.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				// mstEmployeeModel.setUpdateid(mstEmployeeModel.getUpdatedUserId());

			}
			result3 = manager.createQuery("from MstGisdetailsHistEntity where employeeId=" + empId,
					MstGisdetailsHistEntity.class).getResultList();

			for (Iterator iterator = result3.iterator(); iterator.hasNext();) {
				MstGisdetailsHistEntity mstEmployeeEntity = (MstGisdetailsHistEntity) iterator.next();
				// mstEmployeeModel.setCreateddate(new Date());
				// mstEmployeeModel.setCreatedid(mstEmployeeModel.getCreatedUserId());
				mstEmployeeModel.setGisid(mstEmployeeEntity.getGisid());
				mstEmployeeModel.setGisapplicable(mstEmployeeEntity.getGisapplicable());
				mstEmployeeModel.setGisgroup(mstEmployeeEntity.getGisgroup());
				// mstEmployeeModel.setIsactive("Y");
				mstEmployeeModel.setMembership_date(mstEmployeeEntity.getMembership_date());
				// mstEmployeeModel.setUpdatedate(mstEmployeeModel.getUpdatedDate());
				// mstEmployeeModel.setUpdateid(mstEmployeeModel.getUpdatedUserId());

			}

			System.out.println("designation id >>>>>" + mstEmployeeModel.getDesignationId());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			// logger.info("stack trace exceptionend");
		}

		return mstEmployeeModel;
	}

	@Override
	public List<MstNomineeDetailsHistEntity> getNominees(String empId) {
		Session hibSession = manager.unwrap(Session.class);
		List<MstNomineeDetailsHistEntity> result = null;
		result = manager.createQuery("from MstNomineeDetailsHistEntity where employeeId =" + Integer.valueOf(empId),
				MstNomineeDetailsHistEntity.class).getResultList();
		return result;
	}

	@Override
	public MstEmployeeEntity findempid(Long employeeId) {
		// TODO Auto-generated method stub
		MstEmployeeEntity objDept = null;
		Session currentSession = manager.unwrap(Session.class);
		objDept = currentSession.get(MstEmployeeEntity.class, employeeId);
		return objDept;

	}

	@Override
	public MstGpfDetailsEntity findbyGPFiddeatils(Long gpf_id) {
		// TODO Auto-generated method stub
		MstGpfDetailsEntity objDept = null;
		Session currentSession = manager.unwrap(Session.class);
		objDept = currentSession.get(MstGpfDetailsEntity.class, gpf_id);
		return objDept;
	}

	@Override
	public MstGisdetailsEntity findbyGisiddetails(Long gisid) {
		// TODO Auto-generated method stub
		MstGisdetailsEntity objDept = null;
		Session currentSession = manager.unwrap(Session.class);
		objDept = currentSession.get(MstGisdetailsEntity.class, gisid);
		return objDept;
	}

	@Override
	public long updateChangeDetails(MstEmployeeEntity objEntity, @Valid EmpChangeDetailsModel empChangeDetailsModel,
			MstNomineeDetailsEntity[] lArrNomineeDtls) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		objEntity.setDcpsgpfflag(empChangeDetailsModel.getDcpsgpfflag());
		currentSession.update(objEntity);
		// currentSession.save(objEntity);

		long resNmnDtls = deleteNomineeDtls(objEntity.getEmployeeId());
		if (lArrNomineeDtls != null)
			for (Integer lInt = 0; lInt < lArrNomineeDtls.length; lInt++) {
				if (lArrNomineeDtls[lInt] != null) {
					lArrNomineeDtls[lInt].setEmployeeId(objEntity.getEmployeeId());
					currentSession.save(lArrNomineeDtls[lInt]);
				}
			}

		return (long) 1;
	}

	public Long deleteNomineeDtlsHst(Long empid) {
		Session currentSession = manager.unwrap(Session.class);
		long result = 0l;
		try {
			String hql4 = "delete from nominee_details_hst where employee_id = " + empid;
			Query query4 = currentSession.createNativeQuery(hql4);
			result = query4.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Object[]> GetCurrentPostByLvlTwoDetails(long designationId, String ddocode, long locId) {
		// TODO Auto-generated method stub
		Session currentSession = manager.unwrap(Session.class);
		StringBuffer hql = new StringBuffer();
		hql.append(
				"Select p.POST_ID,r.post_name from org_post_mst p inner join org_post_details_rlt r on r.post_id = p.post_id and p.activate_flag = 1 inner join MST_DCPS_DDO_OFFICE off on off.DCPS_DDO_OFFICE_MST_ID =p.office_id ");
		hql.append("where r.LOC_ID = '" + locId + "' and (p.END_DATE > now() or p.END_DATE is null)");
		hql.append("and p.post_Id in (select RL.post_detail_Id ");
		hql.append(
				"from employee_mst_details RL where RL.post_detail_Id is not null and rl.ddo_Code =(select ddo_code ");
		hql.append("from org_ddo_mst where location_code= '" + locId + "')) ");
		hql.append("and r.DSGN_ID = '" + designationId + "' ");
		hql.append("and p.ACTIVATE_FLAG = 1 ");
		hql.append("and p.POST_TYPE_LOOKUP_ID in (10001198130,10001198129,10001198155) ");
		System.out.println("\n " + hql);

		Query query = currentSession.createNativeQuery(hql.toString());
		return query.list();
	}

	@Override
	public void updateFormStatus(Long employeeId) {
		Session currentSession = manager.unwrap(Session.class);
		long result = 0l;
		try {
			String hql4 = "update employee_mst_details set form_status=1 where employee_id = " + employeeId;
			Query query4 = currentSession.createNativeQuery(hql4);
			result = query4.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
