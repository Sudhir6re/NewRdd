package com.mahait.gov.in.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mahait.gov.in.entity.DdoOffice;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.entity.OrgUserMst;
import com.mahait.gov.in.model.OrganisationDtlsModel;
import com.mahait.gov.in.repository.OrganisationDtlsRepo;
import com.mahait.gov.in.repository.OrganizationInstInfoRepo;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
@Transactional
public class OrganisationDtlsServiceImpl implements OrganisationDtlsService {

	@Autowired
	OrganisationDtlsRepo organisationDtlsRepo;

	@Autowired
	OrganizationInstInfoRepo organizationInstInfoRepo;

	@Override
	public @Valid OrganisationDtlsModel lstOfficeDetails(String ddoCode) {
		DdoOffice lstprop = organisationDtlsRepo.lstGetOfficeDtls(ddoCode);
		OrganisationDtlsModel obj = new OrganisationDtlsModel();
		if (lstprop != null) {
			obj.setDdoCode(lstprop.getDcpsDdoCode());
			obj.setOfficeName(lstprop.getDcpsDdoOfficeName());
			obj.setStateId(lstprop.getDcpsDdoOfficeState());
			obj.setDistrictId(lstprop.getDcpsDdoOfficeDistrict());
			obj.setTalukaId(lstprop.getDcpsDdoOfficeTaluka());
			obj.setCity(lstprop.getDcpsDdoOfficeTown());
			obj.setVillage(lstprop.getDcpsDdoOfficeVillage());
			obj.setAddress(lstprop.getDcpsDdoOfficeAddress1());
			obj.setPin(lstprop.getDcpsDdoOfficePin());

			obj.setCityClass(lstprop.getDcpsDdoOfficeCityClass());
			obj.setInstiNo(lstprop.getDiceCode());
			obj.setPercGrant(lstprop.getDcpsDdoOfficeGrant());
			obj.setTel1(lstprop.getDcpsDdoOfficeTelNo1());
			obj.setTel2(lstprop.getDcpsDdoOfficeTelNo2());
			obj.setFax(lstprop.getDcpsDdoOfficeFax());
			obj.setEmail(lstprop.getDcpsDdoOfficeEmail());
		}
		// return obj;

		OrgDdoMst orgInfo = organizationInstInfoRepo.findDDOInfo(ddoCode);
		// OrgDdoMstModel orgDdoMstModel = new OrgDdoMstModel();
		if (orgInfo != null) {
			obj.setDdoOffice(orgInfo.getDdoOffice());
			obj.setDesignationId(orgInfo.getDsgnCode());
			obj.setStartDate(orgInfo.getStartDate());
			obj.setTanNo(orgInfo.getTanNo());
			obj.setItaWardNo(orgInfo.getItawardcircle());
			obj.setBankName(orgInfo.getBankName());
			obj.setBranchName(orgInfo.getBranchName());
			obj.setIfscCode(orgInfo.getIfsCode());
			obj.setAccountNo(orgInfo.getAccountNo());
			obj.setRemarks(orgInfo.getRemarks());
			obj.setInstituteType(orgInfo.getInstituteTypeId());
			if (orgInfo.getBankPassbook() != null)
				obj.setBankPassbook(orgInfo.getBankPassbook());
			else
				obj.setBankPassbook("N");
			if (orgInfo.getBankCheaque() != null)
				obj.setBankCheaque(orgInfo.getBankCheaque());
			else
				obj.setBankCheaque("N");
			if (orgInfo.getDeptLetter() != null)
				obj.setDeptLetter(orgInfo.getDeptLetter());
			else
				obj.setDeptLetter("N");
		}

		return obj;

	}

