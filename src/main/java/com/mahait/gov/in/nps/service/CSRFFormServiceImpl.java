package com.mahait.gov.in.nps.service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mahait.gov.in.common.StringHelperUtils;
import com.mahait.gov.in.entity.CmnLookupMst;
import com.mahait.gov.in.entity.MstEmployeeEntity;
import com.mahait.gov.in.entity.MstNomineeDetailsEntity;
import com.mahait.gov.in.nps.entity.MstEmployeeNPSEntity;
import com.mahait.gov.in.nps.entity.TrnNpsRegFileEntity;
import com.mahait.gov.in.nps.model.CSRFFormModel;
import com.mahait.gov.in.nps.model.MstEmployeeNPSModel;
import com.mahait.gov.in.nps.repository.CSRFFormRepo;

import jakarta.validation.Valid;


@Service("csrfFormService")
@Transactional
public class CSRFFormServiceImpl implements CSRFFormService {

	@Autowired
	private CSRFFormRepo csrfFormRepo;

	public List<CSRFFormModel> findAllEmployees(String ddoCode) {
		List<Object[]> lstprop = csrfFormRepo.findAllEmployees(ddoCode);
		List<CSRFFormModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				CSRFFormModel obj = new CSRFFormModel();
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[0]));
				obj.setEmpName(StringHelperUtils.isNullString(objLst[1]));
				obj.setDOJ(StringHelperUtils.isNullDate(objLst[2]));
				obj.setDesignationName(StringHelperUtils.isNullString(objLst[3]));
				obj.setDDOCode(StringHelperUtils.isNullString(objLst[4]));
				obj.setOfficeName(StringHelperUtils.isNullString(objLst[5]));
				obj.setDcpsId(StringHelperUtils.isNullString(objLst[6]));
				BigInteger empId = (BigInteger)objLst[7];
				obj.setEmpId(empId);

				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public MstEmployeeEntity findEmployeeBySevaarthId(Long empId) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findEmployeeBySevaarthId(empId);
	}

	@Override
	public MstEmployeeNPSModel findCSRFEmployeeBySevaarthId(Long empId) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findCSRFEmployeeBySevaarthId(empId);
	}

	@Override
	public List<MstNomineeDetailsEntity> findNomineeBySevaarthId(int sevaarthId) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findNomineeBySevaarthId(sevaarthId);
	}

	@Override
	public Long saveCSRF(MstEmployeeEntity mstEmployeeEntity, MultipartFile[] files, String ddoLevel2) {

		MstEmployeeNPSEntity mstEmployeeNPSEntity = new MstEmployeeNPSEntity();
		mstEmployeeNPSEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
		mstEmployeeNPSEntity.setEmployeeId(mstEmployeeEntity.getEmployeeId());
		mstEmployeeNPSEntity.setSalutation(mstEmployeeEntity.getSalutation().toString());
		mstEmployeeNPSEntity.setEmployeeFirstName(mstEmployeeEntity.getEmployeeFNameEn());
		mstEmployeeNPSEntity.setEmployeeMiddleName(mstEmployeeEntity.getEmployeeMNameEn());
		mstEmployeeNPSEntity.setEmployeeLastName(mstEmployeeEntity.getEmployeeLNameEn());
		mstEmployeeNPSEntity.setEmployeeFullName(mstEmployeeEntity.getEmployeeFullNameEn());
		mstEmployeeNPSEntity.setEmployeeMotherName(mstEmployeeEntity.getEmployeeMotherName());
		mstEmployeeNPSEntity.setEmployeeFatherHubandName(mstEmployeeEntity.getEmployeeFatherHubandName());
		mstEmployeeNPSEntity.setEmployeeGender(mstEmployeeEntity.getGender());
		mstEmployeeNPSEntity.setPpanNo(mstEmployeeEntity.getSevaarthId() + "PAN");
		mstEmployeeNPSEntity.setEmployeeBirthPlace(mstEmployeeEntity.getEmployeeBirthPlace());
		mstEmployeeNPSEntity.setEmployeeSpouseName(mstEmployeeEntity.getEmployeeSpouseName());

		mstEmployeeNPSEntity.setEmployeeMaritalStatus(Character.toString(mstEmployeeEntity.getMaritalStatus()));

		mstEmployeeNPSEntity.setEmployeePan(mstEmployeeEntity.getPanNo());

		// System.out.println("city class="+mstEmployeeEntity.getCityClass());
		// mstEmployeeNPSEntity.setEmployeeEmploymentClass(mstEmployeeEntity.getCityClass().toString());

		mstEmployeeNPSEntity.setEmpClass(mstEmployeeEntity.getEmpClass().intValue());

		mstEmployeeNPSEntity.setEmployeeBankAccountNo(mstEmployeeEntity.getBankAcntNo().toString());

		mstEmployeeNPSEntity.setDdoCode(ddoLevel2);

		////mstEmployeeNPSEntity.setNumNominess(mstEmployeeEntity.getMstNomineeDetailsEntity().size());

		mstEmployeeNPSEntity.setCountry("IN");
		mstEmployeeNPSEntity.setEmpPermanentCountry("IN");
		mstEmployeeNPSEntity.setSotLangCode("00");
		mstEmployeeNPSEntity.setNationality("IN");

		String Title = mstEmployeeEntity.getSalutation().toString();
		switch (Title) {
		case "1":
			mstEmployeeNPSEntity.setTitle("Shri.");
			break;
		case "2":
			mstEmployeeNPSEntity.setTitle("Smt.");
			break;
		case "3":
			mstEmployeeNPSEntity.setTitle("Kum.");
			break;
		case "4":
			mstEmployeeNPSEntity.setTitle("Mr.");
			break;
		case "5":
			mstEmployeeNPSEntity.setTitle("Mrs.");
			break;
		case "6":
			mstEmployeeNPSEntity.setTitle("Mast");
			break;
		case "7":
			mstEmployeeNPSEntity.setTitle("Dr");
			break;
		}

		// long UidNo=mstEmployeeEntity.getUidNo();
		String UID = mstEmployeeEntity.getUidNo();
		mstEmployeeNPSEntity.setEmployeeAadhar(UID);
		mstEmployeeNPSEntity.setEmployeeDOJ(mstEmployeeEntity.getDoj());
		mstEmployeeNPSEntity.setEmployeeDateOfRetirement(mstEmployeeEntity.getSuperAnnDate());
		mstEmployeeNPSEntity.setEmployeeBankName(mstEmployeeEntity.getEmployeeBankName());
		mstEmployeeNPSEntity.setEmployeeBankBranchName(mstEmployeeEntity.getEmployeeBankBranchName());
		mstEmployeeNPSEntity.setEmployeeBankBankAddress(mstEmployeeEntity.getEmployeeBankBankAddress());
		mstEmployeeNPSEntity.setEmployeeBankPinCode(mstEmployeeEntity.getEmployeeBankPinCode());
		mstEmployeeNPSEntity.setIFSCCode(mstEmployeeEntity.getIfscCode());
		mstEmployeeNPSEntity.setFlatUnitNo(mstEmployeeEntity.getFlatUnitNo());
		mstEmployeeNPSEntity.setBuildingName(mstEmployeeEntity.getBuildingName());
		mstEmployeeNPSEntity.setLocality(mstEmployeeEntity.getLocality());
		mstEmployeeNPSEntity.setDistrict(mstEmployeeEntity.getDistrict());
		mstEmployeeNPSEntity.setState(mstEmployeeEntity.getState());
		mstEmployeeNPSEntity.setPinCode(mstEmployeeEntity.getPinCode());
		mstEmployeeNPSEntity.setEmpPermanentFlatUnitNo(mstEmployeeEntity.getEmpPermanentFlatUnitNo());
		mstEmployeeNPSEntity.setEmpPermanentBuildingName(mstEmployeeEntity.getEmpPermanentBuildingName());
		mstEmployeeNPSEntity.setEmpPermanentLocality(mstEmployeeEntity.getEmpPermanentLocality());
		mstEmployeeNPSEntity.setEmpPermanentDistrict(mstEmployeeEntity.getEmpPermanentDistrict());
		mstEmployeeNPSEntity.setEmpPermanentState(mstEmployeeEntity.getEmpPermanentState());
		mstEmployeeNPSEntity.setEmpPermanentPinCode(mstEmployeeEntity.getEmpPermanentPinCode());
		mstEmployeeNPSEntity.setEmployeeBirthPlace(mstEmployeeEntity.getEmployeeBirthPlace());
		mstEmployeeNPSEntity.setEmpMobileNo(mstEmployeeEntity.getMobileNo1().toString());
		mstEmployeeNPSEntity.setEmpEmailId(mstEmployeeEntity.getEmailId());
		mstEmployeeNPSEntity.setEmpPhoneNo(mstEmployeeEntity.getLandlineNo());
		// mstEmployeeNPSEntity.setCountry(mstEmployeeEntity.getCountry());
		mstEmployeeNPSEntity.setIncomeRange(mstEmployeeEntity.getIncomeRange().intValue());
		mstEmployeeNPSEntity.setEduQual(mstEmployeeEntity.getEduQual().intValue());
		mstEmployeeNPSEntity.setDobProof(mstEmployeeEntity.getDobProof().intValue());
	////	mstEmployeeNPSEntity.setDisplayNameonPranCard(mstEmployeeEntity.getDisplayNameonPranCard().intValue());
		mstEmployeeNPSEntity.setCreatedDate(new Date());
		mstEmployeeNPSEntity.setCreateduserId(1);
		// mstEmployeeNPSEntity.setEmployeeGender(mstEmployeeEntity.getGender());
		mstEmployeeNPSEntity.setEmployeeDOB(mstEmployeeEntity.getDob());
		mstEmployeeNPSEntity.setEmployeePan(mstEmployeeEntity.getPanNo());
		mstEmployeeNPSEntity.setIsActive(3);

		mstEmployeeNPSEntity.setPayScaleDesc(mstEmployeeEntity.getPayScaleDesc());

		if (mstEmployeeEntity.getMstNomineeDetailsEntity() != null) {
			int i = 0;
			for (MstNomineeDetailsEntity mstNomineeDetailsEntity : mstEmployeeEntity.getMstNomineeDetailsEntity()) {
				i++;
				String nomineeName = StringHelperUtils.isNullString(mstNomineeDetailsEntity.getNomineename());
				String relation = StringHelperUtils.isNullString(mstNomineeDetailsEntity.getRelation());
				Date dob = mstNomineeDetailsEntity.getDob();
				Integer percentageShare = StringHelperUtils.isNullInt(mstNomineeDetailsEntity.getPercent_share());
				String guardianName = StringHelperUtils.isNullString(mstNomineeDetailsEntity.getGuardianName());
				String address = StringHelperUtils.isNullString(mstNomineeDetailsEntity.getNomineeaddress());
				String majorOrMinor = StringHelperUtils.isNullString(mstNomineeDetailsEntity.getMajorMinor());
				if (i == 1) {
					mstEmployeeNPSEntity.setEmpNominee1Name(nomineeName);
					mstEmployeeNPSEntity.setEmpNominee1relationship(relation);
					mstEmployeeNPSEntity.setEmpNominee1DOB(dob);
					mstEmployeeNPSEntity.setEmpNominee1Share(percentageShare.toString());
					mstEmployeeNPSEntity.setEmpNominee1GuardName(guardianName);
					mstEmployeeNPSEntity.setNominee1MajorMinor(majorOrMinor);
					mstEmployeeNPSEntity.setNominee1Address(address);
				} else if (i == 2) {
					mstEmployeeNPSEntity.setEmpNominee2Name(nomineeName);
					mstEmployeeNPSEntity.setEmpNominee2relationship(relation);
					mstEmployeeNPSEntity.setEmpNominee2DOB(dob);
					mstEmployeeNPSEntity.setEmpNominee2Share(percentageShare.toString());
					mstEmployeeNPSEntity.setEmpNominee2GuardName(guardianName);
					mstEmployeeNPSEntity.setNominee2MajorMinor(majorOrMinor);
					mstEmployeeNPSEntity.setNominee2Address(address);
				} else if (i == 3) {
					mstEmployeeNPSEntity.setEmpNominee3Name(nomineeName);
					mstEmployeeNPSEntity.setEmpNominee3relationship(relation);
					mstEmployeeNPSEntity.setEmpNominee3DOB(dob);
					mstEmployeeNPSEntity.setEmpNominee3Share(percentageShare.toString());
					mstEmployeeNPSEntity.setEmpNominee3GuardName(guardianName);
					mstEmployeeNPSEntity.setNominee3MajorMinor(majorOrMinor);
					mstEmployeeNPSEntity.setNominee3Address(address);
				}

			}
		}

		// mstEmployeeNPSEntity.setEmployeeSpouseName(mstEmployeeEntity.getEmployeeSpouseName());
		String filePath = "";
		// mstEmployeeNPSEntity.setSevaarthId(mstEmployeeEntity.getSevaarthId());
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().contains("windows")) {
			filePath = "E:/output/";
		} else {
			filePath = "/home/fileUpload/";
		}

		System.out.println("file saving path >>>" + filePath);

		if (!new File(filePath).exists()) {
			new File(filePath).mkdirs();
		}

		filePath = filePath + mstEmployeeEntity.getEmployeeId() + "/" + mstEmployeeEntity.getSevaarthId()
				+ File.separator;
		if (!new File(filePath).exists()) {
			new File(filePath).mkdirs();
		}
		MultipartFile signatureAttachmentIdnew = mstEmployeeEntity.getSignatureAttachmentIdnew();
		if (signatureAttachmentIdnew != null && !signatureAttachmentIdnew.getOriginalFilename().equals("")) {
			// String sign = signatureAttachmentIdnew.getOriginalFilename().replace(" ",
			// "_");
			String sign = mstEmployeeEntity.getEmployeeId() + "_sign.jpg";

			System.out.println("file saving path >>>" + filePath);

			if (sign != "") {
				try {
					File file = new File(filePath + sign);
					FileCopyUtils.copy(signatureAttachmentIdnew.getBytes(), file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			mstEmployeeNPSEntity.setEmployeeSignAttachment(filePath + sign);
		}

		MultipartFile photoAttachmentIdnew = mstEmployeeEntity.getPhotoAttachmentIdnew();
		if (photoAttachmentIdnew != null && !photoAttachmentIdnew.getOriginalFilename().equals("")) {
			// String photo = photoAttachmentIdnew.getOriginalFilename().replace(" ", "_");
			String photo = mstEmployeeEntity.getEmployeeId() + "_photo.jpg";
			if (photo != "") {
				try {
					File file = new File(filePath + photo);
					FileCopyUtils.copy(photoAttachmentIdnew.getBytes(), file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			mstEmployeeNPSEntity.setEmployeePhotoAttachment(filePath + photo);
		}

		Long saveId = csrfFormRepo.saveCSRF(mstEmployeeNPSEntity);
		return saveId;

	}

	@Override
	public MstEmployeeNPSEntity findEmployeeDtlsBySevaarthId(Long empId) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findEmployeeDtlsBySevaarthId(empId);
	}

	@Override
	public List<CSRFFormModel> findAllCSRFApprovedEmployees() {
		List<Object[]> lstprop = csrfFormRepo.findAllCSRFApprovedEmployees();
		List<CSRFFormModel> lstObj = new ArrayList<>();
		if (!lstprop.isEmpty()) {
			for (Object[] objLst : lstprop) {
				CSRFFormModel obj = new CSRFFormModel();
				obj.setSevaarthId(StringHelperUtils.isNullString(objLst[0]));
				obj.setEmpName(StringHelperUtils.isNullString(objLst[1]));
				obj.setDdoAsst(StringHelperUtils.isNullString(objLst[2]));
				obj.setDdo(StringHelperUtils.isNullString(objLst[3]));
				BigInteger empid = (BigInteger)objLst[4];
				obj.setEmpId(empid);

				lstObj.add(obj);
			}
		}
		return lstObj;
	}

	@Override
	public List<Object[]> getEmpData(String sevaarthId, String ddoAsst) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findAllCSRFApprovedEmployees(sevaarthId, ddoAsst);
	}

	@Override
	public List<MstEmployeeNPSEntity> getEmpDataForTextFile(String userName, int countEmp) {
		return csrfFormRepo.getEmpDataForTextFile(userName, countEmp);
	}

	@Override
	public String getDDOLevel2FromDDO1(String userName) {
		return csrfFormRepo.getDDOLevel2FromDDO1(userName);
	}

	@Override
	public List<Object[]> getDeptNameFromDDO2(String userName) {
		return csrfFormRepo.getDeptNameFromDDO2(userName);
	}

	@Override
	public List<Object[]> getempData(String sevaarthId) {
		return csrfFormRepo.getempData(sevaarthId);
	}

	@Override
	public Integer getSeqUpdate() {
		// TODO Auto-generated method stub
		return csrfFormRepo.getSeqUpdate();
	}

	@Override
	public Long getSequenceForTextFile(String sequenceName) {
		// TODO Auto-generated method stub
		return csrfFormRepo.getSequenceForTextFile(sequenceName);
	}

	@Override
	public int saveTrnNpsRegFile(TrnNpsRegFileEntity trnNpsRegFile) {
		// TODO Auto-generated method stub
		return csrfFormRepo.saveTrnNpsRegFile(trnNpsRegFile);

	}

	@Override
	public String getPayScale(Long payScaleCode) {
		// TODO Auto-generated method stub
		return csrfFormRepo.getPayScale(payScaleCode);
	}

	@Override
	public TrnNpsRegFileEntity findTrnNpsFileEntityById(Integer id) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findTrnNpsFileEntityById(id);
	}

	@Override
	public void updateTrnNpsFileEntity(TrnNpsRegFileEntity trnNpsRegFileEntity) {
		// TODO Auto-generated method stub
		csrfFormRepo.updateTrnNpsFileEntity(trnNpsRegFileEntity);
	}

	@Override
	public List<Object[]> findNpsIdByAckNo(String ackNo) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findNpsIdByAckNo(ackNo);
	}

	@Override
	public MstEmployeeNPSEntity findEmployeeByNpsid(Integer npsId) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findEmployeeByNpsid(npsId);
	}

	@Override
	public void updatePranNumberByNpsId(MstEmployeeNPSEntity mstEmployeeNPSEntity) {
		// TODO Auto-generated method stub
		csrfFormRepo.updatePranNumberByNpsId(mstEmployeeNPSEntity);
	}

	@Override
	public void updateEmployeeByEmpId(MstEmployeeEntity mstEmployeeEntity) {
		// TODO Auto-generated method stub
		csrfFormRepo.updateEmployeeByEmpId(mstEmployeeEntity);
	}

	@Override
	public String getStateName(String empState) {
		// TODO Auto-generated method stub
		return csrfFormRepo.getStateName(empState);
	}

	@Override
	public List<Object[]> viewCSRFPhotoSign(int empId) {
		// TODO Auto-generated method stub
		return csrfFormRepo.viewCSRFPhotoSign(empId);
	}

	@Override
	public String getDtoRegNumber(String ddoLevel2) {
		return csrfFormRepo.getDtoRegNumber(ddoLevel2);
	}

	@Override
	public Long getNextSeqNum(String seqName) {
		// TODO Auto-generated method stub
		return csrfFormRepo.getNextSeqNum(seqName);
	}

	@Override
	public Integer getRegSeqNo(String strDate) {
		// TODO Auto-generated method stub
		return csrfFormRepo.getRegSeqNo(strDate);
	}

	@Override
	public String getDdoRegNumber(String userName) {
		return csrfFormRepo.getDdoRegNumber(userName);
	}

	@Override
	public int gettrano(String userName) {
		return csrfFormRepo.gettrano(userName);
	}

	@Override
	public void updatemstEmployeeEntity(@Valid MstEmployeeEntity mstEmployeeEntity) {
		 csrfFormRepo.saveOrUpdate(mstEmployeeEntity);
	}

	@Override
	public List<CmnLookupMst> findCityClass() {
		// TODO Auto-generated method stub
		return csrfFormRepo.findCityClass();
	}

	@Override
	public List<MstNomineeDetailsEntity> findNomineeDtls(Long empId) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findNomineeDtls(empId);
	}

	@Override
	public String findBankName(Long bankCode) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findBankName(bankCode);
	}

	@Override
	public String findBankBranchName(Long bankBranchCode) {
		// TODO Auto-generated method stub
		return csrfFormRepo.findBankBranchName(bankBranchCode);
	}


}
