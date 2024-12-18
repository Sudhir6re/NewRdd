/*package com.mahait.gov.in.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahait.gov.in.entity.MstDcpsBillGroup;
import com.mahait.gov.in.model.MpgSchemeBillGroupModel;

@Service
@Transactional
public class MpgSchemeBillGroupServiceImpl implements MpgSchemeBillGroupService{

SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private MpgSchemeBillGroupRepo mpgSchemeBillGroupRepo;
	
	@Override
	public int saveMpgSchemeBillGroup(MpgSchemeBillGroupModel mpgSchemeBillGroupModel) {
		MstDcpsBillGroup objEntity = new MstDcpsBillGroup();
		
		objEntity.setDcpsDdoBillGroupId(mpgSchemeBillGroupModel.getSchemebillGroupId());
		objEntity.setBillDescription(mpgSchemeBillGroupModel.getBillDescription());
//		objEntity.setScheme_name(mpgSchemeBillGroupModel.getSchemeName());
//		objEntity.setSchemeCode(mpgSchemeBillGroupModel.getSchemeCode());
		objEntity.setScheme_name("MJP");
		objEntity.setSchemeCode("2226F5201");
		objEntity.setDdoCode(mpgSchemeBillGroupModel.getDdoCode());
		objEntity.setDistrictId(mpgSchemeBillGroupModel.getDistrictId());
		objEntity.setBillGroupId(mpgSchemeBillGroupModel.getBillGroupId());
		objEntity.setDdoMapId(mpgSchemeBillGroupModel.getDdoMapId());
	//	objEntity.setSchemeId(mpgSchemeBillGroupModel.getSchemeId());
		objEntity.setSchemeId(59);
		objEntity.setCreatedDate(new Date());
		objEntity.setIsActive('1');

		Serializable id = mpgSchemeBillGroupRepo.saveMpgSchemeBillGroup(objEntity);
		return (int) id;
	}

	@Override
	public List<Object> findAllMpgSchemeBillGroup() {
		return mpgSchemeBillGroupRepo.findAllMpgSchemeBillGroup();
	}
	
	
	
	@Override
	public MpgSchemeBillGroupEntity findMpgSchemeBillGroupBySchemeBillGroupId(int id) {
		MpgSchemeBillGroupEntity mpgSchemeBillGroupEntity = mpgSchemeBillGroupRepo.findMpgSchemeBillGroupBySchemeBillGroupId(id);
		return mpgSchemeBillGroupEntity;
	}
	
	@Override
	public MpgSchemeBillGroupEntity findMpgSchemeBillGroupByBillGroupIdForReject(int id) {
		MpgSchemeBillGroupEntity mpgSchemeBillGroupEntity = mpgSchemeBillGroupRepo.findMpgSchemeBillGroupBySchemeBillGroupId(id);
		if(mpgSchemeBillGroupEntity != null) {
			mpgSchemeBillGroupEntity.setIsActive('0');	// REJECTED
			mpgSchemeBillGroupEntity.setUpdatedDate(new Date());
			mpgSchemeBillGroupRepo.updateMpgSchemeBillGroupStatus(mpgSchemeBillGroupEntity);
		}
		return mpgSchemeBillGroupEntity;
	}
	
	@Override
	public String editSchemeBillGroupSave(MpgSchemeBillGroupEntity mpgSchemeBillGroupEntity) {
		MpgSchemeBillGroupEntity objDeptForReject = mpgSchemeBillGroupRepo.findMpgSchemeBillGroupBySchemeBillGroupId(mpgSchemeBillGroupEntity.getSchemebillGroupId());
		if(objDeptForReject != null) {
			
			objDeptForReject.setSchemebillGroupId(mpgSchemeBillGroupEntity.getSchemebillGroupId());
			objDeptForReject.setBillDescription(mpgSchemeBillGroupEntity.getBillDescription());
			objDeptForReject.setScheme_name(mpgSchemeBillGroupEntity.getScheme_name());
			objDeptForReject.setSchemeCode(mpgSchemeBillGroupEntity.getSchemeCode());
			objDeptForReject.setDdoCode(mpgSchemeBillGroupEntity.getDdoCode());
			objDeptForReject.setDistrictId(mpgSchemeBillGroupEntity.getDistrictId());
			objDeptForReject.setIsActive(mpgSchemeBillGroupEntity.getIsActive());	// UPDATED
			objDeptForReject.setUpdatedDate(new Date());
			mpgSchemeBillGroupRepo.updateMpgSchemeBillGroupStatus(objDeptForReject);
		}
		return "UPDATED";
	}
	
	@Override
	public MpgSchemeBillGroupEntity findAllMpgSchemeBillGroupbyParameter(int id) {
		MpgSchemeBillGroupEntity mpgSchemeBillGroupEntity = mpgSchemeBillGroupRepo.findMpgSchemeBillGroupBySchemeBillGroupId(id);
		return mpgSchemeBillGroupEntity;
	}
	
	public List<MpgSchemeBillGroupEntity> findMpgSchemeBillGroup()
	{
		return mpgSchemeBillGroupRepo.findMpgSchemeBillGroup();
	}

	@Override
	public List<MpgSchemeBillGroupEntity> findAllMpgSchemeBillGroupByDDOCode(String DDOCode,int roleid) {
		
		return mpgSchemeBillGroupRepo.findMpgSchemeBillGroupByDDOCode(DDOCode,roleid);
		return null;
	}
}
*/