	@Override
	public Long SaveorgInstituteInfo(@Valid OrganisationDtlsModel organisationDtlsModel, OrgUserMst messages) {

		DdoOffice objForSave = new DdoOffice();

		objForSave.setDcpsDdoOfficeName(organisationDtlsModel.getOfficeName());
		objForSave.setDcpsDdoOfficeState(organisationDtlsModel.getStateId());
		objForSave.setDcpsDdoOfficeDistrict(organisationDtlsModel.getDistrictId());
		objForSave.setDcpsDdoOfficeTaluka(organisationDtlsModel.getTalukaId());
		objForSave.setDcpsDdoOfficeTown(organisationDtlsModel.getCity());
		objForSave.setDcpsDdoOfficeVillage(organisationDtlsModel.getVillage());
		objForSave.setDcpsDdoOfficeAddress1(organisationDtlsModel.getAddress());
		objForSave.setDcpsDdoOfficePin(organisationDtlsModel.getPin());
		objForSave.setDcpsDdoOfficeCityClass(organisationDtlsModel.getCityClass());
		objForSave.setDiceCode(organisationDtlsModel.getInstiNo());
		objForSave.setDcpsDdoOfficeGrant(organisationDtlsModel.getPercGrant());
		objForSave.setDcpsDdoOfficeTelNo1(organisationDtlsModel.getTel1());
		objForSave.setDcpsDdoOfficeTelNo2(organisationDtlsModel.getTel2());
		objForSave.setDcpsDdoOfficeFax(organisationDtlsModel.getFax());
		objForSave.setDcpsDdoOfficeEmail(organisationDtlsModel.getEmail());
		objForSave.setStatusFlag(0l);
		objForSave.setDcpsDdoOfficeDdoFlag("YES");
		objForSave.setLangId(1l);
		objForSave.setLocId(1l);
		objForSave.setCreatedDate(new Date());
		objForSave.setPostId(messages.getCreatedByPost().getPostId());
		objForSave.setUpdatedPostId(messages.getCreatedByPost().getPostId());
		objForSave.setUserId(messages.getUserId());
		objForSave.setDbId(99l);

		OrgDdoMst orgInfo = new OrgDdoMst();
		// OrgDdoMst orgInfo =
		// organizationInstInfoRepo.findDDOInfo(organisationDtlsModel.getDdoCode());
		orgInfo.setDdoOffice(organisationDtlsModel.getDdoOffice());
		orgInfo.setDsgnCode(organisationDtlsModel.getDesignationId());
		orgInfo.setStartDate(organisationDtlsModel.getStartDate());
		orgInfo.setTanNo(organisationDtlsModel.getTanNo());
		orgInfo.setItawardcircle(organisationDtlsModel.getItaWardNo());
		orgInfo.setBankName(organisationDtlsModel.getBankName());
		orgInfo.setBranchName(organisationDtlsModel.getBranchName());
		orgInfo.setIfsCode(organisationDtlsModel.getIfscCode());
		orgInfo.setAccountNo(organisationDtlsModel.getAccountNo());
		orgInfo.setRemarks(organisationDtlsModel.getRemarks());
		orgInfo.setInstituteTypeId(organisationDtlsModel.getInstituteType());
		orgInfo.setDdoCode(organisationDtlsModel.getDdoCode());

		// Long saveId=null;
		organizationInstInfoRepo.updateorgInstituteInfo(orgInfo);

		// saveId = objForSave.getDdoId();

		Long saveId = 0l;
		if (organisationDtlsModel.getDdoCode() == null) {
			saveId = organisationDtlsRepo.saveorgInstInfo(objForSave);
		} else {
			saveId = 5l;
			organisationDtlsRepo.updateorgInstituteInfo(objForSave);
		}

		return saveId;

	}

	@Override
	public Map<String, Object> findDataByDistrict(String districtId) {
		List talukalist = organisationDtlsRepo.findtalukalist(districtId);

		if (talukalist.size() > 0 && talukalist != null) {

			List citylist = organisationDtlsRepo.findcitylist(districtId);

			Map<String, Object> response = new HashMap<>();

			response.put("talukaList", talukalist);
			response.put("cityList", citylist);
			return response;
		} else {
			Map<String, Object> response = new HashMap<>();
			return response;
		}

	}

