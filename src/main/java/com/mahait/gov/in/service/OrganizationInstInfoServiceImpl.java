package com.mahait.gov.in.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.InstituteType;
import com.mahait.gov.in.entity.OrgDdoMst;
import com.mahait.gov.in.model.OrgDdoMstModel;
import com.mahait.gov.in.repository.OrganizationInstInfoRepo;

import jakarta.validation.Valid;

@Service
@Transactional
public class OrganizationInstInfoServiceImpl implements OrganizationInstInfoService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private OrganizationInstInfoRepo organizationInstInfoRepo;

	@Override
	public OrgDdoMstModel findDDOInfo(String userName) {
		

		OrgDdoMst lstprop = organizationInstInfoRepo.findDDOInfo(userName);
		OrgDdoMstModel orgDdoMstModel = new OrgDdoMstModel();
       if(lstprop!=null) {
    	   orgDdoMstModel.setDdoOffice(lstprop.getDdoOffice());
    	   orgDdoMstModel.setDesignationId(lstprop.getDsgnCode());
    	   orgDdoMstModel.setStartDate(lstprop.getStartDate());
    	   orgDdoMstModel.setTanNo(lstprop.getTanNo());
    	   orgDdoMstModel.setItaWardNo(lstprop.getItawardcircle());
    	   orgDdoMstModel.setBankName(lstprop.getBankName());
    	   orgDdoMstModel.setBranchName(lstprop.getBranchName());
    	   orgDdoMstModel.setIfscCode(lstprop.getIfsCode());
    	   orgDdoMstModel.setAccountNo(lstprop.getAccountNo());
    	   orgDdoMstModel.setRemarks(lstprop.getRemarks());
    	   orgDdoMstModel.setInstituteType(lstprop.getInstituteTypeId());
    	   if(lstprop.getBankPassbook()!=null)
    	   orgDdoMstModel.setBankPassbook(lstprop.getBankPassbook());
    	   else
    		   orgDdoMstModel.setBankPassbook("N");
    	   if(lstprop.getBankCheaque()!=null)
    	   orgDdoMstModel.setBankCheaque(lstprop.getBankCheaque());
    	   else
    		   orgDdoMstModel.setBankCheaque("N");
    	   if(lstprop.getDeptLetter()!=null)
    		   orgDdoMstModel.setDeptLetter(lstprop.getDeptLetter());
    	   else
    		   orgDdoMstModel.setDeptLetter("N");
       }   
            
        
        return orgDdoMstModel;
	}

	@Override
	public List<InstituteType> lstInstType() {
		// TODO Auto-generated method stub
		return organizationInstInfoRepo.lstInstType();
	}

	@Override
	public List<Object[]> getBankBranch(String valueOf) {
		// TODO Auto-generated method stub
		List<Object[]> lstbranchname = organizationInstInfoRepo.getBankBranch(valueOf);
		return lstbranchname;
	}

	@Override
	public Long SaveorgInstituteInfo(@Valid OrgDdoMstModel orgDdoMstModel) {
		
		OrgDdoMst objForSave = organizationInstInfoRepo.findDDOInfo(orgDdoMstModel.getDdoCode());
		objForSave.setDdoOffice(orgDdoMstModel.getDdoOffice());
		objForSave.setDsgnCode(orgDdoMstModel.getDesignationId());
		objForSave.setStartDate(orgDdoMstModel.getStartDate());
		objForSave.setTanNo(orgDdoMstModel.getTanNo());
		objForSave.setItawardcircle(orgDdoMstModel.getItaWardNo());
		objForSave.setBankName(orgDdoMstModel.getBankName());
		objForSave.setBranchName(orgDdoMstModel.getBranchName());
		objForSave.setIfsCode(orgDdoMstModel.getIfscCode());
		objForSave.setAccountNo(orgDdoMstModel.getAccountNo());
		objForSave.setRemarks(orgDdoMstModel.getRemarks());
		objForSave.setInstituteTypeId(orgDdoMstModel.getInstituteType());
		objForSave.setDdoCode(orgDdoMstModel.getDdoCode());
		
		Long saveId=null;
		 organizationInstInfoRepo.updateorgInstituteInfo(objForSave);
		 
			 saveId = objForSave.getDdoId();
		
		
		return saveId;
	}

	@Override
	public int updateorgInstituteInfo(OrgDdoMstModel orgDdoMstModel) {
		
		
		OrgDdoMst findDDOInfo = organizationInstInfoRepo.findDDOInfo(orgDdoMstModel.getDdoCode());
		findDDOInfo.setDdoOffice(orgDdoMstModel.getDdoOffice());
		findDDOInfo.setDsgnCode(orgDdoMstModel.getDesignationId());
		findDDOInfo.setStartDate(orgDdoMstModel.getStartDate());
		findDDOInfo.setTanNo(orgDdoMstModel.getTanNo());
		findDDOInfo.setItawardcircle(orgDdoMstModel.getItaWardNo());
		findDDOInfo.setBankName(orgDdoMstModel.getBankName());
		findDDOInfo.setBranchName(orgDdoMstModel.getBranchName());
		findDDOInfo.setIfsCode(orgDdoMstModel.getIfscCode());
		findDDOInfo.setAccountNo(orgDdoMstModel.getAccountNo());
	  	 findDDOInfo.setRemarks(orgDdoMstModel.getRemarks());
	  	 findDDOInfo.setInstituteTypeId(orgDdoMstModel.getInstituteType());
		organizationInstInfoRepo.updateorgInstituteInfo(findDDOInfo);
	
		
		return 10;
	}
	

	

	
}