	@Override
	public int updateddoOfficeDetails(OrganisationDtlsModel organisationDtlsModel, OrgUserMst messages) {

		DdoOffice lstprop = organisationDtlsRepo.lstGetOfficeDtls(messages.getDdoCode());

		lstprop.setDcpsDdoOfficeName(organisationDtlsModel.getOfficeName());
		lstprop.setDcpsDdoOfficeState(organisationDtlsModel.getStateId());
		lstprop.setDcpsDdoOfficeDistrict(organisationDtlsModel.getDistrictId());
		lstprop.setDcpsDdoOfficeTaluka(organisationDtlsModel.getTalukaId());
		lstprop.setDcpsDdoOfficeTown(organisationDtlsModel.getCity());
		lstprop.setDcpsDdoOfficeVillage(organisationDtlsModel.getVillage());
		lstprop.setDcpsDdoOfficeAddress1(organisationDtlsModel.getAddress());
		lstprop.setDcpsDdoOfficeCityClass(organisationDtlsModel.getCityClass());
		lstprop.setDiceCode(organisationDtlsModel.getInstiNo());
		lstprop.setDcpsDdoOfficeGrant(organisationDtlsModel.getPercGrant());
		lstprop.setDcpsDdoOfficeTelNo1(organisationDtlsModel.getTel1());
		lstprop.setDcpsDdoOfficeTelNo2(organisationDtlsModel.getTel2());
		lstprop.setDcpsDdoOfficeFax(organisationDtlsModel.getFax());
		lstprop.setDcpsDdoOfficeEmail(organisationDtlsModel.getEmail());
		lstprop.setDcpsDdoOfficePin(organisationDtlsModel.getPin());
		lstprop.setStatusFlag(0l);
		lstprop.setDcpsDdoOfficeDdoFlag("YES");
		lstprop.setLangId(1l);
		lstprop.setLocId(1l);
		lstprop.setCreatedDate(new Date());
		lstprop.setPostId(messages.getCreatedByPost().getPostId());
		lstprop.setUpdatedPostId(messages.getCreatedByPost().getPostId());
		lstprop.setUserId(messages.getUserId());
		lstprop.setDbId(99l);

		organisationDtlsRepo.updateddoOfficeDetails(lstprop);

		OrgDdoMst findDDOInfo = organizationInstInfoRepo.findDDOInfo(messages.getDdoCode());
		findDDOInfo.setDdoOffice(organisationDtlsModel.getDdoOffice());
		findDDOInfo.setDsgnCode(organisationDtlsModel.getDesignationId());
		findDDOInfo.setStartDate(organisationDtlsModel.getStartDate());
		findDDOInfo.setTanNo(organisationDtlsModel.getTanNo());
		findDDOInfo.setItawardcircle(organisationDtlsModel.getItaWardNo());
		findDDOInfo.setBankName(organisationDtlsModel.getBankName());
		findDDOInfo.setBranchName(organisationDtlsModel.getBranchName());
		findDDOInfo.setIfsCode(organisationDtlsModel.getIfscCode());
		findDDOInfo.setAccountNo(organisationDtlsModel.getAccountNo());
		findDDOInfo.setRemarks(organisationDtlsModel.getRemarks());
		findDDOInfo.setInstituteTypeId(organisationDtlsModel.getInstituteType());
		findDDOInfo.setBankPassbook(organisationDtlsModel.getBankPassbook());
		findDDOInfo.setBankCheaque(organisationDtlsModel.getBankCheaque());
		findDDOInfo.setDeptLetter(organisationDtlsModel.getDeptLetter());
		organizationInstInfoRepo.updateorgInstituteInfo(findDDOInfo);

		return 10;
	}

}
